package f_TestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class e_DataProvider_1 {
	
	@DataProvider
	public Object[] testData()
	{
		Object[] obj= {"Aditya", "Nishant", "Akshay", "Ankit"};
		return obj;
	}
	
	
	@Test(dataProvider = "testData")
	public void createContact(String name)
	{
		System.out.println("Create contact: "+ name);
	}
	
	

}
