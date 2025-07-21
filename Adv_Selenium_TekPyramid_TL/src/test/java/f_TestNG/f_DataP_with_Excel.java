package f_TestNG;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.concast.crm.generic.fileUtility.ExcelUtiltiy;

public class f_DataP_with_Excel {
	
	@DataProvider
	public Object[][] testData() throws EncryptedDocumentException, IOException
	{
		ExcelUtiltiy exObj=new ExcelUtiltiy();
		int row=exObj.getRowCount("Org");
		int col=exObj.getColCount("Org");
		
		
		Object[][] obj = new Object[row][col];
		
		for(int i=0; i<row; i++)
		{
			for(int j=0; j<col; j++)
			{
				obj[i][j]= exObj.getDataFromExcelFile("Org", i+1, j);
			}
		}
		
		
		return obj;
	}
	
	@Test(dataProvider = "testData")
	public void createContact_Id_and_Name(String org_id, String orgName)
	{
		Reporter.log("Contact id: "+ org_id, true);
		Reporter.log("Contact name: "+ orgName, true);
		
		System.out.println();
	}

}
