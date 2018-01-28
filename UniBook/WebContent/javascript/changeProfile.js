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
function confermaModificaEmail(){
	$.ajax({
		url : 'profileManager',
		type : 'POST',
		datatype : 'text',
		data :{
			request : "codiceVerificaEmail",
			inputEmail: $('#email').val()
		},
		success : function(data) {
			swal({
				  title: "Codice di Verifica",
				  text: "Inserisci il codice di verifica che ti è stato appena inviato sulla nuova email",
				  content: {
					    element: "input",
					    attributes: {
					      placeholder: "Codice di verifica",
					      type: "text",
					    }}
				}).then(function (code){
					if (code == "") {
						swal.showInputError("Devi inserire il codice di verifica!");
						return false
					}
					if(code==data.jsonCode){
						
						$.ajax({
							url : 'profileManager',
							type : 'POST',
							datatype : 'text',
							data :{
								request : "modificaEmail",
								inputEmail: $('#email').val()
							},success: function(){
						
									swal("Email cambiata!", "La tue Email è stata modificata con successo", "success").then(() => {
											window.location.href="page?request=profilo&id="+data.user;
										})
							 }
						});
					}else{
						swal("Il codice è errato","Il codice inserito non corrisponde a quello inviato!","error");
					}
				})
			
		},
	 	error: function() { 
	 		swal("Email non modificata","La tua email non è stata modificata!","error");
     }    
	});
};