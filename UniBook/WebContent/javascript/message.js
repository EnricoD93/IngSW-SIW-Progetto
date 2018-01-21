$(document)
		.ready(
				function() {
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
										console.log(dest);
										console.log(mitt);
										console.log(text);

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
														console.log(data.date);
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
														$('html, body').animate({
															scrollTop : $('body').offset().bottom
														}, 'slow');
														window.scrollTo(0, document.body.scrollHeight);
													}
												});
									});
				});