package m_Listners;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(a_ListenersImp.class)
public class a_Flipkart {
	
	public WebDriver driver;
	public static WebDriver static_driver;

	@Test
	public void verifyPageLogo(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test start", true);
		
		driver=new ChromeDriver();
		static_driver=driver;
		driver.manage().window().maximize();
		
		driver.get("https://www.flipkart.com/");
		
		boolean status=driver.findElement(By.xpath("//img[@title='Flipkart']")).isDisplayed();
		
		Assert.assertEquals(status, false);
		
		Reporter.log(mtd.getName()+" Test end", true);
		
		driver.quit();
	}

}
