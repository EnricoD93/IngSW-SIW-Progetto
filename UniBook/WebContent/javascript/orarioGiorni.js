
function getOrarioLunedì() {
	var checkbox1 = document.getElementById('md_checkbox_1');
	if (checkbox1.checked == 1) {
		var sect=$('#orarioGiorniLunedì');
		sect.html("<div class=\"input-group\"><span class=\"input-group-addon\"> Lunedì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\"  name=\"role\">"
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	"
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	"
				+ " </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\"  name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>	</select></div>	"
				+ "	<span> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <div class=\"btn-group bootstrap-select\"> <input type=\"radio\" name=\"tipolezione_1\" id=\"lezione_1\""
				+ "	class=\"radio-col-unibook\"> <label for=\"lezione_1\">Lezione</label> <input type=\"radio\" name=\"tipolezione_1\" id=\"esercitazione_1\" class=\"radio-col-unibook\">"
				+ " <label for=\"esercitazione_1\" class=\"m-l-20\">Esercitazione</label> </div></div>" +
				"<script src=\"plugins/bootstrap-select/js/bootstrap-select.js\"></script><script src=\"js/admin.js\"></script>");
	} else if (checkbox1.checked == 0) {
		document.getElementById('orarioGiorniLunedì').innerHTML = "";
	}

};

function getOrarioMartedì() {
	var checkbox2 = document.getElementById('md_checkbox_2');
	if (checkbox2.checked == 1) {
		
	var sect=$('#orarioGiorniMartedì');
	sect.html("<div class=\"input-group\"><span class=\"input-group-addon\"> Martedì dalle </span> "
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
			+ " <label for=\"esercitazione_2\" class=\"m-l-20\">Esercitazione</label> </div></div>" +
			"<script src=\"plugins/bootstrap-select/js/bootstrap-select.js\"></script><script src=\"js/admin.js\"></script>");
	
	
		
	} else if (checkbox2.checked == 0) {
		document.getElementById('orarioGiorniMartedì').innerHTML = "";
	}

};
function getOrarioMercoledì() {
	var checkbox3 = document.getElementById('md_checkbox_3');

	if (checkbox3.checked == 1) {
		var sect=$('#orarioGiorniMercoledì');
		sect.html("<div class=\"input-group\"><span class=\"input-group-addon\"> Mercoledì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	"
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	"
				+ " </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>	</select></div>	"
				+ "	<span> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <div class=\"btn-group bootstrap-select\"> <input type=\"radio\" name=\"tipolezione_3\" id=\"lezione_3\""
				+ "	class=\"radio-col-unibook\"> <label for=\"lezione_3\">Lezione</label> <input type=\"radio\" name=\"tipolezione_3\" id=\"esercitazione_3\" class=\"radio-col-unibook\">"
				+ " <label for=\"esercitazione_3\" class=\"m-l-20\">Esercitazione</label> </div></div>" +
				"<script src=\"plugins/bootstrap-select/js/bootstrap-select.js\"></script><script src=\"js/admin.js\"></script>");

	} else if (checkbox3.checked == 0) {
		document.getElementById('orarioGiorniMercoledì').innerHTML = "";
	}

};
function getOrarioGiovedì() {
	var checkbox4 = document.getElementById('md_checkbox_4');
	if (checkbox4.checked == 1) {
		var sect=$('#orarioGiorniGiovedì');
		sect.html("<div class=\"input-group\"><span class=\"input-group-addon\"> Giovedì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	"
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	"
				+ " </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>	</select></div>	"
				+ "	<span> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <div class=\"btn-group bootstrap-select\"> <input type=\"radio\" name=\"tipolezione_4\" id=\"lezione_4\""
				+ "	class=\"radio-col-unibook\"> <label for=\"lezione_4\">Lezione</label> <input type=\"radio\" name=\"tipolezione_4\" id=\"esercitazione_4\" class=\"radio-col-unibook\">"
				+ " <label for=\"esercitazione_4\" class=\"m-l-20\">Esercitazione</label> </div></div>" +
				"<script src=\"plugins/bootstrap-select/js/bootstrap-select.js\"></script><script src=\"js/admin.js\"></script>");
			

	} else if (checkbox4.checked == 0) {
		document.getElementById('orarioGiorniGiovedì').innerHTML = "";
	}
};
function getOrarioVenerdì() {
	var checkbox5 = document.getElementById('md_checkbox_5');
	if (checkbox5.checked == 1) {
		var sect=$('#orarioGiorniVenerdì');
		sect.html("<div class=\"input-group\"><span class=\"input-group-addon\"> Venerdì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	"
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	"
				+ " </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> "
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>	</select></div>	"
				+ "	<span> <b> &nbsp; Tipo lezione: &nbsp;</b> </span> <div class=\"btn-group bootstrap-select\"> <input type=\"radio\" name=\"tipolezione_5\" id=\"lezione_5\""
				+ "	class=\"radio-col-unibook\"> <label for=\"lezione_5\">Lezione</label> <input type=\"radio\" name=\"tipolezione_5\" id=\"esercitazione_5\" class=\"radio-col-unibook\">"
				+ " <label for=\"esercitazione_5\" class=\"m-l-20\">Esercitazione</label> </div></div>" +
				"<script src=\"plugins/bootstrap-select/js/bootstrap-select.js\"></script><script src=\"js/admin.js\"></script>");
			

	} else if (checkbox5.checked == 0) {
		document.getElementById('orarioGiorniVenerdì').innerHTML = "";
	}

};

