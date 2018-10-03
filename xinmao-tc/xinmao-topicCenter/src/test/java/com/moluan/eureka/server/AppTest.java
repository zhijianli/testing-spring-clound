package com.moluan.eureka.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	public static void main(String[] args) {
		int age = 0;
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());// 当前时间
		try {
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str = "1987-12-03";
			Date birthday;
			
		    birthday = sdf.parse(str);
			
		  
	        Calendar cal = Calendar.getInstance();  
	        
	        if (cal.before(birthday)) {  
	            throw new IllegalArgumentException(  
	                    "The birthDay is before Now.It's unbelievable!");  
	        }  
	        int yearNow = cal.get(Calendar.YEAR);  
	        int monthNow = cal.get(Calendar.MONTH);  
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
	        cal.setTime(birthday);  
	  
	        int yearBirth = cal.get(Calendar.YEAR);  
	        int monthBirth = cal.get(Calendar.MONTH);  
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
	  
	        age = yearNow - yearBirth;  
	  
	        if (monthNow <= monthBirth) {  
	            if (monthNow == monthBirth) {  
	                if (dayOfMonthNow < dayOfMonthBirth) age--;  
	            }else{  
	                age--;  
	            }  
	        }  
		    
		    
//	
//	
//	        Calendar birth = Calendar.getInstance();
//	        birth.setTime(birthday);
//	
//	        if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
//	            age = 0;
//	        } else {
//	            age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR)-1;
//	            if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
//	                age += 1;
//	            }
//	        }
//	        System.out.println(now.get(Calendar.YEAR));
//	        System.out.println(birth.get(Calendar.YEAR));
//			System.out.println(now.get(Calendar.DAY_OF_YEAR));
//			System.out.println(birth.get(Calendar.DAY_OF_YEAR));
			System.out.println(age);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
