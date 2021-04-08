package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerifyForgotPass
 */
@WebServlet("/VerifyForgotPass")
public class VerifyForgotPass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String otp = req.getParameter("otp");
		System.out.println(otp);
		HttpSession session = req.getSession();
		String genrateOTP = (String) session.getAttribute("genrateOTP");

		if (genrateOTP.equals(otp)) {
			out.println("<script>");
			out.println("alert('OTP Matched Now You Can Change Your Password')");
			out.println("location.href='forgotpassword.html'");
			out.println("</script>)");
		} else {
			out.println("<script>");
			out.println("alert('Your are Enter Wrong OTP Please Try Again...')");
			out.println("location.href='changepass.html'");
			out.println("</script>)");

		}

	}

}
