package com.bank.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.resource.Template;

public class CheckInsufficient {
	private PreparedStatement pstmnt=null;
	public boolean checkInsufficientBal(String acc,double bal) {
		boolean status=false;
		Connection conn = Template.getMySqlConnection();
		String check="select balance from banklog where accnum=?";
		try {
			pstmnt = conn.prepareStatement(check);
			pstmnt.setString(1, acc);
			ResultSet res = pstmnt.executeQuery();
			double money=0;
			while(res.next())
			{
				money=res.getDouble(1);
				
			}
			if(money>bal)
			{
				status=true;
			}
			else {
				status=false;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	

}
