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
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.CorsoDao;
import persistence.dao.UtenteDao;

public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9139149609635447743L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		RequestDispatcher dispacher;
		List<Corso> corsi;

		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
		Utente currentUser = utenteDao.findByPrimaryKey(username);
		try {
			if (currentUser != null) {
				if (password.equals(utenteDao.findByPrimaryKey(username).getPassword())) {
					currentUser = utenteDao.findByPrimaryKey(username);
					corsi = utenteDao.getCorsi(currentUser.getMatricola());
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