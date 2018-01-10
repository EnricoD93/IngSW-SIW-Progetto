package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Aula;
import model.Corso;
import model.CorsoDiLaurea;
import model.DescrizioneCorso;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.AulaDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.UtenteDao;

public class ChangePage extends HttpServlet {
	String request;
	List<Aula> aule;
	List<Corso> corsi;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Utente currentUser = (Utente) session.getAttribute("currentUser");
		request = req.getParameter("request");
		if (request.equals("aule")) {
			AulaDao aulaDao = DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
			aule = aulaDao.findAll();
			req.setAttribute("aule", aule);
			req.getRequestDispatcher("aule.jsp").forward(req, resp);
		}
		if (request.equals("calendario")) {
			req.getRequestDispatcher("calendarioPersonale.jsp").forward(req, resp);
		}
		if (request.equals("corsi")) {
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			if (currentUser.getRuolo() == 0) {
				corsi = utenteDao.getCorsiIscritto(currentUser.getMatricola());
			} else if (currentUser.getRuolo() == 1) {
				corsi = utenteDao.getCorsiDocente(currentUser.getMatricola());
			}
			req.setAttribute("corsi", corsi);
			req.getRequestDispatcher("corsi.jsp").forward(req, resp);
		}
		if (request.equals("aule")) {
			AulaDao aulaDao = DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
			aule = aulaDao.findAll();
			req.setAttribute("aule", aule);
			req.getRequestDispatcher("aule.jsp").forward(req, resp);
		}
		if (request.equals("profilo")) {
			String profileId = req.getParameter("id");
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			Utente utente = utenteDao.findByPrimaryKey(profileId);
			req.setAttribute("profilo", utente);
			req.getRequestDispatcher("profilo.jsp").forward(req, resp);
		}
		if (request.equals("corso")) {
			System.out.println(req.getParameter("id"));
			Long codice = Long.parseLong(req.getParameter("id"));
			CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			Corso currentCourse = corsoDao.findByPrimaryKey(codice);
			Utente courseDocente = utenteDao.findByPrimaryKey(currentCourse.getDocente());
			req.setAttribute("courseDocente", courseDocente);
			req.setAttribute("currentCourse", currentCourse);
			System.out.println("cambio pagina");
			req.getRequestDispatcher("course.jsp").forward(req, resp);
		}
		if (request.equals("listaStudenti")) {
			Long codice = Long.parseLong(req.getParameter("id"));
			CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
			List<Utente> studentiIscritti = corsoDao.getStudentiIscritti(codice);
			req.setAttribute("studentiIscritti", studentiIscritti);
			req.getRequestDispatcher("studentiIscritti.jsp").forward(req, resp);
		}
		if (request.equals("creaCorso")) {
			List<DescrizioneCorso> listaCorsi;
			List<Aula> listaAule;
			AulaDao aulaDao = DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
			DescrizioneCorsoDao descrizioneDao = DatabaseManager.getInstance().getDaoFactory().getDescrizioneCorsoDao();
			listaAule = aulaDao.findAll();
			listaCorsi = descrizioneDao.findAll();
			req.setAttribute("listaCorsi", listaCorsi);
			req.setAttribute("listaAule", listaAule);
			req.getRequestDispatcher("createCourse.jsp").forward(req, resp);
		}
		if(request.equals("colleghi")) {
				UtenteDao utenteDao=DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
				List<Utente> colleghi;
				colleghi=utenteDao.findColleaguesByCorsoDiLaurea(currentUser);
				req.setAttribute("colleghi", colleghi);
				req.getRequestDispatcher("colleghi.jsp").forward(req, resp);
		}
		if(request.equals("docenti")) {
			UtenteDao utenteDao=DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			List<Utente> docenti;
			docenti=utenteDao.findAllProfessor();
			req.setAttribute("docenti", docenti);
			req.getRequestDispatcher("docenti.jsp").forward(req, resp);
		}
	}

}
