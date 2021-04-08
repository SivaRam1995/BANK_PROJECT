package com.bank.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

public class Template {
	private static Connection conn=null;

	public static Connection getMySqlConnection()
	{
		
		ResourceBundle bundle = ResourceBundle.getBundle("com.bank.utilities.mysql-info");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String pass=bundle.getString("pass");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		}catch(MySQLNonTransientConnectionException |NullPointerException mysqlnon)
		{
			System.out.println("Your MySQl DataBase not Yet Connected Please Connect To Your Dadabse");
			
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConnection()
	{
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
