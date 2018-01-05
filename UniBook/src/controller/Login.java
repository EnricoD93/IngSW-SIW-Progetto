package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Aula;
import model.Corso;
import model.DescrizioneCorso;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.AulaDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.UtenteDao;

public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("matricola", null); // ???? serve?
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		RequestDispatcher dispacher;
		List<Corso> corsi;
		List<Corso> corsiDocente;
		List<Corso> corsiIscritto;
		List<Aula> aule;
		List<DescrizioneCorso> listaCorsi;
		
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		Utente currentUser = utenteDao.findByPrimaryKey(username);
		AulaDao aulaDao=DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
		DescrizioneCorsoDao descrizioneCorsoDao= DatabaseManager.getInstance().getDaoFactory().getDescrizioneCorsoDao();
		listaCorsi=descrizioneCorsoDao.findAll();
		System.out.println(listaCorsi.size());
		aule=aulaDao.findAll();
		try {
			if (currentUser != null) {
				if (password.equals(utenteDao.findByPrimaryKey(username).getPassword())) {
					currentUser = utenteDao.findByPrimaryKey(username);
					corsi = utenteDao.getCorsi(username);
					corsiDocente = utenteDao.getCorsiDocente(username);
					corsiIscritto = utenteDao.getCorsiIscritto(username);
					session.setAttribute("currentUser", currentUser);
					session.setAttribute("corsi", corsi);
					session.setAttribute("corsiDocente", corsiDocente);
					session.setAttribute("corsiIscritto", corsiIscritto);
					session.setAttribute("aule", aule);
					session.setAttribute("listaCorsi", listaCorsi);
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