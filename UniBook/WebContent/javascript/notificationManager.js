$(document).ready(function() {
	window.setInterval(function() {
		checkMessages();
		checkNotifications();
	}, 2000)
});

function checkMessages() {
	var messages;
	var user = $('user');
	$.ajax({
		type : "GET",
		url : "checkNotifications",
		dataType : 'text',
		data : {
			user : user,
			request : "message"
		},
		success : function(data) {
			console.log(data.messages.number);
			messages = data.messages.number;
		}
	});

	return messages;
};

function checkNotifications() {

};