'use strict';
// DOM elements
var chatPage = document.querySelector('.chat-app');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#messageBox');
var messageArea = document.querySelector('#messages');
var createChatroomForm = document.querySelector('#create-chatroom-form');
var chatroomNameInput = document.querySelector('#chatroom-name');
var chatroomSelect = document.querySelector('#chatroom-select');
var chatList = document.querySelector('#chat-list');
// Websocket and user details
var stompClient = null;
var username = "Anonymous";
var userId = ""; // Initial userId
var currentSubscription = null; // Track the current subscription

// Event listeners
document.addEventListener('DOMContentLoaded', function () {
    initializeChat();
    createChatroomForm.addEventListener('submit', createChatRoom);
    messageForm.addEventListener('submit', sendMessage);
    chatroomSelect.addEventListener('change', function () {
        var selectedChatRoomId = chatroomSelect.value;
        changeChatRoomSubscription(selectedChatRoomId);
    });
});

function initializeChat() {
    fetchUserDetails().then(() => {
        fetchChatRooms().then(() => {
            connect();
        });
    }).catch(error => {
        connect(); // Optionally connect with "Anonymous" if user details fail
    });
}

function fetchUserDetails() {
    return fetch('/api/user/details', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to fetch user details');
        }
        return response.json();
    })
    .then(data => {
        if (data.username && data.userId) {
            username = data.username;
            userId = data.userId;
        }
    })
    .catch(error => console.error('Error fetching user details:', error));
}

function fetchChatRooms() {
    return fetch('/api/chatrooms')
        .then(response => response.json())
        .then(data => {
            chatroomSelect.innerHTML = ''; // Clear existing chatrooms
            chatList.innerHTML = ''; // Clear existing chatrooms in the list
            if (Array.isArray(data)) {
                data.forEach(room => {
                    if (room.name && room.chatRoomId) {
                        var roomElement = document.createElement('option');
                        roomElement.textContent = room.name;
                        roomElement.value = room.chatRoomId;
                        chatroomSelect.appendChild(roomElement);

                        // Also add the room to the chat list
                        var listItem = document.createElement('li');
                        listItem.textContent = room.name;

                        // Create a delete button for the chatroom
                        var deleteButton = document.createElement('button');
                        deleteButton.textContent = 'x';
                        deleteButton.addEventListener('click', function() {
                            deleteChatRoom(room.chatRoomId);
                        });

                        listItem.dataset.chatroomId = room.chatRoomId;
                        listItem.addEventListener('click', function() {
                            chatroomSelect.value = room.chatRoomId;
                            changeChatRoomSubscription(room.chatRoomId);
                        });
                        listItem.appendChild(deleteButton);
                        chatList.appendChild(listItem);
                    } else {
                        console.error('Invalid chatroom data:', room);
                    }
                });
                if (data.length > 0) {
                    chatroomSelect.value = data[0].chatRoomId; // Select the first chatroom by default
                    changeChatRoomSubscription(data[0].chatRoomId);
                }
            } else {
                console.error('Unexpected response format:', data);
            }
        })
        .catch(error => console.error('Error fetching chat rooms:', error));
}

function deleteChatRoom(chatRoomId) {
    fetch(`/api/chatrooms/${chatRoomId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            // Remove the chatroom from the UI
            var chatRoomElement = document.querySelector(`li[data-chatroom-id="${chatRoomId}"]`);
            if (chatRoomElement) {
                chatRoomElement.remove();
            }
            var chatRoomOption = document.querySelector(`option[value="${chatRoomId}"]`);
            if (chatRoomOption) {
                chatRoomOption.remove();
            }
            // Optionally, you might want to handle the case where the current chatroom is deleted
            if (chatroomSelect.value === chatRoomId.toString()) {
                chatroomSelect.value = ''; // Reset the select input
                messageArea.innerHTML = ''; // Clear messages
            }
        } else {
            console.error('Failed to delete chatroom');
        }
    })
    .catch(error => console.error('Error deleting chatroom:', error));
}

function connect() {
    chatPage.classList.remove('hidden');
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    // Initially subscribe to the first chatroom or a default chatroom
    var initialChatroomId = chatroomSelect.value;
    subscribeToChatRoom(initialChatroomId);
}

function subscribeToChatRoom(chatroomId) {
    if (stompClient) {
        if (currentSubscription) {
            currentSubscription.unsubscribe(); // Unsubscribe from the previous chatroom
        }
        currentSubscription = stompClient.subscribe('/topic/chatroom/' + chatroomId, onMessageReceived);
        stompClient.subscribe('/topic/chatroom/' + chatroomId + '/users', onUsersReceived);
        loadMessages(chatroomId); // Ensure messages are loaded when switching chatrooms
        loadUsers(chatroomId);
    }
}

function loadMessages(chatroomId) {
    fetch('/api/chatrooms/' + chatroomId + '/messages')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok: ' + response.statusText);
            }
            return response.json();
        })
        .then(messages => {
            if (!Array.isArray(messages)) {
                throw new TypeError('Expected an array of messages');
            }
            messageArea.innerHTML = ''; // Clear current messages
            messages.forEach(appendMessage); // Add fetched messages
        })
        .catch(error => console.error('Error loading messages:', error));
}

function loadUsers(chatroomId) {
    stompClient.send("/app/chat.loadUsers/" + chatroomId, {}, JSON.stringify({}));
}

function onUsersReceived(payload) {
    var users = JSON.parse(payload.body);
    // Implement logic to display users if necessary
}

function onError(error) {
    console.log("Couldn't connect to websocket:", error);
}

function sendMessage(event) {
    event.preventDefault();
    var messageContent = messageInput.value.trim();
    var selectedChatRoomId = chatroomSelect.value;
    if (messageContent && stompClient) {
        var chatMessage = {
            senderName: username,
            userId: userId,
            chatroomId: selectedChatRoomId,
            content: messageContent,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage/" + selectedChatRoomId, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
}

function appendMessage(message) {
    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');
    if (message.userId === userId) {
        messageElement.classList.add('sent-message'); // Add a class for sent messages
    } else {
        messageElement.classList.add('received-message'); // Add a class for received messages
    }
    var usernameElement = document.createElement('strong');
    usernameElement.appendChild(document.createTextNode(message.senderName + ': '));
    var textElement = document.createElement('p');
    textElement.appendChild(document.createTextNode(message.content));
    messageElement.appendChild(usernameElement);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function onMessageReceived(payload) {
    try {
        var message = JSON.parse(payload.body);
        appendMessage(message); // Indicate this is a received message
    } catch (e) {
        console.error("Error parsing JSON:", e, "Payload:", payload.body); // Log the error and payload
    }
}

function createChatRoom(event) {
    event.preventDefault();
    var chatroomName = chatroomNameInput.value.trim();
    if (chatroomName) {
        fetch('/api/chatrooms', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: chatroomName}) // Send chatroomName as a JSON object
        })
        .then(response => response.json())
        .then(data => {
            // Add the new chatroom to the select and list
            var roomElement = document.createElement('option');
            roomElement.textContent = data.name;
            roomElement.value = data.chatRoomId;
            chatroomSelect.appendChild(roomElement);
            var listItem = document.createElement('li');
            listItem.textContent = data.name;
            listItem.dataset.chatroomId = data.chatRoomId;
            listItem.addEventListener('click', function () {
                chatroomSelect.value = data.chatRoomId;
                changeChatRoomSubscription(data.chatRoomId);
            });
            chatList.appendChild(listItem);
            // Select the new chatroom
            chatroomSelect.value = data.chatRoomId;
            changeChatRoomSubscription(data.chatRoomId);
        })
        .catch(error => console.error('Error creating chatroom:', error));
    } else {
        console.log("Chatroom name is empty");
    }
}

function changeChatRoomSubscription(chatroomId) {
    if (stompClient) {
        // Unsubscribe from the current chatroom
        if (currentSubscription) {
            currentSubscription.unsubscribe();
        }

        // Subscribe to the new chatroom
        subscribeToChatRoom(chatroomId);
        // Clear the message area
        messageArea.innerHTML = '';
    }
}
