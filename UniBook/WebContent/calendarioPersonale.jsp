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
			<div class="corsiTitle" align="center">Calendario Personale</div>
			<c:if test="${currentUser.ruolo == 1}">
			<div class="input-group col-sm-3">
					<div class="switch">
						<span class="input-group-addon">Lezione </span> <label><input
							class="switch" type="checkbox" name="lezione" id="lezione" onclick="lessonVerify()"
							 ><span class="lever bg-unibook-switch"></span></label>
					</div>

					<div class="hidden" id="corsi">
<form id="form validation">
						<span class="input-group-addon"> Corsi </span> <select
							class="selectpicker" tabindex="-98" name="corso" id="corsoid" 
							required>
							<c:forEach var="corso" items="${corsi}">

								<option id="cor" value="${corso.codice}" >${corso.nome}</option>

							</c:forEach>
						</select>

						<span class="input-group-addon"> Aula </span> <select
							class="selectpicker" tabindex="-98" name="aula" id="aulaid"
							required>
							<c:forEach var="aula" items="${aule}">

								<option id="aula" value="${aula.id}" >${aula.id}</option>

							</c:forEach>
						</select>
</form>
					</div>

					<div class="switch">
						<span class="input-group-addon">Evento </span> <label><input
							class="switch" type="checkbox" checked="" name="evento"onclick="eventVerify()"
							id="evento"><span class="lever bg-unibook-switch"></span></label>
					</div>
				</div>
			</c:if>
			<div id='wrap'>

				<div id='calendar' ></div>
				<input type="hidden" id="currentUser"
					value="${currentUser.matricola}" />
				<div style='clear: both'></div>
			</div>




		</div>
	</section>
</body>
</html>
