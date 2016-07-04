package com.sword.util;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeywordsUtil {
	WebDriver driver;
	
	public KeywordsUtil(WebDriver driver){
		this.driver  = driver;
	}
	
	
	public String  pasue(String findBy,String object,String data) {
		try {
			driver.wait(Long.parseLong(data));
		} catch (NumberFormatException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constant.FAIL;
		}
		return Constant.PASS;
	}
	
	public String input(String findBy, String object, String data) {
		try {
			By by = getBy(findBy, object);
			WebElement element = driver.findElement(by);
			System.out.println("input" +element.toString());
			element.clear();
			element.sendKeys(data);
		}
		catch (Exception e) {
			System.out.println("fail" + e.getMessage());
			return Constant.FAIL + ". Unalbe input! " + e.getMessage();
			
		}
		return com.sword.util.Constant.PASS ; 
		
	}
	public String  click(String findBy, String object,String data) {
		
		try {
			By by = getBy(findBy, object);
			WebElement element = driver.findElement(by);
			System.out.println("click" +element.toString());
			if(element.isEnabled()){
				element.click();
				System.out.println("click avaiable");
			}else{
				System.out.println("click unavaiable");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return Constant.FAIL + ". Unalbe click! " + e.getMessage();
		}
		
		return Constant.PASS;
		
	}
	
	
	public String  navigate(String findBy,String object, String data) {
		  try{
	            driver.navigate().to(data);
	            
	        }catch(Exception e){
	            return Constant.FAIL +" -- Not able to navigate";
	        }
	        return Constant.PASS;
		
	}
	
	
	private boolean waitForElementPresent(By by, int wait_Time) {
		if(isElementExist(by))
			return true;
		
		for(int second = 0 ; second < wait_Time; second++){
			
			try {
				System.out.println("wait time (second):"+second);
				driver.wait(1000);	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isElementExist(by)){
				return true;
			}
		}
		
		return false;
	}
	private boolean isElementExist(By by) {
		
		try {
			driver.findElement(by);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return false;
		}
	
		return true;
	}

	private By getBy (String findBy, String object){
		
			switch (findBy) {
			case "css":
					return By.cssSelector(object);		
				
			case "name":
					return By.name(object);
				
			case "xpath":
					return By.xpath(object);
				
			case "id":
					return By.id(object);
				
			case "class":
					return By.className(object);			
				
			case "linkText":
					return By.linkText(object);
				
			case "tag":
					return By.tagName(object);
				
			case "partialLinkText":
					return By.partialLinkText(object);
				
			
			}
			return null;
	
		
		
		
	}
		
}
