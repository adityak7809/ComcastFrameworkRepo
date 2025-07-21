package c_GenericUtility_Implementation;

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

public class Test6_CreateContactWithOrganization {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {

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

		String data=excelObj.getDataFromExcelFile("Contact", 7, 3);
		String orgName=data+alphaNum;
		String lastname=excelObj.getDataFromExcelFile("Contact", 7, 2);


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
		Thread.sleep(1000);

		//4. Enter all the details & create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		Thread.sleep(2000);

		//Click Save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//5. Verify Organization name in header of the msg
		String actText=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

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


		//6. Click on "Create Contact" button
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

		//7. Enter all the details & create new Contact
		driver.findElement(By.name("lastname")).sendKeys(lastname);

		//Select Organization
		//		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		driver.findElement(By.xpath("//input[@name='account_name']/ancestor::td[@class='dvtCellInfo']/descendant::img")).click();


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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();



		//5. Verify Conatact is visbile in info page
		String actLastText=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if(actLastText.contains(lastname))
		{
			System.out.println(lastname+" Contact is created==PASS");
		}
		else
		{
			System.out.println(lastname+" Contact is not created==FAIL");
		}

		String actOrgName=driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText();
		if(actOrgName.trim().equals(orgName))
		{
			System.out.println(orgName+" Org Name is visible==PASS");
		}
		else
		{
			System.out.println(orgName+" Org Name is not visible==PASS");
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
