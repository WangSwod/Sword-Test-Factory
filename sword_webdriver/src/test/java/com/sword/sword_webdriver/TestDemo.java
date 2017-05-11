package com.sword.sword_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.jna.platform.KeyboardUtils;
import com.sword.util.KeyboardUtil;
import com.sword.util.LogUtil;
import com.sword.util.Util;

public class TestDemo {
	WebDriver driver;
	String url = "http://www.baidu.com";
	
	@Test
	public void f() {
		LogUtil.info("hello world");
		driver.get(url);
		KeyboardUtil.pressCtrlandTab(); 
		Util.pause(3000);
//		driver.findElement(By.id("kw")).sendKeys("world");
		KeyboardUtil.pressCtrlV_Paste_Mac("hello");
		KeyboardUtil.pressTabKey();
		
		KeyboardUtil.pressTabKey();
		KeyboardUtil.pressTabKey();
		KeyboardUtil.pressEnterKey();
		
		Util.pause(5000);
	}

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
//		System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
		driver = new ChromeDriver();
//		driver = new FirefoxDriver();
		LogUtil.startTestCase("Test Demo");

	}

	@AfterTest
	public void afterTest() {
		LogUtil.endTestCase();
		driver.quit();
		
	}

}
