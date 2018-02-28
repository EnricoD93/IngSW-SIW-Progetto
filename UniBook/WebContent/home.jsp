<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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



					<div class="body table-responsive">

						<div align="left">
							Di seguito trovi l'elenco dei corsi disponibili del tuo corso di
							laurea, consulta il corso che ti interessa cliccandoci sopra.<br>

						</div>
						<br>

						<div class="list-group" style="margin-bottom: 50px;">

							<c:if test="${currentUser.ruolo==0 }">

								<div align="left" class="corsiTitle" style="width: 90%;">
									<font color="#C4161C"><img alt="iscriviti"
										src="images/iscriviti.png">&nbsp;Iscriversi ad un corso</font>
								</div>
								<div class="list-group-item-text">
									Per iscriverti ad un corso basta consultare la scheda del corso
									e cliccare su iscriviti.<br> Potrai così ricevere
									notifiche restando sempre aggiornato su avvisi pubblicati, <br>lezioni
									aggiunte o rimosse.
								</div>
								<br>
							</c:if>
							<c:if test="${currentUser.ruolo==1 }">
								<div align="left" class="corsiTitle" style="width: 90%;">
									<font color="#C4161C"><img alt="iscriviti"
										src="images/iscriviti.png">&nbsp;Creare ad un corso</font>
								</div>
								<div class="list-group-item-text">
									Unibook ti da la possibilità di creare un corso, inserendo
									tutte le informazioni,<br> potrai pubblicare avvisi in
									modo semplice e rapido, per tenere sempre <br>informati
									tutti gli studenti iscritti. Clicca su 'I miei corsi' per
									creare il tuo corso.
								</div>
								<br>
							</c:if>
							<div align="right" class="corsiTitle" style="width: 90%;">
								<font color="#C4161C"><img alt="iscriviti"
									src="images/messaggio.png">&nbsp;Comunica con altri
									utenti</font>
							</div>
							<div align="right" class="list-group-item-text">
								Per comunicare con altri utenti, cerca il docente o lo studente
								nella barra di ricerca, tra <br>i docenti o tra i colleghi,
								e clicca su invia un messaggio per iniziare una conversazione.
							</div>
							<br>





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