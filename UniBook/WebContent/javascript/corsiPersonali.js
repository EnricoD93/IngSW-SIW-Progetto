$(document).ready(function() {
	$('#corsiPersonali').click(function() {

		$.ajax({
			url :  'corsiPersonali.jsp',
			method : 'GET'
		}).then(function(data) {
			$('#corsi').html(data);
			
		});
	});

});