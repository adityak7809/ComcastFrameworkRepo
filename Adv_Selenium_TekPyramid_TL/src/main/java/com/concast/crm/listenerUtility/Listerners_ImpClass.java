package com.concast.crm.listenerUtility;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.concast.crm.baseTest.a_BaseClass;
import com.concast.crm.generic.webdriverUtility.UtilityClassObject;

public class Listerners_ImpClass implements ITestListener, ISuiteListener {


	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	String time=new Date().toString().replace(" ", "_").replace(":", "_");
	String filePath="./AdvanceReport/Report_"+time+".html";


	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		
		
		
		spark=new ExtentSparkReporter(filePath);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Test Report");
		

		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "Chrome 1.123");

	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");

		report.flush();
		
		File file=new File(filePath);
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

	@Override
	public void onTestStart(ITestResult result) {
	    test=report.createTest(result.getName()).assignAuthor("Aditya");
	    UtilityClassObject.setTest(test);
	    test.info(result.getName()+" ======>STARTED<======");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    test.info(result.getName()+" ======>COMPLETED<======");

	}

	@Override
	public void onTestFailure(ITestResult result) {
	    test.fail(result.getName()+" ======>FAILED<======");

		String testName=result.getName();

		String time=new Date().toString().replace(" ", "_").replace(":", "_");

		TakesScreenshot ts=(TakesScreenshot) a_BaseClass.static_driver;
//		File src=ts.getScreenshotAs(OutputType.FILE);
//		File tgt=new File("./Screenshot/"+testName+"_"+time+".png");
//		src.renameTo(tgt);
		
		//Attach screenshot to Extent report
		String file=ts.getScreenshotAs(OutputType.BASE64);
		
		test.addScreenCaptureFromBase64String(file, result.getName()+"_"+time);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}







}
