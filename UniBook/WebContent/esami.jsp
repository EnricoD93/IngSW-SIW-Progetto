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
								<tr name="esameSelezionato" value="${esame.corso}"
									style="cursor: pointer;">
									<th scope="row">${esame.corso}</th>
									<td><a href="page?request=corso&id=${esame.corso}">${esame.nome}
									</a></td>
									<td>${esame.cfu}</td>
									<td>${esame.datareale}</td>
									<td>${esame.voto}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</h2>
		</div>
	</div>
</section>

</body>
</html>