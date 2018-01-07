package controller;

import java.io.IOException;
import java.util.List;

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

public class ShowCourse extends HttpServlet {
	Corso currentCourse;
	Utente currentStudent;
	Utente courseDocente;
	List<Utente> studentiIscritti;
	String richiesta = "vuota";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		richiesta = req.getParameter("richiesta");
		Long codice = Long.parseLong(req.getParameter("codice"));
		CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		currentCourse = corsoDao.findByPrimaryKey(codice);
		courseDocente=utenteDao.findByPrimaryKey(currentCourse.getDocente());
		if (richiesta.equals("mostraCorso")) {
			studentiIscritti = corsoDao.getStudentiIscritti(codice);
			session.setAttribute("courseDocente", courseDocente);
			session.setAttribute("currentCourse", currentCourse);
			session.setAttribute("currentStudent", currentStudent);
			session.setAttribute("studentiIscritti", studentiIscritti);
		}
		if (richiesta.equals("eliminaIscrizioneStudente")) {
			String matricolaStudente = req.getParameter("matricolaStudente");
			utenteDao.eliminaIscrizioneStudente(matricolaStudente, currentCourse.getCodice());
		}

	}
}
