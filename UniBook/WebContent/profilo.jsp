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
				<div align="center" class="corsiTitle">Profilo:</div>
				<div align="center">
					<div align="center" class="profile-pic"
						style="background-image: url('${currentUser.profileImagePath}')">
						<a href="javascript:uploadImage()" class="profile-pic"> <span
							class="glyphicon glyphicon-camera"></span> <span>Cambia
								Immagine</span>
						</a>
					</div>
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
			<div class="container">
				<form class="well form-horizontal" onsubmit="return updateImage();" enctype="multipart/form-data">
					<fieldset>
						<legend> Modifica Immagine Profilo! </legend>
						<div class="form-group">
							<label class="col-md-4 control-label"> Immagine </label>
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-picture-o"></i></span><input type="file" class="form-control" name="image" id="image"
										accept=".png, .jpeg, .jpg" required>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label"> </label>
							<div class="col-md-4 inputGroupContainer">
								<div class="input-group">
									<button type="submit" class="btn btn-success">
										<i class="fa fa-cloud-upload"></i>&nbsp;&nbsp; Aggiorna Immagine
									</button>
								</div>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</h2>
	</div>
</div>

<c:if test="${currentUser==null}">
	<%
		response.sendRedirect("index.html");
	%>
</c:if>


</html>