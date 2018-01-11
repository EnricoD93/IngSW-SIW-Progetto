<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script src="plugins/autosize/autosize.js"></script>
</head>
<section id="centralSection" class="content">
	<div class="content" style="background-color: #C4161C;">
		<div class="image">
			<div class="profile-pic-xs"
				style="background-image: url('${utenteConversazione.profileImagePath}')">
			</div>
		</div>
		<c:if test="${currentUser.ruolo==0}">
			<div align="center" style="color: white; font-size: 30px; ">${utenteConversazione.nome}&thinsp;${utenteConversazione.cognome}</div>
		</c:if>
		<c:if test="${currentUser.ruolo==1}">
			<div style="color: white;">${utenteConversazione.nome}&thinsp;${utenteConversazione.cognome}&nbsp;Matr.
				${utenteConversazione.matricola}</div>
		</c:if>
	</div>
	<div class="container-fluid">
		<div class="block-header">
			<br>
			<div class="row clearfix">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"></div>
				<c:forEach var="messaggio" varStatus="loop" items="${messaggi}">
					<c:if test="${messaggio.mittente!=currentUser.matricola }">
						<div class="card" style="border-radius: 5px; margin-right: 10%">
							<div class="mycontainer">
								<p style="font-size: 16px;">${messaggio.testo}</p>
								<span class="time-left">${messaggio.data}&thinsp;${messaggio.ora}</span>
							</div>
						</div>
					</c:if>
					<c:if test="${messaggio.mittente==currentUser.matricola}">
						<div class="card" style="border-radius: 5px; margin-left: 10%">
							<div class="mycontainer darker">
								<p style="font-size: 16px;">${messaggio.testo}</p>
								<span class="time-right">${messaggio.data}&thinsp;${messaggio.ora}</span>
							</div>
						</div>
					</c:if>
				</c:forEach>
				<br>
				<div class="form-group form-float">
					<div class="form-line">
						<textarea name="messaggio" cols="30" rows="5"
							class="form-control no-resize" required></textarea>
						<label class="form-label">Messaggio</label>
					</div>
					<br>
					<div align="right">
						<button type="button"
							class="btn bg-unibook btn-circle-lg waves-effect waves-circle waves-float">
							<i class="material-icons" style="line-height: inherit;">send</i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</html>