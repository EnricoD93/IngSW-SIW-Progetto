<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>
<section id="centralSection" class="content">
	<div class="container-fluid">
		<div class="block-header">
			<div class="corsiTitle" align="center">Messaggi</div>
			<br>
			<div class="body">
				<ul class="list-group">
					<c:forEach var="utente" varStatus="loop" items="${conversazioni}">
						<li class="list-group-item"><a
							href="page?request=conversazione&id=${utente.matricola}">
								<div class="profile-pic-xs"
									style="background-image: url('${utente.profileImagePath}')"></div>
						</a> <span>${utente.nome}&thinsp;${utente.cognome}</span><span
							class="badge bg-unibook">1 nuovo messaggio</span></li>
					</c:forEach>
				</ul>
			</div>
			<div id="docenti"></div>
		</div>
	</div>
</section>

</body>
</html>