package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.BankDaoImp;
import com.bank.dao.SendMail;
import com.bank.dto.CheckStatusAD;
import com.bank.dto.GetMailId;

@WebServlet("/OnlineReg")
public class OnlineReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String accnum = req.getParameter("accnum");
		HttpSession session = req.getSession();
		session.setAttribute("acc", accnum);
		CheckStatusAD check = new CheckStatusAD();
		boolean status = false;
		try {
			status = check.check(accnum);
		} catch (NullPointerException npx) {
			out.println("<script>");
			out.println("alert('Your MySQl DataBase not Yet Connected Please Connect To Your Dadabse')");
			out.println("location.href='onlinereg.jsp'");
			out.println("</script>)");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (status == false) {
			out.println("<script>");
			out.println("alert('YOUR ACCOUNT WAS DEACTIVATED CONSULT BANK AND RE-ACTIVATE YOUR A/C');");
			out.println("location.href='onlinereg.jsp'");
			out.println("</script>)");

		} else {
			BankDaoImp dao = new BankDaoImp();
			String genrateOTP = dao.genrateOTP();
			session.setAttribute("genrateOTP", genrateOTP);
			SendMail mail = new SendMail();
			GetMailId getEmail = new GetMailId();
			String email = getEmail.getMailId(accnum);
			mail.sendEmail(email, genrateOTP);
			RequestDispatcher dispatcher = req.getRequestDispatcher("onlineregOTP.jsp");
			dispatcher.include(req, resp);
		}

	}
}
