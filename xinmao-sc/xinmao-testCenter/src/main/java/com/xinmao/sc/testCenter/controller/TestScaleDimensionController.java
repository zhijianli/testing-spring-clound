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

import com.xinmao.sc.testCenter.api.TestScaleDimensionApi;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.service.TestScaleDimensionService;
import com.xinmao.sc.testCenter.service.TestScaleInfoService;


@RestController
@RequestMapping("testScaleDimension")
public class TestScaleDimensionController{

//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
	
    @Autowired
    private TestScaleDimensionService service;
    

    @Autowired
    private TestScaleInfoService tsiService;

//	@Override
//	public List<TestScaleDimensionMessage> getMessageByTestScaleId(TestScaleDimensionMessage tsdMessage) {
//    	List<TestScaleDimensionMessage> list = new ArrayList<TestScaleDimensionMessage>();
//    	try{
//    	
//    		Integer testScaleId =tsdMessage.getRelateTestScaleId();
//		    if(testScaleId==null || testScaleId==0){
//				return list;
//		    }
//    		
//	        list = service.getMessageByTestScaleId(tsdMessage);
//        
//    	}catch(Exception e){
//			return list;
//    	}
//    	
//		return list;
//	}
    
    @RequestMapping(value = "/setIsShowDimension",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object setIsShowDimension(TestScaleDimensionMessage testScaleDimensionMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	
    	try{
	        
	        Integer id = testScaleDimensionMessage.getId();
	        Integer isShowDimension = testScaleDimensionMessage.getIsShowDimension();
		    if(id==null || id==0 || isShowDimension==null || 
		       (isShowDimension!=TestScaleDimensionMessage.IS_SHOW_DIMENSION && 
		    	isShowDimension!=TestScaleDimensionMessage.IS_NOT_SHOW_DIMENSION)){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        int updateNum = service.updateMessage(testScaleDimensionMessage);
	        
	       	if(updateNum==0){
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
    	isSuccess = true;
    	result.put("isSuccess", isSuccess);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    @RequestMapping(value="/getMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageById(HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
    	
    	try{
    		
    		Integer dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
		    if(dimensionId==null || dimensionId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    tsdMessage = service.getMessageById(dimensionId);
	    	
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("testScaleDimensionMessage", tsdMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    
    @RequestMapping(value="/getAllDimensionByTestId",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllDimensionByTestId(TestScaleDimensionMessage tsdMessage){
        
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleDimensionMessage> list = new ArrayList<TestScaleDimensionMessage>();
    	int count = 0;
    	try{
    	
    		Integer testScaleId =tsdMessage.getRelateTestScaleId();
		    if(testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
	        list = service.getAllDimensionByTestId(tsdMessage);
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
    
    
    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TestScaleDimensionMessage tsdMessage){
        
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleDimensionMessage> list = new ArrayList<TestScaleDimensionMessage>();
    	int count = 0;
    	try{
    	
    		Integer testScaleId =tsdMessage.getRelateTestScaleId();
		    if(testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
	        list = service.getAllMessage(tsdMessage);
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
    public Object addMessage(@RequestBody TestScaleDimensionMessage testScaleDimensionMessage, HttpServletRequest request){
         
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	       
    		Integer relateTestScaleId = testScaleDimensionMessage.getRelateTestScaleId();
	        String name = testScaleDimensionMessage.getName();
	        
		    if(relateTestScaleId==null || relateTestScaleId==0 || name==null || name.isEmpty()){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
			
		    //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(relateTestScaleId,result)){
				return result;
	        }
	     	
		    Boolean isSuccess =service.addMessage(testScaleDimensionMessage);
	        
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
    	result.put("testScaleDimensionId", testScaleDimensionMessage.getId());
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(@RequestBody TestScaleDimensionMessage testScaleDimensionMessage,HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	
    	try{
	        
	        Integer id = testScaleDimensionMessage.getId();
	        String name = testScaleDimensionMessage.getName();
	        if(id==null || id==0 || name==null || name.isEmpty()){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }

			TestScaleDimensionMessage tsdMessage = service.getMessageById(id);
			if(tsdMessage == null){
				result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
			}
			Integer testScaleId = tsdMessage.getRelateTestScaleId();
			
		    //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(testScaleId,result)){
				return result;
	        }
	        
	    	
	        isSuccess = service.updateMess(testScaleDimensionMessage,testScaleId);
	       	if(!isSuccess){
	    		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	    	}

    	}catch(Exception e){
			log.error(e.getMessage(), e);
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
    public Object deleteMessage(TestScaleDimensionMessage testScaleDimensionMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	        
	        Integer id = testScaleDimensionMessage.getId();
	        Integer testScaleId = testScaleDimensionMessage.getRelateTestScaleId();
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
	        
	        result = service.deleteMess(testScaleDimensionMessage);
	      
        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
		return result;
    }


}

