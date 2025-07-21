package a_DataDrivenTesting;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import UtilityFile.ReadExcelFile;

public class b_ReadSingleData_ExcelFile {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		
		ReadExcelFile exObj=new ReadExcelFile();
		
		//Read single data
	    String orgName=exObj.readSingleData("Org", 1, 2);
	    System.out.println(orgName);
	    
	  
	}

}
