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
import javax.servlet.http.HttpSession;

import com.bank.dto.CheckStatusAD;
import com.bank.resource.Template;

@WebServlet("/ChangeStatus")
public class CheckStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accnum = req.getParameter("accnum");
		HttpSession session = req.getSession();
		session.setAttribute("acc", accnum);
		PrintWriter out = resp.getWriter();
		Connection conn = Template.getMySqlConnection();

		try {
			String sql = "select userimage,accnum,name,email,dob,mobile,gender,aadhar,nationality,balance,status from banklog where accnum=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, accnum);
			ResultSet res = pstmnt.executeQuery();
			String profile = null;
			resp.setContentType("text/html");
			CheckStatusAD checkStatusAD = new CheckStatusAD();
			boolean status = checkStatusAD.check(accnum);
			if (status == true) {
				out.println("<form action='DEACTIVE'>");
				out.println("<table class='table table-bordered table-dark table-striped' target='activityframe'>");
				out.println("<caption>Activate/Deactivate</caption>");
				while (res.next()) {
					Blob blob = res.getBlob(1);
					if (blob != null) {

						int len = (int) blob.length();
						byte[] imgBytes = blob.getBytes(1L, len);
						String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
						profile = "data:image/png;base64," + encodedImg;
						out.println("<tr>");
						out.println("<td style='width:400px;margin-left:45px;'><img src='" + profile + "'></td>");
						out.println(
								"<td><br>ACCOUNT NUMBER :<br><br>NAME :<br><br>EMAIL ID :<br><br>DATE OF BIRTH :<br><br>GENDER :<br><br>AADHAR NO.<br><br>NATIONALITY :<br><br>AVL BALANCE :<br><br><b style='color:orange;'>STATUS :</b></td>");
						out.println("<td><br><span> " + res.getString(2) + "</span><br><br><span> " + res.getString(3)
								+ "</span><br><br><span> " + res.getString(4) + "</span><br><br><span> "
								+ res.getString(5) + "</span><br><br><span>" + res.getString(7)
								+ "</span><br><br><span>" + res.getString(8) + "</span><br><br><span>"
								+ res.getString(9) + "</span><br><br><span>" + res.getString(10)
								+ "</span><br><br><span style='color:orange;'>" + res.getString(11) + "</span></td>");
						out.println("</tr>");
						out.println(
								"<tr><td colspan='4'><button type=\"submit\" class=\"btn btn-primary se\">DEACTIVATE A/C</button></td>");
						out.println("</tr>");
					}
				}
				out.println("</table>");
				out.println("</form>");
				RequestDispatcher dispatcher = req.getRequestDispatcher("a_dcustomer.jsp");
				dispatcher.include(req, resp);
			}

			else {
				out.println("<form action='ACTIVE'>");
				out.println("<table class='table table-bordered table-dark table-striped' target='activityframe'>");
				out.println("<caption>Activate/Deactivate</caption>");
				while (res.next()) {
					Blob blob = res.getBlob(1);
					if (blob != null) {

						int len = (int) blob.length();
						byte[] imgBytes = blob.getBytes(1L, len);
						String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
						profile = "data:image/png;base64," + encodedImg;
						out.println("<tr>");
						out.println("<td style='width:400px;margin-left:45px;'><img src='" + profile + "'></td>");
						out.println(
								"<td><br>ACCOUNT NUMBER :<br><br>NAME :<br><br>EMAIL ID :<br><br>DATE OF BIRTH :<br><br>GENDER :<br><br>AADHAR NO.<br><br>NATIONALITY :<br><br>AVL BALANCE :<br><br><b style='color:orange;'>STATUS :</b></td>");
						out.println("<td><br><span> " + res.getString(2) + "</span><br><br><span> " + res.getString(3)
								+ "</span><br><br><span> " + res.getString(4) + "</span><br><br><span> "
								+ res.getString(5) + "</span><br><br><span>" + res.getString(7)
								+ "</span><br><br><span>" + res.getString(8) + "</span><br><br><span>"
								+ res.getString(9) + "</span><br><br><span>" + res.getString(10)
								+ "</span><br><br><span style='color:red'>" + res.getString(11) + "</span></td>");
						out.println("</tr>");
						out.println(
								"<tr><td colspan='4'><button type=\"submit\" class=\"btn btn-primary se\">ACTIVATE A/C</button></td>");
						out.println("</tr>");
					}
				}
				out.println("</table>");
				out.println("</form>");
				RequestDispatcher dispatcher = req.getRequestDispatcher("a_dcustomer.jsp");
				dispatcher.include(req, resp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
