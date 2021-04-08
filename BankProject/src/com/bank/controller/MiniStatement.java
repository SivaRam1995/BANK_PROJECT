package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.resource.Template;

@WebServlet("/MiniStatement")
public class MiniStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		Connection conn = Template.getMySqlConnection();
		String accnum = req.getParameter("accnum");
		HttpSession session = req.getSession();
		session.setAttribute("acc", accnum);
		int stmnt = Integer.parseInt(req.getParameter("stmnt"));
		try {
			String getname = "select name,balance from banklog where accnum=?";
			pstmnt = conn.prepareStatement(getname);
			pstmnt.setString(1, accnum);
			ResultSet res = pstmnt.executeQuery();
			String myname = null;
			String bal = null;
			while (res.next()) {
				myname = res.getString(1);
				bal = res.getString(2);
			}
			int i = 0;
			String sql = "select * from abc_bank_transaction where fromAccNum=? ORDER BY id DESC LIMIT ?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, accnum);
			pstmnt.setInt(2, stmnt);
			ResultSet result = pstmnt.executeQuery();
			out.println("<form action='MailAttachment'>");
			out.println(
					"<input type=\"submit\" id=\"btnExport\" class='btn btn-success' value=\"Export\" onclick=\"Export()\" />");
			out.println("</form>");
			out.println("<h1>MINI STATEMENT OF LAST " + stmnt + " TRANSACTIONS</h1>");
			out.println("<table id='ReportTable' class=\"table table-bordered table-dark table-striped \">");
			out.println("<caption>My Name: " + myname + "<br>Account Number: " + accnum + "<br>Available Balance . Rs. "
					+ bal + " /-<br>Mini Statement</caption>");
			out.println("<tr><th>No. </th><th>Date</th><th>Transaction A/C Num</th><th>Amount</th><th>C/D</th></tr>");
			while (result.next()) {
				i = i + 1;
				out.println("<tr><td>" + i + "</td><td>" + result.getString(2) + "</td><td>" + result.getString(4)
						+ "</td><td>" + result.getLong(5) + "</td><td>" + result.getString(6) + "</td></tr>");

			}
			out.println("</table>");
			i = 0;
			String ran = new DecimalFormat("0000").format(new Random().nextInt(9999));
			String output = "ABCBANK_STATEMENT" + ran;
			session.setAttribute("filenamee", output);
			out.println(
					"<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js\"></script>");
			out.println(
					"<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js\"></script>");
			out.println("<script type=\"text/javascript\">\r\nvar i=0;" + "        function Export() {\r\ni=i+1;"
					+ "            html2canvas(document.getElementById('ReportTable'), {\r\n"
					+ "                onrendered: function (canvas) {\r\n"
					+ "                    var data = canvas.toDataURL();\r\n"
					+ "                    var docDefinition = {\r\n" + "                        content: [{\r\n"
					+ "                            image: data,\r\n" + "                            width: 500\r\n"
					+ "                        }]\r\n" + "                    };\r\n"
					+ "                    pdfMake.createPdf(docDefinition).download(\"" + output + ".pdf\");\r\n"
					+ "                }\r\n" + "            });\r\n" + "        }\r\n" + "    </script>");

			RequestDispatcher dispatcher = req.getRequestDispatcher("ministatement.jsp");
			dispatcher.include(req, resp);

		}
 catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
