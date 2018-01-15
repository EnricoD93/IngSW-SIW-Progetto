<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>
<section id="centralSection" class="content">
	<div class="container-fluid">
		<div class="block-header">
			<h2>
				<div class="body">
					<div class="corsiTitle" align="center">I miei Corsi</div>


					<table class="table table-striped">
						<thead>
							<tr>
								<th>Cod.</th>
								<th>Nome del Corso</th>
								<c:if test="${currentUser.ruolo == 0 }">
									<th>Docente</th>
								</c:if>
								<c:if test="${currentUser.ruolo == 1 }">
									<th>Corso Di Laurea</th>
								</c:if>
								<th>CFU</th>
								<c:if test="${currentUser.ruolo == 0 }">
									<th></th>
								</c:if>

							</tr>

						</thead>
						<tbody>
							<c:forEach var="corso" items="${corsi}">
								<tr name="corsoSelezionato" value="${corso.codice}"
									style="cursor: pointer;">
									<th scope="row"><a
										href="page?request=corso&id=${corso.codice}">${corso.codice}</a></th>
									<td><a href="page?request=corso&id=${corso.codice}">${corso.nome}
									</a></td>
									<c:if test="${currentUser.ruolo == 0 }">
										<td><a href="page?request=corso&id=${corso.codice}">${corso.cognomeDocente}&nbsp;${corso.nomeDocente}</a></td>
									</c:if>
									<c:if test="${currentUser.ruolo == 1 }">
										<td><a href="page?request=corso&id=${corso.codice}">${corso.corsoDiLaurea}</a></td>
									</c:if>
									<td><a href="page?request=corso&id=${corso.codice}">${corso.cfu}</a></td>
									<td><c:if test="${currentUser.ruolo==0 }">
											<button type="button"
												class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
												style="margin-left: 39px;"
												onclick="javascript:confermaElimina(${currentUser.matricola},${corso.codice});">
												<i class="material-icons">clear</i>

											</button>
										</c:if> <c:if test="${currentUser.ruolo==1 }">
											<button type="button"
												class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
												style="margin-left: 39px;"
												onclick="javascript:confermaEliminaCorso(${currentUser.matricola},${corso.codice});">
												<i class="material-icons">clear</i>

											</button>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>




				</div>
				<c:if test="${currentUser.ruolo == 1 }">
					<div align="right">
						<div class="bg-unibook info-box-3 hover-zoom-effect">
							<a href="page?request=creaCorso">
								<div class="icon">
									<i class="material-icons">mode_edit</i>
								</div>
								<div class="content">
									<div class="text">Crea un Corso</div>
								</div>
							</a>
						</div>
					</div>
				</c:if>
			</h2>
		</div>
	</div>
</section>
</html>