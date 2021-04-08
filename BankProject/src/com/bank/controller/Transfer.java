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

import com.bank.dao.BankDaoImp;
import com.bank.dao.TransactionMail;
import com.bank.dto.CheckInsufficient;
import com.bank.dto.CheckStatusAD;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String to = req.getParameter("acc");
		System.out.println(to);
		String money = req.getParameter("amount");
		String pin=req.getParameter("pin");
		double amount = Double.parseDouble(money);
		CheckStatusAD checkAD = new CheckStatusAD();
		HttpSession session = req.getSession();
		String accnum=(String) session.getAttribute("accnum");
		String checkpin=session.getAttribute("pin").toString();
		if(checkpin.equals(pin)==false)
		{
			out.println("<script>");
			out.println("alert('TRANSACTION FAILED...YOU ENTERED INVALID PIN NUMBER.')");
			out.println("location.href='transfer.jsp'");
			out.println("</script>)");
			
		}
		{
		if (accnum.equals(to)) {
			out.println("<script>");
			out.println("alert('TRANSACTION FAILED...YOU CAN NOT TRANSFER MONEY TO SELF ACCOUNT.')");
			out.println("location.href='transfer.jsp'");
			out.println("</script>)");

		} else {
			boolean status = false;
			try {
				status = checkAD.check(to);
			} catch (NullPointerException npx) {
				out.println("<script>");
				out.println("alert('Your MySQl DataBase not Yet Connected Please Connect To Your Dadabse')");
				out.println("location.href='transfer.jsp'");
				out.println("</script>)");

			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (status == false) {
				out.println("<script>");
				out.println("alert('RECEIVER ACCOUNT WAS NOT IN ACTIVE STATE TRANSACTION FAILED...PLEASE TRY AGAIN');");
				out.println("location.href='transfer.jsp'");
				out.println("</script>)");

			} else {

				String from = (String) session.getAttribute("accnum");
				CheckInsufficient check = new CheckInsufficient();
				boolean statuss = check.checkInsufficientBal(from, amount);
				if (statuss == false) {
					out.println("<script>");
					out.println("alert('TRANSACTION FAILED...INSUFFICIENT BALANCE IN YOUR ACCOUNT.')");
					out.println("location.href='transfer.jsp'");
					out.println("</script>)");
				} else {
					BankDaoImp dao = new BankDaoImp();
					String otp = dao.genrateOTP();
					session.setAttribute("genotp", otp);
					session.setAttribute("acc", to);
					session.setAttribute("amount", money);
					System.out.println(otp);

					String email = (String) session.getAttribute("email");
					TransactionMail mail = new TransactionMail();
					mail.sendTransactionEmail(email, otp);

					out.println("<script>");
					out.println("location.href='transferOTP.jsp'");
					out.println("</script>)");
				}

			}
		}
		}
	}
}
