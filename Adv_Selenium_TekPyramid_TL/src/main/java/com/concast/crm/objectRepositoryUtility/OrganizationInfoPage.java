package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {


	@FindBy(xpath ="//span[@class='dvHeaderText']")
	private WebElement orgNameHeaderText;

	@FindBy(xpath = "//span[contains(@id,'dtlview_Organization Name')]")
	private WebElement orgNameText;
	
	@FindBy(xpath = "//span[@id='dtlview_Industry']")
	private WebElement industryText;
	
	@FindBy(xpath = "//span[@id='dtlview_Type']")
	private WebElement typeText;
	
	@FindBy(xpath = "//span[@id='dtlview_Phone']")
	private WebElement phoneText;

	


	//Initialization---------------
	public OrganizationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getOrgNameHeaderText() {
		return orgNameHeaderText;
	}


	public WebElement getOrgNameText() {
		return orgNameText;
	}
	
	public WebElement getPhoneText() {
		return phoneText;
	}
	
	public WebElement getIndustryText() {
		return industryText;
	}


	public WebElement getTypeText() {
		return typeText;
	}
	
	
}
