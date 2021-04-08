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

import com.bank.resource.Template;

@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accnum = req.getParameter("accnum");
		HttpSession session = req.getSession();
		session.setAttribute("acc", accnum);
		PrintWriter out = resp.getWriter();
		Connection conn = Template.getMySqlConnection();

		try {
			String sql = "select userimage,accnum,name,email,dob,mobile,gender,aadhar from banklog where accnum=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, accnum);
			ResultSet res = pstmnt.executeQuery();
			String profile = null;
			String name = null;
			resp.setContentType("text/html");
			out.println("<form action='UPDATEACDETAILS'>");
			out.println("<table class='table table-bordered table-dark table-striped' target='activityframe'>");
			out.println("<caption>Update A/C Details</caption>");
			while (res.next()) {
				name = res.getString(3);
				System.out.println(name);
				Blob blob = res.getBlob(1);
				if (blob != null) {

					int len = (int) blob.length();
					byte[] imgBytes = blob.getBytes(1L, len);
					String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
					profile = "data:image/png;base64," + encodedImg;
					out.println("<tr>");
					out.println("<td style='width:400px;margin-left:45px;'><img src='" + profile + "'></td>");
					out.println(
							"<td style='width:250px;'><br><br>ACCOUNT NUMBER :<br><br><br>NAME :<br><br><br>EMAIL ID :<br><br><br>DATE OF BIRTH :<br><br><br>MOBILE NO :<br><br><br>GENDER :<br><br><br>AADHAR NO.<br></td>");
					out.println("<td><br><input type='text' class='form-control search' name='accnumber' value="
							+ res.getString(2)
							+ " disabled><br><br><input type='text' class='form-control search' name='name' placeholder='Enter Name' id='getname' required='required'><br><br><input type='text' class='form-control search' placeholder='Enter a valid mail id' name='email' value="
							+ res.getString(4)
							+ " required='required'><br><br><input type='date' class='form-control search' placeholder='YYYY-MM-DD' name='dob' value="
							+ res.getString(5)
							+ " required='required'><br><br><input type='text' class='form-control search' placeholder='Enter Mobile Number' name='mobile' value="
							+ res.getString(6)
							+ " required='required'><br><br><input type='text' class='form-control search' name='gender' value="
							+ res.getString(7)
							+ " required='required'><br><br><input type='text' class='form-control search' placeholder='Enter aadhar Number' name='aadhar' value="
							+ res.getString(8) + " required='required'><br></td>");
					out.println("</tr>");
					out.println(
							"<tr><td colspan='4'><button type=\"submit\" class=\"btn btn-primary se\">UPDATE DETAILS</button></td>");
					out.println("</tr>");
				}
			}
			out.println("</table>");
			out.println("</form>");
			out.println("<script>document.getElementById('getname').value='" + name + "';</script>");
			RequestDispatcher dispatcher = req.getRequestDispatcher("updatecustomer.jsp");
			dispatcher.include(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
