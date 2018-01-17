package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.course.Corso;
import model.course.Lezione;
import model.user.Evento;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.CorsoDao;
import persistence.dao.LezioneDao;
import persistence.dao.UtenteDao;

public class ShowCourse extends HttpServlet {
	Corso currentCourse;
	Utente currentStudent;
	Utente courseDocente;
	List<Utente> studentiIscritti;
	String richiesta = "vuota";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		richiesta = req.getParameter("richiesta");
		Long codice = Long.parseLong(req.getParameter("codice"));
		CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		currentCourse = corsoDao.findByPrimaryKey(codice);
		courseDocente = utenteDao.findByPrimaryKey(currentCourse.getDocente());

		if (richiesta.equals("mostraCorso")) {
			studentiIscritti = corsoDao.getStudentiIscritti(codice);
			session.setAttribute("courseDocente", courseDocente);
			session.setAttribute("currentCourse", currentCourse);
			session.setAttribute("currentStudent", currentStudent);
			session.setAttribute("studentiIscritti", studentiIscritti);
		}
		if (richiesta.equals("eliminaIscrizioneStudente")) {
			String matricolaStudente = req.getParameter("matricolaStudente");
			utenteDao.eliminaIscrizioneStudente(matricolaStudente, currentCourse.getCodice());
		}
		if (richiesta.equals("studentiCorsoPresenze")) {
			Long lezione = Long.parseLong(req.getParameter("lezione"));
			UtenteDao u = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			u.deletePresenze( lezione);
			System.out.println("servlet");
			studentiIscritti = corsoDao.getStudentiIscritti(codice);
			JSONObject result = new JSONObject();
			JSONArray listaJson = new JSONArray();
			for (int i = 0; i < studentiIscritti.size(); i++) {
				try {
					JSONObject studente = new JSONObject();
					studente.put("studente", studentiIscritti.get(i).getMatricola());
					listaJson.put(studente);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		if (richiesta.equals("salvaPresenza")) {
			System.out.println("SALVATA");
			Long lezione = Long.parseLong(req.getParameter("lezione"));
			String matricola = req.getParameter("matricola");
			UtenteDao u = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			String checked = req.getParameter("checked");
			System.out.println(checked);
		
			u.salvaPresenza(matricola, lezione);
		}

	}
}