package com.concast.crm.generic.webdriverUtility;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Aditya
 * Contains: This class contains java resuable methods
 */
public class JavaUtility {


	/**
	 * Generate random numbers
	 * @param limit
	 * @return
	 */
	public int getRandomNumber(int limit)
	{
		Random random=new Random();
		int data=random.nextInt(limit);

		return data;
	}

	public String getAlphaNumeric(int length)
	{
		String Alpha_Numeric="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		SecureRandom random=new SecureRandom();
		StringBuilder sb=new StringBuilder(length);

		for(int i=0; i<=length; i++)
		{
			int index=random.nextInt(Alpha_Numeric.length());
			sb.append(Alpha_Numeric.charAt(index));
		}

		String data=sb.toString();
		return data;
	}


	public String getStartDate(String format)
	{
		Date sysDate=new Date();
		SimpleDateFormat sim=new SimpleDateFormat(format);
		String getDate=sim.format(sysDate);
		return getDate;
	}

	public String getEndDate(String format, int amount )
	{
		Date sysDate=new Date();
		SimpleDateFormat sim=new SimpleDateFormat(format);
		sim.format(sysDate);

		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, amount);
		String reqDate=sim.format(cal.getTime());

		return reqDate;

	}

	public String getTime() {

		Date sysDate=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("HHmmss");
		String time=sim.format(sysDate);
		
		return time;
	}
	
	public String getDate() {

		Date sysDate=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd_MM_yy");
		String date=sim.format(sysDate);
		
		return date;
	}
	
	



	public void jsClick(WebDriver driver, WebElement element) {
		if (element == null)
		{
			throw new IllegalArgumentException("Element cannot be null");
		}
		else 
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	public void jsScrollIntoView(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public void jsScrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void jsScrollToUp(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
	}

}
