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
					<div class="corsiTitle" align="center">Esami</div>

					<table class="table table-striped">
						<thead>
							<tr>
								<th>Cod.</th>
								<th>Nome del Corso</th>
								<th>CFU</th>
								<th>Data conseguimento</th>
								<th>Voto</th>
							</tr>

						</thead>
						<tbody>
							<c:forEach var="esame" items="${esami}">
								<tr name="esameSelezionato" value="${esame.corso}">
									<th scope="row">${esame.corso}</th>
									<td><a href="page?request=corso&id=${esame.corso}">${esame.nome}
									</a></td>
									<td>${esame.cfu}</td>
									<td>${esame.datareale}</td>
									<td><b>${esame.voto}</b></td>
									<td><img src="images/superato.png" /></td>
									<td><button type="button"
											class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
											style="margin-left: 39px;" onclick="javascript:void(0);">
											<i class="material-icons">clear</i>

										</button></td>
								</tr>
							</c:forEach>
							<c:forEach var="esame" items="${esamiIscritto}">
								<tr name="esameSelezionato" value="${esame.corso}">
									<th scope="row">${esame.corso}</th>
									<td><a href="page?request=corso&id=${esame.corso}">${esame.nome}
									</a></td>
									<td>${esame.cfu}</td>
									<td>-</td>
									<td>-</td>
									<td><img src="images/frequentando.png" /></td>
									<td><button type="button"
											class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
											style="margin-left: 39px;" onclick="javascript:void(0);">
											<i class="material-icons">add</i>

										</button></td>
								</tr>
							</c:forEach>
							<c:forEach var="esame" items="${esamiNonSuperati}">
								<tr name="esameSelezionato" value="${esame.corso}">
									<th scope="row">${esame.corso}</th>
									<td><a href="page?request=corso&id=${esame.corso}">${esame.nome}
									</a></td>
									<td>${esame.cfu}</td>
									<td>-</td>
									<td>-</td>
									<td><img src="images/nonfrequentato.png" /></td>
									<td><button type="button"
											class="bg-unibook btn-circle-lg-xs waves-effect waves-circle waves-float"
											style="margin-left: 39px;" onclick="javascript:void(0);">
											<i class="material-icons">clear</i>

										</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="margin-bottom: 10px;">
						<img src="images/superato.png"  style="margin-bottom: 5px;"  />
						<b>Superato</b>
					</div>

					<div style="margin-bottom: 10px;">
						<img src="images/frequentando.png" style="margin-bottom: 5px;" />
						<b>Frequentato</b>
					</div>
					<div style="margin-bottom: 10px;">
						<img src="images/nonfrequentato.png" style="margin-bottom: 5px;" />
						<b>Non Superato</b>
					</div>
				</div>
			</h2>
		</div>
	</div>
</section>

</body>
</html>