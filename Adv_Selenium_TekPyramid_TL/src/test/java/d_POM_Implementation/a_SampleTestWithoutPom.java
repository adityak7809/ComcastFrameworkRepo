package d_POM_Implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class a_SampleTestWithoutPom {
	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("http://49.249.28.218:8888");
		
		WebElement e1 = driver.findElement(By.name("user_name"));
		WebElement e2 = driver.findElement(By.name("user_password"));
		
		driver.navigate().refresh();
		
		e1.sendKeys("admin");
		e2.sendKeys("admin");
		
		WebElement e3 = driver.findElement(By.xpath("//input[@type='submit']"));
		e3.click();
		
	}

}
