function removeExam(corso) {
	$.ajax({
		url : "examsManager",
		type : "POST",
		datatype : 'text',
		data : {
			request : "rimuoviEsame",
			corso : corso
		},
		success : function() {
			swal("Eliminato", "Esame eliminato con successo", "success").then(()=>{window.location.href="page?request=esami"});
		}
	});
};

function addExam(corso) {
	var div = document.createElement("div");
	var span = document.createElement("span");
	var date = document.createElement("input");
	var div2 = document.createElement("div");
	div2.classList = "input-group";
	date.id = "dataesame";
	date.name = "dataesame";
	date.type = "text";
	date.classList = "datepicker form-control";
	date.placeholder = "Seleziona una data";
	date.required = true;
	span.classList = "input-group-addon";
	span.append("Voto: ")
	var voti = [ "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
			"28", "29", "30", "30L" ];
	var selectList = document.createElement("select");
	selectList.id = "votoesame";
	for (var i = 0; i < voti.length; i++) {
		var option = document.createElement("option");
		option.value = voti[i];
		option.text = voti[i];
		selectList.appendChild(option);
	}
	div2.appendChild(span);
	div2.style = "margin-top: 10px;";
	selectList.tabindex = "-98";
	selectList.style = "display: inline;";
	div2.appendChild(selectList);
	div.appendChild(date);
	div.appendChild(div2);
	swal({

		title : 'Inserisci Data e Voto',
		text : 'Inserisci data e voto di conseguimento dell\'esame',
		content : div

	}).then(()=>{
		$.ajax({
		url : "examsManager",
		type : "POST",
		datatype : 'text',
		data : {
			request : "aggiungiEsame",
			corso : corso,
			data : $('#dataesame').val(),
			voto : $('#votoesame').val()
		},
		success : function() {
			swal("Aggiunto", "Esame aggiunto con successo", "success").then(()=>{window.location.href="page?request=esami"});
		},
		error: function(){
			swal("Ops", "Qualcosa è andato storto", "error");
		}
	});});
	$('#dataesame').bootstrapMaterialDatePicker('setDate', moment());
};