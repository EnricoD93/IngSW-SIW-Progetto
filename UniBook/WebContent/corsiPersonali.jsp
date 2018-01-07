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
			<div class="body">
				<div class="corsiTitle" align="center">I miei Corsi</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >Cod.</th>
							<th >Nome del Corso</th>
							<c:if test="${currentUser.ruolo == 0 }">
								<th>Docente</th>
							</c:if>
							<c:if test="${currentUser.ruolo == 1 }">
								<th>CorsoDiLaurea</th>
							</c:if>
							<th>CFU</th>
							<c:if test="${currentUser.ruolo == 0 }">
								<th></th>
							</c:if>
							
						</tr>

					</thead>
					<tbody>
						<c:if test="${currentUser.ruolo == 0 }">
							<c:forEach var="corso" items="${corsiIscritto}">
								<tr name="corsoSelezionato" value="${corso.codice}"
									style="cursor: pointer;">
									<th scope="row">${corso.codice}</th>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});"><a
										id="course"> ${corso.nome} </a></td>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});">${corso.cognomeDocente}&nbsp;${corso.nomeDocente}</td>
									<td onclick="javascript:getCorsoSelezionato(${corso.codice});">${corso.cfu}</td>
									<td><c:if test="${currentUser.ruolo==0 }">
											<button type="button"
												class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
												style="margin-left: 39px;"
												onclick="javascript:confermaEliminaStudente(${currentUser.matricola},${corso.codice});">
												<i class="material-icons">clear</i>

											</button>
										</c:if></td>
								</tr>
							</c:forEach>
						</c:if>


						<c:if test="${currentUser.ruolo == 1 }">

							<c:forEach var="corso" items="${corsiDocente}">
								<tr id="course" name="corsoSelezionato" value="${corso.codice}"
									style="cursor: pointer;"
									onclick="javascript:getCorsoSelezionato(${corso.codice});">
									<th scope="row">${corso.codice}</th>
									<td><a> ${corso.nome} </a></td>
									<td align="center">${corso.corsoDiLaurea}</td>
									<td>${corso.cfu}</td>
								</tr>
							</c:forEach>
						</c:if>
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