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
					<div align="center" class="corsiTitle">Profilo:</div>
					<div align="center">
						<div align="center" class="profile-pic"
							style="background-image: url('${profilo.profileImagePath}')">
							<form onsubmit="return updateImage();"
								enctype="multipart/form-data">
								<c:if test="${currentUser.matricola==profilo.matricola }">
									<a href="javascript:uploadImage()" class="profile-pic"
										style="cursor: pointer;"> <span
										class="glyphicon glyphicon-camera"></span> <span>Cambia
											Immagine</span>
									</a>
								</c:if>
								<c:if test="${currentUser.matricola==profilo.matricola }">
									<button type="submit"
										class="btn btn-default btn-circle-lg waves-effect waves-circle waves-float">
										<i class="material-icons">publish</i>
									</button>
								</c:if>
								<input type="file" class="form-control hidden" name="image"
									id="imagebtn" accept=".png, .jpeg, .jpg" required="">
							</form>
						</div>
					</div>
					<br>
					<c:if test="${currentUser.matricola==profilo.matricola }">
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
					</c:if>
					<div class="profile" id="profile">
						<table class="table table-striped">
							<tbody>

								<tr>
									<th scope="row">Nome:&nbsp;&nbsp;${profilo.nome}
										${profilo.cognome}</th>
								</tr>
								<c:if test="${currentUser.matricola==profilo.matricola }">
									<tr>
										<th>Matricola:&nbsp;&nbsp;${profilo.matricola}</th>
									</tr>
								</c:if>
								<c:if test="${currentUser.matricola==profilo.matricola }">
									<tr>
										<th>Codice Fiscale:&nbsp;&nbsp;${profilo.codicefiscale}</th>
									</tr>

								</c:if>

								<tr>
									<th><c:if test="${profilo.ruolo == 0 }">
							Ruolo:&nbsp;&nbsp;Studente
							</c:if> <c:if test="${profilo.ruolo == 1 }">
							Ruolo:&nbsp;&nbsp;Docente
							</c:if></th>
								</tr>
								<tr>
									<th>Email:&nbsp;&nbsp;${profilo.email}</th>
								</tr>
								<c:if test="${currentUser.matricola==profilo.matricola }">
									<tr>
										<th>Data di
											Nascita:&nbsp;&nbsp;${profilo.getDataNascitaString()}</th>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<div class="modify hidden" id="modify">
						<table class="table table-striped">
							<tbody>

								<tr>
									<th scope="row">Nome:&nbsp;&nbsp;${profilo.nome}
										${profilo.cognome}</th>
								</tr>
								<c:if test="${currentUser.matricola==profilo.matricola }">
									<tr>
										<th>Matricola:&nbsp;&nbsp;${profilo.matricola}</th>
									</tr>
								</c:if>
								<c:if test="${currentUser.matricola==profilo.matricola }">
									<tr>
										<th>Codice Fiscale:&nbsp;&nbsp;${profilo.codicefiscale}</th>
									</tr>

								</c:if>
								<tr>
									<th><c:if test="${profilo.ruolo == 0 }">
							Ruolo:&nbsp;&nbsp;Studente
							</c:if> <c:if test="${profilo.ruolo == 1 }">
							Ruolo:&nbsp;&nbsp;Docente
							</c:if></th>
								</tr>
								<tr>
									<th style="display: -webkit-inline-box;">Email:&nbsp;&nbsp;
										<input name="email" id="email"cols="30" rows="5"
										class="form-control no-resize hidden" value="${profilo.email}"
										required> <label class="form-label"></label>&nbsp;
										<button id="modificaEmail" type="button" class="btn bg-unibook waves-effect" onclick="javascript:modificaEmail();">
											<span>Modifica</span>
										</button>	
										<button  id="modificaEmailConfirm" type="button" class="btn bg-unibook waves-effect hidden" >
											<i class="material-icons">check</i>
										</button>
									</th>
								</tr>
								<c:if test="${currentUser.matricola==profilo.matricola }">
									<tr>
										<th>Data di
											Nascita:&nbsp;&nbsp;${profilo.getDataNascitaString()}</th>
									</tr>
								</c:if>
							
								<tr>
									<th style="display: -webkit-inline-box;">Password:&nbsp;&nbsp;
										<input name="password" id="password" cols="30" rows="5"
										class="form-control no-resize hidden" value="${profilo.password}"
										required> <label class="form-label"></label>&nbsp;
										<button id="modificaPassword" type="button" class="btn bg-unibook waves-effect" onclick="javascript:modificaPassword();">
											<span>Modifica</span>
										</button>
										<button  id="modificaPasswordConfirm" type="button" class="btn bg-unibook waves-effect hidden" onclick="javascript:confermaModificaPassword();">
											<i class="material-icons">check</i>
										</button>
									</th>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</h2>
		</div>
	</div>
</section>

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