package com.concast.crm.generic.fileUtility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonUtility {

	public String  getDataFromJsonFile(String key) throws FileNotFoundException, IOException, ParseException
	{
		String filePath="./configAppData/CommonData.json";

		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader(filePath));
		JSONObject map=(JSONObject)obj;

		String data=map.get(key).toString();
		
		return data;
	}
	
	public void setDataToJsonFile(String key, String value) throws FileNotFoundException, IOException, ParseException
	{
		String filePath="./configAppData/CommonData.json";

		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader(filePath));
		JSONObject map=(JSONObject)obj;
		
		map.put(key, value);
		
		try 
		(FileWriter file=new FileWriter(filePath)){
		file.write(map.toJSONString());
		file.flush();
		}
		
	}

}
