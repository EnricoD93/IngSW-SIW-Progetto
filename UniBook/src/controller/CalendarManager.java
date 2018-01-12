package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Evento;
import model.GiornoCalendario;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.CalendarioPersonaleDao;

public class CalendarManager extends HttpServlet {

	String request;
	String matricola;
	List<Evento> listaEventi;
	Utente currentUser;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		JSONArray listaJson = new JSONArray();
		HttpSession session = req.getSession();
		request = req.getParameter("request");
		matricola = req.getParameter("matricola");
		if (request.equals("Eventi")) {
			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();
			
			
			listaEventi = calendarioPersonaleDao.findAllEventsUtente(matricola);
			
			
	
			for (int i = 0; i < listaEventi.size(); i++) {
				JSONObject evento = new JSONObject();

				try {
					evento.put("title", listaEventi.get(i).getTitle());
					Date date = new Date(listaEventi.get(i).getInizio().getTime());
					Calendar cal = new GregorianCalendar();
					cal.setTime(date);
					evento.put("annoIn", cal.get(Calendar.YEAR));
					evento.put("meseIn", cal.get(Calendar.MONTH));
					evento.put("giornoIn", cal.get(Calendar.DAY_OF_MONTH));
					 date = new Date(listaEventi.get(i).getFine().getTime());
					 cal = new GregorianCalendar();
					cal.setTime(date);
					evento.put("annoFi", cal.get(Calendar.YEAR));
					evento.put("meseFi", cal.get(Calendar.MONTH));
					evento.put("giornoFi", cal.get(Calendar.DAY_OF_MONTH));
					
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listaJson.put(evento);
			}
			try {
				result.put("result", listaJson);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.setContentType("application/json");
			resp.getWriter().print(result);
		}

	}
}
