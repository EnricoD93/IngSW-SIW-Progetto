<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<div class="container-fluid">
	<div class="block-header">
		<h2>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>ADVANCED FORM EXAMPLE WITH VALIDATION</h2>
						<ul class="header-dropdown m-r--5">
							<li class="dropdown"><a href="javascript:void(0);"
								class="dropdown-toggle" data-toggle="dropdown" role="button"
								aria-haspopup="true" aria-expanded="false"> <i
									class="material-icons">more_vert</i>
							</a>
								<ul class="dropdown-menu pull-right">
									<li><a href="javascript:void(0);"
										class=" waves-effect waves-block">Action</a></li>
									<li><a href="javascript:void(0);"
										class=" waves-effect waves-block">Another action</a></li>
									<li><a href="javascript:void(0);"
										class=" waves-effect waves-block">Something else here</a></li>
								</ul></li>
						</ul>
					</div>
					<div class="body">
						<form id="wizard_with_validation" method="POST" role="application"
							class="wizard clearfix" novalidate="novalidate">
							<div class="steps clearfix">
								<ul role="tablist">
									<li role="tab" class="first current" aria-disabled="false"
										aria-selected="true" style="width: 33.3333%;"><a
										id="wizard_with_validation-t-0"
										href="#wizard_with_validation-h-0"
										aria-controls="wizard_with_validation-p-0"><span
											class="current-info audible">current step: </span><span
											class="number">1.</span> Account Information</a></li>
									<li role="tab" class="disabled" aria-disabled="true"
										style="width: 33.3333%;"><a
										id="wizard_with_validation-t-1"
										href="#wizard_with_validation-h-1"
										aria-controls="wizard_with_validation-p-1"><span
											class="number">2.</span> Profile Information</a></li>
									<li role="tab" class="disabled last" aria-disabled="true"
										style="width: 33.3333%;"><a
										id="wizard_with_validation-t-2"
										href="#wizard_with_validation-h-2"
										aria-controls="wizard_with_validation-p-2"><span
											class="number">3.</span> Terms &amp; Conditions - Finish</a></li>
								</ul>
							</div>
							<div class="content clearfix">
								<h3 id="wizard_with_validation-h-0" tabindex="-1"
									class="title current">Account Information</h3>
								<fieldset id="wizard_with_validation-p-0" role="tabpanel"
									aria-labelledby="wizard_with_validation-h-0"
									class="body current" aria-hidden="false">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control" name="username"
												required="" aria-required="true"> <label
												class="form-label">Username*</label>
										</div>
									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<input type="password" class="form-control" name="password"
												id="password" required="" aria-required="true"> <label
												class="form-label">Password*</label>
										</div>
									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<input type="password" class="form-control" name="confirm"
												required="" aria-required="true"> <label
												class="form-label">Confirm Password*</label>
										</div>
									</div>
								</fieldset>

								<h3 id="wizard_with_validation-h-1" tabindex="-1" class="title">Profile
									Information</h3>
								<fieldset id="wizard_with_validation-p-1" role="tabpanel"
									aria-labelledby="wizard_with_validation-h-1" class="body"
									aria-hidden="true" style="display: none;">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" name="name" class="form-control"
												required="" aria-required="true"> <label
												class="form-label">First Name*</label>
										</div>
									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" name="surname" class="form-control"
												required="" aria-required="true"> <label
												class="form-label">Last Name*</label>
										</div>
									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<input type="email" name="email" class="form-control"
												required="" aria-required="true"> <label
												class="form-label">Email*</label>
										</div>
									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<textarea name="address" cols="30" rows="3"
												class="form-control no-resize" required=""
												aria-required="true"></textarea>
											<label class="form-label">Address*</label>
										</div>
									</div>
									<div class="form-group form-float">
										<div class="form-line">
											<input min="18" type="number" name="age" class="form-control"
												required="" aria-required="true"> <label
												class="form-label">Age*</label>
										</div>
										<div class="help-info">The warning step will show up if
											age is less than 18</div>
									</div>
								</fieldset>

								<h3 id="wizard_with_validation-h-2" tabindex="-1" class="title">Terms
									&amp; Conditions - Finish</h3>
								<fieldset id="wizard_with_validation-p-2" role="tabpanel"
									aria-labelledby="wizard_with_validation-h-2" class="body"
									aria-hidden="true" style="display: none;">
									<input id="acceptTerms-2" name="acceptTerms" type="checkbox"
										required="" aria-required="true"> <label
										for="acceptTerms-2">I agree with the Terms and
										Conditions.</label>
								</fieldset>
							</div>
							<div class="actions clearfix">
								<ul role="menu" aria-label="Pagination">
									<li class="disabled" aria-disabled="true"><a
										href="#previous" role="menuitem">Previous</a></li>
									<li aria-hidden="false" aria-disabled="false"><a
										href="#next" role="menuitem" class="waves-effect">Next</a></li>
									<li aria-hidden="true" style="display: none;"><a
										href="#finish" role="menuitem" class="waves-effect">Finish</a></li>
								</ul>
							</div>
						</form>
					</div>
				</div>
			</div>
		</h2>
	</div>
</div>

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

</html>