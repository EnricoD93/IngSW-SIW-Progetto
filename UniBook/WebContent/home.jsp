<%@page import="persistence.DatabaseManager"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.Utente"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<title>Home | UniBook</title>
</head>
<body>
	<section id="centralSection" class="content">
	<div class="container-fluid">
		<div class="block-header">
			<h2>

				<div class="body table-responsive">
					<div class="corsiTitle">Corsi disponibili</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th width="30">Cod.</th>
								<th width="50%">Nome del Corso</th>
								<th>Docente</th>
								<th>Corso Di Laurea</th>
								<th>CFU</th>
								<th></th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="corso" items="${corsi}">
								<tr name="corsoSelezionato" value="${corso.codice}"
									style="cursor: pointer;">
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});"
										scope="row">${corso.codice}</td>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});">
										${corso.nome}</td>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});">${corso.cognomeDocente}&nbsp;${corso.nomeDocente}</td>

									<td onclick="javascript:getCorsoSelezionato(${corso.codice});">${corso.corsoDiLaurea}</td>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});">${corso.cfu}</td>
									<td><c:if test="${currentUser.ruolo==0 }">
											<button type="button"
												class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
												style="margin-left: 10px;"
												onclick="javascript:iscriviStudente(${corso.codice},${currentUser.matricola});">
												<i class="material-icons">add</i>

											</button>
										</c:if></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>


				<!-- <button id="button">Click me</button> -->
				<c:if test="${currentUser==null}">
					<%
						response.sendRedirect("index.html");
					%>
				</c:if>

				<%
					response.setHeader("Cache-Control", "no-cache");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Pragma", "no-cache");
					response.setDateHeader("Expires", 0);
				%>

			</h2>
		</div>
	</div>
	</section>

</body>

</html>