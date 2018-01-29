package controller.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import controller.util.EmailSender;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class ProfileManager extends HttpServlet {
	private String host;
	private String port;
	private String email;
	private String password;
	private String code;
	static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random(System.currentTimeMillis());
	static private final int LENGHT = 6;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utente currentUser = (Utente) req.getSession().getAttribute("currentUser");
		String request = req.getParameter("request");
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		code = generateCode();
		if (request.equals("modificaPassword")) {
			String inputPassword = req.getParameter("inputPassword");
			utenteDao.passwordModify(currentUser.getMatricola(), inputPassword);
		} else if(request.equals("modificaEmail")) {
			String inputEmail = req.getParameter("inputEmail");
			utenteDao.emailModify(currentUser.getMatricola(), inputEmail);
		}else if(request.equals("modificaDescrizione")) {
			String inputDescrizione = req.getParameter("inputDescrizione");
			utenteDao.descrizioneModify(currentUser.getMatricola(), inputDescrizione);
			JSONObject json= new JSONObject();
			try {
				json.put("user", currentUser.getMatricola());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.setContentType("application/json");
			resp.getWriter().print(json);
		} if (request.equals("codiceVerificaEmail")) {
			String inputEmail = req.getParameter("inputEmail");
		sendVerificationCode(req,resp,inputEmail,currentUser.getNome(),currentUser.getMatricola());
		utenteDao.setVerifyCode(currentUser.getMatricola(), code);
		JSONObject json= new JSONObject();
		try {
			json.put("jsonCode", code);
			json.put("user", currentUser.getMatricola());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("application/json");
		resp.getWriter().print(json);
		}
	}

	private void sendVerificationCode(HttpServletRequest req, HttpServletResponse resp, String dest, String nome,
			String matricola) throws ServletException, IOException {
		final String subject = "Codice di verifica";
		final String text = "<html>\r\n" + "<head>\r\n" + "UniBook\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "<td bgcolor=\"#C4161C\">" + "<img src=\"cid:image\">\r\n"
				+ "<p> <font size =\"5px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> Ciao, <b>" + nome
				+ "</b>" + "</font></p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> La modifica dell'email è quasi completata. </p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> Per verificare la tua email inserisci il codice sottostante.</p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> Il tuo codice di verifica è <b>"
				+ code + "</b>" + " </font></p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> Cordiali saluti <b> UniBook</b></font>\r\n"
				+ "</div></body>\r\n" + "</html>";

		String resultMessage = "";
		HttpSession session = req.getSession();
		session.setAttribute("userMatr", matricola);
		session.setAttribute("dbcode", code);
		try {
			EmailSender.sendEmail(host, port, email, password, dest, subject, text);
			resultMessage = "The e-mail was sent successfully";
		} catch (final Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} 

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

	public String generateCode() {

		StringBuilder sb = new StringBuilder(LENGHT);
		for (int i = 0; i < LENGHT; i++) {
			sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
		}
		return sb.toString();
	}
}
