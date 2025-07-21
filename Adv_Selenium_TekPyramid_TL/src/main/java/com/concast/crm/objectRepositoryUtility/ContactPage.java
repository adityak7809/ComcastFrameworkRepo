package com.concast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	   //Declaration-----------------
	
		@FindBy(xpath ="//img[contains(@title,'Create Contact')]")
		private WebElement createContLink;
			
		
		//Initialization---------------
		public ContactPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		public WebElement getCreateContLink() {
			return createContLink;
		}

}
