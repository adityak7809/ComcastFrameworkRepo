package com.concast.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatbaseUtility {
	
	String url="jdbc:mysql://localhost:3306/hr";
	String user="root";
	String password="@Mysql080989";
	
	Connection con;
	
	public void getDbConnection() throws SQLException
	{
		try {
		con=DriverManager.getConnection(url, user, password);
		} catch(Exception e)
		{
			
		}
	}
	
	public void closeDbConnection() throws SQLException
	{
		try {
		con.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public ResultSet executeSelectQuery(String query) throws SQLException
	{
		ResultSet rs=null;
		try {
		Statement stat=con.createStatement();
		rs= stat.executeQuery(query);
		}
		catch(Exception e)
		{
			
		}
		return rs;
	}
	
	public int executeNonSelectQuery(String query)
	{
		int rs=0;
		try {
		Statement stat=con.createStatement();
		rs= stat.executeUpdate(query);
		}
		catch(Exception e)
		{
			
		}
		return rs;
	}

}
