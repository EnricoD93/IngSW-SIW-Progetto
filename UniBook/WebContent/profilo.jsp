<%@page import="persistence.DatabaseManager"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Utente"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<div class="container-fluid">
	<div class="block-header">
		<h2>
			<div class="body table-responsive">
				<div class="corsiTitle">Profilo:</div>
				<div class="image">
					<img src="images/user.png" width="96" height="96" alt="User">
					<a id="uploadImage" class="bg-unibook waves-effect"> <i
						class="material-icons">file_upload</i>
					</a>
				</div>
				</br>
				<table class="table table-striped">
					<tbody>

						<tr>
							<th scope="row">Nome: ${currentUser.nome}&nbsp;
								${currentUser.cognome}</th>
						</tr>
						<tr>
							<th>Matricola: ${currentUser.matricola}</th>
						</tr>
						<tr>
							<th><c:if test="${currentUser.ruolo == 0 }">
							Ruolo: Studente
							</c:if> <c:if test="${currentUser.ruolo == 1 }">
							Ruolo: Docente
							</c:if></th>
						</tr>
						<tr>
							<th>Email: ${currentUser.email}</th>
						</tr>
						<tr>
							<th>Data di Nascita: ${currentUser.dataNascita}</th>
						</tr>

					</tbody>
				</table>
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


<!-- Jquery Core Js -->
<script src="plugins/jquery/jquery.min.js"></script>


</body>


</html>
</html>