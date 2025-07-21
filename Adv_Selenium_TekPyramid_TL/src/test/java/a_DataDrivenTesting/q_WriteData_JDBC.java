package a_DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class q_WriteData_JDBC {
	public static void main(String[] args) throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/hr";
		String user="root";
		String password="@Mysql080989";
		
		Connection con=DriverManager.getConnection(url, user, password);
		Statement stat=con.createStatement();
		
		String s="insert into jobs values('Jack', 'Manual Test Engg', 4000, 6000)";
		
		//Write data
		int result=stat.executeUpdate(s);
		System.out.println(result);
		
		
		
	}

}
