package Java_Programs;

public class c_EvenAndOdd_Count {
	public static void main(String[] args) {
		
		int num=123456789;
		int evenCount=0;
		int oddCount=0;
		
		while(num!=0)
		{
			int temp=num%10;
			if(temp%2==0)
			{
				evenCount++;
			}
			else
			{
				oddCount++;
			}
			
			num=num/10;
		}
		
		System.out.println("Even Count= "+evenCount);
		System.out.println("Odd Count= "+oddCount);
	}

}
