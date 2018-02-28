package controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.course.Aula;
import model.course.Corso;
import model.course.DescrizioneCorso;
import model.user.Notifica;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.DescrizioneCorsoDao;
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
		String request = (String) req.getParameter("request");
		if (request != null && request.equals("media")) {
			DescrizioneCorsoDao cdao = DatabaseManager.getInstance().getDaoFactory().getDescrizioneCorsoDao();
			List<DescrizioneCorso> esamiI = cdao.findByCDL("0733");
			List<DescrizioneCorso> esamiM = cdao.findByCDL("0726");
			req.setAttribute("esamiI", esamiI);
			req.setAttribute("esamiM", esamiM);
		} else if (session.getAttribute("currentUser") != null) {
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			Utente currentUser = (Utente) session.getAttribute("currentUser");
			List<Corso> corsi = utenteDao.getCorsi(currentUser.getMatricola());
			req.setAttribute("corsi", corsi);

			CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
					.getCalendarioPersonaleDAO();

			int size = calendarioPersonaleDao.findAllEventsUtente(currentUser.getMatricola()).size();
			req.setAttribute("size", size);
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		} else
			req.getRequestDispatcher("login").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		if (session.getAttribute("currentUser") == null) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Utente currentUser = utenteDao.findByPrimaryKey(username);

			if (currentUser != null) {
				if (password.equals(utenteDao.findByPrimaryKey(username).getPassword())) {
					NotificaDao notiDao = DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();
					CorsoDao corsiDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
					AulaDao aulaDao = DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
					List<Notifica> notifiche = notiDao.findAllNotifications(currentUser.getMatricola());
					List<Utente> tuttiutenti = utenteDao.findAll();
					List<Corso> tutticorsi = corsiDao.findAll();
					List<Aula> tutteaule = aulaDao.findAll();
					session.setAttribute("currentUser", currentUser);
					session.setAttribute("tutteaule", tutteaule);
					session.setAttribute("tuttiutenti", tuttiutenti);
					session.setAttribute("tutticorsi", tutticorsi);
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