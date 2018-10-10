package com.xinmao.sc.testCenter.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
import com.xinmao.sc.testCenter.domain.TestScaleClassRelationMessage;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleNormMessage;
import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;
import com.xinmao.sc.testCenter.domain.TestScalePopulationRangeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleQualitativeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleTitleMessage;
import com.xinmao.common.userOperationCenter.domain.CommonCollection;
import com.xinmao.common.userOperationCenter.domain.CommonComment;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.testCenter.service.CommonCollectionService;
import com.xinmao.sc.testCenter.service.CommonCommentService;
import com.xinmao.sc.testCenter.service.TestScaleClassRelationService;
import com.xinmao.sc.testCenter.service.TestScaleClassService;
import com.xinmao.sc.testCenter.service.TestScaleDimensionService;
import com.xinmao.sc.testCenter.service.TestScaleInfoService;
import com.xinmao.sc.testCenter.service.TestScaleNormService;
import com.xinmao.sc.testCenter.service.TestScaleOrderService;
import com.xinmao.sc.testCenter.service.TestScalePopulationRangeService;
import com.xinmao.sc.testCenter.service.TestScaleQualitativeService;
import com.xinmao.sc.testCenter.service.TestScaleTitleService;
import com.xinmao.sc.testCenter.api.TestScaleInfoApi;


@RestController
//@RequestMapping("testCenter/testScaleInfo")
public class TestScaleInfoController implements TestScaleInfoApi{

//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
	
    @Autowired
    private TestScaleOrderService tsoService;
	
    @Autowired
    private TestScaleInfoService service;
    
    @Autowired
    private TestScaleTitleService testScaleTitleService;
    
    @Autowired
    private TestScaleClassRelationService testScaleClassRelationService;
    
    @Autowired
    private TestScaleClassService testScaleClassService;
    
    @Autowired
    private TestScalePopulationRangeService testScalePopulationRangeService;
    
    @Autowired
    private TestScaleDimensionService tsdService;
    
    @Autowired
    private TestScaleQualitativeService tsqService;
    
    @Autowired
    private TestScaleNormService tsnService;
    
    @Autowired
    private CommonCollectionService commonCollectionService;
    
    @Autowired
    private CommonCommentService commonCommentService;

    
    @RequestMapping(value = "/setIsShowDimensionProfile",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object setIsShowDimensionProfile(TestScaleInfoMessage testScaleInfoMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	
    	try{
	        
	        Integer id = testScaleInfoMessage.getId();
	        Integer isShowDimensionProfile = testScaleInfoMessage.getIsShowDimensionProfile();
		    if(id==null || id==0 || isShowDimensionProfile==null || 
		       (isShowDimensionProfile!=TestScaleInfoMessage.IS_SHOW_DIMENSION_PROFILE && 
		        isShowDimensionProfile!=TestScaleInfoMessage.IS_NOT_SHOW_DIMENSION_PROFILE)){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        int updateNum = service.updateMessage(testScaleInfoMessage);
	        
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
    
    @RequestMapping(value = "/setTop",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object setTop(TestScaleInfoMessage testScaleInfoMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	
    	try{
	        
	        Integer id = testScaleInfoMessage.getId();
	        Integer isTop = testScaleInfoMessage.getIsTop();
		    if(id==null || id==0 || isTop==null || 
		       (isTop!=TestScaleInfoMessage.IS_TOP && isTop!=TestScaleInfoMessage.IS_NOT_TOP)){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        int updateNum = service.updateMessage(testScaleInfoMessage);
	        
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
    
    @RequestMapping(value = "/setOnLine",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object setOnLine(TestScaleInfoMessage testScaleInfoMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	
    	try{
	        
	        Integer id = testScaleInfoMessage.getId();
	        Integer isOnLine = testScaleInfoMessage.getIsEnable();
		    if(id==null || id==0 || isOnLine==null ||
		    		(isOnLine!=TestScaleInfoMessage.IS_ONLINE && isOnLine!=TestScaleInfoMessage.IS_NOT_ONLINE)){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    //所有的量表都要统一建题目，否则不让上线
		    TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
		    tstMessage.setRelateTestScaleId(id);
		    List<TestScaleTitleMessage>  tstList = testScaleTitleService.getAllTitleAndOption(tstMessage);
		    if(tstList==null || tstList.size()==0){
		   		result.setCode(ErrorCode.ERROR_TITLE_NOT_CREATE.getCode());
			    result.setMsg(ErrorCode.ERROR_TITLE_NOT_CREATE.getMessage());
				return result;
		    }else{
		    	//所有的量表都要统一建题目的选项，否则不让上线
		    	for(int i = 0;i<tstList.size();i++){
		    		TestScaleTitleMessage tstMess = tstList.get(i);
		    		List<TestScaleOptionMessage> tsoList = tstMess.getTsoList();
		    		if(tsoList==null || tsoList.size()==0){
				   		result.setCode(ErrorCode.ERROR_OPTION_RANGE_NOT_CREATE.getCode());
					    result.setMsg(ErrorCode.ERROR_OPTION_RANGE_NOT_CREATE.getMessage());
						return result;
				    }
		    	}
		    }
		    
		    //所有的量表都要统一建人群范围,否则不让上线
		    TestScalePopulationRangeMessage tsprMessage = new TestScalePopulationRangeMessage();
		    tsprMessage.setRelateTestScaleId(id);
		    List<TestScalePopulationRangeMessage> tsprList = testScalePopulationRangeService.getAllMessage(tsprMessage);
		    if(tsprList==null || tsprList.size()==0){
		   		result.setCode(ErrorCode.ERROR_POPULATION_RANGE_NOT_CREATE.getCode());
			    result.setMsg(ErrorCode.ERROR_POPULATION_RANGE_NOT_CREATE.getMessage());
				return result;
		    }
		    
		    //所有的量表都要统一建维度，否则不让上线
		    TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
		    tsdMessage.setRelateTestScaleId(id);
		    List<TestScaleDimensionMessage> tsdList = tsdService.getAllDimensionByTestId(tsdMessage);
		    if(tsdList==null || tsdList.size()==0){
		   		result.setCode(ErrorCode.ERROR_DIMENSION_NOT_CREATE.getCode());
			    result.setMsg(ErrorCode.ERROR_DIMENSION_NOT_CREATE.getMessage());
				return result;
		    }
		    
		    //所有的量表都要统一建常模，否则不让上线
		    TestScaleNormMessage tsnMessage = new TestScaleNormMessage();
		    tsnMessage.setRelateTestScaleId(id);
		    List<TestScaleNormMessage>  tsnList = tsnService.getAllMessage(tsnMessage);
		    if(tsnList==null || tsnList.size()==0){
		   		result.setCode(ErrorCode.ERROR_NORM_NOT_CREATE.getCode());
			    result.setMsg(ErrorCode.ERROR_NORM_NOT_CREATE.getMessage());
				return result;
		    }
		    
		    //所有的量表都要统一建定性，否则不让上线
		    TestScaleQualitativeMessage tsqMessage = new TestScaleQualitativeMessage();
		    tsqMessage.setRelateTestScaleId(id);
		    List<TestScaleQualitativeMessage>  tsqList = tsqService.getAllMessage(tsqMessage);
		    if(tsqList==null || tsqList.size()==0){
		   		result.setCode(ErrorCode.ERROR_QUALITATIVE_NOT_CREATE.getCode());
			    result.setMsg(ErrorCode.ERROR_QUALITATIVE_NOT_CREATE.getMessage());
				return result;
		    }else{
		    	//所有的量表都要统一建定性成立条件，否则不让上线
		    	for(int i=0;i<tsqList.size();i++){
		    		TestScaleQualitativeMessage tsqMess = tsqList.get(i);
		    		List<String> coqStrList = tsqMess.getCoqStrList();
				    if(coqStrList==null || coqStrList.size()==0){
				   		result.setCode(ErrorCode.ERROR_CONDITIONS_OF_QUALITATIVE_CREATE.getCode());
					    result.setMsg(ErrorCode.ERROR_CONDITIONS_OF_QUALITATIVE_CREATE.getMessage());
						return result;
				    }
		    	}
		    }
		    
	        int updateNum = service.updateMessage(testScaleInfoMessage);
	        
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
    
    
    @Override
	public ResultEntity addNumberOfTest(@RequestBody TestScaleInfoMessage tsInfoMess) {
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	try{
	        Integer testScaleId =tsInfoMess.getId();
		    if(testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    isSuccess = service.addNumberOfTest(tsInfoMess);
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

    @RequestMapping(value="/getCollectionTestList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getCollectionTestList(CommonCollection commonCollection,HttpServletResponse response){
    	
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleInfoMessage> collectionTestList = new  ArrayList<TestScaleInfoMessage>();

    	try{
            
	        Long mid = commonCollection.getMid();
    		Integer pageIndex = commonCollection.getPageIndex();
    		Integer pageSize = commonCollection.getPageSize();
//    		Long mid =Long.parseLong(request.getParameter("mid"));
//    		Integer pageIndex =Integer.parseInt(request.getParameter("pageIndex"));
//    		Integer pageSize =Integer.parseInt(request.getParameter("pageSize"));
		    if(mid==null || mid==0|| pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    //获取收藏的测试量表ID列表
//		    CommonCollection commonCollection = new CommonCollection();
//		    commonCollection.setMid(mid);
//		    commonCollection.setPageIndex(pageIndex);
//		    commonCollection.setPageSize(pageSize);
		    List<CommonCollection> collectionList = commonCollectionService.getCollectionListByUserId(commonCollection);
    		
		    if(collectionList==null || collectionList.size()==0){
//		    	log.error(ErrorCode.COLLECTION_IS_NULL.getMessage());
//		   		result.setCode(ErrorCode.COLLECTION_IS_NULL.getCode());
//				result.setMsg(ErrorCode.COLLECTION_IS_NULL.getMessage());
				result.setCode(ErrorCode.SUCCESS.getCode());
				result.setMsg(ErrorCode.SUCCESS.getMessage());
		    	result.put("collectionTestList",collectionTestList);
				return result;
		    }
    		
    		//根据测试量表id列表获取量表id
		    TestScaleInfoMessage tsiList = new TestScaleInfoMessage();
		    List<Integer> searchList = new ArrayList<Integer>();
		    for(int i=0;i<collectionList.size();i++){
		    	searchList.add(Integer.valueOf(collectionList.get(i).getArticleId()+""));
		    }
		    tsiList.setSearchList(searchList);
		    tsiList.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
		    List<TestScaleInfoMessage> testList  = service.getAllMessageByIdList(tsiList);
		    
		    //收藏列表排序
		    for(int i=0;i<searchList.size();i++){
		    	Integer articleId = searchList.get(i);
		    	for(int j=0;j<testList.size();j++){
		    		TestScaleInfoMessage testMess = testList.get(j);
		    		Integer testId = testMess.getId();
		    		if(articleId.equals(testId)){
		    			collectionTestList.add(testMess);
		    		}
		    	}
		    }
		    
	        if(collectionTestList!=null && collectionTestList.size()>0){
	            for(int i =0;i<collectionTestList.size();i++){
	        		TestScaleInfoMessage tsMessage = collectionTestList.get(i);
	        		
	        		//获取量表下面题目数
	        		getNumberOfTitle(tsMessage);
	        		
	        		//获取量表的评论数
	        		getNumberOfComment(tsMessage);
		        }
	        }
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("collectionTestList",collectionTestList);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }

	private void getNumberOfComment(TestScaleInfoMessage tsMessage) {
		CommonComment commonComment =new CommonComment();
		commonComment.setArticleId(tsMessage.getId().longValue());
		Integer numberOfComment = commonCommentService.getTestScaleCommentNum(commonComment);
		tsMessage.setNumberOfComment(numberOfComment);
	}
    
    
    //获取量表分类列表
    @RequestMapping(value="/getSelectionTestList",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Object getSelectionTestList(){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleInfoMessage> selectionTestList  =new  ArrayList<TestScaleInfoMessage>();

    	try{
            
	        TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
	        tsiMessage.setIsTop(TestScaleInfoMessage.IS_TOP);
	        tsiMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
	        selectionTestList = service.getAllHomePageMessage(tsiMessage);
	        if(selectionTestList!=null && selectionTestList.size()>0){
	            for(int i =0;i<selectionTestList.size();i++){
	        		TestScaleInfoMessage tsMessage = selectionTestList.get(i);
	        		
	        		//获取量表下面题目数
	        		getNumberOfTitle(tsMessage);
		        }
	        }
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("selectionTestList", selectionTestList);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
	@Override
	public TestScaleInfoMessage getTestScaleAllMessageById(@RequestBody TestScaleOrderMessage tsoMessage) {
		TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
    	
    	try{
    	
	        Integer testScaleId = tsoMessage.getRelateTestScaleId();
		    if(testScaleId==null || testScaleId==0){
		    	log.error(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return null;
		    }
		    
		    tsiMessage = service.getMessageById(testScaleId);
		    
	        //判断相关量表是否存在
	        if(tsiMessage == null){
	        	return null;
	        }
	        
	        //获取题目与选项信息
	        Map<Integer,TestScaleTitleMessage> titleMap = testScaleTitleService.getTitleAndOptionInfo(tsoMessage);
	        tsiMessage.setTitleMap(titleMap);
		    
	        //获取相关维度常模信息
		    List<TestScaleDimensionMessage> tsdList = tsdService.getMessageByTestScaleId(tsoMessage);
		    tsiMessage.setTsdList(tsdList);
		    
		    //获取定性信息
		    List<TestScaleQualitativeMessage> tsqList = tsqService.getMessageByTestScaleId(testScaleId);
		    tsiMessage.setTsqList(tsqList);
		    
		    
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
    	
		return tsiMessage;
		
	}
	
	
	@Override
	public TestScaleInfoMessage getTestScaleAllMessage(@RequestBody TestScaleOrderMessage tsoMessage) {
		TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
    	
    	try{
    	
	        Integer testScaleId = tsoMessage.getRelateTestScaleId();
	        Integer qualitativeId = tsoMessage.getRelateQualitativeId();
		    if(testScaleId==null || testScaleId==0){
		    	log.error(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return null;
		    }
		    
		    tsiMessage = service.getMessageById(testScaleId);
		    
	        //判断相关量表是否存在
	        if(tsiMessage == null){
	        	return null;
	        }
	        
	        //获取相关维度信息
		    List<TestScaleDimensionMessage> tsdList = tsdService.selectAll(testScaleId);
		    tsiMessage.setTsdList(tsdList);
		    
		    //获取定性信息
		    TestScaleQualitativeMessage tsq = tsqService.getMessById(qualitativeId);
		    List<TestScaleQualitativeMessage> tsqList = new ArrayList<TestScaleQualitativeMessage>();
		    tsqList.add(tsq);
		    tsiMessage.setTsqList(tsqList);
		    
		    
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
    	
		return tsiMessage;
		
	}

    
    @RequestMapping(value="/getRecommendTestScale",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getRecommendTestScale(HttpServletRequest request,HttpServletResponse response){

    	response.addHeader("Access-Control-Allow-Origin", "*");
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleInfoMessage> recommendTestScaleList = new ArrayList<TestScaleInfoMessage>();
    	int count = 0;
    	try{
	        Integer testScaleOrderId =Integer.parseInt(request.getParameter("testScaleOrderId"));
		    if(testScaleOrderId==null || testScaleOrderId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
	        //根据订单id获取量表id
		    TestScaleOrderMessage tsoMessage = new TestScaleOrderMessage();
		    tsoMessage.setId(testScaleOrderId);
		    TestScaleOrderMessage tsoMess = tsoService.getTestScaleOrderById(tsoMessage);
		    
		    if(tsoMess==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    Integer testScaleId = tsoMess.getRelateTestScaleId();
		    
		    //根据量表id获取相关联的分类id集合
		    TestScaleClassRelationMessage tscrMessage = new TestScaleClassRelationMessage();
		    tscrMessage.setTestScaleId(testScaleId);
		    List<Integer> classIdList = testScaleClassRelationService.getAllClassIdList(tscrMessage);
		    
		    
		    //获得这些分类下面的所有量表id
		    if(classIdList!=null && classIdList.size()>0){
		    	
		    	TestScaleClassRelationMessage tscrMess = new TestScaleClassRelationMessage();
		    	tscrMess.setSearchList(classIdList);
		    	List<Integer> tcIdList = testScaleClassRelationService.getTestIdListByClassIdList(tscrMess);
		    	tcIdList.remove(testScaleId);
		    	
		    	if(tcIdList!=null && tcIdList.size()>0){
		    		 //获取热门测试信息
			        TestScaleInfoMessage tsInfoMessage = new TestScaleInfoMessage();
			        tsInfoMessage.setLimit(TestScaleInfoMessage.TEST_RESULT_LIMIT);
			        tsInfoMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
			        tsInfoMessage.setSearchList(tcIdList);
			        recommendTestScaleList = service.getHotMessage(tsInfoMessage);
			        if(recommendTestScaleList!=null && recommendTestScaleList.size()>0){
			        	count = recommendTestScaleList.size();
			        }
		    	}
		    }
    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("recommendTestScaleList",recommendTestScaleList);
    	result.put("count",count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }

	@Override
	public ResultEntity getTestScaleMap(@RequestBody TestScaleInfoMessage tsInfoMess) {
    	ResultEntity result = new ResultEntity(); 
    	Map<Integer,TestScaleInfoMessage> testScaleInfoMap = new HashMap<Integer,TestScaleInfoMessage>();
    	try{
    		
	         if(tsInfoMess==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
			 	return result;
	         }
    		  
			 List<Integer> testScaleIdList = tsInfoMess.getSearchList();
			 if(testScaleIdList!=null && testScaleIdList.size()>0){
	    		 TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
	    		 tsiMessage.setSearchList(testScaleIdList);
	    		 List<TestScaleInfoMessage> testScaleInfoList = service.getAllMessageByIdList(tsiMessage);
	    		 if(testScaleInfoList!=null && testScaleInfoList.size()>0){
	    			 
	        		 for(int i=0;i<testScaleInfoList.size();i++){
	        			 TestScaleInfoMessage tsiMess = testScaleInfoList.get(i);
	        			 int testScaleId = tsiMess.getId();
	        			
	        			 //获取量表下面题目数
	 			    	 getNumberOfTitle(tsiMess);
	        			 testScaleInfoMap.put(testScaleId, tsiMess);
	        		 }
	    		 }
	    		
			 }

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleInfoMap",testScaleInfoMap);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
	}
    
    
    //获取量表分类列表
    @RequestMapping(value="/getHomePageMessage",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Object getHomePageMessage(HttpServletResponse response){
    	
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleClassMessage> homeRecommClassList = new ArrayList<TestScaleClassMessage>();
    	List<TestScaleInfoMessage> selectionTestList  =new  ArrayList<TestScaleInfoMessage>();
    	List<TestScaleInfoMessage> hotTestLIst  =new  ArrayList<TestScaleInfoMessage>();

    	try{
        
    		//获取首页推荐分类信息
    		TestScaleClassMessage tscMessage = new TestScaleClassMessage();
    		tscMessage.setHomeRecomm(TestScaleClassMessage.IS_HOME_RECOMM);
    		homeRecommClassList = testScaleClassService.getHomeRecommMessage(tscMessage);
	        
            //获取推荐测试信息
	        TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
	        tsiMessage.setIsTop(TestScaleInfoMessage.IS_TOP);
	        tsiMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
	        selectionTestList = service.getAllHomePageMessage(tsiMessage);
	        if(selectionTestList!=null && selectionTestList.size()>0){
	            for(int i =0;i<selectionTestList.size();i++){
	        		TestScaleInfoMessage tsMessage = selectionTestList.get(i);
	        		
	        		//获取量表下面题目数
	        		getNumberOfTitle(tsMessage);
		        }
	        }
	
	        
	        //获取热门测试信息
	        TestScaleInfoMessage tsInfoMessage = new TestScaleInfoMessage();
	        tsInfoMessage.setLimit(TestScaleInfoMessage.HOME_PAGE_LIMIT);
	        tsInfoMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
	        List<TestScaleInfoMessage> oriHotTestLIst = service.getHotMessage(tsInfoMessage);
	        
	        //15个量表随机获取10个
	        if(oriHotTestLIst!=null && oriHotTestLIst.size()>0){
		        if(oriHotTestLIst.size()<=TestScaleInfoMessage.HOME_PAGE_HOT_TEST_NUM){
		        	hotTestLIst = oriHotTestLIst;
		        }else{
		        	int oriSize = oriHotTestLIst.size();
		        	List<Integer> randomList  = new  ArrayList<Integer>();
		            while(randomList.size() < TestScaleInfoMessage.HOME_PAGE_HOT_TEST_NUM){
		            	int random = (int)(Math.random()*oriSize);
			        	if(!randomList.contains(random)){
			        		randomList.add(random);
			        	}
			        }
		            for(int i=0;i<randomList.size();i++){
		            	hotTestLIst.add(oriHotTestLIst.get(randomList.get(i)));
		            }
		        }
		        
		        //获取量表下面题目数
		        if(hotTestLIst!=null && hotTestLIst.size()>0){
		        	for(int i=0;i<hotTestLIst.size();i++){
		        		TestScaleInfoMessage tsMessage = hotTestLIst.get(i);
		        		getNumberOfTitle(tsMessage);
		        	}
		        }
	        }
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("homeRecommClassList", homeRecommClassList);
    	result.put("selectionTestList", selectionTestList);
    	result.put("hotTestLIst", hotTestLIst);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;

        
    }
    
    public ResultEntity getTestScaleNum(){
    	ResultEntity result = new ResultEntity(); 
    	int testScaleNum = 0;
    	try{
    		 testScaleNum = service.getTestScaleNum();
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleNum",testScaleNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    	  
    }
    
    @RequestMapping(value="/getMessageByIdFromFront",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageByIdFromFront(HttpServletRequest request,HttpServletResponse response){
		
    	response.addHeader("Access-Control-Allow-Origin", "*");
		ResultEntity result = new ResultEntity(); 
    	TestScaleInfoMessage testScaleInfoMessage = new TestScaleInfoMessage();   
    	Integer isCollected =CommonCollection.IS_NOT_COLLECTED;
    	
    	try{
	        Integer testScaleId =Integer.parseInt(request.getParameter("testScaleId"));
	        if(testScaleId==null || testScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	        }
	        
	        TestScaleInfoMessage tsInfoMessage = new TestScaleInfoMessage();
	        tsInfoMessage.setId(testScaleId);
	        tsInfoMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
	        testScaleInfoMessage = service.getMessByIdFromFront(tsInfoMessage);
	 
	        //判断量表是否存在
	        if(testScaleInfoMessage==null){
	      		result.setCode(ErrorCode.ERROR_TESTSCALE_NOT_ONLINE.getCode());
				result.setMsg(ErrorCode.ERROR_TESTSCALE_NOT_ONLINE.getMessage());
				return result;
	        }
	        
	        //获取量表下面题目数
	        getNumberOfTitle(testScaleInfoMessage);
	        	
        	 //获取量表关联分类Id
    		TestScaleClassRelationMessage testScaleClassRelationMessage = new TestScaleClassRelationMessage();
    		testScaleClassRelationMessage.setTestScaleId(testScaleId);
    		List<TestScaleClassRelationMessage> tscrList = testScaleClassRelationService.getAllMessage(testScaleClassRelationMessage);
    		
    		//关联量表与相关类目信息
    		if(tscrList!=null&&tscrList.size()>0){
    			List<Integer> searchList  = new ArrayList<Integer>(); 
        		for(int i=0;i<tscrList.size();i++){
        			searchList.add(tscrList.get(i).getTestScaleClassId());
        		}
        		
        		TestScaleClassMessage tscMessage = new TestScaleClassMessage();
        		tscMessage.setSearchList(searchList);
        		tscMessage.setHomeRecomm(TestScaleClassMessage.IS_HOME_RECOMM);
        		List<TestScaleClassMessage>  tscList = testScaleClassService.getAllMessageByIdList(tscMessage);
                
        		if(tscList!=null && tscList.size()>0){
        			testScaleInfoMessage.setTscList(tscList);
        		}
    		}
    		
    	    //获取人群范围
       		getPopulationRange(testScaleInfoMessage, testScaleId);
       		
       		//判断此量表是否被收藏
       		String userIdObj = request.getParameter("userId");
       		if(userIdObj!=null && StringUtil.isNotEmpty(userIdObj)){
		        Integer userId =Integer.parseInt(userIdObj);
	       		
	       		CommonCollection commonCollection = new CommonCollection();
	       		commonCollection.setArticleId(Long.valueOf(testScaleId+""));
	       		commonCollection.setMid(Long.valueOf(userId+""));
	       		commonCollection.setSource(CommonCollection.SOURCE_TEST_SCALE);
	       		if(commonCollectionService.isCollected(commonCollection)){
	       			isCollected=CommonCollection.IS_COLLECTED;
	       		}
	       		
	       		//取最新的订单记录信息
	       		TestScaleOrderMessage tsoMessage = new TestScaleOrderMessage();
	       		tsoMessage.setRelateTestScaleId(testScaleId);
	       		tsoMessage.setUserId(userId);
	       		TestScaleOrderMessage tsOrderMessage = tsoService.getLatestOrder(tsoMessage);
	       		if(tsOrderMessage!=null){
	           		testScaleInfoMessage.setLatestOrderId(tsOrderMessage.getId());
	       		}
	       		
       		}
       		
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleInfoMessage",testScaleInfoMessage);
    	result.put("isCollected",isCollected);
    	result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    //获取人群范围
	private void getPopulationRange(TestScaleInfoMessage testScaleInfoMessage, Integer testScaleId) {
		TestScalePopulationRangeMessage tsprMessage = new TestScalePopulationRangeMessage();
		tsprMessage.setRelateTestScaleId(testScaleId);
		List<TestScalePopulationRangeMessage> tsprList = testScalePopulationRangeService.getAllMessage(tsprMessage);
		testScaleInfoMessage.setTsprList(tsprList);
	}
    
//    @RequestMapping(value="/getNewestMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
//    public Object getNewestMessage(HttpServletRequest request){
//
//    	ResultEntity result = new ResultEntity(); 
//    	List<TestScaleInfoMessage> list = new ArrayList<TestScaleInfoMessage>();
//    	int count = 0;
//    	
//    	try{
//    		TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
//    		tsiMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
//	        list = service.getNewestMessage(tsiMessage);
//	        if(list!=null&&list.size()>0){
//        	   for(int i=0;i<list.size();i++){
//			    	TestScaleInfoMessage tsMessage = list.get(i);
//			    	
//			    	//获取量表下面题目数
//			    	getNumberOfTitle(tsMessage);
//			    }
//	        	count = list.size();
//	        }
//    	
//    	}catch(Exception e){
//    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
//			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//			result.setMsg(e.getMessage());
//			return result;
//    	}
//    	result.put("testScaleInfoList",list);
//    	result.put("count", count);
//		result.setCode(ErrorCode.SUCCESS.getCode());
//		result.setMsg(ErrorCode.SUCCESS.getMessage());
//		return result;
//        
//    }
    
	 
	 //获取量表分类下面的最热量表
	 @RequestMapping(value="/getHotMessageFromClassPage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	 public Object getHotMessageFromClassPage(HttpServletRequest request){

	    	ResultEntity result = new ResultEntity();  
	    	List<TestScaleInfoMessage> tsiList = new ArrayList<TestScaleInfoMessage>();
	    	int count = 0;
	    	try{
			        //获取热门测试信息
			        TestScaleInfoMessage tsInfoMessage = new TestScaleInfoMessage();
			        tsInfoMessage.setLimit(TestScaleInfoMessage.CLASS_PAGE_LIMIT);
			        tsInfoMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
			        tsiList = service.getHotMessage(tsInfoMessage);
				    if(tsiList!=null && tsiList.size()>0){
				        for(int i=0;i<tsiList.size();i++){
					    	TestScaleInfoMessage tsMessage = tsiList.get(i);
					    	
					    	//获取量表下面题目数
					    	getNumberOfTitle(tsMessage);
					    }
				        count = tsiList.size();
				    }

	    	}catch(Exception e){
	    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
				result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
				result.setMsg(e.getMessage());
				return result;
	    	}
	    	result.put("testScaleInfoList",tsiList);
	    	result.put("count", count);
			result.setCode(ErrorCode.SUCCESS.getCode());
			result.setMsg(ErrorCode.SUCCESS.getMessage());
			return result;
	}
	
    @RequestMapping(value="/getMessageFromClassPage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageFromClassPage(HttpServletRequest request,HttpServletResponse response){

    	response.addHeader("Access-Control-Allow-Origin", "*");
    	ResultEntity result = new ResultEntity();  
    	Integer testScaleClassId = 0;
    	List<TestScaleInfoMessage> tsiList = new ArrayList<TestScaleInfoMessage>();
    	int count = 0;
    	try{
	        testScaleClassId =Integer.parseInt(request.getParameter("testScaleClassId"));
		    if(testScaleClassId==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    if(testScaleClassId == 0){
		    	TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
	    		tsiMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
	    		tsiList = service.getNewestMessage(tsiMessage);
		        if(tsiList!=null&&tsiList.size()>0){
	        	   for(int i=0;i<tsiList.size();i++){
				    	TestScaleInfoMessage tsMessage = tsiList.get(i);
				    	
				    	//获取量表下面题目数
				    	getNumberOfTitle(tsMessage);
				    }
		        	count = tsiList.size();
		        }
		    }else{
		    	 //获取分类信息
//			    TestScaleClassMessage tscMessage = testScaleClassService.getMessByIdFromFront(testScaleClassId);
			    
			    //获取分类下的量表信息
			    List<Integer> tscIdList = new ArrayList<Integer>();
			    TestScaleClassRelationMessage  tscrMessage = new TestScaleClassRelationMessage();
			    tscrMessage.setTestScaleClassId(testScaleClassId);
			    List<TestScaleClassRelationMessage> tscrList = testScaleClassRelationService.getAllMessage(tscrMessage);
			    if(tscrList!=null && tscrList.size()>0){
				    for(int i=0;i<tscrList.size();i++){
				    	TestScaleClassRelationMessage tscrMess = tscrList.get(i);
				    	tscIdList.add(tscrMess.getTestScaleId());
			        }
				    
				    TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
				    tsiMessage.setSearchList(tscIdList);
//				    tsiMessage.setIsEnable(TestScaleInfoMessage.IS_ONLINE);
				    tsiList = service.getAllMessageFromClassPage(tsiMessage);
				    if(tsiList!=null && tsiList.size()>0){
				        for(int i=0;i<tsiList.size();i++){
					    	TestScaleInfoMessage tsMessage = tsiList.get(i);
					    	
					    	//获取量表下面题目数
					    	getNumberOfTitle(tsMessage);
					    }
				        count = tsiList.size();
				    }
			    }
		    }

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleClassId",testScaleClassId);
    	result.put("testScaleInfoList",tsiList);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    //获取量表下面题目数
	private void getNumberOfTitle(TestScaleInfoMessage tsMessage) {
		Integer tsId = tsMessage.getId();
		
		TestScaleTitleMessage testScaleTitleMessage = new TestScaleTitleMessage();
		testScaleTitleMessage.setRelateTestScaleId(tsId);
		Integer numberOfTitle = testScaleTitleService.selectCount(testScaleTitleMessage);
		if(numberOfTitle!=null && numberOfTitle>0){
			tsMessage.setNumberOfTitle(numberOfTitle);
		}
	}
    
    
    
    @RequestMapping(value="/getMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageById(HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	TestScaleInfoMessage testScaleInfoMessage = new TestScaleInfoMessage();    	
    	try{
	        Integer id =Integer.parseInt(request.getParameter("id"));
	        if(id==null || id==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	        }
	        
	        testScaleInfoMessage = service.getMessageById(id);
	        
	        //获取所有分类信息
	        List<TestScaleClassMessage> allTscList = testScaleClassService.getAllMessage();
	        
	        //获取量表关联分类Id
    		TestScaleClassRelationMessage testScaleClassRelationMessage = new TestScaleClassRelationMessage();
    		testScaleClassRelationMessage.setTestScaleId(id);
    		List<TestScaleClassRelationMessage> tscrList = testScaleClassRelationService.getAllMessage(testScaleClassRelationMessage);
    		
    		//关联量表与相关类目信息
    		if(tscrList!=null&&tscrList.size()>0){
    			List<Integer> searchList  = new ArrayList<Integer>(); 
        		for(int i=0;i<tscrList.size();i++){
        			searchList.add(tscrList.get(i).getTestScaleClassId());
        		}
        		for(int j=0;j<allTscList.size();j++){
        			TestScaleClassMessage tscMessage = allTscList.get(j);
        			Integer tscMessageId = tscMessage.getId();
        			if(searchList.contains(tscMessageId)){
        				tscMessage.setIsBelongTo(true);
        			}else{
        				tscMessage.setIsBelongTo(false);
        			}
        		}
    		}
    		
    		testScaleInfoMessage.setTscList(allTscList);
    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleInfoMessage",testScaleInfoMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }

    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TestScaleInfoMessage testScaleInfoMessage,HttpServletResponse response){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleInfoMessage> list = new ArrayList<TestScaleInfoMessage>();
    	int count = 0;
    	int testScaleNum = 0;
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	try{
    		Integer pageIndex = testScaleInfoMessage.getPageIndex();
    		Integer pageSize = testScaleInfoMessage.getPageSize();
		    if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
	    	PageHelper.startPage(pageIndex, pageSize);
	        
	        list = service.getAllMessage(testScaleInfoMessage);
	        if(list!=null&&list.size()>0){
	        	
	        	for(int i=0;i<list.size();i++){
	        		TestScaleInfoMessage tsMessage = list.get(i);
	        		Integer tsId = tsMessage.getId();
	        		
	        		//获取量表下面题目数
	        		getNumberOfTitle(tsMessage);
	        		
	        		//获取量表关联分类信息
	        		TestScaleClassRelationMessage testScaleClassRelationMessage = new TestScaleClassRelationMessage();
	        		testScaleClassRelationMessage.setTestScaleId(tsId);
	        		List<TestScaleClassRelationMessage> tscrList = testScaleClassRelationService.getAllMessage(testScaleClassRelationMessage);
	        		
	        		if(tscrList!=null&&tscrList.size()>0){
	        			List<Integer> searchList  = new ArrayList<Integer>(); 
		        		for(int j=0;j<tscrList.size();j++){
		        			searchList.add(tscrList.get(j).getTestScaleClassId());
		        		}
		        		TestScaleClassMessage tscMessage = new TestScaleClassMessage();
		        		tscMessage.setSearchList(searchList);
		        		List<TestScaleClassMessage> tscList = testScaleClassService.getAllMessageByIdList(tscMessage);
		        		if(tscList!=null&&tscList.size()>0){
		        			tsMessage.setTscList(tscList);
		        		}
	        		}

	        		//获取人群范围
	        		getPopulationRange(tsMessage, tsId);
	        	}
	        	
	        	//获取量表总数
	        	count = list.size();
	        	
	        	TestScaleInfoMessage tsiMess = new TestScaleInfoMessage();
	        	String name = testScaleInfoMessage.getName();
	        	Integer isEnable = testScaleInfoMessage.getIsEnable();
	        	if(name !=null && !name.isEmpty()){
	        		tsiMess.setName(name);
	        	}
	        	if(isEnable !=null){
	        		tsiMess.setIsEnable(isEnable);
	        	}
	        	testScaleNum = service.getTestScaleNumByCondition(tsiMess);
	        
	        }
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("contentList", list);
    	result.put("count", count);
    	result.put("testScaleNum", testScaleNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    
    //添加测试量表信息
    @RequestMapping(value = "add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(@RequestBody TestScaleInfoMessage testScaleInfoMessage, HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	        String name = testScaleInfoMessage.getName();
	        Integer displayMode = testScaleInfoMessage.getDisplayMode();
	        if(name==null || name.isEmpty() ||
	           displayMode==null){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	        }
	        
	        if(name.length()>TestScaleInfoMessage.TESTSCALE_NAME_LIMIT){
 		   		result.setCode(ErrorCode.ERROR_TESTSCALE_NAME_LIMIT.getCode());
 				result.setMsg(ErrorCode.ERROR_TESTSCALE_NAME_LIMIT.getMessage());
 				return result;
	 	    }
	     	
	        Boolean isSuccess = service.addMessage(testScaleInfoMessage);
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
    	result.put("testScaleInfoId", testScaleInfoMessage.getId());
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(@RequestBody TestScaleInfoMessage testScaleInfoMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	
    	try{
	        
	        Integer id = testScaleInfoMessage.getId();
	        String name = testScaleInfoMessage.getName();
		    if(id==null || id==0 || name==null || name.isEmpty() ){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
	        if(name.length()>TestScaleInfoMessage.TESTSCALE_NAME_LIMIT){
 		   		result.setCode(ErrorCode.ERROR_TESTSCALE_NAME_LIMIT.getCode());
 				result.setMsg(ErrorCode.ERROR_TESTSCALE_NAME_LIMIT.getMessage());
 				return result;
	 	    }
	    	
		    //判断量表是否已上线，已上线不允许修改
	        if(service.isOnline(id,result)){
				return result;
	        }
		    
		    
	        isSuccess = service.updateMess(testScaleInfoMessage);
	        
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
    public Object deleteMessage(TestScaleInfoMessage testScaleInfoMessage,HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	        
	        Integer id = testScaleInfoMessage.getId();
	        if(id==null || id==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        
		    //判断量表是否已上线，已上线不允许修改
	        if(service.isOnline(id,result)){
				return result;
	        }
	        
	        result = service.deleteMess(testScaleInfoMessage);
	      
        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
		return result;
    }


}

