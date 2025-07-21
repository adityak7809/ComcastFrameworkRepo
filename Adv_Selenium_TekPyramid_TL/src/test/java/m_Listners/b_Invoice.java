package m_Listners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.concast.crm.baseTest.a_BaseClass;
import com.concast.crm.listenerUtility.Listerners_ImpClass;

@Listeners(Listerners_ImpClass.class)
public class b_Invoice extends a_BaseClass{

	@Test
	public void ceateInvoice_Test()
	{
		System.out.println("execute createInvoice_Test");
		
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
		
		
	}
	
	
	
	@Test
	public void createInvoiceWithContact_Test()
	{
		
		System.out.println("execute createInvoiceWithContact_Test");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

}
