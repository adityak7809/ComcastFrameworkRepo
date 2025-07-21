package d_POM_Implementation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.concast.crm.generic.fileUtility.FileUtility;
import com.concast.crm.generic.webdriverUtility.WebdriverUtility;
import com.concast.crm.objectRepositoryUtility.AutoHealingPage;
import com.concast.crm.objectRepositoryUtility.HomePage;

public class c_AutoHealing {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver=null;
	     
		//Object Creation
		FileUtility fileObj=new FileUtility();
		WebdriverUtility webObj=new WebdriverUtility();
		
		//Read Common Data from Property file
		String browser=fileObj.getDataFromPropertiesFile("browser");
		String URL=fileObj.getDataFromPropertiesFile("url");
		String username=fileObj.getDataFromPropertiesFile("username");
		String password=fileObj.getDataFromPropertiesFile("password");


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
		AutoHealingPage autoHObj=new AutoHealingPage(driver);
		HomePage homeObj=new HomePage(driver);
		
		
		driver.manage().window().maximize();

		//Implicit Wait		
		webObj.waitForPageToLoad(driver);

		//Nevigate to URL
		driver.navigate().to(URL);
		
		//By using business method from POM class
		autoHObj.getUsernameEdit().sendKeys(username);
		autoHObj.getPasswordEdit().sendKeys(password);
		autoHObj.getLoginButton().click();
		
		Thread.sleep(1000);

		
		//6. Logout
		homeObj.logout();

		//Close browser
		driver.quit();
	
	}

}
