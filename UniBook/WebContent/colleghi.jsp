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
				<div class="corsiTitle" align="center">Colleghi</div> <br>
				<div id="aule">
					<div class="row clearfix">
						<c:forEach var="collega" items="${colleghi}">
							<div class="col-xs-2" style="min-width: 170px">
								<a href="page?request=profilo&id=${collega.matricola}">
									<div class="card">
										<div class="header bg-unibook" style="padding: 10px">
											<h2 align="center" class="col-white">${collega.cognome}&thinsp;${collega.nome}
											</h2>
										</div>
										<div align="center" class="body">
											<div class="image">
												<div class="profile-pic-s"
													style="background-image: url('${collega.profileImagePath}')">
												</div>
											</div>
								<div align="center">
									<b>Matricola: </b> <br> ${collega.matricola}
								</div>
							</div>
					</div>
					</a>
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