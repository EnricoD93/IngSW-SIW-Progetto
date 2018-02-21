<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>

<!DOCTYPE html>
<html>
<script src="javascript/mapAule.js"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAb97am6mBB7n7mKN1e3dr2bwxLavJyvik&callback=initMap">
	
</script>
<section id="centralSection" class="content">
	<div class="container-fluid">
		<div class="block-header">
			<div class="body">
				<div class="corsiTitle" align="center">Aule</div>
				<div id="map" style="height: 250px;"></div>
				<br>
				<div id="aule">
					<div class="row clearfix">
						<c:forEach var="aula" items="${aule}">
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
								<div class="card">
									<div class="header bg-unibook">
										<h2 class="col-white">${aula.id}
											<a href="javascript:localizza(${aula.id});"><i
												style="color: white; margin-left: 78%; position: inline-block;"
												class="material-icons">gps_fixed</i></a>
										</h2>
										
									</div>
									<div class="body">
										<b>Ubicazione :</b> ${aula.ubicazione}<br> <b>
											Capienza:</b> ${aula.posti} posti.
									</div>
								</div>
							</div>
						</c:forEach>

					</div>

				</div>
			</div>
			<br></br> <b>*Tutte le aule sono dotate di accesso alla rete
				Wi-fi tramite apposite credenziali</b>
		</div>
	</div>
</section>

</html>