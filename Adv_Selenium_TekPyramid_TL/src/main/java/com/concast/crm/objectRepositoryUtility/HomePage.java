package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.concast.crm.generic.webdriverUtility.JavaUtility;

public class HomePage {
	
	WebDriver driver;
    

	//Declaration-----------------
	@FindBy(linkText ="Organizations")
	private WebElement orgLink;

	@FindBy(linkText ="Contacts")
	private WebElement contactLink;
	
	@FindBy(xpath ="//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutButton;
	
	@FindBy(linkText ="Sign Out")
	private WebElement logoutLink;


	//Initialization---------------
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}
	
	
	public WebElement getLogoutButton() {
		return logoutButton;
	}
	
	public WebElement getLogoutLink() {
		return logoutLink;
	}


	public void logout() throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.moveToElement(getLogoutButton()).perform();
		Thread.sleep(500);
		JavaUtility javaUtil=new JavaUtility();
		javaUtil.jsClick(driver, getLogoutLink());
		
	}


}
