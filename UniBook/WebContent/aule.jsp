<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<body  class="theme-red">
<section id="centralSection">
<div class="container-fluid">
	<div class="block-header">
		<h2>
			<div class="body table-responsive">
				<div class="corsiTitle">Aule</div>
				<img alt="mappa" src="images/aule.gif">
				<table class="table table-striped">
					<tbody>

						<c:forEach var="aula" items="${aule}">
							<tr>
								<th>
								<td>L'aula <b>${aula.id}</b> appartiene al Corso di Laurea
									<c:if test="${aula.corsoDiLaurea == 733 }">
									di <b>Informatica</b>
									</c:if> <c:if test="${aula.corsoDiLaurea == 726 }">
									di <b>Matematica</b>
									</c:if> ha una capienza di <b>${aula.posti}</b> posti
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<br></br> <b>*Tutte le aule sono dotate di accesso alla rete
				Wi-fi tramite apposite credenziali</b>
		</h2>
	</div>
</div>
<c:if test="${currentUser==null}">
	<%
		response.sendRedirect("index.html");
	%>
</c:if> <%
 	response.setHeader("Cache-Control", "no-cache");
 	response.setHeader("Cache-Control", "no-store");
 	response.setHeader("Pragma", "no-cache");
 	response.setDateHeader("Expires", 0);
 %> </section>



</html>