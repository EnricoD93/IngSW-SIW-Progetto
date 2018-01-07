<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<body>
	<div class="container-fluid">
		<div class="block-header">
			<h2>
				<div class="body table-responsive">
				<div align="center">
					<div class="profile-pic-my"
						style="background-image: url('${courseDocente.profileImagePath}')">
					</div>
					<div align="center" class="corsiTitle">${currentCourse.nome}</div>
				</div>
				<div align="right" class="listaStudenti">
					<button type="button" id="listaStudenti"
						class="btn bg-unibook btn-circle-lg waves-effect waves-circle waves-float"
						title="Studenti iscritti">
						<i class="material-icons">people</i>
					</button>
				</div>


				<table class="table table-striped">
					<tbody>

						<tr>
							<th scope="row">Docente:
								${currentCourse.cognomeDocente}&nbsp;
								${currentCourse.nomeDocente}</th>
						</tr>
						<tr>
							<th scope="row">Codice del Corso: ${currentCourse.codice}</th>
						</tr>
						<tr>
						<c:if test="${currentCourse.corsoDiLaurea==733}">
							<th>Codice del Corso: ${currentCourse.corsoDiLaurea} Corso di Laurea Informatica</th>
						</c:if>
						<c:if test="${currentCourse.corsoDiLaurea==726}">
							<th scope="row">Codice del Corso: ${currentCourse.corsoDiLaurea} - Corso di Laurea Matematica</th>
						</c:if>
						</tr>
						<tr>
							<th scope="row">Anno del corso: ${currentCourse.anno}</th>
						</tr>
						<tr>
							<th scope="row">Cfu: ${currentCourse.cfu}</th>
						</tr>
					
						<tr>
							<th scope="row">Giorni di Lezione: ${currentCourse.giorno}</th>
						</tr>
						<tr>
							<th scope="row">Ore di Lezione: ${currentCourse.oreLezione}</th>
						</tr>
						<tr>
							<th scope="row">Ore di Esercitazione: ${currentCourse.oreEsercitazione}</th>
						</tr>
						<tr>
							<th scope="row">Email: ${currentUser.email}</th>
						</tr>
							<tr>
							<th scope="row">Descrizione: ${currentCourse.descrizione}</th>
						</tr>
						<tr>
							<th scope="row">Requisiti: ${currentCourse.requisiti}</th>
						</tr>
						<tr>
							<th scope="row">Materiale: ${currentCourse.materiale}</th>
						</tr>
						

					</tbody>
				</table>
		</div>
		<div align="right">
			<div class="bg-unibook info-box-3 hover-zoom-effect">
				<a href="javascript:changeProfile();">
					<div class="icon">
						<i class="material-icons">mode_edit</i>
					</div>
					<div class="content">
						<div class="text">Modifica Profilo</div>
					</div>
				</a>
			</div>
		</div>
		</h2>
	</div>
	</div>
</body>
<script src="js/pages/ui/tooltips-popovers.js"></script>
<script src="javascript/changePage.js"></script>
</html>