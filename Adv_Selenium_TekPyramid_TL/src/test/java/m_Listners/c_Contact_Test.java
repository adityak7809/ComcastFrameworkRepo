package m_Listners;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.concast.crm.baseTest.a_BaseClass;


public class c_Contact_Test extends a_BaseClass{

	@Test
	public void ceateContact_Test()
	{
		System.out.println("execute ceateContact_Test");
		
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Contact");
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	
	}
	
	
	
	@Test
	public void createContactWithEmail_Test()
	{
		
		System.out.println("execute createContactWithEmail_Test");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

}
