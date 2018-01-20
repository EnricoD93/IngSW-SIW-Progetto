package controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class NotificationManager extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String utente = req.getParameter("user");
		String request = req.getParameter("request");
		if (request.equals("message")) {
			UtenteDao udao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			int unread = udao.findUnreadMessages(utente);
			JSONObject messages=new JSONObject();
			try {
				messages.put("number", unread);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.setContentType("application/json");
			resp.getWriter().print(messages);
		}
		if (request.equals("notification"))
			;
	}
}
