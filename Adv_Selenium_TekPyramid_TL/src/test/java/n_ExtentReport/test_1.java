package n_ExtentReport;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class test_1 {
	
	public 	String filePath="D:\\Study\\Programs\\Java Programs\\Eclipse\\Adv_Selenium_TekPyramid_1\\AdvanceReport\\Report_Sample_1.html";

	public ExtentSparkReporter spark=null;
	public ExtentReports report=null;
	
	
	@BeforeSuite
	public void configBS()
	{
		spark=new ExtentSparkReporter(filePath);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Test Report");
		spark.config().setReportName("Demo");
		
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
	public void Sample_1()
	{
		ExtentTest test=report.createTest("Sample_1").assignAuthor("Noddy").assignCategory("Smoke").assignDevice("WIndpwos");
		test.pass("Test1");
	}
	

}
