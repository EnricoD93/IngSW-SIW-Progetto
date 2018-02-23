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
					<div class="corsiTitle">Ciao ${currentUser.nome}&nbsp;! 😉</div>

					<br>
					<div class="descrizione" style="margin-bottom: 50px;">
						Di seguito trovi tutti i corsi del dipartimento al quale risulti
						iscritto. <br>
					</div>
					<div class="list-group" style="margin-bottom:50px;">
						<div class="list-group-item">
							<div class="list-group-item-heading">Iscriversi ad un corso</div>
							<div class="list-group-item-text">Per iscriverti ad un
								corso clicca sul nome del corso al quale vuoi iscriverti e in
								seguito clicca su Iscriviti.</div>
						</div>
						<div class="list-group-item">
							<div class="list-group-item-heading">Inviare un messaggio
								ad un utente</div>
							<div class="list-group-item-text">Per inviare un messaggio
								ad un altro utente clicca sul profilo e seleziona Docenti o
								Colleghi. Seleziona il profilo dell'utente al quale vuoi inviare
								il messaggio e in seguit clicca su Invia messaggio.</div>
						</div>
					</div>


					<div class="body table-responsive">
						<div align="center" class="corsiTitle">Corsi disponibili</div>


						<div align="center">
							Di seguito l'elenco dei corsi del tuo corso di laurea, clicca sul
							nome del corso per consultare la scheda del corso ed iscriverti
							ad esso.</br> Potrai ricevere in tempo reale ogni avviso.
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