package com.bank.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.resource.Template;

public class VerifyActiveStatus {
	private PreparedStatement stmnt = null;

	public boolean checkActive(String name, String pass) throws SQLException {
		boolean status=false;
		Connection conn = Template.getMySqlConnection();
		
		
			String sql = "select status from banklog where loginid=? and password=?";
			stmnt = conn.prepareStatement(sql);
			stmnt.setString(1, name);
			stmnt.setString(2, pass);
			ResultSet res = stmnt.executeQuery();
			String st=null;
		while(res.next())
		{
			st=res.getString(1);
		}
		if(st.equals("ACTIVATE"))
		{
			status=true;
		}
		else {
			status=false;
		}
		
			return status;
		
		
				
	}

}
