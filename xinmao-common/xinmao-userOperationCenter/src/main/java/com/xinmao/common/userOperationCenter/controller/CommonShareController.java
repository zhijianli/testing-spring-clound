package com.xinmao.common.userOperationCenter.controller;


import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;
import com.xinmao.common.userOperationCenter.domain.CommonShare;
import com.xinmao.common.userOperationCenter.service.CommonShareService;


@RestController
@RequestMapping("commonShare")
public class CommonShareController {
	
	Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CommonShareService service;
    
    
    @RequestMapping(value = "add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(CommonShare commonShare){
         
    	ResultEntity result = new ResultEntity(); 
    	Long shareId = 0l;
    	try{
    		
    		Long articleId = commonShare.getArticleId();
    		Long mid = commonShare.getMid();
    		Byte plateformType = commonShare.getPlateformType();
    		
    		if(articleId==null || articleId==0||
    		   mid==null || mid==0||
    		   plateformType==null || plateformType==0){
		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		
		    Date currentTime = new Date();
		    commonShare.setIsDelete(0);
		    commonShare.setShareTime(currentTime);
		    commonShare.setSource(CommonShare.SOURCE_TEST_SCALE);
	        service.addMessage(commonShare);
	        
	        shareId = commonShare.getShareId();
	        if(shareId==null || shareId==0){
	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
				return result;
	        }
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("shareId",shareId);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
}

