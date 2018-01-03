$(document).ready(getCentralSection);

function getCentralSection(){
	$('#profilo').click(function() {

		$.ajax({
			url :  'profilo.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);
			
		});
	});
	$('#corsiPersonali').click(function() {

		$.ajax({
			url :  'corsiPersonali.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);
			
		});
	});
	$('#aule').click(function() {

		$.ajax({
			url :  'aule.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);
			
		});
	});
}