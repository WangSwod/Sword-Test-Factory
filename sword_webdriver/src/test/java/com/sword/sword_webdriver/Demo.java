package com.sword.sword_webdriver;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sword.data.PropertyMap;
import com.sword.processor.TestCaseProcessor;
import com.sword.util.LogUtil;

public class Demo {
	@Test
	public void f() {
		PropertyMap map = new PropertyMap();
		String pathToSuite = map.getProperty("TestSuite_Path");
		assertTrue(TestCaseProcessor.excute(pathToSuite));

	}
	
	@BeforeTest
	public void befereTest(){
		LogUtil.startTestCase(this.getClass().getName());
	}
	
	@AfterTest
	public void afterTest(){
		LogUtil.endTestCase();
	}
}
