
$(document).ready(confirmButtonOk);

function uploadImage(){
	$('#imagebtn').click();
};

function updateImage() {

	var data = new FormData();
	data.append('request', "profile");
	data.append('image', $("input[type=file]")[0].files[0]);

	$.ajax({
		url : 'updateimage',
		data : data,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function() {
			showUploadOKMessage();
		},
	 	error: function() { 
         showUploadErrMessage();
     }    
	});
	return false;
}

function showUploadErrMessage(){
	swal(
			"Ops qualcosa è andato storto.",
			"Riprova","error");
}

function showUploadOKMessage() {
	swal("Immagine caricata con successo!", "Aggiorna per vedere le modifiche.",
			"success");
}
function confirmButtonOk() {
	$('#confirmButtonOk').click(function() {
		//	window.location.replace("home.jsp");
	});
}