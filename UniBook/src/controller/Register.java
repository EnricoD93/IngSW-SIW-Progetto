package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("username");
		String cognome = req.getParameter("userlastname");
		String matricola = req.getParameter("usermatr");
		String email = req.getParameter("email");
		String codicef = req.getParameter("codf");
		String password = req.getParameter("password");
		String ruolo= req.getParameter("role");
		ServletOutputStream out=resp.getOutputStream();
		out.println("<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<meta\r\n" + 
				"	content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\"\r\n" + 
				"	name=\"viewport\">\r\n" + 
				"<title>Registrazione</title>\r\n" + 
				"<!-- Favicon-->\r\n" + 
				"<link rel=\"icon\" href=\"favicon.ico\" type=\"image/x-icon\">\r\n" + 
				"\r\n" + 
				"<!-- Google Fonts -->\r\n" + 
				"<link\r\n" + 
				"	href=\"https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext\"\r\n" + 
				"	rel=\"stylesheet\" type=\"text/css\">\r\n" + 
				"<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\"\r\n" + 
				"	rel=\"stylesheet\" type=\"text/css\">\r\n" + 
				"\r\n" + 
				"<!-- Bootstrap Core Css -->\r\n" + 
				"<link href=\"plugins/bootstrap/css/bootstrap.css\" rel=\"stylesheet\">\r\n" + 
				"\r\n" + 
				"<!-- Waves Effect Css -->\r\n" + 
				"<link href=\"plugins/node-waves/waves.css\" rel=\"stylesheet\" />\r\n" + 
				"\r\n" + 
				"<!-- Animation Css -->\r\n" + 
				"<link href=\"plugins/animate-css/animate.css\" rel=\"stylesheet\" />\r\n" + 
				"\r\n" + 
				"<!-- Custom Css -->\r\n" + 
				"<link href=\"css/style.css\" rel=\"stylesheet\">\r\n" + 
				"</head>");
		out.println("<body class=\"signup-page\">\r\n" + 
				"	<div class=\"signup-box\">\r\n" + 
				"		<div class=\"logo\">\r\n" + 
				"			<a href=\"index.html\"><image src=\"logo.png\"></a> <small>Piattaforma\r\n" + 
				"				di comunicazione Docente-Studente</small>\r\n" + 
				"		</div>\r\n" + 
				"		<div class=\"card\">\r\n" + 
				"			<div class=\"body\">");
		out.println("Utente:"+nome + " " + cognome + " " + matricola+" "+email+" "+codicef+" "+password+" "+ruolo+"</br>");
		out.println("Registrato correttamente");
		out.println("	</div>	</div>\r\n" + 
				"	</div>\r\n" + 
				"\r\n" + 
				"	<!-- Jquery Core Js -->\r\n" + 
				"	<script src=\"plugins/jquery/jquery.min.js\"></script>\r\n" + 
				"\r\n" + 
				"	<!-- Bootstrap Core Js -->\r\n" + 
				"	<script src=\"plugins/bootstrap/js/bootstrap.js\"></script>\r\n" + 
				"\r\n" + 
				"	<!-- Waves Effect Plugin Js -->\r\n" + 
				"	<script src=\"plugins/node-waves/waves.js\"></script>\r\n" + 
				"\r\n" + 
				"	<!-- Validation Plugin Js -->\r\n" + 
				"	<script src=\"plugins/jquery-validation/jquery.validate.js\"></script>\r\n" + 
				"\r\n" + 
				"	<!-- Custom Js -->\r\n" + 
				"	<script src=\"js/admin.js\"></script>\r\n" + 
				"	<script src=\"js/pages/examples/sign-up.js\"></script>\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>");
		
	}
}
