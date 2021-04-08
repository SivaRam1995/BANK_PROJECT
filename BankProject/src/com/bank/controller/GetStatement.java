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

/**
 * Servlet implementation class GetStatement
 */
@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		Connection conn = Template.getMySqlConnection();
		HttpSession session = req.getSession();
		String accnum = (String) session.getAttribute("accnum");
		try {
			int i = 0;
			String sql = "select * from abc_bank_transaction where fromAccNum=? ";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, accnum);
			ResultSet result = pstmnt.executeQuery();
			out.println("<h1>ALL TRANSACTION DETAILS</h1>");
			out.println("<table class=\"table table-bordered table-dark table-striped \">");
			out.println("<caption>All Transaction Details</caption>");
			out.println("<tr><th>No. </th><th>Date</th><th>Transaction A/C Num</th><th>Amount</th><th>C/D</th></tr>");
			while (result.next()) {
				i = i + 1;
				out.println("<tr><td>" + i + "</td><td>" + result.getString(2) + "</td><td>" + result.getString(4)
						+ "</td><td>" + result.getDouble(5) + "</td><td>" + result.getString(6) + "</td></tr>");

			}
			out.println("</table>");
			i = 0;
			RequestDispatcher dispatcher = req.getRequestDispatcher("statement.jsp");
			dispatcher.include(req, res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
