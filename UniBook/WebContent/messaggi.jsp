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
					<c:forEach var="utente" items="${conversazioni}">

						<li class="list-group-item" style="height: 75px;">

							<div class="profile-pic-xs"
								style="float:left; background-image: url('${utente.profileImagePath}');"></div>
							<a style="float: left;"
							href="page?request=conversazione&id=${utente.matricola}"> <span
								style="float: left; font-size: 18px; margin-left: 10px;">${utente.nome}&thinsp;${utente.cognome}</span></a>
							<c:if
								test="${letti[utente.matricola]>0 &&letti[utente.matricola]<2}">
								<span class="badge bg-unibook"> <c:out
										value="${letti[utente.matricola]}" /> nuovo messaggio
								</span>

							</c:if> <c:if test="${ letti[utente.matricola]>1}">
								<span class="badge bg-unibook"><c:out
										value="${letti[utente.matricola]}" /> nuovi messaggi</span>
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div id="docenti"></div>
		</div>
	</div>
</section>

</body>
</html>