package d_POM_Implementation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.concast.crm.generic.fileUtility.ExcelUtiltiy;
import com.concast.crm.generic.fileUtility.FileUtility;
import com.concast.crm.generic.webdriverUtility.JavaUtility;
import com.concast.crm.generic.webdriverUtility.WebdriverUtility;
import com.concast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.concast.crm.objectRepositoryUtility.HomePage;
import com.concast.crm.objectRepositoryUtility.LoginPage;
import com.concast.crm.objectRepositoryUtility.OrganizationPage;

public class d_HandleDynamicElementUsingPOM {
	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver=null;

		//Object Creation
		FileUtility fileObj=new FileUtility();
		ExcelUtiltiy excelObj=new ExcelUtiltiy();
		JavaUtility javaObj=new JavaUtility();	
		WebdriverUtility webObj=new WebdriverUtility();

		//Read Common Data from Property file

		String browser=fileObj.getDataFromPropertiesFile("browser");
		String URL=fileObj.getDataFromPropertiesFile("url");
		String username=fileObj.getDataFromPropertiesFile("username");
		String password=fileObj.getDataFromPropertiesFile("password");

		//Generate random number

		String alphaNum=javaObj.getAlphaNumeric(5);

		//Read Test Script from Excel


		String data=excelObj.getDataFromExcelFile("Organization", 10, 2);
		String orgName=data+alphaNum;

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
		


		driver.manage().window().maximize();

		//Implicit Wait		
		webObj.waitForPageToLoad(driver);

		//Nevigate to URL
		driver.navigate().to(URL);

		//By using business method from POM class
		logObj.loginToApp(username, password);	
		Thread.sleep(1000);

		//2. Navigate to organization module
		homeObj.getOrgLink().click();

		//3. Click on "Create Organization" button
		orgObj.getCreateOrgLink().click();

		//4. Enter all the details & create new Organization
		createOrgObj.createOrg(orgName);
		Thread.sleep(500);

		// Go back to Organization page
		homeObj.getOrgLink().click();
		
		// Search for Organization
		orgObj.getSearchBox().sendKeys(orgName);
		webObj.selectFromDropdown(orgObj.getDropdown(), 1);
		orgObj.getSearchNowButton().click();
		Thread.sleep(1000);
		
		
		// In dynamic WebTable, select and delete Org 
		String org_deleteName=orgObj.getOrgName().getText();
		if(org_deleteName.equals(orgName))
		{
			orgObj.getTickButton().click();
			Thread.sleep(500);
			orgObj.getDelButton().click();
			Thread.sleep(500);
			webObj.switchToAlert(driver, "accept");
			System.out.println(org_deleteName+" Org deleted successfully");
		}
		else
		{
			System.out.println(org_deleteName+" Org delete failed===FAILED===");
		}

		//6. Logout
		homeObj.logout();

		//Close browser
		driver.quit();

	}

}
