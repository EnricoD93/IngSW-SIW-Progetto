package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmailVerify extends HttpServlet {
	private String host;
	private String port;
	private String email;
	private String password;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String dest = req.getParameter("email");
		final String nome = req.getParameter("username");
		final String subject = "Codice di verifica";
		final String text = "Ciao " + nome + " " + ",\n\nIl tuo codice di verifica è: 11111";
		
		String resultMessage = "";

		try {
			EmailSender.sendEmail(host, port, email, password, dest, subject, text);
			resultMessage = "The e-mail was sent successfully";
		} catch (final Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			req.setAttribute("Message", resultMessage);
			req.getRequestDispatcher("index.html").forward(req, resp);
		}
		System.out.println(resultMessage);
	}
	
	
	
}
