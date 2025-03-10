'use strict';
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var chatId = document.querySelector('#chatid').value;
var username = document.querySelector('#name').value.trim();
var stompClient= null;
var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];
sessionStorage.setItem('name', username);
// window.onload = function() {
//     // Проверяем наличие имени пользователя в localStorage
//     username = sessionStorage.getItem('name')
//
//
//     if (username) {
//         // Если имя пользователя найдено, подключаемся к вебсокету
//         var socket = new SockJS('/ws')
//         stompClient = Stomp.over(socket)
//         stompClient.connect({}, onConnected, onError)
//     } else {
//         // Если имени нет, отображаем страницу ввода имени
//         usernamePage.classList.remove('hidden');
//     }
//
//     // Обработчик события для отправки сообщения
//     messageForm.addEventListener('submit', sendMessage, true);
// };




function connect(event){
    username = document.querySelector("#name").value.trim();
    if(username){
        // usernamePage.classList.add('hidden');
        // chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws')
        stompClient = Stomp.over(socket)
        stompClient.connect({}, onConnected, onError)
    }else {
        console.log(username)
    }
    event.preventDefault();
}

function onError(){
    connectingElement.textContent = "Не получилось.";
    connectingElement.style.color = 'red';
}

// function onMessageReceived(payload){
//     var message = JSON.parse(payload.body);
//
//     var messageElement = document.createElement('li');
//
//     if(message.type === 'JOIN') {
//         messageElement.classList.add('event-message');
//         message.content = message.sender + ' joined!';
//     } else if (message.type === 'LEAVE') {
//         messageElement.classList.add('event-message');
//         message.content = message.sender + ' left!';
//     } else {
//         messageElement.classList.add('chat-message');
//
//         var avatarElement = document.createElement('i');
//         var avatarText = document.createTextNode(message.sender[0]);
//         avatarElement.appendChild(avatarText);
//         avatarElement.style['background-color'] = getAvatarColor(message.sender);
//
//         messageElement.appendChild(avatarElement);
//
//         var usernameElement = document.createElement('span');
//         var usernameText = document.createTextNode(message.sender);
//         usernameElement.appendChild(usernameText);
//         messageElement.appendChild(usernameElement);
//     }
//
//     var textElement = document.createElement('p');
//     var messageText = document.createTextNode(message.message);
//     textElement.appendChild(messageText);
//
//     messageElement.appendChild(textElement);
//
//     messageArea.appendChild(messageElement);
//     messageArea.scrollTop = messageArea.scrollHeight;
// }

// function getAvatarColor(messageSender) {
//     var hash = 0;
//     for (var i = 0; i < messageSender.length; i++) {
//         hash = 31 * hash + messageSender.charCodeAt(i);
//     }
//     var index = Math.abs(hash % colors.length);
//     return colors[index];
// }
//
//
function onConnected(){
    stompClient.subscribe('/topic/public', onMessageReceived)
    stompClient.send('/app/chat.addUser',JSON.stringify({Sender: username, type: 'JOIN'}));
    connectingElement.classList.add('hidden')
}




function sendMessage(event){
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient){
        var chatMessage = {
            sender: username,
            message: messageInput.value,
            type: "CHAT"
        };
        stompClient.send('/app/chat.${chatid}.sendMessage', {}, JSON.stringify(chatMessage));
        messageInput.value = '';

    }
    event.preventDefault()
}

messageForm.addEventListener('submit', connect, true);


