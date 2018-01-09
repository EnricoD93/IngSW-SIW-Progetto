function iscriviStudente(password, matricola) {
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
	
};

function confermaEliminaStudente(matricolaStudente, password, codice) {
	console.log(password);
	// swal(
	// {
	// title : "Inserisci la tua password per cancellare l'iscrizione:",
	// type : "input",
	// inputType : "password",
	// showCancelButton : true,
	// closeOnConfirm : false
	// },
	// function(typedPassword) {
	// // if (pass == typedPassword) {
	// swal(
	// {
	// title : "Vuoi eliminare l'iscrizione?",
	// text : "Sei sicuro di voler eliminare l'iscrizione ?",
	// type : "warning",
	// showCancelButton : true,
	// confirmButtonColor : "#C4161C",
	// confirmButtonText : "Si, elimina!",
	// cancelButtonText : "Non eliminare",
	// closeOnConfirm : false
	// },
	// function() {
	// console.log("sto eliminando")
	// eliminaIscrizioneStudente(matricolaStudente,pass,codice);
	// console.log("ho eliminato")
	// swal(
	// "Iscrizione eliminata!",
	// "Cancellazione dell'iscrizione è avvenuta con successo.",
	// "success");
	//
	// });
	// }else{
	// swal("Pasword errata!",
	// "Riprova.\n", "error");
	// }
	// });
};

function eliminaIscrizioneStudente(matricolaStudente, codice) {
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
// function conferma(password){
// console.log(password);
// }
