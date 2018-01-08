$(document).ready(getCentralSection);

function getCentralSection() {
	$('#profilo').click(function() {

		$.ajax({
			url : 'profilo.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);

		});
	});
	$('#course').click(function() {

		$.ajax({
			url : 'course.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);

		});
	});
	$('#calendario').click(function() {

		$.ajax({
			url : 'calendarioPersonale.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);

		});
	});

	$('#createCourse').click(function() {
		$.ajax({
			url : 'createCourse.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);

		});
	});

	$('#corsiPersonali').click(function() {

		$.ajax({
			url : 'corsiPersonali.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);

		});
	});
	$('#aule').click(function() {

		$.ajax({
			url : 'aule.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);

		});
	});
	$('#listaStudenti').click(function() {

		$.ajax({
			url : 'studentiIscritti.jsp',
			method : 'GET',
		}).then(function(data) {
			$('#centralSection').html(data);

		});
	});
};
function getCorsoSelezionato(codice) {
	$.ajax({
		type : "GET",
		url : "showcourse",
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
};

function iscriviStudente(codice, matricola) {
	swal({
		title : "Inserisci la tua password per iscriverti:",
		type : "input",
		inputType : "password",
		showCancelButton : true,
		closeOnConfirm : false
	}, function(typedPassword) {
		// verificare password se è giusta
		console.log(typedPassword);

		$.ajax({
			type : "GET",
			url : "iscrivistudente",
			datatype : 'text',
			data : {
				codice : codice,
				matricola : matricola,
				richiesta : "iscrizione"
			},
			success : function(data) {
				swal("Iscrizione avvenuta",
						"L'iscrizione al corso è avvenuta con successo.",
						"success");
			},
			error : function(data) {
				swal("Iscrizione non effettuata!",
						"Risulti già iscritto al corso.\n", "error");

			}

		});
	});
};

function confermaEliminaStudente(matricolaStudente,password,codice) {
	console.log(password);
//	swal(
//			{
//				title : "Inserisci la tua password per cancellare l'iscrizione:",
//				type : "input",
//				inputType : "password",
//				showCancelButton : true,
//				closeOnConfirm : false
//			},
//			function(typedPassword) {
//			//	if (pass == typedPassword) {
//					swal(
//							{
//								title : "Vuoi eliminare l'iscrizione?",
//								text : "Sei sicuro di voler eliminare l'iscrizione ?",
//								type : "warning",
//								showCancelButton : true,
//								confirmButtonColor : "#C4161C",
//								confirmButtonText : "Si, elimina!",
//								cancelButtonText : "Non eliminare",
//								closeOnConfirm : false
//							},
//							function() {
//								console.log("sto eliminando")
//								eliminaIscrizioneStudente(matricolaStudente,pass,codice);
//								console.log("ho eliminato")
//								swal(
//										"Iscrizione eliminata!",
//										"Cancellazione dell'iscrizione è avvenuta con successo.",
//										"success");
//
//							});
//				}else{
//					swal("Pasword errata!",
//							"Riprova.\n", "error");
//				}
//			});
};

function eliminaIscrizioneStudente(matricolaStudente,codice) {
	console.log("FUNCTIONAAA")
	$.ajax({
		type : "GET",
		url : "showcourse",
		datatype : 'text',
		data : {
			richiesta : "eliminaIscrizioneStudente",
			matricolaStudente : matricolaStudente,
			codice : codice
		}

	});
};
//function conferma(password){
//	console.log(password);
//}
