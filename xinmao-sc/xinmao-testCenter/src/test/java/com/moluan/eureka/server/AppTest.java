package com.moluan.eureka.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.xinmao.common.util.wechat.SecurityCheckUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.web.multipart.MultipartFile;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	public static void main(String[] args) {


	    //https://segmentfault.com/a/1190000019955207?utm_source=tag-newest 这里有些小程序如何用上检测接口


        String accessToken = SecurityCheckUtil.getAccessToken();
        MultipartFile mfile = SecurityCheckUtil.getMulFileByPath("https://image-testscale.oss-cn-hangzhou.aliyuncs.com/th.jpg");
        String textConetnt = "有违规文字内容测试特3456书yuuo莞6543李zxcz蒜7782法fgnv级";
		System.out.println("文字检测接口接口为："+SecurityCheckUtil.checkText(textConetnt,accessToken));
        System.out.println("图片检测接口接口为："+SecurityCheckUtil.checkPic(mfile,accessToken));
//        try {
//            // 下载网络文件
//            int bytesum = 0;
//            int byteread = 0;
//            InputStream inStream = mfile.getInputStream();
//            FileOutputStream fs = new FileOutputStream("D:/我的资料/心猫公司资料/music/th.jpg");
//
//            byte[] buffer = new byte[1204];
//            int length;
//            while ((byteread = inStream.read(buffer)) != -1) {
//                bytesum += byteread;
////                System.out.println(bytesum);
//                fs.write(buffer, 0, byteread);
//            }
//            System.out.println("-----Success-----");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



//		int age = 0;
//        Calendar now = Calendar.getInstance();
//        now.setTime(new Date());// 当前时间
//		try {
//	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//			String str = "1987-12-03";
//			Date birthday;
//
//		    birthday = sdf.parse(str);
//
//
//	        Calendar cal = Calendar.getInstance();
//
//	        if (cal.before(birthday)) {
//	            throw new IllegalArgumentException(
//	                    "The birthDay is before Now.It's unbelievable!");
//	        }
//	        int yearNow = cal.get(Calendar.YEAR);
//	        int monthNow = cal.get(Calendar.MONTH);
//	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
//	        cal.setTime(birthday);
//
//	        int yearBirth = cal.get(Calendar.YEAR);
//	        int monthBirth = cal.get(Calendar.MONTH);
//	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
//
//	        age = yearNow - yearBirth;
//
//	        if (monthNow <= monthBirth) {
//	            if (monthNow == monthBirth) {
//	                if (dayOfMonthNow < dayOfMonthBirth) age--;
//	            }else{
//	                age--;
//	            }
//	        }
//
//
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
//			System.out.println(age);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
