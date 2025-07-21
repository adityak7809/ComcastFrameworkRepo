package UtilityFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ReadExcelFile {
	public String filePath="./src/test/resources/TestData.xlsx";

	
	//Read single data-----------------------------------------------------
	public  String readSingleData(String sheetName, int rowNum, int colNum) throws IOException
	{
		FileInputStream fis=new FileInputStream(filePath);

		Workbook book=WorkbookFactory.create(fis);

		//Optimized Code
		try {
			String data=book.getSheet(sheetName).getRow(rowNum).getCell(colNum).toString();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Cell is empty";
	}

	//Read multiple data-------------------------------------------------
	public void readAllData(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(filePath);

		Workbook book=WorkbookFactory.create(fis);

		Sheet sheet=book.getSheet(sheetName);

		int rowCount=sheet.getLastRowNum();
		int colCount=sheet.getRow(0).getLastCellNum();


		for(int i=0; i<=rowCount; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				try {
					String data=sheet.getRow(i).getCell(j).toString();
					System.out.print(data+" ");
				}
				catch (Exception e) {
					System.out.println("Cell is empty");
					e.printStackTrace();
				}
			}
			System.out.println();
		}
	}

	//Read Multiple data based on condition---------------------------------
	public void readRowData(String sheetName, String condition) throws  IOException
	{
		FileInputStream fis=new FileInputStream(filePath);

		Workbook book=WorkbookFactory.create(fis);

		Sheet sheet=book.getSheet(sheetName);

		int rowCount=sheet.getLastRowNum();
		int colCount=sheet.getRow(0).getLastCellNum();

		for(int i=1; i<=rowCount; i++)
		{ 
			String colData=sheet.getRow(i).getCell(0).toString();
			if(colData.equals(condition))
			{
				for(int j=0; j<colCount; j++)
				{
					try {
						String rowData=sheet.getRow(i).getCell(j).toString();
						System.out.print(rowData+" ");
					}
					catch(Exception e)
					{
						System.out.println("Cell is empty");
					}
				}
			}
		}
	}



	//Write data back to excel--------------------------------------------------
	public void writeData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook book = WorkbookFactory.create(fis);

		Sheet sheet = book.getSheet(sheetName);
		if (sheet == null) {
			sheet = book.createSheet(sheetName);
		}

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}

		Cell cell = row.createCell(colNum);

		cell.setCellValue(data);

		FileOutputStream fos = new FileOutputStream(filePath);
		book.write(fos);
		book.close();
		fis.close();
		fos.close();
	}

}





















