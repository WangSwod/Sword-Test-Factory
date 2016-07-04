package com.sword.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	@FindBy(linkText = "Conference")
	WebElement conference_Btn;
	
	@FindBy(css="img.default-logo")
	WebElement commer_Logo;
	
	@FindBy(css="input.form-control")
	WebElement input_Search;
	
	@FindBy(css="span.commer-icon.commer-speakers")
	WebElement login_Btn;
	
	public HomePage(WebDriver driver){
		this.driver  = driver;
		if(!(this.driver.getTitle().equals("Simba Events"))){
			driver.get("http://54.254.213.247/");
		}	
	}
	
	

	public LoginPage clickOnLoginBtn() {
		login_Btn.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}

}
