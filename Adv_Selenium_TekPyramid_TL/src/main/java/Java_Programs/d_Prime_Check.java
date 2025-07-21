package Java_Programs;

public class d_Prime_Check {
	public static void main(String[] args) {
		int num=4;
		
		int count=0;
		
		for(int i=1; i<=num; i++)
		{
			if(num%i==0)
			{
				count++;
			}
		}
		if(count==2)
		{
			System.out.println(num+" is Prime Number");
		}
		else
		{
			System.out.println(num+" is not a Prime Number");
		}
	}

}
