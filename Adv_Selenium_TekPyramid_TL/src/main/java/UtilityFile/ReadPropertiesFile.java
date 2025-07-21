package UtilityFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {

	public String readData(String key) throws IOException
	{
		String filePath="./src/test/resources/CommonData.properties";
		FileInputStream file=new FileInputStream(filePath);

		Properties prop=new Properties();
		prop.load(file);

		String data=prop.getProperty(key);

		return data;
	}

	public void write(String key, String value) throws IOException
	{
		String filePath="./src/test/resources/CommonData.properties";
		FileInputStream file=new FileInputStream(filePath);

		Properties prop=new Properties();
		prop.load(file);

		prop.put(key, value);

		FileOutputStream fos=new FileOutputStream(filePath);
		prop.store(fos, "Successfull");

	}

}
