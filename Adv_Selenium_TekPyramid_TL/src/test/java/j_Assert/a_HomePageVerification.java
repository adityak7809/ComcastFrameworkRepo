package j_Assert;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class a_HomePageVerification {
	
	@Test
	public void verifyhomePageTitle_Test(Method mtd)
	{
		System.out.println(mtd.getName() + " Test Start");
		String expPageTitle="Home";
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://49.249.28.218:8888");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		Assert.fail();
		
		
		String actTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		
		Assert.assertEquals(expPageTitle, actTitle);
		
		
		driver.quit();
		
		System.out.println(mtd.getName()+" Test End");
		
		
	}
	
	@Test
	public void verifyHomePageLogo_Test(Method mtd)
	{
		System.out.println(mtd.getName() + " Test Start");
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://49.249.28.218:8888");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isDisplayed();
		
		Assert.assertTrue(status);
		
		driver.quit();
		
		System.out.println(mtd.getName() + " Test End");
		
		
	}

}
