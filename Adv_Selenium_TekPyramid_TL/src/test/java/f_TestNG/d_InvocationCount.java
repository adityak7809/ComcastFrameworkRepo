package f_TestNG;

import org.testng.annotations.Test;

public class d_InvocationCount {
	
	
	@Test(invocationCount = 10)
	public void createOrder()
	{
		System.out.println("Create order==>123");
	}
	
	

}
