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

import com.bank.resource.Template;

@WebServlet("/SearchStatement")
public class SearchStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Connection conn = Template.getMySqlConnection();
		String accnum = req.getParameter("accnum");
		String date = req.getParameter("searchdate");
		resp.setContentType("text/html");
		try {
			String sql=null;
			if(date.length()==0)
			{
			sql = "select * from abc_bank_transaction where fromAccNum=? or TransactionDate=? ";
			}
			else if(accnum==null){
				sql = "select * from abc_bank_transaction where fromAccNum=? or TransactionDate=? ";
			}
			else if(date.length()==0 && accnum!=null){
				sql = "select * from abc_bank_transaction where fromAccNum=? and TransactionDate=? ";
				
			}
			int i = 0;
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, accnum);
			pstmnt.setString(2, date);
			ResultSet result = pstmnt.executeQuery();
			out.println(
					"<input type=\"button\" id=\"btnExport\" class='btn btn-success' value=\"Export\" onclick=\"Export()\" />");
			out.println("<h1>ALL TRANSACTION DETAILS</h1>");
			out.println("<table  id='ReportTable' class=\"table table-bordered table-dark table-striped \">");
			out.println("<caption>Transaction Statement Details</caption>");
			out.println("<tr><th>No. </th><th>Date</th><th>Transaction A/C Num</th><th>Amount</th><th>C/D</th></tr>");
			while (result.next()) {
				i = i + 1;
				out.println("<tr><td>" + i + "</td><td>" + result.getString(2) + "</td><td>" + result.getString(3)
						+ "</td><td>" + result.getDouble(5) + "</td><td>" + result.getString(6) + "</td></tr>");

			}
			out.println("</table>");
			i = 0;

			out.println(
					"<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js\"></script>");
			out.println(
					"<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js\"></script>");
			out.println("<script type=\"text/javascript\">\r\n" + "        function Export() {\r\n"
					+ "            html2canvas(document.getElementById('ReportTable'), {\r\n"
					+ "                onrendered: function (canvas) {\r\n"
					+ "                    var data = canvas.toDataURL();\r\n"
					+ "                    var docDefinition = {\r\n" + "                        content: [{\r\n"
					+ "                            image: data,\r\n" + "                            width: 500\r\n"
					+ "                        }]\r\n" + "                    };\r\n"
					+ "                    pdfMake.createPdf(docDefinition).download(\"ABCBANK_STATEMENT.pdf\");\r\n"
					+ "                }\r\n" + "            });\r\n" + "        }\r\n" + "    </script>");

			RequestDispatcher dispatcher = req.getRequestDispatcher("statement.jsp");
			dispatcher.include(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
