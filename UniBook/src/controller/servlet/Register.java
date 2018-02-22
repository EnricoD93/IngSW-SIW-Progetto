package controller.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.EmailSender;
import model.user.CalendarioPersonale;
import model.user.GiornoCalendario;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.UtenteDao;

public class Register extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		String nome = req.getParameter("username");
		String cognome = req.getParameter("userlastname");
		String matricola = req.getParameter("usermatr");
		String mailto = req.getParameter("email");
		String dataNascita = req.getParameter("dataNascita");
		String codicef = req.getParameter("codf");
		String password = req.getParameter("password");
		String ruolo = req.getParameter("role");
		code = generateCode();
		String cdl = req.getParameter("cdl");

		GiornoCalendario giornoCalendario = new GiornoCalendario();

		// Date date = new Date(giornoCalendario.parse(dataNascita));
		Date date = giornoCalendario.parseToDate(dataNascita);
		giornoCalendario.parseToGiornoCalendario(date);
		giornoCalendario.stampa();
		
		switch (ruolo) {
		case "1":

			Utente doc = new Utente(matricola, nome, cognome, date, codicef, mailto, password, cdl,
					Integer.parseInt(ruolo), code,"ciao");

			UtenteDao docenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			docenteDao.save(doc);
			CalendarioPersonale calendarioPersonale = new CalendarioPersonale(doc.getMatricola());
			CalendarioPersonaleDao cdao=DatabaseManager.getInstance().getDaoFactory().getCalendarioPersonaleDAO();
			cdao.save(calendarioPersonale);
			break;
		case "0":

			Utente stud = new Utente(matricola, nome, cognome, date, codicef, mailto, password, cdl,
					Integer.parseInt(ruolo), code,"ciao");

			UtenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			studenteDao.save(stud);
			CalendarioPersonale calendarioPersonale2 = new CalendarioPersonale(stud.getMatricola());
			CalendarioPersonaleDao cdao2=DatabaseManager.getInstance().getDaoFactory().getCalendarioPersonaleDAO();
			cdao2.save(calendarioPersonale2);
			break;
		default:
			break;
		}
		sendVerificationCode(req, resp, mailto, nome, matricola);

	}

	private void sendVerificationCode(HttpServletRequest req, HttpServletResponse resp, String dest, String nome,
			String matricola) throws ServletException, IOException {
		final String subject = "Codice di verifica";
		final String text = "<html>\r\n" + "<head>\r\n" + "UniBook\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "<td bgcolor=\"#C4161C\">" + "<img src=\"cid:image\">\r\n"
				+ "<p> <font size =\"5px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> Ciao, <b>" + nome
				+ "</b>" + "</font></p>\r\n"
				+ "<p> <font size =\"4px\" color=\"black\" face=\"Robot,Arial,Tahoma,sans-serif\"> La tua registrazione è quasi completata. </p>\r\n"
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
		} finally {
			req.setAttribute("Message", resultMessage);
			req.getRequestDispatcher("emailVerify.jsp").forward(req, resp);
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
