package com.bank.dto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.resource.Template;

public class SearchAcNumbers {
	public List<String> allAcNumbers()
	{
		Connection conn = Template.getMySqlConnection();
		List<String> accnumbers = new ArrayList<String>();
	
		try {
			String sql="select accnum from banklog";
			Statement stmnt = conn.createStatement();
			ResultSet res = stmnt.executeQuery(sql);
			while(res.next())
			{
				accnumbers.add('"' + res.getString(1) + '"');
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accnumbers;
		
	}

	public List<String> allAcNumbersNames()
	{
		Connection conn = Template.getMySqlConnection();
		List<String> accnumnames = new ArrayList<String>();
	
		try {
			String sql="select name,accnum from banklog";
			Statement stmnt = conn.createStatement();
			ResultSet res = stmnt.executeQuery(sql);
			while(res.next())
			{
				accnumnames.add('"' + res.getString(1) + '"');
				accnumnames.add('"' + res.getString(2) + '"');
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accnumnames;
		
	}
}
