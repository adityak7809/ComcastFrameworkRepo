package l_Screenshots;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test_1 {
	
	public static void main(String[] args) {
		
		Date sysDate=new Date();
		
		System.out.println(sysDate.toString());
		
		SimpleDateFormat sim=new SimpleDateFormat("HH_mm_ss");
		
		String time=sim.format(sysDate);
		System.out.println(time);
		
		
	}

}
