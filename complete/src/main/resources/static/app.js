let userId = Math.random().toString(36).substring(2, 7)

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket?playerId=' + userId
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/user/topic/privateChat', (message) => {
        showPrivateChat(JSON.parse(message.body));
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function privateChat() {
    stompClient.publish({
        destination: "/app/privateChat",
        body: JSON.stringify({
            'toUser': $("#toUser").val(),
            'message': $("#message").val()
        })
    });
}

function showPrivateChat(message) {
    $("#greetings").append("<tr><td>" + message.fromUser + ' > ' + message.toUser + ":" + message.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#send").click(() => privateChat());
});

