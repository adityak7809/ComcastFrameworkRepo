package f_TestNG;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class e_DataProvider_2 {
	
	@DataProvider
	public Object[][] testData()
	{
		Object obj[][]= {{"A_001", "Aditya", 9999999999L},
				         {"A_002", "Ankit", 8888888888L},
				         {"A_003", "Nishant", 7777777777L},
				         {"A_004", "Akshay", 66666666666L}};
		
		return obj;
	}
	
	
	@Test(dataProvider = "testData")
	public void createContact_Id_and_Name(String id, String name, long phoneNo)
	{
		Reporter.log("Contact id: "+ id, true);
		Reporter.log("Contact name: "+ name, true);
		Reporter.log("Contact number: "+ phoneNo, true);
		System.out.println();
	}
}
