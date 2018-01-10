<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>
<section id="centralSection" class="content">
	<div class="container-fluid">
		<div class="block-header">
			<div class="body">
				<div class="corsiTitle" align="center">Colleghi</div>
				<div id="aule">
					<div class="row clearfix">
						<c:forEach var="collega" items="${colleghi}">
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
								<div class="card">
									<div class="header bg-unibook">
										<h2 class="col-white">${collega.matricola}</h2>
									</div>
									<div class="body">
										<a href="page?request=profilo&id=${collega.matricola}">
											<div class="profile-pic-xs"
												style="background-image: url('${collega.profileImagePath}')"></div>
										</a> <b>Nome : </b> ${collega.nome}<br> <b>Cognome : </b>
										${collega.cognome}
									</div>
								</div>
							</div>
						</c:forEach>

					</div>

				</div>
			</div>
			<br>
		</div>
	</div>
</section>

</html>