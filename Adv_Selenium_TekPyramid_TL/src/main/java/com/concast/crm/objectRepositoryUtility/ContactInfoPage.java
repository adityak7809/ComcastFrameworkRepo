package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	@FindBy(xpath = "//span[@id='dtlview_Last Name']")
	private WebElement contLastnameText;
	
	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']")
	private WebElement orgNameText;
	
	
	@FindBy(xpath = "//span[@id='dtlview_Support Start Date']")
	private WebElement startDateText;
	
	@FindBy(xpath = "//span[@id='dtlview_Support End Date']")
	private WebElement endDateText;


	//Initialization---------------
	public ContactInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getContLastnameText() {
		return contLastnameText;
	}


	public WebElement getStartDateText() {
		return startDateText;
	}


	public WebElement getEndDateText() {
		return endDateText;
	}


	public WebElement getOrgNameText() {
		return orgNameText;
	}
	
	
}
