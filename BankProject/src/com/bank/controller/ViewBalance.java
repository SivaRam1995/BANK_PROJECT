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

import com.bank.resource.Template;

@WebServlet("/viewBalance")
public class ViewBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Connection conn = Template.getMySqlConnection();
		HttpSession session = req.getSession();
		String loginid = (String) session.getAttribute("logid");

		try {
			resp.setContentType("text/html");
			String sql = "select name,balance from banklog where loginid=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, loginid);
			ResultSet res = pstmnt.executeQuery();
			String name = null;
			double amount = 0;
			while (res.next()) {
				name = res.getString(1);
				amount = res.getDouble(2);

			}
			session.setAttribute("debitedmoney", amount);

			out.println(" <div>\r\n" + "        <div class=\"card left\" style=\"width: 25rem;\" id=\"show\">\r\n"
					+ "            <h1>Hello, " + name + "</h1><hr><br><h1>Your Balance is:</h1><hr>\r\n"
					+ "            <h1 id='rupee'>Rs. " + amount + "/-</h1>\r\n" + "          </div>      \r\n"
					+ "    </div>\r\n" + "   ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("balance.jsp");
		dispatcher.include(req, resp);
	}
}
