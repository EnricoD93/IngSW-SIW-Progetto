package controller.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.EmailSender;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class ForgotPassword extends HttpServlet {
	
	private String host;
	private String port;
	private String email;
	private String password;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email=req.getParameter("email");
		System.out.println(email);
		UtenteDao udao=DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		Utente u=udao.findUtenteByEmail(email);
		sendPassword(req, resp, email, u);
		
		
	}
	private void sendPassword(HttpServletRequest req, HttpServletResponse resp,String dest,Utente utente)
			throws ServletException, IOException {
		final String subject = "Recupero Credenziali";
		final String text = "<html>\r\n" + "<head>\r\n" + "UniBook\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "<td bgcolor=\"#C4161C\">" + "<img src=\"cid:image\">\r\n"
				+ "<p> <font size =\"5px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> Ciao, <b>" + utente.getNome()
				+ "</b>" + "</font></p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> Ecco le tue credenziali per accedere. </p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> La tua matricola è: "+utente.getMatricola()+"</p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> La tua password è: <b>"
				+  utente.getPassword()+ "</b>" + " </font></p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> Cordiali saluti <b> UniBook</b></font>\r\n"
				+ "</div></body>\r\n" + "</html>";

		String resultMessage = "";
		try {
			EmailSender.sendEmail(host, port, email, password, dest, subject, text);
			resultMessage = "The e-mail was sent successfully";
		} catch (final Exception ex) {
			ex.printStackTrace();
			resultMessage = "There was an error: " + ex.getMessage();
		} finally {
			req.setAttribute("Message", resultMessage);
			req.getRequestDispatcher("index.html").forward(req, resp);
		}
		System.out.println(resultMessage);
	}
	
	
	@Override
	public void init() {
		// reads SMTP server setting from web.xml file
		final ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		email = context.getInitParameter("mail");
		password = context.getInitParameter("password");
	}
}
