package i_ParallelExecution;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Ecommerce {
	
	@Test
	public void myntra_Test() throws InterruptedException {

		WebDriver driver=new ChromeDriver();
		driver.get("https://www.myntra.com/");

		Thread.sleep(4000);

		// locate search button, add querry and click on submit
		driver.findElement(By.xpath("//*[@placeholder='Search for products, brands and more']")).sendKeys("tshirt");
		driver.findElement(By.xpath("//a[@class='desktop-submit']")).click();
		Thread.sleep(2000);

		//Extract element address
		List<String> prodName=new ArrayList<>();
		List<WebElement> elementAddrsss=  driver.findElements(By.xpath("//ul[@class='results-base']/li[@class='product-base']/a/div[2]/h4"));
		for(WebElement el:elementAddrsss)
		{
			prodName.add(el.getText());
		}

		//Extract Product name and Price
		for(int i=0; i<prodName.size(); i=i+2)
		{
			String name=prodName.get(i);
			System.out.println("Product Name: "+name);
			//Based on Dependency
			String dynamicAddress = "//h4[text()='"+name+"']/../div/span";

			String prodPrice = driver.findElement(By.xpath(dynamicAddress)).getText();
			System.out.println(prodPrice);
			System.out.println("*********************");
		}


		driver.quit();

	}
	
	@Test
	public void flipkart_Test() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.navigate().to("https://www.flipkart.com/");
		
		Thread.sleep(4000);
		
		//Locate the search button, add querry and click submit
		driver.findElement(By.name("q")).sendKeys("mobiles");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		
		//Extract element name
		String val1=driver.findElement(By.xpath("//div[text()='SAMSUNG Galaxy F06 5G (Lit Violet, 128 GB)']/../../div[2]/div/div/div")).getText();
		System.out.println(val1);
		
		String val2=driver.findElement(By.xpath("//div[text()='SAMSUNG Galaxy F06 5G (Bahama Blue, 128 GB)']/../../div[2]/div/div/div")).getText();
		System.out.println(val2);
		
		String val3=driver.findElement(By.xpath("//div[text()='MOTOROLA g05 (Plum Red, 64 GB)']/../../div[2]/div/div/div")).getText();
		System.out.println(val3);
		
		String val4=driver.findElement(By.xpath("//div[text()='realme C61 (Safari Green, 128 GB)']/../../div[2]/div/div/div")).getText();
		System.out.println(val4);
		
		String val5=driver.findElement(By.xpath("//div[text()='SAMSUNG Galaxy F06 5G (Bahama Blue, 128 GB)']/../../div[2]/div/div/div")).getText();
		System.out.println(val5);
		
		
		driver.quit();
		
	}
	
	@Test
	public void demo_Test() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.tutorialspoint.com/selenium/practice/radio-button.php");
		
		Thread.sleep(4000);
		
		//Identify Element
		WebElement el1=driver.findElement(By.xpath("//input[@onclick='show2();']"));
		WebElement el2=driver.findElement(By.xpath("//label[text()='Yes']"));
		
		//Validation
		boolean flag1=el1.isEnabled();
		boolean flag2=el2.isDisplayed();
		
		
		if(flag1==true && flag2==true)
		{
			//Perform Action
			el1.click();
		}
		
		boolean flag3=el1.isSelected();
		
		if(flag3==true)
		{
			String str=driver.findElement(By.xpath("//div[@id='check']")).getText();
			System.out.println(str);
		}
		
		driver.quit();
	}
	

}
