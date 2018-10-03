package com.xinmao.sc.orderCenter.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.orderCenter.domain.TestscaleShareFriendsRelation;
import com.xinmao.sc.orderCenter.service.TestscaleShareFriendsRelationService;


@RestController
@RequestMapping("testscaleShareFriendsRelation")
public class TestscaleShareFriendsRelationController{
	
	private static final Logger log = LogManager.getLogger();

    @Autowired
    private TestscaleShareFriendsRelationService service;
    
    @RequestMapping(value = "insertRelation",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object insertRelation(TestscaleShareFriendsRelation record , HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	try{
    		    Long sharingPersonId = record.getSharingPersonId();
    		    Long testerId = record.getTesterId();
    		    Long testScaleId = record.getTestScaleId();
    		    if(sharingPersonId==null || sharingPersonId<=0 || 
    		       testerId==null || testerId<=0 ||
    		       testScaleId==null || testScaleId<=0 ){
    		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
    				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
    				return result;
    		    }
    		   
    		    Date currentTime = new Date();
    		    record.setIsDelete((byte)0);
    		    record.setIsEnable((byte)0);
    		    record.setCreateTime(currentTime);
    		    record.setUpdateTime(currentTime);
    	     	
    		    record = service.insertRelation(record);
    		    
    		    Long id = record.getId();
    	        if(id==null || id<=0){
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
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
}

