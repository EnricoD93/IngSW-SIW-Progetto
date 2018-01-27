function changeProfile(){
	$('#profile').addClass("hidden");
	$('#modify').removeClass("hidden");
};
function modificaEmail(){
	
	console.log("click");
	$('#email').removeClass("hidden");
	$('#modificaEmail').addClass("hidden");
	$('#modificaEmailConfirm').removeClass("hidden");

};
function modificaPassword(){
	
	console.log("click");
	$('#password').removeClass("hidden");
	$('#modificaPassword').addClass("hidden");
	$('#modificaPasswordConfirm').removeClass("hidden");

};
function confermaModificaPassword(){
	var valore=$('#password').val();
	console.log(valore);
	$.ajax({
		url : 'profileManager',
		type : 'POST',
		data :{
			request : "modificaPassword",
			inputPassword: $('#password').val()
		},
		success : function() {
			swal("Password modificata","La tua password è stata modificata!","success");
		},
	 	error: function() { 
	 		swal("Password non modificata","La tua password non è stata modificata!","error");
     }    
	});
	
};