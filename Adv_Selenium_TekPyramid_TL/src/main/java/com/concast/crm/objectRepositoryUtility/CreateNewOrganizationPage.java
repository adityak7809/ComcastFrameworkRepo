package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrganizationPage {

	//Declaration-----------------
		@FindBy(name ="accountname")
		private WebElement orgNameEdit;
		
		@FindBy(xpath ="//input[@name='phone']")
		private WebElement phoneEdit;


		@FindBy(name = "industry")
		private WebElement industry;
		
		@FindBy(name = "accounttype")
		private WebElement type;

		@FindBy(xpath = "//input[@title='Save [Alt+S]']")
		private WebElement saveButton;
		
		

		
		

		//Initialization---------------
		public CreateNewOrganizationPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		public WebElement getOrgNameEdit() {
			return orgNameEdit;
		}
		
		public WebElement getPhoneEdit() {
			return phoneEdit;
		}


		public WebElement getSaveButton() {
			return saveButton;
		}
		
		public void createOrg(String orgName)
		{
			orgNameEdit.sendKeys(orgName);
			getSaveButton().click();
		}


		public WebElement getIndustry() {
			return industry;
		}


		public WebElement getType() {
			return type;
		}
		
		
	

}
