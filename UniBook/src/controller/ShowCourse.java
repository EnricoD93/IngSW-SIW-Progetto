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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Long codice = Long.parseLong(req.getParameter("codice"));
		
		Corso currentCourse;
		String imagePathDocente;
		List<Utente> studentiIscritti;
		
		CorsoDao corsoDao= DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
		UtenteDao utenteDao=DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		
		currentCourse=corsoDao.findByPrimaryKey(codice);
		imagePathDocente= utenteDao.findByPrimaryKey(currentCourse.getDocente()).getProfileImagePath();
		studentiIscritti=corsoDao.getStudentiIscritti(codice);
		
		session.setAttribute("currentCourse", currentCourse);
		session.setAttribute("studentiIscritti", studentiIscritti);
		session.setAttribute("imagePathDocente", imagePathDocente );
		
		
		
	}
}
