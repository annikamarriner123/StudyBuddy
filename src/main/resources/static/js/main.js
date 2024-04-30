'use strict';

var chatPage = document.querySelector('.chat-app');
var messageForm = document.querySelector('#message-input'); 
var messageInput = document.querySelector('#messageBox');
var messageArea = document.querySelector('#messages'); 

var stompClient = null;
var username = "Anonymous"; 

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
    var username = "Anonomus";
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username, // Assume username is stored and available
            content: messageContent,
            type: 'CHAT'
        };

        //appendMessage(chatMessage);

        // Send the message to the server
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    } else {
        console.log("Failed to send message: Message content is empty or Stomp client is not initialized.");
    }
}
function appendMessage(message) {
    var messageElement = document.createElement('li');

    // This checks if the sender is the user and applies 'sent' or 'received'
    if (message.sender === username) {
        messageElement.classList.add('chat-message', 'sent');
    } else {
        messageElement.classList.add('chat-message', 'receusived');
    }

    var usernameElement = document.createElement('strong');
   // var usernameText = document.createTextNode(message.sender + ': ');
    var usernameText = document.createTextNode(username + ': ');
    usernameElement.appendChild(usernameText);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(usernameElement);
    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
    
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    
    if (message.sender !== username) { // Check if the message is from another user
        appendMessage(message);
    }else{
        return;
    }
}

messageForm.addEventListener('submit', sendMessage);

connect();
