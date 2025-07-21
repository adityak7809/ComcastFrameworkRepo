package com.concast.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws IOException
	{
		String filePath="./configAppData/CommonData.properties";
		FileInputStream fis=new FileInputStream(filePath);
		Properties propObj=new Properties();
		propObj.load(fis);

		String data=propObj.getProperty(key);

		fis.close();
		return data;
	}

	public void setDataToPropertiesFile(String key, String value) throws IOException
	{
		String filePath="./configAppData/CommonData.properties";
		FileInputStream fis=new FileInputStream(filePath);
		Properties propObj=new Properties();
		propObj.load(fis);

		propObj.put(key, value);

		FileOutputStream fos=new FileOutputStream(filePath);
		propObj.store(fos, "Data write successfull.....");
		
		fis.close();
		fos.close();
	}

}
