<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<title>Home | UniBook</title>
</head>
<body>
	<section id="centralSection" class="content">
		<div class="container-fluid">
			<div class="block-header">
				<h2>
					<div class="corsiTitle">Benvenuto ${currentUser.nome}&nbsp;!</div>

					</br>
					<div class="body table-responsive">
						<div align="center" class="corsiTitle">Corsi disponibili</div>
						
					
					<div align="center">		
					Di seguito l'elenco dei corsi del tuo corso di
									laurea, clicca sul nome del corso per consultare la scheda del
									corso ed iscriverti ad esso.</br> Potrai ricevere in tempo reale
									ogni avviso.
					</div>
					
						
						<table class="table table-striped">
							<thead>
								<tr>
									<th width="30">Cod.</th>
									<th width="50%">Nome del Corso</th>
									<th>Docente</th>
									<th>Corso Di Laurea</th>
									<th>CFU</th>
								</tr>

							</thead>
							<tbody>

								<c:forEach var="corso" items="${corsi}">
									<tr name="corsoSelezionato" value="${corso.codice}"
										style="cursor: pointer;">
										<th scope="row">${corso.codice}</th>
										<td><a href="page?request=corso&id=${corso.codice}">${corso.nome}
										</a></td>
										<td><a href="page?request=profilo&id=${corso.docente}">${corso.cognomeDocente}&nbsp;${corso.nomeDocente}</a></td>
										<td>${corso.corsoDiLaurea}</td>
										<td>${corso.cfu}</td>
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