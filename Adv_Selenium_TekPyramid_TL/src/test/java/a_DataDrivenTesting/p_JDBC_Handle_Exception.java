package a_DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class p_JDBC_Handle_Exception {

	public static void main(String[] args) throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/hr";
		String user="root";
		String password="@Mysql080989";
		
		Connection con=null;
		try {
		//Step 1: Load/Register the database driver
		//Step 2: Connect to database
		con = DriverManager.getConnection(url, user, password);
		System.out.println("======Connection Established=====");
		
		//Step 3: Create SQL Statement 
	    Statement stat= con.createStatement();
	    
		//Step 4: Execute 'Select Query' and get Result
	    String query="select * from jobs";
	    
	    //Read Data--------------------------------------------------------------s
	    ResultSet rs = stat.executeQuery(query);
	    
	    
	    //Print data in console
	    while(rs.next())
	    {
	    	System.out.println(rs.getString(1)+" "+rs.getString("job_title")+" "+rs.getString(3)+" "+rs.getInt(4));
	    }
	    
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//Step 5: Close the connection
			con.close();
			System.out.println("======Connection Closed=====");
		}
	    
	    
		
	    


	}





}
