package Java_Programs;

public class b_Palindrome_String {
	public static void main(String[] args) {
		
		String str="abcDDcba";
		String temp=str;
		
		String rev="";
		
		for(int i=0; i<temp.length(); i++)
		{
			rev=temp.charAt(i)+rev;
		}
		
		if(rev.equals(str))
		{
			System.out.println("String is Palindrome");
		}
		else
		{
			System.out.println("String is not Palindrome");
		}
		
	}

}
