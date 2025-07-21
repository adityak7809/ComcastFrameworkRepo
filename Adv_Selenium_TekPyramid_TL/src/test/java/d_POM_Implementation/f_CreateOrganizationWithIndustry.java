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
import com.concast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.concast.crm.objectRepositoryUtility.OrganizationPage;

public class f_CreateOrganizationWithIndustry {

	public static void main(String[] args) throws InterruptedException, IOException {

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

		String data=excelUtil.getDataFromExcelFile("Organization", 1, 2);
		String orgName=data+alphaNum;

		String indName=excelUtil.getDataFromExcelFile("Organization", 4, 3);
		String type=excelUtil.getDataFromExcelFile("Organization", 4, 4);

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
		createOrgObj.createOrg(orgName);

		webUtil.selectFromDropdown(createOrgObj.getIndustry(), indName);
		
		webUtil.selectFromDropdown(createOrgObj.getType(), type);
		

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


		//6. Logout
		homeObj.logout();

		//Close browser
		driver.quit();
	}

}
