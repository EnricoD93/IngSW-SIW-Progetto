<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prova</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,700&amp;subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="plugins/bootstrap-select/css/bootstrap-select.css"
	rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<select class="selectpicker" multiple>
  <option>Mustard</option>
  <option>Ketchup</option>
  <option>Relish</option>
</select>


<select class="selectpicker" data-live-search="true">
  <option data-tokens="ketchup mustard">Hot Dog, Fries and a Soda</option>
  <option data-tokens="mustard">Burger, Shake and a Smile</option>
  <option data-tokens="frosting">Sugar, Spice and all things nice</option>
</select>
</body>
<script src="plugins/jquery/jquery.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.js"></script>
<script src="plugins/bootstrap-select/js/bootstrap-select.js"></script>
</html>