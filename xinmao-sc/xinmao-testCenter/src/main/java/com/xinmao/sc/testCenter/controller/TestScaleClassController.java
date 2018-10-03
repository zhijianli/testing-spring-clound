package com.xinmao.sc.testCenter.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
import com.xinmao.sc.testCenter.domain.TestScaleClassRelationMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.service.TestScaleClassRelationService;
import com.xinmao.sc.testCenter.service.TestScaleClassService;
import com.xinmao.sc.testCenter.service.TestScaleInfoService;


@RestController
@RequestMapping("testScaleClass")
public class TestScaleClassController {
	
//	Logger log = Logger.getLogger(this.getClass());
	
	private static final Logger log = LogManager.getLogger();

    @Autowired
    private TestScaleClassService service;
    
    @Autowired
    private TestScaleInfoService testScaleInfoService;

    @Autowired
    private TestScaleClassRelationService testScaleClassRelationService;
    
    
    @RequestMapping(value = "/setHomeRecomm",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object setHomeRecomm(TestScaleClassMessage testScaleClassMessage ,HttpServletRequest request){

    	
    	ResultEntity result = new ResultEntity(); 
    	int updateNum = 0;
    	
    	try{
    		
    		Integer id = testScaleClassMessage.getId();
    		Integer homeRecomm = testScaleClassMessage.getHomeRecomm();
		    
		    if(id==null || id==0 || homeRecomm==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
	        Date currentTime = new Date();
	    	testScaleClassMessage.setUpdateTime(currentTime);
	    	
	    	updateNum = service.updateMess(testScaleClassMessage);
	    	
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
    	
    	result.put("isSuccess",true);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    
    
    @RequestMapping(value="/getMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageById(HttpServletRequest request){

    	
    	ResultEntity result = new ResultEntity(); 
    	TestScaleClassMessage testScaleClassMessage = new TestScaleClassMessage();
    	
    	try{
		 
	        Integer id = Integer.parseInt(request.getParameter("id"));
		    if(id==null || id==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        testScaleClassMessage = service.getMessageById(id);
    	
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("testScaleClassMessage", testScaleClassMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }

    
    @RequestMapping(value="/getAllMessageFromFront",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessageFromFront(HttpServletResponse response){
    	
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleClassMessage> list = new ArrayList<TestScaleClassMessage>();
    	int count = 0;
    	try{
             
	    	//获取量表分类列表信息
    		TestScaleClassMessage tscMessage = new TestScaleClassMessage();
    		tscMessage.setHomeRecomm(TestScaleClassMessage.IS_HOME_RECOMM);
	        list = service.getHomeRecommMessage(tscMessage);
	        if(list!=null&&list.size()>0){
	        	count = list.size();
	        }
        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleClassList", list);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;

        
    }
    
    
    @RequestMapping(value="/getAllTestScaleClass",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllTestScaleClass(){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleClassMessage> list = new ArrayList<TestScaleClassMessage>();
    	int count = 0;
    	try{
        
	    	//获取量表分类列表信息
	        list = service.getAllMessage();
	        
	        if(list!=null&&list.size()>0){
		        count = list.size();
	        }
        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleClassList", list);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;

        
    }
    
    
    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleClassMessage> list = new ArrayList<TestScaleClassMessage>();
    	int count = 0;
    	try{
        
	    	//获取量表分类列表信息
	        list = service.getAllMessage();
	        
	        if(list!=null&&list.size()>0){
	        
		        //获取该分类下量表数量与测试人数
		        for(int i =0;i<list.size();i++){
		        	 int testScaleNum = 0;
		        	 int numberOfTest = 0 ; 

		        	 TestScaleClassMessage testScaleClassMessage = list.get(i);
		        	 
		        	 //查询分类下的量表数量
		        	 TestScaleClassRelationMessage  relationMessage = new TestScaleClassRelationMessage();
		        	 relationMessage.setTestScaleClassId(testScaleClassMessage.getId());
		        	 List<TestScaleClassRelationMessage> relateList = testScaleClassRelationService.getAllMessage(relationMessage);

 		        	 //查询分类下的量表对应的测试人数之和
		        	 if(relateList!=null&&relateList.size()>0){
		        		 
			        	 testScaleNum = relateList.size();
			        	 
			        	 List<Integer> intList  = new ArrayList<Integer>();  
			        	 for(int j =0;j<testScaleNum;j++){
			        		 intList.add(relateList.get(j).getTestScaleId());
			        	 }
			        	 TestScaleInfoMessage testScaleInfoMessage = new TestScaleInfoMessage();
			        	 testScaleInfoMessage.setSearchList(intList);
			        	 List<TestScaleInfoMessage> scaleInfoList = testScaleInfoService.getAllMessageByIdList(testScaleInfoMessage);
		
			        	 for(int k =0;k<scaleInfoList.size();k++){
			        		 numberOfTest = numberOfTest+scaleInfoList.get(k).getNumberOfTest();
			        	 }
			         }
		        	 testScaleClassMessage.setTestScaleNum(testScaleNum);
		        	 testScaleClassMessage.setNumberOfTest(numberOfTest); 
		        }
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
    
    @RequestMapping(value = "add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(TestScaleClassMessage testScaleClassMessage , HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	try{
    		    String name = testScaleClassMessage.getName();
    		    String relatePicSrc = testScaleClassMessage.getRelatePicSrc();
    		    if(name==null || name.isEmpty() || relatePicSrc==null || relatePicSrc.isEmpty()){
    		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
    				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
    				return result;
    		    }
    		   
    		    Date currentTime = new Date();
    	     	testScaleClassMessage.setIsDelete(0);
    	     	testScaleClassMessage.setIsEnable(0);
    	     	testScaleClassMessage.setHomeRecomm(0);
    	     	testScaleClassMessage.setCreateTime(currentTime);
    	     	testScaleClassMessage.setUpdateTime(currentTime);
    	     	
    	        service.addMessage(testScaleClassMessage);
    	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleClassId", testScaleClassMessage.getId());
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(TestScaleClassMessage testScaleClassMessage ,HttpServletRequest request){

    	
    	ResultEntity result = new ResultEntity(); 
    	int updateNum = 0;
    	
    	try{
    		Integer id = testScaleClassMessage.getId();
    	    String name = testScaleClassMessage.getName();
		    String relatePicSrc = testScaleClassMessage.getRelatePicSrc();
		    
		    if(id==null || id==0 || name==null || name.isEmpty() || relatePicSrc==null || relatePicSrc.isEmpty()){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
	        Date currentTime = new Date();
	    	testScaleClassMessage.setUpdateTime(currentTime);
	    	
	    	updateNum = service.updateMess(testScaleClassMessage);
	    	
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
    	result.put("isSuccess",true);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    @RequestMapping(value = "/delete",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object deleteMessage(TestScaleClassMessage testScaleClassMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	        
	        Integer id = testScaleClassMessage.getId();
	        if(id==null || id==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        
	        result = service.deleteMess(testScaleClassMessage);
	      
        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
		return result;
    }
    
}

