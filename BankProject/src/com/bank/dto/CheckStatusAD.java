package com.bank.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.resource.Template;

public class CheckStatusAD {
	private PreparedStatement pstmnt = null;

	public boolean check(String accnum) throws SQLException {
		boolean status = false;
		Connection conn = Template.getMySqlConnection();

		String sql = "select status from banklog where accnum=? and status='Activate';";
		pstmnt = conn.prepareStatement(sql);
		pstmnt.setString(1, accnum);
		ResultSet res = pstmnt.executeQuery();
		if (res.next()) {
			status = true;
		} else {
			status = false;
		}

		return status;

	}
}
