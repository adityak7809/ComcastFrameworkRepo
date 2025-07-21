package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {

	   //Declaration-----------------
	
		@FindBy(name ="lastname")
		private WebElement lastnameEdit;
		
		@FindBy(xpath = "//input[@title='Save [Alt+S]']")
		private WebElement saveButton;
		
		@FindBy(xpath ="//input[@name='support_start_date']")
		private WebElement startDateEdit;
		
		@FindBy(xpath ="//input[@name='support_end_date']")
		private WebElement endDateEdit;
		
		@FindBy(xpath ="//input[@name='account_name']/ancestor::td[@class='dvtCellInfo']/descendant::img")
		private WebElement orgButton;
		
		
			
		
		//Initialization---------------
		public CreateNewContactPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		public WebElement getLastnameEdit() {
			return lastnameEdit;
		}


		public WebElement getStartDateEdit() {
			return startDateEdit;
		}


		public WebElement getEndDateEdit() {
			return endDateEdit;
		}


		public WebElement getSaveButton() {
			return saveButton;
		}


		public WebElement getOrgButton() {
			return orgButton;
		}
		
		
		
		

}
