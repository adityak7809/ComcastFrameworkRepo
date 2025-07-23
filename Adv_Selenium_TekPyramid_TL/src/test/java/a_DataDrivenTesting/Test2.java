package a_DataDrivenTesting;

import java.util.Arrays;

import org.testng.annotations.Test;

public class Test2 {
	
	@Test
	public void m1() {
		
int arr[]= {1,2,3,4,5,6,7};
		
		int rev[]=new int[arr.length];
		
		
		for(int i=arr.length-1; i>=0; i--)
		{
			for(int j=0; j<rev.length; j++)
			{
				rev[j]=arr[i];
			}
		}
		
		System.out.println(Arrays.toString(rev));
		
		
		
	}
}
