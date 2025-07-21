package a_DataDrivenTesting;

import java.io.IOException;

import UtilityFile.ReadExcelFile;

public class e_WriteData_ExcelFile {
	public static void main(String[] args) throws  IOException {
		
		//Update data in excel and read
		
	    ReadExcelFile exObj=new ReadExcelFile();
	    
	    exObj.writeData("Cont", 0, 0, "Cont_ID");
	    exObj.writeData("Cont", 0, 1, "FirstName");
	    exObj.writeData("Cont", 0, 2, "LastName");
	    
	    exObj.writeData("Cont", 1, 0, "-------");
	    exObj.writeData("Cont", 1, 1, "---------");
	    exObj.writeData("Cont", 1, 2, "--------");
	    
	    exObj.writeData("Cont", 2, 0, "A_001");
	    exObj.writeData("Cont", 2, 1, "James");
	    exObj.writeData("Cont", 2, 2, "Bond");
	    
	    exObj.writeData("Cont", 3, 0, "A_002");
	    exObj.writeData("Cont", 3, 1, "Oggy");
	    exObj.writeData("Cont", 3, 2, "William");
	    
	    exObj.writeData("Cont", 4, 0, "A_003");
	    exObj.writeData("Cont", 4, 1, "Jack");
	    exObj.writeData("Cont", 4, 2, "Richer");
	    
	    System.out.println("Data write completed");
	    
	    exObj.readAllData("Cont");

		
	}

}
