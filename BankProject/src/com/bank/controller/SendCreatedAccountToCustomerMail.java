package com.bank.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dto.MailWithAttachment;

@WebServlet("/sendCustomerAccMail")
public class SendCreatedAccountToCustomerMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
				try {
					String name=(String) session.getAttribute("newname");
			String email=(String) session.getAttribute("newemail");
			String home=System.getProperty("user.home");
			String filename = (String) session.getAttribute("newaadhar");
			Thread.sleep(6000);
			String sub="DEAR "+name+" WELCOME TO ABC BANK YOUR ACCOUNT DETAILS.";
			String bodytxt="WELCOME TO ABC BANK.\n\nYOUR ACCOUNT CREATED SUCCESSFULL.";
			MailWithAttachment mail = new MailWithAttachment();
			mail.sendEmail(email, filename, home,sub,bodytxt);
			out.println("<script>");
			out.println("alert('Successfull...! Details Send to customer Mail')");
			out.println("location.href='createcustomer.jsp'");
			out.println("</script>)");

		} 
		catch(FileNotFoundException fne)
		{
			out.println("<script>");
			out.println("alert('TimeOut...!  Sends Failed. Please Try Again...!')");
			out.println("location.href='displayCustomerRegisteredAcc.jsp'");
			out.println("</script>)");
		}
		catch ( InterruptedException e) {
			e.printStackTrace();
		} 
				
		}

}
