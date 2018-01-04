<%@page import="persistence.DatabaseManager"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Utente"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- Favicon-->
<link rel="icon" href="favicon.ico" type="image/x-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" type="text/css">

<!-- Bootstrap Core Css -->
<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Waves Effect Css -->
<link href="plugins/node-waves/waves.css" rel="stylesheet" />

<!-- Animation Css -->
<link href="plugins/animate-css/animate.css" rel="stylesheet" />

<!-- Custom Css -->
<link href="css/style.css" rel="stylesheet">

<!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
<link href="css/themes/all-themes.css" rel="stylesheet" />
	<div class="container-fluid" >
		<div class="block-header">
			<h2>
				<c:if test="${currentUser.ruolo == 0 }">
					<div class="body table-responsive">
						<div class="corsiTitle">Corsi</div>
						<table class="table table-striped">
							<thead>
								<tr>
									<th width="30">Cod.</th>
									<th width="50%">Nome del Corso</th>
									<th>Docente</th>
									<th>CFU</th>
								</tr>

							</thead>
							<tbody>

								<c:forEach var="corso" items="${corsiIscritto}">
									<tr>
										<th scope="row">${corso.codice}</th>
										<td><a href="index.html"> ${corso.nome} </a></td>
										<td>${corso.cognomeDocente} ${corso.nomeDocente}</td>
										<td>${corso.cfu}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${currentUser.ruolo == 1 }">
					<div class="body table-responsive">
					<button type="button" class="btn btn-lg waves-effect btncreacorso">
						<i class="material-icons">note_add</i> &nbsp; <font id="creaCorso"size="5px" ><b>Crea Corso</b></font>
					</button>
					<br></br>
					<div class="corsiTitle">Corsi</div>
					<table class="table table-striped">
							<thead>
								<tr>
									<th width="30">Cod.</th>
									<th width="50%">Nome del Corso</th>
									<th>CFU</th>
								</tr>

							</thead>
							<tbody>

								<c:forEach var="corso" items="${corsiDocente}">
									<tr>
										<th scope="row">${corso.codice}</th>
										<td><a href="index.html"> ${corso.nome} </a></td>
										<td>${corso.cfu}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
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
	


</html>