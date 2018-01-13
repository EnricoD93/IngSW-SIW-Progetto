<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script src="plugins/autosize/autosize.js"></script>
<script src="javascript/message.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('html, body').animate({
			scrollTop : $('body').offset().bottom
		//scrollTop: $('#your-id').offset().top
		//scrollTop: $('.your-class').offset().top
		}, 'slow');
		window.scrollTo(0, document.body.scrollHeight);

	});
</script>
</head>
<section id="centralSection" class="content" style="overflow: auto; margin: 70px 0px 0px 300px;">
	<div class="content" style="background-color: #C4161C; position: fixed; width: 100%; z-index:5">
		<div class="image">
			<div class="profile-pic-xs"
				style="background-image: url('${utenteConversazione.profileImagePath}')">
			</div>
		</div>
		<c:if test="${currentUser.ruolo==0}">
			<div align="center" style="color: white; font-size: 30px;">${utenteConversazione.nome}&thinsp;${utenteConversazione.cognome}</div>
		</c:if>
		<c:if test="${currentUser.ruolo==1}">
			<div align="center" style="color: white; font-size: 30px;">${utenteConversazione.nome}&thinsp;${utenteConversazione.cognome}&nbsp;Matr.
				${utenteConversazione.matricola}</div>
		</c:if>
	</div>
	<div class="container-fluid">
		<div class="block-header">
			<br>
			<div class="row clearfix">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"></div>
				<div id="messages">
					<c:forEach var="messaggio" varStatus="loop" items="${messaggi}">
						<c:if test="${messaggio.mittente!=currentUser.matricola }">
							<div class="card" style="border-radius: 5px; margin-right: 10%">
								<div class="mycontainer">
									<p style="font-size: 16px;">${messaggio.testo}</p>
									<span class="time-left">${messaggio.datareale}&thinsp;</span>
								</div>
							</div>
						</c:if>
						<c:if test="${messaggio.mittente==currentUser.matricola}">
							<div class="card" style="border-radius: 5px; margin-left: 10%">
								<div class="mycontainer darker">
									<p style="font-size: 16px;">${messaggio.testo}</p>
									<span class="time-right">${messaggio.datareale}&thinsp;</span>
								</div>
							</div>
						</c:if>
					</c:forEach><div id="lastRow"></div>
				</div>
				<br>
				<div class="form-group form-float" >
					<div class="form-line">
						<textarea id="text" cols="30" rows="5"
							class="form-control no-resize" required autofocus></textarea>
						<label class="form-label">Messaggio</label> <input type="hidden"
							id="dest" value="${utenteConversazione.matricola}"> <input
							type="hidden" id="mitt" value="${currentUser.matricola}">
						<div align="right">
							<button id="send" type="button" title="Invia"
								class="btn bg-unibook btn-circle-lg waves-effect waves-circle waves-float">
								<i class="material-icons" style="line-height: inherit;">send</i>
							</button>
						</div>
					</div>
					<br>
				</div>
			</div>
		</div>
	</div>
</section>
</html>