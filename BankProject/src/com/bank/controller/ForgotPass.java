package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.BankDaoImp;
import com.bank.resource.Template;

@WebServlet("/ForgotPass")
public class ForgotPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = null;
		String logid = request.getParameter("user");
		Connection conn = Template.getMySqlConnection();
		try {
			String sql = "select email from banklog where loginid=?";
			pstmnt = conn.prepareStatement(sql);

			pstmnt.setString(1, logid);
			ResultSet res = pstmnt.executeQuery();

			while (res.next()) {
				email = res.getString(1);
			}
			if(email==null)
			{
				out.println("<script>");
				out.println("alert('Please Enter a Valid Login Id')");
				out.println("location.href='changepass.html'");
				out.println("</script>)");

			}
			else {

			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("logid", logid);
			BankDaoImp dao = new BankDaoImp();
			String genrateOTP = dao.genrateOTP();
			System.out.println(genrateOTP);
			session.setAttribute("genrateOTP", genrateOTP);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SendForgotMail");
			dispatcher.forward(request, response);
			}

		}
		catch (NullPointerException npx) {
			out.println("<script>");
			out.println("alert('Your MySQl DataBase not Yet Connected Please Connect To Your Dadabse')");
			out.println("location.href='changepass.html'");
			out.println("</script>)");

		}catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

	}

}
