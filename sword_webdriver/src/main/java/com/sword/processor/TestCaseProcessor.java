package com.sword.processor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.sword.keyword.Keywords;
import com.sword.propertyMap.PropertyMap;
import com.sword.util.ExcelUtil;

public class TestCaseProcessor {

	private static PropertyMap map;
	private static Keywords keyword_Actions;
	private static Method[] methods;

	private static String sheet_TestCase;
	private static String sheet_TestSuite;

	private static int testSuite_Result;
	private static int testSuite_Feature;
	private static int testSuite_RunMode;

	private static int testCase_Result;
	private static int testCase_TestCase;
	private static int testCase_RunMode;

	private static int column_TCID;
	private static int column_Keyword;
	private static int column_Expression;
	private static int column_Data;
	private static int column_Result;

	public static void excute(String pathToSuite) {

		init();

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Map<String, Integer> features = getFeaturesFromSuite(pathToSuite);

		String resultOfFeature;
		String pathToFeature;

		for (String feature : features.keySet()) {

			pathToFeature = "testcase/" + feature + ".xls";
			resultOfFeature = processFeatureExcel(pathToFeature);
			resultMap.put(features.get(feature), resultOfFeature);

		}

		setFeatureResultToSuite(pathToSuite, resultMap);

	}

	private static void init() {
		// Get all the information from the property file
		map = new PropertyMap();

		sheet_TestCase = map.getProperty("sheet_TestCase");
		sheet_TestSuite = map.getProperty("sheet_TestSuite");

		testSuite_Feature = Integer.valueOf(map.getProperty("testSuite_Feature"));
		testSuite_Result = Integer.valueOf(map.getProperty("testSuite_Result"));
		testSuite_RunMode = Integer.valueOf(map.getProperty("testSuite_RunMode"));

		testCase_Result = Integer.valueOf(map.getProperty("testCase_Result"));
		testCase_TestCase = Integer.valueOf(map.getProperty("testCase_TestCase"));
		testCase_RunMode = Integer.valueOf(map.getProperty("testCase_RunMode"));

		column_TCID = Integer.valueOf(map.getProperty("TCId"));
		column_Keyword = Integer.valueOf(map.getProperty("Keyword"));
		column_Expression = Integer.valueOf(map.getProperty("Expression"));
		column_Data = Integer.valueOf(map.getProperty("Data"));
		column_Result = Integer.valueOf(map.getProperty("Result"));

		// Create Keywords class and all the methods in the Keywords class for
		// reflection later
		keyword_Actions = new Keywords();
		methods = keyword_Actions.getClass().getMethods();

	}

	private static Map<String, Integer> getFeaturesFromSuite(String pathToSuite) {

		Map<String, Integer> results = new HashMap<String, Integer>();

		ExcelUtil.openExcel(pathToSuite);

		results = ExcelUtil.searchExcel(sheet_TestSuite, testSuite_RunMode, "Y", testSuite_Feature);

		ExcelUtil.closeExcel();

		return results;
	}

	private static void setFeatureResultToSuite(String pathToSuite, Map<Integer, String> resultMap) {

		ExcelUtil.openExcel(pathToSuite);

		ExcelUtil.setMultipleCellContent(sheet_TestSuite, testSuite_Result, resultMap);

		ExcelUtil.closeExcel();
	}

	private static String processFeatureExcel(String pathToFeature) {

		String resultForTestStep;
		String resultForTestCase = "NT";
		String resultForFeature = "NT";

		ExcelUtil.openExcel(pathToFeature);

		Map<String, Integer> testcases = ExcelUtil.searchExcel(sheet_TestCase, testCase_RunMode, "Y",
				testCase_TestCase);

		String keyword;
		String expression;
		String data;

		int rowCount;

		for (String testcase : testcases.keySet()) {
			rowCount = ExcelUtil.getRowCount(testcase);

			for (int i = 1; i < rowCount; i++) {
				keyword = ExcelUtil.getCellContent(testcase, i, column_Keyword);
				expression = ExcelUtil.getCellContent(testcase, i, column_Expression);
				data = ExcelUtil.getCellContent(testcase, i, column_Data);

				resultForTestStep = excute_Keyword(keyword, expression, data);

				if (resultForTestStep.toLowerCase() == "fail") {
					resultForTestCase = "Fail";
					resultForFeature = "Fail";
				}
				ExcelUtil.setCellContent(resultForTestStep, testcase, i, column_Result);
			}

			if (resultForTestCase.toLowerCase() != "fail") {
				resultForTestCase = "Pass";
			}
			ExcelUtil.setCellContent(resultForTestCase, sheet_TestCase, testcases.get(testcase), testCase_Result);

		}
		
		if(resultForFeature.toLowerCase() != "fail"){
			resultForFeature = "Pass";
		}

		return resultForFeature;
	}

	private static String excute_Keyword(String keyword, String expression, String data) {
		
		try {
			for(int i = 0 ; i < methods.length ; i++){
				
				if(methods[i].getName() == keyword){
					methods[i].invoke(keyword_Actions, expression,data);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
					
		}
		
		
		
		return "Pass";
	}

}
