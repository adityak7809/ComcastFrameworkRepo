package a_DataDrivenTesting;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import UtilityFile.ReadExcelFile;

public class d_ReadMultipleData_OnCndn_ExcelFile {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		
		ReadExcelFile exObj=new ReadExcelFile();
		
		//Read multiple data based on condition
		exObj.readRowData("Org", "tc_08");
//		System.out.println("Test complete");
	  
	}

}
