function iscriviStudente(codice, matricola) {
	swal({
		title : "Inserisci la tua password per iscriverti:",
		type : "input",
		inputType : "password",
		showCancelButton : true,
		closeOnConfirm : false
	}, function(typedPassword) {
		$.ajax({
			type : "GET",
			url : "iscrivistudente",
			datatype : 'text',
			data : {
				codice : codice,
				matricola : matricola,
				typedPassword : typedPassword,
				richiesta : "iscrizione"
			},
			success : function(data) {
				swal("Iscrizione avvenuta",
						"L'iscrizione al corso è avvenuta con successo.",
						"success");
				window.location.href="page?request=corso&id="+codice;
			},
			error : function(data) {
				alert(data.status);
				if (data.status === 405) {
					swal("Risulti già iscritto a questo corso", "", "error");
				}
				if (data.status === 401) {
					swal("Password errata!", "Riprova.\n", "error");
				}
			}

		});
	});
};

function confermaEliminaStudente(matricola, codice) {
	swal(
			{
				title : "Inserisci la tua password per confermare la cancellazione:",
				type : "input",
				inputType : "password",
				showCancelButton : true,
				closeOnConfirm : false
			},
			function(typedPassword) {
				$
						.ajax({
							type : "GET",
							url : "iscrivistudente",
							datatype : 'text',
							data : {
								codice : codice,
								matricola : matricola,
								typedPassword : typedPassword,
								richiesta : "cancellazione"
							},
							success : function(data) {
								swal(
										"Eliminazione avvenuta",
										"L'iscrizione al corso è stata cancellata con successo.",
										"success");
							},
							error : function(data) {
								swal(
										"Non risulti iscritto a questo corso",
										"Non puoi cancellarti da un corso al quale non sei iscritto",
										"error");
							}

						});
			});

};