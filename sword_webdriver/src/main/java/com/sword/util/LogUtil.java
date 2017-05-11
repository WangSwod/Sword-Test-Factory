package com.sword.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogUtil {
	
	private static Logger logger = LogManager.getLogger(LogUtil.class.getName()) ;

//	public static void init(String clazzName) {
//
//		// set the path to the log4j2.xml file
//		File file = new File("log4j2.xml");
//		try {
//			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//			ConfigurationSource source = new ConfigurationSource(inputStream);
//			Configurator.initialize(null,source);
//			
//		} catch (FileNotFoundException e) {
//			
//			e.printStackTrace();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//			
//		// Define a static logger variable
//		logger = LogManager.getLogger(clazzName);
//
//	}
	
	public static void startTestCase(String testCaseName) {
		logger.info("---------------------------------------------------");
		logger.info("********       " + testCaseName + "     ***********");
	}
	
	public static void endTestCase() {
		logger.info("********       The End Of TestCase     ***********");
		logger.info("---------------------------------------------------");
		
	}
	
	public static void info(String str) {
		logger.info(str);
	}
	
	public static void  trace(String str) {
		logger.trace(str);
	}
	
	public static void  debug(String str) {
		logger.debug(str);
	}
	
	public static void  warn(String str) {
		logger.warn(str);
	}
	public static void  error(String str) {
		logger.error(str);
	}
	public static void  fatal(String str) {
		logger.fatal(str);
	}
}
