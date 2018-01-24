package controller.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
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

import model.course.Aula;
import model.course.Corso;
import model.course.Lezione;
import model.user.CalendarioPersonale;
import model.user.Evento;
import model.user.GiornoCalendario;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.EventoDao;
import persistence.dao.LezioneDao;
import persistence.dao.UtenteDao;

public class CalendarManager extends HttpServlet {

	String request;
	String matricola;
	List<Evento> listaEventi;
	List<Evento> listaSoloEventi;
	List<Lezione> listaSoloLezioni;
	Utente currentUser;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utente currentUser = (Utente) req.getSession().getAttribute("currentUser");
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		LezioneDao lezioneDao= DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
		List<Corso> corsi= utenteDao.getCorsiDocente(currentUser.getMatricola());
		AulaDao aula=DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
		List<Aula> aule=aula.findAll();
		req.setAttribute("corsi", corsi);
		req.setAttribute("aule", aule);
		EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		listaSoloEventi = eventoDao.findEvent();
		listaSoloLezioni= lezioneDao.findLezioni();
		System.out.println("listaSoloEventi"+ listaSoloEventi.size());
		req.setAttribute("listaSoloEventi", listaSoloEventi.size());
		req.setAttribute("listaSoloLezioni", listaSoloLezioni.size());
		req.getRequestDispatcher("calendarioPersonale.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		JSONArray listaJson = new JSONArray();
		HttpSession session = req.getSession();
		request = req.getParameter("request");
		matricola = req.getParameter("matricola");
		EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		if (request.equals("Eventi")) {
			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();

			listaEventi = calendarioPersonaleDao.findAllEventsUtente(matricola);
			listaSoloEventi = eventoDao.findEvent();
			LezioneDao lezioneDao= DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
			listaSoloLezioni= lezioneDao.findLezioni();
			req.setAttribute("listaSoloLezioni", listaSoloLezioni.size());
			System.out.println("listaSoloEventi"+ listaSoloEventi.size());
			req.setAttribute("listaSoloEventi", listaSoloEventi.size());
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
			Boolean lezione = Boolean.parseBoolean(req.getParameter("lezione"));
			Boolean evento = Boolean.parseBoolean(req.getParameter("evento"));
			Timestamp startT = null;
			Timestamp endT = null;
			String title = req.getParameter("title");

			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();
			try {
				startT = parseDate(start);
				endT = parseDate(end);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (lezione) {
				System.out.println(req.getParameter("corso"));
				Long corso = Long.parseLong(req.getParameter("corso"));
				String aula = req.getParameter("aula");

				Date data = new Date(startT.getTime());
				System.out.println("la data" + data.toString());
				System.out.println("devo creare lezione");
				LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
				GiornoCalendario g = new GiornoCalendario();
				g.parseToGiornoCalendario(data);
				Lezione l = new Lezione(corso, g, startT, endT, aula, 0);
				lezioneDao.save(l);
				// salvo la lezione nel calendario degli studenti
				CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
				List<Utente> studentiIscritti = corsoDao.getStudentiIscritti(corso);
				System.out.println("gli studenti iscritti sono :");

				Evento e = new Evento(l.getId(), title, startT, endT, "nessuna");
				System.out.println("salvo il nuovo evento " + title);
				eventoDao.save(e);

				calendarioPersonaleDao.saveEvent(matricola, e);

				for (int i = 0; i < studentiIscritti.size(); i++) {
					System.out.println(studentiIscritti.get(i).getNome());
					calendarioPersonaleDao.saveEvent(studentiIscritti.get(i).getMatricola(), e);
				}

			} else {
				Evento e = new Evento(title, startT, endT, "nessuna");
				eventoDao.save(e);
				calendarioPersonaleDao.saveEvent(matricola, e);
			}

		}
	}

	private Timestamp parseDate(String str_date) throws ParseException {

		Long secs = Long.parseLong(str_date);
		java.sql.Timestamp timeStampDate = new Timestamp(secs);
		return timeStampDate;

	}
}
