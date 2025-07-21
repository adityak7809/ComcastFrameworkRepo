package i_ParallelExecution;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.concast.crm.baseTest.a_BaseClass;
import com.concast.crm.generic.fileUtility.ExcelUtiltiy;
import com.concast.crm.generic.fileUtility.FileUtility;
import com.concast.crm.generic.webdriverUtility.JavaUtility;
import com.concast.crm.generic.webdriverUtility.WebdriverUtility;
import com.concast.crm.objectRepositoryUtility.ContactInfoPage;
import com.concast.crm.objectRepositoryUtility.ContactPage;
import com.concast.crm.objectRepositoryUtility.CreateNewContactPage;
import com.concast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.concast.crm.objectRepositoryUtility.HomePage;
import com.concast.crm.objectRepositoryUtility.LoginPage;
import com.concast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.concast.crm.objectRepositoryUtility.OrganizationPage;

public class b_Contact_Test extends a_BaseClass {
	
	@Test(priority = 1)
	public  void createContact_Test() throws EncryptedDocumentException, IOException {


		//Object Creation
		ExcelUtiltiy excelUtil=new ExcelUtiltiy();
		
		//Read Test Script from Excel
		String lastname=excelUtil.getDataFromExcelFile("Contact", 1, 2);

		//POM Object
		HomePage homeObj=new HomePage(driver);
		ContactPage contObj=new ContactPage(driver);
		CreateNewContactPage createNewContObj=new CreateNewContactPage(driver);
		ContactInfoPage contInfoObj=new ContactInfoPage(driver);

		//2. Navigate to Contact module
		homeObj.getContactLink().click();

		//3. Click on "Create Contact" button
		contObj.getCreateContLink().click();

		//4. Enter all the details & create new Contact
		createNewContObj.getLastnameEdit().sendKeys(lastname);


		//Click Save
		createNewContObj.getSaveButton().click();

		//5. Verify Conatact lastname is visbile in info page
		
		String actContLastName= contInfoObj.getContLastnameText().getText();
		if(actContLastName.equals(lastname))
		{
			System.out.println("Contact last name is displayed==PASS");
		}
		else
		{
			System.out.println("Contact last name is not displayed==FAIL");
		}
	}
	
	@Test(priority = 2)
	public void createContactWithSupportDate_Test() throws InterruptedException, EncryptedDocumentException, IOException {

		
		//Object Creation
		ExcelUtiltiy excelUtil=new ExcelUtiltiy();
		JavaUtility javaUtil=new JavaUtility();	

		//Read Test Script from Excel
		String lastname=excelUtil.getDataFromExcelFile("Contact", 1, 2);


		//POM Object
		HomePage homeObj=new HomePage(driver);
		ContactPage contObj=new ContactPage(driver);
		CreateNewContactPage createNewContObj=new CreateNewContactPage(driver);
		ContactInfoPage contInfoObj=new ContactInfoPage(driver);


		//2. Navigate to Contact module
		homeObj.getContactLink().click();

		//3. Click on "Create Contact" button
		contObj.getCreateContLink().click();

		//4. Enter all the details & create new Contact
		createNewContObj.getLastnameEdit().sendKeys(lastname);

		//Get dates
		String startDate=javaUtil.getStartDate("yyyy-MM-dd");

		String endDate=javaUtil.getEndDate("yyyy-MM-dd", 30);

		createNewContObj.getStartDateEdit().clear();
		createNewContObj.getStartDateEdit().sendKeys(startDate);

		createNewContObj.getEndDateEdit().clear();
		createNewContObj.getEndDateEdit().sendKeys(endDate);


		//Click Save
		createNewContObj.getSaveButton().click();


		//5. Verify start and end date is visbile in info page
		String actStartDate=contInfoObj.getStartDateText().getText();

		if(actStartDate.contains(startDate))
		{
			System.out.println(startDate+" is verified==PASS");
		}
		else
		{
			System.out.println(startDate+" is not verified==FAIL");
		}

		String actEndDate=contInfoObj.getEndDateText().getText();

		if(actEndDate.contains(endDate))
		{
			System.out.println(endDate+" is verified==PASS");
		}
		else
		{
			System.out.println(endDate+" is not verified==FAIL");
		}
		
	}
	
	@Test(priority = 3)
	public void createContactWithOrganization_Test() throws InterruptedException, EncryptedDocumentException, IOException {

		

		//Object Creation
		FileUtility fileUtil=new FileUtility();
		ExcelUtiltiy excelUtil=new ExcelUtiltiy();
		JavaUtility javaUtil=new JavaUtility();	
		WebdriverUtility webUtil=new WebdriverUtility();

		//Generate random number
		String alphaNum=javaUtil.getAlphaNumeric(5);

		//Read Test Script from Excel
		
		String data=excelUtil.getDataFromExcelFile("Contact", 7, 3);
		String orgName=data+alphaNum;
		String lastname=excelUtil.getDataFromExcelFile("Contact", 7, 2);


		//POM Object
		LoginPage logObj=new LoginPage(driver);
		HomePage homeObj=new HomePage(driver);
		OrganizationPage orgObj=new OrganizationPage(driver);
		CreateNewOrganizationPage createOrgObj=new CreateNewOrganizationPage(driver);
		OrganizationInfoPage orgInfoObj=new OrganizationInfoPage(driver);
		ContactPage contObj=new ContactPage(driver);
		CreateNewContactPage createNewContObj=new CreateNewContactPage(driver);
		ContactInfoPage contInfoObj=new ContactInfoPage(driver);
		
		driver.manage().window().maximize();

		//Implicit Wait
		webUtil.waitForPageToLoad(driver);


		//2. Navigate to organization module
		homeObj.getOrgLink().click();
		
		//3. Click on "Create Organization" button
		orgObj.getCreateOrgLink().click();

		//4. Enter all the details & create new Organization
		//Click Save button
		createOrgObj.createOrg(orgName);

		
		//5. Verify Organization name in header of the msg
		String actText=orgInfoObj.getOrgNameHeaderText().getText();

		if(actText.contains(orgName))
		{
			System.out.println(orgName+" is created==PASS");
		}
		else
		{
			System.out.println(orgName+" is not created==FAIL");
		}

		//5. Navigate to Contact module
		driver.navigate().refresh();
		homeObj.getContactLink().click();


		//6. Click on "Create Contact" button
		contObj.getCreateContLink().click();

		//7. Enter all the details & create new Contact
		createNewContObj.getLastnameEdit().sendKeys(lastname);

		//Select Organization
		createNewContObj.getOrgButton().click();


		//------------------------------------------------------------------
		String parentWinId=driver.getWindowHandle();
		Set<String> allWInId=driver.getWindowHandles();
		for(String id:allWInId)
		{
			if(!id.equals(parentWinId))
			{
				driver.switchTo().window(id);
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
				driver.findElement(By.xpath("//input[@name='search']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//a[@id='1']")).click();
			}
		}
		driver.switchTo().window(parentWinId);

		//------------------------------------------------------------------
		Thread.sleep(2000);

		//Click Save
		createNewContObj.getSaveButton().click();


		//5. Verify Conatact is visbile in info page
		String actContLastName= contInfoObj.getContLastnameText().getText();
		if(actContLastName.equals(lastname))
		{
			System.out.println("Contact last name is displayed==PASS");
		}
		else
		{
			System.out.println("Contact last name is not displayed==FAIL");
		}

		String actOrgName= contInfoObj.getOrgNameText().getText();

		if(actOrgName.equals(orgName))
		{
			System.out.println("Organization name is displayed==PASS");
		}
		else
		{
			System.out.println("Organization name is not displayed==FAIL");
		}
	}

}
