package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.resource.Template;

public class CreditAndDebit {
	
	private PreparedStatement pstmnt = null;
	Connection conn = Template.getMySqlConnection();
	public double getDebit(String to) {
		double money = 0;
		try {
			String sql = "select balance from `banklog` where accnum=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, to);
			ResultSet res = pstmnt.executeQuery();
			while (res.next()) {
				money = res.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return money;

	}

	public double getCredit(String from) {
		double money = 0;
		try {
			String sql = "select balance from `banklog` where accnum=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, from);
			ResultSet res = pstmnt.executeQuery();
			while (res.next()) {
				money = res.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return money;

	}

}
