package com.bank.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bank.resource.Template;

public class SaveDetails {

	private Connection conn=null;
	private PreparedStatement pstmnt=null;

	public boolean saveEmp(String name, String log, String password, String designation) throws SQLException {
		boolean status = false;
		conn = Template.getMySqlConnection();
		String sql = "update emplogin set loginid=?,password=? where empname=? and designation=?";
		pstmnt = conn.prepareStatement(sql);
		pstmnt.setString(1, log);
		pstmnt.setString(2, password);
		pstmnt.setString(3, name);
		pstmnt.setString(4, designation);
		int i = pstmnt.executeUpdate();
		if (i == 1) {
			status = true;
		} else {
			status = false;
		}
		return status;

	}

	public boolean saveCustomer(String accnum, String pin, String log, String password) throws SQLException {
		boolean status = false;
		conn = Template.getMySqlConnection();
		String sql = "update banklog set pin=?,loginid=?,password=? where accnum=?";
		pstmnt = conn.prepareStatement(sql);
		pstmnt.setString(1, pin);
		pstmnt.setString(2, log);
		pstmnt.setString(3, password);
		pstmnt.setString(4, accnum);
		int i = pstmnt.executeUpdate();
		if (i == 1) {
			status = true;
		} else {
			status = false;
		}
		return status;



	}

}
