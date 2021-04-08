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
import com.bank.resource.Template;

@WebServlet("/ACTIVE")
public class Active extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String accnum = (String) session.getAttribute("acc");
		Connection conn = Template.getMySqlConnection();
		try {
			String update = "update banklog set status='ACTIVATE' where accnum=?";
			pstmnt = conn.prepareStatement(update);
			pstmnt.setString(1, accnum);
			int i = pstmnt.executeUpdate();
			if (i == 1) {
				out.println("<script>");
				out.println("alert('ACCOUNT RE-ACTIVATED SUCCESSFULL')");
				out.println("location.href='a_dcustomer.jsp'");
				out.println("</script>)");

			} else {
				out.println("<script>");
				out.println("alert('Failed.. Please Try Again')");
				out.println("location.href='a_dcustomer.jsp'");
				out.println("</script>)");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
