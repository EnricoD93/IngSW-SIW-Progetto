$(document).ready(function() {
	$("button").click(function() {

		$.ajax({
			url :  'corsiPersonali.jsp',
			method : 'GET'
		}).then(function(data) {
			$('h2').html(data);
		});
	});

});