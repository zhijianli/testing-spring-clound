package com.xinmao.common.userOperationCenter.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;
import com.xinmao.common.userCenter.domain.wechat.MemberWechat;
import com.xinmao.common.userCenter.domain.wechat.UserWeiXin;
import com.xinmao.common.userOperationCenter.api.CommonCollectionApi;
import com.xinmao.common.userOperationCenter.domain.CommonCollection;
import com.xinmao.common.userOperationCenter.service.CommonCollectionService;
import com.xinmao.common.userOperationCenter.service.MemberWechatService;
import com.xinmao.common.util.wechat.OAuthService;

/**
 * 获取微信用户信息
 * 
 * @author admin
 * 
 */
@RestController
@RequestMapping("memberWechat")
public class MemberWechatController{
	
	Logger log = Logger.getLogger(this.getClass());

//    @Autowired
//    private CommonCollectionService service;
	
	 @Autowired
     private MemberWechatService memberWechatService;
    
    /**
     * @Description: 根据code查询用户信息 如若没有此用户 则创建新用户
     * @Author: 李志坚
     * @Date: 2018/7/4 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getuserInfo.json")
    @ResponseBody
    public Object getuserInfo(String code,UserWeiXin userWeiXin) {
        ResultEntity result = new ResultEntity();
//        Long mid = null;
        MemberWechat memberWechat = new MemberWechat();
        if (code == null || code.equals("")) {
            result.setCode(-1);
            result.setMsg("code不能为空");
            return result;
        }
        try {
            String openId = OAuthService.getXcxOpenId(code);
            if(openId != null){
                userWeiXin.setOpenid(openId);
                memberWechat = memberWechatService.getWechatUser(userWeiXin);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.put("mid", memberWechat.getMid());
        result.put("nickname", memberWechat.getNickname());
        result.put("headimgurl", memberWechat.getHeadimgurl());
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }
    
//    @RequestMapping(value = "collectionTestScale",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
//    public Object collectionTestScale(CommonCollection commonCollection){
//         
//    	ResultEntity result = new ResultEntity(); 
//    	Boolean isSuccess = false;
//    	try{
//    		
//    		Long articleId = commonCollection.getArticleId();
//    		Long mid = commonCollection.getMid();
//    		Integer isCollected = commonCollection.getIsCollected();
//    		
//    		if(articleId==null || articleId==0||
//    		   mid==null || mid==0 || 
//    		   isCollected == null){
//		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//				return result;
//    		}
//    		
//    		if(isCollected == commonCollection.IS_COLLECTED){
//    			
//    	    	    Long id = 0l;
//    			    Date currentTime = new Date();
//    			    commonCollection.setIsDelete(0);
//    			    commonCollection.setCollectionTime(currentTime);
//    			    commonCollection.setCreateTime(currentTime);
//    			    commonCollection.setModifyTime(currentTime);
//    			    commonCollection.setSource(CommonCollection.SOURCE_TEST_SCALE);
//    		     	
//    		        service.addMessage(commonCollection);
//    		        
//    		        id = commonCollection.getColId();
//    		        if(id==null || id==0){
//    		        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//    					result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
//    					return result;
//    		        }
//    		}else if(isCollected == commonCollection.IS_NOT_COLLECTED){
//    			
//    	    	    Integer updateNum = 0;
//    			    Date currentTime = new Date();
//    			    commonCollection.setIsDelete(1);
//    			    commonCollection.setModifyTime(currentTime);
//    			    commonCollection.setSource(CommonCollection.SOURCE_TEST_SCALE);
//    		     	
//    		        updateNum = service.cancelCollection(commonCollection);
//    		        
//    		        if(updateNum==null || updateNum==0){
//    		        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//    					result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
//    					return result;
//    		        }
//    		}else{
//    	     	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//    			result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//    			return result;
//    		}
//    		
//    	}catch(Exception e){
//    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
//			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//			result.setMsg(e.getMessage());
//			return result;
//    	}
//    	isSuccess = true;
//    	result.put("isSuccess",isSuccess);
//		result.setCode(ErrorCode.SUCCESS.getCode());
//		result.setMsg(ErrorCode.SUCCESS.getMessage());
//		return result;
//    }
    
}

