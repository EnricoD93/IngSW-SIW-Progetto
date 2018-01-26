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
			swal("Eliminato", "Esame eliminato con successo", "success");
		}
	});
};

function addExam(corso) {
	var date=document.createElement("input");
	date.id="dataesame";
	date.name="dataesame";
	date.type="text";
	date.classList="datepicker form-control";
	date.placeholder="Seleziona una data";
	date.required=true;
	$('#dataesame').bootstrapMaterialDatePicker('setDate', moment());
	swal({
		
		title : 'Inserisci Data e Voto',
		text : 'Inserisci data e voto di conseguimento dell\'esame',
		content:date
			
	});
	$('#dataesame').click(function() {
		$('#dataesame').bootstrapMaterialDatePicker('setDate', moment());
	});
};