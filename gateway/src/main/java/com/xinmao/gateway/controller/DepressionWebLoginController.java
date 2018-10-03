//package com.xinmao.gateway.controller;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.xinmao.common.entity.ErrorCode;
//import com.xinmao.common.entity.ResultEntity;
//import com.xinmao.common.userCenter.domain.Member;
//import com.xinmao.common.userCenter.domain.SystemUserInfo;
//import com.xinmao.common.util.MD5Util;
//import com.xinmao.gateway.domain.TokenMessage;
//import com.xinmao.gateway.service.MemberService;
//import com.xinmao.gateway.service.SystemUserInfoService;
//import com.xinmao.gateway.token.CookieManager;
//import com.xinmao.gateway.token.TokenManager;
//
///**
// * depression-web项目登录相关Controller
// * @author 李志坚
// * @date 2018/2/2.
// */
//@RestController
//@RequestMapping("/depressionWeb")
//public class DepressionWebLoginController {
//	
////    @Autowired
////    private MemberService memberService;
//    
//    @Autowired
//    private SystemUserInfoService systemUserInfoService;
//	
//    @Autowired
//    private TokenManager tokenManager;
//    
//    @Autowired
//    private CookieManager cookieManager;
//	
//	//登录
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public Object login(HttpServletResponse response,SystemUserInfo userInfo) {
//        ResultEntity result = new ResultEntity(); 
//        String token = null;
//
//        try{
//	        //检查用户信息
//        	result = checkUserInfo(userInfo,result);
//			if(result!=null && result.size()>0 && ResultEntity.ERROR==result.getCode()){
//				return result;
//			}
//			
//			//从数据库中获取用户
//			String mobilePhone = userInfo.getMobilePhone();
//			String password = userInfo.getUserPsw();
//			userInfo.setUserPsw(MD5Util.getMD5String(password));
//			SystemUserInfo suInfo = systemUserInfoService.getSystemUserInfo(userInfo);
//			if (null == suInfo) {
//				result.setCode(ResultEntity.ERROR);
//				result.setError("用户名或密码错误");
//				return result;
//			}
//
//			// 检查用户是否被禁用
//			Byte isEnable = suInfo.getIsEnable();
//			if (isEnable.intValue() == 1) {
//				result.setCode(ResultEntity.ERROR);
//				result.setError("登录失败");
//				return result;
//			}
//
//	        //创建token
//			TokenMessage tokenMessage = new TokenMessage();
//			tokenMessage.setUserId(suInfo.getUserId());
//			tokenMessage.setUserName(mobilePhone);
//			Calendar ca=Calendar.getInstance();
//			ca.setTime(new Date());
//			ca.add(Calendar.HOUR_OF_DAY, TokenMessage.TOKEN_EXPIRES_HOUR);
////			ca.add(Calendar.MINUTE, TokenMessage.TOKEN_EXPIRES_HOUR);
//			tokenMessage.setExpiresTime(ca.getTime());
//			
//	        token = tokenManager.createToken(tokenMessage);
//	        
//        }catch(Exception e){
//			result.setCode(ResultEntity.ERROR);
//			result.setMsg(e.getMessage());
//			return result;
//        }
//        
//        //将生成的token数据放入到head头的Authorization中
////        response.setHeader(TokenMessage.AUTHORIZATION, token);
//        
//        //将生成的token数据放入到cookie中
//        cookieManager.addCookie(response,TokenMessage.TOKEN_COOKIE_NAME,token);
//		result.setCode(ResultEntity.SUCCESS);
//		result.setMsg("登录成功！");
//		
//        return result;
//
//    }
//    
//    //退出登录
//    @RequestMapping(value = "/logout",method = RequestMethod.GET)
//    public Object logout(HttpServletRequest request,HttpServletResponse response) {
//    	 ResultEntity result = new ResultEntity();
//    	 
//    	 //删除token
////        tokenManager.deleteToken(response);
//    	 cookieManager.delCookie(request, response, TokenMessage.TOKEN_COOKIE_NAME);
// 		 result.setCode(ResultEntity.SUCCESS);
// 		 result.setMsg("退出成功！");
//         return result;
//    }
//
//
//
//	private ResultEntity checkUserInfo(SystemUserInfo userInfo, ResultEntity result) {
//		String mobilePhone = userInfo.getMobilePhone();
//		String password = userInfo.getUserPsw();
//		String code = userInfo.getCode();
//		
//		if (null == userInfo || 
//			StringUtils.isEmpty(code) || 
//		    StringUtils.isEmpty(mobilePhone) ||
//		    StringUtils.isEmpty(password)) {
//			result.setCode(ResultEntity.ERROR);
//			result.setError("获取用户登录数据为空");
//			return result;
//		}
//
//		// 检查验证码是否正确
////			MemberAuthCode memberAuthCode = new MemberAuthCode();
////			memberAuthCode.setMobilePhone(mobilePhone);
////			MemberAuthCode authCode = memberAuthCodeService
////					.getAuthCode(memberAuthCode);
////			if (null == authCode) {
////				result.setCode(ResultEntity.ERROR);
////				result.setError("手机号不正确");
////				return result;
////			}
////			if (!code.equals(authCode.getAuthCode())) {
////				result.setCode(ResultEntity.ERROR);
////				result.setError("验证码不正确");
////				return result;
////			}
//		return result;
//	}
//    
//    
//
//}
