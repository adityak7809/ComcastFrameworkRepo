package Java_Programs;

public class b_Palindrome_Number {
	public static void main(String[] args) {
		int num=12321;
		int temp=num;
		int rev=0;
		
		while(temp!=0)
		{
			rev=rev*10 + temp%10;
			temp=temp/10;
		}
		
		
		
		if(num==rev)
		{
			System.out.println("Number is Palindrome");
		}
		else
		{
			System.out.println("Number is not Palindrome");
		}
	}

}
