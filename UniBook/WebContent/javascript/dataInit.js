$(document).ready(
		function() {

			var valore = $('#inizio').val();
			var valore2 = $('#fine').val();

			var arrayI = valore.split(",");
			var arrayF = valore2.split(",");

			$('#dataInizio').bootstrapMaterialDatePicker('setDate',
					new Date(arrayI[0], arrayI[1] - 1, arrayI[2]));
			$('#dataFine').bootstrapMaterialDatePicker('setDate',
					new Date(arrayF[0], arrayF[1] - 1, arrayF[2]));

			$.ajax({
				type : "GET",
				url : "page",
				datatype : 'text',
				data : {
					request : "modificaC",
					corso : $('#codice').val()
				},
				success : function(data) {

					var lista = data['lista'];
					for (var i = 0; i < Object.keys(lista).length; i++) {
						var giorno=document.getElementsByName(lista[i]["giorno"]);
						giorno[0].click();
						
						var dalle= document.getElementById(lista[i]["giorno"]+"Inizio");
						dalle.value=lista[i]["dalle"];
						var alle= document.getElementById(lista[i]["giorno"]+"Fine");
						alle.value=lista[i]["alle"];
						var aula= document.getElementById(lista[i]["giorno"]+"Aula");
						console.log(aula);
						aula.value=lista[i]["aula"];
						console.log(lista[i]["aula"])
					}
$('.selectpicker').selectpicker('refresh');
				}
			});

		});
