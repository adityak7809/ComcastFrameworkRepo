 package m_Listners;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.concast.crm.generic.webdriverUtility.JavaUtility;

public class a_ListenersImp implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result)
	{
		String testName=result.getName();
		
		JavaUtility javaUtil=new JavaUtility();
		
		TakesScreenshot ts = (TakesScreenshot) a_Flipkart.static_driver;

		File src=ts.getScreenshotAs(OutputType.FILE);

		File tgt=new File("./Screenshot/"+testName+"_"+javaUtil.getDate()+"_"+javaUtil.getTime()+".png"+"");
		src.renameTo(tgt);
		
		Reporter.log("Screenshot taken_"+testName, true);
	}


}
