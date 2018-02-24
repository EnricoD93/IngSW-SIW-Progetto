<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no"
	name="viewport">
<title>Registrazione</title>
<link rel="icon" href="favicon.ico" type="image/x-icon">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" type="text/css">
<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="css/mysweetalert.css" rel="stylesheet">
<link href="plugins/node-waves/waves.css" rel="stylesheet" />

<link href="plugins/animate-css/animate.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet">
</head>

<body id="body" class="signup-page">
	<div class="signup-box">
		<div class="logo">
			<a href="index.html"><img src="logo.png" width="200" height="70"></a>
			<small>Piattaforma di comunicazione Docente-Studente</small>
		</div>
		<div class="card">
			<div class="body">
				<div align="center">
					<b>Registrazione quasi completa!</b>
				</div>
				<br> Controlla la tua posta e inserisci il codice di verifica<br>


				<div class="myinline input-group form-group">
					<span class="myinline input-group-addon"> <i
						class="material-icons">lock</i>
					</span>
					<div class="form-line">

						<input id="verify" type="text" class="form-control"
							name="verifyCode" minlength="6" placeholder="Codice di verifica"
							required>

					</div>
				</div>
				<div style="display:flex;">
					<input name="email" id="email" cols="30" rows="5"
						class="form-control no-resize hidden" value="" required><label
						class="form-label"></label>&nbsp;

					<button id="modificaEmailConfirm" type="button"
						class="btn btn-circle-lg-xs bg-unibook waves-effect hidden"
						style="margin-top: 4px;" onclick="javascript:confermaModificaEmail();">
						<i class="material-icons">check</i>
					</button>
				</div><br>
				<div class="row">
					<button id="verifyButton"
						onclick="javascript:registration( '${dbcode}');"
						class="btn btn-lg bg-unibook waves-effect" type="submit">Verifica</button>
					<button class="btn btn-lg bg-unibook waves-effect" type="submit"
						onclick="javascript:invia();">Invia</button>

					<button class="btn btn-lg bg-unibook waves-effect" type="button"
						onclick="javascript:modificaEmail();">Cambia email</button>
				</div>

			</div>
		</div>
	</div>

	<div class="sweet-overlay" tabindex="-1"
		style="opacity: -0.05; display: none;"></div>

	<div class="sweet-alert hideSweetAlert" data-custom-class=""
		data-has-cancel-button="false" data-has-confirm-button="true"
		data-allow-outside-click="false" data-has-done-function="false"
		data-animation="pop" data-timer="null"
		style="display: none; margin-top: -149px; opacity: 0;">
		<div class="sa-icon sa-success" style="display: block;">
			<span class="sa-line sa-tip"></span> <span class="sa-line sa-long"></span>

			<div class="sa-placeholder"></div>
			<div class="sa-fix"></div>
		</div>
		<h2>Registrazione completata!</h2>
		<p style="display: block;">Ora accedi per continuare.</p>
		<fieldset>
			<input type="text" tabindex="3" placeholder="">
			<div class="sa-input-error"></div>
		</fieldset>
		<div class="sa-error-container">
			<div class="icon">!</div>
			<p>Not valid!</p>
		</div>
		<div class="sa-button-container">
			<button class="cancel" tabindex="2" style="display: none;">Cancel</button>
			<div class="sa-confirm-button-container">
				<button id="confirmButtonOk"
					class="confirm btn btn-lg bg-red waves-effect" tabindex="1"
					style="display: inline-block; background-color: rgb(140, 212, 245); box-shadow: rgba(140, 212, 245, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px inset;">OK</button>
				<div class="la-ball-fall">
					<div></div>
					<div></div>
					<div></div>
				</div>
			</div>
		</div>
	</div>
	<script src="plugins/jquery/jquery.min.js"></script>
	<script src="javascript/confirmRegistration.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="js/pages/ui/dialogs.js"></script>
	<script src="plugins/bootstrap/js/bootstrap.js"></script>
	<script src="plugins/node-waves/waves.js"></script>
	<script src="plugins/jquery-validation/jquery.validate.js"></script>
	<script src="js/pages/examples/sign-up.js"></script>
</body>
</html>