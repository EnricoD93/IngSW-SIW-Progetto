var verified = false;
$(document).ready(confirmButtonOk);
function registration(code) {
	var fieldcode = $('#verify').val();
	if (fieldcode == code) {
		verified = true;
		showRegistrationMessage();
	} else {
		verified = false;
		showWarningMessage();
	}
};

function showRegistrationMessage() {
	swal("Registrazione completata!", "Adesso accedi per continuare.",
			"success");
}
function showWarningMessage() {
	swal(
			"Codice non valido!",
			"Ricontrolla il codice inserito.\n Richiedi un nuovo codice cliccando su \"Invia\" oppure clicca su \"Cambia\" per modificare l'email inserita precedentemente.");
}
function confirmButtonOk() {
	$('#confirmButtonOk').click(function() {
		if (verified == true)
			window.location.replace("index.html");
	});
}