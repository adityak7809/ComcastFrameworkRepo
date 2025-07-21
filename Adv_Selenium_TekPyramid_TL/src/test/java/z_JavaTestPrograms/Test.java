package z_JavaTestPrograms;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://testautomationpractice.blogspot.com/");
		
		
		//Date 1
		WebElement datePicker_1=driver.findElement(By.xpath("//input[@id='datepicker']"));

		Date sysDate_1=new Date();
		SimpleDateFormat sim_1 = new SimpleDateFormat("MM/dd/yyyy");
		
		String date_1 = sim_1.format(sysDate_1);
		datePicker_1.sendKeys(date_1);
		
		
		//Date 2
		WebElement datePicker_2=driver.findElement(By.xpath("//input[@id='txtDate']"));
		
		Date sysDate_2=new Date();
		SimpleDateFormat sim_2 = new SimpleDateFormat("dd/MM/yyyy");
		
		String date_2=sim_2.format(sysDate_2);
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='"+date_2+"'", datePicker_2);
		
		
		driver.findElement(By.id("name")).sendKeys("Aditya");
		
		
		 
	}

}
