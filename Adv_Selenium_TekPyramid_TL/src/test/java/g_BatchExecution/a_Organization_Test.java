package g_BatchExecution;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.concast.crm.baseTest.a_BaseClass;
import com.concast.crm.generic.fileUtility.ExcelUtiltiy;
import com.concast.crm.generic.webdriverUtility.JavaUtility;
import com.concast.crm.generic.webdriverUtility.WebdriverUtility;
import com.concast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.concast.crm.objectRepositoryUtility.HomePage;
import com.concast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.concast.crm.objectRepositoryUtility.OrganizationPage;

public class a_Organization_Test extends a_BaseClass {

	@Test
	public void createOrg_Test() throws InterruptedException, IOException {


		// Utility Object Creation
		ExcelUtiltiy excelLib = new ExcelUtiltiy();
		JavaUtility javaLib=new JavaUtility();

		// Generate random number
		String alphaNum = javaLib.getAlphaNumeric(5);

		// Read Test Script from Excel

		String data = excelLib.getDataFromExcelFile("Organization", 1, 2);
		String orgName = data + alphaNum;

		// POM Object
		HomePage homeObj = new HomePage(driver);
		OrganizationPage orgObj = new OrganizationPage(driver);
		CreateNewOrganizationPage createOrgObj = new CreateNewOrganizationPage(driver);
		OrganizationInfoPage orgInfoObj = new OrganizationInfoPage(driver);

		
		// 2. Navigate to organization module
		homeObj.getOrgLink().click();

		// 3. Click on "Create Organization" button
		orgObj.getCreateOrgLink().click();

		// Click Save button
		createOrgObj.createOrg(orgName);

		// 5. Verify Organization name in header of the msg
		String actText = orgInfoObj.getOrgNameHeaderText().getText();

		if (actText.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}

		String actOrgName = orgInfoObj.getOrgNameText().getText();

		if (actOrgName.equals(orgName)) {
			System.out.println("Organization name is displayed==PASS");
		} else {
			System.out.println("Organization name is not displayed==FAIL");
		}

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

		if(actText.contains(indName))
		{
			System.out.println(indName+ " Industry name is visible==PASS");
		}
		else
		{
			System.out.println(indName+ " Industry name is not visible==FAIL");
		}

		String actType= orgInfoObj.getTypeText().getText();
		if(actType.equals(type))
		{
			System.out.println(type+" Type name is displayed==PASS");
		}
		else
		{
			System.out.println(type+" Type name is not displayed==FAIL");
		}

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

		if(actText.equals(phone))
		{
			System.out.println(phone+" Phone number is visible==PASS");
		}
		else
		{
			System.out.println(phone+" Phone number is not visible==FAIL");
		}


	}

}
