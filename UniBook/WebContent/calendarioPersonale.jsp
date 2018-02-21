<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html>
<html>
<head>



<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
	rel="stylesheet">
<link href="css/calendar.css" rel="stylesheet">
<script src="javascript/calendar.js"></script>
<script src="javascript/myCalendar.js"></script>

</head>
<body>
	<section id="centralSection" class="content">
		<div class="container-fluid">
			<input id="ruolo" class="hidden" value="${currentUser.ruolo}">
			<div class="corsiTitle" align="center">Calendario Personale</div>
			<div class="" id="counters">
			<c:if test="${currentUser.ruolo == 1}">
					<div class="input-group col-sm-3" style="display:inline-block;">
						<div class="switch">
							<span class="input-group-addon">Lezione </span> <label><input
								class="switch" type="checkbox" name="lezione" id="lezione"
								onclick="lessonVerify()"><span
								class="lever bg-unibook-switch"></span></label>
						</div>

						<div class="hidden" id="corsi">
							<form id="form validation">
								<span class="input-group-addon"> Corsi </span> <select
									class="selectpicker" tabindex="-98" name="corso" id="corsoid"
									required>
									<c:forEach var="corso" items="${corsi}">

										<option id="cor" value="${corso.codice}">${corso.nome}</option>

									</c:forEach>
								</select> <span class="input-group-addon"> Aula </span> <select
									class="selectpicker" tabindex="-98" name="aula" id="aulaid"
									required>
									<c:forEach var="aula" items="${aule}">

										<option id="aula" value="${aula.id}">${aula.id}</option>

									</c:forEach>
								</select>
								<div>
											<span class="input-group-addon">  Dalle </span> <select
												class="selectpicker" tabindex="-98" name="oraInizio" id="oraInizio">

												<option value="0000-00-00 08:30:00.00">08:30</option>
												<option value="0000-00-00 09:30:00.00">09:30</option>
												<option value="0000-00-00 10:30:00.00">10:30</option>
												<option value="0000-00-00 11:30:00.00">11:30</option>
												<option value="0000-00-00 12:30:00.00">12:30</option>
												<option value="0000-00-00 14:00:00.00">14:00</option>
												<option value="0000-00-00 15:00:00.00">15:00</option>
												<option value="0000-00-00 16:00:00.00">16:00</option>
												<option value="0000-00-00 17:00:00.00">17:00</option>
												<option value="0000-00-00 18:00:00.00">18:00</option>

											</select> <span class="input-group-addon"><b> &nbsp; alle </b>
											</span> <select class="selectpicker" tabindex="-98"
												name="oraFine" id="oraFine">

												<option value="0000-00-00 09:30:00.00">09:30</option>
												<option value="0000-00-00 10:30:00.00">10:30</option>
												<option value="0000-00-00 11:30:00.00">11:30</option>
												<option value="0000-00-00 12:30:00.00">12:30</option>
												<option value="0000-00-00 14:00:00.00">14:00</option>
												<option value="0000-00-00 15:00:00.00">15:00</option>
												<option value="0000-00-00 16:00:00.00">16:00</option>
												<option value="0000-00-00 17:00:00.00">17:00</option>
												<option value="0000-00-00 18:00:00.00">18:00</option>
												<option value="0000-00-00 19:00:00.00">19:00</option>


											</select> <!-- <span class="input-group-addon"> <b> &nbsp; Tipo
													lezione: &nbsp;</b>
											</span> <span class="input-group-addon"><input type="radio"
												name="tipolezione_6" id="lezione6" value="lezione"
												class="radio-col-unibook" checked> <label
												for="lezione6">Lezione</label> <input type="radio"
												name="tipolezione_6" id="esercitazione6" value="esercitazione"
												class="radio-col-unibook"> <label
												for="esercitazione6" class="m-l-20">Esercitazione</label></span>
							
							 --> 
								</div>
							</form>
						</div>

						<div class="switch">
							<span class="input-group-addon">Evento </span> <label><input
								class="switch" type="checkbox" checked="" name="evento"
								onclick="eventVerify()" id="evento"><span
								class="lever bg-unibook-switch"></span></label>
						</div>
					</div>
				</c:if>
				<div class="info-box-3 myinfobox bg-unibook hover-expand-effect"
					style="margin-right: 20px">
					<div class="icon">
						<i class="material-icons">event</i>
					</div>
					<div class="content">
						<div class="text">Eventi</div>
						<div class="number count-to" data-from="0"
							data-to="${listaSoloEventi}" data-speed="1000"
							data-fresh-interval="20">
							<script type="text/javascript">
								$('.number').countTo();
							</script>
							${listaSoloEventi}
						</div>
					</div>
				</div>
				<div class="info-box-3 myinfobox bg-unibook hover-expand-effect">
					<div class="icon">
						<i class="material-icons">school</i>
					</div>
					<div class="content">
						<div class="text">Lezioni</div>
						<div class="number count-to" data-from="0"
							data-to="${listaSoloLezioni}" data-speed="1000"
							data-fresh-interval="20">
							<script type="text/javascript">
								$('.number').countTo();
							</script>
							${listaSoloLezioni}
						</div>
					</div>

				</div>
				
			</div>

			<div id='wrap'>

				<div id='calendar'></div>
				<input type="hidden" id="currentUser"
					value="${currentUser.matricola}" />
				<div style='clear: both'></div>
			</div>




		</div>
	</section>
</body>
</html>
