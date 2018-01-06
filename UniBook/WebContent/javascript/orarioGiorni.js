
function getOrarioLunedì() {
	var checkbox1 = document.getElementById('md_checkbox_1');
	if (checkbox1.checked == 1) {
		document.getElementById('orarioGiorniLunedì').innerHTML = " <div class=\"input-group\"> <span class=\"input-group-addon\">" +
				" Lunedì dalle </span>" +
				"<div class=\"btn-group bootstrap-select input-group-btn open\">" +
						"<button type=\"button\" class=\"btn dropdown-toggle btn-default\" data-toggle=\"dropdown\" title=\"08:00\" aria-expanded=\"true\">" +
							"<span class=\"filter-option pull-left\">08:00</span>" +
									"&nbsp;" +
								"<span class=\"bs-caret\">" +
									"<span class=\"caret\"></span>" +
								"</span>" +
						"</button>" +
					 "<div class=\"dropdown-menu open\" style=\"max-height: 205px; overflow: hidden; min-height: 0px;\">" +
					 		"<ul class=\"dropdown-menu inner\" role=\"menu\"style=\"max-height: 195px; overflow-y: auto; min-height: 0px;\">" +
					 			"<li data-original-index=\"0\" class=\"selected\">" +
					 				"<a tabindex=\"0\"class=\"\" style=\"\" data-tokens=\"null\">" +
					 					"<span class=\"text\">08:00</span>" +
					 					"<span class=\"glyphicon glyphicon-ok check-mark\"></span>" +
					 				"</a>" +
					 			"</li>" +
					 			"<li data-original-index=\"1\">" +
					 				"<a tabindex=\"0\" class=\"\" style=\"\" data-tokens=\"null\">" +
					 					"<span class=\"text\">09:00</span>" +
					 					"<span class=\"glyphicon glyphicon-ok check-mark\"></span>" +
					 				"</a>" +
					 			"</li>" +
					 			"<li data-original-index=\"2\">" +
					 				"<a tabindex=\"0\"class=\"\" style=\"\" data-tokens=\"null\">" +
					 					"<span class=\"text\">10:00</span>" +
					 					"<span class=\"glyphicon glyphicon-ok check-mark\"></span>" +
					 				"</a>" +
					 			"</li>" +
					 		"</ul>" +
					 	"</div>" +
					 	"<select class=\"selectpicker\" tabindex=\"-98\" name=\"time\">" +
					 		"<option value=\"08\">08:00</option>" +
					 		"<option value=\"08\">09:00</option>" +
					 		"<option value=\"08\">10:00</option>" +
					 	"</select>" +
					"</div>" +
				"&nbsp; " +
				
				"alle</b></span><div class=\"btn-group bootstrap-select input-group-btn open\"> <button type=\"button\"class=\"btn dropdown-toggle btn-default\" data-toggle=\"dropdown\"" +
				" title=\"09:30\" aria-expanded=\"false\"><span class=\"filter-option pull-left\">09:30</span>&nbsp;<span class=\"bs-caret\"><span class=\"caret\"></span></span>" +
				"</button> <select class=\"selectpicker\" tabindex=\"-98\" name=\"time\">" +
				"<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "+
				"<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"+
				" <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>	</select></div>	"+
				"</select> </div> <span class=\"input-group-addon\"> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <span class=\"input-group-addon\">" +
				"<input type=\"radio\" name=\"tipolezione_1\" id=\"lezione_1\" class=\"radio-col-unibook\"> <label for=\"lezione_1\">" +
				"Lezione</label> <input type=\"radio\" name=\"tipolezione_1\" id=\"esercitazione_1\" class=\"radio-col-unibook\"> " +
				"<label for=\"esercitazione_1\" class=\"m-l-20\">Esercitazione</label></span>&nbsp; </div> </div>\" " ;

	} else if (checkbox1.checked == 0) {
		document.getElementById('orarioGiorniLunedì').innerHTML = "";
	}

};

function getOrarioMartedì() {
	var checkbox2 = document.getElementById('md_checkbox_2');
	if (checkbox2.checked == 1) {
		document.getElementById('orarioGiorniMartedì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Martedì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	"
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	"
				+ " </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>	</select></div>	"
				+ "	<span> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <div class=\"btn-group bootstrap-select\"> <input type=\"radio\" name=\"tipolezione_2\" id=\"lezione_2\""
				+ "	class=\"radio-col-unibook\"> <label for=\"lezione_2\">Lezione</label> <input type=\"radio\" name=\"tipolezione_2\" id=\"esercitazione_2\" class=\"radio-col-unibook\">"
				+ " <label for=\"esercitazione_2\" class=\"m-l-20\">Esercitazione</label>	</div> </div></div>";
	} else if (checkbox2.checked == 0) {
		document.getElementById('orarioGiorniMartedì').innerHTML = "";
	}

};
function getOrarioMercoledì() {
	var checkbox3 = document.getElementById('md_checkbox_3');

	if (checkbox3.checked == 1) {
		document.getElementById('orarioGiorniMercoledì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Mercoledì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	"
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	"
				+ " </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>"
				+ "	</select></div>	"
				+ "	<span> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <div class=\"btn-group bootstrap-select\"> <input type=\"radio\" name=\"tipolezione_3\" id=\"lezione_3\""
				+ "	class=\"radio-col-unibook\"> <label for=\"lezione_3\">Lezione</label> <input type=\"radio\" name=\"tipolezione_3\" id=\"esercitazione_3\" class=\"radio-col-unibook\">"
				+ " <label for=\"esercitazione_3\" class=\"m-l-20\">Esercitazione</label>	</div> </div></div>";

	} else if (checkbox3.checked == 0) {
		document.getElementById('orarioGiorniMercoledì').innerHTML = "";
	}

};
function getOrarioGiovedì() {
	var checkbox4 = document.getElementById('md_checkbox_4');
	if (checkbox4.checked == 1) {
		document.getElementById('orarioGiorniGiovedì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Giovedì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	"
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	"
				+ " </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>"
				+ "	</select></div>"
				+ "	<span> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <div class=\"btn-group bootstrap-select\"> <input type=\"radio\" name=\"tipolezione_4\" id=\"lezione_4\""
				+ "	class=\"radio-col-unibook\"> <label for=\"lezione_4\">Lezione</label> <input type=\"radio\" name=\"tipolezione_4\" id=\"esercitazione_4\" class=\"radio-col-unibook\">"
				+ " <label for=\"esercitazione_4\" class=\"m-l-20\">Esercitazione</label>	</div> </div></div>";

	} else if (checkbox4.checked == 0) {
		document.getElementById('orarioGiorniGiovedì').innerHTML = "";
	}
};
function getOrarioVenerdì() {
	var checkbox5 = document.getElementById('md_checkbox_5');
	if (checkbox5.checked == 1) {
		document.getElementById('orarioGiorniVenerdì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Venerdì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	"
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	"
				+ " </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>"
				+ "	</select></div>"
				+ "	<span> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <div class=\"btn-group bootstrap-select\"> <input type=\"radio\" name=\"tipolezione_5\" id=\"lezione_5\""
				+ "	class=\"radio-col-unibook\"> <label for=\"lezione_5\">Lezione</label> <input type=\"radio\" name=\"tipolezione_5\" id=\"esercitazione_5\" class=\"radio-col-unibook\">"
				+ " <label for=\"esercitazione_5\" class=\"m-l-20\">Esercitazione</label>	</div> </div></div>";

	} else if (checkbox5.checked == 0) {
		document.getElementById('orarioGiorniVenerdì').innerHTML = "";
	}

};

