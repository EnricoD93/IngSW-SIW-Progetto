
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<div class="container-fluid">
	<div class="block-header">
		<h2>
			<div class="row clearfix">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="card">
						<div class="header">
							<h2>Crea un Corso</h2>
						</div>
						<div class="body">
							<form id="form_validation" method="POST" novalidate="novalidate">
								<div class="input-group">
									<span class="input-group-addon"> Seleziona il Corso da
										creare </span>
									<div class="btn-group bootstrap-select show-tick">
										<div class="dropdown-menu open"
											style="max-height: 410px; overflow: hidden; min-height: 92px;">
											<ul class="dropdown-menu inner" role="menu"
												style="max-height: 400px; overflow-y: auto; min-height: 82px;">
												<c:forEach var="corso" items="${listaCorsi}">
													<li data-original-index="1"><a tabindex="0" class=""
														style="" data-tokens="null"><span class="text">${corso.codice}
													&nbsp; - &nbsp; ${corso.nome}</span>
													<span
															class="glyphicon glyphicon-ok check-mark"></span></a></li>
												</c:forEach>
												
											</ul>
										</div>
										<select class="form-control show-tick" tabindex="-98"
											name="role">
											<c:forEach var="corso" items="${listaCorsi}">
												<option value="${corso.codice}">${corso.codice}
													&nbsp; - &nbsp; ${corso.nome}</option>
								</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<input type="text" class="form-control" name="name"
											required="" aria-required="true"> <label
											class="form-label">Name</label>
									</div>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<input type="text" class="form-control" name="surname"
											required="" aria-required="true"> <label
											class="form-label">Surname</label>
									</div>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<input type="email" class="form-control" name="email"
											required="" aria-required="true"> <label
											class="form-label">Email</label>
									</div>
								</div>
								<div class="form-group">
									<input type="radio" name="gender" id="male" class="with-gap">
									<label for="male">Male</label> <input type="radio"
										name="gender" id="female" class="with-gap"> <label
										for="female" class="m-l-20">Female</label>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<textarea name="description" cols="30" rows="5"
											class="form-control no-resize" required=""
											aria-required="true"></textarea>
										<label class="form-label">Description</label>
									</div>
								</div>
								<div class="form-group form-float">
									<div class="form-line">
										<input type="password" class="form-control" name="password"
											required="" aria-required="true"> <label
											class="form-label">Password</label>
									</div>
								</div>
								<div class="form-group">
									<input type="checkbox" id="checkbox" name="checkbox"> <label
										for="checkbox">I have read and accept the terms</label>
								</div>
								<button class="btn btn-primary waves-effect" type="submit">SUBMIT</button>
							</form>
						</div>
					</div>
				</div>
			</div>

		</h2>
	</div>
</div>

</html>