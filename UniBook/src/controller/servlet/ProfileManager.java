package controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class ProfileManager extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utente currentUser = (Utente) req.getSession().getAttribute("currentUser");
		String request=req.getParameter("request");
		String inputPassword=req.getParameter("inputPassword");
		if(request.equals("modificaPassword")) {
			UtenteDao utenteDao=DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			utenteDao.passwordModify(currentUser.getMatricola(),inputPassword);
		}
	}
}
