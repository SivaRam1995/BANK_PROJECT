package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dto.AccountDetailsMail;
import com.bank.dto.CheckLoginId;
import com.bank.dto.SaveDetails;

@WebServlet("/SaveEmp")
public class SaveEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		String email = (String) session.getAttribute("email");
		String designation = (String) session.getAttribute("designation");
		String log = req.getParameter("log");
		String password = req.getParameter("password");
		String l=(String) session.getAttribute("loginid");
		String p=(String) session.getAttribute("pass");
		SaveDetails saveDetails = new SaveDetails();
		boolean status;
		try {
			if (log.equals(l) && password.equals(p) == true) {
				out.println("<script>");
				out.println("alert('You Are Details Are Same.')");
				out.println("location.href='empprofileedit.jsp'");
				out.println("</script>)");
			}
			else if(log.equals(l)==false){
			CheckLoginId checklogid = new CheckLoginId();
			boolean logid_status = checklogid.checkingLogId("emplogin", log);
			boolean log_id = checklogid.checkingLogId("banklog", log);
			
			
			if (logid_status == true | log_id == true) {
				out.println("<script>");
				out.println("alert('You are Login Id Invalid. Please Try Again With Another Login Id.')");
				out.println("location.href='empprofileedit.jsp'");
				out.println("</script>)");
			}
			}
			else {
			status = saveDetails.saveEmp(name, log, password,designation);
			if (status == true) {
				String logid=log;
				String pass=password;
				session.setAttribute("loginid", logid);
				session.setAttribute("pass", pass);
				String m = "Dear "+name+" Your new Login Id and Password.\n\nloginid: "+log+"\n\npassword: "+password+"";
				AccountDetailsMail mail = new AccountDetailsMail();
				mail.sendAccountDetailsEmail(email, "DEAR "+designation+" YOUR DETAILS UPDATED SUCCESSFULLY.", m);
				out.println("<script>");
				out.println("alert('Details Updated Successfully')");
				out.println("location.href='empprofile.jsp'");
				out.println("</script>)");

			} else {
				out.println("<script>");
				out.println("alert('Error At the moment You cannot change the details.')");
				out.println("location.href='empprofileedit.jsp'");
				out.println("</script>)");


			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
