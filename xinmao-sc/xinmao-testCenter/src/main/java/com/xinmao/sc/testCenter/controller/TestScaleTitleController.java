package com.xinmao.sc.testCenter.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.xinmao.sc.testCenter.api.TestScaleTitleApi;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleTitleMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderDimensionNormMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;
import com.xinmao.sc.testCenter.service.TestScaleInfoService;
import com.xinmao.sc.testCenter.service.TestScaleOrderTitleService;
import com.xinmao.sc.testCenter.service.TestScaleTitleService;


@RestController
@RequestMapping("testScaleTitle")
public class TestScaleTitleController{

//	Logger log = Logger.getLogger(this.getClass());
	
	private static final Logger log = LogManager.getLogger();
	
    @Autowired
    private TestScaleTitleService service;
    
    @Autowired
    private TestScaleInfoService tsiService;
    
    @Autowired
    private TestScaleOrderTitleService tsotService;
    

    @RequestMapping(value="/getAllTitleAndOption",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllTitleAndOption(HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleTitleMessage> tstList = new ArrayList<TestScaleTitleMessage>();
    	Integer isNewResultPage =0;
    	try{
    		
	        Integer testScaleId =Integer.parseInt(request.getParameter("testScaleId"));
		    if(testScaleId==null || testScaleId==0){
				   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
						result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
						return result;
			}
		    
		    //判断量表是否为空
		    TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
		    tsiMessage.setId(testScaleId);
		    tsiMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
		    TestScaleInfoMessage tsiMess = tsiService.getMessByIdFromFront(tsiMessage);
		    if(tsiMess==null){
				result.setCode(ErrorCode.ERROR_TESTSCALE_NOT_ONLINE.getCode());
				result.setMsg(ErrorCode.ERROR_TESTSCALE_NOT_ONLINE.getMessage());
				return result;
		    }
		    isNewResultPage = tsiMess.getIsNewResultPage();
	        
       		//获取该量表下所有题目与选项信息
       		TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
       		tstMessage.setRelateTestScaleId(testScaleId);
       		tstList = service.getAllTitleAndOption(tstMessage);
       		if(tstList==null||tstList.size()==0){
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
    	
    	result.put("isNewResultPage", isNewResultPage);
    	result.put("testScaleTitleList", tstList);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    @RequestMapping(value="/getTitleAndOptionAlready",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public ResultEntity getTitleAndOptionAlready(TestScaleOrderTitleMessage tsotMessage) {
        
    	ResultEntity result = new ResultEntity(); 
    	int count = 0;
    	TestScaleOrderMessage tsoMessage = new TestScaleOrderMessage();
    	List<TestScaleOrderTitleMessage> chooseList = new ArrayList<TestScaleOrderTitleMessage>();
    	
    	try{
    		Integer testScaleOrderId = tsotMessage.getTestScaleOrderId();
    		Integer pageIndex = tsotMessage.getPageIndex();
    		Integer pageSize = tsotMessage.getPageSize();
		    if(testScaleOrderId==null || testScaleOrderId==0|| 
		    		pageIndex==null || pageIndex==0 || 
		    		pageSize==null || pageSize==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
//		    //判断量表是否为空
//		    TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
//		    tsiMessage.setId(testScaleId);
//		    TestScaleInfoMessage tsiMess = tsiService.getMessageById(testScaleId);
//		    if(tsiMess==null){
//		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//				return result;
//		    }
		    
		    //获得选中的题目和选项
		    tsoMessage = tsotService.getChooseTitleAndOption(tsotMessage);
		    if(tsoMessage == null){
		   		result.setCode(ErrorCode.ERROR_TITLE_AND_OPTION_IS_NULL.getCode());
			    result.setMsg(ErrorCode.ERROR_TITLE_AND_OPTION_IS_NULL.getMessage());
				return result;
		    }
		 
//		    Integer testScaleId = tsoMessage.getRelateTestScaleId();
		    chooseList = tsoMessage.getTsotList();
		    
		    //获取用户选择的选项对应的量表题目与所有选项
		    List<Integer> searchList = new ArrayList<Integer>();
		    if(chooseList!=null && chooseList.size()>0){
		    	for(int i =0;i<chooseList.size();i++){
		    		TestScaleOrderTitleMessage tsotMess = chooseList.get(i);
		    		searchList.add(tsotMess.getTestScaleTitleId());
		    	}
		    }
			TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
			tstMessage.setSearchList(searchList);
			List<TestScaleTitleMessage> tstList = service.selectAllBySearchIdList(tstMessage);
			
			
			//用户选择的选项与其他选项拼装
			Map<Integer,TestScaleTitleMessage> titleMap = new HashMap<Integer,TestScaleTitleMessage>();
			if(tstList!=null && tstList.size()>0){
				for(int i=0;i<tstList.size();i++){
					TestScaleTitleMessage tstMess = tstList.get(i);
					Integer titleId = tstMess.getId();
					titleMap.put(titleId,tstMess);
				}
			}
			if(chooseList!=null && chooseList.size()>0){
				for(int i =0;i<chooseList.size();i++){
					List<TestScaleOptionMessage> optionList = new ArrayList<TestScaleOptionMessage>();
					TestScaleOrderTitleMessage chooseTsotMess = chooseList.get(i);
					Integer titleId = chooseTsotMess.getTestScaleTitleId();
					Integer optionId = chooseTsotMess.getOptionId();
					TestScaleTitleMessage tstMess = titleMap.get(titleId);
					
					if(tstMess!=null){
						List<TestScaleOptionMessage> tsoList = tstMess.getTsoList();
						for(int j=0;j<tsoList.size();j++){
							TestScaleOptionMessage tsoMess = tsoList.get(j);
							Integer testScaleOptionId = tsoMess.getId();
							if(testScaleOptionId==optionId){
								tsoMess.setOptionWord(chooseTsotMess.getOptionWord()); 
								tsoMess.setOptionPic(chooseTsotMess.getOptionPicSrc());
								tsoMess.setOptionScore(chooseTsotMess.getOptionScore());
							}
							optionList.add(tsoMess);					
						}
					}else{
						TestScaleOptionMessage tsoMess = new TestScaleOptionMessage();
						tsoMess.setId(optionId);
						tsoMess.setOptionWord(chooseTsotMess.getOptionWord()); 
						tsoMess.setOptionPic(chooseTsotMess.getOptionPicSrc());
						tsoMess.setOptionScore(chooseTsotMess.getOptionScore());
						optionList.add(tsoMess);
					}
		
					chooseTsotMess.setOptionList(optionList);
					
					chooseTsotMess.setTitleOrderNumber((pageIndex-1)*pageSize+i+1);
				}
				
				count = chooseList.size();
			}
		
    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("count", count);
//    	result.put("testScaleTitleNum", tsoMessage.getTestScaleTitleNum());
//    	result.put("chooseList", chooseList);
//    	result.put("tsodnList", tsoMessage.getTsodnList());
    	result.put("tsoMessage",tsoMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
	}
    
    @RequestMapping(value="/getAllTitleAndOptionFromEAP",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllTitleAndOptionFromEAP(TestScaleTitleMessage testScaleTitleMessage){

    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleTitleMessage> tstList = new ArrayList<TestScaleTitleMessage>();
    	int count = 0;
    	int testScaleTitleNum = 0;
    	try{
		    
    		Integer pageIndex = testScaleTitleMessage.getPageIndex();
    		Integer pageSize = testScaleTitleMessage.getPageSize();
    		Integer testScaleId = testScaleTitleMessage.getRelateTestScaleId();
		    if(pageIndex==null || pageIndex==0 || 
		       pageSize==null || pageSize==0|| 
		    		   testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    //判断量表是否为空
		    TestScaleInfoMessage tsiMess = tsiService.getMessageById(testScaleId);
		    if(tsiMess==null){
				result.setCode(ErrorCode.ERROR_TESTSCALE_IS_NULL.getCode());
				result.setMsg(ErrorCode.ERROR_TESTSCALE_IS_NULL.getMessage());
				return result;
		    }
	        
       		//获取该量表下所有题目与选项信息
//       	TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
//       	tstMessage.setRelateTestScaleId(testScaleId);
		    
	    	PageHelper.startPage(pageIndex, pageSize);
       		tstList = service.getAllTitleAndOptionFromEAP(testScaleTitleMessage);
       		
    		if(tstList!=null&&tstList.size()>0){
    			count = tstList.size();
    			
    			//获取该量表下所有题目数量
    			TestScaleTitleMessage tstMess = new TestScaleTitleMessage();
    			tstMess.setRelateTestScaleId(testScaleId);
    			testScaleTitleNum = service.selectCount(tstMess);
    		}
    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("count", count);
    	result.put("testScaleTitleList", tstList);
    	result.put("testScaleTitleNum", testScaleTitleNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    
    
//    public Object getAllTitleAndOption(Integer testScaleId){
//
//    	ResultEntity result = new ResultEntity(); 
//    	List<TestScaleTitleMessage> tstList = new ArrayList<TestScaleTitleMessage>();
//    	
//    	try{
//    		
//		    if(testScaleId==null || testScaleId==0){
//				   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//						result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//						return result;
//			}
//		    
//		    //判断量表是否为空
//		    TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
//		    tsiMessage.setId(testScaleId);
//		    tsiMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
//		    TestScaleInfoMessage tsiMess = tsiService.getMessByIdFromFront(tsiMessage);
//		    if(tsiMess==null){
//				result.setCode(ErrorCode.ERROR_TESTSCALE_NOT_ONLINE.getCode());
//				result.setMsg(ErrorCode.ERROR_TESTSCALE_NOT_ONLINE.getMessage());
//				return result;
//		    }
//		 
//	        
//       		//获取该量表下所有题目与选项信息
//       		TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
//       		tstMessage.setRelateTestScaleId(testScaleId);
//       		tstList = service.getAllTitleAndOption(tstMessage);
//       		if(tstList==null||tstList.size()==0){
//       			result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//    			result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//    			return result;
//       		}
//    	
//    	}catch(Exception e){
//    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
//			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//			result.setMsg(e.getMessage());
//			return result;
//    	}
//    	
//    	result.put("testScaleTitleList", tstList);
//		result.setCode(ErrorCode.SUCCESS.getCode());
//		result.setMsg(ErrorCode.SUCCESS.getMessage());
//		return result;
//        
//    }
    
    
    @RequestMapping(value="/getMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageById(HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	TestScaleTitleMessage testScaleTitleMessage = new TestScaleTitleMessage();
    	
    	try{
    		
	        int id =Integer.parseInt(request.getParameter("id"));
	        testScaleTitleMessage = service.getMessageById(id);
	        
	    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("testScaleTitleMessage", testScaleTitleMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TestScaleTitleMessage testScaleTitleMessage){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleTitleMessage> list = new ArrayList<TestScaleTitleMessage>();
    	int count = 0;
    	int testScaleTitleNum = 0;
    	try{
    		
    		Integer pageIndex = testScaleTitleMessage.getPageIndex();
    		Integer pageSize = testScaleTitleMessage.getPageSize();
    		Integer relateTestScaleId = testScaleTitleMessage.getRelateTestScaleId();
		    if(pageIndex==null || pageIndex==0 || 
		       pageSize==null || pageSize==0|| 
		       relateTestScaleId==null || relateTestScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	    	PageHelper.startPage(pageIndex, pageSize);
		    
    		list = service.searchMessage(testScaleTitleMessage);
    		if(list!=null&&list.size()>0){
    			count = list.size();
    			
    			TestScaleTitleMessage tstMess = new TestScaleTitleMessage();
    			tstMess.setRelateTestScaleId(relateTestScaleId);
    			String problemWord = testScaleTitleMessage.getProblemWord();
    			if(problemWord!=null && !problemWord.isEmpty()){
    				tstMess.setProblemWord(problemWord);
    			}

    			testScaleTitleNum = service.selectCount(tstMess);
    		}
        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("contentList", list);
    	result.put("count", count);
    	result.put("testScaleTitleNum", testScaleTitleNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(@RequestBody TestScaleTitleMessage testScaleTitleMessage, HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	try{
	        
	        Integer relateTestScaleId = testScaleTitleMessage.getRelateTestScaleId();
	        
	        if(relateTestScaleId==null || relateTestScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        
		    //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(relateTestScaleId,result)){
				return result;
	        }
	        
	        Boolean isSuccess = service.addMessage(testScaleTitleMessage);
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
    	result.put("testScaleTitleId", testScaleTitleMessage.getId());
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(@RequestBody TestScaleTitleMessage testScaleTitleMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	
    	try{
	        
	        Integer id = testScaleTitleMessage.getId();
	        Integer optionType = testScaleTitleMessage.getOptionType();
	        if(id==null || id==0 || optionType==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        
	        TestScaleTitleMessage tstMess = service.getMessById(id);
	        
		    //判断量表是否已上线，已上线不允许修改
	        if(tsiService.isOnline(tstMess.getRelateTestScaleId(),result)){
				return result;
	        }
	        
	        isSuccess = service.updateMess(testScaleTitleMessage);
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
    public Object deleteMessage(TestScaleTitleMessage testScaleTitleMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	        
	        Integer id = testScaleTitleMessage.getId();
	        Integer testScaleId = testScaleTitleMessage.getRelateTestScaleId();
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
	        
	        result = service.deleteMess(testScaleTitleMessage);
	      
        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
		return result;
    }



    
}

