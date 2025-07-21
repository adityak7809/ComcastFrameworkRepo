package d_POM_Implementation;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

public class j_CreateContactWithOrganization {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {

		WebDriver driver=null;

		//Object Creation
		FileUtility fileUtil=new FileUtility();
		ExcelUtiltiy excelUtil=new ExcelUtiltiy();
		JavaUtility javaUtil=new JavaUtility();	
		WebdriverUtility webUtil=new WebdriverUtility();

		//Read Common Data from Property file
		String browser=fileUtil.getDataFromPropertiesFile("browser");
		String url=fileUtil.getDataFromPropertiesFile("url");
		String username=fileUtil.getDataFromPropertiesFile("username");
		String password=fileUtil.getDataFromPropertiesFile("password");

		//Generate random number
		String alphaNum=javaUtil.getAlphaNumeric(5);

		//Read Test Script from Excel
		
		String data=excelUtil.getDataFromExcelFile("Contact", 7, 3);
		String orgName=data+alphaNum;
		String lastname=excelUtil.getDataFromExcelFile("Contact", 7, 2);


		//Open browser
		
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
			System.out.println("Opening Chrome browser....");
		}

		else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
			System.out.println("Opening Firefox browser....");
		}
		else if(browser.equals("edge"))
		{
			driver=new EdgeDriver();
			System.out.println("Opening Edge browser....");
		}
		else//Default Browser
		{
			driver=new ChromeDriver();
			System.out.println("Opening Default browser....");
		}
		

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

		//Nevigate to URL
		driver.navigate().to(url);


		//1. Login
		logObj.loginToApp(username, password);	
		Thread.sleep(1000);

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
//		driver.navigate().refresh();
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

		String actOrgName= orgInfoObj.getOrgNameText().getText();

		if(actOrgName.equals(orgName))
		{
			System.out.println("Organization name is displayed==PASS");
		}
		else
		{
			System.out.println("Organization name is not displayed==FAIL");
		}


		//6. Logout
		homeObj.logout();

		//Close browser
		driver.quit();
	}

}
