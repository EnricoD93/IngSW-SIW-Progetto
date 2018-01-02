$(document).ready(function() {
	$("button").click(function() {

		$.ajax({
			url :  'corsiPersonali.jsp',
			method : 'GET'
		}).then(function(data) {
			$('#corsi').html(data);
			document.getElementById('#corsi').display="none";
			
			console.log(data);
		});
	});

});