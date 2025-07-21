package p_Debug;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
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

/**
 * @author Aditya
 * This class contains test cases related to Organization Module
 */

public class a_OrgTest extends a_BaseClass{

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

	@Test
	public void createOrganizationWithIndustry_Test() throws InterruptedException, IOException 
	{		
		// Utility Object Creation
		ExcelUtiltiy excelUtil=new ExcelUtiltiy();
		JavaUtility javaUtil=new JavaUtility();	
		WebdriverUtility webdriverLib=new WebdriverUtility();


		//Generate random number
		String alphaNum=javaUtil.getAlphaNumeric(5);

		//Read Test Script from Excel

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
		homeObj.getOrgLink().click();


		//3. Click on "Create Organization" button
		orgObj.getCreateOrgLink().click();

		//4. Enter all the details & create new Organization
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

	@Test
	public void createOrganizationWithPhoneNumber_Test() throws EncryptedDocumentException, IOException
	{

		//Object Creation
		ExcelUtiltiy excelUtil=new ExcelUtiltiy();
		JavaUtility javaUtil=new JavaUtility();	


		//Generate random number
		String alphaNum=javaUtil.getAlphaNumeric(5);

		//Read Test Script from Excel
		String data=excelUtil.getDataFromExcelFile("Organization", 1, 2);
		String orgName=data+alphaNum;

		String phone=excelUtil.getDataFromExcelFile("Organization", 7, 3);




		//POM Object
		HomePage homeObj=new HomePage(driver);
		OrganizationPage orgObj=new OrganizationPage(driver);
		CreateNewOrganizationPage createOrgObj=new CreateNewOrganizationPage(driver);
		OrganizationInfoPage orgInfoObj=new OrganizationInfoPage(driver);

		//2. Navigate to organization module
		homeObj.getOrgLink().click();

		//3. Click on "Create Organization" button
		orgObj.getCreateOrgLink().click();


		//4. Enter all the details & create new Organization
		createOrgObj.getOrgNameEdit().sendKeys(orgName);

		createOrgObj.getPhoneEdit().sendKeys(phone);

		//Click Save
		createOrgObj.getSaveButton().click();



		//5. Verify phone is visbile in info page
		String actText=orgInfoObj.getPhoneText().getText();
		Assert.assertEquals(true, actText.contains(phone));



	}

}
