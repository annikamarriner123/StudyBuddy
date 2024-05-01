'use strict';

// DOM elements
var chatPage = document.querySelector('.chat-app');
var messageForm = document.querySelector('#message-input');
var messageInput = document.querySelector('#messageBox');
var messageArea = document.querySelector('#messages');

// Websocket and user details
var stompClient = null;
var username = "Anonymous";

// Event listeners
document.addEventListener('DOMContentLoaded', function() {
    initializeChat();
});

function initializeChat() {
    fetchUserDetails().then(() => {
        connect();
        fetchChatRooms();
    }).catch(error => {
        console.error('Initialization failed:', error);
        connect(); // Optionally connect with "Anonymous" if user details fail
    });
}

function fetchUserDetails() {
    return fetch('/api/user/details')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch user details');
            }
            return response.json();
        })
        .then(data => {
            username = data.username || "Anonymous"; // Use fallback if username isn't provided
            console.log("Username set to:", username);
        });
}

function fetchChatRooms() {
    fetch('/chatrooms')
        .then(response => response.json())
        .then(data => {
            data.forEach(room => {
                var roomElement = document.createElement('option');
                roomElement.textContent = room.name;
                document.querySelector('#chatroom-select').appendChild(roomElement);
            });
        })
        .catch(error => console.error('Error fetching chat rooms:', error));
}

function connect() {
    chatPage.classList.remove('hidden');
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: username, type: 'JOIN'}));
}

function onError(error) {
    console.log("Couldn't connect to websocket:", error);
}

function sendMessage(event) {
    event.preventDefault();
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username, 
            content: messageContent, 
            type: 'CHAT'};
        var chatSender = chatMessage.sender;
        console.log("Message sent by " +chatMessage.sender);
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    } else {
        console.log("Failed to send message: Message content is empty or Stomp client is not initialized.");
    }
}

function appendMessage(message) {
    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message', chatSender);
    var usernameElement = document.createElement('strong');
    usernameElement.appendChild(document.createTextNode(message.sender + ': '));
    var textElement = document.createElement('p');
    textElement.appendChild(document.createTextNode(message.content));
    messageElement.appendChild(usernameElement);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight; // Auto-scroll to new message
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log("message recevied");
    if (message.sender !== username) {
        console.log("nessage received from " + message.sender);
        appendMessage(message);
    }
}

messageForm.addEventListener('submit', sendMessage);
