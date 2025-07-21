package a_DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class h_Assignment {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		for (int i = 0; i <= 10; i++) {
			String value = random_Num().toString();
			writeData(i, 0, value);
			
		}

		System.out.println("Test execution completed....");

	}

	// write data to excel
	public static void writeData(int rowNum, int colNum, String data) throws EncryptedDocumentException, IOException {
		String file_path="./src/test/resources/TestData.xlsx";
		
		FileInputStream fis=new FileInputStream(file_path);
		Workbook book=WorkbookFactory.create(fis);
		
		Sheet sheet=book.getSheet("RandomData");
		if(sheet==null)
		{
			sheet=book.createSheet("RandomData");
		}
	
		Row row=sheet.getRow(rowNum);
		if(row==null)
		{
			row=sheet.createRow(rowNum);
		}
		
		Cell cell=row.getCell(colNum);
		if(cell==null)
		{
			cell=row.createCell(colNum);
		}
		
		cell.setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream(file_path);
		book.write(fos);
		book.close();
		fis.close();
		fos.close();
	}

	// Generate random number
	public static StringBuilder random_Num() {
		int limit = 15;
		String AlphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(limit);

		for (int i = 0; i <= limit; i++) {
			int index = random.nextInt(AlphaNumeric.length());
			sb.append(AlphaNumeric.charAt(index));
		}

		return sb;
	}

}
