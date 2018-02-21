package controller.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.user.Evento;
import model.user.GiornoCalendario;
import model.user.Notifica;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.EventoDao;
import persistence.dao.LezioneDao;
import persistence.dao.NotificaDao;
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
		LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
		EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		AulaDao aula = DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
		List<Corso> corsi = utenteDao.getCorsiDocente(currentUser.getMatricola());
		List<Aula> aule = aula.findAll();
		req.setAttribute("corsi", corsi);
		req.setAttribute("aule", aule);
		listaSoloEventi = eventoDao.findEvent();
		listaSoloLezioni = lezioneDao.findLezioni();
		System.out.println("listaSoloEventi" + listaSoloEventi.size());
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

		// DAO
		EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
		CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
				.getCalendarioPersonaleDAO();
		CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
		NotificaDao notificaDao=DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();

		if (request.equals("Eventi")) {

			listaEventi = calendarioPersonaleDao.findAllEventsUtente(matricola);
			listaSoloEventi = eventoDao.findEvent();
			listaSoloLezioni = lezioneDao.findLezioni();
			req.setAttribute("listaSoloLezioni", listaSoloLezioni.size());
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
					evento.put("id", listaEventi.get(i).getId());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				listaJson.put(evento);
			}
			try {
				result.put("result", listaJson);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			resp.setContentType("application/json");
			resp.getWriter().print(result);
		}
		if (request.equals("creaEvento")) {
			String start = req.getParameter("start");
			String end = req.getParameter("end");
			Boolean lezione = Boolean.parseBoolean(req.getParameter("lezione"));
			Timestamp startT = null;
			Timestamp endT = null;
			String title = req.getParameter("title");
			Evento e;
			JSONObject json = new JSONObject();
			try {
				startT = parseDate(start);
				endT = parseDate(end);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			if (lezione) {
				System.out.println(req.getParameter("corso"));
				Long corso = Long.parseLong(req.getParameter("corso"));
				String aula = req.getParameter("aula");
				String oraInizio= req.getParameter("oraInizio");
				String oraFine= req.getParameter("oraFine");
				int type=0;
//				String tipo = req.getParameter("tipolezione_6");
				System.out.println("corso "+corso);
//				System.out.println("tipoLezione "+tipo);
//				if (tipo.equals("esercitazione"))
//					type = 1;
//				else
//					type = 0;
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Martedì");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				java.util.Date parsedDate;
				Timestamp oraIn=null;
				Timestamp oraFin=null;
				try {
					System.out.println(oraInizio);
					parsedDate = dateFormat.parse(oraInizio);
			
				oraIn = new java.sql.Timestamp(parsedDate.getTime());
				parsedDate = dateFormat.parse(oraFine);
				oraFin = new java.sql.Timestamp(parsedDate.getTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				Date data = new Date(startT.getTime());
				System.out.println(data.toLocalDate());
				g = new GiornoCalendario();
				g.parseToGiornoCalendario(data);
				Lezione l = new Lezione(corso, g, oraIn, oraFin, aula,type);
				lezioneDao.save(l);
				
				// salvo la lezione nel calendario degli studenti iscritti
				Corso c=corsoDao.findByPrimaryKey(corso);
				List<Utente> studentiIscritti = corsoDao.getStudentiIscritti(corso);
		
				//salvo l'evento nel database e nel calendario del docente
				e = new Evento(l.getId(), title, startT, endT, "nessuna");
				eventoDao.save(e);
				calendarioPersonaleDao.saveEvent(matricola, e);
				
				//creo una notifica per ogni studente iscritto
				Timestamp t=new Timestamp(System.currentTimeMillis());
				for (int i = 0; i < studentiIscritti.size(); i++) {
					calendarioPersonaleDao.saveEvent(studentiIscritti.get(i).getMatricola(), e);
					notificaDao.save(new Notifica(studentiIscritti.get(i).getMatricola(),t,0,c.getNome()));
				}
			} else {
				//creo un evento e lo aggiungo nel calendario dell'utente loggato
				e = new Evento(title, startT, endT, "nessuna");
				eventoDao.save(e);
				calendarioPersonaleDao.saveEvent(matricola, e);
			}

			try {
				json.put("id", e.getId());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			resp.setContentType("application/json");
			resp.getWriter().print(json);
		}
		if (request.equals("rimuoviEvento")) {
			Long id = Long.parseLong(req.getParameter("id"));
			Lezione l = lezioneDao.findByPrimaryKey(id);
			if (l != null) {
				//elimino la lezione dal calendario
				Long corso = l.getCorso();
				List<Utente> studentiIscritti = corsoDao.getStudentiIscritti(corso);
				Corso c=corsoDao.findByPrimaryKey(corso);
				Timestamp t=new Timestamp(System.currentTimeMillis());
				Evento e = eventoDao.findByPrimaryKey(l.getId());
				//elimino la lezione dal calendario di tutti gli studenti iscritti al corso e creo una notifica per ognuno di essi
				for (int i = 0; i < studentiIscritti.size(); i++) {
					calendarioPersonaleDao.deleteEvent(studentiIscritti.get(i).getMatricola(), e);
					notificaDao.save(new Notifica(studentiIscritti.get(i).getMatricola(),t,1,c.getNome()));
				}	
				calendarioPersonaleDao.deleteEvent(matricola, e);
				eventoDao.delete(e);
					lezioneDao.delete(l);

			} else {
				//elimino l'evento dal calendario
				Evento e = eventoDao.findByPrimaryKey(id);
				calendarioPersonaleDao.deleteEvent(matricola, e);
				eventoDao.delete(e);
			}

		}

	}

	private Timestamp parseDate(String str_date) throws ParseException {

		Long secs = Long.parseLong(str_date);
		java.sql.Timestamp timeStampDate = new Timestamp(secs);
		return timeStampDate;

	}
}
