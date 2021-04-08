package com.bank.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

import com.bank.resource.Template;

public class BankDtoImp {

	private static Connection conn = null;
	private static PreparedStatement pstmnt = null;

	public static Boolean login(String name, String pass) {
		conn = Template.getMySqlConnection();
		boolean status = false;
		try {

			String sql = "select * from `BankLog` where `loginid`=? and `Password`=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, name);
			pstmnt.setString(2, pass);
			ResultSet res = pstmnt.executeQuery();
			if (res.next()) {
				status = true;
			} else {
				status = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

	public static String genrateOTP() {

		String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
		System.out.println(otp);
		return otp;
	}

	public static Boolean bankLogin(String name, String pass) throws SQLException {
		conn = Template.getMySqlConnection();
		boolean status = false;
		String sql = "select * from `emplogin` where `loginid`=? and `Password`=?";
		pstmnt = conn.prepareStatement(sql);
		pstmnt.setString(1, name);
		pstmnt.setString(2, pass);
		ResultSet res = pstmnt.executeQuery();
		if (res.next()) {
			status = true;
		} else {
			status = false;
		}

		return status;

	}

}
