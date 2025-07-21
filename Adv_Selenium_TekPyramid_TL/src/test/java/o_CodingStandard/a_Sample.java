package o_CodingStandard;

import com.concast.crm.generic.webdriverUtility.JavaUtility;

/**
 * @author Aditya
 * This is a sample class
 */
public class a_Sample {
	
	public static void main(String[] args) {
		
		
		/* This is Object creation  for Java Utility*/
		JavaUtility obj=new JavaUtility();
		long num=obj.getRandomNumber(1000000000);
		
		System.out.println(num);
	}

}
