package a_DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Test1 {
	public static void main(String[] args) throws SQLException {
	
		String url="jdbc:mysql://localhost:3306/hr";
		String user="root";
		String password="@Mysql080989";
		
		Connection con=DriverManager.getConnection(url, user, password);
		
		String s="select * from jobs";
		Statement stat=con.createStatement();
		
		
		ResultSet rs=stat.executeQuery(s);
		while(rs.next())
		{
			System.out.println(rs.getString(1)+" "+rs.getString(2));
		}
 		
	}

}
