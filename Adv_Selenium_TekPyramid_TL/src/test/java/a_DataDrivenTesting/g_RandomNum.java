package a_DataDrivenTesting;

import java.security.SecureRandom;
import java.util.Random;

public class g_RandomNum {
	public static void main(String[] args) {
		
		//Generate Random number
		Random ran=new Random();
		int num=ran.nextInt(1000);
		System.out.println("Random number:- "+num);
		
		
		//Generate random AphaNumeric number
		int limit=20;
		String Alpha_Numeric="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
		SecureRandom random=new SecureRandom();
		StringBuilder sb=new StringBuilder(limit);
		
		for(int i=0; i<=limit; i++)
		{
			 int index = random.nextInt(Alpha_Numeric.length());
			 sb.append(Alpha_Numeric.charAt(index));
		}
		
		System.out.println("Alphanumeric:- "+sb);
	
	}
}
