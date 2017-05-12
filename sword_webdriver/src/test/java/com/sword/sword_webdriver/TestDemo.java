package com.sword.sword_webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sword.propertyMap.PropertyMap;
import com.sword.util.ExcelUtil;
import com.sword.util.LogUtil;
import com.sword.util.Util;

public class TestDemo {
	WebDriver driver;
	String url = "http://www.baidu.com";
	
	@Test
	public void f() {
		
		driver.get(url);
		
		PropertyMap map = new PropertyMap();
		
		try {
			driver.findElement(map.getLocator("baiduSearch")).sendKeys("hello");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
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
