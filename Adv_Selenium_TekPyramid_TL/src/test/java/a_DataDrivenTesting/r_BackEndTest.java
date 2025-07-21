package a_DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class r_BackEndTest {
	
	@Test
	public void Test() throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/hr";
		String user="root";
		String password="@Mysql080989";
		
		String expJobTitle="AC_ACCOUNT";
	    boolean flag=false;
		
		Connection con=DriverManager.getConnection(url, user, password);
		
		Statement stat=con.createStatement();
		String s="select * from jobs";
		
		ResultSet rs=stat.executeQuery(s);
		while(rs.next())
		{
			String actJobTitle=rs.getString(1);
			if(expJobTitle.equals(actJobTitle))
			{
				flag=true;
				System.out.println(expJobTitle+" is available==PASS");
			}
		}
		
		if(flag==false)
		{
			System.out.println(expJobTitle+" is not available==FAIL");
			//Assert.fail();
		}
		
	}

}
