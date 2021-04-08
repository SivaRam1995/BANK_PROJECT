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

@WebServlet("/SearchCustomer")
public class SearchCustomer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String serch = req.getParameter("accnum");
		resp.setContentType("text/html");
		try {
			Connection conn = Template.getMySqlConnection();
			String sql = "select * from banklog where accnum=? or name=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, serch);
			pstmnt.setString(2, serch);
			ResultSet res = pstmnt.executeQuery();

			String profile = null;
			out.println("<h1>CUSTOMER DETAILS</h1>");
			out.println("<table class='table table-bordered table-dark table-striped'>");
			out.println(
					"<tr><th>BANK NAME</th><th>ACCOUNT TYPE</th><th>ACCOUNT NUMBER</th><th>ACCOUNT HOLDER NAME</th><th>EMAIL ID</th><th>DATE OF BIRTH</th><th>MOBILE NO</th><th>GENDER</th><th>AADHAR NO.</th><th>NATIONALATY</th><th>STATUS</th><th>PHOTO</th></tr>");
			while (res.next()) {
				Blob blob = res.getBlob(6);
				if (blob != null) {

					int len = (int) blob.length();
					byte[] imgBytes = blob.getBytes(1L, len);
					String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
					profile = "data:image/png;base64," + encodedImg;
				}
				String color=null;
				String status=res.getString(17);
				if(status.equals("ACTIVATE"))
				{
				color="color:orange;";
				}
				else {
				color="color:red;";
				}

				out.println("<tr>");
				out.println("<td>" + res.getString(2) + "</td><td>" + res.getString(3) + "</td><td>" + res.getString(4)
						+ "</td><td>" + res.getString(7) + "</td><td>" + res.getString(10) + "</td><td>"
						+ res.getString(11) + "</td><td>" + res.getString(12) + "</td><td>" + res.getString(13)
						+ "</td><td>" + res.getString(14) + "</td><td>" + res.getString(15)
						+ "</td><td style='"+color+"'>" + status + "</td><td><img src='" + profile
						+ "' class='imgg'></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/myactivity.jsp");
			dispatcher.include(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
