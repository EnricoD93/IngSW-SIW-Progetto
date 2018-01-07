<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<div class="container-fluid">
		<div class="block-header">
			<h2>
				<div class="body table-responsive">
					<div class="corsiTitle" align="center">${currentCourse.nome }</div>
					<div class="corsiTitle" align="left">
						Studenti Iscritti
						<div align="right">
							<button type="button" id="aggiungiStudente"
								class="bg-unibook btn-circle-lg waves-effect waves-circle waves-float"
								title="AggiungiStudente" data-toggle="tooltip"
								data-placement="left" title=""
								data-original-title="Aggiungi Studente">
								<i class="material-icons">person_add</i>
							</button>
						</div>
					</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th></th>
								<th>Nome</th>
								<th>Matricola</th>
								<th>Codice Fiscale</th>
								<th>Email</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="studente" varStatus="loop"
								items="${studentiIscritti}">
								<tr style="cursor: pointer;">
									<td>${loop.index+1}</td>
									<td>
										<div class="profile-pic-xs"
											style="background-image: url('${studente.profileImagePath}')"></div>
									</td>
									<td scope="row">${studente.cognome}&nbsp;${studente.nome}</td>
									<td>${studente.matricola}</td>
									<td>${studente.codicefiscale}</td>
									<td>${studente.email}</td>

									<td><button type="button" id="eliminaStudente"
											class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
											title="EliminaStudente" data-toggle="tooltip"
											data-placement="left" title=""
											data-original-title="Elimina Studente">
											<i class="material-icons">delete</i>
										</button></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
		</div>
		</h2>
	</div>
	</div>
</body>
<script src="js/pages/ui/tooltips-popovers.js"></script>
</html>