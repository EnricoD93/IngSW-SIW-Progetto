function addExam() {
	console.log("sono qua");
	var table = document.getElementById("table");
	var cella = table.insertRow(1);
	var l = document.getElementById("table").rows.length - 1;
	console.log(l);
	cella.innerHTML = "<link href=\"css/style.css\" rel=\"stylesheet\"><td><input class=\"form-control\" id=\"value" + l
			+ "\" type=\"number\" ></td><td><input class=\"form-control\"id=\"crediti" + l
			+ "\" type=\"number\" ></td>" + "<td><input class=\"form-control\" id=\"voto" + l
			+ "\" type=\"number\" ></td>";
}
function calcolaMedia() {
	var l = document.getElementById("table").rows.length - 1;
	var sommaCrediti = 0;
	var somma = 0;
	var voto;
	for (var i = 1; i <= l; i++) {
		var cred = "#crediti" + i;
		var vot = "#voto" + i;
		var crediti = $(cred).val();
		var voto = $(vot).val();
		if (voto > 30) {
			swal("Valore non valido", "Hai inserito un valore superiore a 30!",
					'warning');
		} else if (voto == "") {

		} else if (voto < 18) {
			swal("Valore non valido", "Hai inserito un valore inferiore a 18!",
					'warning');
		} else {
			somma += crediti * voto;
			parseInt(crediti);
			sommaCrediti = parseInt(crediti) + parseInt(sommaCrediti);
		}
	}
	var media = somma / sommaCrediti;
	console.log(media);
	if(media>0){
	document.getElementById("media").innerHTML = media;
	}else{
		media="Non hai inserito nessun valore!"
		document.getElementById("media").innerHTML = media;
	}
}