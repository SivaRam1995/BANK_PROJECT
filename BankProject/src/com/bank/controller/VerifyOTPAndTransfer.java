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
import javax.servlet.http.HttpSession;

import com.bank.dao.CreditAndDebit;
import com.bank.dto.CreditAndDebitMail;
import com.bank.resource.Template;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

@WebServlet("/VerifyOTPAndTransfer")
public class VerifyOTPAndTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstmnt = null;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int chance = 3;
		String otp = req.getParameter("new_otp");
		System.out.println(otp);
		HttpSession session = req.getSession();
		String genrateOTP = (String) session.getAttribute("genotp");
		System.out.println(genrateOTP);

		PrintWriter out = resp.getWriter();
		LocalDate date = java.time.LocalDate.now();
		String todaydate = date.toString();
		System.out.println(todaydate);

		String from = (String) session.getAttribute("accnum");
		System.out.println(from);
		String to = (String) session.getAttribute("acc");
		System.out.println(to);
		String money = (String) session.getAttribute("amount");
		System.out.println(money);
		double amount = Double.parseDouble(money);

		String credit = "Credited";
		String debit = "Debited";

		try {
			if (genrateOTP.equals(otp)) {
				Connection conn = Template.getMySqlConnection();
				String debited = "update `banklog` set `balance`=balance-? where accnum=?";
				pstmnt = conn.prepareStatement(debited);
				pstmnt.setDouble(1, amount);
				pstmnt.setString(2, from);
				int i = pstmnt.executeUpdate();
				String sql = "INSERT INTO `abc_bank_transaction` (`TransactionDate`, `fromAccNum`, `toAccNum`, `Amount`, `Cre_or_Deb`) VALUES (?,?,?,?,?);";
				pstmnt = conn.prepareStatement(sql);
				pstmnt.setString(1, todaydate);
				pstmnt.setString(2, from);
				pstmnt.setString(3, to);
				pstmnt.setDouble(4, amount);
				pstmnt.setString(5, debit);
				int j = pstmnt.executeUpdate();
				String credited = "update `banklog` set `balance`=balance+? where accnum=?";
				pstmnt = conn.prepareStatement(credited);
				pstmnt.setDouble(1, amount);
				pstmnt.setString(2, to);
				int k = pstmnt.executeUpdate();
				String update = "INSERT INTO `abc_bank_transaction` (`TransactionDate`, `fromAccNum`, `toAccNum`, `Amount`, `Cre_or_Deb`) VALUES (?,?,?,?,?)";
				pstmnt = conn.prepareStatement(update);
				pstmnt.setString(1, todaydate);
				pstmnt.setString(2, to);
				pstmnt.setString(3, from);
				pstmnt.setDouble(4, amount);
				pstmnt.setString(5, credit);
				int l = pstmnt.executeUpdate();
				if (i == 1 & j == 1 & k == 1 & l == 1) {
					String receivermail = null;
					String mailing = "select email from `banklog` where accnum=?";
					pstmnt = conn.prepareStatement(mailing);
					pstmnt.setString(1, to);
					ResultSet resultSet = pstmnt.executeQuery();
					while (resultSet.next()) {
						receivermail = resultSet.getString(1);
						System.out.println(receivermail);

					}
					CreditAndDebit c = new CreditAndDebit();
					double debitedmoney = c.getDebit(from);
					double creditedMoney = c.getCredit(to);

					String senderemail = (String) session.getAttribute("email");
					System.out.println(senderemail);
					System.out.println(receivermail);
					CreditAndDebitMail mail = new CreditAndDebitMail();
					String fromlast4 = from.substring(from.length() - 4);
					String tolast4 = to.substring(to.length() - 4);
					String deb = "Rs. " + amount + " /- DEBITED FROM YOUR ABC BANK A/C No.XXXX-XXXX-" + fromlast4
							+ "\n\n AVAILABLE BALANCE IS Rs. " + debitedmoney
							+ " /- \n\n\nTHANK YOU FOR CHOOSING ABC BANK ONLINE SYSTEM.";
					String cre = "Rs. " + amount + " /- CREDITED FROM A/C No.XXXX-XXXX-" + tolast4
							+ "\n\n AVAILABLE BALANCE IN YOUR A/C IS Rs. " + creditedMoney
							+ " /- \n\n\nTHANK YOU FOR CHOOSING ABC BANK ONLINE SYSTEM.";
					mail.sendTransactionEmail(senderemail, debit, fromlast4, deb);
					mail.sendTransactionEmail(receivermail, credit, tolast4, cre);

					out.println("<script>");
					out.println("alert('Amount Transfered Successfully')");
					out.println("location.href='transfer.jsp'");
					out.println("</script>)");

				} else {
					out.println("<script>");
					out.println("alert('Transacton failed Try Again')");
					out.println("location.href='transfer.jsp'");
					out.println("</script>)");

				}
			}

			else {
				if (chance == 3) {
					chance = chance - 1;
					out.println("<script>");
					out.println("alert('Your are Enter Wrong OTP Please Re-Enter')");
					out.println("location.href='transfer.jsp'");
					out.println("</script>)");
				} else if (chance == 2) {
					chance = chance - 1;
					out.println("<script>");
					out.println("alert('Your are Enter Wrong OTP Please Re-Enter one more chance')");
					out.println("location.href='transfer.jsp'");
					out.println("</script>)");
				} else if (chance == 1) {
					chance = chance - 1;
					out.println("<script>");
					out.println("alert('Your are Enter Wrong OTP Please Re-Enter')");
					out.println("location.href='transfer.jsp'");
					out.println("</script>)");
				} else {
					chance = 3;
					out.println("<script>");
					out.println("alert('Your are Enter Wrong OTP Please Register again and Enter a valid Email Id')");
					out.println("location.href='transfer.jsp'");
					out.println("</script>)");
				}

			}

		} catch (MySQLNonTransientConnectionException | NullPointerException mysqlnon) {
			out.println("<script>");
			out.println("alert('Your MySQl DataBase not Yet Connected Please Connect To Your Dadabse')");
			out.println("location.href='transfer.jsp'");
			out.println("</script>)");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
