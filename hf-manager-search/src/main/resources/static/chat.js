/**
 * Created by 123 on 2019-8-26.
 */
var stopClient = null;
var socket=null;
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
function initSocket() {
    socket = new SockJS('/gs-guide-websocket');
    stopClient = Stomp.over(socket);
}
function connect() {
    stopClient.connect({"id": $('#id').val()}, function (frame) {
        setConnected(true);
        console.log("connected" + frame);
        stopClient.subscribe('/topic/chat/' + $('#id').val(), function (greeting) {
            showGreeting(greeting.body);
        }, {"id": "host_" + $('#id').val()});
    }, function (e) {
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
    stopClient.send("/app/chat", {}, JSON.stringify({
        'content': $("#content").val(),
        'id': $('#id').val(), 'pid': $('#pid').val()
    }));
    showGreeting("me:" + $('#content').val())
}
function showGreeting(msg) {
    $("#greetings").append("<tr><td>" + msg + "</td></tr>");
}
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $('#connect').click(function () {
        initSocket();
        connect();
        var val = $('#count').text();
        var count = ++val;
        console.log(count)
        $('#count').text(count);
    })
    $("#disconnect").click(function () {
        disconnect();
        var val = $('#count').text();
        var count = --val;
        console.log(count)
        $('#count').text(count);
    });
    $('#send').click(function () {
        sendName();
    });
})
