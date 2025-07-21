package a_DataDrivenTesting;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import UtilityFile.ReadExcelFile;

public class c_ReadMultipleData_ExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {


		ReadExcelFile exObj=new ReadExcelFile();



		//Read multiple data

		exObj.readAllData("Prod");

	}

}
