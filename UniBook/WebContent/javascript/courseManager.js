
function iscriviStudente(codice, matricola) {
	swal({
		title : "Inserisci la tua password per iscriverti",
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
				request : "iscrizione"
			},
			success : function(data) {
				swal("Iscrizione avvenuta",
						"L'iscrizione al corso è avvenuta con successo.",
						"success");
				sleep(1300).then(() => {
				window.location.href="page?request=corso&id="+codice;
				});
			},
			error : function(data) {
				if (data.status === 405) {
					swal("Risulti già iscritto a questo corso", "", "error");
				}
				if (data.status === 401) {
					swal("Password errata!", "Riprova.\n", "error");
				}
				if (data.status === 403) {
					swal("Nello stesso giorno ci sono più eventi");
				
				}
			}

		});
	});
};

function iscriviStudenteM(codice) {
	console.log(codice);
	swal({
		title : "Inserisci la matricola dello studente da iscrivere",
		type : "input",
		inputType : "number",
		showCancelButton : true,
		closeOnConfirm : false
	}, function(matricola) {
		$.ajax({
			type : "GET",
			url : "iscrivistudente",
			datatype : 'text',
			data : {
				codice : codice,
				matricola : matricola,
				request : "iscrizioneM"
			},
			success : function(data) {
				swal("Iscrizione avvenuta",
						"L'iscrizione dello studente al corso è avvenuta con successo.",
						"success");
				var studenti= $('students');
				var lastRow=studenti.last();
				var tr=$('<tr></tr>');
				tr.html("<td>${loop.index+1}</td><td><a href=\"page?request=profilo&id=${studente.matricola}\"><div class=\"profile-pic-xs\""
							+"style=\"background-image: url('${studente.profileImagePath}')\"></div>"
								+"</a></td><td scope=\"row\"><a href=\"page?request=profilo&id="+data.matricola+"\">${studente.cognome}&nbsp;${studente.nome}</a></td>"
								+"<td>"+data.matricola+"</td>	<c:if test=\"${currentUser.matricola==currentCourse.docente}\">"
								+"<td>${studente.codicefiscale}</td></c:if><td>${studente.email}</td><td><c:if test=\"${currentUser.matricola==currentCourse.docente}\">"
								+"<button type=\"button\" id=\"eliminaStudente\" onclick=\"javascript:confermaEliminaM(${studente.matricola},${currentCourse.codice})\""
								+"class=\"bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float\"title=\"Elimina Studente\">"
								+"<i class=\"material-icons\">delete</i></button></c:if></td><c:if test=\"${currentCourse.docente==currentUser.matricola}\">"
								+"<td style=\"padding-top: 16px; padding-left: 35px;\"><input type=\"checkbox\" id=\"${studente.matricola}\"name=\"${studente.matricola}\"> " +
								+		"<label for=\"${studente.matricola}\"></label></td></c:if>");
				var lastRow=$('#lastRow');
				lastRow.before(tr);
			},
			error : function(data) {
				if (data.status === 405) {
					swal("Lo studente risulta già iscritto a questo corso", "", "error");
				}
				if (data.status === 403) {
					swal("Lo studente inserito non esiste","", "error");
				
				}
			}

		});
	});
};





function sleep (time) {
	  return new Promise((resolve) => setTimeout(resolve, time));
	}

function confermaElimina(matricola, codice) {
	swal(
			{
				title : "Inserisci la tua password per confermare la cancellazione dell'iscrizione",
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
								request : "cancellazione"
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
function confermaEliminaM(matricola, codice) {
	swal(
			{
				title : "Inserisci la tua password per confermare la cancellazione dell'iscrizione",
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
								request : "cancellazione"
							},
							success : function(data) {
								swal(
										"Eliminazione avvenuta",
										"L'iscrizione al corso è stata cancellata con successo.",
										"success");
								sleep(1300).then(() => {
									window.location.href="page?request=listaStudenti&codice="+codice;
									});
							},
							error : function(data) {
								swal(
										"Lo studente non è iscritto a questo corso",
										"Non puoi cancellare uno studente da un corso al quale non è iscritto",
										"error");
							}

						});
			});

};

function confermaEliminaCorso(matricola, codice) {
	swal(
			{
				title : "Inserisci la tua password per confermare l'eliminazione del Corso",
				type : "input",
				inputType : "password",
				showCancelButton : true,
				closeOnConfirm : false
			},
			function(typedPassword) {
				$
						.ajax({
							type : "POST",
							url : "createCourse",
							datatype : 'text',
							data : {
								codice : codice,
								matricola : matricola,
								typedPassword : typedPassword,
								request : "cancel"
							},
							success : function(data) {
								swal(
										"Eliminazione avvenuta",
										"Il corso è stato cancellata con successo.",
										"success");
							},
							error : function(data) {
								if (data.status === 405) {
									swal("Non sei il titolare di questo corso", "Non puoi cancellare un corso del quale non sei titolare", "error");
								}
								if (data.status === 401) {
									swal("Password errata!", "Riprova.\n", "error");
								}
							}

						});
			});

};
function salvaPresenze(){
		var saved="null";
		console.log("in function");
		console.log( $('#lezioneid').val());
	$.ajax({
		type : "GET",
		url : "showcourse",
		async: false,
		datatype : 'text',
		data : {
			codice : $('#codice').val(),
			lezione : $('#lezioneid').val(),
			richiesta : "studentiCorsoPresenze"
		},
		success : function(data) {
			console.log("data");
			for (var i = 0; i < data.result.length; i++){
					console.log(data.result[i].studente);
					var str=data.result[i].studente;
					var check = document.getElementById(str);
					
						if(check.checked==1){
						$.ajax({
							type : "GET",
							url : "showcourse",
							async: false,
							datatype : 'text',
							data : {
								codice : $('#codice').val(),
								lezione : $('#lezioneid').val(),
								richiesta : "salvaPresenza",
								matricola : data.result[i].studente,
								checked : check.checked
							},
							success: function(){
								saved="true";	
							}
						});
						
						}
						saved="false";
			}
			if(saved=="true" || saved=="false"){
				swal(
					"Presenze salvate",
					"Presenze salvate con successo.",
					"success");
			}
		
		},
		error:function(){	swal(
		"Seleziona una lezione per poter salvare le presenze!");}
	
	});
};



