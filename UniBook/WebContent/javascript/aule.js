$(document).ready(getAule);

function getAule(){
	$('#aule').click(function() {

		$.ajax({
			url :  'aule.jsp',
			method : 'GET'
		}).then(function(data) {
			$('#centralSection').html(data);
			
		});
	});
}