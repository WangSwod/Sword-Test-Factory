package com.sword.sword_webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sword.util.Util;

public class TestDemo {
	WebDriver driver;
	String url = "http://www.baidu.com";
	
	@Test
	public void f() {
		
		driver.get(url);
		Util.pause(3000);
	}

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
		driver = new ChromeDriver();

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
