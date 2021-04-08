package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.BankDaoImp;
import com.bank.resource.Template;

@WebServlet("/BankLogin")
public class BankLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement stmnt = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String name = req.getParameter("user");
		String pass = req.getParameter("pass");
		HttpSession session = req.getSession();
		session.setAttribute("logid", name);
		session.setAttribute("pass", pass);
		BankDaoImp dao = new BankDaoImp();
		Boolean login = null;
		try {
			login = dao.bankLogin(name, pass);
		} catch (NullPointerException npx) {
			out.println("<script>");
			out.println("alert('Your MySQl DataBase not Yet Connected Please Connect To Your Dadabse')");
			out.println("location.href='abcbanklogin.jsp'");
			out.println("</script>)");

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Connection conn = Template.getMySqlConnection();

		resp.setContentType("text/html");

		if (login) {
			try {
				String sql = "select * from emplogin where loginid=?";
				stmnt = conn.prepareStatement(sql);
				stmnt.setString(1, name);
				ResultSet res = stmnt.executeQuery();
				while (res.next()) {
					String empname = res.getString(2);
					String loginid = res.getString(3);
					String password = res.getString(4);

					Blob blob = res.getBlob(5);
					String photo = null;
					if (blob != null) {

						int len = (int) blob.length();
						byte[] imgBytes = blob.getBytes(1L, len);
						String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
						photo = "data:image/png;base64," + encodedImg;
					}
					String dob = res.getString(6);
					String email = res.getString(7);
					String mobile = res.getString(8);
					String aadhar = res.getString(9);
					String gender = res.getString(10);
					String nationality = res.getString(11);
					String designation = res.getString(12);
					session.setAttribute("name", empname);
					session.setAttribute("loginid", loginid);
					session.setAttribute("pass", password);
					session.setAttribute("photo", photo);
					session.setAttribute("dob", dob);
					session.setAttribute("email", email);
					session.setAttribute("mobile", mobile);
					session.setAttribute("aadhar", aadhar);
					session.setAttribute("gender", gender);
					session.setAttribute("nationality", nationality);
					session.setAttribute("designation", designation);
				}

				out.println("<script>");
				/* out.println("alert('LoginSuccess')"); */
				out.println("window.parent.location.href='welcomeemployee.jsp'");
				out.println("</script>)");

			} catch (NullPointerException npx) {
				out.println("<script>");
				out.println("alert('Your MySQl DataBase not Yet Connected Please Connect To Your Dadabse')");
				out.println("location.href='login.jsp'");
				out.println("</script>)");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			out.println("<script>");
			out.println("alert('Wrong Credentials')");
			out.println("location.href='abcbanklogin.jsp'");
			out.println("</script>)");

		}

	}

}
