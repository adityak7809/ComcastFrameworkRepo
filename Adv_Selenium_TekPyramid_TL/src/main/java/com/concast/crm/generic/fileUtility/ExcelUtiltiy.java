package com.concast.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtiltiy {
	
	//Row Count
	public int  getRowCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		String filePath="./testData/TestCaseData.xlsx";
		FileInputStream fis=new FileInputStream(filePath);
		
		Workbook book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		
	    int rowCount=sheet.getLastRowNum();
	    
		book.close();
	    return rowCount;
	}
	
	//Col count
	public int  getColCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		String filePath="./testData/TestCaseData.xlsx";
		FileInputStream fis=new FileInputStream(filePath);
		
		Workbook book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		
	    int colCount=sheet.getRow(0).getLastCellNum();
	    
	    book.close();
	    return colCount;
	}
	
	//Read Single data----------------------------
	public String getDataFromExcelFile(String sheetName, int rowNum, int colNum) throws EncryptedDocumentException, IOException
	{
		String filePath="./testData/TestCaseData.xlsx";
		FileInputStream fis=new FileInputStream(filePath);
		
		Workbook book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		
		try {
		String data=sheet.getRow(rowNum).getCell(colNum).toString();
		return data;
		} catch(Exception e)
		{
			return "Cell is empty";
		}
	}
	
	//Read multiple data--------------------------------
	public void getAllDataFromExcelFile(String sheetName) throws IOException  
	{
		String filePath="./testData/TestCaseData.xlsx";
		FileInputStream fis=new FileInputStream(filePath);
		
		Workbook book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		
		int rowCount=sheet.getLastRowNum();
		int colCount=sheet.getRow(0).getLastCellNum();
		
		for(int i=0; i<=rowCount; i++)
		{
			for(int j=0; j<=colCount; j++)
			{
				try {
					String data=sheet.getRow(i).getCell(j).toString();
					System.out.print(data+" ");
				} catch(Exception e)
				{
				//	System.out.println("Cell is empty");
				}
			}
			System.out.println();
		}
	}
	
	//Read data based on the Condition--------------------------
	public void getRowDataFromExcelFile(String sheetName, String condition) throws  IOException
	{
		String filePath="./testData/TestCaseData.xlsx"; 
		FileInputStream fis=new FileInputStream(filePath);
		
		Workbook book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		
		int rowCount=sheet.getLastRowNum();
		int colCount=sheet.getRow(0).getLastCellNum();
		

		for(int i=0; i<=rowCount; i++)
		{
			String data=sheet.getRow(i).getCell(0).toString();
			if(data.equals(condition))
			{
				for(int j=0; j<=colCount; j++)
				{
					try {
					String rowData=sheet.getRow(i).getCell(j).toString();
					System.out.print(rowData+" ");
					} 
					catch(Exception e){
//						System.out.println("Cell is empty");
					}
				}
			}
		}
	}
	
	
	
	//Write Data back to Excel---------------------
    public void setDataToExcelFile(String sheetName, int rowNum, int colNum, String data) throws EncryptedDocumentException, IOException
    {
    	String filePath="./testData/TestCaseData.xlsx";
		FileInputStream fis=new FileInputStream(filePath);
		
		Workbook book=WorkbookFactory.create(fis);
		
		Sheet sheet=book.getSheet(sheetName);
		if(sheet==null)
		{
			sheet=book.createSheet(sheetName);
		}
		
		Row row=sheet.getRow(rowNum);
		if(row==null)
		{
			row=sheet.createRow(rowNum);
		}
		
		Cell cell=row.createCell(colNum);
		cell.setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream(filePath);
		book.write(fos);
		book.close();
		fis.close();
		fos.close();
		
		

    }

}
