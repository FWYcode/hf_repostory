/**
 * Created by 123 on 2019-8-26.
 */
var stopClient = null;
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
    var socket = new SockJS('/gs-guide-websocket');
    stopClient = Stomp.over(socket);
    stopClient.connect({}, function (frame) {
        setConnected(true);
        console.log("connected" + frame)
        stopClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        })
    },function (e) {
        console.log(e);
    });
}
function disconnect() {
    if (stopClient != null) {
        stopClient.disconnect();
    }
    setConnected(false);
    console.log("disconnect")
}
function sendName() {
    stopClient.send("/app/hello", {}, JSON.stringify({'content': $("#content").val()}))
}
function showGreeting(msg) {
    $("#greetings").append("<tr><td>" + msg + "</td></tr>");
}
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $('#connect').click(function () {
        connect();
    })
    $("#disconnect").click(function () {
        disconnect();
    });
    $('#send').click(function () {
        sendName();
    });
})
