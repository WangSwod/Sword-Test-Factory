package com.sword.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sword.util.Constant;
import com.sword.util.ExcelReader;
import com.sword.util.ExcelWriter;
import com.sword.util.KeywordsUtil;

public class MainTest {
	WebDriver driver;
	KeywordsUtil keywordsUtil;
	java.lang.reflect.Method methods[];

	Properties properties;
	static Logger logger;

	ExcelReader testSuite_Reader;
	ExcelReader testCase_Reader;

	ExcelWriter testCase_Writer;
	@BeforeTest
	public void setup() {

		properties = new Properties();

		try {
			properties.load(new FileInputStream(new File("./src/com/sword/config/config.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger = Logger.getLogger(MainTest.class);
		PropertyConfigurator.configure(properties.getProperty("log4j_Path"));

		// 设置chromedriver路径
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
		driver = new ChromeDriver();

		keywordsUtil = new KeywordsUtil(driver);
		methods = keywordsUtil.getClass().getMethods();
	}

	@Test
	public void keywordDriverTestLogin() {

		testSuite_Reader = new ExcelReader(properties.getProperty("TestSuite_Path"));

		// excelName_SheetName_ColumnName_PropertyOfColumn
		int testSuite_TestSuite_SheetNum = 0;
		int testSuite_TestSuite_Rows = testSuite_Reader.getRows(testSuite_TestSuite_SheetNum);
		int testSuite_TestSuite_Testcase_Column = Integer.parseInt(properties.getProperty("testSuite_TestCase"));
		int testSuite_TestSuite_Runmode_Column = Integer.parseInt(properties.getProperty("testSuite_RunMode"));

		String testSuite_TestSuite_Testcase_Content, testSuite_TestSuite_Runmode_Content;
		String testCase_Path;

		for (int testSuite_TestSuite_currentRow = 1; testSuite_TestSuite_currentRow < testSuite_TestSuite_Rows; testSuite_TestSuite_currentRow++) {

			testSuite_TestSuite_Testcase_Content = testSuite_Reader.getCellContent(testSuite_TestSuite_SheetNum,
					testSuite_TestSuite_Testcase_Column, testSuite_TestSuite_currentRow);

			testSuite_TestSuite_Runmode_Content = testSuite_Reader.getCellContent(testSuite_TestSuite_SheetNum,
					testSuite_TestSuite_Runmode_Column, testSuite_TestSuite_currentRow);

			if (testSuite_TestSuite_Runmode_Content.equals(Constant.RUN_YES)) {
				testCase_Path = "./testcase/" + testSuite_TestSuite_Testcase_Content + ".xls";
				logger.info("testCase_Path:" + testCase_Path);

				testCase_Reader = new ExcelReader(testCase_Path);
				testCase_Writer = new ExcelWriter(testCase_Path);
				
				String testCase_TestCase_SheetName =  properties.getProperty("testCase_sheet_Test_Case");
				String testCase_TestCase_TCID_Content, testCase_TestCase_Runmode_Content;

				int testCase_TestCase_TCID_Column = Integer.parseInt(properties.getProperty("testCase_TCId_Column"));
				int testCase_TestCase_Runmode_Column = Integer
						.parseInt(properties.getProperty("testCase_RunMode_Column"));

				int testCase_TestCase_Rows = testCase_Reader.getRows(testCase_TestCase_SheetName);

				for (int testCase_TestCase_CurrentRow = 1; testCase_TestCase_CurrentRow < testCase_TestCase_Rows; testCase_TestCase_CurrentRow++) {
					testCase_TestCase_Runmode_Content = testCase_Reader.getCellContent(testCase_TestCase_SheetName,
							testCase_TestCase_Runmode_Column, testCase_TestCase_CurrentRow);
					if (testCase_TestCase_Runmode_Content.equals(Constant.RUN_YES)) {

						testCase_TestCase_TCID_Content = testCase_Reader.getCellContent(testCase_TestCase_SheetName,
								testCase_TestCase_TCID_Column, testCase_TestCase_CurrentRow);

						if (testCase_Reader.isSheetExist(testCase_TestCase_TCID_Content)) {
							int testCase_TCID_Rows = testCase_Reader.getRows(testCase_TestCase_TCID_Content);
							
							int testCase_TCID_Runmode_Column = Integer
									.parseInt(properties.getProperty("testCase_RunMode_Column"));
							for (int testCase_TCID_currentRow = 1; testCase_TCID_currentRow < testCase_TCID_Rows; testCase_TCID_currentRow++) {
								if(testCase_Reader.getCellContent(testCase_TestCase_TCID_Content, 
										testCase_TCID_Runmode_Column, testCase_TCID_currentRow).equals(Constant.RUN_YES)){
									
									excuteTestStep(testCase_TestCase_TCID_Content,testCase_TCID_currentRow);
								}
								
							}

						}
					}
				}
			
				testCase_Reader.close();
				

			}

		}

		testSuite_Reader.close();

	}
//	 @AfterTest
//	 public void tearDown(){
//	 driver.quit();
//	
//	 }

	private void excuteTestStep(String testCase_Name,int currentRow) {
		// TODO Auto-generated method stub

		String findBy, object, keyword, data_Content, run_Mode, testCaseId,data;
		String result;
		String testCase_TestStep_SheetName = properties.getProperty("testCase_sheet_Test_Step");
		
		int testCase_TestStep_Result_Column = Integer.parseInt(properties.getProperty("testCase_Result_Column"));
		
		int rows = testCase_Reader.getRows(testCase_TestStep_SheetName);
		
		for (int i = 1; i < rows; i++) {
			// run_Mode
			run_Mode = testCase_Reader.getCellContent(testCase_TestStep_SheetName,
					Integer.parseInt(properties.getProperty("testCase_RunMode_Column")), i);
			// testCaseId
			testCaseId = testCase_Reader.getCellContent(testCase_TestStep_SheetName,
					Integer.parseInt(properties.getProperty("testCase_TCId_Column")), i);

			if (run_Mode.equals(Constant.RUN_YES) && testCaseId.equals(testCase_Name)) {
				// findBy
				findBy = testCase_Reader.getCellContent(testCase_TestStep_SheetName,
						Integer.parseInt(properties.getProperty("testCase_FindBy_Column")), i);
				// object
				object = testCase_Reader.getCellContent(testCase_TestStep_SheetName,
						Integer.parseInt(properties.getProperty("testCase_Object_Column")), i);
				// keyword
				keyword = testCase_Reader.getCellContent(testCase_TestStep_SheetName,
						Integer.parseInt(properties.getProperty("testCase_Keyword_Column")), i);
				// data
				data_Content = testCase_Reader.getCellContent(testCase_TestStep_SheetName,
						Integer.parseInt(properties.getProperty("testCase_Data_Column")),i);
				
				data = processData(data_Content, testCase_Name,currentRow);
				logger.info("data:"+data);
				logger.info("keywords:" + keyword);
				for (int j = 0; j < methods.length; j++) {

					if (methods[j].getName().equals(keyword)) {

						try {
							System.out.println(methods[j].getName());
							result = (String)methods[j].invoke(keywordsUtil, findBy, object, data);
							testCase_Writer.addResultToExcel(testCase_TestStep_SheetName,
									testCase_TestStep_Result_Column,i,result);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
				}

			}
		}

	}

	private String processData(String data,String testCase_Name, int currentRow) {
		// TODO Auto-generated method stub
		String  column_Name;
		if(data.startsWith("col")){
			 column_Name = data.split("\\|")[1];
			 logger.info("data name:"+data);
			 logger.info("column name:"+column_Name);
			return testCase_Reader.getCellContent(testCase_Name, column_Name, currentRow);
		}else if(data.startsWith("config")){
			 column_Name = data.split("\\|")[1];
			 logger.info("data name:"+data);
			 logger.info("column name:"+column_Name);
			 return properties.getProperty(column_Name);
			
		}
		return data;
	}

}
