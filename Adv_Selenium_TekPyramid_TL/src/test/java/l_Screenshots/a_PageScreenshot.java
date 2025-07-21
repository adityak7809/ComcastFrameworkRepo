package l_Screenshots;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import com.concast.crm.generic.webdriverUtility.JavaUtility;



public class a_PageScreenshot {
	
	@Test
	public void pageScreenshot() throws IOException
	{
		JavaUtility javaUtil=new JavaUtility();
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		
//		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		
		TakesScreenshot ts=(TakesScreenshot) driver;

		File src=ts.getScreenshotAs(OutputType.FILE);
		
		File tgt=new File("./Screenshot/"+javaUtil.getDate()+"_"+javaUtil.getTime()+".png"+"");
		
//		src.renameTo(tgt);
		
//		FileUtils.copyFile(src,tgt);
		
		FileHandler.copy(src, tgt);
		
		driver.quit();
		
	}

}
