package controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.user.Messaggio;
import model.user.Notifica;
import persistence.DatabaseManager;
import persistence.dao.MessaggioDao;
import persistence.dao.NotificaDao;
import persistence.dao.UtenteDao;

public class NotificationManager extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String utente = req.getParameter("user");
		String request = req.getParameter("request");
		if (request.equals("message")) {
			UtenteDao udao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			int unread = udao.findAllUnreadMessages(utente);
			JSONObject messages = new JSONObject();
			try {
				messages.put("number", unread);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.setContentType("application/json");
			resp.getWriter().print(messages);
		}
		if (request.equals("newmessage")) {
			UtenteDao udao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			MessaggioDao mdao = DatabaseManager.getInstance().getDaoFactory().getMessaggioDAO();
			String mitt = req.getParameter("mitt");
			List<Messaggio> messages = udao.findAllUnreadMessages(utente, mitt);
			JSONArray array = new JSONArray();
			try {
				for (Messaggio messaggio : messages) {
					JSONObject message = new JSONObject();
					message.put("id", messaggio.getId());
					message.put("datar", messaggio.getDatareale());
					message.put("data", messaggio.getData());
					message.put("dest", messaggio.getDestinatario());
					message.put("mitt", messaggio.getMittente());
					message.put("text", messaggio.getTesto());
					array.put(message);
					mdao.updateUnreadMessages(messaggio.getId());

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.setContentType("application/json");
			resp.getWriter().print(array);
		}
		if (request.equals("notification")) {
			NotificaDao notiDao = DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();
			List<Notifica> notifiche = notiDao.findAllNotifications(utente);
			int unread = notiDao.findUnreadNotifications(utente);
			Notifica last = null;
			if (notifiche.size() > 0) {
				last = notifiche.get(0);
			}
			JSONObject messages = new JSONObject();
			JSONObject lastNotify = new JSONObject();
			JSONArray array = new JSONArray();
			try {
				messages.put("number", unread);
				if (last != null) {
					lastNotify.put("id", last.getId());
					lastNotify.put("type", last.getType());
					lastNotify.put("data", last.getDatareale());
					lastNotify.put("testo", last.getTesto());
				}
				array.put(messages);
				array.put(lastNotify);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.getSession().setAttribute("notifications", notifiche);
			req.getSession().setAttribute("newnotifications", unread);
			resp.setContentType("application/json");
			resp.getWriter().print(array);
		}
		if (request.equals("updateNotifications")) {
			NotificaDao notiDao = DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();
			notiDao.updateUnreadNotification(utente);
		}
	}
}
