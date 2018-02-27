function calcolaMedia() {
	var cdl = $('#corso').val();
	var button = $('#calcola');
	button.attr("href", "javascript:chiudiMedia()");
	button.text("Chiudi");
	$('#calcola').attr("hidden", true);
	$('#chiudi').attr("hidden", false);
	console.log(cdl);
	$('#listaesami').attr("hidden", false);
	if (cdl == "0733") {
		$('#matematica').attr("hidden", true);
		$('#informatica').attr("hidden", false);
	}
	if (cdl == "0726") {
		$('#informatica').attr("hidden", true);
		$('#matematica').attr("hidden", false);
	}
	$('#exam').click();
}
function chiudiMedia() {
	var button = $('#calcola');
	button.attr("href", "javascript:calcolaMedia()");
	button.text("Calcola media");
	$('#chiudi').attr("hidden", true);
	$('#calcola').attr("hidden", false);
	$('#listaesami').attr("hidden", true);
}