package d_POM_Implementation;

import java.io.IOException;

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
import com.concast.crm.objectRepositoryUtility.HomePage;
import com.concast.crm.objectRepositoryUtility.LoginPage;

public class i_CreateContactWithSupportDate {
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

		//Read Test Script from Excel
		String lastname=excelUtil.getDataFromExcelFile("Contact", 1, 2);


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
		ContactPage contObj=new ContactPage(driver);
		CreateNewContactPage createNewContObj=new CreateNewContactPage(driver);
		ContactInfoPage contInfoObj=new ContactInfoPage(driver);

		driver.manage().window().maximize();

		//Implicit Wait
		webUtil.waitForPageToLoad(driver);

		driver.navigate().to(url);

		//1. Login
		logObj.loginToApp(username, password);	
		Thread.sleep(1000);


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



		//6. Logout
		WebdriverUtility act=new WebdriverUtility();
		act.moveToElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		Thread.sleep(500);
		driver.findElement(By.linkText("Sign Out")).click();

		//Close browser
		driver.quit();
	}

}
