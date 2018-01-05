
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<div class="container-fluid">
	<div class="block-header">
		<h2>
			<div class="row clearfix">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="card">
						<div class="header">
							<h2>Crea un Corso</h2>
						</div>
						<div class="body">
							<form id="form_validation" method="POST" novalidate="novalidate">
								<div class="input-group">
									<span class="input-group-addon"> Selezionare il Corso da
										creare </span>
									<div class="btn-group bootstrap-select show-tick">

										<select class="form-control show-tick" tabindex="-98"
											name="role">
											<c:forEach var="corso" items="${listaCorsi}">
												<option value="${corso.codice}">Cod.
													${corso.codice} &nbsp; - &nbsp; ${corso.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="input-group">
									<span class="input-group-addon">Selezionare i giorni in
										cui si desidera fare lezione </span> <input type="checkbox"
										id="md_checkbox_1" name="checkbox" onclick="getOrarioGiorni()">
									<label for="md_checkbox_1">Lunedì</label> &nbsp; <input
										type="checkbox" id="md_checkbox_2" name="checkbox"
										onclick="getOrarioGiorni()"> <label
										for="md_checkbox_2"> Martedì</label> &nbsp; <input
										type="checkbox" id="md_checkbox_3" name="checkbox"
										onclick="getOrarioGiorni()"> <label
										for="md_checkbox_3"> Mercoledì</label> &nbsp; <input
										type="checkbox" id="md_checkbox_4" name="checkbox"
										onclick="getOrarioGiorni()"> <label
										for="md_checkbox_4"> Giovedì</label> &nbsp; <input
										type="checkbox" id="md_checkbox_5" name="checkbox"
										onclick="getOrarioGiorni()"> <label
										for="md_checkbox_5"> Venerdì</label>
								</div>
								<div id="orarioGiorniLunedì"></div>
								<div id="orarioGiorniMartedì"></div>
								<div id="orarioGiorniMercoledì"></div>
								<div id="orarioGiorniGiovedì"></div>
								<div id="orarioGiorniVenerdì"></div>

								<div id="orarioGiorni">
									<div class="input-group">
										<span class="input-group-addon"> Lunedì dalle </span>
										<div class="btn-group bootstrap-select show-tick">

											<select class="form-control show-tick" tabindex="-98"
												name="role">

												<option value="08">08:00</option>
												<option value="08">09:00</option>
												<option value="08">10:00</option>

											</select>
										</div>
										<span> <b> &nbsp; alle &nbsp;</b>
										</span>
										<div class="btn-group bootstrap-select show-tick">

											<select class="form-control show-tick" tabindex="-98"
												name="role">

												<option value="08">08:30</option>

											</select>
										</div>
										<span> <b> &nbsp; Tipo lezione: &nbsp;</b>
										</span>
										<div class="btn-group bootstrap-select">
											<input type="radio" name="tipolezione" id="lezione"
												class="radio-col-unibook"> <label for="lezione">Lezione</label>
											<input type="radio" name="tipolezione" id="esercitazione"
												class="radio-col-unibook"> <label for="esercitazione"
												class="m-l-20">Esercitazione</label>
										</div>



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
										<input type="text" class="form-control" name="surname"
											required="" aria-required="true"> <label
											class="form-label">Surname</label>
									</div>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<input type="email" class="form-control" name="email"
											required="" aria-required="true"> <label
											class="form-label">Email</label>
									</div>
								</div>
								<div class="form-group">
									<input type="radio" name="gender" id="male" class="with-gap">
									<label for="male">Male</label> <input type="radio"
										name="gender" id="female" class="with-gap"> <label
										for="female" class="m-l-20">Female</label>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<textarea name="description" cols="30" rows="5"
											class="form-control no-resize" required=""
											aria-required="true"></textarea>
										<label class="form-label">Description</label>
									</div>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<input type="password" class="form-control" name="password"
											required="" aria-required="true"> <label
											class="form-label">Password</label>
									</div>
								</div>
								<div class="form-group">
									<input type="checkbox" id="checkbox" name="checkbox"> <label
										for="checkbox">I have read and accept the terms</label>
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

</html>