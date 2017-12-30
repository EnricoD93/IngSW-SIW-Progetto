package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SUCA");
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		session.removeAttribute("currentUser");
		session.invalidate();
		RequestDispatcher rd = req.getRequestDispatcher("index.html");
		rd.forward(req, resp);
	}
}
