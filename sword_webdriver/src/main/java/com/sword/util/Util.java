package com.sword.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	
	public static void pause(int millisecond){
		
		try{
			
			Thread.sleep(millisecond);
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	public static void  waitForElement(WebDriver driver, By by) {
		
		new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(by)); 
	}
}
