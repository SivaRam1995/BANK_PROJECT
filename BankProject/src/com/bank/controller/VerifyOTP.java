package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dto.AccountDetailsMail;
import com.bank.resource.Template;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

@WebServlet("/VerifyOTP")
@MultipartConfig(maxFileSize = 569999999)
public class VerifyOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String otp = req.getParameter("otp");
		System.out.println(otp);
		HttpSession session = req.getSession();
		String genrateOTP = (String) session.getAttribute("genrateOTP");
		int pin = (int) (session.getAttribute("pin"));
		String accnum = (String) session.getAttribute("accnum");
		String logid = (String) session.getAttribute("logid");
		String pass = (String) session.getAttribute("pass");
		String email = (String) session.getAttribute("email");

		try {

			if (genrateOTP.equals(otp)) {
				int i = 0;
				Connection conn = Template.getMySqlConnection();

				String sql = "update `banklog` set `pin`=?, `loginid`=?,`password`=?, email=? where accnum=?";
				pstmnt = conn.prepareStatement(sql);
				pstmnt.setInt(1, pin);
				pstmnt.setString(2, logid);
				pstmnt.setString(3, pass);
				pstmnt.setString(4, email);
				pstmnt.setString(5, accnum);
				i = pstmnt.executeUpdate();

				if (i == 1) {
					String m = "Account Details\n\n\nAccount Number: " + accnum + "\n\nEmail Id: " + email
							+ "\n\nPin No: " + pin + "\n\nlogin id: " + logid + "\n\nPassword: " + pass
							+ "\n\n Keep The Details With You.";
					System.out.println(m);
					AccountDetailsMail mail = new AccountDetailsMail();
					mail.sendAccountDetailsEmail(email, "ABC Online BankAccount Created Successfull", m);

					out.println("<script>");
					out.println("alert('Successfully Created An Account You are ready to login. WELCOME ABC ONLINE BANKING')");
					out.println("location.href='login.jsp'");
					out.println("</script>)");
					Template.closeConnection();
				} else {
					out.println("<script>");
					out.println("alert('account creation failed.......please try again.')");
					out.println("location.href='bankregister.jsp'");
					out.println("</script>)");
					Template.closeConnection();
				}

			} else {
					out.println("<script>");
					out.println("alert('Your are Enter Wrong OTP.')");
					out.println("location.href='bankregister.jsp'");
					out.println("</script>)");
				}

			

		} catch (MySQLNonTransientConnectionException | NullPointerException mysqlnon) {
			out.println("<script>Your MySQl DataBase not Yet Connected Please Connect To Your Dadabse</script>");

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
