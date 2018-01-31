$(document).ready(function() {
	checkMessages();
	checkNotifications();
	window.setInterval(function() {
		checkMessages();
		checkNotifications();
	}, 60000)
});

function checkMessages() {
	var messages;
	var user = $('#curUser').val();
	$.ajax({
		type : "GET",
		url : "checkNotifications",
		dataType : 'text',
		async: true,
		data : {
			user : user,
			request : "message"
		},
		success : function(data) {
			var json = JSON.parse(data);
			messages = json.number;
			if(messages!=0)
			document.getElementById("msgcount").innerHTML=messages;
		}
	});

	return messages;
};

function checkNotifications() {
	var notifications;
	var user = $('#curUser').val();
	$.ajax({
		type : "GET",
		url : "checkNotifications",
		dataType : 'text',
		async: true,
		data : {
			user : user,
			request : "notification"
		},
		success : function(data) {
			var json = JSON.parse(data);
			notifications = json.number;
			if(notifications!=0)
			document.getElementById("notifycount").innerHTML=notifications;
		}
	});

	return notifications;
};