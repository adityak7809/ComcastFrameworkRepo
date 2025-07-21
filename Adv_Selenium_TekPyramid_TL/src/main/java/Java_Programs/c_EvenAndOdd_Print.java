package Java_Programs;

public class c_EvenAndOdd_Print {
	public static void main(String[] args) {
		
		int num=123456789;
		int even=0;
		int odd=0;
		
		while(num!=0)
		{
			int temp=num%10;
			num=num/10;
			if(temp%2==0)
			{
				even=even*10+temp;
			}
			else
			{
				odd=odd*10+temp;
			}
		}
		
		System.out.println("Even: "+even);
		System.out.println("Odd: "+odd);
		
	}

}
