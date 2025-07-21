package f_TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class c_DependsOn_Test3 {
	public String orgName="";
	
	@Test
	public void createContactTest()
	{
		System.out.println("TestCase:- createContact");
		orgName="Facebook";
		System.out.println("Org created : "+orgName);
		Assert.fail();
	}
	
	@Test(dependsOnMethods = "createContactTest")
	public void modifyContactTest()
	{
		System.out.println("TestCase: modifyContactTest");
		orgName="Instgram";
		System.out.println("Orgname: "+orgName);
	}
	
	@Test(dependsOnMethods = "modifyContactTest")
	public void deleteConatctTest()
	{
		System.out.println("TestCase: deleteConatctTest");
		orgName=null;
		System.out.println("Orgname: "+orgName);	}

}
