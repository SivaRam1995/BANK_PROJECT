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

@WebServlet("/SaveCustomer")
public class SaveCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		String accnum = (String) session.getAttribute("accnum");
		String email = (String) session.getAttribute("email");
		String pin = req.getParameter("pin");
		String log = req.getParameter("log");
		String password = req.getParameter("pass");
		String p =session.getAttribute("pin").toString();
		String pas = (String) session.getAttribute("pass");
		String l = (String) session.getAttribute("log");
		try {
			if (pin.equals(p) && password.equals(pas) && log.equals(l) == true) {
				out.println("<script>");
				out.println("alert('You Are Details Are Same.')");
				out.println("location.href='myprofileedit.jsp'");
				out.println("</script>)");
			} else if (log.equals(l) == false) {
				CheckLoginId checklogid = new CheckLoginId();
				boolean logid_status = checklogid.checkingLogId("emplogin", log);
				boolean log_id = checklogid.checkingLogId("banklog", log);
				if (logid_status == true | log_id == true) {
					out.println("<script>");
					out.println("alert('You are Login Id Invalid. Please Try Again With Another Login Id.')");
					out.println("location.href='myprofileedit.jsp'");
					out.println("</script>)");
				} 
			}else {
					SaveDetails saveDetails = new SaveDetails();
					boolean status;
					status = saveDetails.saveCustomer(accnum, pin, log, password);
					if (status == true) {
						session.setAttribute("logid", log);
						session.setAttribute("pass", password);
						String logid = log;
						String pass = password;
						session.setAttribute("loginid", logid);
						session.setAttribute("pass", pass);
						String m = "Dear " + name + " Your new pin, Login Id and Password.\n\npin: " + pin
								+ "\n\nloginid: " + log + "\n\npassword: " + password + "";
						AccountDetailsMail mail = new AccountDetailsMail();
						mail.sendAccountDetailsEmail(email, "DEAR " + name + " YOUR DETAILS UPDATED SUCCESSFULLY.", m);
						out.println("<script>");
						out.println("alert('Details Updated Successfully')");
						out.println("location.href='myprofile.jsp'");
						out.println("</script>)");

					} else {
						out.println("<script>");
						out.println("alert('Error At the moment You cannot change the details.')");
						out.println("location.href='myprofileedit.jsp'");
						out.println("</script>)");

					}

				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
