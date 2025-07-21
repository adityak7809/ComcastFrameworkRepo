package a_DataDrivenTesting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test2 {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader("./src/test/resources/AppCommonData.json"));
		
		JSONObject map=(JSONObject)obj;
		
		System.out.println(map.get("url"));
		String username=map.get("username").toString();
		System.out.println(username);
		
		map.put("orgName", "Facebook");
		
		
		
	}
}
