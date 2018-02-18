<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<meta name="theme-color" content="#c4161c">
<link rel="icon" sizes="192x192" href="highreslogo.png">
<title>Home | UniBook</title>
<!-- Favicon-->
<link rel="icon" href="favicon.ico" type="image/x-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" type="text/css">


<!-- Bootstrap Css -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">


<!-- Waves Effect Css -->
<link href="plugins/node-waves/waves.css" rel="stylesheet" />


<!-- Custom Css -->
<link href="css/style.css" rel="stylesheet">

<!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
<link href="css/themes/all-themes.css" rel="stylesheet" />

<!-- Script -->

<script src="plugins/jquery/jquery.js"></script>
<script src="javascript/changeProfile.js" type="text/javascript"></script>

<script src="javascript/uploadImage.js" type="text/javascript"></script>
<!-- Jquery Core Js -->
<script src="plugins/jquery/jquery.min.js"></script>

<!-- Bootstrap Core Js -->
<script src="plugins/bootstrap/js/bootstrap.js"></script>
<script src="plugins/bootstrap-notify/bootstrap-notify.js"></script>
<!-- Select Plugin Js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

<!-- Slimscroll Plugin Js -->
<script src="plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

<!-- Waves Effect Plugin Js -->
<script src="plugins/node-waves/waves.js"></script>
<!-- Custom Js -->

<script src="js/admin.js"></script>
<script src="js/demo.js"></script>
<script src="javascript/notificationManager.js"></script>
<script src="plugins/jquery-countto/jquery.countTo.js"></script>

</head>
<body class="theme-red">
<section id="centralSection" class="content">
		<div class="container-fluid">
			<div class="block-header">
				<h2>
					<div class="corsiTitle">Benvenuto!</div>
				
				
					

						<div align="left">
							Unibook è pensata per semplificare e favorire l’interazione tra il docente di un corso e i propri studenti.
							Accedi per continuare o se non conosci Unibook e vuoi provare tutte le funzionalità registrati in pochi semplici passi
						</div>

	<!-- Page Loader -->
	<div class="page-loader-wrapper">
		<div class="loader">
			<div class="preloader">
				<div class="spinner-layer pl-unibook">
					<div class="circle-clipper left">
						<div class="circle"></div>
					</div>
					<div class="circle-clipper right">
						<div class="circle"></div>
					</div>
				</div>
			</div>
			<p>Caricamento...</p>
		</div>
	</div>
	<!-- #END# Page Loader -->
	<!-- Overlay For Sidebars -->
	<div class="overlay"></div>
	<!-- #END# Overlay For Sidebars -->
	<!-- Search Bar -->


	<!-- #END# Search Bar -->
	<!-- Top Bar -->
	<nav class="navbar">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="javascript:void(0);" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-collapse"
					aria-expanded="false"></a> <a href="javascript:void(0);"
					class="bars"></a> <a class="navbar-brand" href="home"><logoimg>
					<img src="logo.png"></img></logoimg></a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse"></div>
		</div>
	</nav>
	<!-- #Top Bar -->
	<section>
		<!-- Left Sidebar -->
		<aside id="leftsidebar" class="sidebar">

			<!-- Menu -->
			<div class="menu">
				<ul class="list">
					<li class="header">Navigazione principale</li>

					<li><a href="page?request=aule"> <i class="material-icons">business</i>
							<span>Aule</span>
					</a></li>

					<li><a href="javascript:void(0)"> <i
							class="material-icons">my_location</i> <span>Dove siamo</span>
					</a></li>
					<li><a href="chiSiamo.jsp"> <i class="material-icons">info</i>
							<span>Chi siamo</span>
					</a></li>


					<li class="active"></li>
				</ul>
			</div>
	
		
			<!-- #Footer -->
		</aside>
		<!-- #END# Left Sidebar -->
		<!-- Right Sidebar -->

		<!-- #END# Right Sidebar -->
	</section>
</body>

</html>