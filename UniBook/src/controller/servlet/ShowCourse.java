package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.course.Avviso;
import model.course.Corso;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.AvvisoDao;
import persistence.dao.CorsoDao;
import persistence.dao.UtenteDao;

public class ShowCourse extends HttpServlet {
	Corso currentCourse;
	Utente courseDocente;
	List<Utente> studentiIscritti;
	String richiesta = "vuota";
	List<Avviso> avvisi;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		richiesta = req.getParameter("richiesta");
		Long codice = Long.parseLong(req.getParameter("codice"));
		CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		AvvisoDao avvDao = DatabaseManager.getInstance().getDaoFactory().getAvvisoDAO();
		currentCourse = corsoDao.findByPrimaryKey(codice);
		courseDocente = utenteDao.findByPrimaryKey(currentCourse.getDocente());

		if (richiesta.equals("mostraCorso")) {
			studentiIscritti = corsoDao.getStudentiIscritti(codice);
			avvisi = avvDao.findAllByCourse(codice);
			req.setAttribute("courseDocente", courseDocente);
			req.setAttribute("currentCourse", currentCourse);
			req.setAttribute("studentiIscritti", studentiIscritti);
			req.setAttribute("advices", avvisi);
		}
		if (richiesta.equals("eliminaIscrizioneStudente")) {
			String matricolaStudente = req.getParameter("matricolaStudente");
			utenteDao.eliminaIscrizioneStudente(matricolaStudente, currentCourse.getCodice());
		}
		if (richiesta.equals("studentiCorsoPresenze")) {
			Long lezione = Long.parseLong(req.getParameter("lezione"));
			UtenteDao u = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			u.deletePresenze(lezione);
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
			Long lezione = Long.parseLong(req.getParameter("lezione"));
			String matricola = req.getParameter("matricola");
			UtenteDao u = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			String checked = req.getParameter("checked");
			u.salvaPresenza(matricola, lezione);
		}
		if (richiesta.equals("salvaAvviso")) {
			String id = req.getParameter("id");
			if (!id.equals("new"))
				avvDao.delete(Long.parseLong(id));
			String text = req.getParameter("text");
			String title = req.getParameter("title");
			Timestamp t = new Timestamp(System.currentTimeMillis());
			Avviso avviso = new Avviso(text, title, codice, t);
			avvDao.save(avviso);
		}
		if (richiesta.equals("eliminaAvviso")) {
			Long id = Long.parseLong(req.getParameter("id"));
			avvDao.delete(id);
		}
		if (richiesta.equals("getAvviso")) {
			Long id = Long.parseLong(req.getParameter("id"));
			Avviso a = avvDao.findByPrimaryKey(id);
			JSONObject json = new JSONObject();
			try {
				json.put("id", a.getId());
				json.put("titolo", a.getTitolo());
				json.put("testo", a.getText());
				json.put("corso", a.getCorso());
				json.put("data", a.getDatareale());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.setContentType("application/json");
			resp.getWriter().print(json);
		}

	}
}