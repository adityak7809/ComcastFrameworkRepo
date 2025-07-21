package a_DataDrivenTesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import UtilityFile.ReadExcelFile;
import UtilityFile.ReadPropertiesFile;

public class f_Assignment {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver=null;

		/* Note:- 
		 * Common data should not be hardcoded 
		 * Test Script should be able to run in different browsers with minimal chnages
		 * After the execution, write ORG_ID back to excel
		 */

		ReadPropertiesFile prop=new ReadPropertiesFile();
		String browser=prop.readData("browser");
		String URL=prop.readData("url");
		String username=prop.readData("username");
		String password=prop.readData("password");

		ReadExcelFile exObj=new ReadExcelFile();
		String orgName=exObj.readSingleData("Org", 3, 2);

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
		driver.manage().window().maximize();

		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//Nevigate to URL
		driver.navigate().to(URL);

		//1. Login
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		//2. Navigate to organization module
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();

		//3. Click on "Create Organization" button
		driver.findElement(By.xpath("//img[contains(@title,'Create Organization')]")).click();

		//4. Enter all the details & create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		driver.findElement(By.xpath("(//td/div/input[@name='button'])[1]")).click();
		Thread.sleep(1000);

		//5. Verify Organization name in header of the msg
		String expText=orgName;
		String actText=driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		if(expText.equals(actText))
		{
			System.out.println("Organization name is visbile in header");
		}
		else
		{
			System.out.println("Organization name is not visible in header");
		}

		// Fetch the organization No
		String orgNo=driver.findElement(By.xpath("(//td[@class='dvtCellInfo'])[2]")).getText();

		//Write Organization No to excel
		exObj.writeData("Org", 3, 3, orgNo);

		//6. Logout
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(500);
		driver.findElement(By.linkText("Sign Out")).click();

		//Close browser
		driver.quit();


	}

}
