$(document)
		.ready(
				function() {

					var list;
					var list2;
						list = document.getElementsByName('votoi');
					
						list2 = document.getElementsByName('votom');
				
					for (var i = 0; i < list.length; i++) {
						list[i]
								.addEventListener(
										"change",
										function changeMedia() {
											var list;
											var lode = 0;
											var cfu;
											var totalevoti = 0;
											var totalevotip = 0;
											var num = 0;
											var totcfu = 0;
												cfu = document
														.getElementsByName('cfui')
												list = document
														.getElementsByName('votoi');
											for (var i = 0; i < list.length; i++) {
												if (list[i].value != "none") {
													if (list[i].value == "30L") {
														lode++;
													}
													num++;
													console
															.log("totale esami: "
																	+ num);
													totcfu += parseInt(cfu[i].innerText);
													console
															.log("totale crediti: "
																	+ totcfu);
													totalevoti += parseInt(list[i].value);
													console.log("totale voti: "
															+ totalevoti);
													totalevotip += (parseInt(list[i].value) * parseInt(cfu[i].innerText));
												}
											}
											document.getElementById('arit').innerHTML = totalevoti
													/ num;
											document.getElementById('pond').innerHTML = totalevotip
													/ totcfu;

											document.getElementById('laurea').innerHTML = Math.round(Math.round((((totalevotip/totcfu) * 11) / 3)
													)+ (lode * 0.33));

										});
					}
					for (var i = 0; i < list2.length; i++) {
						list2[i]
								.addEventListener(
										"change",
										function changeMedia() {
											var list;
											var cdl = $('#corso').val();
											var lode = 0;
											var cfu;
											var totalevoti = 0;
											var totalevotip = 0;
											var num = 0;
											var totcfu = 0;
												cfu = document
														.getElementsByName('cfum')
												list = document
														.getElementsByName('votom');
											for (var i = 0; i < list.length; i++) {
												if (list[i].value != "none") {
													if (list[i].value == "30L") {
														lode++;
													}
													num++;
													console
															.log("totale esami: "
																	+ num);
													totcfu += parseInt(cfu[i].innerText);
													console
															.log("totale crediti: "
																	+ totcfu);
													totalevoti += parseInt(list[i].value);
													console.log("totale voti: "
															+ totalevoti);
													totalevotip += (parseInt(list[i].value) * parseInt(cfu[i].innerText));
												}
											}
											document.getElementById('arit').innerHTML = totalevoti
													/ num;
											document.getElementById('pond').innerHTML = totalevotip
													/ totcfu;

											document.getElementById('laurea').innerHTML = Math.round(Math.round((((totalevotip/totcfu) * 11) / 3)
													)+ (lode * 0.33));

										});
					}
				});

function calcolaMedia() {
	var cdl = $('#corso').val();
	var button = $('#calcola');
	button.attr("href", "javascript:chiudiMedia()");
	button.text("Chiudi");
	$('#calcola').attr("hidden", true);
	$('#chiudi').attr("hidden", false);
	console.log(cdl);
	$('#listaesami').attr("hidden", false);
	if (cdl == "0733") {
		$('#matematica').attr("hidden", true);
		$('#informatica').attr("hidden", false);
	}
	if (cdl == "0726") {
		$('#informatica').attr("hidden", true);
		$('#matematica').attr("hidden", false);
	}
	$('#exam').click();
}
function chiudiMedia() {
	var button = $('#calcola');
	button.attr("href", "javascript:calcolaMedia()");
	button.text("Calcola media");
	document.getElementById('arit').innerHTML = 0;
document.getElementById('pond').innerHTML = 0;

document.getElementById('laurea').innerHTML = 0;
	$('#chiudi').attr("hidden", true);
	$('#calcola').attr("hidden", false);
	$('#listaesami').attr("hidden", true);
}