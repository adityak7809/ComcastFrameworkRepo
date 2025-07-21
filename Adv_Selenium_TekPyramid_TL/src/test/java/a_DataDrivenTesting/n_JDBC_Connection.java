package a_DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class n_JDBC_Connection {

	public static void main(String[] args) throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/hr";
		String user="root";
		String password="@Mysql080989";
		
		//Step 1: Load/Register the database driver
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		//Step 2: Connect to database
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("======Connection Established=====");
		
		//Step 3: Create SQL Statement 
	    Statement stat= con.createStatement();
	    
		//Step 4: Execute 'Select Query' and get Result
	    String query="select * from jobs";
	    ResultSet rs = stat.executeQuery(query);
	    
	    
	    //Print data in console
	    while(rs.next())
	    {
	    	System.out.println(rs.getString(1)+" "+rs.getString("job_title")+" "+rs.getString(3)+" "+rs.getInt(4));
	    } 
	    
	    
		//Step 5: Close the connection
	    con.close();


	}





}
