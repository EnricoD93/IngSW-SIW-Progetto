<%@page import="persistence.DatabaseManager"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Utente"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<title>Home | UniBook</title>
<!-- Favicon-->
<link rel="icon" href="favicon.ico" type="image/x-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" type="text/css">

<!-- Bootstrap Core Css -->
<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Waves Effect Css -->
<link href="plugins/node-waves/waves.css" rel="stylesheet" />

<!-- Animation Css -->
<link href="plugins/animate-css/animate.css" rel="stylesheet" />

<!-- Custom Css -->
<link href="css/style.css" rel="stylesheet">

<!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
<link href="css/themes/all-themes.css" rel="stylesheet" />
</head>

<body class="theme-red">
	<!-- Page Loader -->
	<div class="page-loader-wrapper">
		<div class="loader">
			<div class="preloader">
				<div class="spinner-layer pl-red">
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
	<div class="search-bar">
		<div class="search-icon">
			<i class="material-icons">search</i>
		</div>
		<input type="text" placeholder="START TYPING...">
		<div class="close-search">
			<i class="material-icons">close</i>
		</div>
	</div>
	<!-- #END# Search Bar -->
	<!-- Top Bar -->
	<nav class="navbar">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="javascript:void(0);" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar-collapse"
				aria-expanded="false"></a> <a href="javascript:void(0);"
				class="bars"></a> <a class="navbar-brand" href="home.jsp"><logoimg>
				<image src="logo.png"></image></logoimg></a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<!-- Call Search -->
				<li><a href="javascript:void(0);" class="js-search"
					data-close="true"><i class="material-icons">search</i></a></li>
				<!-- #END# Call Search -->
				<!-- Notifications -->
				<li class="dropdown"><a href="javascript:void(0);"
					class="dropdown-toggle" data-toggle="dropdown" role="button"> <i
						class="material-icons">notifications</i> <span class="label-count">7</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">Notifiche</li>
						<li class="body">
							<ul class="menu">
								<li><a href="javascript:void(0);">
										<div class="icon-circle bg-light-green">
											<i class="material-icons">person_add</i>
										</div>
										<div class="menu-info">
											<h4>12 new members joined</h4>
											<p>
												<i class="material-icons">access_time</i> 14 mins ago
											</p>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<div class="icon-circle bg-cyan">
											<i class="material-icons">add_shopping_cart</i>
										</div>
										<div class="menu-info">
											<h4>4 sales made</h4>
											<p>
												<i class="material-icons">access_time</i> 22 mins ago
											</p>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<div class="icon-circle bg-red">
											<i class="material-icons">delete_forever</i>
										</div>
										<div class="menu-info">
											<h4>
												<b>Nancy Doe</b> deleted account
											</h4>
											<p>
												<i class="material-icons">access_time</i> 3 hours ago
											</p>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<div class="icon-circle bg-orange">
											<i class="material-icons">mode_edit</i>
										</div>
										<div class="menu-info">
											<h4>
												<b>Nancy</b> changed name
											</h4>
											<p>
												<i class="material-icons">access_time</i> 2 hours ago
											</p>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<div class="icon-circle bg-blue-grey">
											<i class="material-icons">comment</i>
										</div>
										<div class="menu-info">
											<h4>
												<b>John</b> commented your post
											</h4>
											<p>
												<i class="material-icons">access_time</i> 4 hours ago
											</p>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<div class="icon-circle bg-light-green">
											<i class="material-icons">cached</i>
										</div>
										<div class="menu-info">
											<h4>
												<b>John</b> updated status
											</h4>
											<p>
												<i class="material-icons">access_time</i> 3 hours ago
											</p>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<div class="icon-circle bg-purple">
											<i class="material-icons">settings</i>
										</div>
										<div class="menu-info">
											<h4>Settings updated</h4>
											<p>
												<i class="material-icons">access_time</i> Yesterday
											</p>
										</div>
								</a></li>
							</ul>
						</li>
						<li class="footer"><a href="javascript:void(0);">Vedi
								tutte le notifiche</a></li>
					</ul></li>
				<!-- #END# Notifications -->
				<!-- Tasks -->
				<li class="dropdown"><a href="javascript:void(0);"
					class="dropdown-toggle" data-toggle="dropdown" role="button"> <i
						class="material-icons">message</i> <span class="label-count">9</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">TASKS</li>
						<li class="body">
							<ul class="menu tasks">
								<li><a href="javascript:void(0);">
										<h4>
											Footer display issue <small>32%</small>
										</h4>
										<div class="progress">
											<div class="progress-bar bg-pink" role="progressbar"
												aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
												style="width: 32%"></div>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<h4>
											Make new buttons <small>45%</small>
										</h4>
										<div class="progress">
											<div class="progress-bar bg-cyan" role="progressbar"
												aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
												style="width: 45%"></div>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<h4>
											Create new dashboard <small>54%</small>
										</h4>
										<div class="progress">
											<div class="progress-bar bg-teal" role="progressbar"
												aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
												style="width: 54%"></div>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<h4>
											Solve transition issue <small>65%</small>
										</h4>
										<div class="progress">
											<div class="progress-bar bg-orange" role="progressbar"
												aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
												style="width: 65%"></div>
										</div>
								</a></li>
								<li><a href="javascript:void(0);">
										<h4>
											Answer GitHub questions <small>92%</small>
										</h4>
										<div class="progress">
											<div class="progress-bar bg-purple" role="progressbar"
												aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"
												style="width: 92%"></div>
										</div>
								</a></li>
							</ul>
						</li>
						<li class="footer"><a href="javascript:void(0);">View All
								Tasks</a></li>
					</ul></li>
				<!-- #END# Tasks -->
				<li class="pull-right"><a href="javascript:void(0);"
					class="js-right-sidebar" data-close="true"><i
						class="material-icons">settings</i></a></li>
			</ul>
		</div>
	</div>
	</nav>
	<!-- #Top Bar -->
	<section> <!-- Left Sidebar --> <aside id="leftsidebar"
		class="sidebar"> <!-- User Info -->
	<div class="user-info">
		<div class="image">
			<img src="images/user.png" width="48" height="48" alt="User" />
		</div>

		<div class="info-container">
			<div class="name" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">${currentUser.nome}
				${currentUser.cognome} Matr. ${currentUser.matricola}</div>
			<div class="email">${currentUser.email}</div>
			<div class="btn-group user-helper-dropdown">
				<i class="material-icons" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
				<ul class="dropdown-menu pull-right">
					<li><a href="javascript:void(0);"><i
							class="material-icons">person</i>Profile</a></li>
					<li role="seperator" class="divider"></li>
					<li><a href="javascript:void(0);"><i
							class="material-icons">group</i>Followers</a></li>
					<li><a href="javascript:void(0);"><i
							class="material-icons">shopping_cart</i>Sales</a></li>
					<li><a href="javascript:void(0);"><i
							class="material-icons">favorite</i>Likes</a></li>
					<li role="seperator" class="divider"></li>
					<li><a href="index.html"> <i class="material-icons">input</i>Log
							Out
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- #User Info --> <!-- Menu -->
	<div class="menu">
		<ul class="list">
			<li class="header">Navigazione principale</li>
			<li><a href="home.jsp"> <i class="material-icons">home</i> <span>Home</span>
			</a></li>
			<li><a href="corsiPersonali.jsp"> <i class="material-icons">library_books</i>
					<span>I miei Corsi</span>
			</a></li>
			<li><a href="pages/calendar.html"> <i class="material-icons">date_range</i>
					<span>Calendario personale</span>
			</a></li>
			<li><a href="aule.jsp"> <i class="material-icons">business</i>
					<span>Aule</span>
			</a></li>

			<li class="active"></li>
		</ul>
	</div>
	<!-- #Menu --> <!-- Footer -->
	<div class="legal">Progetto realizzato per Ingegneria del
		Software / Web Computing a.a. 2017/2018</div>
	<!-- #Footer --> </aside> <!-- #END# Left Sidebar --> <!-- Right Sidebar --> <aside
		id="rightsidebar" class="right-sidebar">
	<ul class="nav nav-tabs tab-nav-right" role="tablist">
		<li role="presentation" class="active"><a href="settings"
			data-toggle="tab">Impostazioni</a></li>
	</ul>
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane fade in active in active"
			id="settings">
			<div class="demo-settings">
				<p>Impostazioni generali</p>
				<ul class="setting-list">
					<li><span>Notifiche</span>
						<div class="switch">
							<label><input type="checkbox" checked><span
								class="lever"></span></label>
						</div></li>
					<li><span>Aggiornamenti automatici</span>
						<div class="switch">
							<label><input type="checkbox" checked><span
								class="lever"></span></label>
						</div></li>
				</ul>
			</div>
		</div>
	</div>
	</aside> <!-- #END# Right Sidebar --> </section>

	<section class="content">
	<div class="container-fluid">
		<div class="block-header">
			<h2>
				<div class="body table-responsive">
					<div class="corsiTitle">Aule</div>
					<img alt="mappa" src="images/image-gallery/aule.gif">
					<table class="table table-striped">
						<tbody>

							<c:forEach var="aula" items="${aule}">
								<tr>
									<th>
									<td>L'aula <b>${aula.id}</b> appartiene al Corso di Laurea
									<c:if test="${aula.corsoDiLaurea == 733 }">
									di <b>Informatica</b> </c:if>
								
									<c:if test="${aula.corsoDiLaurea == 726 }">
									di <b>Matematica</b> </c:if>
								
									ha una capienza di <b>${aula.posti}</b> posti</td>
									</th>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<br></br>
<b>*Tutte le aule sono dotate di accesso alla rete Wi-fi tramite apposite credenziali</b>
			</h2>
		</div>
	</div>
		<c:if test="${currentUser==null}">
					<%
						response.sendRedirect("index.html");
					%>
				</c:if>

				<%
					response.setHeader("Cache-Control", "no-cache");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Pragma", "no-cache");
					response.setDateHeader("Expires", 0);
				%>
	
	</section>

	<!-- Jquery Core Js -->
	<script src="plugins/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core Js -->
	<script src="plugins/bootstrap/js/bootstrap.js"></script>

	<!-- Select Plugin Js -->
	<script src="plugins/bootstrap-select/js/bootstrap-select.js"></script>

	<!-- Slimscroll Plugin Js -->
	<script src="plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

	<!-- Waves Effect Plugin Js -->
	<script src="plugins/node-waves/waves.js"></script>

	<!-- Custom Js -->
	<script src="js/admin.js"></script>

	<!-- Demo Js -->
	<script src="js/demo.js"></script>
</body>


</html>