//package com.xinmao.gateway.token;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.stereotype.Component;
//
//import com.xinmao.gateway.domain.TokenMessage;
//
///**
// * token管理类，负责token的获取，创建，删除，检查
// * @author 李志坚
// * @date 2018/2/2.
// */
//@Component
//public class TokenManager {
//
//	
//	   public String createToken (TokenMessage tokenMessage) throws Exception {
//		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
//	    	String userId = tokenMessage.getUserId()+"";
//	    	String userName = tokenMessage.getUserName();
//	    	String expiresTime = simpleDateFormat.format(tokenMessage.getExpiresTime());
//	    	
//	    	Base64 base64 = new Base64();
//	    	String userIdStr = base64.encodeToString(userId.getBytes(TokenMessage.CHARACTER_SET));
//	    	String userNameStr = base64.encodeToString(userName.getBytes(TokenMessage.CHARACTER_SET));
//	    	String expiresTimeStr = base64.encodeToString(expiresTime.getBytes(TokenMessage.CHARACTER_SET));
//	    	
//	    	String tokenStr = userIdStr+TokenMessage.SPLIT_CHARACTER+
//	    			userNameStr+TokenMessage.SPLIT_CHARACTER+
//	    			expiresTimeStr+TokenMessage.SPLIT_CHARACTER;
//	    	
//	       	//用秘钥加密
//	    	byte[] tokenByte = tokenStr.getBytes(TokenMessage.CHARACTER_SET);
//	    	byte[] desTokenByte = DES.encrypt(tokenByte);
//
//	    	return base64.encodeToString(desTokenByte);
//	    	
//	    }
//
//	    public TokenMessage getToken (String token) throws Exception {
//	    	Base64 base64 = new Base64();
//	    	
//	    	byte[] desTokenByte =  base64.decode(token);
//	    	byte[] tokenByte = DES.decrypt(desTokenByte);
//	    	
//	    	String tokenStr = new String(tokenByte,TokenMessage.CHARACTER_SET);
//	    	String[] tokenStrAry = tokenStr.split(TokenMessage.SPLIT_CHARACTER);
//	    	String userIdStr = tokenStrAry[0];
//	    	String userNameStr = tokenStrAry[1];
//	    	String expiresTimeStr = tokenStrAry[2];
//	    	
//	    	
//	    	String userId = new String(base64.decode(userIdStr), TokenMessage.CHARACTER_SET);
//	    	String userName = new String(base64.decode(userNameStr), TokenMessage.CHARACTER_SET);
//	    	String expiresTime = new String(base64.decode(expiresTimeStr), TokenMessage.CHARACTER_SET);
//	    	
//	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
//	    	TokenMessage tokenMessage = new TokenMessage();
//	    	tokenMessage.setUserId(Long.valueOf(userId));
//	    	tokenMessage.setUserName(userName);
//	    	Date expiresTimeDate = format.parse(expiresTime.trim());
//	    	tokenMessage.setExpiresTime(expiresTimeDate);
//	    	
//	    	return tokenMessage;
//	    	
//	    }
//
//	    public boolean checkToken (TokenMessage tokenMessage) throws Exception {
//	    	  
//	    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
//	    	  String nowDateStr = sdf.format(new Date());
//	    	  Date nowDate =  sdf.parse(nowDateStr);
//	    	  
//	    	  Date expiresTime = tokenMessage.getExpiresTime();
//              
//              //如果当期时间小于过期时间，说明还没过期，验证通过
//              if (nowDate.getTime()<expiresTime.getTime()) {
//            	  return true;
//              }
//              
//              return false;
//	    }
//
//	    public void deleteToken (HttpServletResponse response) {
//	    	response.setHeader(TokenMessage.AUTHORIZATION,"");
//	    }
//	
//}
