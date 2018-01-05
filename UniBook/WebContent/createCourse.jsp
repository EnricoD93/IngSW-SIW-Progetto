
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="plugins/bootstrap-select/css/bootstrap-select.css"
	rel="stylesheet">
<div class="container-fluid">
	<div class="block-header">
		<h2>
			<div class="row clearfix">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="card">
						<div class="header" style="background-color: #C4161C;">
							<h2 style="color: white;">Crea un Corso</h2>
						</div>
						<div class="body">
							<form id="form_validation" method="POST" novalidate="novalidate">
								<div class="input-group">
									<span class="input-group-addon"> Selezionare il Corso da
										creare </span> <select class="selectpicker" data-live-search="true"
										tabindex="-98">
										<c:forEach var="corso" items="${listaCorsi}">
											<option value="${corso.codice}">Cod. ${corso.codice}
												&nbsp; - &nbsp; ${corso.nome}</option>
										</c:forEach>
									</select> &nbsp;



								</div>

								<div class="input-group">
									<span class="input-group-addon">Selezionare i giorni in
										cui si desidera fare lezione </span> <input type="checkbox"
										id="md_checkbox_1" name="checkbox" onclick="getOrarioLunedì()">
									<label for="md_checkbox_1">Lunedì</label> &nbsp; <input
										type="checkbox" id="md_checkbox_2" name="checkbox"
										onclick="getOrarioMartedì()"> <label
										for="md_checkbox_2"> Martedì</label> &nbsp; <input
										type="checkbox" id="md_checkbox_3" name="checkbox"
										onclick="getOrarioMercoledì()"> <label
										for="md_checkbox_3"> Mercoledì</label> &nbsp; <input
										type="checkbox" id="md_checkbox_4" name="checkbox"
										onclick="getOrarioGiovedì()"> <label
										for="md_checkbox_4"> Giovedì</label> &nbsp; <input
										type="checkbox" id="md_checkbox_5" name="checkbox"
										onclick="getOrarioVenerdì()"> <label
										for="md_checkbox_5"> Venerdì</label>
								</div>
								<div id="orarioGiorniLunedì"></div>
								<div id="orarioGiorniMartedì"></div>
								<div id="orarioGiorniMercoledì"></div>
								<div id="orarioGiorniGiovedì"></div>
								<div id="orarioGiorniVenerdì"></div>


								<div id="orarioGiorni">
									<div class="input-group">
										<span class="input-group-addon"> Lunedì dalle </span> <select
											class="selectpicker" tabindex="-98" name="time">

											<option value="08">08:00</option>
											<option value="08">09:00</option>
											<option value="08">10:00</option>

										</select> <span class="input-group-addon"><b> &nbsp; alle </b> </span>
										<select class="selectpicker" tabindex="-98" name="time">

											<option value="08">08:00</option>
											<option value="08">09:00</option>
											<option value="08">10:00</option>

										</select> <span class="input-group-addon"> <b> &nbsp; Tipo
												lezione: &nbsp;</b>
										</span> <span class="input-group-addon"><input type="radio"
											name="tipolezione" id="lezione_1" class="radio-col-unibook">
											<label for="lezione_1">Lezione</label> <input type="radio"
											name="tipolezione" id="esercitazione"
											class="radio-col-unibook"> <label for="esercitazione"
											class="m-l-20">Esercitazione</label></span>&nbsp;
									</div>


								</div>
								<div class="input-group">
									<span class="input-group-addon"> Selezionare l'aula in
										cui si desidera fare lezione </span>
									<div class="btn-group bootstrap-select show-tick">

										<select class="form-control show-tick" tabindex="-98"
											name="role">
											<c:forEach var="aula" items="${aule}">
												<option value="${aula.id}">Aula ${aula.id}</option>
											</c:forEach>
										</select>
									</div>

								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<textarea name="description" cols="30" rows="5"
											class="form-control no-resize" required=""
											aria-required="true"></textarea>
										<label class="form-label">Descrizione</label>
									</div>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<textarea name="description" cols="30" rows="5"
											class="form-control no-resize" required=""
											aria-required="true"></textarea>
										<label class="form-label">Requisiti</label>
									</div>
								</div>

								<div class="input-group">
									<span class="input-group-addon">Propedeuticità</span> <select
										class="selectpicker" multiple="" tabindex="-98">
										<option>Mustard</option>
										<option>Ketchup</option>
										<option>Relish</option>
									</select>&nbsp;
								</div>

								<button class="btn btn-primary waves-effect" type="submit">SUBMIT</button>
							</form>
						</div>

					</div>
				</div>
			</div>
		</h2>
	</div>
</div>
<script src="javascript/orarioGiorni.js"></script>
<script src="plugins/jquery/jquery.js"></script>
<script src="plugins/bootstrap-select/js/bootstrap-select.js"></script>
<!-- da non cancellare per la form -->
<script src="js/admin.js"></script>
</html>