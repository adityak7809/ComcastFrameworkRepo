package k_Reports;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class b_Hard_Soft_Assert {
	
	@Test
	public void hardAssert_Test(Method mtd)
	{
		Reporter.log(mtd.getName()+ "Test start");
		
		Reporter.log("Test_1");
		Reporter.log("Test_2");
		Reporter.log("Test_3");
		Reporter.log("Test_4");
		
		Reporter.log(mtd.getName()+ "Test end");
	}
	
	@Test
	public void softAssert_Test(Method mtd)
	{
		Reporter.log(mtd.getName()+ "Test start", true);
	
		Reporter.log("Test_1", true);
		Reporter.log("Test_2", true);
		Reporter.log("Test_3", true);
		Reporter.log("Test_4", true);

		Reporter.log(mtd.getName()+ "Test end", true);
		
	}

}
