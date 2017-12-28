package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Studente;
import persistence.DatabaseManager;
import persistence.dao.StudenteDao;

public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("matricola", null);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		RequestDispatcher dispacher=req.getRequestDispatcher("home.html");
		System.out.println("username:" + username);
		System.out.println("password:" + password);

		StudenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getStudenteDAO();
		// System.out.println(studenteDao.findByPrimaryKey(username).getMatricola());
		System.out.println(username);
		try {
			if (username.equals(studenteDao.findByPrimaryKey(username).getMatricola())) {
				System.out.println("esiste");
				dispacher.forward(req, resp);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			dispacher = req.getRequestDispatcher("index.html");
			dispacher.forward(req, resp);
		}

	}

}