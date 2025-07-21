package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutoHealingPage {

	@FindAll({@FindBy(name="user_name"), @FindBy(xpath="//input[@name='user_name']")})
	private WebElement usernameEdit;

	@FindAll({@FindBy(name="user_password"), @FindBy(xpath="//input[@name='user_password']")})
	private WebElement passwordEdit;

	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath="//input[@id='submitButton']") })
	private WebElement loginButton;
	

	//Initialization---------------
	public AutoHealingPage(WebDriver driver)
	{
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

}
