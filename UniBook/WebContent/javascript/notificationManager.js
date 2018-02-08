$(document).ready(function() {
	checkMessages();
	checkNotifications();
	window.setInterval(function() {
		checkMessages();
		checkNotifications();
	}, 10000)
});

function checkMessages() {
	var messages;
	var user = $('#curUser').val();
	$.ajax({
		type : "GET",
		url : "checkNotifications",
		dataType : 'text',
		async : true,
		data : {
			user : user,
			request : "message"
		},
		success : function(data) {
			var json = JSON.parse(data);
			messages = json.number;
			if (messages != 0)
				document.getElementById("msgcount").innerHTML = messages;
		}
	});
};

function checkNotifications() {
	var notifications;
	var lastNotify;
	var user = $('#curUser').val();
	var currentnotification = document.getElementById('notcnt');
	console.log(currentnotification);
	$
			.ajax({
				type : "GET",
				url : "checkNotifications",
				dataType : 'text',
				async : true,
				data : {
					user : user,
					request : "notification"
				},
				success : function(data) {
					var json = JSON.parse(data);
					notifications = json[0];
					lastNotify = json[1];
					console.log(currentnotification.value);
					console.log(notifications.number);
					if (notifications.number != 0)
						document.getElementById("notifycount").innerHTML = notifications.number;
					if (currentnotification.value != notifications.number) {
						showNotification('', lastNotify.testo, "right");
						currentnotification.value = notifications.number;
					}
				}
			});
};

function showNotification(colorName, text, placementFrom, placementAlign,
		animateEnter, animateExit) {
	if (colorName === null || colorName === '') {
		colorName = 'bg-unibook';
	}
	if (text === null || text === '') {
		text = 'Turning standard Bootstrap alerts';
	}
	if (animateEnter === null || animateEnter === '') {
		animateEnter = 'animated fadeInDown';
	}
	if (animateExit === null || animateExit === '') {
		animateExit = 'animated fadeOutUp';
	}
	var allowDismiss = true;

	$
			.notify(
					{
						message : text
					},
					{
						type : colorName,
						allow_dismiss : allowDismiss,
						newest_on_top : true,
						timer : 1000,
						placement : {
							from : placementFrom,
							align : placementAlign
						},
						animate : {
							enter : animateEnter,
							exit : animateExit
						},
						template : '<div data-notify="container" style="bottom:10px;" class="bootstrap-notify-container alert alert-dismissible {0} '
								+ (allowDismiss ? "p-r-35" : "")
								+ '" role="alert">'
								+ '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>'
								+ '<span data-notify="icon"></span> '
								+ '<span data-notify="title">{1}</span> '
								+ '<span data-notify="message">{2}</span>'
								+ '<div class="progress" data-notify="progressbar">'
								+ '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style=" width: 0%;"></div>'
								+ '</div>'
								+ '<a href="{3}" target="{4}" data-notify="url"></a>'
								+ '</div>'
					});
}