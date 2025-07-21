package d_POM_Implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class b_SampleTestWithPom {
	
	@FindBy(name="user_name")
	WebElement e1;
	
	@FindBy(name="user_password")
	WebElement e2;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement e3;
	
	@Test
	public void SampleTest() {
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://49.249.28.218:8888");
		
		b_SampleTestWithPom s=PageFactory.initElements(driver, b_SampleTestWithPom.class);
		
		s.e1.sendKeys("admin");
		s.e2.sendKeys("admin");
		
		driver.navigate().refresh();
		
		s.e1.sendKeys("admin");
		s.e2.sendKeys("admin");
		
		s.e3.click();
		
	}

}
