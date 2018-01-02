package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Docente;
import model.Studente;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class Register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("username");
		String cognome = req.getParameter("userlastname");
		String matricola = req.getParameter("usermatr");
		String email = req.getParameter("email");
		String dataNascita = req.getParameter("dataNascita");
		String codicef = req.getParameter("codf");
		String password = req.getParameter("password");
		String ruolo = req.getParameter("role");
		Long cdl = Long.parseLong(req.getParameter("cdl"));
		ServletOutputStream out = resp.getOutputStream();
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ITALIAN);
		Date date;
		try {
			switch (ruolo) {
			case "1":
				System.out.println("docente");
				date = format.parse(dataNascita);

				Utente doc = new Utente(matricola, nome, cognome, date, codicef, email, password, cdl,
						Integer.parseInt(ruolo));

				UtenteDao docenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
				docenteDao.save(doc);
				break;

			case "0":
				System.out.println("studente");
				date = format.parse(dataNascita);

				Utente stud = new Utente(matricola, nome, cognome, date, codicef, email, password, cdl,
						Integer.parseInt(ruolo));

				UtenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
				studenteDao.save(stud);
				break;
			default:
				break;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		req.getRequestDispatcher("index.html").forward(req, resp);
		
	}
}
