<%@page import="persistence.DatabaseManager"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Utente"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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
									di <b>Informatica</b> </c:if>
								
									<c:if test="${aula.corsoDiLaurea == 726 }">
									di <b>Matematica</b> </c:if>
								
									ha una capienza di <b>${aula.posti}</b> posti</td>
									</th>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<br></br>
<b>*Tutte le aule sono dotate di accesso alla rete Wi-fi tramite apposite credenziali</b>
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
	
	</section>
	
	<!-- Jquery Core Js -->
	<script src="plugins/jquery/jquery.min.js"></script>

	
</body>


</html>