//package com.xinmao.gateway.token;
//
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import com.xinmao.gateway.domain.TokenMessage;
//
///**
// * Token验证拦截器，用以验证token是否匹配
// * @author 李志坚
// * @date 2018/2/2.
// */
//@Component
//public class TokenInterceptor extends HandlerInterceptorAdapter {
//	
//    @Autowired
//    private TokenManager manager;
//    
//    @Autowired
//    private CookieManager cookieManager;
//
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//    	
//    	//从内存中调取相应的url，如果不匹配，说明不需要验证token，直接通过
//    	String requestUri = request.getRequestURI();
//        if(requestUri.contains("login")||requestUri.contains("logout")){
//        	return true;
//        }
//    	
//        //从header中得到token
////        String authorization = request.getHeader(TokenMessage.AUTHORIZATION);
//        
//        //从cookie中得到token
//        Cookie cookie = cookieManager.getCookieByName(request, TokenMessage.TOKEN_COOKIE_NAME);
//        if(cookie!=null){
//    	   String token = cookie.getValue();
//           if(token!=null){
//           	
//           	   //从头信息authorization中获取token
//               TokenMessage tokenMessage = manager.getToken(token);
//               
//               Date expiresTime = tokenMessage.getExpiresTime();
//               
//               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
// 	    	   String nowDateStr = sdf.format(new Date());//获取当前时间
// 	    	   Date nowDate =  sdf.parse(nowDateStr);
//               
//               //验证token如果token验证成功，将token对应的用户id存在request中，便于之后注入
//               if (manager.checkToken(tokenMessage)) {
//                    // request.setAttribute("userId", tokenMessage.getUserId());
//               	
//	               	//如果有效期小于半个小时，就重新再生成一个token
//	               	if((expiresTime.getTime()-nowDate.getTime())<TokenMessage.TOKEN_TIME_SECTION){
//	           			Calendar ca=Calendar.getInstance();
//	           			ca.setTime(new Date());
//	           			ca.add(Calendar.HOUR_OF_DAY, TokenMessage.TOKEN_EXPIRES_HOUR);
//	               		tokenMessage.setExpiresTime(ca.getTime());
//	               		String newToken = manager.createToken(tokenMessage);
//	                    //   response.setHeader(TokenMessage.AUTHORIZATION, token);
//	               		cookieManager.editCookie(request,response,TokenMessage.TOKEN_COOKIE_NAME,newToken);
//	               	}
//               	
//                    return true;
//               }
//           }
//        }
//        
//        //五：如果验证不通过，跳转到登录页面
//        response.sendRedirect("http://192.168.0.56:4002/");
//       
//        return false;
//    }
//}
