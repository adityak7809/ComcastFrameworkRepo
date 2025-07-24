package com.concast.crm.baseTest;


import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.concast.crm.generic.databaseUtility.DatbaseUtility;
import com.concast.crm.generic.fileUtility.FileUtility;
import com.concast.crm.generic.webdriverUtility.UtilityClassObject;
import com.concast.crm.objectRepositoryUtility.HomePage;
import com.concast.crm.objectRepositoryUtility.LoginPage;



/*
 * @author Aditya
 * Class Name: a_BaseClass
 * Uses: This class contains pre and post condition annotation methods 
 */

public class a_BaseClass {
	
	public WebDriver driver=null;
	public static WebDriver static_driver;
	public DatbaseUtility dbLib=new DatbaseUtility();
	public FileUtility fileLib=new FileUtility();
	
	
	@BeforeSuite(groups= {"SmokeTest", "RegressionTest"})
	public void configBS() throws SQLException
	{
		System.out.println("===Connect DB, Report config=== ");	
		dbLib.getDbConnection();
		
		
	}


	@BeforeClass(groups= {"SmokeTest", "RegressionTest"})
//	@Parameters("Browser")
	public void configBC() throws IOException
	{
		System.out.println("===Launch the browser===");
		
		String propData=fileLib.getDataFromPropertiesFile("browser");
		
		String browser=System.getProperty("browser",propData);
		
		if(browser.equals("headless"))
		{
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--headless");
			driver=new ChromeDriver(options);
			System.out.println("Opening Chrome headless browser....");
		}
		else if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
			System.out.println("Opening Chrome browser....");
		}
		else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
			System.out.println("Opening Firefox browser....");
		}
		else if(browser.equals("edge"))
		{
			driver=new EdgeDriver();
			System.out.println("Opening Edge browser....");
		}
		else//Default Browser
		{
			driver=new ChromeDriver();
			System.out.println("Opening Default browser....");
		}
		
		static_driver=driver;
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups= {"SmokeTest", "RegressionTest"})
	public void configBM() throws IOException
	{
		
		System.out.println("===Login===");
		String url=fileLib.getDataFromPropertiesFile("url");
		String username=fileLib.getDataFromPropertiesFile("username");
		String password=fileLib.getDataFromPropertiesFile("password");
		
		LoginPage logObj=new LoginPage(driver);
		logObj.loginToApp(url,username, password);	
		
	}

	@AfterMethod(groups= {"SmokeTest", "RegressionTest"})
	public void configAM() throws InterruptedException
	{
		System.out.println("===Logout===");
		HomePage homeObj = new HomePage(driver);
		homeObj.logout();
	}

	@AfterClass(groups= {"SmokeTest", "RegressionTest"})
	public void configAC()
	{
		System.out.println("===Close the browser===");
		driver.quit();
	}

	@AfterSuite(groups= {"SmokeTest", "RegressionTest"})
	public void configAS() throws SQLException, IOException
	{
		System.out.println("===Close DB, Report backup=== ");	
		dbLib.closeDbConnection();
		
		
	}
	

}
