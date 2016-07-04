package com.sword.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import jxl.Workbook;

public class LoginPage {
	WebDriver driver;
	
	
	@FindBy(name="email")
	WebElement input_Email;
	
	@FindBy(name="password")
	WebElement input_Password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement login_Btn;
	
	@FindBy(linkText = "Forgot Your Password?")
	WebElement link_Forgot_Your_Password;
	
	@FindBy(name="remember")
	WebElement checkbox_Remember_Me;
	
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		
	}
	
	public void inputEmail(String email){
		input_Email.clear();
		input_Email.sendKeys(email);
	}
	
	public void inputPsd(String psd){
		input_Password.clear();
		input_Password.sendKeys(psd);
	}
	
	public void clickOnLoginBtn(){
		login_Btn.click();
	}
	
	public void clickOnRemeberMeCheckBox(){
		checkbox_Remember_Me.click();
	}
	
	public void clickOnForgotYourPassword(){
		link_Forgot_Your_Password.click();
	}
	
}
