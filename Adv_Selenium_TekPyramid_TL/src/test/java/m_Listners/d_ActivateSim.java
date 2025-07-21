package m_Listners;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.concast.crm.baseTest.a_BaseClass;


public class d_ActivateSim extends a_BaseClass {

	
	@Test(retryAnalyzer = com.concast.crm.listenerUtility.RetryListener_ImpClass.class)
	public void activateSim_Test()
	{
		System.out.println("execute ceateContact_Test");
		
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Contact");
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	
	}
	
	
	
	
}
