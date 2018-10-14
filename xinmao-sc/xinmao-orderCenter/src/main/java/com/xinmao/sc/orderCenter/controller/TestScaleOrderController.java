package com.xinmao.sc.orderCenter.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.xinmao.sc.orderCenter.api.TestScaleOrderApi;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderDimensionNormMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;
import com.xinmao.sc.orderCenter.domain.TestscaleQualitativeMemberWechatRelation;
import com.xinmao.sc.orderCenter.domain.TestscaleShareFriendsRelation;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.orderCenter.service.TestScaleInfoService;
import com.xinmao.sc.orderCenter.service.TestScaleOrderDimensionNormService;
import com.xinmao.sc.orderCenter.service.TestScaleOrderService;
import com.xinmao.sc.orderCenter.service.TestScaleQualitativeService;
import com.xinmao.sc.orderCenter.service.TestscaleQualitativeMemberWechatRelationService;
import com.xinmao.sc.orderCenter.service.TestscaleShareFriendsRelationService;
import com.xinmao.sc.testCenter.domain.ConditionsOfQualitativeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleNormMessage;
import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleQualitativeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleTitleMessage;


@RestController
//@RequestMapping("testScaleOrder")
public class TestScaleOrderController implements TestScaleOrderApi{
	
//	Logger log = Logger.getLogger(this.getClass());
	
	private static final Logger log = LogManager.getLogger();

    @Autowired
    private TestScaleOrderService service;
    
    @Autowired
    private TestScaleInfoService  tsiService; 
    
    @Autowired
    private TestScaleQualitativeService tsqService;
    
    @Autowired
    private TestScaleOrderDimensionNormService tsodnService;
    
    @Autowired
    private TestscaleShareFriendsRelationService tsfrService;
    
    @Autowired
    private TestscaleQualitativeMemberWechatRelationService tqmwrService;

    
    @RequestMapping(value="/completeTest",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object completeTest(TestScaleOrderMessage testScaleOrderMessage,String mwHeadimgurl,HttpServletResponse response){

    	response.addHeader("Access-Control-Allow-Origin", "*");
    	
    	ResultEntity result = new ResultEntity(); 
    	
    	try{
	    	
    		//判断各参数是否为空
	        Integer testScaleId = testScaleOrderMessage.getRelateTestScaleId();
	        Integer userId = testScaleOrderMessage.getUserId();
	        String sex = testScaleOrderMessage.getSex();
	        Date birthday = testScaleOrderMessage.getBirthday();
//	        String userName= testScaleOrderMessage.getUserName();
	        Long telephone = testScaleOrderMessage.getTelephone();
	        String tsotListStr = testScaleOrderMessage.getTsotListStr();

//	        List<TestScaleOrderTitleMessage> tsotList = testScaleOrderMessage.getTsotList();
	        if(testScaleId==null || testScaleId==0 ||
//	           userId==null || userId==0 ||
	           sex==null || sex.isEmpty()||
	           birthday==null||
//	           tsotList == null || tsotList.size()==0||
	           tsotListStr == null || tsotListStr.isEmpty()
//	           ||userName == null || userName.isEmpty() ||
//	           telephone == null || telephone==0
	           ){
 		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
 				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
 				return result;
 	        }
	        
	        log.error("有一笔测试量表订单开始创建了：量表id为："+testScaleId+",用户id为："+userId+",用户手机号为："+telephone);
	        
	        List<TestScaleOrderTitleMessage> tsotList = new ArrayList<TestScaleOrderTitleMessage>();
	        JSONArray jsonArray = new JSONArray(tsotListStr);
	        for(int i =0;i<jsonArray.length();i++){
	        	JSONObject jsonObj = jsonArray.getJSONObject(i);
	        	TestScaleOrderTitleMessage tsotMess = new TestScaleOrderTitleMessage();
	        	tsotMess.setOptionId((Integer) jsonObj.get("optionId"));
	        	tsotMess.setTestScaleTitleId((Integer) jsonObj.get("testScaleTitleId"));
	        	tsotList.add(tsotMess);
	        }
	        testScaleOrderMessage.setTsotList(tsotList);
	        
	 	    for(int i=0;i<tsotList.size();i++){
	 		   TestScaleOrderTitleMessage tsotMessage = tsotList.get(i);
	 		   Integer titleId = tsotMessage.getTestScaleTitleId();
	 		   Integer optionId = tsotMessage.getOptionId();
	 		   
	 		   if(titleId==null || titleId ==0 || optionId==null || optionId==0){
	 				result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
	 				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	 				return result;
	 		   }
	 			   
	 	    }
	  	   
	        //获取量表，维度，人群范围，常模，定性信息
	        TestScaleInfoMessage tsiMessage = tsiService.getTestScaleAllMessageById(testScaleOrderMessage);
	        if(tsiMessage == null){
	    		result.setCode(ErrorCode.ERROR_GET_TEST_SCALE_INFO.getCode());
				result.setMsg(ErrorCode.ERROR_GET_TEST_SCALE_INFO.getMessage());
				return result;
	        }
	    	
	        //设置题目选项信息
	        Boolean sutaoiIsSuccess = setUpTitleAndOptionInfo(tsiMessage,testScaleOrderMessage);
	        if(!sutaoiIsSuccess){
	    		result.setCode(ErrorCode.ERROR_SET_UP_TITLE_OPTION_INFO.getCode());
				result.setMsg(ErrorCode.ERROR_SET_UP_TITLE_OPTION_INFO.getMessage());
				return result;
	        }
	        
	        //根据量表的前端展现方式，选择不同的计算方式：原始分，z分，t分,计算各维度最终分数，同时设置订单与维度常模是否正常
	        Boolean sudiIsSuccess = setUpDimensionInfo(tsiMessage,testScaleOrderMessage);
	        if(!sudiIsSuccess){
	    		result.setCode(ErrorCode.ERROR_SET_UP_DIMENSION_INFO.getCode());
				result.setMsg(ErrorCode.ERROR_SET_UP_DIMENSION_INFO.getMessage());
				return result;
	        }

	        
	        //选择符合条件的定性
	        Boolean cqIsSuccess = chooseQualitative(tsiMessage,testScaleOrderMessage);
	        if(!cqIsSuccess){
	    		result.setCode(ErrorCode.ERROR_CHOOSE_QUALITATIVE.getCode());
				result.setMsg(ErrorCode.ERROR_CHOOSE_QUALITATIVE.getMessage());
				return result;
	        }
	        
	        Boolean isSuccess = service.completeTest(testScaleOrderMessage,tsiMessage, mwHeadimgurl);
			if(!isSuccess){
				result.setCode(ErrorCode.ERROR_COMPLETE_TEST.getCode());
				result.setMsg(ErrorCode.ERROR_COMPLETE_TEST.getMessage());
				return result;
			}
			
			//测试量表的numberOfTest+1
			TestScaleInfoMessage tsInfoMess = new TestScaleInfoMessage();
			tsInfoMess.setId(testScaleId);
			Map<String, Object> resultMap = tsiService.addNumberOfTest(tsInfoMess);
         	ResultEntity resultEntity = (ResultEntity) resultMap;
        	if(!ErrorCode.SUCCESS.getCode().equals(resultEntity.getCode())){
          		result.setCode(ErrorCode.ERROR_UPDATE_NUMBER_OF_TEST.getCode());
        		result.setMsg(ErrorCode.ERROR_UPDATE_NUMBER_OF_TEST.getMessage());
        		return result;
        	}
			
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("testScaleOrderId", testScaleOrderMessage.getId());
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    public Boolean setUpTitleAndOptionInfo(TestScaleInfoMessage tsiMessage,TestScaleOrderMessage testScaleOrderMessage){
    	
       Map<Integer,TestScaleTitleMessage> titleMap = tsiMessage.getTitleMap();
 	   List<TestScaleOrderTitleMessage> tsotList = testScaleOrderMessage.getTsotList();
 	   
 	   if(tsotList == null || tsotList.size()==0){
 		   log.error("前端推送的题目信息为空");
 		   return false;
 	   }
 	   
	   for(int i=0;i<tsotList.size();i++){
		   TestScaleOrderTitleMessage tsotMessage = tsotList.get(i);
		   Integer titleId = tsotMessage.getTestScaleTitleId();
		   Integer optionId = tsotMessage.getOptionId();
		   
		   if(titleId==null || titleId ==0 || optionId==null || optionId==0){
			   log.error("前端推送的题目或者选项id为空");
			   return false;
		   }
			   
		   TestScaleTitleMessage tstMess = titleMap.get(titleId);
	   	   TestScaleOptionMessage tsoMessage = tstMess.getTsoMessage();
	   	   tsotMessage.setProblemWord(tstMess.getProblemWord());
	   	   tsotMessage.setProblemPicSrc(tstMess.getProblemPicSrc());
	   	   tsotMessage.setOptionWord(tsoMessage.getOptionWord());
	   	   tsotMessage.setOptionScore(tsoMessage.getOptionScore());
	   	   tsotMessage.setOptionPicSrc(tsoMessage.getOptionPic());
	   }
	   
	   return true;
    }
    
    public Boolean setUpDimensionInfo(TestScaleInfoMessage tsiMessage,TestScaleOrderMessage testScaleOrderMessage){
    	  
    	   Map<Integer,Float> dimensionScoreMap = new HashMap<Integer,Float>();
    	   Map<Integer,TestScaleTitleMessage> titleMap = tsiMessage.getTitleMap();
		   List<TestScaleDimensionMessage> tsdList = tsiMessage.getTsdList();
		   
		   List<TestScaleOrderDimensionNormMessage> tsodnList = new ArrayList<TestScaleOrderDimensionNormMessage>();
		   Integer displayMode = tsiMessage.getDisplayMode();
		   if(displayMode == null|| tsdList==null || tsdList.size()==0){
			    log.error("量表的displayMode为空或者量表的维度信息为空");
				return false;
		   }else if(TestScaleInfoMessage.DISPLAY_MODE_O_POINT==displayMode||
				    TestScaleInfoMessage.DISPLAY_MODE_T_POINT==displayMode||
				    TestScaleInfoMessage.DISPLAY_MODE_Z_POINT==displayMode){
			    Boolean isNormal =true;
			    for(int i=0;i<tsdList.size();i++){
			    	Float dimensionOriScore = 0f;
			    	Float dimensionScore = 0f;
			    	TestScaleDimensionMessage tsdMessage = tsdList.get(i);
			    	TestScaleNormMessage tsnMessage = tsdMessage.getTsnMessage();
			    	if(tsnMessage == null){
			    		log.error("量表维度对应的常模信息为空");
			    		return false;
			    	}
			    	
			    	Integer dimensionId = tsdMessage.getId();
			    	Float dimensionFullScore = tsdMessage.getDimensionFullScore();
			    	Float mean = tsnMessage.getAverageValue();
			    	Float sd = tsnMessage.getStandardDeviation();
			    	Float highScore = tsnMessage.getHighScore();
			    	Float lowScore = tsnMessage.getLowScore();
			    	List<Integer> tstIdList = tsdMessage.getTstIdList();
			    	if(tstIdList!=null && tstIdList.size()>0){
			    	   	for(int j=0;j<tstIdList.size();j++){
			    	   		Integer titleId = tstIdList.get(j);
			    	   		TestScaleTitleMessage tstMess = titleMap.get(titleId);
			    	   		TestScaleOptionMessage tsoMessage = tstMess.getTsoMessage();
			    	   		Float optionScore = tsoMessage.getOptionScore();
//			    	   		if(optionScore!=null && optionScore>0){
			    	   		if(optionScore!=null){
			    	   			dimensionOriScore +=optionScore;
			    	   		}
				    	}
			    	}
			    	
			    	//不同的前端展现方式计算不同的维度分
			    	if(TestScaleInfoMessage.DISPLAY_MODE_T_POINT==displayMode){
			    		dimensionScore = TestScaleDimensionMessage.getTscore(dimensionOriScore, mean, sd);	   
			    		log.error("维度id为："+dimensionId+",计算方式为T分，维度原始分为："+dimensionOriScore+",维度平均分为："+mean+",维度标准差为："+sd+",最终计算维度得分为："+dimensionScore);
			    	}else if(TestScaleInfoMessage.DISPLAY_MODE_Z_POINT==displayMode){
			    		dimensionScore = TestScaleDimensionMessage.getZscore(dimensionOriScore, mean, sd);
			    		log.error("维度id为："+dimensionId+",计算方式为Z分，维度原始分为："+dimensionOriScore+",维度平均分为："+mean+",维度标准差为："+sd+",最终计算维度得分为："+dimensionScore);
			    	}else{
			    		dimensionScore = TestScaleDimensionMessage.getRoughscore(dimensionOriScore, dimensionFullScore);
			    		log.error("维度id为："+dimensionId+",计算方式为原始分，维度原始分为："+dimensionOriScore+",维度满分为："+dimensionFullScore+",最终计算维度得分为："+dimensionScore);
			    	}
			    	
			    	TestScaleOrderDimensionNormMessage tsodnMessage = new TestScaleOrderDimensionNormMessage();
			    	tsodnMessage.setDimensionId(dimensionId);
			    	tsodnMessage.setDimensionName(tsdMessage.getName());
			    	tsodnMessage.setDimensionDescription(tsdMessage.getDescription());
			    	tsodnMessage.setNormId(tsnMessage.getId());
			    	tsodnMessage.setNormHighScore(tsnMessage.getHighScore());
			    	tsodnMessage.setNormLowScore(tsnMessage.getLowScore());
			    	tsodnMessage.setDimensionScore(dimensionScore);
			    	if(dimensionScore>=lowScore && dimensionScore<=highScore){
			    		tsodnMessage.setIsNormal(TestScaleOrderDimensionNormMessage.IS_NORMAL);
			    	}else{
			    		tsodnMessage.setIsNormal(TestScaleOrderDimensionNormMessage.IS_NOT_NORMAL);
			    		isNormal = false;
			    	}
			    	
			    	tsodnList.add(tsodnMessage);
			    	dimensionScoreMap.put(dimensionId, dimensionScore);
			    }  			   
			   
			    //设置订单是否正常
			    if(isNormal){
			    	testScaleOrderMessage.setIsNormal(TestScaleOrderMessage.IS_NORMAL);
			    }else{
			    	testScaleOrderMessage.setIsNormal(TestScaleOrderMessage.IS_NOT_NORMAL);
			    }
			    
			    //设置key为维度id，value为该用户在该维度下得分的map
			    testScaleOrderMessage.setDimensionScoreMap(dimensionScoreMap);
			    
			    //设置维度常模快照信息
			    testScaleOrderMessage.setTsodnList(tsodnList);
			    
		   }else{
			   log.error("量表的displayMode不属于任何一种后台规定的displayMode");
			   return false;
		   }
        	
    	return true;
    }
    
    
    public Boolean chooseQualitative(TestScaleInfoMessage tsiMessage,TestScaleOrderMessage testScaleOrderMessage){
    	List<TestScaleQualitativeMessage> tsqList = tsiMessage.getTsqList();
    	Integer testScaleId = testScaleOrderMessage.getRelateTestScaleId();
    	Integer userId = testScaleOrderMessage.getUserId();
    	if(tsqList!=null && tsqList.size()>0){
    		TestScaleQualitativeMessage premiseTsqMess = null;
    		TestScaleQualitativeMessage otherTsqMess = null;
    		List<TestScaleQualitativeMessage> ordinaryList = new ArrayList<TestScaleQualitativeMessage>();
    		for(int i=0;i<tsqList.size();i++){
    			TestScaleQualitativeMessage tsqMessage = tsqList.get(i);
    			Integer qualitativeType = tsqMessage.getQualitativeType();
    			if(qualitativeType != null){
    				if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_PREMISE == qualitativeType){
    					premiseTsqMess = tsqMessage;
    				}else if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_OTHER == qualitativeType){
    					otherTsqMess = tsqMessage;
    				}else if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_ORDINARY == qualitativeType){
    					ordinaryList.add(tsqMessage);
    				}
    			}
    			
    		}
    		
    		//先判断是否符合前提定性
    		if(premiseTsqMess!=null && isConformingQualitative(testScaleOrderMessage, premiseTsqMess)){
    			log.error("符合前提定性的成立条件");
    		    return true;	
    		}
    		
    		
    		//再判断是否符合普通定性
    		if(ordinaryList!=null && ordinaryList.size()>0){
    			for(int i=0;i<ordinaryList.size();i++){
    				TestScaleQualitativeMessage tsqMessage  = ordinaryList.get(i);
    				if(isConformingQualitative(testScaleOrderMessage, tsqMessage)){
    					log.error("符合普通定性的成立条件");
    	    		    return true;	
    	    		}
    			}
    		}
    		
    		//如果前面的定性成立条件都不符合，用其他定性
    		if(otherTsqMess!=null){
    			testScaleOrderMessage.setRelateQualitativeId(otherTsqMess.getId());
				testScaleOrderMessage.setRelateQualitativeName(otherTsqMess.getName());
				log.error("符合其他定性的成立条件");
    		    return true;	
    		}
    		
    	}
    	log.error("在订单的创建过程中，用户id为"+userId+"的用户不符合量表id为"+testScaleId+"的量表所需要的定性成立条件，需要查询该量表的定性成立条件设置是否合理，"
    			+ "比如'前提定性'，'其他定性'是否有设置，'普通定性'的定性成立条件是否符合大多数情况。");
    	
    	return false;
    }

	private Boolean isConformingQualitative(TestScaleOrderMessage testScaleOrderMessage,
			TestScaleQualitativeMessage tsqMess) {
		
		Boolean isConformingQualitative = false;
		Map<Integer,Float> dimensionScoreMap = testScaleOrderMessage.getDimensionScoreMap();
		
		List<ConditionsOfQualitativeMessage> coqList = tsqMess.getCoqList();
		if(coqList!=null && coqList.size()>0){
			int matchNum = 0;
		
			for(int i=0;i<coqList.size();i++){
				ConditionsOfQualitativeMessage coqMessage = coqList.get(i);
//				Integer leftType = coqMessage.getLeftType();
//				Float leftValue = coqMessage.getLeftValue();
//				Integer rightType = coqMessage.getRightType();
//				Float rightValue = coqMessage.getRightValue();
				Integer leftDimensionId = coqMessage.getLeftDimensionId();
				Integer rightDimensionId = coqMessage.getRightDimensionId();
				Float rightScore = coqMessage.getRightScore();
				Integer leftRightRelation = coqMessage.getLeftRightRelation();
				Float leftValueScore = 0f;
				Float rightValueScore = 0f;
				
//				if(ConditionsOfQualitativeMessage.LEFT_TYPE_DIMENSION == leftType){
//					leftValueScore = dimensionScoreMap.get(leftValue.intValue());
//				}else if(ConditionsOfQualitativeMessage.LEFT_TYPE_SCORE == leftType){
//					leftValueScore = leftValue;
//				}
				if(leftDimensionId!=null && leftDimensionId>0){
					leftValueScore = dimensionScoreMap.get(leftDimensionId);
				}else{
					log.error("定性成立条件的左侧维度id为空或者为0");
					return false;
				}
				
//				if(ConditionsOfQualitativeMessage.RIGHT_TYPE_DIMENSION == rightType){
//					rightValueScore = dimensionScoreMap.get(rightValue.intValue());
//				}else if(ConditionsOfQualitativeMessage.RIGHT_TYPE_SCORE == rightType){
//					rightValueScore = rightValue;
//				}
				if(rightDimensionId!=null && leftDimensionId>0){
					rightValueScore = dimensionScoreMap.get(rightDimensionId);
				}else if(rightScore!=null){
					rightValueScore = rightScore;
				}else{
					log.error("定性成立条件的右侧维度id为空或者为0，且定性成立条件右侧的分数为空");
					return false;
				}
				
				if(ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_GREATER == leftRightRelation){
					if(leftValueScore>rightValueScore){
						matchNum++;
						log.error("因为"+leftValueScore+">"+rightValueScore+"是正确的，所以id为"+coqMessage.getId()+"的定性条件成立");
					}else{
						log.error("因为"+leftValueScore+">"+rightValueScore+"是错误的，所以id为"+coqMessage.getId()+"的定性条件不成立");
					}
				}else if(ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_GREATER_EQUAL == leftRightRelation){
					if(leftValueScore>=rightValueScore){
						matchNum++;
						log.error("因为"+leftValueScore+">="+rightValueScore+"是正确的，所以id为"+coqMessage.getId()+"的定性条件成立");
					}else{
						log.error("因为"+leftValueScore+">="+rightValueScore+"是错误的，所以id为"+coqMessage.getId()+"的定性条件不成立");
					}
				}else if(ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_EQUAL == leftRightRelation){
					if(Math.abs(leftValueScore-rightValueScore)<0.00000001){//表示两者相等
						matchNum++;
						log.error("因为"+leftValueScore+"="+rightValueScore+"是正确的，所以id为"+coqMessage.getId()+"的定性条件成立");
					}else{
						log.error("因为"+leftValueScore+"="+rightValueScore+"是错误的，所以id为"+coqMessage.getId()+"的定性条件不成立");
					}
				}else if(ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_LESS_EQUAL == leftRightRelation){
					if(leftValueScore<=rightValueScore){
						matchNum++;
						log.error("因为"+leftValueScore+"<="+rightValueScore+"是正确的，所以id为"+coqMessage.getId()+"的定性条件成立");
					}else{
						log.error("因为"+leftValueScore+"<="+rightValueScore+"是错误的，所以id为"+coqMessage.getId()+"的定性条件不成立");
					}
				}else if(ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_LESS == leftRightRelation){
					if(leftValueScore<rightValueScore){
						matchNum++;
						log.error("因为"+leftValueScore+"<"+rightValueScore+"是正确的，所以id为"+coqMessage.getId()+"的定性条件成立");
					}else{
						log.error("因为"+leftValueScore+"<"+rightValueScore+"是错误的，所以id为"+coqMessage.getId()+"的定性条件不成立");
					}
				}
				
			}
			if(matchNum == coqList.size()){
				testScaleOrderMessage.setRelateQualitativeId(tsqMess.getId());
				testScaleOrderMessage.setRelateQualitativeName(tsqMess.getName());
				isConformingQualitative = true;
			}
		}
		return isConformingQualitative;
	}
    
	/**
	 * 获取测试结果信息
	 * @param 李志坚
	 * @param 
	 */
    @RequestMapping(value="/getTestResultMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getTestResultMessage(HttpServletRequest request,HttpServletResponse response){
    	
    	response.addHeader("Access-Control-Allow-Origin", "*");

    	ResultEntity result = new ResultEntity(); 
    	TestScaleOrderMessage tsoMessage = new TestScaleOrderMessage();
    	try{
    		
    		Integer testScaleOrderId = Integer.parseInt(request.getParameter("testScaleOrderId"));
 	        if(testScaleOrderId==null || testScaleOrderId==0){
 		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
 				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
 				return result;
 	        }
 	        
 	        //获取订单信息
 	        tsoMessage = service.getMessageById(testScaleOrderId);
 	        if(tsoMessage!=null){
 	        	Long userId = Long.valueOf(tsoMessage.getUserId());
 	        	
 	        	//获取量表相关信息
 	        	TestScaleInfoMessage tsiMessage = tsiService.getTestScaleAllMessage(tsoMessage);
 	        	Integer tsiId = tsiMessage.getId();
 	            
 	        	//获取哪些维度可以展示
 	        	Map<Integer,Integer> isShowDimensionMap = new HashMap<Integer,Integer>();
 	        	List<TestScaleDimensionMessage> tsdList = tsiMessage.getTsdList();
 	        	if(tsdList!=null && tsdList.size()>0){
 	        	    for(int i=0;i<tsdList.size();i++){
 	        	    	TestScaleDimensionMessage tsd = tsdList.get(i);
 	        	    	isShowDimensionMap.put(tsd.getId(),tsd.getIsShowDimension());
 	        		}
 	        	}
 	        	
 	        	//设置相关维度常模信息
 	        	TestScaleOrderDimensionNormMessage tsodnMessage = new TestScaleOrderDimensionNormMessage();
 	        	tsodnMessage.setTestScaleOrderId(testScaleOrderId);
 	        	List<TestScaleOrderDimensionNormMessage>  tsodnList = tsodnService.getAllMessage(tsodnMessage);
 	        	if(tsodnList!=null && tsodnList.size()>0){
 	        		for(int i =0;i<tsodnList.size();i++){
 	        			TestScaleOrderDimensionNormMessage tsodn = tsodnList.get(i);
 	        			Integer tsodnId = tsodn.getDimensionId();
 	        			Integer isShowDimension = isShowDimensionMap.get(tsodnId);
 	        			if(isShowDimension!=null){
 	        				tsodn.setIsShowDimension(isShowDimension);
 	        			}
 	        			
 	        			//把维度常模分设置为小数位后两位
 	        			Float dimensionScore = tsodn.getDimensionScore();
 	        			tsodn.setDimensionScore((float)((Math.round(dimensionScore*100))/100.0));
 	        			
 	        		}
 	        		tsoMessage.setTsodnList(tsodnList);
 	        	}
 	        	
 	        	//设置相关定性
 	        	List<TestScaleQualitativeMessage> tsqList = tsiMessage.getTsqList();
 	        	if(tsqList!=null && tsqList.size()>0){
 	        		tsoMessage.setTsqMessage(tsqList.get(0));
 	        	}
 	        	
 	        	//设置是否展示维度剖面
 	        	tsoMessage.setIsShowDimensionProfile(tsiMessage.getIsShowDimensionProfile());
 	        	
 	        	//配置定性关联的用户
 	        	Integer isShow = null;
 	        	Object obj = request.getParameter("isShow");
 	        	if(obj!=null){
 	        		 isShow = Integer.parseInt((String)obj);
 	        	}
 	        	
        		this.configQualitativeRelateMember(tsoMessage, userId, tsiId,isShow);
 		    }
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("testScaleOrderMessage", tsoMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

	private void configQualitativeRelateMember(TestScaleOrderMessage tsoMessage, Long userId, Integer tsiId,Integer isShow) {
		
		//获取关联用户id的集合
		List<Long> testerIdList = this.getTesterIdList(userId,tsiId);
		
		//获取各个定性关联用户
		TestScaleQualitativeMessage tsqMessage = new TestScaleQualitativeMessage();
		tsqMessage.setRelateTestScaleId(tsiId);
		List<TestScaleQualitativeMessage> tsqMessageList = tsqService.getListByTestScaleId(tsqMessage);
		if(tsqMessageList!=null & tsqMessageList.size()>0){
			if(tsqMessageList.size()>TestscaleQualitativeMemberWechatRelation.QUALITATIVE_NUM){
				tsqMessageList = tsqMessageList.subList(0, TestscaleQualitativeMemberWechatRelation.QUALITATIVE_NUM);
			}
			
			for(int i =0;i<tsqMessageList.size();i++){
				
				TestScaleQualitativeMessage tsqMess = tsqMessageList.get(i);
				Integer tsqId = tsqMess.getId();
				
				List<TestscaleQualitativeMemberWechatRelation> totalRelList = new ArrayList<TestscaleQualitativeMemberWechatRelation>();
				
				//获取每个定性的其他用户头像信息
				if(testerIdList!=null && testerIdList.size()>0){
					TestscaleQualitativeMemberWechatRelation tqmwRel = new TestscaleQualitativeMemberWechatRelation();
					tqmwRel.setQualitativeId(Long.valueOf(tsqId));
					tqmwRel.setSearchIdList(testerIdList);
					List<TestscaleQualitativeMemberWechatRelation> tqmwRelList = tqmwrService.getListByQidAndMidList(tqmwRel);
					if(tqmwRelList!=null && tqmwRelList.size()>TestscaleQualitativeMemberWechatRelation.MEMBER_NUM){
						tqmwRelList = tqmwRelList.subList(0, TestscaleQualitativeMemberWechatRelation.MEMBER_NUM);
					}
					totalRelList.addAll(tqmwRelList);
				}
				
				//获取本人的用户头像信息
				TestscaleQualitativeMemberWechatRelation selfRel = new TestscaleQualitativeMemberWechatRelation();
				selfRel.setQualitativeId(Long.valueOf(tsqId));
				selfRel.setMid(userId);
				TestscaleQualitativeMemberWechatRelation selfRelation = tqmwrService.getListByQidAndMid(selfRel);
				if(selfRelation!=null){
					if(isShow!=null && TestscaleQualitativeMemberWechatRelation.IS_SHOW.equals(isShow)){
						selfRelation.setIsSelf(true);
					}
					totalRelList.add(selfRelation);
				}
					
				tsqMess.setTqmwRelList(totalRelList);
			}
			tsoMessage.setTsqList(tsqMessageList);
		}
	}

	private List<Long> getTesterIdList(Long userId,Integer testScaleId) {
		
		List<Long>  totalTesterIdList = new ArrayList<Long>();
		List<Long> searchIdList = new ArrayList<Long>();
		searchIdList.add(userId);
		
		//获取四级用户关系列表--往后追溯
		for(int i=0;i<TestscaleShareFriendsRelation.USER_PROPAGATION_LEVEL_FOUR;i++){
			TestscaleShareFriendsRelation record = new TestscaleShareFriendsRelation();
			record.setSearchIdList(searchIdList);
			record.setTestScaleId(Long.valueOf(testScaleId));
			List<Long>  testerIdList = tsfrService.getTesterListBySharingPersonIdList(record);
			if(testerIdList==null || testerIdList.size()==0){
				break;
			}
			searchIdList = testerIdList;
			totalTesterIdList.addAll(testerIdList);
		}
		
	    //获取两级用户关系列表--往前追溯
		searchIdList.clear();
		searchIdList.add(userId);
		for(int i=0;i<TestscaleShareFriendsRelation.USER_PROPAGATION_LEVEL_TWO;i++){
			TestscaleShareFriendsRelation record = new TestscaleShareFriendsRelation();
			record.setSearchIdList(searchIdList);
			record.setTestScaleId(Long.valueOf(testScaleId));
			List<Long>  sharingPersonIdList = tsfrService.getSharingPersonIdListByTesterIdList(record);
			if(sharingPersonIdList==null || sharingPersonIdList.size()==0){
				break;
			}
			searchIdList = sharingPersonIdList;
			totalTesterIdList.addAll(sharingPersonIdList);
		}
		
		if(totalTesterIdList!=null && totalTesterIdList.size()>0){
			
			//去掉重复Id
			HashSet hashSet  =  new HashSet(totalTesterIdList); 
			totalTesterIdList.clear(); 
			totalTesterIdList.addAll(hashSet);
			
			//去掉本人id
			Integer selfIndex = null;
			for(int i=0;i<totalTesterIdList.size();i++){
				Long testId = totalTesterIdList.get(i);
				if(userId.equals(testId)){
					selfIndex = i;
				}
			}
			if(selfIndex!=null){
				totalTesterIdList.remove(selfIndex.intValue());
			}
		}
		return totalTesterIdList;
	}
    
    
    @RequestMapping(value="/getTestResultListMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getTestResultListMessage(TestScaleOrderMessage tsoMessage,HttpServletResponse response){

    	response.addHeader("Access-Control-Allow-Origin", "*");
    	
    	ResultEntity result = new ResultEntity(); 
    	
    	List<TestScaleOrderMessage> tsoList = new ArrayList<TestScaleOrderMessage>();
    	int count = 0;
    	
    	try{
	    	
	        Integer userId = tsoMessage.getUserId();
    		Integer pageIndex = tsoMessage.getPageIndex();
    		Integer pageSize = tsoMessage.getPageSize();
		    if(userId==null || userId==0 || pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
		    PageHelper.startPage(pageIndex, pageSize);
	        
	        tsoList = service.getAllMessage(tsoMessage);
	        if(tsoList!=null && tsoList.size()>0){
	        	Map<Integer,TestScaleInfoMessage> testScaleInfoMap = new HashMap<Integer,TestScaleInfoMessage>();
	        	List<Integer> testScaleIdList = new ArrayList<Integer>();
	        	for(int i=0;i<tsoList.size();i++){
	        		Integer relateTestScaleId = tsoList.get(i).getRelateTestScaleId();
	        		if(!testScaleIdList.contains(relateTestScaleId)){
	        			testScaleIdList.add(relateTestScaleId);
	        		}
	        	}
	        	
	        	TestScaleInfoMessage tsInfoMess = new TestScaleInfoMessage();
	        	tsInfoMess.setSearchList(testScaleIdList);
	        	
	        	//获取订单相关量表信息 
	        	Map<String, Object> resultMap = tsiService.getTestScaleMap(tsInfoMess);
	         	ResultEntity resultEntity = (ResultEntity) resultMap;
	        	if(ErrorCode.SUCCESS.getCode().equals(resultEntity.getCode())){
	        		testScaleInfoMap = (Map)resultEntity.get("testScaleInfoMap");
		        	if(testScaleInfoMap!=null && testScaleInfoMap.size()>0){
			        	for(int i=0;i<tsoList.size();i++){
			        		TestScaleOrderMessage tsoMess = tsoList.get(i);
			        		Integer testScaleId = tsoMess.getRelateTestScaleId();
			        		TestScaleInfoMessage tScaleInfoMess = new TestScaleInfoMessage();
			        		Map<Object,Object> tsiMap = (Map<Object, Object>)testScaleInfoMap.get(testScaleId+"");
			        		tScaleInfoMess.setId((Integer) tsiMap.get("id"));
			        		tScaleInfoMess.setAbstractStr((String) tsiMap.get("abstractStr"));
			        		tScaleInfoMess.setNumberOfTitle((Integer) tsiMap.get("numberOfTitle"));
			        		tScaleInfoMess.setNumberOfTest((Integer) tsiMap.get("numberOfTest"));
			        		tScaleInfoMess.setRelatePicSrc((String) tsiMap.get("relatePicSrc"));
			        		tScaleInfoMess.setIsNewResultPage((Integer) tsiMap.get("isNewResultPage"));
//			        		tScaleInfoMess.setName((String) tsiMap.get("name"));
//			        		Long dataLong = (Long) tsiMap.get("createTime");
//			        		Date date = new Date(dataLong);
//			        		tScaleInfoMess.setCreateTime(date);
			        		tsoMess.setTsiMessage(tScaleInfoMess);
			        	}
		        	}
	        	}else{
	        		result.setCode(resultEntity.getCode());
	    			result.setMsg(resultEntity.getMsg());
	    			return result;
	        	}
	        	
//		        count = tsoList.size();
		    }
	    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("testScaleOrderList",tsoList);
//    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    
    @RequestMapping(value="/exitTest",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object exitTest(HttpServletRequest request){
    	
    	ResultEntity result = new ResultEntity(); 
    	TestScaleOrderMessage tsoMessage = new TestScaleOrderMessage();
    	
    	try{
	    	
	        int id = Integer.parseInt(request.getParameter("id"));
	        tsoMessage = service.getMessageById(id);
	    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("tsoMessage", tsoMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    @Override
    public TestScaleOrderMessage getTestScaleOrderById(@RequestBody TestScaleOrderMessage tsOrderMessage){

    	TestScaleOrderMessage tsoMessage = new TestScaleOrderMessage();
    	
    	try{
    		Integer id = tsOrderMessage.getId();
    		
	        if(id==null || id==0){
	        	log.error(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return null;
	        }
    		
	        tsoMessage = service.getMessageById(id);
	    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
    	
		return tsoMessage;
        
    }
    
    @Override
    public TestScaleOrderMessage getLatestOrder(@RequestBody TestScaleOrderMessage tsoMessage){
    	
    	TestScaleOrderMessage tsOrderMessage = new TestScaleOrderMessage();
    	
    	try{
    		Integer testScaleId = tsoMessage.getRelateTestScaleId();
    		Integer userId = tsoMessage.getUserId();
    		
	        if(testScaleId == null || testScaleId == 0 ||
	        		userId == null || userId==0){
	        	log.error(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return null;
	        }
    		
	        tsOrderMessage = service.getLatestOrder(tsoMessage);
	    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
    	
		return tsOrderMessage;
        
    }

    @RequestMapping(value="/isHaveTestOrder",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object isHaveTestOrder(TestScaleOrderMessage testScaleOrderMessage,HttpServletResponse response){
    	
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	ResultEntity result = new ResultEntity();
    	Integer testScaleOrderNum = null;
    	try{
    		
    		Integer userId = testScaleOrderMessage.getUserId();
    		Integer relateTestScaleId = testScaleOrderMessage.getRelateTestScaleId();
		    if(userId==null || userId==0 ||
		       relateTestScaleId==null || relateTestScaleId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    //根据userId贺相关量表id获取测试记录数
		    TestScaleOrderMessage tsoMessage= new TestScaleOrderMessage();
		    tsoMessage.setUserId(userId);
		    tsoMessage.setRelateTestScaleId(relateTestScaleId);
        	testScaleOrderNum = service.getTestScaleOrderNumByCondition(tsoMessage);

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("testScaleOrderNum", testScaleOrderNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value="/getTestScaleOrderNumByUserId",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getTestScaleOrderNumByUserId(TestScaleOrderMessage testScaleOrderMessage){
    	ResultEntity result = new ResultEntity(); 
    	Integer testScaleOrderNum = null;
    	try{
    		
    		Integer userId = testScaleOrderMessage.getUserId();
		    if(userId==null || userId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    //根据userId获取测试记录数
		    TestScaleOrderMessage tsoMessage= new TestScaleOrderMessage();
		    tsoMessage.setUserId(userId);
        	testScaleOrderNum = service.getTestScaleOrderNumByCondition(tsoMessage);

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("testScaleOrderNum", testScaleOrderNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TestScaleOrderMessage tsoMessage){
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleOrderMessage> list = new ArrayList<TestScaleOrderMessage>();
    	int count = 0;
    	int numberOfTest = 0;
    	int testScaleNum = 0;
    	int testScaleOrderNum = 0;
    	try{
    		
    		Integer pageIndex = tsoMessage.getPageIndex();
    		Integer pageSize = tsoMessage.getPageSize();
		    if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
		    PageHelper.startPage(pageIndex, pageSize);
		    
	        list = service.getAllMessage(tsoMessage);
	        if(list!=null&&list.size()>0){
	        	
	            //获取总测试次数
	            numberOfTest = service.getTestScaleOrderNum();
	        	
	        	//获取量表数量
	        	Map<String, Object> resultMap = tsiService.getTestScaleNum();
	        	
	        	ResultEntity resultEntity = (ResultEntity) resultMap;
	        	if(ErrorCode.SUCCESS.getCode().equals(resultEntity.getCode())){
	        	    testScaleNum = (Integer) resultEntity.get("testScaleNum");
	        	}
	        	
	        	//获取维度常模快照信息
	        	for(int i=0;i<list.size();i++){
	        		TestScaleOrderMessage tsOrderMessage = list.get(i);
	        		TestScaleOrderDimensionNormMessage tsodnMessage = new TestScaleOrderDimensionNormMessage();
	        		tsodnMessage.setTestScaleOrderId(tsOrderMessage.getId());
	        		List<TestScaleOrderDimensionNormMessage> tsodnList = tsodnService.getAllMessage(tsodnMessage);
	        		tsOrderMessage.setTsodnList(tsodnList);
	        	}
	        	
	        	count = list.size();
	        	
	        	//获取当前的测试订单数
	        	Integer id = tsoMessage.getId();
	        	Long telephone = tsoMessage.getTelephone();
	        	String startTime = tsoMessage.getStartTime();
	        	String endTime = tsoMessage.getEndTime();
	        	TestScaleOrderMessage tsoMess = new TestScaleOrderMessage();
	        	if(id!=null && id!=0){
	        		tsoMess.setId(id);
	        	}
	        	if(telephone!=null && telephone!=0){
	        		tsoMess.setTelephone(telephone);
	        	}
	        	if(startTime!=null && !startTime.isEmpty()){
	        		tsoMess.setStartTime(startTime);
	        	}
       	        if(endTime!=null && !endTime.isEmpty()){
       	        	tsoMess.setEndTime(endTime);
	        	}
	        	testScaleOrderNum = service.getTestScaleOrderNumByCondition(tsoMess);
	        
	        }

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("numberOfTest", numberOfTest);
    	result.put("testScaleNum", testScaleNum);
    	result.put("testScaleOrderNum", testScaleOrderNum);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		result.put("testScaleOrderList", list);
		return result;
        
    }
    
    @RequestMapping(value="/exportOrderMessage",method=RequestMethod.GET)
    public void exportOrderMessage(TestScaleOrderMessage tsoMessage, HttpServletResponse response){
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleOrderMessage> list = new ArrayList<TestScaleOrderMessage>();
    	try{

//    		Integer pageIndex = tsoMessage.getPageIndex();
//    		Integer pageSize = tsoMessage.getPageSize();
//		    if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
//		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//				return;
//		    }
//    		
//		    PageHelper.startPage(pageIndex, pageSize);
    		
		    //导出数据，但数据限制在800
	        list = service.getAllMessageByLimit(tsoMessage);
	        
	        if(list!=null&&list.size()>0){
	        	
	        	//获取维度常模快照信息
	        	for(int i=0;i<list.size();i++){
	        		TestScaleOrderMessage tsOrderMessage = list.get(i);
	        		TestScaleOrderDimensionNormMessage tsodnMessage = new TestScaleOrderDimensionNormMessage();
	        		tsodnMessage.setTestScaleOrderId(tsOrderMessage.getId());
	        		List<TestScaleOrderDimensionNormMessage> tsodnList = tsodnService.getAllMessage(tsodnMessage);
	        		tsOrderMessage.setTsodnList(tsodnList);
	        	}
	        	

	    		response.setContentType("application/vnd.ms-excel");  
	    		String codedFileName;
	    		try
	    		{
	    			codedFileName = java.net.URLEncoder.encode("心理测试流水", "UTF-8");
	    		} catch (UnsupportedEncodingException e1)
	    		{
	    			codedFileName = "TestScaleOrderList";
	    		} 
	    		
	    		response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xlsx");  
	    				
	    		OutputStream  fOut = null;
	    		try{
	    			Workbook wb = new XSSFWorkbook();
//	    	        CreationHelper createHelper = wb.getCreationHelper();
	    	        Sheet sheet = wb.createSheet("TSOLIST");
	    	        
	    	        //设置列宽
	    	        sheet.setColumnWidth(0, 20*256);
	    	        sheet.setColumnWidth(1, 20*256);
	    	        sheet.setColumnWidth(2, 20*256);
	    	        sheet.setColumnWidth(3, 20*256);
	    	        sheet.setColumnWidth(4, 20*256);
	    	        sheet.setColumnWidth(5, 20*256);
	    	        sheet.setColumnWidth(6, 20*256);
	    	        sheet.setColumnWidth(7, 20*256);
	    	        sheet.setColumnWidth(8, 60*256);
	    	        sheet.setColumnWidth(9, 20*256);
	    	        
	    	        
	    	        //填写列名
	    	        Row row = sheet.createRow((short)0);
	    	        row.createCell(0).setCellValue("订单流水号");
	    	        row.createCell(1).setCellValue("用户昵称");
	    	        row.createCell(2).setCellValue("性别");
	    	        row.createCell(3).setCellValue("年龄");
	    	        row.createCell(4).setCellValue("用户手机号");
	    	        row.createCell(5).setCellValue("所测量表");
	    	        row.createCell(6).setCellValue("量表价格");
	    	        row.createCell(7).setCellValue("定性名称");
	    	        row.createCell(8).setCellValue("维度：常模：分数");
	    	        row.createCell(9).setCellValue("测试时间");
	    	        
	    	        //填写信息
	    	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    	       
	    	        for(int i=0; i< list.size(); i++)
	    	        {
	    	        	TestScaleOrderMessage tMess = list.get(i);
	    	        	row = sheet.createRow(i+1);
		    	        row.setHeightInPoints(100);
	    		        row.createCell(0).setCellValue(tMess.getId());
	    		        if(tMess.getUserName()!=null){
	    		        	row.createCell(1).setCellValue(tMess.getUserName());
	    		        }else{
	    		        	row.createCell(1).setCellValue("");
	    		        }
	    		        
	    		        row.createCell(2).setCellValue(tMess.getSex());
	    		        row.createCell(3).setCellValue(tMess.getBirthday());
	    		        if(tMess.getTelephone()!=null){
	    		        	row.createCell(4).setCellValue(tMess.getTelephone());
	    		        }else{
	    		        	row.createCell(4).setCellValue("");
	    		        }
	    		        
	    		        row.createCell(5).setCellValue(tMess.getRelateTestScaleName());
		    	        row.createCell(6).setCellValue(tMess.getRelateTestScalePrice());
		    	        row.createCell(7).setCellValue(tMess.getRelateQualitativeName());
		    	        String dnsStr = "";
		    	        List<TestScaleOrderDimensionNormMessage> tList = tMess.getTsodnList();
		    	        for(int j=0;j<tList.size();j++){
		    	        	TestScaleOrderDimensionNormMessage tsodnMess = tList.get(j);
		    	        	String dimensionName = tsodnMess.getDimensionName();
		    	        	Float normHighScore = tsodnMess.getNormHighScore();
		    	        	Float normLowScore = tsodnMess.getNormLowScore();
		    	        	Float dimensionScore = tsodnMess.getDimensionScore();
		    	        	Integer isNormal = tsodnMess.getIsNormal();
		    	        	String isNormalStr = "";
		    	        	if(TestScaleOrderDimensionNormMessage.IS_NORMAL==isNormal){
		    	        		isNormalStr = "正常";
		    	        	}else if(TestScaleOrderDimensionNormMessage.IS_NOT_NORMAL==isNormal){
		    	        		isNormalStr = "异常";
		    	        	}
		    	        	dnsStr+="维度名字："+dimensionName+"；"+"常模："+normLowScore+"-"+normHighScore+
		    	        			"；维度得分："+dimensionScore+"；"+isNormalStr+"  \r\n";
		    	        }
		    	        row.createCell(8).setCellValue(dnsStr);
		    	        row.createCell(9).setCellValue(sdf.format(tMess.getCreateTime()));
	    	        }
	    	        	       	        
	    	        fOut = response.getOutputStream();  
	    	        wb.write(fOut);
	    		}catch (Exception e)
	    		{
	    			log.error(ErrorCode.ERROR_EXPORT_TEST_SCALE_ORDER.getMessage(), e);
	    			result.setCode(ErrorCode.ERROR_EXPORT_TEST_SCALE_ORDER.getCode());
	    			result.setMsg(ErrorCode.ERROR_EXPORT_TEST_SCALE_ORDER.getMessage());
	    		}finally
	    		{
	    			if(fOut != null)
	    			{
	    				try
	    				{
	    					fOut.flush();
	    					fOut.close();
	    				} catch (IOException e)
	    				{
	    					// TODO Auto-generated catch block
	    					log.error(ErrorCode.ERROR_EXPORT_TEST_SCALE_ORDER.getMessage(), e);
	    				}
	    			}
	    		}	
	        }

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
    	}
        
    }
    
    
    @RequestMapping(value = "add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(TestScaleOrderMessage testScaleOrderMessage, HttpServletRequest request){
         
    	ResultEntity result = new ResultEntity(); 
    	try{
	        
	        Long telephone = testScaleOrderMessage.getTelephone();
	        String sex = testScaleOrderMessage.getSex();
	        String relateQualitativeName = testScaleOrderMessage.getRelateQualitativeName();
	        String relateTestScaleName = testScaleOrderMessage.getRelateTestScaleName();
	        
		    if(telephone==null || telephone==0|| 
		       sex==null || sex.isEmpty()|| 
		       relateQualitativeName==null || relateQualitativeName.isEmpty()|| 
		       relateTestScaleName==null || relateTestScaleName.isEmpty()){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    Date currentTime = new Date();
	        testScaleOrderMessage.setIsDelete(0);
	        testScaleOrderMessage.setIsEnable(0);
	        testScaleOrderMessage.setBirthday(currentTime);
	        testScaleOrderMessage.setCreateTime(currentTime);
	        testScaleOrderMessage.setUpdateTime(currentTime);
	     	
	        service.addMessage(testScaleOrderMessage);
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleOrderId", testScaleOrderMessage.getId());
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(TestScaleOrderMessage testScaleOrderMessage,HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	int updateNum = 0;
    	
    	try{
	        Integer id = testScaleOrderMessage.getId();
		    if(id==null || id==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    Date currentTime = new Date();
	        testScaleOrderMessage.setUpdateTime(currentTime);
	    	
	        updateNum = service.updateMess(testScaleOrderMessage);
	    	
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
    	result.put("updateNum", updateNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
}

