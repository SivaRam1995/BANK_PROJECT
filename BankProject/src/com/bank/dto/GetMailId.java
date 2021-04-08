package com.bank.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.resource.Template;

public class GetMailId {
	private static PreparedStatement pstmnt = null;
	private static ResultSet res = null;


	public String getMailId(String accnum) {
		String email=null;
		Connection conn = Template.getMySqlConnection();
		String sql = "select email from banklog where accnum=?";
		try {
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setString(1, accnum);
			res = pstmnt.executeQuery();
			while (res.next()) {
				email = res.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return email;
	}

}
