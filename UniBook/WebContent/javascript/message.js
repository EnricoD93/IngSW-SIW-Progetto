$(document)
		.ready(
				function() {
					window.setInterval(function() {
						checkNewMessage();
					}, 3000);
					$('#text').keypress(function(e) {
						if (e.keyCode == 13)
							$('#send').click();
					});
					$('#send')
							.click(
									function() {
										var dest = $('#dest').val();
										var mitt = $('#mitt').val();
										var text = $('#text').val();

										$
												.ajax({
													url : 'page',
													method : 'POST',
													datatype : 'text',
													data : {
														request : "inviaMessaggio",
														mitt : mitt,
														dest : dest,
														text : text
													},
													success : function(data) {
														var messages = $('messages');
														var lastRow = messages
																.last();
														var div = $('<div></div>');
														div
																.html("<div class=\"card\" style=\"border-radius: 5px; margin-left: 10%\"><div class=\"mycontainer darker\">"
																		+ "<p style=\"font-size: 16px;\">"
																		+ text
																		+ "</p><span class=\"time-right\">"
																		+ data.date
																		+ "&thinsp;</span>"
																		+ "</div>	</div>");
														var lastRow = $('#lastRow');
														lastRow.before(div);
														document
																.getElementById("text").value = "";
														$('html, body')
																.animate(
																		{
																			scrollTop : $(
																					'body')
																					.offset().bottom
																		},
																		'slow');
														window
																.scrollTo(
																		0,
																		document.body.scrollHeight);
													}
												});
									});
				});
function checkNewMessage() {
	var mitt = $('#mitt').val();
	$
			.ajax({
				url : 'checkNotifications',
				method : 'GET',
				datatype : 'text',
				data : {
					request : "newmessage",
					user : mitt
				},
				success : function(data) {
					console.log(data);
					var messages = $('messages');
					var lastRow = messages.last();
					for (var i = 0; i < data.lenght; i++) {
						console.log(data[i].text);
						var div = $('<div></div>');
						div
								.html("<div class=\"card\" style=\"border-radius: 5px; margin-right: 10%\"><div class=\"mycontainer\">"
										+ "<p style=\"font-size: 16px;\">"
										+ data[i].text
										+ "</p><span class=\"time-left\">"
										+ data[i].date
										+ "&thinsp;</span>"
										+ "</div> </div>");
						var lastRow = $('#lastRow');
						lastRow.before(div);
					}
					$('html, body').animate({
						scrollTop : $('body').offset().bottom
					}, 'slow');
					window.scrollTo(0, document.body.scrollHeight);
				}
			});
}
