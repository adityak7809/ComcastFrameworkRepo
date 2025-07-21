package a_DataDrivenTesting;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class k_ReadData_JSON {

	
	
	public static void main(String[] args) throws IOException, ParseException, InterruptedException {

		WebDriver driver=null;
		
		
		//1. Parse JSON physical file into Java object using JsonParse class
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader("./src/test/resources/AppCommonData.json"));

		//Convert java object into JsonObject using downcasting
		JSONObject map=(JSONObject)obj;

		//Read data-----------------------------------------------------
		String browser=map.get("browser").toString();
		String url=map.get("url").toString();
		String username=map.get("username").toString();
		String password=map.get("password").toString();
		int timeout=Integer.parseInt(map.get("timeout").toString());
		
		

		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

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
		
		driver.close();


	}

}
