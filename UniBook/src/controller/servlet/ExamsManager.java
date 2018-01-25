package controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.Esame;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.EsameDao;
import persistence.dao.UtenteDao;

public class ExamsManager extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String request = req.getParameter("request");
		Utente currentUser = (Utente) req.getSession().getAttribute("currentUser");
		EsameDao esameDao = DatabaseManager.getInstance().getDaoFactory().getEsameDAO();
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		System.out.println(request);
		if (request.equals("rimuoviEsame")) {
			Long corso = Long.parseLong(req.getParameter("corso"));
			Esame e = esameDao.findByPrimaryKey(corso);
			utenteDao.deleteExam(currentUser.getMatricola(),e);
			System.out.println("eliminato");
		}
		else if(request.equals("aggiungiEsame")) {
		
		}

	}
}
