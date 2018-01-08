<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>
<link href="plugins/sweetalert/sweetalert.css" rel="stylesheet">
<section id="centralSection" class="content">
	<div class="container-fluid">
		<div class="block-header">
			<h2>
				<div class="body table-responsive">
					<div class="corsiTitle" align="center">${currentCourse.nome }</div>
					<div class="corsiTitle" align="left">
						Studenti Iscritti
						<c:if test="${currentUser.matricola==currentCourse.docente}">
							<button type="button" id="aggiungiStudente"
								style="margin-left: 700px;"
								class="bg-unibook btn-circle-lg waves-effect waves-circle waves-float"
								title="Aggiungi Studente">
								<i class="material-icons">person_add</i>
							</button>
						</c:if>

					</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th></th>
								<th>Nome</th>
								<th>Matricola</th>
								<c:if test="${currentUser.matricola==currentCourse.docente}">
									<th>Codice Fiscale</th>
								</c:if>
								<th>Email</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="studente" varStatus="loop"
								items="${studentiIscritti}">
								<tr style="cursor: pointer;">
									<td>${loop.index+1}</td>
									<td><a
										href="page?request=profilo&id=${studente.matricola}">
											<div class="profile-pic-xs"
												style="background-image: url('${studente.profileImagePath}')"></div>
									</a></td>
									<td scope="row">${studente.cognome}&nbsp;${studente.nome}</td>
									<td>${studente.matricola}</td>
									<c:if test="${currentUser.matricola==currentCourse.docente}">
										<td>${studente.codicefiscale}</td>
									</c:if>
									<td>${studente.email}</td>

									<td><c:if
											test="${currentUser.matricola==currentCourse.docente}">
											<button type="button" id="eliminaStudente"
												onclick="javascript:confermaEliminaStudente(${studente.matricola},${currentCourse.codice})"
												class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
												title="Elimina Studente">
												<i class="material-icons">delete</i>
											</button>
										</c:if></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</h2>
		</div>
	</div>
</section>
<script src="plugins/sweetalert/sweetalert.min.js"></script>
<script src="js/pages/ui/dialogs.js"></script>

</html>