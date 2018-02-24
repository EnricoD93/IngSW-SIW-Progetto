var editor2;
var title;
$(document).ready(function(){
	(function () {
									editor2 = CKEDITOR.replace( 'myeditor', {
										removePlugins: 'sourcearea'

									} );
									editor2.on( 'change', function ( ev ) {
										document.getElementById( 'editorcontent2' ).innerHTML = editor2.getData();
										
									} );
									$('#advicetitle').on('change keydown keypress keyup mousedown click mouseup',function(ev){
										console.log($('#advicetitle').val());
										title=$('#advicetitle');
										document.getElementById('titlecontent').innerHTML=$('#advicetitle').val();
									});
								})();
});


function iscriviStudente(codice, matricola) {
	var codice=codice;
	var matricola=matricola;
	swal({
		title : "Inserisci la tua password per iscriverti",
		buttons: {
			  confirm:"Ok",
			  cancel:"Annulla"},
			  closeOnClickOutside: false,
		content: {
		    element: "input",
		    attributes: {
		      placeholder: "Password",
		      type: "password",
		    }}
	}).then((typedPassword) => {
		if(typedPassword!=null){
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
					swal("Password errata!", "Riprova.\n", "error").then(()=>{iscriviStudente(codice, matricola)});
				}
				if (data.status === 403) {
					swal("Nello stesso giorno ci sono più eventi");
				
				}
			}

		});}
	});
};

function iscriviStudenteM(codice) {
	swal({
		title : "Inserisci la matricola dello studente da iscrivere",
		content: {
		    element: "input",
		    attributes: {
		      placeholder: "Matricola",
		      type: "number",
		    }}
	}).then((matricola)=> {
		$.ajax({
			type : "GET",
			url : "iscrivistudente",
			datatype : 'text',
			data : {
				codice : codice,
				matricola : matricola,
				request : "iscrizioneM"
			},
			success : function() {
				swal("Iscrizione avvenuta",
						"L'iscrizione dello studente al corso è avvenuta con successo.",
						"success").then(() => {
							window.location.href="page?request=listaStudenti&codice="+codice;
						});
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
	var matricola=matricola;
	var codice=codice;
	swal(
			{
				title : "Inserisci la tua password per confermare la cancellazione dell'iscrizione",
				buttons: {
					  confirm:"Ok",
					  cancel:"Annulla"},
					  closeOnClickOutside: false,
				content: {
				    element: "input",
				    attributes: {
				      placeholder: "Password",
				      type: "password",
				    }}
			}).then((typedPassword) =>  {
				if(typedPassword!=null){
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
								if (data.status === 405) {
									swal("Lo studente risulta già iscritto a questo corso", "", "error");
								}
								if (data.status === 401) {
									swal("Password errata!", "Riprova.\n", "error").then(()=>{confermaElimina(matricola, codice)});
								}
								if (data.status === 403) {
									swal("Lo studente inserito non esiste","", "error");
								
								}
							}

						});}
			});

};

function confermaEliminaM(matricola, codice) {
	var matricola=matricola;
	var codice=codice;
	swal(
			{
				title : "Inserisci la tua password per confermare la cancellazione dell'iscrizione",
				buttons: {
					  confirm:"Ok",
					  cancel:"Annulla"},
					  closeOnClickOutside: false,
				content: {
				    element: "input",
				    attributes: {
				      placeholder: "Password",
				      type: "password",
				    }}
			}).then((typedPassword) =>   {
				if(typedPassword!=null){
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
								if (data.status === 405) {
									swal("Lo studente risulta già iscritto a questo corso", "", "error");
								}
								if (data.status === 401) {
									swal("Password errata!", "Riprova.\n", "error").then(()=>{confermaEliminaM(matricola, codice)});
								}
								if (data.status === 403) {
									swal("Lo studente inserito non esiste","", "error");
								
								}
							}

						});}
			});

};

function confermaEliminaCorso(matricola, codice) {
	swal(
			{
				title : "Inserisci la tua password per confermare l'eliminazione del Corso",
				buttons: {
					  confirm:"Ok",
					  cancel:"Annulla"},
					  closeOnClickOutside: false,
				content: {
				    element: "input",
				    attributes: {
				      placeholder: "Password",
				      type: "password",
				    }}
			}).then((typedPassword) =>  {
				if(typedPassword!=null){
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
										"success").then(() => {
											window.location.href="page?request=corsi";
										});;
							},
							error : function(data) {
								if (data.status === 405) {
									swal("Non sei il titolare di questo corso", "Non puoi cancellare un corso del quale non sei titolare", "error");
								}
								if (data.status === 401) {
									swal("Password errata!", "Riprova.\n", "error").then(()=>{confermaEliminaCorso(matricola, codice)});
								}
							}

						});}
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

function postAdvice(){
	$('#txteditor').removeClass("hidden");
	$('#preview').removeClass("hidden");
	$('#advicebutton').addClass("hidden");
	$('#saveadvicebutton').removeClass("hidden");
	
};
function saveAdvice(){
	var text=editor2.getData();
	var id=$('#idadvice').val();
	var corso=$('#codice').val();
	var title=$('#advicetitle').val();
	console.log(title);
	$
	.ajax({
		type : "GET",
		url : "showcourse",
		datatype : 'text',
		data : {
			id:id,
			title:title,
			text : text,
			codice : corso,
			richiesta : "salvaAvviso"
		},
		success : function(data) {
			document.getElementById('idadvice').value="new";
			
			swal(
					"Avviso pubblicato",
					"L'avviso è stato pubblicato con successo.",
					"success").then(() => {
						window.location.href="page?request=corso&id="+corso;
					});
		},
		error : function(data) {
		}

	});
	
	
	
	$('#txteditor').addClass("hidden");
	$('#preview').addClass("hidden");
	$('#advicebutton').removeClass("hidden");
	$('#saveadvicebutton').addClass("hidden");
}

function delAdvice(id){
	var corso=$('#codice').val();
	$
	.ajax({
		type : "GET",
		url : "showcourse",
		datatype : 'text',
		data : {
			id:id,
			richiesta: "eliminaAvviso",
			codice:corso
		},
		success : function(data) {
			swal(
					"Avviso eliminato",
					"L'avviso è stato eliminato con successo.",
					"success").then(() => {
						window.location.href="page?request=corso&id="+corso;
					});
			
		},
		error : function(data) {
		}

	});
}

function modifyAdvice(id){
	var corso=$('#codice').val();
	$
	.ajax({
		type : "GET",
		url : "showcourse",
		datatype : 'text',
		data : {
			id:id,
			richiesta: "getAvviso",
			codice:corso
		},
		success : function(data) {
			var avviso={
					id: data.id,
					titolo: data.titolo,
					testo: data.testo,
					corso: data.corso,
					data: data.data
					
			}
			postAdvice();
			$('#txteditor').removeClass("hidden");
			document.getElementById('advicetitle').value=avviso.titolo;
			document.getElementById('idadvice').value=avviso.id;
			document.getElementById('titlecontent').innerHTML=$('#advicetitle').val();
			editor2.setData(avviso.testo);
			var elmnt = document.getElementById('advicetitle');
			elmnt.scrollIntoView();
		},
		error : function(data) {
		}

	});
}
function modificaCorso(codice){
	$
	.ajax({
		type : "GET",
		url : "page",
		datatype : 'text',
		data : {
			request: "modificaCorso",
			corso:codice
		}
	});
}

