package controller.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		Long corso = Long.parseLong(req.getParameter("corso"));
		Esame e = esameDao.findByPrimaryKey(corso);
		if (request.equals("rimuoviEsame")) {
			utenteDao.deleteExam(currentUser.getMatricola(), e);
			System.out.println("eliminato");
		} else if (request.equals("aggiungiEsame")) {
			String data = req.getParameter("data");
			String votoS = req.getParameter("voto");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate;
			try {
				parsedDate = dateFormat.parse(data);
				Timestamp timestamp = new Timestamp(parsedDate.getTime());
			int voto;
			if (votoS.equals("30L"))
				voto = 31;
			else
				voto = Integer.parseInt(votoS);
			utenteDao.superaEsame(currentUser.getMatricola(), e, timestamp, voto);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}

	}
}
