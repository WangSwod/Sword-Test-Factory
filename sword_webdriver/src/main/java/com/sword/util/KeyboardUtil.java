package com.sword.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyboardUtil {

	public static void pressTabKey() {
		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}

		robot.keyPress(KeyEvent.VK_TAB);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_TAB);
		LogUtil.info("Press the tab key");
		
		
		
		
	}

	public static void pressEnterKey() {
		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		LogUtil.info("Press the enter key");
	}

	public static void pressCtrlV_Paste_Mac(String string) {

		StringSelection contents = new StringSelection(string);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(contents, null);

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}

		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_META);
		LogUtil.info("Paste the text from clipboard");

	}

	public static void pressCtrlV_Paste_Windows(String string) {
		StringSelection contents = new StringSelection(string);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(contents, null);

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		LogUtil.info("Paste the text from clipboard");
	}
	
	//This method is used to switch to target application
	public static void pressCtrlandTab() {
		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_META);
		LogUtil.info("Press the command + tab");
	}
}
