package com.sword.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static Workbook workbook;
	private static String path;

	public static void openExcel(String pathToExcel) {
		InputStream inputStream;
		path = pathToExcel;

		try {
			inputStream = new FileInputStream(path);
			workbook = WorkbookFactory.create(inputStream);

			LogUtil.info("Open the excel file successfully at " + path);

		} catch (FileNotFoundException e) {
			LogUtil.info("Fail to open the excel file at " + path);
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			LogUtil.info("Fail to open the excel file at " + path);
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			LogUtil.info("Fail to open the excel file at " + path);
			e.printStackTrace();
		} catch (IOException e) {
			LogUtil.info("Fail to open the excel file at " + path);
			e.printStackTrace();
		}

	}
	
	private static int  getRowCount(Sheet sheet) {
		
		int result = sheet.getLastRowNum() +1;
		
		LogUtil.info("Get " + result  + " rows from sheet:" +sheet.getSheetName());
		return result;
	}
	
	public static int  getRowCount(String sheetName) {
		if (workbook == null) {
			LogUtil.warn("Please make sure the excel file is alreday open");
			throw new NullPointerException("Workbook is null");
		}
		
		Sheet sheet = workbook.getSheet(sheetName);
		return getRowCount(sheet);
	}
	
	public static int  getRowCount(int sheetId) {
		if (workbook == null) {
			LogUtil.warn("Please make sure the excel file is alreday open");
			throw new NullPointerException("Workbook is null");
		}
		
		Sheet sheet = workbook.getSheetAt(sheetId);
		
		return getRowCount(sheet);
	}
	
	public static Map<String, Integer> searchExcel(String sheetName , 
			int conditionColumn , String keyword, int tragetColumn ){
		
		Map<String, Integer> resultMap = new HashMap<String,Integer>();
		
		int rowCount = getRowCount(sheetName);

		String conditionValue;
		String resultValue;
		for (int i = 1; i < rowCount; i++) {
			conditionValue = getCellContent(sheetName, i, conditionColumn);
			
			LogUtil.info(conditionValue);
			LogUtil.info(keyword);

			if (conditionValue.equalsIgnoreCase(keyword)) {
				resultValue = getCellContent(sheetName, i, tragetColumn);
				resultMap.put(resultValue, Integer.valueOf(i));
				LogUtil.info("put value in the map");
				
			}
		}
		
		LogUtil.info("Put all the features in the result Map:"+resultMap);
		return resultMap;
	}
	
	
	public static void setMultipleCellContent(String sheetName, int targetColumn, Map<Integer, String> contents ) {
		
		for (Integer rowId : contents.keySet()) {
			String content = contents.get(rowId);
			ExcelUtil.setCellContent(content, sheetName, rowId.intValue(), targetColumn);

		}
	}

	private static String getCellContent(Sheet sheet, int rowId, int columnId) {

		LogUtil.info("Select row at row" + rowId);
		Row row = sheet.getRow(rowId);

		LogUtil.info("Select cell at row" + rowId + " column" + columnId);
		Cell cell = row.getCell(columnId);

		String result = null;
		LogUtil.info("cell type:" + cell.getCellTypeEnum());
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;

		case STRING:
			result = cell.getStringCellValue();
			break;

		case NUMERIC:
			result = String.valueOf(cell.getNumericCellValue());
			break;

		case FORMULA:
			result = cell.getCellFormula();
			break;

		case BLANK:
			result = "";
			break;
		default:

		}

		LogUtil.info("Get the cell value: " + result);
		return result;
	}

	public static String getCellContent(int sheetId, int rowId, int columnId) {

		if (workbook == null) {
			LogUtil.warn("Please make sure the excel file is alreday open");
			throw new NullPointerException("Workbook is null");
		}

		LogUtil.info("Open sheet at sheet" + sheetId);
		Sheet sheet = workbook.getSheetAt(sheetId);

		return getCellContent(sheet, rowId, columnId);

	}

	public static String getCellContent(String sheetName, int rowId, int columnId) {
		if (workbook == null) {
			LogUtil.warn("Please make sure the excel file is alreday open");
			throw new NullPointerException("Workbook is null");
		}

		LogUtil.info("Open sheet of name:" + sheetName);
		Sheet sheet = workbook.getSheet(sheetName);

		return getCellContent(sheet, rowId, columnId);

	}

	private static void setCellContent(String content, Sheet sheet, int rowId, int columnId) {
		LogUtil.info("Select row at row" + rowId);
		Row row = sheet.getRow(rowId);
		if(row == null){
			row = sheet.createRow(rowId);
		}
		
		LogUtil.info("Select cell at row" + rowId + " column" + columnId);
		Cell cell = row.getCell(columnId);

		if (cell == null) {
			cell = row.createCell(columnId);
		}

		cell.setCellType(CellType.STRING);
		cell.setCellValue(content);

		// write the output to a file
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			workbook.write(outputStream);
			outputStream.close();

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		LogUtil.info("Write to cell the content: " + content);

	}

	public static void setCellContent(String content, int sheetId, int rowId, int columnId) {
		if (workbook == null) {
			LogUtil.warn("Please make sure the excel file is alreday open");
			throw new NullPointerException("Workbook is null");
		}

		LogUtil.info("Open sheet at sheet" + sheetId);
		Sheet sheet = workbook.getSheetAt(sheetId);

		setCellContent(content, sheet, rowId, columnId);
	}

	public static void setCellContent(String content, String sheetName, int rowId, int columnId) {
		if (workbook == null) {
			LogUtil.warn("Please make sure the excel file is alreday open");
			throw new NullPointerException("Workbook is null");
		}

		LogUtil.info("Open sheet of name:" + sheetName);
		Sheet sheet = workbook.getSheet(sheetName);
		
		setCellContent(content, sheet, rowId, columnId);
	}

	public static void closeExcel() {
		if (workbook == null) {
			LogUtil.warn("Please make sure the excel file is alreday open");
			throw new NullPointerException("Workbook is null");
		}

		try {
			workbook.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
