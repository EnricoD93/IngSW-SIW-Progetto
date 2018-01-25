<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>
<script src="javascript/courseManager.js" type="text/javascript"></script>
<body>
	<section id="centralSection" class="content">
		<div class="container-fluid">
			<div class="block-header">
				<h2>
					<input class="hidden" id="codice" value="${codice}">


					<div class="corsiTitle" align="center">${currentCourse.nome }</div>
					<div class="corsiTitle input-group" align="left">
						Studenti Iscritti <br>
						<c:if
							test="${currentUser.ruolo == 1 && currentCourse.docente==currentUser.matricola}">

							<span class="input-group-addon "> Lezione </span>
							<select class="selectpicker" tabindex="-98" name="lezione"
								id="lezioneid" required>
								<c:forEach var="lezione" items="${lezioni}">

									<option value="${lezione.id}">${lezione.data.giorno}/${lezione.data.mese}/${lezione.data.anno}</option>

								</c:forEach>
							</select>

						</c:if>

						<c:if test="${currentUser.matricola==currentCourse.docente}">
							<button type="button" id="aggiungiStudente"
								onclick="javascript:iscriviStudenteM(${currentCourse.codice})"
								style="margin-left: 700px;"
								class="bg-unibook btn-circle-lg waves-effect waves-circle waves-float"
								title="Aggiungi Studente">
								<i class="material-icons">person_add</i>
							</button>

						</c:if>
					</div>
					<div class="body table-responsive">
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
									<th></th>
									<c:if
										test="${currentUser.ruolo == 1 && currentCourse.docente==currentUser.matricola}">
										<th>Presenze</th>
									</c:if>
								</tr>

							</thead>
							<tbody>
							<thead id="students">
								<tr>
									<c:forEach var="studente" varStatus="loop"
										items="${studentiIscritti}">
										<tr>
											<td>${loop.index+1}</td>
											<td><a
												href="page?request=profilo&id=${studente.matricola}">
													<div class="profile-pic-xs"
														style="background-image: url('${studente.profileImagePath}')"></div>
											</a></td>
											<td scope="row"><a
												href="page?request=profilo&id=${studente.matricola}">${studente.cognome}&nbsp;${studente.nome}</a></td>
											<td>${studente.matricola}</td>
											<c:if test="${currentUser.matricola==currentCourse.docente}">
												<td>${studente.codicefiscale}</td>
											</c:if>
											<td>${studente.email}</td>

											<td><c:if
													test="${currentUser.matricola==currentCourse.docente}">
													<button type="button" id="eliminaStudente"
														onclick="javascript:confermaEliminaM(${studente.matricola},${currentCourse.codice})"
														class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
														title="Elimina Studente">
														<i class="material-icons">delete</i>
													</button>
												</c:if></td>
											<c:if test="${currentCourse.docente==currentUser.matricola}">
												<td style="padding-top: 16px; padding-left: 35px;"><input
													type="checkbox" id="${studente.matricola}"
													name="${studente.matricola}"> <label
													for="${studente.matricola}"></label></td>
											</c:if>
										</tr>
									</c:forEach>
										<tr id="lastRow"></tr>
								</tr>

							</thead>
							</tbody>
						</table>
					</div>
					<c:if
						test="${currentUser.ruolo == 1 && currentCourse.docente==currentUser.matricola}">
						<div align="right">
							<div class="bg-unibook info-box-3 hover-zoom-effect">
								<a href="javascript:salvaPresenze();">
									<div class="icon">
										<i class="material-icons">assignment_turned_in</i>
									</div>
									<div class="content">
										<div class="text">Salva Presenze</div>
									</div>
								</a>
							</div>
						</div>
					</c:if>
				</h2>
			</div>
		</div>
	</section>
</body>
<script src="plugins/sweetalert/sweetalert.min.js"></script>
<script src="js/pages/ui/dialogs.js"></script>
<script src="javascript/courseManager.js"></script>

</html>