package controller;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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
import persistence.IdBroker;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.EventoDao;

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
		if (request.equals("creaEvento")) {
			String start = req.getParameter("start");
			String end = req.getParameter("end");
			System.out.println(start);
			System.out.println(end);
			Timestamp startT = null;
			Timestamp endT = null;
			try {
				startT = parseDate(start);
				endT = parseDate(end);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// trasformare le stringhe in Timestamp e passarle all'evento
			String title = req.getParameter("title");
			System.out.println(startT);
			System.out.println(endT);
			Evento e = new Evento(title, startT, endT, "nessuna");
			EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
			System.out.println("salvo il nuovo evento " + title);
			eventoDao.save(e);
			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();
			calendarioPersonaleDao.saveEvent(matricola, e);

		}

	}

	private Timestamp parseDate(String str_date) throws ParseException {

		Long secs = Long.parseLong(str_date);
		java.sql.Timestamp timeStampDate = new Timestamp(secs);
		return timeStampDate;

	}
}
