var stompClient = null


export function connect() {
    const socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket)
    stompClient.connect({}, frame =>{
        console.log('Connected: ' + frame)
        stompClient.subscribe('/topic/message', message => {
            showGreeting(JSON.parse(message.body).content)
        });
    });
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    setConnected(false)
    console.log("Disconnected")
}

export function sendMessage(message) {
    stompClient.send("/app/messageText", {}, JSON.stringify(message)
}

export function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>")
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault()
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});