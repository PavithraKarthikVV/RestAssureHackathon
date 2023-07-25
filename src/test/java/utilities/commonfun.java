package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;

public class commonfun {
	
	public static long phoneNum() {
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder();

	    // first not 0 digit
	    sb.append(random.nextInt(9) + 1);
	    // rest of 9 digits
	    for (int i = 0; i < 9; i++) {
	        sb.append(random.nextInt(10));
	    }

	    return Long.valueOf(sb.toString()).longValue();
	}
	public static String getcurrentDateTime() {
		  Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	        String strDate = sdf.format(cal.getTime());
			return strDate.toString();
	}
public static String duedate() {

	LocalDateTime Time = LocalDateTime.now().plusDays(10);
	String date = String.valueOf(Time);
	return date;
}
}
