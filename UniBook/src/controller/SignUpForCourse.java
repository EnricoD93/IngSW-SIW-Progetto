package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class SignUpForCourse extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String richiesta = req.getParameter("richiesta");
		UtenteDao udao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		Long codice = Long.parseLong(req.getParameter("codice"));
		Utente u = (Utente) session.getAttribute("currentUser");
		String typedPassword = req.getParameter("typedPassword");
		String matricola = req.getParameter("matricola");
		System.out.println("matricola");
		System.out.println(typedPassword);
		if (u.getPassword().equals(typedPassword)) {
			if (richiesta.equals("iscrizione")) {
				if (udao.iscritto(matricola, codice)) {
					resp.setStatus(405);
					return;
				} else {
					udao.iscriviStudente(matricola, codice);
				}
			}
			if (richiesta.equals("cancellazione")) {
				udao.eliminaIscrizioneStudente(matricola, codice);
				req.getRequestDispatcher("home.jsp").forward(req, resp);
			}
		} else {
			resp.setStatus(401);
			System.out.println("password errata");
		}
	}
}
