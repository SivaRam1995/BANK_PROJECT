package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/VerifyOnlineReg")
public class VerifyOnlineReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String otp = req.getParameter("otp");
		System.out.println(otp);
		HttpSession session = req.getSession();
		String genrateOTP = (String) session.getAttribute("genrateOTP");

		if (genrateOTP.equals(otp)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("bankregister.jsp");
			dispatcher.include(req, resp);

		} else {
			out.println("<script>");
			out.println("alert('Your are Enter Wrong OTP.')");
			out.println("location.href='onlineregOTP.jsp'");
			out.println("</script>)");
		}

	}

}
