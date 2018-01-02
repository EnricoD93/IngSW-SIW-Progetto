package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

public class EmailVerify extends HttpServlet {
	private String host;
	private String port;
	private String email;
	private String password;
	private String code;
	static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random(System.currentTimeMillis());
	static private final int LENGHT = 8;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String dest = req.getParameter("email");
		final String nome = req.getParameter("username");
		final String subject = "Codice di verifica";
		final String text = "<html>\r\n" + "<head>\r\n" + "UniBook\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "<td bgcolor=\"#C4161C\">" + "<img src=\"cid:image\">\r\n"
				+ "<p> <font size =\"5px\" color=\"white\" face=\"Robot,Arial,Tahoma,sans-serif\"> Ciao, <b>" + nome
				+ "</b>" + "</font></p>\r\n"
				+ "<p> <font size =\"4px\" color=\"white\" face=\"Robot,Arial,Tahoma,sans-serif\"> La tua registrazione è quasi completata. </p>\r\n"
				+ "<p> <font size =\"4px\" color=\"white\" face=\"Robot,Arial,Tahoma,sans-serif\"> Per verificare la tua email inserisci il codice sottostante.</p>\r\n"
				+ "<p> <font size =\"4px\" color=\"white\" face=\"Robot,Arial,Tahoma,sans-serif\"> Il tuo codice di verifica è <b>"
				+ code + "</b>" + " </font></p>\r\n"
				+ "<p> <font size =\"4px\" color=\"white\" face=\"Robot,Arial,Tahoma,sans-serif\"> Cordiali saluti <b> UniBook</b></font>\r\n"
				+ "</div></body>\r\n" + "</html>";

		String resultMessage = "";
		HttpSession session=req.getSession();
		session.setAttribute("code", code);
		try {
			EmailSender.sendEmail(host, port, email, password, dest, subject, text);
			resultMessage = "The e-mail was sent successfully";
		} catch (final Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			req.setAttribute("Message", resultMessage);
			req.getRequestDispatcher("emailVerification.html").forward(req, resp);
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
		code = generateCode();
	}

	public String generateCode() {
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
		}
		return sb.toString();
	}
}
