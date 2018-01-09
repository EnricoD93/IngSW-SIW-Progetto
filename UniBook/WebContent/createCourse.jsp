<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>

<link
	href="plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet">
<script src="javascript/orarioGiorni.js"></script>
<script src="plugins/momentjs/moment.js"></script>
<script src="plugins/momentjs/moment.min.js"></script>
<script src="javascript/data.js"></script>
<script
	src="plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>
<script src="js/pages/forms/basic-form-elements.js"></script>
<script src="plugins/jquery-validation/jquery.validate.js"></script>

<section id="centralSection" class="content">
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
								<form id="form_validation" method="POST" action="createCourse">
									<div class="input-group">
										<span class="input-group-addon"> Selezionare il Corso
											da creare </span> <select class="selectpicker" name="codice"
											data-live-search="true" tabindex="-98" required>
											<c:forEach var="corso" items="${listaCorsi}">
												<option value="${corso.codice}">Cod.
													${corso.codice} &nbsp; - &nbsp; ${corso.nome}</option>
											</c:forEach>
										</select> &nbsp;



									</div>

									<div class="form-group">
										<div class="form-line">
											<label for="dataInizio">Selezionare la data di inizio
												corso</label> &nbsp; <input id="dataInizio" type="text"
												class="datepicker form-control"
												placeholder="Seleziona una data" data-dtp="dtp_b4zAz"
												required>
										</div>
									</div>

									<div class="form-group">
										<div class="form-line">
											<label for="dataFine">Selezionare la data di fine
												corso</label> &nbsp; <input id="dataFine" type="text"
												class="datepicker form-control"
												placeholder="Seleziona una data" data-dtp="dtp_b4zAz"
												required>
										</div>
									</div>



									<div class="input-group">
										<span class="input-group-addon" required>Selezionare i
											giorni in cui si desidera fare lezione </span> <input
											type="checkbox" id="md_checkbox_1" name="lunedì"
											onclick="getOrarioLunedì()"> <label
											for="md_checkbox_1">Lunedì</label> &nbsp; <input
											type="checkbox" id="md_checkbox_2" name="martedì"
											onclick="getOrarioMartedì()"> <label
											for="md_checkbox_2"> Martedì</label> &nbsp; <input
											type="checkbox" id="md_checkbox_3" name="mercoledì"
											onclick="getOrarioMercoledì()"> <label
											for="md_checkbox_3"> Mercoledì</label> &nbsp; <input
											type="checkbox" id="md_checkbox_4" name="giovedì"
											onclick="getOrarioGiovedì()"> <label
											for="md_checkbox_4"> Giovedì</label> &nbsp; <input
											type="checkbox" id="md_checkbox_5" name="venerdì"
											onclick="getOrarioVenerdì()"> <label
											for="md_checkbox_5"> Venerdì</label>
									</div>
									<!-- Orario Lunedi -->
									<div class="hidden" id="orarioGiorniLunedì">
										<div class="input-group">
											<span class="input-group-addon"> Lunedì dalle </span> <select
												class="selectpicker" tabindex="-98" name="time">

												<option value="08:30">08:30</option>
												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="14:00">14:00</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>

											</select> <span class="input-group-addon"><b> &nbsp; alle </b>
											</span> <select class="selectpicker" tabindex="-98" name="time">

												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="13:30">13:30</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>
												<option value="19:00">19:00</option>

											</select> <span class="input-group-addon"> <b> &nbsp; Tipo
													lezione: &nbsp;</b>
											</span> <span class="input-group-addon"><input type="radio"
												name="tipolezione_1" id="lezione_1"
												class="radio-col-unibook" checked> <label
												for="lezione_1">Lezione</label> <input type="radio"
												name="tipolezione_1" id="esercitazione_1"
												class="radio-col-unibook"> <label
												for="esercitazione_1" class="m-l-20">Esercitazione</label></span>&nbsp;
										</div>
									</div>
									<!-- Orario Martedì -->
									<div class="hidden" id="orarioGiorniMartedì">
										<div class="input-group">
											<span class="input-group-addon"> Martedì dalle </span> <select
												class="selectpicker" tabindex="-98" name="time">

												<option value="08:30">08:30</option>
												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="14:00">14:00</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>

											</select> <span class="input-group-addon"><b> &nbsp; alle </b>
											</span> <select class="selectpicker" tabindex="-98" name="time">

												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="13:30">13:30</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>
												<option value="19:00">19:00</option>

											</select> <span class="input-group-addon"> <b> &nbsp; Tipo
													lezione: &nbsp;</b>
											</span> <span class="input-group-addon"><input type="radio"
												name="tipolezione_2" id="lezione_2"
												class="radio-col-unibook" checked> <label
												for="lezione_2">Lezione</label> <input type="radio"
												name="tipolezione_2" id="esercitazione_2"
												class="radio-col-unibook"> <label
												for="esercitazione_2" class="m-l-20">Esercitazione</label></span>&nbsp;
										</div>
									</div>
									<!-- Orario Mercoledì -->
									<div class="hidden" id="orarioGiorniMercoledì">
										<div class="input-group">
											<span class="input-group-addon"> Mercoledì dalle </span> <select
												class="selectpicker" tabindex="-98" name="time">

												<option value="08:30">08:30</option>
												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="14:00">14:00</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>

											</select> <span class="input-group-addon"><b> &nbsp; alle </b>
											</span> <select class="selectpicker" tabindex="-98" name="time">

												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="13:30">13:30</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>
												<option value="19:00">19:00</option>

											</select> <span class="input-group-addon"> <b> &nbsp; Tipo
													lezione: &nbsp;</b>
											</span> <span class="input-group-addon"><input type="radio"
												name="tipolezione_3" id="lezione_3"
												class="radio-col-unibook" checked> <label
												for="lezione_3">Lezione</label> <input type="radio"
												name="tipolezione_3" id="esercitazione_3"
												class="radio-col-unibook"> <label
												for="esercitazione_3" class="m-l-20">Esercitazione</label></span>&nbsp;
										</div>
									</div>
									<!-- Orario Giovedì -->
									<div class="hidden" id="orarioGiorniGiovedì">
										<div class="input-group">
											<span class="input-group-addon"> Giovedì dalle </span> <select
												class="selectpicker" tabindex="-98" name="time">

												<option value="08:30">08:30</option>
												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="14:00">14:00</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>

											</select> <span class="input-group-addon"><b> &nbsp; alle </b>
											</span> <select class="selectpicker" tabindex="-98" name="time">

												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="13:30">13:30</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>
												<option value="19:00">19:00</option>

											</select> <span class="input-group-addon"> <b> &nbsp; Tipo
													lezione: &nbsp;</b>
											</span> <span class="input-group-addon"><input type="radio"
												name="tipolezione_4" id="lezione_4"
												class="radio-col-unibook" checked> <label
												for="lezione_4">Lezione</label> <input type="radio"
												name="tipolezione_4" id="esercitazione_4"
												class="radio-col-unibook"> <label
												for="esercitazione_4" class="m-l-20">Esercitazione</label></span>&nbsp;
										</div>
									</div>
									<!-- Orario Venerdì -->
									<div class="hidden" id="orarioGiorniVenerdì">
										<div class="input-group">
											<span class="input-group-addon"> Venerdì dalle </span> <select
												class="selectpicker" tabindex="-98" name="time">

												<option value="08:30">08:30</option>
												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="14:00">14:00</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>

											</select> <span class="input-group-addon"><b> &nbsp; alle </b>
											</span> <select class="selectpicker" tabindex="-98" name="time">

												<option value="09:30">09:30</option>
												<option value="10:30">10:30</option>
												<option value="11:30">11:30</option>
												<option value="12:30">12:30</option>
												<option value="13:30">13:30</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>
												<option value="19:00">19:00</option>

											</select> <span class="input-group-addon"> <b> &nbsp; Tipo
													lezione: &nbsp;</b>
											</span> <span class="input-group-addon"><input type="radio"
												name="tipolezione_5" id="lezione_5"
												class="radio-col-unibook" checked> <label
												for="lezione_5">Lezione</label> <input type="radio"
												name="tipolezione_5" id="esercitazione_5"
												class="radio-col-unibook"> <label
												for="esercitazione_5" class="m-l-20">Esercitazione</label></span>&nbsp;
										</div>
									</div>



									<div class="input-group">
										<span class="input-group-addon"> Selezionare l'aula in
											cui si desidera fare lezione </span>
										<div class="btn-group bootstrap-select show-tick">

											<select class="form-control show-tick" tabindex="-98"
												name="idAula" required>
												<c:forEach var="aula" items="${listaAule}">
													<option value="${aula.id}">Aula ${aula.id}</option>
												</c:forEach>
											</select>
										</div>

									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<textarea name="descrizione" cols="30" rows="5"
												class="form-control no-resize" required></textarea>
											<label class="form-label">Descrizione</label>
										</div>
									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<textarea name="requisiti" cols="30" rows="5"
												class="form-control no-resize"></textarea>
											<label class="form-label">Requisiti</label>
										</div>
									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<textarea name="materiale" cols="30" rows="5"
												class="form-control no-resize"></textarea>
											<label class="form-label">Materiale</label>
										</div>
									</div>

									<div class="input-group">
										<span class="input-group-addon">Propedeuticità</span> <select
											class="selectpicker" data-live-search="true" multiple=""
											tabindex="-98">
											<c:forEach var="corso" items="${listaCorsi}">
												<option value="${corso.codice}">${corso.nome}</option>
											</c:forEach>
										</select>&nbsp;
									</div>
									<div class="input-group col-sm-3">
										<div class="switch">
											<span class="input-group-addon">Abilita iscrizioni </span> <label><input
												class="switch" type="checkbox" checked=""><span
												class="lever bg-unibook-switch"></span></label>
										</div>
									</div>
									<button class="btn bg-unibook waves-effect" type="button"
										onclick="javascript:verificaGiorni();">Crea corso</button>
									<button id="creacorso"
										class="btn btn-primary waves-effect hidden" type="submit"></button>
								</form>
							</div>

						</div>
					</div>
				</div>
			</h2>
		</div>
	</div>
</section>



</html>