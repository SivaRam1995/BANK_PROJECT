package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.SendMail;

@WebServlet("/SendForgotMail")
public class SendForgotMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String otp = (String) session.getAttribute("genrateOTP");
		String email = (String) session.getAttribute("email");
		SendMail mail = new SendMail();
		mail.sendEmail(email, otp);
		out.println("<script>");
		out.println("location.href='forgot_otp.html'");
		out.println("</script>)");

	}

}
