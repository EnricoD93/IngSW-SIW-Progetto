package controller.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTPTokener;

import model.course.Corso;
import model.course.DescrizioneCorso;
import model.course.Lezione;
import model.user.CalendarioPersonale;
import model.user.Evento;
import model.user.GiornoCalendario;
import model.user.Notifica;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.EventoDao;
import persistence.dao.LezioneDao;
import persistence.dao.NotificaDao;
import persistence.dao.UtenteDao;

public class CreateCourse extends HttpServlet {
	ArrayList<Lezione> lezioni;
	public String coincidenti = "NC";
	Utente user;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String request = req.getParameter("request");
		String codiceCorso = req.getParameter("codice");
		String descrizione = req.getParameter("descrizione");
		String requisiti = req.getParameter("requisiti");
		String materiale = req.getParameter("materiale");
		String dataInizio = req.getParameter("dataInizio");
		String dataFine = req.getParameter("dataFine");
		String[] propedeuticit� = req.getParameterValues("prop");
		user = (Utente) req.getSession().getAttribute("currentUser");
		lezioni = new ArrayList<>();

		// creazione di una lezione in base alle carateristiche selezionate (manca il
		// tipo lezione)
		if (request.equals("create")) {
			GiornoCalendario g;
			String giorniLezione = createLessons(req, resp, codiceCorso);
			// creazione del corso
			DescrizioneCorsoDao descCorsoDao = DatabaseManager.getInstance().getDaoFactory().getDescrizioneCorsoDao();
			DescrizioneCorso corso = descCorsoDao.findByPrimaryKey(Long.parseLong(codiceCorso));
			CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
			Corso c = new Corso();
			c.setCodice(corso.getCodice());
			c.setAnno(corso.getAnno());
			c.setCfu(corso.getCfu());
			c.setCorsoDiLaurea(corso.getCorsoDiLaurea());
			c.setNome(corso.getNome());
			c.setOreEsercitazione(corso.getOreEsercitazione());
			c.setOreLezione(corso.getOreLezione());
			c.setDescrizione(descrizione);
			c.setRequisiti(requisiti);
			c.setGiorno(giorniLezione);
			c.setMateriale(materiale);
			c.setDocente(user.getMatricola());
			c.setNomeDocente(user.getNome());
			c.setCognomeDocente(user.getCognome());
			g = new GiornoCalendario();
			g.parseToGiornoCalendario(g.parseToDate(dataInizio));
			c.setDataInizio(g);
			g = new GiornoCalendario();
			g.parseToGiornoCalendario(g.parseToDate(dataFine));
			c.setDataFine(g);
			corsoDao.save(c);
			if (propedeuticit� != null)
				for (String string : propedeuticit�) {
					corsoDao.setPropedeutico(Long.parseLong(string), Long.parseLong(codiceCorso));
				}
			// ----salvataggio delle lezioni del corso nel db e nel calendario del docente
			// loggato----

			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();
			LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
			EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
			for (int i = 0; i < lezioni.size(); i++) {

				// salvataggio lezioni nel db
				lezioneDao.save(lezioni.get(i));

				// creazione lezioni come evento e salvataggio nel db
				Calendar cal2 = new GregorianCalendar();
				cal2.set(lezioni.get(i).getData().getAnno(), lezioni.get(i).getData().getMese() - 1,
						lezioni.get(i).getData().getGiorno());
				cal2.set(Calendar.HOUR_OF_DAY, 13);
				cal2.set(Calendar.MINUTE, 30);
				Date dateEventoIn = (Date) cal2.getTime();
				Timestamp ev1 = new Timestamp(dateEventoIn.getTime());
				String title;
				if(lezioni.get(i).getTipo()==0)
					title="Lezione";
				else 
					title="Esercitazione";
				Evento e = new Evento(lezioni.get(i).getId(), title +" "+ corso.getNome(), ev1, ev1, "ProvaLezione");
				e.setId(lezioni.get(i).getId());
				eventoDao.save(e);

				// salvataggio delle lezioni nel calendario dell'utente
				calendarioPersonaleDao.saveEvent(user.getMatricola(), e);

			}

		}
		if (request.equals("cancel")) {
			Utente u = (Utente) session.getAttribute("currentUser");
			String typedPassword = req.getParameter("typedPassword");
			String matricola = req.getParameter("matricola");
			Long codice = Long.parseLong(req.getParameter("codice"));
			CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
			Corso c = corsoDao.findByPrimaryKey(codice);
			if (u.getPassword().equals(typedPassword)) {
				if (!u.getMatricola().equals(c.getDocente())) {
					resp.setStatus(405);
					return;
				} else {
					CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
							.getCalendarioPersonaleDAO();

					List<Evento> listaEventiCal = calendarioPersonaleDao.findAllEventsUtente(u.getMatricola());
					LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
					List<Evento> listaLezioni = lezioneDao.findCourseLessons(codice);
					List<Utente> iscritti = corsoDao.getStudentiIscritti(codice);
					NotificaDao notificaDao = DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();
					Timestamp t = new Timestamp(System.currentTimeMillis());
					// eliminazione lezioni dal calendario degli studenti iscritti e elimina
					// iscrizione degli studenti
					UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
					for (int i = 0; i < iscritti.size(); i++) {
						notificaDao.save(new Notifica(iscritti.get(i).getMatricola(), t, 2, c.getNome()));
						lezioneDao.eliminaLezioniDalCalendario(listaEventiCal, listaLezioni, calendarioPersonaleDao,
								iscritti.get(i).getMatricola());
						UtenteDao utente = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
						utente.eliminaIscrizioneStudente(iscritti.get(i).getMatricola(), codice);
					}
					// eliminazione lezioni dal calendario del docente
					lezioneDao.eliminaLezioniDalCalendario(listaEventiCal, listaLezioni, calendarioPersonaleDao,
							u.getMatricola());

					// eliminazione lezioni dal db
					for (int i = 0; i < listaLezioni.size(); i++) {
						utenteDao.deletePresenze(listaLezioni.get(i).getId());
						lezioneDao.deleteLessonByEvent(listaLezioni.get(i));
					}
					// eliminazione del corso
					corsoDao.deletePropedeutico(codice);
					corsoDao.deleteAvvisi(codice);
					corsoDao.delete(c);
				}
			} else {
				resp.setStatus(401);
				return;
			}
		}
		if (request.equals("modify")) {
			EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
			GiornoCalendario g;
			Utente u = (Utente) session.getAttribute("currentUser");
			String matricola = req.getParameter("matricola");
			Long codice = Long.parseLong(req.getParameter("codice"));
			CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
			Corso c = corsoDao.findByPrimaryKey(codice);
			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();

			List<Evento> listaEventiCal = calendarioPersonaleDao.findAllEventsUtente(u.getMatricola());
			LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
			List<Evento> listaLezioni = lezioneDao.findCourseLessons(codice);
			List<Utente> iscritti = corsoDao.getStudentiIscritti(codice);
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			for (int i = 0; i < iscritti.size(); i++) {

				lezioneDao.eliminaLezioniDalCalendario(listaEventiCal, listaLezioni, calendarioPersonaleDao,
						iscritti.get(i).getMatricola());
			}

			lezioneDao.eliminaLezioniDalCalendario(listaEventiCal, listaLezioni, calendarioPersonaleDao,
					u.getMatricola());

			for (int i = 0; i < listaLezioni.size(); i++) {
				utenteDao.deletePresenze(listaLezioni.get(i).getId());
				lezioneDao.deleteLessonByEvent(listaLezioni.get(i));
			}

			String giorniLezione = createLessons(req, resp, codiceCorso);

			for (int i = 0; i < lezioni.size(); i++) {

				// salvataggio lezioni nel db
				lezioneDao.save(lezioni.get(i));

				// creazione lezioni come evento e salvataggio nel db
				Calendar cal2 = new GregorianCalendar();
				cal2.set(lezioni.get(i).getData().getAnno(), lezioni.get(i).getData().getMese() - 1,
						lezioni.get(i).getData().getGiorno());
				cal2.set(Calendar.HOUR_OF_DAY, 13);
				cal2.set(Calendar.MINUTE, 30);
				Date dateEventoIn = (Date) cal2.getTime();
				Timestamp ev1 = new Timestamp(dateEventoIn.getTime());
				String title;
				if(lezioni.get(i).getTipo()==0)
					title="Lezione";
				else 
					title="Esercitazione";
				Evento e = new Evento(lezioni.get(i).getId(), title +" "+ c.getNome(), ev1, ev1, "ProvaLezione");
				e.setId(lezioni.get(i).getId());
				eventoDao.save(e);

				// salvataggio delle lezioni nel calendario dell'utente
				calendarioPersonaleDao.saveEvent(user.getMatricola(), e);

				for (Utente utente : iscritti) {
					calendarioPersonaleDao.saveEvent(matricola, e);
				}
			}
			corsoDao.deletePropedeutico(codice);

			c.setDescrizione(descrizione);
			c.setRequisiti(requisiti);
			c.setGiorno(giorniLezione);
			c.setMateriale(materiale);
			g = new GiornoCalendario();
			g.parseToGiornoCalendario(g.parseToDate(dataInizio));
			c.setDataInizio(g);
			g = new GiornoCalendario();
			g.parseToGiornoCalendario(g.parseToDate(dataFine));
			c.setDataFine(g);

			if (propedeuticit� != null)
				for (String string : propedeuticit�) {
					corsoDao.setPropedeutico(Long.parseLong(string), Long.parseLong(codiceCorso));
				}
			corsoDao.update(c);
		}
		resp.sendRedirect("page?request=corsi");
	}

	public double convert(String s) {
		String[] num = s.split(":");
		double intero = Double.parseDouble(num[0]);
		double decimale = Double.parseDouble(num[1]);
		double n = intero + decimale / 100;
		return n;

	}

	public String createLessons(HttpServletRequest req, HttpServletResponse resp, String codiceCorso) {

		String dataInizio = req.getParameter("dataInizio");
		String dataFine = req.getParameter("dataFine");
		Lezione luned� = null;
		Lezione marted� = null;
		Lezione mercoled� = null;
		Lezione gioved� = null;
		Lezione venerd� = null;
		String giorniLezione = "";
		int type = 0;

		try {
			if (req.getParameter("lunedi") != null) {
				String aulaLun = req.getParameter("idAula_1");
				String oraInizioLun = req.getParameter("oraInizioLun");
				String oraFineLun = req.getParameter("oraFineLun");
				String tipo = req.getParameter("tipolezione_1");
				if (tipo.equals("esercitazione"))
					type = 1;
				else
					type = 0;
				GiornoCalendario g = new GiornoCalendario();
				giorniLezione += "lunedi," + oraInizioLun + "," + oraFineLun + "," + "0" + "," + aulaLun + "_";
				g.setGiornoDellaSettimana("Luned�");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date parsedDate;
				parsedDate = dateFormat.parse(oraInizioLun);

				Timestamp oraInLun = new java.sql.Timestamp(parsedDate.getTime());
				parsedDate = dateFormat.parse(oraFineLun);
				Timestamp oraFinLun = new java.sql.Timestamp(parsedDate.getTime());
				luned� = new Lezione(Long.parseLong(codiceCorso), g, oraInLun, oraFinLun, aulaLun, type);
			}
			if (req.getParameter("martedi") != null) {
				String aulaMar = req.getParameter("idAula_2");
				String oraInizioMar = req.getParameter("oraInizioMar");
				String oraFineMar = req.getParameter("oraFineMar");
				String tipo = req.getParameter("tipolezione_2");
				if (tipo.equals("esercitazione"))
					type = 1;
				else
					type = 0;
				giorniLezione += "martedi," + oraInizioMar + "," + oraFineMar + "," + "0" + "," + aulaMar + "_";
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Marted�");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				Date parsedDate = dateFormat.parse(oraInizioMar);
				Timestamp oraInMar = new java.sql.Timestamp(parsedDate.getTime());
				parsedDate = dateFormat.parse(oraFineMar);
				Timestamp oraFinMar = new java.sql.Timestamp(parsedDate.getTime());
				marted� = new Lezione(Long.parseLong(codiceCorso), g, oraInMar, oraFinMar, aulaMar, type);
			}
			if (req.getParameter("mercoledi") != null) {
				String aulaMer = req.getParameter("idAula_3");
				String oraInizioMer = req.getParameter("oraInizioMer");
				String oraFineMer = req.getParameter("oraFineMer");
				String tipo = req.getParameter("tipolezione_3");
				if (tipo.equals("esercitazione"))
					type = 1;
				else
					type = 0;
				giorniLezione += "mercoledi," + oraInizioMer + "," + oraFineMer + "," + "0" + "," + aulaMer + "_";
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Mercoled�");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				Date parsedDate = dateFormat.parse(oraInizioMer);
				Timestamp oraInMer = new java.sql.Timestamp(parsedDate.getTime());
				parsedDate = dateFormat.parse(oraFineMer);
				Timestamp oraFinMer = new java.sql.Timestamp(parsedDate.getTime());
				mercoled� = new Lezione(Long.parseLong(codiceCorso), g, oraInMer, oraFinMer, aulaMer, type);
			}
			if (req.getParameter("giovedi") != null) {
				String aulaGio = req.getParameter("idAula_4");
				String oraInizioGio = req.getParameter("oraInizioGio");
				String oraFineGio = req.getParameter("oraFineGio");
				String tipo = req.getParameter("tipolezione_4");
				if (tipo.equals("esercitazione"))
					type = 1;
				else
					type = 0;
				giorniLezione += "giovedi," + oraInizioGio + "," + oraFineGio + "," + "0" + "," + aulaGio + "_";
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Gioved�");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				Date parsedDate = dateFormat.parse(oraInizioGio);
				Timestamp oraInGio = new java.sql.Timestamp(parsedDate.getTime());
				parsedDate = dateFormat.parse(oraFineGio);
				Timestamp oraFinGio = new java.sql.Timestamp(parsedDate.getTime());
				gioved� = new Lezione(Long.parseLong(codiceCorso), g, oraInGio, oraFinGio, aulaGio, type);
			}
			if (req.getParameterValues("venerdi") != null) {
				String aulaVen = req.getParameter("idAula_5");
				String oraInizioVen = req.getParameter("oraInizioVen");
				String oraFineVen = req.getParameter("oraFineVen");
				String tipo = req.getParameter("tipolezione_5");
				if (tipo.equals("esercitazione"))
					type = 1;
				else
					type = 0;
				giorniLezione += "venerdi," + oraInizioVen + "," + oraFineVen + "," + "0" + "," + aulaVen + "_";
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Venerd�");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				Date parsedDate = dateFormat.parse(oraInizioVen);
				Timestamp oraInVen = new java.sql.Timestamp(parsedDate.getTime());
				parsedDate = dateFormat.parse(oraFineVen);
				Timestamp oraFinVen = new java.sql.Timestamp(parsedDate.getTime());
				venerd� = new Lezione(Long.parseLong(codiceCorso), g, oraInVen, oraFinVen, aulaVen, type);
			}

			// salvataggio data inizio e fine del corso
			CalendarioPersonale cal = new CalendarioPersonale();
			GiornoCalendario g = new GiornoCalendario();
			GiornoCalendario inizio = new GiornoCalendario();
			GiornoCalendario fine = new GiornoCalendario();
			inizio.parseToGiornoCalendario(g.parseToDate(dataInizio));
			fine.parseToGiornoCalendario(g.parseToDate(dataFine));
			fine.setGiorno(fine.getGiorno());
			fine.next();
			// calcolo delle lezioni specifiche che il corso prevede
			if (luned� != null) {
				lezioni.addAll(
						cal.getLezioniCorso(luned�.getCorso(), inizio, fine, luned�.getData().getGiornoDellaSettimana(),
								luned�.getAula(), luned�.getTipo(), luned�.getOraInizio(), luned�.getOraFine(), this));
			}
			if (marted� != null) {
				lezioni.addAll(cal.getLezioniCorso(marted�.getCorso(), inizio, fine,
						marted�.getData().getGiornoDellaSettimana(), marted�.getAula(), marted�.getTipo() , marted�.getOraInizio(),
						marted�.getOraFine(), this));
			}
			if (mercoled� != null) {
				lezioni.addAll(cal.getLezioniCorso(mercoled�.getCorso(), inizio, fine,
						mercoled�.getData().getGiornoDellaSettimana(), mercoled�.getAula(), mercoled�.getTipo(), mercoled�.getOraInizio(),
						mercoled�.getOraFine(), this));
			}
			if (gioved� != null) {
				lezioni.addAll(cal.getLezioniCorso(gioved�.getCorso(), inizio, fine,
						gioved�.getData().getGiornoDellaSettimana(), gioved�.getAula(), gioved�.getTipo(), gioved�.getOraInizio(),
						gioved�.getOraFine(), this));
			}
			if (venerd� != null) {
				lezioni.addAll(cal.getLezioniCorso(venerd�.getCorso(), inizio, fine,
						venerd�.getData().getGiornoDellaSettimana(), venerd�.getAula(), venerd�.getTipo(), venerd�.getOraInizio(),
						venerd�.getOraFine(), this));
			}
			if (coincidenti.equals("C")) {
				new Thread() {
					public void run() {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						NotificaDao nd = DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();
						Notifica n = new Notifica(user.getMatricola(), new Timestamp(System.currentTimeMillis()), 5);
						nd.save(n);
					};
				}.start();

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return giorniLezione;
	}
}
