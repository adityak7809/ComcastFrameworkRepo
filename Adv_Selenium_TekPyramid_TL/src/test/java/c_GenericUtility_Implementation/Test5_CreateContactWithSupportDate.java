package c_GenericUtility_Implementation;

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

public class Test5_CreateContactWithSupportDate {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {

		//Read Common Data from Property file
		FileUtility fileObj=new FileUtility();

		String browser=fileObj.getDataFromPropertiesFile("browser");
		String URL=fileObj.getDataFromPropertiesFile("url");
		String username=fileObj.getDataFromPropertiesFile("username");
		String password=fileObj.getDataFromPropertiesFile("password");

		//Generate random number
		JavaUtility javaObj=new JavaUtility();
		String alphaNum=javaObj.getAlphaNumeric(5);

		//Read Test Script from Excel
		ExcelUtiltiy excelObj=new ExcelUtiltiy();

		String lastname=excelObj.getDataFromExcelFile("Contact", 1, 2);


		//Open browser
		WebDriver driver=null;
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
		driver.manage().window().maximize();

		//Implicit Wait
		WebdriverUtility webObj=new WebdriverUtility();
		webObj.waitForPageToLoad(driver);

		//Nevigate to URL
		driver.navigate().to(URL);

		//Verify login page must be displayed
		String expLoginPageTitle="vtiger CRM 5 - Commercial Open Source CRM";
		String actLoginPageTitle=driver.getTitle();

		if(expLoginPageTitle.equals(actLoginPageTitle))
		{
			System.out.println("Login Page is displayed successfully");
		}
		else
		{
			System.out.println("Login Page is not displayed");
		}

		//1. Login
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(1000);

		//Verify Home Page must be displayed
		String actHomePageTitle=driver.getTitle();

		if(actHomePageTitle.contains("Home"))
		{
			System.out.println("Home Page is displayed successfully");
		}
		else
		{
			System.out.println("Home Page is not displayed");
		}

		//2. Navigate to Contact module
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		boolean conHeader=driver.findElement(By.xpath("//a[@class='hdrLink' and text()='Contacts']")).isDisplayed();

		if(conHeader==true)
		{
			System.out.println("Contacts Page is displayed succesfully");
		}
		else
		{
			System.out.println("Contacts Page is not displayed");
		}


		//3. Click on "Create Contact" button
		driver.findElement(By.xpath("//img[contains(@title,'Create Contact')]")).click();
		boolean createContHeader=driver.findElement(By.xpath("//span[text()='Creating New Contact']")).isDisplayed();

		if(createContHeader==true)
		{
			System.out.println("Create new Contact Page is displayed succesfully");
		}
		else
		{
			System.out.println("Create new Contact Page is not displayed");
		}

		//4. Enter all the details & create new Contact
		driver.findElement(By.name("lastname")).sendKeys(lastname);

		//Get dates
		String startDate=javaObj.getStartDate("yyyy-MM-dd");

		String endDate=javaObj.getEndDate("yyyy-MM-dd", 30);

		driver.findElement(By.xpath("//input[@name='support_start_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_start_date']")).sendKeys(startDate);

		driver.findElement(By.xpath("//input[@name='support_end_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_end_date']")).sendKeys(endDate);


		//Click Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();



		//5. Verify start and end date is visbile in info page
		String actStartDate=driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();

		if(actStartDate.contains(startDate))
		{
			System.out.println(startDate+" is verified==PASS");
		}
		else
		{
			System.out.println(startDate+" is not verified==FAIL");
		}

		String actEndDate=driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();

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
