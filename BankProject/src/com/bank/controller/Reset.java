package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dto.AccountDetailsMail;
import com.bank.resource.Template;

@WebServlet("/Reset")
public class Reset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		String logid = (String) session.getAttribute("logid");
		String email = (String) session.getAttribute("email");
		Connection conn = Template.getMySqlConnection();

		try {
			String sql = "update banklog set password=? where loginid=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, pass);
			pstmnt.setString(2, logid);
			int i = pstmnt.executeUpdate();
			if (i == 1) {
				String m = "Account Details\n\n\nLogin Id: " + logid + "\n\nPassword: " + pass
						+ "\n\n Keep The Details With You.";
				AccountDetailsMail mail = new AccountDetailsMail();
				mail.sendAccountDetailsEmail(email, "ABC Online BankAccount Password Changed Successfull", m);

				out.println("<script>");
				out.println("alert('Password Changed Successfull Now You Are Ready To LogIn')");
				out.println("location.href='login.jsp'");
				out.println("</script>");

			} else {
				out.println("<script>");
				out.println("alert('Please Enter Valid Inputs')");
				out.println("location.href='forgotpassword.html'");
				out.println("</script>");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
