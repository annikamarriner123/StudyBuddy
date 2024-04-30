'use strict';

var chatPage = document.querySelector('.chat-app');
var messageForm = document.querySelector('#message-input'); 
var messageInput = document.querySelector('#messageBox');
var messageArea = document.querySelector('#messages'); 

var stompClient = null;
var username = "Anonymous"; // Default username

document.addEventListener('DOMContentLoaded', function() {
    fetchChatRooms();
});

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect() {
    chatPage.classList.remove('hidden');

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
            {},
            JSON.stringify({sender: username, type: 'JOIN'})
            );
}



function onError(error) {
    console.log("Couldnt connect to websocket");
}
function sendMessage(event) {
    event.preventDefault(); // Prevent default form submission behavior
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username, // Assume username is stored and available
            content: messageContent,
            type: 'CHAT'
        };

        // Append message to the chat area directly (optimistic UI update)
        appendMessage(chatMessage);

        // Send the message to the server
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    } else {
        console.log("Failed to send message: Message content is empty or Stomp client is not initialized.");
    }
}

function appendMessage(message) {
    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    if (message.sender !== username) { // Check if the message is from another user
        appendMessage(message);
    }
}



function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log("Message from other user " + message);

    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}



function fetchChatRooms() {
    fetch('/api/chatrooms')
    .then(response => response.json())
    .then(data => updateChatList(data))
    .catch(error => console.error('Error fetching chat rooms:', error));
}

function updateChatList(chatRooms) {
    const chatList = document.getElementById('chat-list');
    chatList.innerHTML = ''; // Clear existing entries
    chatRooms.forEach(chatRoom => {
        let listItem = document.createElement('li');
        listItem.textContent = chatRoom.name; // Assuming 'name' is the attribute you want to display
        chatList.appendChild(listItem);
    });
}


messageForm.addEventListener('submit', sendMessage);

connect();
