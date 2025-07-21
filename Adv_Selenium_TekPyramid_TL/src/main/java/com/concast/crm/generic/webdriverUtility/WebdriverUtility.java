package com.concast.crm.generic.webdriverUtility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtility {

	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void waitForElementToPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchToChildWindow(WebDriver driver, String title )
	{
		String parentWinId=driver.getWindowHandle();
		Set<String> allWInId=driver.getWindowHandles();

		for(String id:allWInId)
		{
			driver.switchTo().window(id);
			if(driver.getTitle().contains(title))
			{
				
			}

		}
	}

	public void switchToParentWindow(WebDriver driver)
	{

	}

	//Overloaded methods-----------------
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}

	public void switchToFrame(WebDriver driver, String frameName)
	{
		driver.switchTo().frame(frameName);
	}

	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}

	public void switchToAlert(WebDriver driver, String action)
	{
		Alert alert=driver.switchTo().alert();

		switch(action.toLowerCase())
		{
		case "accept": alert.accept();
		break;
		case "dismiss": alert.dismiss();
		break;
		}
	}

	public void selectFromDropdown(WebElement element, String text)
	{
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}

	public void selectFromDropdown(WebElement element, int index)
	{
		Select select=new Select(element);
		select.selectByIndex(index);
	}

	public void moveToElement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void mouseClick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.click(element).perform();
	}

	public void rightClick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}

	public void doubleClick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}

	public void dragAndDrop(WebDriver driver, WebElement src, WebElement trgt)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(src, trgt).perform();
	}


}
