package com.sword.sword_webdriver;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sword.processor.TestCaseProcessor;
import com.sword.propertyMap.PropertyMap;
import com.sword.util.ExcelUtil;
import com.sword.util.LogUtil;
import com.sword.util.Util;

public class TestDemo {
//	WebDriver driver;
//	String url = "http://www.baidu.com";
	
	@Test
	public void f() {
		
//		driver.get(url);
		
		PropertyMap map = new PropertyMap();
		String pathToSuite = map.getProperty("TestSuite_Path");
		assertTrue(TestCaseProcessor.excute(pathToSuite));
		
		
	}

	@BeforeTest
	public void beforeTest() {

//		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
////		System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
//		driver = new ChromeDriver();
////		driver = new FirefoxDriver();
		LogUtil.startTestCase("Test Demo");

	}

	@AfterTest
	public void afterTest() {
		LogUtil.endTestCase();
//		driver.quit();
		
	}

}
