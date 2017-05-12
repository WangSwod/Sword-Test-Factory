package com.sword.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class TableHelper {
	WebElement table ; 
	
	public TableHelper(WebElement table) {
		this.table = table;
	}
	
	public void setTable(WebElement table) {
		this.table = table;
	}
	
	public WebElement getTable() {
		return table;
	}
	
	public int  getRowCount() {
		return table.findElements(By.tagName("tr")).size();
	}
	
	public int  getColumnCount() {
		WebElement firstRow = table.findElements(By.tagName("tr")).get(0);
		return firstRow.findElements(By.tagName("td")).size();
		
	}
	
	public WebElement getCell(int row, int column) {
		
		try {
			WebElement cell ;
			
			WebElement targetRow = table.findElements(By.tagName("tr")).get(row-1);
			cell = targetRow.findElements(By.tagName("td")).get(column-1);
			
			return cell;
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("can not find such cell on row"+row +" and column"+column);
		}
		
	}
	
}

