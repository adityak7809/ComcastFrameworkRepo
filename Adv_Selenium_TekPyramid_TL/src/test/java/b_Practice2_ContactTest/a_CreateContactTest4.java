package b_Practice2_ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class a_CreateContactTest4 {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {

		//Read Common Data from Property file
		FileInputStream fio=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties prop=new Properties();
		prop.load(fio);

		String browser=prop.getProperty("browser");
		String URL=prop.getProperty("url");
		String username=prop.getProperty("username");
		String password=prop.getProperty("password");

		//Generate random number
		Random ran=new Random();
		int rabdomNum=ran.nextInt(1000);

		//Read Test Script from Excel
		FileInputStream fis=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook book=WorkbookFactory.create(fis);

		String lastname=book.getSheet("Contact").getRow(1).getCell(2).toString();

		book.close();

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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


		//Click Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();



		//5. Verify Conatact is visbile in info page
		String actText=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if(actText.contains(lastname))
		{
			System.out.println(lastname+" is created==PASS");
		}
		else
		{
			System.out.println(lastname+" is not created==FAIL");
		}

		String actContLastName= driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		if(actContLastName.equals(lastname))
		{
			System.out.println("Contact last name is displayed==PASS");
		}
		else
		{
			System.out.println("Contact last name is not displayed==FAIL");
		}


		//6. Logout
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(500);
		driver.findElement(By.linkText("Sign Out")).click();

		//Close browser
		driver.quit();
	}

}
