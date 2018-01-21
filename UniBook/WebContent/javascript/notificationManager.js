$(document).ready(function() {
	window.setInterval(function() {
		checkMessages();
		checkNotifications();
	}, 3000)
});

function checkMessages() {
	var messages;
	var user = $('#curUser').val();
	console.log(user);
	$.ajax({
		type : "GET",
		url : "checkNotifications",
		dataType : 'text',
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

};