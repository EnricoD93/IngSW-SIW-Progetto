function getOrarioLunedì() {
	var checkbox1 = document.getElementById('md_checkbox_1');
	if (checkbox1.checked == 1) {
		$('#orarioGiorniLunedì').removeClass("hidden");
	}
	else if (checkbox1.checked == 0) {
		$('#orarioGiorniLunedì').addClass("hidden");
	}
};

function getOrarioMartedì() {
	var checkbox2 = document.getElementById('md_checkbox_2');
	if (checkbox2.checked == 1) {
		$('#orarioGiorniMartedì').removeClass("hidden");
	} else if (checkbox2.checked == 0) {
		$('#orarioGiorniMartedì').addClass("hidden");
	}
};
function getOrarioMercoledì() {
	var checkbox3 = document.getElementById('md_checkbox_3');

	if (checkbox3.checked == 1) {
		$('#orarioGiorniMercoledì').removeClass("hidden");
	} else if (checkbox3.checked == 0) {
		$('#orarioGiorniMercoledì').addClass("hidden");	}
};
function getOrarioGiovedì() {
	var checkbox4 = document.getElementById('md_checkbox_4');
	if (checkbox4.checked == 1) {
		$('#orarioGiorniGiovedì').removeClass("hidden");
	} else if (checkbox4.checked == 0) {
		$('#orarioGiorniGiovedì').addClass("hidden");	}
};
function getOrarioVenerdì() {
	var checkbox5 = document.getElementById('md_checkbox_5');
	if (checkbox5.checked == 1) {
		$('#orarioGiorniVenerdì').removeClass("hidden");
	} else if (checkbox5.checked == 0) {
		$('#orarioGiorniVenerdì').addClass("hidden");	}
};
function verificaGiorni() {
	var checkbox1 = document.getElementById('md_checkbox_1').checked;
	var checkbox2 = document.getElementById('md_checkbox_2').checked;
	var checkbox3 = document.getElementById('md_checkbox_3').checked;
	var checkbox4 = document.getElementById('md_checkbox_4').checked;
	var checkbox5 = document.getElementById('md_checkbox_5').checked;
	if (checkbox1 == 1 || checkbox2 == 1 || checkbox3 == 1 || checkbox4 == 1
			|| checkbox5 == 1) {
		$('#creacorso').click();
	} else {
		swal("Non hai selezionato il giorno in cui tenere lezione.",
				"Devi selezionare almeno un giorno in cui tenere la lezione",
				"error");
	}

};
function getEsami(){
	console.log("esami");

			window.location.href="page?request=listaEsami";
}

