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

			<div id='wrap'>

				<div id='calendar'></div>
<input type="hidden" id="currentUser" value="${currentUser.matricola}"/>
				<div style='clear: both'></div>
			</div>
		</div>
	</section>
</body>
</html>
