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

import com.xinmao.sc.testCenter.api.TestScaleQualitativeApi;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleQualitativeMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.service.TestScaleInfoService;
import com.xinmao.sc.testCenter.service.TestScaleQualitativeService;


@RestController
//@RequestMapping("testCenter/testScaleQualitative")
public class TestScaleQualitativeController implements TestScaleQualitativeApi{

//	Logger log = Logger.getLogger(this.getClass());
	
	private static final Logger log = LogManager.getLogger();
	
    @Autowired
    private TestScaleQualitativeService service;

    @Autowired
    private TestScaleInfoService tsiService;

    
	@Override
	public ResultEntity getMessageByIdFromFront(@RequestBody TestScaleQualitativeMessage tsqMess) {
		
		ResultEntity result = new ResultEntity(); 
    	TestScaleQualitativeMessage tsqMessage = new TestScaleQualitativeMessage();
    	
    	try{
    	
	        Integer qualitativeId =tsqMess.getId();
		    if(qualitativeId==null || qualitativeId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    tsqMessage = service.getMessageByIdFromFront(qualitativeId);
    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("tsqMessage", tsqMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
		
	}
	
    @RequestMapping(value="/getMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageById(HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	TestScaleQualitativeMessage tsqMessage = new TestScaleQualitativeMessage();
    	
    	try{
    	
	        Integer qualitativeId =Integer.parseInt(request.getParameter("qualitativeId"));
		    if(qualitativeId==null || qualitativeId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    tsqMessage = service.getMessageById(qualitativeId);
    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("tsqMessage", tsqMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TestScaleQualitativeMessage tsqMessage){
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleQualitativeMessage> list = new ArrayList<TestScaleQualitativeMessage>();
    	int count = 0;
    	try{
    		Integer testScaleId =tsqMessage.getRelateTestScaleId();
		    if(testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		list = service.getAllMessage(tsqMessage);
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
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(@RequestBody TestScaleQualitativeMessage testScaleQualitativeMessage, HttpServletRequest request){
      	ResultEntity result = new ResultEntity(); 
    	
    	try{
	        Integer relateTestScaleId = testScaleQualitativeMessage.getRelateTestScaleId();
	        String name = testScaleQualitativeMessage.getName();
	        Integer qualitativeType = testScaleQualitativeMessage.getQualitativeType();
		    if(relateTestScaleId==null || relateTestScaleId==0 || 
		    		name==null || name.isEmpty() || qualitativeType==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
	    	Integer testScaleId = testScaleQualitativeMessage.getRelateTestScaleId();
	    	
	    	//判断相关量表是否存在
	    	TestScaleInfoMessage tsiMessage = tsiService.getMessageById(testScaleId);
	    	if(tsiMessage == null){
	    		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	    	}
		    
		    //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(relateTestScaleId,result)){
				return result;
	        }
	     	
		    Boolean isSuccess = service.addMessage(testScaleQualitativeMessage,testScaleId);
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
    	result.put("testScaleQualitativeId", testScaleQualitativeMessage.getId());
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(@RequestBody TestScaleQualitativeMessage testScaleQualitativeMessage ,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	
    	try{
    		
	        Integer id = testScaleQualitativeMessage.getId();
	        String name = testScaleQualitativeMessage.getName();
	        Integer qualitativeType = testScaleQualitativeMessage.getQualitativeType();
		    if(id==null || id==0 || name==null || name.isEmpty() || qualitativeType==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
	    	
	    	//根据id判断此定性是否存在，不存在抛出异常
	    	Integer qualitativeId = testScaleQualitativeMessage.getId();
	    	TestScaleQualitativeMessage tsqMessage = service.getMessById(qualitativeId);
	    	if(tsqMessage==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	    	}
	    	
	    	Integer testScaleId = tsqMessage.getRelateTestScaleId();
		    
	    	//判断相关量表是否存在
	    	TestScaleInfoMessage tsiMessage = tsiService.getMessageById(testScaleId);
	    	if(tsiMessage == null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	    	}
	    	
	    	
		    //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(testScaleId,result)){
				return result;
	        }
	        
	        isSuccess = service.updateMess(testScaleQualitativeMessage,testScaleId);
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
		result.put("isSuccess", isSuccess);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "/delete",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object deleteMessage(TestScaleQualitativeMessage testScaleQualitativeMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	        
	        Integer id = testScaleQualitativeMessage.getId();
	        Integer testScaleId = testScaleQualitativeMessage.getRelateTestScaleId();
	        if(id==null || id==0 ||
	           testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        
		    //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(testScaleId,result)){
				return result;
	        }
	        
	        result = service.deleteMess(testScaleQualitativeMessage);
	      
        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
		return result;
    }

    @RequestMapping(value="/getMessageByIdList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public List<TestScaleQualitativeMessage>  getMessageByIdList(@RequestBody TestScaleQualitativeMessage tsqMessage) {
//    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleQualitativeMessage> tsqmList = new ArrayList<TestScaleQualitativeMessage>();
    	
        try{
	        
        	List<Integer> searchIdList = tsqMessage.getSearchIdList();
	        if(searchIdList==null || searchIdList.size()<=0){
				return tsqmList;
		    }
	        
	        tsqmList = service.getMessageByIdList(tsqMessage);
        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return tsqmList;
    	}
		return tsqmList;
	}

	@Override
	public List<TestScaleQualitativeMessage> getListByTestScaleId(@RequestBody TestScaleQualitativeMessage tsqMessage) {
        List<TestScaleQualitativeMessage> tsqmList = new ArrayList<TestScaleQualitativeMessage>();
    	
        try{
        	Integer testScaleId = tsqMessage.getRelateTestScaleId();
	        if(testScaleId==null || testScaleId<=0){
				return tsqmList;
		    }
	        tsqmList = service.selectAll(testScaleId);
        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return tsqmList;
    	}
		return tsqmList;
	}
}

