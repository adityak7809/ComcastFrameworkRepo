package q_Maven_CMD;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.concast.crm.baseTest.a_BaseClass;
import com.concast.crm.generic.fileUtility.ExcelUtiltiy;
import com.concast.crm.generic.webdriverUtility.JavaUtility;
import com.concast.crm.generic.webdriverUtility.UtilityClassObject;
import com.concast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.concast.crm.objectRepositoryUtility.HomePage;
import com.concast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.concast.crm.objectRepositoryUtility.OrganizationPage;


@Listeners(com.concast.crm.listenerUtility.Listerners_ImpClass.class)
public class a_CreateOrg_Test extends a_BaseClass{
	

	
	
	@Test
	public void createOrg_Test() throws InterruptedException, IOException {


		/* Utility Object Creation */
		ExcelUtiltiy excelLib = new ExcelUtiltiy();
		JavaUtility javaLib=new JavaUtility();

		/* Generate random number */
		UtilityClassObject.getTest().info("Generate random number");
		String alphaNum = javaLib.getAlphaNumeric(5);

		/* Read Test Script from Excel */
		UtilityClassObject.getTest().info("Read data from Excel file");
		String data = excelLib.getDataFromExcelFile("Organization", 1, 2);
		String orgName = data + alphaNum;

		/* POM Object */
		HomePage homeObj = new HomePage(driver);
		OrganizationPage orgObj = new OrganizationPage(driver);
		CreateNewOrganizationPage createOrgObj = new CreateNewOrganizationPage(driver);
		OrganizationInfoPage orgInfoObj = new OrganizationInfoPage(driver);


		/* 2. Navigate to organization module */
		UtilityClassObject.getTest().info("Navigate to Organization module");
		homeObj.getOrgLink().click();

		// 3. Click on "Create Organization" button
		UtilityClassObject.getTest().info("Click on Create Org");
		UtilityClassObject.getTest().info("Enter all details");
		orgObj.getCreateOrgLink().click();

		// Click Save button
		UtilityClassObject.getTest().info("Click on Save button");
		createOrgObj.createOrg(orgName);

		// 5. Verify Organization name in header of the msg
		String actText = orgInfoObj.getOrgNameHeaderText().getText();
		
		Assert.assertEquals(true, actText.contains(orgName));

		String actOrgName = orgInfoObj.getOrgNameText().getText();

		Assert.assertEquals(true, actOrgName.contains(orgName));
		
	

	}

}
