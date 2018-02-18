<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="WebPattern.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Map</title>
<script src="javascript/map.js"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAb97am6mBB7n7mKN1e3dr2bwxLavJyvik&callback=initMap">
	
</script>
</head>
<body>
	<section id="centralSection" class="content">
	<img alt="doveSiamo" src="images/dovesiamo.png" style="float:left; width:60px;"></img>
		<h2>Siamo qui!</h2>
		<div id="map" style="height: 500px; width: 100%;">
			</div>
	</section>
</body>
</html>