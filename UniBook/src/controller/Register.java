package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("username");
		String cognome = req.getParameter("userlastname");
		String matricola = req.getParameter("usermatr");
		String email = req.getParameter("email");
		String codicef = req.getParameter("codf");
		String password = req.getParameter("password");
		String ruolo= req.getParameter("role");
		System.out.println(nome + " " + cognome + " " + matricola+" "+email+" "+codicef+" "+password+" "+ruolo);
	}
}
