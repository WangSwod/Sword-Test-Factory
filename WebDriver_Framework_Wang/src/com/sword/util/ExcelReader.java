package com.sword.util;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReader {
	
	private Workbook workbook;
	
	public ExcelReader(String filePath) {
		// TODO Auto-generated constructor stub
		File file = new File(filePath);
		try {
			workbook = Workbook.getWorkbook(file);
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isSheetExist(String sheetName){
		if(workbook.getSheet(sheetName) == null){
			return false;
		}else {
			return true;
		}
		
	}
	public void close() {
		workbook.close();
		
	}
	
	public Workbook	 getWorkBook() {
		return workbook;
	}
	public String getCellContent(String sheetName, String columnName , int rowId) {
		Sheet sheet = getSheet(sheetName);
		int columns = sheet.getColumns();
		int columnId  = -1 ;
		Cell cell;
		for(int column = 0 ; column < columns; column++){
			cell = sheet.getCell(column,0);
			if(cell.getContents().equals(columnName)){
				columnId = column;
				break;
			}
		}
		if(columnId == -1){
			return null;
		}
		return sheet.getCell(columnId, rowId).getContents();
	}
	public int getRows(int sheetNum) {
		return getSheet(sheetNum).getRows();
	}	
	public int  getRows(String sheetName) {
		return getSheet(sheetName).getRows();	
	}
	public String getCellContent(int sheetNum, int columnId, int rowId) {	
		return getSheet(sheetNum).getCell(columnId,rowId).getContents();		
	}
	public String getCellContent(String sheetName, int columnId, int rowId) {	
		return getSheet(sheetName).getCell(columnId,rowId).getContents();		
	}
	public Sheet getSheet(int sheetNum) {
		return workbook.getSheet(sheetNum);		
	}
	public Sheet getSheet(String sheetName) {
		return workbook.getSheet(sheetName);		
	}
}
