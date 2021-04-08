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

import com.bank.dto.AccountDetailsMail;
import com.bank.resource.Template;

@WebServlet("/CheckBal")
public class CheckBal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accnum = req.getParameter("accnum");
		PrintWriter out = resp.getWriter();
		Connection conn = Template.getMySqlConnection();
		try {
			String sql = "select userimage,accnum,name,email,balance,status from banklog where accnum=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, accnum);
			ResultSet res = pstmnt.executeQuery();
			String profile = null;
			resp.setContentType("text/html");
			out.println("<table class='table table-bordered table-dark table-striped' target='activityframe'>");
			out.println("<caption>AVAILABLE BALANCE</caption>");
			String balance = null;
			while (res.next()) {
				balance = res.getString(5);
				Blob blob = res.getBlob(1);
				if (blob != null) {

					int len = (int) blob.length();
					byte[] imgBytes = blob.getBytes(1L, len);
					String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
					profile = "data:image/png;base64," + encodedImg;
					String color=null;
					String status=res.getString(6);
					if(status.equals("ACTIVATE"))
					{
					color="color:orange;";
					}
					else {
					color="color:red;";
					}
					out.println("<tr>");
					out.println("<td style='width:100px;margin-left:45px;'><img src='" + profile
							+ "' style='width:200px;height:200px;'><br><br><br><b> Account Number:</b><span> "
							+ res.getString(2) + "</span><br> <b>Name:</b><span> " + res.getString(3)
							+ "</span><br><b> Email Id:</b><span> " + res.getString(4)
							+ "</span><br><b> Status:</b><span style='"+color+"'> " + status
							+ "</span></td>");
					out.println("<td style='width:350px;'><b id='avl'>AVAILABLE BALANCE IS.<br><br></b><b id='bal'>Rs. "
							+ balance + " /-</b></td>");
					out.println("</tr>");
				}
			}
			out.println("</table>");
			String mailing = "select email from banklog where accnum=?";
			pstmnt = conn.prepareStatement(mailing);
			pstmnt.setString(1, accnum);
			ResultSet re = pstmnt.executeQuery();
			String email = null;
			while (re.next()) {
				email = re.getString(1);
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("checkbal.jsp");
			dispatcher.include(req, resp);
			String last4 = accnum.substring(accnum.length() - 4);
			String m = " ABC BANK YOUR A/C No.XXXX-XXXX-XXXX-" + last4 + "\n\n AVAILABLE BALANCE IS Rs. " + balance
					+ " /-.\n\n\nTHANK YOU FOR VISITING ABC BANK.";
			AccountDetailsMail mail = new AccountDetailsMail();
			mail.sendAccountDetailsEmail(email, "AVAILABLE BALANCE", m);



		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
