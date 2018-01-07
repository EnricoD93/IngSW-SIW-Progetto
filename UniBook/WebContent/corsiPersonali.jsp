<%@page import="persistence.DatabaseManager"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Utente"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link href="plugins/sweetalert/sweetalert.css" rel="stylesheet">
<div class="container-fluid">
	<div class="block-header">
		<h2>
			<c:if test="${currentUser.ruolo == 0 }">
				<div class="body">
					<div class="corsiTitle" align="center">Corsi</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th width="30">Cod.</th>
								<th width="50%">Nome del Corso</th>
								<th>Docente</th>
								<th>CFU</th>
								<th>Elimina Iscrizione</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="corso" items="${corsiIscritto}">
								<tr  name="corsoSelezionato" value="${corso.codice}"
									style="cursor: pointer;"
									>
									<th scope="row">${corso.codice}</th>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});"><a id="course"> ${corso.nome} </a></td>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});">${corso.cognomeDocente}&nbsp;${corso.nomeDocente}</td>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});">${corso.cfu}</td>
									<td><c:if test="${currentUser.ruolo==0 }">
											<button type="button"
												class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
												style="margin-left: 39px;"
												onclick="javascript:confermaEliminaStudente(${currentUser.matricola},${corso.codice});">
												<i class="material-icons">remove_circle</i>

											</button>
										</c:if></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${currentUser.ruolo == 1 }">
				<div class="body">
					<div class="corsiTitle" align="center">Corsi</div>
					<br></br>
					<table class="table table-striped">
						<thead>
							<tr>
								<th width="30">Cod.</th>
								<th width="100%">Nome del Corso</th>
								<th>CFU</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="corso" items="${corsiDocente}">
								<tr id="course" name="corsoSelezionato" value="${corso.codice}"
									style="cursor: pointer;"
									onclick="javascript:getCorsoSelezionato(${corso.codice});">
									<th scope="row">${corso.codice}</th>
									<td><a> ${corso.nome} </a></td>
									<td>${corso.cfu}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<div class="bg-unibook info-box-4 hover-zoom-effect">
					<a id="createCourse" class="waves-block waves-effect">
						<div class="icon">
							<i class="material-icons">library_add</i>
						</div>
						<div class="content">
							<div class="text">Crea un nuovo Corso</div>
						</div>
					</a>
				</div>
			</c:if>
		</h2>
	</div>
</div>

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
<script src="javascript/changePage.js"></script>
<script src="plugins/sweetalert/sweetalert.min.js"></script>


</html>