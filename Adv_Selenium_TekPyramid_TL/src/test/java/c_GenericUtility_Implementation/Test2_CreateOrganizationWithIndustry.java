package c_GenericUtility_Implementation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.concast.crm.generic.fileUtility.ExcelUtiltiy;
import com.concast.crm.generic.fileUtility.FileUtility;
import com.concast.crm.generic.webdriverUtility.JavaUtility;
import com.concast.crm.generic.webdriverUtility.WebdriverUtility;

public class Test2_CreateOrganizationWithIndustry {

	public static void main(String[] args) throws InterruptedException, IOException {

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

		String data=excelObj.getDataFromExcelFile("Organization", 1, 2);
		String orgName=data+alphaNum;
		
		String indName=excelObj.getDataFromExcelFile("Organization", 4, 3);
		String type=excelObj.getDataFromExcelFile("Organization", 4, 4);

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

		//2. Navigate to organization module
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		boolean orgHeader=driver.findElement(By.xpath("//a[@class='hdrLink' and text()='Organizations']")).isDisplayed();

		if(orgHeader==true)
		{
			System.out.println("Organization Page is displayed succesfully");
		}
		else
		{
			System.out.println("Organization Page is not displayed");
		}


		//3. Click on "Create Organization" button
		driver.findElement(By.xpath("//img[contains(@title,'Create Organization')]")).click();
		boolean createOrgHeader=driver.findElement(By.xpath("//span[text()='Creating New Organization']")).isDisplayed();

		if(createOrgHeader==true)
		{
			System.out.println("Create new organization Page is displayed succesfully");
		}
		else
		{
			System.out.println("Create new organization Page is not displayed");
		}

		//4. Enter all the details & create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		Select selectIndustry=new Select(driver.findElement(By.xpath("//select[@name='industry']")));
		selectIndustry.selectByVisibleText(indName);

		Select selectType=new Select(driver.findElement(By.xpath("//select[@name='accounttype']")));
		selectType.selectByVisibleText(type);

		//Click Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();


		//5. Verify Industry and type name is visbile in info page
		String actText=driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();

		if(actText.contains(indName))
		{
			System.out.println(indName+ " Industry name is visible==PASS");
		}
		else
		{
			System.out.println(indName+ " Industry name is not visible==FAIL");
		}

		String actType= driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if(actType.equals(type))
		{
			System.out.println(type+" Type name is displayed==PASS");
		}
		else
		{
			System.out.println(type+" Type name is not displayed==FAIL");
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
