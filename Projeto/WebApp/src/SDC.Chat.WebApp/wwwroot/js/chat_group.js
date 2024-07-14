"use strict";

var groupId = document.getElementById("groupId").value;

var connection = new signalR.HubConnectionBuilder().withUrl("/chatHub?groupId=" + groupId).build();

document.getElementById("sendButton").disabled = true;

connection.on("ReceiveMessage", function (user, message, data) {
    var li = document.createElement("li");
    document.getElementById("messagesList").appendChild(li);
    li.textContent = `${user} disse ${message} em ${data}`;
});

connection.onclose(async () => {
    console.log("Disconnected")

    try {
        console.log("Trying to reconnect");
        await connection.start();
        console.log("Reconnected");
    } catch (err) {
        console.log(err);
        setTimeout(start, 5000);
    }
});

connection.start().then(function () {
    document.getElementById("sendButton").disabled = false;
}).catch(function (err) {
    return console.error(err.toString());
});

document.getElementById("sendButton").addEventListener("click", function (event) {
    var message = document.getElementById("messageInput").value;
    var groupId = document.getElementById("groupId").value;
    connection.invoke("SendGroupMessage", message, groupId).catch(function (err) {
        return console.error(err.toString());
    });
    event.preventDefault();
});