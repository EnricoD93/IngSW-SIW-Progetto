package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		System.out.println("username:" + username);
		System.out.println("password:" + password);


		StudenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getStudenteDAO();
		System.out.println(studenteDao.findByPrimaryKey(username).getMatricola());
		System.out.println(username);
		if (username.equals(studenteDao.findByPrimaryKey(username).getMatricola())) {
			System.out.println("esiste");
		} else
			System.out.println("non esiste");
	}

}