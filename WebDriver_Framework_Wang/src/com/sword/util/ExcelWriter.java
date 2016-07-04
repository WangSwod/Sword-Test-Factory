package com.sword.util;

import java.io.File;

import jxl.CellType;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelWriter {

	File sourceXlsFile;

	public ExcelWriter(String filePath) {
		// TODO Auto-generated constructor stub
		this.sourceXlsFile = new File(filePath);
	}

	public void updateExcel(String sheetName, int columnId, int rowId, String result) {
		try {
			Workbook rwb = Workbook.getWorkbook(sourceXlsFile);// 原xls文件
			WritableWorkbook wwb = Workbook.createWorkbook(sourceXlsFile, rwb);// 临时xls文件
			WritableSheet sheet = wwb.getSheet(sheetName);// 工作表
			WritableCell c00 = sheet.getWritableCell(columnId, rowId);// 第一列第一行单元格
			if (c00.getType() == CellType.LABEL) {
				((Label) c00).setString(result);
			}
			wwb.write();

			// 一定上close workbook，不然更新不了
			wwb.close();
			rwb.close();
			System.out.println("更新完成.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addResultToExcel(String sheetName, int columnId, int rowId, String result) {
		try {
			Workbook rwb = Workbook.getWorkbook(sourceXlsFile);// 原xls文件
			WritableWorkbook wwb = Workbook.createWorkbook(sourceXlsFile, rwb);// 临时xls文件
			WritableSheet sheet = wwb.getSheet(sheetName);// 工作表
//			WritableCell c00 = sheet.getWritableCell(columnId, rowId);// 第一列第一行单元格
//			if (c00.getType() == CellType.LABEL) {
//				((Label) c00).setString(result);
//			}
			Label label = new Label(columnId,rowId,result);
			sheet.addCell(label);
			wwb.write();

			// 一定上close workbook，不然更新不了
			wwb.close();
			rwb.close();
			System.out.println("更新完成.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
