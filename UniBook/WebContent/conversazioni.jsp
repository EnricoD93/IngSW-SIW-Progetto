<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style>
.container {
	border: 2px solid #dedede;
	background-color: #f1f1f1;
	border-radius: 5px;
	padding: 10px;
	margin: 10px 0;
}

.darker {
	border-color: #ccc;
	background-color: #ddd;
}

.container::after {
	content: "";
	clear: both;
	display: table;
}

.container img {
	float: left;
	max-width: 60px;
	width: 100%;
	margin-right: 20px;
	border-radius: 50%;
}

.container img.right {
	float: right;
	margin-left: 20px;
	margin-right: 0;
}

.time-right {
	float: right;
	color: #aaa;
}

.time-left {
	float: left;
	color: #999;
}
</style>
<script src="plugins/autosize/autosize.js"></script>
</head>
<section id="centralSection" class="content">
	<div class="container-fluid">
		<div class="block-header">
			<div class="corsiTitle" align="center">Messaggi</div>
			<br>
			<div class="body">
				<c:forEach var="messaggio" varStatus="loop" items="${messaggi}">
					<c:if test="${messaggio.mittente!=currentUser.matricola }">
						<div class="container">
							<img src="${utenteConversazione.profileImagePath}" alt="Avatar"
								style="width: 100%;">
							<p>${messaggio.testo}</p>
							<span class="time-right">${messaggio.data}&thinsp;${messaggio.ora}</span>
						</div>
					</c:if>
					<c:if test="${messaggio.mittente==currentUser.matricola}">
						<div class="container darker">
							<img src="${currentUser.profileImagePath}" alt="Avatar"
								class="right" style="width: 100%;">
							<p>${messaggio.testo}</p>
							<span class="time-left">${messaggio.data}&thinsp;${messaggio.ora}</span>
						</div>
					</c:if>
				</c:forEach>
				<textarea name="descrizione" cols="30" rows="5"
												class="form-control no-resize" required></textarea><button type="button" class="btn bg-red btn-circle-lg waves-effect waves-circle waves-float">
                                    <i class="material-icons">print</i>
                                </button>
			</div>
		</div>
	</div>
</section>
</html>