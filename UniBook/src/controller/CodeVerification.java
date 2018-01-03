package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class CodeVerification extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String code = req.getParameter("verifyCode");
		String matricola = (String) session.getAttribute("userMatr");
		System.out.println(matricola);

		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		Utente u = utenteDao.findByPrimaryKey(matricola);
		System.out.println("codice nel database: " + u.getVerifyCode());
		System.out.println("codice nella form: " + code);
		if (u.getVerifyCode().equals(code)) {
			System.out.println("Registrazione effettuata con successo");
			//req.getRequestDispatcher("index.html").forward(req, resp);
		} else {
			System.out.println("codice sbagliato");
		}
	}
}
