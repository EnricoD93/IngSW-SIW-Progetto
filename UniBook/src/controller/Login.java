package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Corso;
import model.Studente;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.StudenteDao;
import persistence.dao.UtenteDao;

public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("matricola", null); // ???? serve?
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		RequestDispatcher dispacher;
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		Utente currentUser;
		List<Corso> corsi;
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		Utente utente = utenteDao.findByPrimaryKey(username);
		try {
			if (utente != null) {
				if (password.equals(utenteDao.findByPrimaryKey(username).getPassword())) {
					currentUser = utenteDao.findByPrimaryKey(username);
					corsi = utenteDao.getCorsi(username);
					session.setAttribute("currentUser", currentUser);
					session.setAttribute("corsi", corsi);
					dispacher = req.getRequestDispatcher("home.jsp");
					dispacher.forward(req, resp);

				} else {
					dispacher = req.getRequestDispatcher("index.html");
					dispacher.forward(req, resp);
					// Password errata
				}
			} else {
				// Utente non esiste
				dispacher = req.getRequestDispatcher("index.html");
				dispacher.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			dispacher = req.getRequestDispatcher("index.html");
			dispacher.forward(req, resp);
		}

	}

}