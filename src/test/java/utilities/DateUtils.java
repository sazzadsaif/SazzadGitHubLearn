package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static String returnNextMonth(){

        // Creating Date Object

        Date dNow = new Date();

        //Creating Calender object for Gregorian Calender

        Calendar calendar = new GregorianCalendar();

        // Set Calender date to current date

        calendar.setTime(dNow);

        // Create Object for SimpleDateFormat

        //Defining Date Format to - example: (Jan-2000)

        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");

        // Incrementing the current date

        calendar.add(Calendar.MONTH,1);

        //Generating the date based on the specified format

        String date =  sdf.format(calendar.getTime());

        return date;

    }
}
