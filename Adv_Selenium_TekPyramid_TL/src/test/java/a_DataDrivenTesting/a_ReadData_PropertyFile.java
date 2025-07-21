package a_DataDrivenTesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import UtilityFile.ReadPropertiesFile;

public class a_ReadData_PropertyFile {



	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriver driver=null;;

		//fetch data from properties file
		ReadPropertiesFile prop=new ReadPropertiesFile();


		String browser=prop.readData("browser");
		String URL=prop.readData("url");
		String username=prop.readData("username");
		String password=prop.readData("password");

		String orgName="QSpider_TekPyramid_Online";

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

		//Enter data in Username Textfiled
		driver.findElement(By.name("user_name")).sendKeys(username);
		Thread.sleep(1000);

		//enter data in Password textfield
		driver.findElement(By.name("user_password")).sendKeys(password);
		Thread.sleep(1000);

		//click Login button
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		//click Organization buttton
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();

		//click on Create Organization
		driver.findElement(By.xpath("//img[contains(@title,'Create Organization')]")).click();

		//enter data in Organization name textfield
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		//click save button
		driver.findElement(By.xpath("(//td/div/input[@name='button'])[1]")).click();
		Thread.sleep(1000);

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

		//		driver.switchTo().alert().accept();


		driver.close();

	}

}
