package com.bank.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bank.dto.AccountDetailsMail;
import com.bank.resource.Template;

@WebServlet("/CustomerRegister")
@MultipartConfig(maxFileSize = 569999999)
public class CustomerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pstmnt = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String bankname = req.getParameter("bankname");
		String typeacc = req.getParameter("acctype");
		Random rand = new Random();
		String accnum = String.format((Locale) null, "11%02d%04d%04d", rand.nextInt(100), rand.nextInt(10000),
				rand.nextInt(10000));
		System.out.println(accnum);
		InputStream inputStream = null;
		Part filePart = req.getPart("userpic");
		if (filePart != null) {
			inputStream = filePart.getInputStream();
		}

		String fname = req.getParameter("fname").toUpperCase();
		String lname = req.getParameter("lname").toUpperCase();
		String name = fname + " " + lname;
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");
		String mobile = req.getParameter("mobile");
		String gender = req.getParameter("gender");
		String aadhar = req.getParameter("aadhar");
		String nationality = req.getParameter("nationality");

		Connection conn = Template.getMySqlConnection();

		try {
			String check = "select `accnum` from `banklog` where accnum=? or aadhar=?";
			pstmnt = conn.prepareStatement(check);
			pstmnt.setString(1, accnum);
			pstmnt.setString(2, aadhar);
			ResultSet res = pstmnt.executeQuery();
			if (res.next()) {

				out.println("<script>");
				out.println("alert('You Are Already Registerd.')");
				out.println("location.href='createcustomer.jsp'");
				out.println("</script>)");

			} else {

				String sql = "insert into `banklog`(`bankname`, `acctype`, `accnum`, `userimage`, `name`, `email`, `dob`, `mobile`, `gender`, `aadhar`,`nationality`) "
						+ " values(?,?,?,?,?,?,?,?,?,?,?)";
				pstmnt = conn.prepareStatement(sql);
				pstmnt.setString(1, bankname);
				pstmnt.setString(2, typeacc);
				pstmnt.setString(3, accnum);
				pstmnt.setBlob(4, inputStream);
				pstmnt.setString(5, name);
				pstmnt.setString(6, email);
				pstmnt.setString(7, dob);
				pstmnt.setString(8, mobile);
				pstmnt.setString(9, gender);
				pstmnt.setString(10, aadhar);
				pstmnt.setString(11, nationality);
				pstmnt.executeLargeUpdate();
				session.setAttribute("newbankname", bankname);
				session.setAttribute("newtypeacc", typeacc);
				session.setAttribute("newaccnum", accnum);
				session.setAttribute("newinputStream", inputStream);
				session.setAttribute("newname", name);
				session.setAttribute("newemail", email);
				session.setAttribute("newdob", dob);
				session.setAttribute("newmobile", mobile);
				session.setAttribute("newgender", gender);
				session.setAttribute("newaadhar", aadhar);
				session.setAttribute("newnationality", nationality);

				String m = "Account Details\n\n\nName: " + name + "\n\nAccount Number: " + accnum + "\n\nEmail Id: "
						+ email + "\n\nMobile No: " + mobile + "\n\nGender: " + gender + "\n\nAadhar No: " + aadhar
						+ "\n\nNationality: " + nationality
						+ "\n\n Keep The Details With You At The Time Of Creating Online A/C This Fields Are Manditory.";
				AccountDetailsMail mail = new AccountDetailsMail();
				mail.sendAccountDetailsEmail(email, "ABC BankAccount Created Successfull", m);
				out.println("<script>");
				out.println("alert('Successfully Created An Account The Details Sended To Your Mail')");
				out.println("location.href='displayCustomerRegisteredAcc.jsp'");
				out.println("</script>)");
				Template.closeConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
