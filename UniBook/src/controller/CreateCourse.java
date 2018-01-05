package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Corso;
import model.DescrizioneCorso;
import persistence.DatabaseManager;
import persistence.UtilDao;
import persistence.dao.CorsoDao;
import persistence.dao.DescrizioneCorsoDao;

public class CreateCourse extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codiceCorso = req.getParameter("codice");
		String descrizione = req.getParameter("descrizione");
		String giorniLezione = "";
		if (req.getParameter("lunedì") != null) {
			giorniLezione += req.getParameter("lunedì");
		}
		if (req.getParameter("martedì") != null) {
			giorniLezione += req.getParameter("martedì");
		}
		if (req.getParameter("mercoledì") != null) {
			giorniLezione += req.getParameter("mercoledì");
		}
		if (req.getParameter("giovedì") != null) {
			giorniLezione += req.getParameter("giovedì");
		}
		if (req.getParameter("venerdì") != null) {
			giorniLezione += req.getParameter("venerdì");
		}
		
		
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
		String cognome = req.getParameter("userlastname");
		String matricola = req.getParameter("usermatr");
		String mailto = req.getParameter("email");
		String dataNascita = req.getParameter("dataNascita");
		String codicef = req.getParameter("codf");
		String password = req.getParameter("password");
		String ruolo = req.getParameter("role");
	}
}
