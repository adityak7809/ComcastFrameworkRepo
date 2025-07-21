package j_Assert;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class b_Hard_Soft_Assert {
	
	@Test
	public void hardAssert_Test()
	{
		System.out.println("Test_1");
		Assert.assertEquals("home", "home");
		System.out.println("Test_2");
		System.out.println("Test_3");
		Assert.assertFalse(false);
		System.out.println("Test_4");
	}
	
	@Test
	public void softAssert_Test()
	{
		SoftAssert as=new SoftAssert();
		
		System.out.println("Test_1");
		as.assertEquals("home", "home");
		System.out.println("Test_2");
		System.out.println("Test_3");
		as.assertFalse(true);
		System.out.println("Test_4");
		
		as.assertAll();
	}

}
