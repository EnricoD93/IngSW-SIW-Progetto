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

<!-- Sweet Alert Css-->
<link href="css/mysweetalert.css" rel="stylesheet" />
<!-- Animation Css -->
<link href="plugins/animate-css/animate.css" rel="stylesheet" />

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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- Waves Effect Plugin Js -->
<script src="plugins/node-waves/waves.js"></script>
<!-- Custom Js -->

<script src="js/admin.js"></script>
<script src="js/demo.js"></script>
<script src="javascript/notificationManager.js"></script>
<script src="plugins/jquery-countto/jquery.countTo.js"></script>

</head>
<body class="theme-red">
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
					<image src="logo.png"></image></logoimg></a>
			</div>

			<div class="collapse navbar-collapse slimScrollDiv" id="navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
				<div class="wrap dropdown">
					<div class="search">
						<input type="text" id="searchbarinput" onchange="search();"
							class="searchTerm" placeholder="Cerca...">
						<button type="button" class="searchButton">
							<i class="material-icons">search</i>
						</button>
					</div>
				</div>
				<div id="elements" class="list-group searchlist slimScrollBar">
					<c:forEach var="utente" items="${tuttiutenti}">
						<button type="button" name="searchelements"
							class="list-group-item hidden" id=${utente.matricola }
							value="${utente.matricola}${utente.nome}${utente.cognome}">${utente.nome}&nbsp;${utente.cognome}</button>
					</c:forEach>
					<c:forEach var="aula" items="${tutteaule}">
						<button type="button" name="searchelements"
							class="list-group-item hidden" id=${aula.id }
							value="${aula.id}${aula.ubicazione}">${aula.id}&nbsp;${aula.ubicazione}</button>
					</c:forEach>
					<c:forEach var="corso" items="${tutticorsi}">
						<button type="button" name="searchelements"
							class="list-group-item hidden" id=${corso.codice }
							value="${corso.codice}${corso.nome}${corso.nomeDocente}${corso.cognomeDocente }">${corso.nome}&nbsp;-&nbsp;${corso.nomeDocente}&nbsp;${corso.cognomeDocente }</button>
					</c:forEach>
				</div>
					<!-- Notifications -->
					<li class="dropdown"><a href="javascript:void(0);"
						class="dropdown-toggle" data-toggle="dropdown" role="button">
							<i class="material-icons">notifications</i><input class="hidden"
							id="notcnt" value="${notifications.size() }"> <span
							id="notifycount" class="label-count"></span>
					</a>
						<ul class="dropdown-menu">
							<li class="header">Notifiche</li>
							<li class="body">
								<ul class="menu">
									<c:if test="${empty notifications }">
										<div align="center" class="menu-info">
											<h4 align="center">Nessuna notifica</h4>
										</div>
									</c:if>
									<c:forEach var="notification" items="${notifications}">
										<li><a href="javascript:void(0);"> <c:choose>
													<c:when test="${notification.type==0}">
														<div class="icon-circle bg-unibook">
															<i class="material-icons">note_add</i>
														</div>
													</c:when>
													<c:when test="${notification.type==1}">
														<div class="icon-circle bg-unibook">
															<i class="material-icons">delete</i>
														</div>
													</c:when>
													<c:when test="${notification.type==2}">
														<div class="icon-circle bg-unibook">
															<i class="material-icons">delete</i>
														</div>
													</c:when>
													<c:when test="${notification.type==3}">
														<div class="icon-circle bg-unibook">
															<i class="material-icons">content_paste</i>
														</div>
													</c:when>
													<c:when test="${notification.type==4}">
														<div class="icon-circle bg-unibook">
															<i class="material-icons">delete_forever</i>
														</div>
													</c:when>
												</c:choose>
												<div class="menu-info">
													<h4>${notification.testo}</h4>
													<p>
														<i class="material-icons">access_time</i>
														${notification.datareale}
													</p>
												</div>
										</a></li>
									</c:forEach>
								</ul>
							</li>
							<li class="footer"><a href="javascript:void(0);">Vedi
									tutte le notifiche</a></li>
						</ul></li>
					<!-- #END# Notifications -->
					<!-- Tasks -->
					<li class="dropdown"><a href="page?request=messaggi"
						role="button"> <i class="material-icons">message</i> <span
							id="msgcount" class="label-count"></span>
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
							<li class="footer"><a href="javascript:void(0);">View
									All Tasks</a></li>
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
	<section>
		<!-- Left Sidebar -->
		<aside id="leftsidebar" class="sidebar">
			<!-- User Info -->
			<div class="user-info">
				<div class="image">
					<div class="profile-pic-s"
						style="background-image: url('${currentUser.profileImagePath}')">
					</div>
				</div>


				<div class="info-container">
					<div class="name" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">${currentUser.nome}
						${currentUser.cognome} &nbsp; - &nbsp; Matr.
						${currentUser.matricola}</div>
					<div class="email">${currentUser.email}</div>
					<div class="btn-group user-helper-dropdown">
						<i class="material-icons" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
						<ul class="dropdown-menu pull-right">
							<li><a
								href="page?request=profilo&id=${currentUser.matricola}"><i
									class="material-icons">person</i>Profilo</a></li>
							<li role="seperator" class="divider"></li>
							<li><a href="page?request=colleghi"><i
									class="material-icons">group</i>Colleghi</a></li>
							<li><a href="page?request=docenti"><i
									class="material-icons">school</i>Docenti</a></li>
							<li role="seperator" class="divider"></li>
							<li><a href="logout"><i class="material-icons">exit_to_app</i>Log
									Out </a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- #User Info -->
			<!-- Menu -->
			<div class="menu">
				<ul class="list">
					<li class="header">Navigazione principale</li>
					<li><a href="home"> <i class="material-icons">home</i> <span>Home</span>
					</a></li>
					<li><a href="page?request=corsi"> <i
							class="material-icons">library_books</i> <span>I miei
								Corsi</span>
					</a></li>
					<li><a href="calendarManager?request=Eventi"> <i
							class="material-icons">date_range</i> <span>Calendario
								personale</span>
					</a></li>
					<li><a href="page?request=aule"> <i class="material-icons">business</i>
							<span>Aule</span>
					</a></li>
					<c:if test="${currentUser.ruolo==0 }">
						<li><a href="page?request=esami"> <i
								class="material-icons">collections_bookmark</i> <span>Esami</span>
						</a></li>
					</c:if>
					<li><a href="map.jsp"> <i class="material-icons">my_location</i>
							<span>Dove siamo</span>
					</a></li>
					<li><a href="chiSiamo.jsp"> <i class="material-icons">info</i>
							<span>Chi siamo</span>
					</a></li>


					<li class="active"></li>
				</ul>
			</div>
			<input type="hidden" id="curUser" value="${ currentUser.matricola }" />
			<!-- #Menu -->
			<!-- Footer -->
			<div class="legal">Progetto realizzato per Ingegneria del
				Software / Web Computing a.a. 2017/2018</div>
			<!-- #Footer -->
		</aside>
		<!-- #END# Left Sidebar -->
		<!-- Right Sidebar -->
		<aside id="rightsidebar" class="right-sidebar">
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
		</aside>
		<!-- #END# Right Sidebar -->
	</section>
</body>

</html>