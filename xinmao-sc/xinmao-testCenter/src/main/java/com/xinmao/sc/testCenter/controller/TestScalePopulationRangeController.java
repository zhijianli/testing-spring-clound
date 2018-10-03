package com.xinmao.sc.testCenter.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScalePopulationRangeMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.service.TestScaleInfoService;
import com.xinmao.sc.testCenter.service.TestScalePopulationRangeService;


@RestController
@RequestMapping("testScalePopulationRange")
public class TestScalePopulationRangeController {

//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
	
    @Autowired
    private TestScalePopulationRangeService service;
    
    @Autowired
    private TestScaleInfoService tsiService;
    
    
    @RequestMapping(value="/getMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageById(HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	List<TestScalePopulationRangeMessage> list = new ArrayList<TestScalePopulationRangeMessage>();
    	
    	try{
    		
//	        int id = Integer.parseInt(request.getParameter("id"));
//	        list = service.getMessageById(id);
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("contentList", list);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TestScalePopulationRangeMessage tsprMessage){
    	ResultEntity result = new ResultEntity(); 
    	List<TestScalePopulationRangeMessage> list = new ArrayList<TestScalePopulationRangeMessage>();
    	int count = 0;
    	Integer displayMode = 0;
    	try{
    		Integer testScaleId =tsprMessage.getRelateTestScaleId();
		    if(testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
		    //根据id获取量表前端展示样式
		    TestScaleInfoMessage tsiMess = tsiService.getMessageById(testScaleId);
		    displayMode = tsiMess.getDisplayMode();
		    
	        list = service.getAllMessage(tsprMessage);
	        if(list!=null&&list.size()>0){
	        	 count = list.size();
	        }
	       
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("contentList", list);
    	result.put("count", count);
    	result.put("displayMode", displayMode);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    @RequestMapping(value = "add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(@RequestBody TestScaleInfoMessage testScaleInfoMessage , HttpServletRequest request){
      	ResultEntity result = new ResultEntity(); 
      	Boolean isSuccess = false;
    	try{
	        
	        Integer testScaleId = testScaleInfoMessage.getId();
		    if(testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
	        //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(testScaleId,result)){
				return result;
	        }
    		
	        isSuccess = service.addMessage(testScaleInfoMessage);
			if(!isSuccess){
				result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
			}
	        
		}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
		}
		result.put("isSuccess",isSuccess);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(@RequestBody TestScaleInfoMessage testScaleInfoMessage ,HttpServletRequest request){
    	
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	       
	        Integer testScaleId = testScaleInfoMessage.getId();
	        if(testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        
	        //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(testScaleId,result)){
				return result;
	        }
	    	
	        result = service.updateMess(testScaleInfoMessage);
	        
       	}catch(Exception e){
       		log.error(e.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}

		return result;
    }
}

