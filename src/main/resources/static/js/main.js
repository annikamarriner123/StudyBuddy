var stompClient = null;
var currentSubscription = null;

function connect() {
    var socket = new SockJS('/ws'); // Ensure this path matches your actual WebSocket endpoint
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        // Optionally set up initial room subscription here if needed
    }, function(error) {
        console.log('Connection error: ' + error);
    });
}

function joinRoom(roomId) {
    if (stompClient && stompClient.connected) {
        if (currentSubscription) {
            currentSubscription.unsubscribe(); // Unsubscribe from the previous room
        }
        currentSubscription = stompClient.subscribe('/topic/public', function (message){//('/topic/chatroom/' + roomId, function (message) {
            showMessage(JSON.parse(message.body).content);
        });
        console.log('Joined room: ' + roomId);
    } else {
        console.log('Stomp client is not connected.');
        // Reconnect logic or handling could go here
    }
}

function sendMessage() {
    var messageContent = document.getElementById('messageBox').value;
    if (messageContent && stompClient && stompClient.connected) {
        var chatMessage = {
            content: messageContent,
            // Include other necessary properties like senderId if required
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        document.getElementById('messageBox').value = '';
    } else {
        console.log('Cannot send message. Stomp client is not connected.');
    }
}

function showMessage(message) {
    var messages = document.getElementById('messages');
    var messageElement = document.createElement('div');
    messageElement.innerHTML = message; // Adjust according to how you want messages displayed
    messages.appendChild(messageElement);
}

connect();
