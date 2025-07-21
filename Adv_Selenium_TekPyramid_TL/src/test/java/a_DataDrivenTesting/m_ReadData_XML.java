package a_DataDrivenTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class m_ReadData_XML {
	
	//Approach 1----------------------------------------------
	
	@Test(priority = 0)
	public void readData(XmlTest test1)
	{
	  System.out.println(test1.getParameter("browser"));
	  System.out.println(test1.getParameter("url"));
	  System.out.println(test1.getParameter("un"));
	  System.out.println(test1.getParameter("pwd"));
	}


	//Approach 2----------------------------------------------
	
	@Parameters({"browser", "url", "un", "pwd"})
   @Test(priority = 1)
   public void read(String browser, String URL, String un, String pwd) throws InterruptedException 
   {
	   WebDriver driver=null;

		String bro= browser;
		String url=URL;
		String username=un;
		String password=pwd;
		
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
		driver.navigate().to(url);

		//Enter data in Username Textfiled
		driver.findElement(By.name("user_name")).sendKeys(username);
		Thread.sleep(1000);

		//enter data in Password textfield
		driver.findElement(By.name("user_password")).sendKeys(password);
		Thread.sleep(1000);

		//click Login button
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.quit();
		
		
	   
   }
}