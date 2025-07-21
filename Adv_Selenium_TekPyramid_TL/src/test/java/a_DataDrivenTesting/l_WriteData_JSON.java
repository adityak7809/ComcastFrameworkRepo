package a_DataDrivenTesting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class l_WriteData_JSON {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
//		JSONParser parser=new JSONParser();
//		Object obj=parser.parse(new FileReader("./src/test/resources/AppCommonData.json"));
		
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader("./src/test/resources/AppCommonData.json"));
		
		JSONObject map=(JSONObject)obj;
		
		// Read data from JSON----------------------------
		String url=map.get("url").toString();
		System.out.println(url);
		
		
		//Write data into JSON
		map.put("name", "Aditya");
		
		//Store data to JSON file
		FileWriter file=new FileWriter("./src/test/resources/AppCommonData.json");
		file.write(map.toJSONString());
	    file.flush();
	
	    System.out.println(map.get("name").toString());
		
	}

}
