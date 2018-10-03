package com.xinmao.gateway.token;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
/**
 * 
 * DES加密工具类，提供DES加密解密方法
 * @author 李志坚
 * @date 2018/2/2.
 *
 DES加密介绍
      DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。
 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DES {
	
	
    /**
     * 秘钥，长度要是8的倍数
     * 
     */
    public static final String SECRET_KEY_STR = "12345678";

    /**
     * 加密
     */
    public static  byte[] encrypt(byte[] datasource) {            
        try{
	        SecureRandom random = new SecureRandom();
	        
	        //创建一个密匙工厂，然后用它把DESKeySpec转换成
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        
	        DESKeySpec desKey = new DESKeySpec(SECRET_KEY_STR.getBytes());
	        
	        SecretKey securekey = keyFactory.generateSecret(desKey);
	        
	        //Cipher对象实际完成加密操作
	        Cipher cipher = Cipher.getInstance("DES");
	        
	        //用密匙初始化Cipher对象
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
	        
	        //现在，获取数据并加密
	        //正式执行加密操作
	        return cipher.doFinal(datasource);
        }catch(Throwable e){
                e.printStackTrace();
        }
        return null;
}
    /**
     * 解密
     */
    public static byte[] decrypt(byte[] src) throws Exception {
    	
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(SECRET_KEY_STR.getBytes());
            
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            
            // 真正开始解密操作
            return cipher.doFinal(src);
    }
    
    //测试
	public static void main(String args[]) {

	     //待加密内容
	     String str = "测试内容";
	     
	     byte[] result = DES.encrypt(str.getBytes());
	     System.out.println("加密后："+new String(result));
	     //直接将如上内容解密
	     try {
	             byte[] decryResult = DES.decrypt(result);
	             System.out.println("解密后："+new String(decryResult));
	     } catch (Exception e1) {
	             e1.printStackTrace();
	     }
	}
}