package n_ExtentReport;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class c_Add_Screenshot {

	public 	String filePath="D:\\Study\\Programs\\Java Programs\\Eclipse\\Adv_Selenium_TekPyramid_1\\AdvanceReport\\Report_createContact.html";

	public ExtentSparkReporter spark;
	public ExtentReports report;
	


	@BeforeSuite
	public void configBS()
	{

		spark=new ExtentSparkReporter(filePath);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Test Report");

		report=new ExtentReports();
		report.attachReporter(spark);

	}

	@AfterSuite
	public void configAS() throws IOException
	{
		report.flush();

		Desktop.getDesktop().open(new File(filePath));
	}



	@Test
	public void createContact(Method mtd) throws IOException
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8888/");
		TakesScreenshot ts=(TakesScreenshot) driver;
		String file=ts.getScreenshotAs(OutputType.BASE64);
		
		ExtentTest test=report.createTest(mtd.getName()).assignAuthor("Aditya").assignCategory("Smoke").assignDevice("Windows-11", "Chrome 1.12");
		
		test.info("Login to application");
		test.info("Navigate to Contact Page");
		test.info("Create contact");

		if("HDFCd".equals("HDFC"))
		{
			test.pass("Contact is created");
		}
		else
		{
			test.fail("Contact is not created");
			test.addScreenCaptureFromBase64String(file, "ErrorFile");
		}

		test.info("Logout to application");
		
		driver.close();

	}

	

}
