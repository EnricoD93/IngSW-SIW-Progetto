var verified = false;
$(document).ready(buttoninit);
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
			"success").then(()=>{window.location.href="home"});
}
function showWarningMessage() {
	swal(
			"Codice non valido!",
			"Ricontrolla il codice inserito.\n Richiedi un nuovo codice cliccando su \"Invia\" oppure clicca su \"Cambia\" per modificare l'email inserita precedentemente.");
}
function buttoninit() {
	if(localStorage.checked=="true"){
		console.log("cliccato");
		$.ajax({
			type : "POST",
			url : "home",
			datatype : 'text',
			data : {
				username : localStorage.username,
				password : localStorage.password
			},
			success : function(data) {
				window.location.href="home";
			},
			error : function(data) {

				swal("", "Matricola o password errata", "error");

			}

		});
	}
	$("#username").keyup(function(event) {
	    if (event.keyCode === 13) {
	        $("#btnlogin").click();
	    }
	});
	
	$("#password").keyup(function(event) {
	    if (event.keyCode === 13) {
	        $("#btnlogin").click();
	    }
	});
	
	$('#confirmButtonOk').click(function() {
		if (verified == true)
			window.location.replace("index.html");
	});
}

function sendForgotPassword() {
	$.ajax({
		type : "POST",
		url : "sendEmail",
		datatype : 'text',
		data : {
			codice : codice,
			richiesta : "mostraCorso"
		},
		success : function(data) {

			$.ajax({
				url : 'course.jsp',
				method : 'GET',
			}).then(function(data) {
				$('#centralSection').html(data);

			});
		}

	});

}

function checkLogin() {
	if ($('#rememberme').is(':checked')) {
		console.log("entro");
        localStorage.setItem("username",$('#username').val());
        localStorage.setItem("password",$('#password').val());
        localStorage.setItem("checked",true);
    } else {
        localStorage.removeItem("username");
        localStorage.removeItem("password");
        localStorage.removeItem("checked");
    }
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	$.ajax({
		type : "POST",
		url : "home",
		datatype : 'text',
		data : {
			username : username,
			password : password
		},
		success : function(data) {
			window.location.href="home";
		},
		error : function(data) {

			swal("", "Matricola o password errata", "error");

		}

	});
}
function invia(){
	console.log("invio");
	$.ajax({
		type : "GET",
		url : "register",
		success : function(data) {
			swal("Email Inviata", "L'email è stata nuovamente inviata aggiorna la posta ricevuta e inserisci il codice ricevuto", "success");
		}
	});
}

function modificaEmail(){
	
	console.log("click");
	$('#email').removeClass("hidden");
	$('#modificaEmailConfirm').removeClass("hidden");

}
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
						
									swal("Email cambiata!", "La tua Email è stata modificata con successo", "success").then(() => {
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
