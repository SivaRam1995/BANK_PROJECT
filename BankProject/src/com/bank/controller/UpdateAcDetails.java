package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dto.AccountDetailsMail;
import com.bank.resource.Template;

@WebServlet("/UPDATEACDETAILS")
public class UpdateAcDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String accnum = (String) session.getAttribute("acc");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");
		String mobile = req.getParameter("mobile");
		String gender = req.getParameter("gender");
		String aadhar = req.getParameter("aadhar");
		Connection conn = Template.getMySqlConnection();
		try {
			String update = "update banklog set name=?,email=?,dob=?,mobile=?,gender=?,aadhar=? where accnum=?";
			pstmnt = conn.prepareStatement(update);
			pstmnt.setString(1, name);
			pstmnt.setString(2, email);
			pstmnt.setString(3, dob);
			pstmnt.setString(4, mobile);
			pstmnt.setString(5, gender);
			pstmnt.setString(6, aadhar);
			pstmnt.setString(7, accnum);
			int i = pstmnt.executeUpdate();
			if (i == 1) {
				String m = "Updated Account Details\n\n\nName: " + name + "\n\nAccount Number: " + accnum
						+ "\n\nEmail Id: " + email + "\n\nMobile No: " + mobile + "\n\ngender: " + gender
						+ "\n\nAadhar No: " + aadhar
						+ "\n\n Keep The Details With You At The Time Of Creating Online A/C This Fields Are Manditory.";
				System.out.println(m);
				AccountDetailsMail mail = new AccountDetailsMail();
				mail.sendAccountDetailsEmail(email, "ABC BankAccount Details Updated Successfull", m);

				out.println("<script>");
				out.println("alert('ACCOUNT DETAILS UPDATED SUCCESSFULL')");
				out.println("location.href='updatecustomer.jsp'");
				out.println("</script>)");

			} else {
				out.println("<script>");
				out.println("alert('Failed.. Please Try Again')");
				out.println("location.href='updatecustomer.jsp'");
				out.println("</script>)");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
