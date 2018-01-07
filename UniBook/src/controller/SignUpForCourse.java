package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class SignUpForCourse extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String richiesta = req.getParameter("richiesta");
		UtenteDao udao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		Long codice = Long.parseLong(req.getParameter("codice"));
		String matricola = req.getParameter("matricola");
		if (richiesta.equals("iscrizione")) {
			if (udao.iscritto(matricola, codice)) {
				System.out.println(
						"l'utente con matricola " + matricola + "è già iscritto al corso con codice " + codice);
				resp.setStatus(400);
				return;
			} else
				udao.iscriviStudente(matricola, codice);
		}
		if (richiesta.equals("cancellazione")) {
			udao.eliminaIscrizioneStudente(matricola, codice);
		}

	}
}
