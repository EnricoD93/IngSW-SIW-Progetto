package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarioPersonale;
import model.Corso;
import model.DescrizioneCorso;
import model.Evento;
import model.GiornoCalendario;
import model.Lezione;
import model.Utente;
import persistence.DatabaseManager;
import persistence.UtilDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.EventoDao;
import persistence.dao.LezioneDao;

public class CreateCourse extends HttpServlet {
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
		Utente user = (Utente) req.getSession().getAttribute("currentUser");
		Lezione luned� = null;
		Lezione marted� = null;
		Lezione mercoled� = null;
		Lezione gioved� = null;
		Lezione venerd� = null;
		ArrayList<Lezione> lezioni = new ArrayList<>();
		if (request.equals("create")) {
			String giorniLezione = "";
			if (req.getParameter("lunedi") != null) {
				giorniLezione += "lunedi ";
				String aulaLun = req.getParameter("idAula_1");
				double oraInizioLun = convert(req.getParameter("oraInizioLun"));
				double oraFineLun = convert(req.getParameter("oraFineLun"));
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Luned�");
				luned� = new Lezione(Long.parseLong(codiceCorso), g, oraInizioLun, oraFineLun, aulaLun, 0);
			}
			if (req.getParameter("martedi") != null) {
				giorniLezione += "martedi ";
				String aulaMar = req.getParameter("idAula_2");
				double oraInizioMar = convert(req.getParameter("oraInizioMar"));
				double oraFineMar = convert(req.getParameter("oraFineMar"));
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Marted�");
				marted� = new Lezione(Long.parseLong(codiceCorso), g, oraInizioMar, oraFineMar, aulaMar, 0);
			}
			if (req.getParameter("mercoledi") != null) {
				String aulaMer = req.getParameter("idAula_3");
				double oraInizioMer = convert(req.getParameter("oraInizioMer"));
				double oraFineMer = convert(req.getParameter("oraFineMer"));
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Mercoled�");
				mercoled� = new Lezione(Long.parseLong(codiceCorso), g, oraInizioMer, oraFineMer, aulaMer, 0);
				giorniLezione += "mercoledi ";
			}
			if (req.getParameter("giovedi") != null) {
				String aulaGio = req.getParameter("idAula_4");
				double oraInizioGio = convert(req.getParameter("oraInizioGio"));
				double oraFineGio = convert(req.getParameter("oraFineGio"));
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Gioved�");
				gioved� = new Lezione(Long.parseLong(codiceCorso), g, oraInizioGio, oraFineGio, aulaGio, 0);
				giorniLezione += "giovedi ";
			}
			if (req.getParameterValues("venerdi") != null) {
				String aulaVen = req.getParameter("idAula_3");
				double oraInizioVen = convert(req.getParameter("oraInizioVen"));
				double oraFineVen = convert(req.getParameter("oraFineVen"));
				GiornoCalendario g = new GiornoCalendario();
				g.setGiornoDellaSettimana("Venerd�");
				venerd� = new Lezione(Long.parseLong(codiceCorso), g, oraInizioVen, oraFineVen, aulaVen, 0);
				giorniLezione += "venerdi";
			}
			CalendarioPersonale cal = new CalendarioPersonale();
			GiornoCalendario g = new GiornoCalendario();
			GiornoCalendario inizio = new GiornoCalendario();
			GiornoCalendario fine = new GiornoCalendario();
			inizio.parseToGiornoCalendario(g.parseToDate(dataInizio));
			inizio.stampa();
			fine.parseToGiornoCalendario(g.parseToDate(dataFine));
			fine.stampa();

			if (luned� != null) {
				lezioni.addAll(
						cal.getLezioniCorso(luned�.getCorso(), inizio, fine, luned�.getData().getGiornoDellaSettimana(),
								luned�.getAula(), 0, luned�.getOraInizio(), luned�.getOraFine()));
			}
			if (marted� != null) {
				lezioni.addAll(cal.getLezioniCorso(marted�.getCorso(), inizio, fine,
						marted�.getData().getGiornoDellaSettimana(), marted�.getAula(), 0, marted�.getOraInizio(),
						marted�.getOraFine()));
			}
			if (mercoled� != null) {
				lezioni.addAll(cal.getLezioniCorso(mercoled�.getCorso(), inizio, fine,
						mercoled�.getData().getGiornoDellaSettimana(), mercoled�.getAula(), 0, mercoled�.getOraInizio(),
						mercoled�.getOraFine()));
			}
			if (gioved� != null) {
				lezioni.addAll(cal.getLezioniCorso(gioved�.getCorso(), inizio, fine,
						gioved�.getData().getGiornoDellaSettimana(), gioved�.getAula(), 0, gioved�.getOraInizio(),
						gioved�.getOraFine()));
				System.out.println("la size di lezioni � " + lezioni.size());
			}
			if (venerd� != null) {
				lezioni.addAll(cal.getLezioniCorso(venerd�.getCorso(), inizio, fine,
						venerd�.getData().getGiornoDellaSettimana(), venerd�.getAula(), 0, venerd�.getOraInizio(),
						venerd�.getOraFine()));
			}

			EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();

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
			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();
			LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
			corsoDao.save(c);

			for (int i = 0; i < lezioni.size(); i++) {
				lezioneDao.save(lezioni.get(i));
				Calendar cal2 = new GregorianCalendar();
				cal2.set(lezioni.get(i).getData().getAnno(), lezioni.get(i).getData().getMese() - 1,
						lezioni.get(i).getData().getGiorno());
				cal2.set(Calendar.HOUR_OF_DAY, 13);
				cal2.set(Calendar.MINUTE, 30);
				Date dateEventoIn = (Date) cal2.getTime();
				Timestamp ev1 = new Timestamp(dateEventoIn.getTime());
				Evento e = new Evento("Lezione " + corso.getNome() + i, ev1, ev1, "ProvaLezione");
				eventoDao.save(e);
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
					corsoDao.delete(c);
				}
			} else {
				resp.setStatus(401);
			}
		}
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	public double convert(String s) {
		String[] num = s.split(":");
		double intero = Double.parseDouble(num[0]);
		double decimale = Double.parseDouble(num[1]);
		double n = intero + decimale / 100;
		return n;

	}

}
