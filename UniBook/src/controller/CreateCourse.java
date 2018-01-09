package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Corso;
import model.DescrizioneCorso;
import model.GiornoCalendario;
import model.Utente;
import persistence.DatabaseManager;
import persistence.UtilDao;
import persistence.dao.CorsoDao;
import persistence.dao.DescrizioneCorsoDao;

public class CreateCourse extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codiceCorso = req.getParameter("codice");
		String descrizione = req.getParameter("descrizione");
		String requisiti = req.getParameter("requisiti");
		String materiale = req.getParameter("materiale");
		String dataInizio=req.getParameter("dataInizio");
		String dataFine=req.getParameter("dataFine");
		String aula=req.getParameter("idAula");
		Utente user = (Utente) req.getSession().getAttribute("currentUser");
		String giorniLezione = "";
		if (req.getParameter("luned�") != null) {
			giorniLezione += "luned� ";
		}
		if (req.getParameter("marted�") != null) {
			giorniLezione += "marted� ";
		}
		if (req.getParameter("mercoled�") != null) {
			giorniLezione += "mercoled� ";
		}
		if (req.getParameter("gioved�") != null) {
			giorniLezione += "gioved� ";
		}
		if (req.getParameter("venerd�") != null) {
			giorniLezione += "venerd� ";
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
		c.setRequisiti(requisiti);
		c.setGiorno(giorniLezione);
		c.setMateriale(materiale);
		c.setDocente(user.getMatricola());
		c.setNomeDocente(user.getNome());
		c.setCognomeDocente(user.getCognome());
		GiornoCalendario g= new GiornoCalendario();
		g.parseToGiornoCalendario(g.parseToDate(dataInizio));
		c.setDataInizio(g);
		g= new GiornoCalendario();
		g.parseToGiornoCalendario(g.parseToDate(dataFine));
		c.setDataFine(g);
		
		corsoDao.save(c);
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}
}
