package com.sword.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CanvasHelper {
	
	private JavascriptExecutor javascriptExecutor;
	
	public CanvasHelper(WebDriver driver) {		
		this.javascriptExecutor = (JavascriptExecutor)driver;
	}
	
	
	public void drawFillRect(String id, int x , int y, int width, int height) {
		String script = "var c = document.getElementById(argument[0]);"
				+"var cxt = c.getContext('2d')"
				+"cxt.fillStyle = 'red'"
				+"cxt.fillRect(argument[1],argument[2],argument[3],argument[4])";
				
		javascriptExecutor.executeScript(script,id,x,y,width,height);
	}
	
	//TODO implement this class using more javascript
	
	
	
}
