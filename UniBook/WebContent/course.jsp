<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script src="plugins/ckeditor/ckeditor.js"></script>
<script src="javascript/courseManager.js" type="text/javascript"></script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<body>
	<section id="centralSection" class="content">
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
							<form action="page" method="GET">
								<input type="hidden" name="request" value=listaStudenti>
								<input type="hidden" name="codice"
									value="${currentCourse.codice}">
								<button type="submit"
									class="btn bg-unibook btn-circle-lg waves-effect waves-circle waves-float"
									title="Studenti iscritti">
									<i class="material-icons">people</i>
								</button>
							</form>
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
										<th>Codice del Corso: ${currentCourse.corsoDiLaurea}
											Corso di Laurea Informatica</th>
									</c:if>
									<c:if test="${currentCourse.corsoDiLaurea==726}">
										<th scope="row">Codice del Corso:
											${currentCourse.corsoDiLaurea} - Corso di Laurea Matematica</th>
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
									<th scope="row">Ore di Lezione:
										${currentCourse.oreLezione}</th>
								</tr>
								<tr>
									<th scope="row">Ore di Esercitazione:
										${currentCourse.oreEsercitazione}</th>
								</tr>
								<tr>
									<th scope="row">Descrizione: ${currentCourse.descrizione}</th>
								</tr>
								<tr>
									<th scope="row">Propedeuticità: <c:forEach var="esame" items="${esami}" varStatus="loop"> ${esame.nome}<c:if test="${!loop.last}">,</c:if> </c:forEach></th>
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
					<c:if test="${currentUser.matricola==currentCourse.docente }">
						<input type="hidden" id="codice" value="${currentCourse.codice}">
						<div align="right">
							<div class="bg-unibook info-box-3 hover-zoom-effect">
								<a href="javascript:changeProfile();">
									<div class="icon">
										<i class="material-icons">mode_edit</i>
									</div>
									<div class="content">
										<div class="text">Modifica Corso</div>
									</div>
								</a>
							</div>
						</div>
					</c:if>
					<c:if test="${currentUser.ruolo==0 }">
						<div align="right">
							<div class="bg-unibook info-box-3 hover-zoom-effect">
								<a
									href="javascript:iscriviStudente(${currentCourse.codice},${currentUser.matricola});">
									<div class="icon">
										<i class="material-icons">add</i>
									</div>
									<div class="content">
										<div class="text">Iscrivi</div>
									</div>
								</a>
							</div>
						</div>

					</c:if>
					<div align="center" class="corsiTitle">Avvisi</div>
					<c:if test="${empty advices}">Nessun avviso pubblicato</c:if>
					<c:forEach var="avviso" items="${advices}">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="card">
								<div class="header">
									<h2>
										<strong>${avviso.titolo}</strong> <small>Pubblicato il
											${avviso.datareale}</small>
										<c:if test="${currentUser.matricola==currentCourse.docente }">
											<ul class="header-dropdown m-r--5">
												<li class="dropdown"><a href="javascript:void(0);"
													class="dropdown-toggle" data-toggle="dropdown"
													role="button" aria-haspopup="true" aria-expanded="false">
														<i class="material-icons">more_vert</i>
												</a>
													<ul class="dropdown-menu pull-right">
														<li><a
															href="javascript:modifyAdvice(${avviso.id})"
															class=" waves-effect waves-block"><i
																class="material-icons">edit</i>Modifica Avviso</a></li>
														<li><a href="javascript:delAdvice(${avviso.id})"
															class=" waves-effect waves-block"><i
																class="material-icons">close</i>Elimina Avviso</a></li>
													</ul></li>
											</ul>
										</c:if>
									</h2>
								</div>
								<div class="body">${avviso.text}</div>
							</div>
						</div>
					</c:forEach>
					<c:if test="${currentUser.matricola==currentCourse.docente }">
						<div class="hidden" id="preview">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<h3>Anteprima Avviso:</h3>
								<div class="card">
									<div class="header">

										<h2>
											<strong>
												<div id="titlecontent"></div>
											</strong>
										</h2>
										<small>Pubblicato il gg/mm/aaaa - hh:mm</small>

									</div>
									<div class="body">
										<div id="editorcontent2"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="hidden" id="txteditor">
						<input class="hidden" id="idadvice" value="new">
							<input type="text" class="form-control"
								placeholder="Titolo Avviso" id="advicetitle" autofocus>
							<textarea name="myeditor" id="myeditor"></textarea>

						</div>

						<br>
						<div align="right" id="advicebutton" class=" ">
							<div class="bg-unibook info-box-3 hover-zoom-effect">
								<a href="javascript:postAdvice();">
									<div class="icon">
										<i class="material-icons">content_paste</i>
									</div>
									<div class="content">
										<div class="text">Pubblica Avviso</div>
									</div>
								</a>
							</div>
						</div>
						<div class="hidden" align="right" id="saveadvicebutton">
							<div class="bg-unibook info-box-3 hover-zoom-effect">
								<a href="javascript:saveAdvice();">
									<div class="icon">
										<i class="material-icons">save</i>
									</div>
									<div class="content">
										<div class="text">Salva Avviso</div>
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
</html>