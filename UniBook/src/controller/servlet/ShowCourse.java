package controller.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.course.Avviso;
import model.course.Corso;
import model.course.Lezione;
import model.user.Esame;
import model.user.Notifica;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.AvvisoDao;
import persistence.dao.CorsoDao;
import persistence.dao.LezioneDao;
import persistence.dao.NotificaDao;
import persistence.dao.UtenteDao;

public class ShowCourse extends HttpServlet {
	Corso currentCourse;
	Utente courseDocente;
	List<Utente> studentiIscritti;
	String richiesta = "vuota";
	List<Avviso> avvisi;
	List<Esame> propedeutici;
	List<String> giorni;

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
//
//		if (richiesta.equals("mostraCorso")) {
//			studentiIscritti = corsoDao.getStudentiIscritti(codice);
//			avvisi = avvDao.findAllByCourse(codice);
//			propedeutici = corsoDao.getEsamiPropedeutici(codice);
//			String giorniLezione = currentCourse.getGiorno();
//			System.err.println(giorniLezione);
//			String[] giorni = giorniLezione.split("_");
//			ArrayList<HashMap<String, String>> lista = new ArrayList<>();
//			for (int i = 0; i < giorni.length; i++) {
//				System.out.println("giorni " + giorni[i]);
//			}
//			for (int i = 0; i < giorni.length; i++) {
//				String[] giorno = giorni[i].split(",");
//				HashMap<String, String> h = new HashMap<>();
//				h.put("giorno", giorno[0]);
//				h.put("dalle", giorno[1]);
//				h.put("alle", giorno[2]);
//				h.put("tipo", giorno[3]);
//				h.put("aula", giorno[4]);
//
//				lista.add(h);
//			}
//			for (HashMap<String, String> hashMap : lista) {
//				System.out.println(hashMap);
//			}
//			req.setAttribute("courseDocente", courseDocente);
//			req.setAttribute("esami", propedeutici);
//			req.setAttribute("currentCourse", currentCourse);
//			req.setAttribute("giorni",giorni);
//			req.setAttribute("studentiIscritti", studentiIscritti);
//			req.setAttribute("advices", avvisi);
//		}
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
			LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
			Lezione lez = lezioneDao.findByPrimaryKey(lezione);
			u.salvaPresenza(matricola, lezione);
		}
		if (richiesta.equals("salvaAvviso")) {
			String id = req.getParameter("id");
			if (!id.equals("new"))
				avvDao.delete(Long.parseLong(id));
			String text = req.getParameter("text");
			String title = req.getParameter("title");
			Timestamp t = new Timestamp(System.currentTimeMillis());
			NotificaDao notificaDao = DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();
			studentiIscritti = corsoDao.getStudentiIscritti(codice);
			for (Utente u : studentiIscritti) {
				notificaDao.save(new Notifica(u.getMatricola(), t, 3, currentCourse.getNome()));
			}
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