<%@page import="persistence.DatabaseManager"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Utente"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="plugins/sweetalert/sweetalert.css" rel="stylesheet">
<div class="container-fluid">
	<div class="block-header">
		<h2>
			<div class="body table-responsive">
				<div align="center" class="corsiTitle">Profilo:</div>
				<div align="center">
					<div align="center" class="profile-pic"
						style="background-image: url('${currentUser.profileImagePath}')">
						<form
							onsubmit="return updateImage();" enctype="multipart/form-data">
							<a href="javascript:uploadImage()" class="profile-pic"
								style="cursor: pointer;"> <span
								class="glyphicon glyphicon-camera"></span> <span>Cambia
									Immagine</span>
							</a> <button type="submit" class="btn btn-default btn-circle-lg waves-effect waves-circle waves-float">
                                    <i class="material-icons">publish</i>
                                </button><input type="file" class="form-control hidden" name="image"
								id="imagebtn" accept=".png, .jpeg, .jpg" required="">
						</form>
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
							<th scope="row">Nome:&nbsp;&nbsp;${currentUser.nome}
								${currentUser.cognome}</th>
						</tr>
						<tr>
							<th>Matricola:&nbsp;&nbsp;${currentUser.matricola}</th>
						</tr>
						<tr>
							<th>Codice Fiscale:&nbsp;&nbsp;${currentUser.codicefiscale}</th>
						</tr>

						<tr>
							<th><c:if test="${currentUser.ruolo == 0 }">
							Ruolo:&nbsp;&nbsp;Studente
							</c:if> <c:if test="${currentUser.ruolo == 1 }">
							Ruolo:&nbsp;&nbsp;Docente
							</c:if></th>
						</tr>
						<tr>
							<th>Email:&nbsp;&nbsp;${currentUser.email}</th>
						</tr>
						<tr>
							<th>Data di
								Nascita:&nbsp;&nbsp;${currentUser.getDataNascitaString()}</th>
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
<script src="javascript/uploadImage.js" type="text/javascript"></script>
<script src="plugins/sweetalert/sweetalert.min.js"></script>
<script src="js/pages/ui/dialogs.js"></script>
<script src="plugins/jquery-validation/jquery.validate.js"></script>
</html>