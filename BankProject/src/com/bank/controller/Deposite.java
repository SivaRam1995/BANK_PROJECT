package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dto.AccountDetailsMail;
import com.bank.resource.Template;

@WebServlet("/DEPOSITE")
public class Deposite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		LocalDate date = java.time.LocalDate.now();
		String todaydate = date.toString();
		String accnum = req.getParameter("accnum");
		String money = req.getParameter("amt");
		double amount = Double.parseDouble(money);
		Connection conn = Template.getMySqlConnection();
		try {
			String sql = "insert into abc_bank_transaction(TransactionDate,fromAccNum,toAccNum, Amount, Cre_or_Deb) values(?,?,?,?,?)";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, todaydate);
			pstmnt.setString(2, accnum);
			pstmnt.setString(3, accnum);
			pstmnt.setDouble(4, amount);
			pstmnt.setString(5, "Deposite");
			int i = pstmnt.executeUpdate();
			if (i == 1) {
				String update = "update banklog set balance=balance+? where accnum=?";
				pstmnt = conn.prepareStatement(update);
				pstmnt.setDouble(1, amount);
				pstmnt.setString(2, accnum);
				int res = pstmnt.executeUpdate();
				if (res == 1) {
					String mailing = "select email,balance from banklog where accnum=?";
					pstmnt = conn.prepareStatement(mailing);
					pstmnt.setString(1, accnum);
					ResultSet re = pstmnt.executeQuery();
					String email = null;
					String balance=null;
					while (re.next()) {
						email = re.getString(1);
						balance=re.getString(2);
					}
					String last4 = accnum.substring(accnum.length() - 4);
					String m = "Rs. " + money + "/- DEPOSITED IN YOUR A/C No.XXXX-XXXX-XXXX-" + last4
							+ " BY SELF. \n\nAVAILABLE BALANCE IS..  Rs. "+balance+" /- .\n\n\nTHANK YOU FOR VISITING ABC BANK.";
					System.out.println(m);
					AccountDetailsMail mail = new AccountDetailsMail();
					mail.sendAccountDetailsEmail(email, "DEPOSITE BY SELF", m);

					out.println("<script>");
					out.println("alert('DEPOSITED SUCCESSFULL')");
					out.println("location.href='deposite.jsp'");
					out.println("</script>)");

				} else {
					out.println("<script>");
					out.println("alert('Failed')");
					out.println("location.href='deposite.jsp'");
					out.println("</script>)");

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
