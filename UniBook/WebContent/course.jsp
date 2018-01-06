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
				<di
				v class="body table-responsive">
					<div align="center">
						<div class="profile-pic"
							style="background-image: url('${currentUser.profileImagePath}')">
							<c:if test="${currentUser.ruolo == 1 }">
								<a href="javascript:uploadImage()" class="profile-pic"> <span
									class="glyphicon glyphicon-camera"></span> <span>Cambia
										Immagine</span>
								</a>
							</c:if>
						</div>
						<div align="center" class="corsiTitle">Algoritmi Paralleli e
							Sistemi Distribuiti</div>
					</div>
					<div align="right" class="studentilist">
						<button type="button"
							class="btn bg-unibook btn-circle-lg waves-effect waves-circle waves-float"
							title="Studenti iscritti" data-toggle="tooltip"
							data-placement="left" title=""
							data-original-title="Studenti iscritti">
							<i class="material-icons">people</i>
						</button>
					</div>


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
								<th>Codice Fiscale: ${currentUser.codicefiscale}</th>
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
								<th>Data di Nascita: ${currentUser.getDataNascitaString()}</th>
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
</html>