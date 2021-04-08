package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.resource.Template;

@WebServlet("/WithdrawMoney")
public class WithdrawMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accnum = req.getParameter("accnum");
		PrintWriter out = resp.getWriter();
		Connection conn = Template.getMySqlConnection();

		try {
			String sql = "select userimage,accnum,name,email,status from banklog where accnum=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, accnum);
			ResultSet res = pstmnt.executeQuery();
			String profile = null;
			resp.setContentType("text/html");
			out.println("<form action='WITHDRAW' required='required'>");
			out.println("<table class='table table-bordered table-dark table-striped' target='activityframe'>");
			out.println("<caption>MONEY WITHDRAW</caption>");
			while (res.next()) {
				Blob blob = res.getBlob(1);
				if (blob != null) {

					int len = (int) blob.length();
					byte[] imgBytes = blob.getBytes(1L, len);
					String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
					profile = "data:image/png;base64," + encodedImg;
					String color=null;
					String status=res.getString(5);
					if(status.equals("ACTIVATE"))
					{
					color="color:orange;";
					}
					else {
					color="color:red;";
					}
					out.println("<tr>");
					out.println("<td style='width:350px;margin-left:45px;'><img src='" + profile
							+ "' style='width:200px;height:200px;'><br><br><br><b> Account Number:</b><span> "
							+ res.getString(2) + "</span><br> <b>Name:</b><span> " + res.getString(3)
							+ "</span><br><b> Email Id:</b><span> " + res.getString(4)
							+ "</span><br><b> Status:</b><span style='"+color+"'> " + status
							+ "</span></td>");
					out.println(
							"<td style='width:280px;'><br><br><br>ACCOUNT NUMBER :<br><br><br><br><br>ENTER AMOUNT :</td><td><br><br><br><input type=\"number\" placeholder=\"Enter Account Number \" value="
									+ accnum + " class=\"form-control search\" name=\"accnum\" required=\"required\">");
					out.println(
							"<br><br><br><input type=\"number\" placeholder=\"Enter Amount \" id='amo' class=\"form-control search\" name=\"amt\" required=\"required\"></td>");
					out.println("</tr>");
					out.println(
							"<tr><td colspan='4'><button type=\"submit\" class=\"btn btn-primary se\" id='mySubmit' onclick='myFunction()'>WITHDRAW MONEY</button></td>");

					out.println("</tr>");
				}
			}
			out.println("</table>");
			out.println("</form>");
			RequestDispatcher dispatcher = req.getRequestDispatcher("withdraw.jsp");
			dispatcher.include(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
