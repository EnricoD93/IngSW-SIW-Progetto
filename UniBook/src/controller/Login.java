package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.DatabaseManager;
import persistence.dao.StudenteDao;


public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ciao");
		HttpSession session = req.getSession();
		session.setAttribute("matricola", "");
		 String username = (String) session.getAttribute("matricola");

		// NON FUNZIONA

		 StudenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getStudenteDAO();
		 if (username.equals(studenteDao.findByPrimaryKey(username).getMatricola())) {
		 System.out.println("esiste");
		 } else
		 System.out.println("non esiste");
	}
	
}