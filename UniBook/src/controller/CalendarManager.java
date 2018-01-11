package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GiornoCalendario;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.CalendarioPersonaleDao;

public class CalendarManager extends HttpServlet {

	String request;
	List<GiornoCalendario> listaEventi;
	Utente currentUser;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		request = req.getParameter("request");
		currentUser= (Utente) session.getAttribute("currentUser");
		
		if(request.equals("Eventi")) {
			CalendarioPersonaleDao calendarioPersonaleDao= DatabaseManager.getInstance().getDaoFactory().getCalendarioPersonaleDAO();
			listaEventi=calendarioPersonaleDao.findByPrimaryKey(currentUser.getMatricola()).getEventi();
		}

	}
}
