package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.BankDaoImp;
import com.bank.dto.VerifyActiveStatus;
import com.bank.resource.Template;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement stmnt = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String name = req.getParameter("user");
		String pass = req.getParameter("pass");
		HttpSession session = req.getSession();
		session.setAttribute("logid", name);
		session.setAttribute("pass", pass);
		VerifyActiveStatus activeStatus = new VerifyActiveStatus();
		boolean checking;
		try {
			checking = activeStatus.checkActive(name, pass);
			if (checking == false) {
				out.println("<script>");
				out.println("alert('Your Account Deactivated. Please Consult Bank And Re-Activate Your Account.')");
				out.println("location.href='login.jsp'");
				out.println("</script>)");
			} else {
				BankDaoImp dao = new BankDaoImp();
				Boolean login = dao.login(name, pass);
				Connection conn = Template.getMySqlConnection();
				resp.setContentType("text/html");

				if (login) {
					try {
						String sql = "select accnum,email,pin from banklog where loginid=?";
						stmnt = conn.prepareStatement(sql);
						stmnt.setString(1, name);
						ResultSet res = stmnt.executeQuery();
						while (res.next()) {
							String accnum = res.getString(1);
							String email = res.getString(2);
							String pin=res.getString(3);
							session.setAttribute("accnum", accnum);
							session.setAttribute("email", email);
							session.setAttribute("pin", pin);
						}

						out.println("<script>");
						// out.println("alert('LoginSuccess')");
						out.println("window.parent.location.href='welcome.jsp'");
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
					out.println("location.href='login.jsp'");
					out.println("</script>)");

				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NullPointerException npx) {
			out.println("<script>");
			out.println("alert('Wrong Credentials')");
			out.println("location.href='login.jsp'");
			out.println("</script>)");

		}

	}

}
