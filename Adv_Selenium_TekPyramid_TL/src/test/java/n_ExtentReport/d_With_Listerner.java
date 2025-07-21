package n_ExtentReport;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.concast.crm.baseTest.a_BaseClass;
import com.concast.crm.listenerUtility.Listerners_ImpClass;


@Listeners(Listerners_ImpClass.class)
public class d_With_Listerner extends a_BaseClass {
	

	
	@Test
	public void createContactTest()
	{
		
		Assert.fail();
	    System.out.println("Test1");

		
	}
	
	@Test
	public void modifyContactTest()
	{
		
	    System.out.println("Test2");

	}
	
	@Test
	public void deleteConatctTest()
	{
	    System.out.println("Test3");
	}

}
