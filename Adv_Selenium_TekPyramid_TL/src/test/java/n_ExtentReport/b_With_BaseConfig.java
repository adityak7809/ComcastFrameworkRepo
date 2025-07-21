package n_ExtentReport;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class b_With_BaseConfig {

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

		ExtentTest test=report.createTest(mtd.getName()).assignAuthor("Aditya").assignCategory("Smoke").assignDevice("Windows-11", "Chrome 1.12");
		test.info("Login to application");
		test.info("Navigate to Contact Page");
		test.info("Create contact");

		if("HDFC".equals("HDFC"))
		{
			test.pass("Contact is created");
		}
		else
		{
			test.fail("Contact is not created");
		}

		test.info("Logout to application");

	}

	@Test
	public void createContactWithOrg(Method mtd) throws IOException
	{
		ExtentTest test=report.createTest(mtd.getName()).assignAuthor("Oggy").assignCategory("Regression").assignDevice("Windows-11", "Firefox 1.54");

		test.info("Login to application");
		test.info("Navigate to Contact Page");
		test.info("Create contact with Org");

		if("Googlle".equals("Google"))
		{
			test.pass("Contact is created");
		}
		else
		{
			test.fail("Contact is not created");
		}

		test.info("Logout to application");

	}

	@Test
	public void createContactWithPhoneNumber(Method mtd) throws IOException
	{
		ExtentTest test=report.createTest(mtd.getName()).assignAuthor("Jack").assignCategory("Integration").assignDevice("Windows-11", "Edge 1.11");

		test.info("Login to application");
		test.info("Navigate to Contact Page");
		test.info("Create contact with phone number");

		if("9876543210".equals("9876543210"))
		{
			test.pass("Contact is created");
		}
		else
		{
			test.fail("Contact is not created");
		}

		test.info("Logout to application");

	}

}
