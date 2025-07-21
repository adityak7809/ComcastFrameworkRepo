package l_Screenshots;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import com.concast.crm.generic.webdriverUtility.JavaUtility;

public class b_ElementScreenshot {
	
   @Test
   public void screenshot() throws IOException
   {
	   JavaUtility javaUtil=new JavaUtility();
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		
		WebElement logo=driver.findElement(By.xpath("//img[@title='Flipkart']"));
		
		File src=logo.getScreenshotAs(OutputType.FILE);
		
		File tgt=new File("./Screenshot/"+javaUtil.getDate()+"_"+javaUtil.getTime()+".png"+"");
		
		
		FileHandler.copy(src, tgt);
		
		driver.quit();
		
		
   }
	
	

}
