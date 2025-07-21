 package Java_Programs;

public class d_Prime_NumberPrint {
	public static void main(String[] args) {
		 int limit=10;
		 int count=0;
		 int num=2;
		 
		 while(count<limit)
		 {
			 int tempCount=0;
			 for(int i=1; i<=num; i++)
			 {
				 if(num%i==0)
				 {
					 tempCount++;
				 }
			 }
			 if(tempCount==2)
			 {
				 System.out.println(num);
				 count++;
			 }
			 num++;
		 }
	}
}
