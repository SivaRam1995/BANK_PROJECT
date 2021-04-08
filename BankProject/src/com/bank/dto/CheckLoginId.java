package com.bank.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.resource.Template;

public class CheckLoginId {

	private static Connection conn = null;
	private static PreparedStatement pstmnt = null;

	public boolean checkingLogId(String tablename,String loginid) {
		boolean status = false;
		conn = Template.getMySqlConnection();
		try {
			String sql = "select `loginid` from `"+tablename+"` where `loginid`=?";
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, loginid);
			ResultSet res = pstmnt.executeQuery();
			if (res.next()) {
				status = true;
			}

			else {
				status = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

	
}
