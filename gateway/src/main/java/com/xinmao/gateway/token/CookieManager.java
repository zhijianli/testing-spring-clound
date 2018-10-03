//package com.xinmao.gateway.token;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//
///**
// * cookie管理类，负责cookie的获取，创建，删除
// * @author 李志坚
// * @date 2018/2/7
// */
//@Component
//public class CookieManager {
//	
//    /**
//     * cookie的有效期（秒）
//     * 6小时
//     */
//    public static final int COOKIE_EXPIRES_HOUR = 21600;
//	
//    /**  
//     * 添加cookie  
//     * @param response  
//     * @param name  
//     * @param value  
//     */  
//    public void addCookie(HttpServletResponse response,String name,String value){  
//        Cookie cookie = new Cookie(name.trim(), value.trim());  
//        cookie.setMaxAge(COOKIE_EXPIRES_HOUR);
//        cookie.setPath("/");  
//        response.addCookie(cookie);  
//    }  
//    /**  
//     * 修改cookie  
//     * @param request  
//     * @param response  
//     * @param name  
//     * @param value  
//     * 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。  
//     */  
//    public void editCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){  
//        Cookie[] cookies = request.getCookies();  
//        if(null==cookies) {  
//            System.out.println("没有cookie==============");  
//        }else{  
//            for(Cookie cookie : cookies){  
//                if(cookie.getName().equals(name)){  
//                    System.out.println("原值为:"+cookie.getValue());  
//                    cookie.setValue(value);  
//                    cookie.setPath("/");  
//                    cookie.setMaxAge(COOKIE_EXPIRES_HOUR);  
//                    response.addCookie(cookie);  
//                    break;  
//                }  
//            }  
//        }  
//    } 
//    
//    /**  
//     * 删除cookie  
//     * @param request  
//     * @param response  
//     * @param name  
//     */  
//    public void delCookie(HttpServletRequest request,HttpServletResponse response,String name){  
//        Cookie[] cookies = request.getCookies();  
//        if(null==cookies) {  
//            System.out.println("没有cookie==============");  
//        }else{  
//            for(Cookie cookie : cookies){  
//                if(cookie.getName().equals(name)){  
//                    cookie.setValue(null);  
//                    cookie.setMaxAge(0);// 立即销毁cookie  
//                    cookie.setPath("/");  
//                    response.addCookie(cookie);  
//                    break;  
//                }  
//            }  
//        }  
//    }  
//
//
//	/**  
//	 * 根据名字获取cookie  
//	 * @param request  
//	 * @param name cookie名字  
//	 * @return  
//	 */  
//	public Cookie getCookieByName(HttpServletRequest request,String name){  
//	    Map<String,Cookie> cookieMap = ReadCookieMap(request);  
//	    if(cookieMap.containsKey(name)){  
//	        Cookie cookie = (Cookie)cookieMap.get(name);  
//	        return cookie;  
//	    }else{  
//	        return null;  
//	    }    
//	}  
//	
//    /**  
//     * 将cookie封装到Map里面  
//     * @param request  
//     * @return  
//     */  
//    private Map<String,Cookie> ReadCookieMap(HttpServletRequest request){    
//        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();  
//        Cookie[] cookies = request.getCookies();  
//        if(null!=cookies){  
//            for(Cookie cookie : cookies){  
//                cookieMap.put(cookie.getName(), cookie);
//            }  
//        }  
//        return cookieMap;  
//    } 
//
//}
