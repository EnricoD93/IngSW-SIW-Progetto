<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>
<section id="centralSection" class="content">
	<div class="container-fluid">
		<div class="block-header">
			<div class="body">
				<div class="corsiTitle" align="center">Docenti</div>
				<br>
				<div id="docenti">
					<div class="corsiTitle">Corso di Laurea in Informatica</div>
					<div class="row clearfix">
						<c:forEach var="docente" items="${docenti}">
							<c:if test="${docente.corsoDiLaurea==0733 }">
								<div class="col-xs-6" style="min-width: 170px">
									<a href="page?request=profilo&id=${docente.matricola}">
										<div class="card">
											<div class="header bg-unibook" style="padding: 10px">
												<h2 align="center" class="col-white">${docente.cognome}&thinsp;${docente.nome}
												</h2>
											</div>
											<div align="center" class="body">
												<div class="image">
													<div class="profile-pic-s"
														style="background-image: url('${docente.profileImagePath}')">
													</div>
												</div>
												<c:if test="${currentUser.ruolo==1 }">
													<div align="center">
														<b>Matricola: </b> <br> ${docente.matricola}
													</div>
												</c:if>
											</div>
										</div>
									</a>
								</div>
							</c:if>
						</c:forEach>

					</div>
					<div class="corsiTitle">Corso di Laurea in Matematica</div>
					<div class="row clearfix">
						<c:forEach var="docente" items="${docenti}">
						<c:if test="${docente.corsoDiLaurea==0726 }">
							<div class="col-xs-6" style="min-width: 170px">
								<a href="page?request=profilo&id=${docente.matricola}">
									<div class="card">
										<div class="header bg-unibook" style="padding: 10px">
											<h2 align="center" class="col-white">${docente.cognome}&thinsp;${docente.nome}
											</h2>
										</div>
										<div align="center" class="body">
											<div class="image">
												<div class="profile-pic-s"
													style="background-image: url('${docente.profileImagePath}')">
												</div>
											</div>
											<c:if test="${currentUser.ruolo==1 }">
												<div align="center">
													<b>Matricola: </b> <br> ${docente.matricola}
												</div>
											</c:if>
										</div>
									</div>
								</a>
							</div>
							</c:if>
						</c:forEach>

					</div>
				</div>
			</div>
			<br>
		</div>
	</div>
</section>

</html>