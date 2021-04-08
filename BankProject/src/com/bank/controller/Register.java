package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.BankDaoImp;
import com.bank.dto.CheckLoginId;
import com.bank.resource.Template;

@WebServlet("/Register")
@MultipartConfig(maxFileSize = 569999999)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pstmnt = null;
	private static ResultSet res = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String accnum = (String) session.getAttribute("acc");
		int pin = Integer.parseInt(req.getParameter("pin"));
		String logid = req.getParameter("logid");
		String pass = req.getParameter("pwd");
		String email = req.getParameter("email");
		session.setAttribute("accnum", accnum);
		session.setAttribute("pin", pin);
		session.setAttribute("logid", logid);
		session.setAttribute("pass", pass);
		session.setAttribute("email", email);
		try {
			String pinn = null;
			String loginid = null;
			String password = null;
			Connection conn = Template.getMySqlConnection();
			String check = "select pin,loginid,password from banklog where accnum=?";
			pstmnt = conn.prepareStatement(check);
			pstmnt.setString(1, accnum);
			res = pstmnt.executeQuery();
			while (res.next()) {
				pinn = res.getString(1);
				loginid = res.getString(2);
				password = res.getString(3);

			}
			if (pinn == null && loginid == null && password == null) {
				System.out.println(logid);
				CheckLoginId checklogid = new CheckLoginId();
				boolean logid_status = checklogid.checkingLogId("emplogin", logid);
				boolean log_id = checklogid.checkingLogId("banklog", logid);
				if (logid_status == true | log_id == true) {
					out.println("<script>");
					out.println("alert('You are Login Id Invalid. Please Try Again With Another Login Id.')");
					out.println("location.href='bankregister.jsp'");
					out.println("</script>)");
				} else {
					BankDaoImp dao = new BankDaoImp();
					String genrateOTP = dao.genrateOTP();
					System.out.println(genrateOTP);
					session.setAttribute("genrateOTP", genrateOTP);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/SendEmail");
					dispatcher.forward(req, resp);
				}
			} else {
				out.println("<script>");
				out.println("alert('You are already registered ABC ONLINE BANKING.')");
				out.println("location.href='bankregister.jsp'");
				out.println("</script>)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
