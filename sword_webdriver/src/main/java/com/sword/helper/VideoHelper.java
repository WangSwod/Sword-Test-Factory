package com.sword.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VideoHelper {
	
	WebElement video;
	WebDriver driver;
	JavascriptExecutor javascriptExecutor;
	
	
	public VideoHelper(WebDriver driver, WebElement video) {
		this.driver = driver;
		this.video = video;
		this.javascriptExecutor = (JavascriptExecutor)driver;
	}
	
	public VideoHelper(WebDriver driver) {
		this.driver = driver;
		this.video = driver.findElement(By.tagName("video"));
		this.javascriptExecutor = (JavascriptExecutor)driver;
	}
	
	public void  play() {
		
		String script = "return argument[0].play();";
		javascriptExecutor.executeScript(script, video);
	}
	
	public void  pause() {
		String script = "return argument[0].pause();";
		javascriptExecutor.executeScript(script, video);
	}
	
	public String  getVideoSource() {
		String script = "return argument[0].currentSrc;";
		return (String) javascriptExecutor.executeScript(script, video);
	}
	
	public int getDuration() {
		String script = "return argument[0].duration;";
		Double duration =  ((Double)javascriptExecutor.executeScript(script, video));
		return duration.intValue();
	}
}
