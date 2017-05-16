package com.sword.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

	public static void pause(int millisecond) {

		try {
			LogUtil.info("Command || Pause " + millisecond / 1000 + " second");
			Thread.sleep(millisecond);
		} catch (Exception e) {
			LogUtil.info("Command || Fail to pause");
			e.printStackTrace();
		}
	}

	public static void waitForElement(WebDriver driver, By by) {

		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static boolean imageComparison(String path_ExpectedImage, String path_ActualImage) throws IOException {

		File expected_File = new File(path_ExpectedImage);
		File actual_File = new File(path_ActualImage);

		BufferedImage expected = ImageIO.read(expected_File);
		BufferedImage actual = ImageIO.read(actual_File);

		DataBuffer data_Expected = expected.getData().getDataBuffer();
		DataBuffer data_Actual = actual.getData().getDataBuffer();

		int size_ExpectedImage = data_Expected.getSize();
		int size_ActualImage = data_Actual.getSize();

		if (size_ActualImage == size_ExpectedImage) {
			for (int i = 0; i < size_ExpectedImage; i++) {

				if (data_Actual.getElem(i) != data_Expected.getElem(i)) {
					return false;
				}
			}

			LogUtil.info("Command || compare successfully, those images are the same");
			return true;

		}

		LogUtil.info("Command || compare successfully, those images are different");
		return false;

	}
	
	public static String formatDate(String date) {
		if(date.length()<2){
			return "0" +date;
					
		}
		return date;
		
	}

	public static String getDate(Date date, String seperator) {
		String year = String.valueOf(DateUtil.getYear(date));
		String month = formatDate(String.valueOf(DateUtil.getMonth(date)));
		String day = formatDate(String.valueOf(DateUtil.getDay(date)));

		return year + seperator + month + seperator + day;

	}

	public static String getTime(Date date, String seperator) {
		String hour = formatDate(String.valueOf(DateUtil.getHour(date)));
		
		String minute = formatDate(String.valueOf(DateUtil.getMinute(date)));
		String second = formatDate(String.valueOf(DateUtil.getSecond(date)));

		return hour + seperator + minute + seperator + second;
	}

	public static boolean createFile(String path) {
		File file = new File(path);

		// check if the file already exists
		if (file.exists()) {
			LogUtil.info("fail to create, file already exists");
			return false;
		}

		// check if the directory of file already exists
		if (!file.getParentFile().exists()) {
			LogUtil.info("directory of the file does not exist,try to create it");
			if (!file.getParentFile().mkdirs()) {
				LogUtil.info("fail to create directory of the file ");
				return false;
			}
		}

		// try to create the file
		try {

			if (file.createNewFile()) {
				LogUtil.info("create file successfully");
				return true;
			} else {
				LogUtil.info("fail to create file");
				return false;
			}

		} catch (IOException e) {

			LogUtil.info("fail to create file");
			e.printStackTrace();
			return false;

		}
	}

	public static boolean createDir(String path) {
		File dir = new File(path);

		if (dir.exists()) {
			LogUtil.info("fail to create, directory already exist");
			return false;

		}

		if (dir.mkdirs()) {
			LogUtil.info("create directory successfully");
			return true;
		} else {
			LogUtil.info("fail to create directory");
			return false;
		}
	}

	public static boolean isDirExist(String dir) {
		return new File(dir).exists();
	}
}
