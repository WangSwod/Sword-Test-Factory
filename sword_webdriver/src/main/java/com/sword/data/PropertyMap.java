package com.sword.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class PropertyMap {

	private static String default_Path = "propertyMap.property";

	Properties properties;

	public PropertyMap() {

		this(default_Path);
	}

	public PropertyMap(String path) {

		// create Properties object
		properties = new Properties();

		// loading .property file
		try {
			File file = new File(path);
			// file.getAbsolutePath();
			FileInputStream inputStream = new FileInputStream(file);
			properties.load(inputStream);
			inputStream.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}

	// return By object from .property file

	public By getLocator(String key) throws Exception {

		String value = properties.getProperty(key);

		String locatorType = value.split(":")[0];
		String locatorValue = value.split(":")[1];

		switch (locatorType.toLowerCase()) {
		case "id":
			return By.id(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		case "css":
			return By.cssSelector(locatorValue);
		case "tagName":
			return By.tagName(locatorValue);
		case "linkText":
			return By.linkText(locatorValue);
		case "partialLinkText":
			return By.partialLinkText(locatorValue);
		case "className":
			return By.className(locatorValue);

		default:
			throw (new Exception("Wrong locatorType in the property file"));

		}

	}
}
