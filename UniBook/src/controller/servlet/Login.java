package controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.course.Corso;
import model.user.Notifica;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.NotificaDao;
import persistence.dao.UtenteDao;

public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9139149609635447743L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("currentUser") != null) {
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			Utente currentUser = (Utente) session.getAttribute("currentUser");
			List<Corso> corsi = utenteDao.getCorsi(currentUser.getMatricola());
			req.setAttribute("corsi", corsi);

			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();

			int size = calendarioPersonaleDao.findAllEventsUtente(currentUser.getMatricola()).size();
			req.setAttribute("size", size);
			System.out.println(size);
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		} else
			req.getRequestDispatcher("index.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		RequestDispatcher dispacher;
		List<Corso> corsi;

		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		if (session.getAttribute("currentUser") == null) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Utente currentUser = utenteDao.findByPrimaryKey(username);

			if (currentUser != null) {
				if (password.equals(utenteDao.findByPrimaryKey(username).getPassword())) {
					NotificaDao notiDao=DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();
					List<Notifica> notifiche=notiDao.findAllNotifications(currentUser.getMatricola());
					corsi = utenteDao.getCorsi(currentUser.getMatricola());
					session.setAttribute("currentUser", currentUser);
					session.setAttribute("notifications", notifiche);
				} else {
					resp.setStatus(405);
				}
			} else {
				resp.setStatus(405);
			}
		}

	}

}