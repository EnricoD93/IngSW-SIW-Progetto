$(document).ready(getCorsi);

function getCorsi(){
	$('#corsiPersonali').click(function() {

		$.ajax({
			url :  'corsiPersonali.jsp',
			method : 'GET'
		}).then(function(data) {
			$('#centralSection').html(data);
			
		});
	});
}