package com.bank.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dto.MailWithAttachment;
import com.bank.resource.Template;

@WebServlet("/MailAttachment")
public class MailAttachment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		String acc = (String) session.getAttribute("acc");
		try {
			Connection conn = Template.getMySqlConnection();

			String sql = "select email from banklog where accnum=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, acc);
			ResultSet result = pstmnt.executeQuery();
			String email = null;
			while (result.next()) {
				email = result.getString(1);
			}
			String home=System.getProperty("user.home");
			String filename = (String) session.getAttribute("filenamee");
			Thread.sleep(6000);
			String sub="ABC BANK STATEMENT PDF ATTACHMENT";
			String bodytxt="THANK YOU FOR VISITING ABC BANK.\n\nYOUR BANK STATEMENT";
			MailWithAttachment mail = new MailWithAttachment();
			mail.sendEmail(email, filename, home,sub,bodytxt);
			out.println("<script>");
			out.println("alert('Successfull...! Statement Send to customer Mail')");
			out.println("location.href='ministatement.jsp'");
			out.println("</script>)");

		} 
		catch(FileNotFoundException fne)
		{
			out.println("<script>");
			out.println("alert('TimeOut...! Statement Sends Failed. Please Try Again...!')");
			out.println("location.href='ministatement.jsp'");
			out.println("</script>)");
		}
		catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} 
				
		}

}
