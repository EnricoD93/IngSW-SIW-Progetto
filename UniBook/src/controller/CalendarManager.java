package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.GiornoCalendario;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.CalendarioPersonaleDao;

public class CalendarManager extends HttpServlet {

	String request;
	String matricola;
	List<GiornoCalendario> listaEventi;
	Utente currentUser;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject result=new JSONObject();
		JSONArray listaJson= new JSONArray();
		HttpSession session = req.getSession();
		request = req.getParameter("request");
		matricola=req.getParameter("matricola");
		if(request.equals("Eventi")) {
			CalendarioPersonaleDao calendarioPersonaleDao= DatabaseManager.getInstance().getDaoFactory().getCalendarioPersonaleDAO();
		//	listaEventi=calendarioPersonaleDao.findByPrimaryKey(matricola).getEventi();
			for(int i=0; i<listaEventi.size();i++) {
			JSONObject evento=new JSONObject();
			try {
				evento.put("title", "titoloprova");
				evento.put("anno", listaEventi.get(i).getAnno());
				evento.put("mese", listaEventi.get(i).getMese());
				evento.put("giorno", listaEventi.get(i).getGiorno() );
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

	}
}
