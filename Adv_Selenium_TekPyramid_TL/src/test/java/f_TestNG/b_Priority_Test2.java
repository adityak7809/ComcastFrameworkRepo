package f_TestNG;

import org.testng.annotations.Test;

public class b_Priority_Test2 {
	
	@Test(priority = 1)
	public void createContactTest()
	{
		System.out.println("TestCase:- createContact");
	}
	
	@Test(priority = 2)
	public void modifyContactTest()
	{
		System.out.println("TestCase: modifyContactTest");
	}
	
	@Test(priority = 3)
	public void deleteConatctTest()
	{
		System.out.println("TestCase: deleteConatctTest");
	}

}
