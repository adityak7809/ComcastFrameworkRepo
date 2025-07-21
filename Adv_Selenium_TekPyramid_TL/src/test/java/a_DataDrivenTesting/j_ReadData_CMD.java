package a_DataDrivenTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class j_ReadData_CMD {

	@Test
	public void SeleniumTest() throws InterruptedException {

       //Run Maven cmnd in CMD
	   //mvn -Dtest=j_ReadData_CMD test -Dbrowser=chrome -Durl=http://49.249.28.218:8888 -Dusername=admin -Dpassword=admin

		WebDriver driver=null;

		String browser=System.getProperty("browser");
		String url=System.getProperty("url"); // http://49.249.28.218:8888
		String un=System.getProperty("username"); 
		String pwd=System.getProperty("password");

		System.out.println(browser);
		System.out.println(url);
		System.out.println(un);
		System.out.println(pwd);

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
		driver.findElement(By.name("user_name")).sendKeys(un);
		Thread.sleep(1000);

		//enter data in Password textfield
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		Thread.sleep(1000);

		//click Login button
		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}

}
