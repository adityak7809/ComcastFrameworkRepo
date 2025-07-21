package b_Practice1_OrgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class a_CreateOrganizationWithIndustryTest2 {

	public static void main(String[] args) throws InterruptedException, IOException {

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

		String data=book.getSheet("Organization").getRow(4).getCell(2).toString();
		String orgName=data+rabdomNum;
		String indName=book.getSheet("Organization").getRow(4).getCell(3).toString();
		String type=book.getSheet("Organization").getRow(4).getCell(4).toString();


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
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(500);
		driver.findElement(By.linkText("Sign Out")).click();

		//Close browser
		driver.quit();
	}

}
