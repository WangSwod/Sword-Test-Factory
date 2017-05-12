package com.sword.sword_webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sword.util.ExcelUtil;
import com.sword.util.LogUtil;
import com.sword.util.Util;

public class TestDemo {
	WebDriver driver;
	String url = "http://www.baidu.com";
	
	@Test
	public void f() {
		LogUtil.info("hello world");
		driver.get(url);
		
		ExcelUtil.openExcel("testcase/suite1.xls");
		String content = ExcelUtil.getCellContent(1, 0, 1);
		LogUtil.info("content is "+content);
		
		ExcelUtil.setCellContent("hello", 1, 23, 1);
		
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
