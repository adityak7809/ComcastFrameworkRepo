package n_ExtentReport;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class a_SampleReportTest {


	@Test
	public void createContactTest(Method mtd) throws IOException
	{
		String filePath="./AdvanceReport/Report.html";
		
		//Spark Report Config
		ExtentSparkReporter spark=new ExtentSparkReporter(filePath);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");

		//Add Env information and create test
		ExtentReports report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WIndows-11");
		report.setSystemInfo("BROWSER", "Chrome 1.1.15");
		report.setSystemInfo("AUTHOR", "Aditya");
		
		
		

		//Create Test
//		ExtentTest test=report.createTest("createContactTest").assignAuthor("Aditya_Kumar").assignCategory("Smoke").assignDevice("Windows-11", "Chrome 138.0.7204.101");
		ExtentTest test=report.createTest("createContactTest");
		
		test.log(Status.INFO, "Login to application");
		test.log(Status.INFO, "Navigate to Contact Page");
		test.log(Status.INFO, "Create Contact");

		if("HDFC".equals("HDFC"))
		{
			test.pass("Contact is created");
		}
		else
		{
			test.fail("Contact is not created");
		}
		
		test.info("Logout from Application");
	

		 //Unless you call this method, your reports will not be written with logs
		report.flush();
		
		System.out.println("Opening report....");
		
		//Open report
		File file = new File(filePath);
		Desktop.getDesktop().open(file);
		
		
	}

}
