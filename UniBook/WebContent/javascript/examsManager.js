function removeExam(corso){
	$.ajax({
		url : "examsManager",
		type : "POST",
		datatype : 'text',
		data:{
			request: "rimuoviEsame",
			corso: corso
		}, success: function(){
			swal("Eliminato", "Esame Eliminato con successo", "success");
		}
	});
};