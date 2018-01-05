$(document).ready(getOrarioGiorni);

function getOrarioGiorni() {

	var checkbox1 = document.getElementById('md_checkbox_1');
	var checkbox2 = document.getElementById('md_checkbox_2');
	var checkbox3 = document.getElementById('md_checkbox_3');
	var checkbox4 = document.getElementById('md_checkbox_4');
	var checkbox5 = document.getElementById('md_checkbox_5');
	if (checkbox1.checked == 1) {
		document.getElementById('orarioGiorniLunedì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Lunedì dalle </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">" 
				+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
				+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	" 
				+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	" 
				+" </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
				+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
				+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> " 
				+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
				+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>"
				+ "	</select></div>	</div></div>";

	} else if (checkbox1.checked == 0) {
		document.getElementById('orarioGiorniLunedì').innerHTML = "";
	}

	if (checkbox2.checked == 1) {
		document.getElementById('orarioGiorniMartedì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Martedì dalle </span> "
			+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">" 
			+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
			+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	" 
			+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	" 
			+" </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
			+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
			+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> " 
			+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
			+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>"
			+ "	</select></div>	</div></div>";

	} else if (checkbox2.checked == 0) {
		document.getElementById('orarioGiorniMartedì').innerHTML = "";
	}

	if (checkbox3.checked == 1) {
		document.getElementById('orarioGiorniMercoledì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Mercoledì dalle </span> "
			+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">" 
			+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
			+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	" 
			+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	" 
			+" </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
			+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
			+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> " 
			+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
			+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>"
			+ "	</select></div>	</div></div>";

	} else if (checkbox3.checked == 0) {
		document.getElementById('orarioGiorniMercoledì').innerHTML = "";
	}
	
	if (checkbox4.checked == 1) {
		document.getElementById('orarioGiorniGiovedì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Giovedì dalle </span> "
			+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">" 
			+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
			+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	" 
			+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	" 
			+" </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
			+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
			+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> " 
			+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
			+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>"
			+ "	</select></div>	</div></div>";

	} else if (checkbox4.checked == 0) {
		document.getElementById('orarioGiorniGiovedì').innerHTML = "";
	}
	
	if (checkbox5.checked == 1) {
		document.getElementById('orarioGiorniVenerdì').innerHTML = "<div id=\"orarioGiorni\"> <div class=\"input-group\"><span class=\"input-group-addon\"> Venerdì dalle </span> "
			+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">" 
			+ "<option value=\"08\">08:30</option> <option value=\"08\">09:30</option>	<option value=\"08\">10:30</option>	"
			+ "<option value=\"08\">11:30</option> <option value=\"08\">12:30</option>	<option value=\"08\">14:00</option>	" 
			+ "<option value=\"08\">15:00</option> <option value=\"08\">16:00</option>	<option value=\"08\">17:00</option>	<option value=\"08\">18:00</option>	" 
			+" </select> </div> <span> <b> &nbsp; alle &nbsp;</b> </span> "
			+ "<div class=\"btn-group bootstrap-select show-tick\"> <select class=\"form-control show-tick\" tabindex=\"-98\" name=\"role\">"
			+ "	<option value=\"08\">09:30</option> <option value=\"08\">10:30</option> <option value=\"08\">11:30</option> " 
			+ "<option value=\"08\">12:30</option> <option value=\"08\">13:30</option> <option value=\"08\">15:00</option>"
			+ " <option value=\"08\">16:00</option> <option value=\"08\">17:00</option> <option value=\"08\">18:00</option> <option value=\"08\">19:00</option>"
			+ "	</select></div>	</div></div>";

	} else if (checkbox5.checked == 0) {
		document.getElementById('orarioGiorniVenerdì').innerHTML = "";
	}

};
