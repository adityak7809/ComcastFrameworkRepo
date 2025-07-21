package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	

	//Declaration-----------------
	
	@FindBy(xpath ="//img[contains(@title,'Create Organization')]")
	private WebElement createOrgLink;
	
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchBox;

	@FindBy(xpath = "//div[@id='basicsearchcolumns_real']//select[@name='search_field']")
	private WebElement dropdown;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchNowButton;
	
	@FindBy(xpath = "//a[@title='Organizations']")
	private WebElement orgName;
	
	@FindBy(xpath = "//input[@name='selected_id']")
	private WebElement tickButton;
	
	@FindBy(xpath = "//a[contains(text(),'del')]")
	private WebElement delButton;


	
	//Initialization---------------
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	public WebElement getCreateOrgLink() {
		return createOrgLink;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getDropdown() {
		return dropdown;
	}
	
	
	
	public WebElement getSearchNowButton() {
		return searchNowButton;
	}

	
	public WebElement getOrgName() {
		return orgName;
	}


	public WebElement getTickButton() {
		return tickButton;
	}
	
	
	public WebElement getDelButton() {
		return delButton;
	}
	
	

}
