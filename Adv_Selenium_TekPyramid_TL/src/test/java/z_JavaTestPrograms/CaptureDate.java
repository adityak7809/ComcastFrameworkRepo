package z_JavaTestPrograms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CaptureDate {
	
	public static void main(String[] args) {
		
		//Capture System date
		Date dateObj=new Date();
		System.out.println("System date:- "+dateObj.toString());
		
		//Capture current date in required format
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String date=sim.format(dateObj);
		System.out.println("Current date:- "+date);
		
		//Capture required date
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String dateReq=sim.format(cal.getTime());
		System.out.println("Req date:- "+dateReq);
		
	}

}
