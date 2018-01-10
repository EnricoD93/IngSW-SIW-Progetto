package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarioPersonale;
import model.Corso;
import model.DescrizioneCorso;
import model.GiornoCalendario;
import model.Lezione;
import model.Utente;
import persistence.DatabaseManager;
import persistence.UtilDao;
import persistence.dao.CorsoDao;
import persistence.dao.DescrizioneCorsoDao;

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
		String aulaLun = req.getParameter("idAula_1");
		String aulaMar = req.getParameter("idAula_2");
		String aulaMer = req.getParameter("idAula_3");
		String aulaGio = req.getParameter("idAula_4");
		String aulaVen = req.getParameter("idAula_5");
double oraInizioLun=Double.parseDouble(req.getParameter("oraInizioLun"));		

		if (request.equals("create")) {
			String giorniLezione = "";
			if (req.getParameter("lunedi") != null) {
				giorniLezione += "lunedi ";
			}
			if (req.getParameter("martedi") != null) {
				giorniLezione += "martedi ";
			}
			if (req.getParameter("mercoledi") != null) {
				giorniLezione += "mercoledi ";
			}
			if (req.getParameter("giovedi") != null) {
				giorniLezione += "giovedi ";
			}
			if (req.getParameterValues("venerdi") != null) {
				giorniLezione += "venerdi";
			}
			System.out.println("i giorni di lezione sono " + giorniLezione);
			CalendarioPersonale cal= new CalendarioPersonale();
			GiornoCalendario g= new GiornoCalendario();
			GiornoCalendario inizio= new GiornoCalendario();
			GiornoCalendario fine= new GiornoCalendario();
		inizio.parseToGiornoCalendario(g.parseToDate(dataInizio));
		fine.parseToGiornoCalendario(g.parseToDate(dataFine));
			ArrayList<Lezione> lezioni= cal.getLezioniCorso(Long.parseLong(codiceCorso),inizio,fine,giorniLezione,aulaLun,0,oraInizioLun,oraInizioLun);
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
}
