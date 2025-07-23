package q_Maven_CMD;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.concast.crm.baseTest.a_BaseClass;
import com.concast.crm.generic.fileUtility.ExcelUtiltiy;
import com.concast.crm.generic.webdriverUtility.JavaUtility;
import com.concast.crm.generic.webdriverUtility.UtilityClassObject;
import com.concast.crm.generic.webdriverUtility.WebdriverUtility;
import com.concast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.concast.crm.objectRepositoryUtility.HomePage;
import com.concast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.concast.crm.objectRepositoryUtility.OrganizationPage;


@Listeners(com.concast.crm.listenerUtility.Listerners_ImpClass.class)
public class b_CreateOrgWithIndustry_Test extends a_BaseClass{
	
	@Test
	public void createOrganizationWithIndustry_Test() throws InterruptedException, IOException 
	{		
		// Utility Object Creation
		ExcelUtiltiy excelUtil=new ExcelUtiltiy();
		JavaUtility javaUtil=new JavaUtility();	
		WebdriverUtility webdriverLib=new WebdriverUtility();


		//Generate random number
		UtilityClassObject.getTest().info("Generate random number");
		String alphaNum=javaUtil.getAlphaNumeric(5);

		//Read Test Script from Excel
		UtilityClassObject.getTest().info("Read data from Excel file");
		String data=excelUtil.getDataFromExcelFile("Organization", 1, 2);
		String orgName=data+alphaNum;

		String indName=excelUtil.getDataFromExcelFile("Organization", 4, 3);
		String type=excelUtil.getDataFromExcelFile("Organization", 4, 4);


		//POM Object
		HomePage homeObj=new HomePage(driver);
		OrganizationPage orgObj=new OrganizationPage(driver);
		CreateNewOrganizationPage createOrgObj=new CreateNewOrganizationPage(driver);
		OrganizationInfoPage orgInfoObj=new OrganizationInfoPage(driver);


		//2. Navigate to organization module
		UtilityClassObject.getTest().info("Navigate to Organization module");
		homeObj.getOrgLink().click();


		//3. Click on "Create Organization" button
		UtilityClassObject.getTest().info("Click on Create Org");
		orgObj.getCreateOrgLink().click();

		//4. Enter all the details & create new Organization
		UtilityClassObject.getTest().info("Click on Save button");
		createOrgObj.createOrg(orgName);

		webdriverLib.selectFromDropdown(createOrgObj.getIndustry(), indName);

		webdriverLib.selectFromDropdown(createOrgObj.getType(), type);


		//Click Save button
		createOrgObj.getSaveButton();


		//5. Verify Industry and type name is visbile in info page
		String actText=orgInfoObj.getIndustryText().getText();

		Assert.assertEquals(true, actText.contains(indName));

		String actType= orgInfoObj.getTypeText().getText();
		Assert.assertEquals(true, actType.contains(type));


	}


}
