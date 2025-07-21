package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.concast.crm.generic.webdriverUtility.WebdriverUtility;

public class LoginPage extends WebdriverUtility{
	
	WebDriver driver;
	
	//Declaration-----------------
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	//Initialization---------------
	public LoginPage(WebDriver driver)
	{   
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Utilization------------------
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	//Business Logic
	
	public void loginToApp(String username, String password)
	{ 
		getUsernameEdit().sendKeys(username);
		getPasswordEdit().sendKeys(password);
		getLoginButton().click();
	}
	
	public void loginToApp(String url, String username, String password)
	{   
		driver.manage().window().maximize();
		driver.get(url);
		waitForPageToLoad(driver);
		getUsernameEdit().sendKeys(username);
		getPasswordEdit().sendKeys(password);
		getLoginButton().click();
	}

	
}
