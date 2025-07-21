package Java_Programs;

public class a_Reverse_String {
	public static void main(String[] args) {
		
		String str="This is a Java Program";
		String rev="";
		
		
		for(int i=0; i<str.length(); i++)
		{
			rev=str.charAt(i)+rev;
		}
		System.out.println(rev);
	}

}
