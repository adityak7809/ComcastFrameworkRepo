package f_TestNG;

import org.junit.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class g_Config_Annotations {

	@BeforeSuite
	public void m1()
	{
		System.out.println("BS");
	}


	@BeforeClass
	public void m2()
	{
		System.out.println("BC");
	}

	@BeforeMethod
	public void m3()
	{
		System.out.println("BM");
	}
	
	
	@Test
	public void Test_1()
	{
		System.out.println("This is Test_1");
	}
	
	
	@Test
	public void Test_2()
	{
		System.out.println("This is Test_2");
	}

	@AfterMethod
	public void m4()
	{
		System.out.println("AM");
	}

	@AfterClass
	public void m5()
	{
		System.out.println("AC");
	}

	@AfterSuite
	public void m6()
	{
		System.out.println("AS");	
	}







}
