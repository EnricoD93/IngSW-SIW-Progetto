<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="javascript/esami.js" type="text/javascript"></script>
</head>
<section id="centralSection" class="content">
	<body>


		<div align="left" class="corsiTitle" id="med">
			<font color="#C4161C"><img alt="calcolatrice"
				src="images/calculator.png" width="40px">&nbsp;Media:</font>
		<div  class="corsiTitle"id="media">	</div>
		</div>
		<h4 >
			<span style="margin-top:50px;">Clicca per aggiungere una riga</span>
			<button
				class="btn bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
				title="Studenti iscritti" onclick="addExam();">
				<i class="material-icons">add</i>
			</button>
		</h4>

		<table class="table table-striped" id="table">
			<thead>
				<tr>
					<th>Nome del Corso</th>
					<th>Crediti</th>
					<th>Voto</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input class="form-control" id="value1" type="number"></td>
					<td><input class="form-control" id="crediti1" type="number"></td>
					<td><input class="form-control" id="voto1" type="number"></td>
				</tr>
			</tbody>
		</table>
		<div align="right">
			<button id="calcolaMedia" type="button"
				class="btn bg-unibook waves-effect"
				onclick="javascript:calcolaMedia();">Calcola</button>
		</div>
	</body>
</section>

</html>