package com.xinmao.sc.testCenter.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinmao.sc.testCenter.domain.ConditionsOfQualitativeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleQualitativeMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.mapper.ConditionsOfQualitativeMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleDimensionMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleInfoMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleQualitativeMapper;

@Service
public class TestScaleQualitativeService {
	
//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TestScaleQualitativeMapper mapper;
    
    @Autowired
    private TestScaleInfoMapper tsiMapper;
    
    @Autowired
    private ConditionsOfQualitativeMapper coqMapper;
    
    @Autowired
    private TestScaleDimensionMapper tsdMapper;
    
    @Autowired
    private TestScaleInfoService tsiService;
    
    
    public List<TestScaleQualitativeMessage> getMessageByTestScaleId(Integer testScaleId){
    	
    	TestScaleQualitativeMessage tsqMess = new TestScaleQualitativeMessage();
    	tsqMess.setRelateTestScaleId(testScaleId);
    	List<TestScaleQualitativeMessage> tsqList =  mapper.getMessageByTestScaleId(tsqMess);
    	
    	//获取定性成立条件
    	if(tsqList!=null && tsqList.size()>0){
    		for(int i=0;i<tsqList.size();i++){
    			TestScaleQualitativeMessage tsqMessage = tsqList.get(i);
    			Integer qualitativeId = tsqMessage.getId();
    			ConditionsOfQualitativeMessage coqMessage = new ConditionsOfQualitativeMessage();
    			coqMessage.setTestScaleQualitativeId(qualitativeId);
    			List<ConditionsOfQualitativeMessage> coqList = coqMapper.selectAll(coqMessage);
    			if(coqList!=null && coqList.size()>0){
    				tsqMessage.setCoqList(coqList);
    			}
    		}
    	}
    	
    	return tsqList;
        
   }
    
 public List<TestScaleQualitativeMessage> selectAll(Integer testScaleId){
    	
    	TestScaleQualitativeMessage tsqMess = new TestScaleQualitativeMessage();
    	tsqMess.setRelateTestScaleId(testScaleId);
    	return  mapper.selectAll(tsqMess);
   }
    
    public TestScaleQualitativeMessage getMessageByIdFromFront(int qualitativeId){
    	
    	return mapper.getMessById(qualitativeId);
        
   }
    
    
    public TestScaleQualitativeMessage getMessById(int qualitativeId){
        return mapper.getMessById(qualitativeId);
    }
    
    public TestScaleQualitativeMessage getMessageById(int qualitativeId){
    	
         TestScaleQualitativeMessage tsqMessage = mapper.getMessById(qualitativeId);
         
         //判断定性是否存在
         if(tsqMessage==null){
        	 throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
         }
         
         Integer testScaleId = tsqMessage.getRelateTestScaleId();
         
  		
         //查询定性成立条件数据
         ConditionsOfQualitativeMessage coqMessage = new ConditionsOfQualitativeMessage();
		 coqMessage.setTestScaleQualitativeId(qualitativeId);
		 List<ConditionsOfQualitativeMessage> coqList = coqMapper.selectAll(coqMessage);
		 if(coqList!=null && coqList.size()>0){
			 tsqMessage.setCoqList(coqList);
		 }
         
		 //查询所有维度
		 TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
		 tsdMessage.setRelateTestScaleId(testScaleId);
		 List<TestScaleDimensionMessage> dimensionList = tsdMapper.selectAll(tsdMessage);
		 tsqMessage.setAllTsdList(dimensionList);
		 
         return tsqMessage;
    }
    
    public List<TestScaleQualitativeMessage> getAllMessage(TestScaleQualitativeMessage tsqMessage){
    	Integer testScaleId =tsqMessage.getRelateTestScaleId();
    	
    	//判断此量表是否存在
    	TestScaleInfoMessage tsiMessage = tsiMapper.getMessById(testScaleId);
    	if(tsiMessage==null){
    		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
    	}
    	
        List<TestScaleQualitativeMessage> list = mapper.selectAll(tsqMessage);
        if(list!=null&&list.size()>0){
        	for(int i=0;i<list.size();i++){
        		TestScaleQualitativeMessage tsQualitativeMessage = list.get(i);
        		
        		List<String> coqStrList = new ArrayList<String>();
        		ConditionsOfQualitativeMessage coqMessage = new ConditionsOfQualitativeMessage();
        		coqMessage.setTestScaleQualitativeId(tsQualitativeMessage.getId());
        		List<ConditionsOfQualitativeMessage> coqList = coqMapper.selectAll(coqMessage);
        		if(coqList!=null && coqList.size()>0){
        			for(int j=0;j<coqList.size();j++){
        				ConditionsOfQualitativeMessage coQualitativeMessage = coqList.get(j);
//        				Float leftValue = coQualitativeMessage.getLeftValue();
//        				Integer leftType = coQualitativeMessage.getLeftType();
//        				Float rightValue = coQualitativeMessage.getRightValue();
//        				Integer rightType = coQualitativeMessage.getRightType();
        				Integer leftDimensionId = coQualitativeMessage.getLeftDimensionId();
        				Integer rightDimensionId = coQualitativeMessage.getRightDimensionId();
        				Float rightScore = coQualitativeMessage.getRightScore();
        				Integer leftRightRelation = coQualitativeMessage.getLeftRightRelation();
        				String leftValueStr = "";
                        String rightValueStr = "";
                        String leftRightRelationStr = "";
                        String coqStr = "";
                        
        				//取左侧
//                        if(leftType!=null){
//                		    if(leftType==ConditionsOfQualitativeMessage.LEFT_TYPE_DIMENSION){
//                		    	if(leftValue!=null){
//                			    	TestScaleDimensionMessage tsdMessage = tsdMapper.getMessById(leftValue.intValue()); 
//                			    	if(tsdMessage!=null){
//                			    		leftValueStr = tsdMessage.getName();
//                			    	}
//                		    	}
//            			    }else if(leftType==ConditionsOfQualitativeMessage.LEFT_TYPE_SCORE){
//            			    	leftValueStr = leftValue+"";
//            			    }else{
//            			    	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//            			    }
//                        }
                        if(leftDimensionId!=null && leftDimensionId>0){
                        	TestScaleDimensionMessage tsdMessage = tsdMapper.getMessById(leftDimensionId); 
        			    	if(tsdMessage!=null){
        			    		leftValueStr = tsdMessage.getName();
        			    	}
                        }else{
        			    	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
        			    }
        	
        			    
        			    //取右侧
//                        if(rightType!=null ){
//                		    if(rightType==ConditionsOfQualitativeMessage.RIGHT_TYPE_DIMENSION){
//                		    	if(rightValue!=null){
//                		    	  	TestScaleDimensionMessage tsdMessage = tsdMapper.getMessById(rightValue.intValue()); 
//                			    	if(tsdMessage!=null){
//                			    		rightValueStr = tsdMessage.getName();
//                			    	}
//                		    	}
//            			    }else if(rightType==ConditionsOfQualitativeMessage.RIGHT_TYPE_SCORE){
//            			    	rightValueStr = rightValue+"";
//            			    }else{
//            			    	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//            			    }
//                        }
        	
                        if(rightDimensionId!=null && rightDimensionId>0){
                         	TestScaleDimensionMessage tsdMessage = tsdMapper.getMessById(rightDimensionId); 
                			if(tsdMessage!=null){
                				rightValueStr = tsdMessage.getName();
                			}
                        }else if (rightScore!=null){
                        	rightValueStr = rightScore+"";
                        }else{
        			    	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
        			    }
        			    
        			    
        			    //取左右侧关系
                        if(leftRightRelation!=null){
            			    if(leftRightRelation == ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_GREATER){
            			    	leftRightRelationStr = ">";
            			    }else if(leftRightRelation == ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_GREATER_EQUAL){
            			    	leftRightRelationStr = ">=";
            			    }else if(leftRightRelation == ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_EQUAL){
            			    	leftRightRelationStr = "=";
            			    }else if(leftRightRelation == ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_LESS_EQUAL){
            			    	leftRightRelationStr = "<=";
            			    }else if(leftRightRelation == ConditionsOfQualitativeMessage.LEFT_RIGHT_RELATION_LESS){
            			    	leftRightRelationStr = "<";
            			    }else{
            			    	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
            			    }
                        }
        			    
        			    //拼成定性成立条件字符串
        			    coqStr = leftValueStr+leftRightRelationStr+rightValueStr;
        			    coqStrList.add(coqStr);
        				
        			}
        			
        			tsQualitativeMessage.setCoqStrList(coqStrList);
        				
        		}
        		
        	}
        	
        }
        
        return list;
   }
    
    @Transactional
    public Boolean addMessage(TestScaleQualitativeMessage testScaleQualitativeMessage,Integer testScaleId) {
    	try{
	    	Boolean isSuccess = false;
	    	
	    	isRepeatQualitativeWhenAdd(testScaleQualitativeMessage,testScaleId);
	    	
	    	//插入定性数据
		    Date currentTime = new Date();
	        testScaleQualitativeMessage.setIsDelete(0);
	        testScaleQualitativeMessage.setIsEnable(0);
	        testScaleQualitativeMessage.setCreateTime(currentTime);
	        testScaleQualitativeMessage.setUpdateTime(currentTime);
	    	
	        mapper.insertMess(testScaleQualitativeMessage);
	        
	        int insertQualitativeId = testScaleQualitativeMessage.getId();
	        if(insertQualitativeId == 0){
	        	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	        }
	        
	        //插入定性成立条件数据
	        List<ConditionsOfQualitativeMessage> coqList = testScaleQualitativeMessage.getCoqList();
	        if(coqList!=null && coqList.size()>0){
		        for(int i=0;i<coqList.size();i++){
		        	ConditionsOfQualitativeMessage coqMessage = coqList.get(i);
		        	
		        	
		        	//判断定性条件：leftDimensionId 不能为空， rightDimensionId与rightScore不能全为空
		        	Integer leftDimensionId = coqMessage.getLeftDimensionId();
		        	Integer rightDimensionId = coqMessage.getRightDimensionId();
		        	Float rightScore = coqMessage.getRightScore();
		        	if(leftDimensionId ==null ||leftDimensionId == 0 ||
		        			((rightDimensionId ==null || rightDimensionId == 0)&& rightScore ==null)){
		        		throw new RuntimeException(ErrorCode.ERROR_CONDITION_OF_QUALITATIVE_IS_NULL.getMessage());
		        	}
		        	
		        	coqMessage.setTestScaleQualitativeId(insertQualitativeId);
		        	coqMessage.setIsEnable(0);
		        	coqMessage.setIsDelete(0);
		        	coqMessage.setCreateTime(currentTime);
		        	coqMessage.setUpdateTime(currentTime);
		        	
		        	coqMapper.insertMess(coqMessage);
		        	int insertCoqId = coqMessage.getId();
		        	if(insertCoqId == 0){
		             	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
		            }    
		        }
	        }
	        
	        isSuccess = true;
	        return isSuccess;
        
    	}catch(Exception e){
    		log.error(e.getMessage(), e);
    		throw new RuntimeException(e.getMessage(),e);
    	}
        
    }

    //如果是添加为前提定性或其他定性，需要判断数据库中是否已经有前提定性或者其他定性，
    //因为前提定性或其他定性一个量表只能创建一个。
	private void isRepeatQualitativeWhenAdd(TestScaleQualitativeMessage testScaleQualitativeMessage,Integer testScaleId) {
		Integer qualitativeType = testScaleQualitativeMessage.getQualitativeType();
    	if(qualitativeType !=null){
    		if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_PREMISE == qualitativeType){
    			
    			TestScaleQualitativeMessage tsqMess = new TestScaleQualitativeMessage();
    			tsqMess.setRelateTestScaleId(testScaleId);
    			List<TestScaleQualitativeMessage> tsqMessList = mapper.selectAll(tsqMess);
    			if(tsqMessList!=null && tsqMessList.size()>0){
    				for(int i=0;i<tsqMessList.size();i++){
    					TestScaleQualitativeMessage tsqMessage = tsqMessList.get(i);
    					if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_PREMISE == tsqMessage.getQualitativeType()){
    						throw new RuntimeException(ErrorCode.ERROR_HAVE_PREMISE_QUALITATIVE.getMessage());
    					}
    				}
    			}
    		}else if(qualitativeType == TestScaleQualitativeMessage.QUALITATIVE_TYPE_OTHER){
    			
    			TestScaleQualitativeMessage tsqMess = new TestScaleQualitativeMessage();
    			tsqMess.setRelateTestScaleId(testScaleId);
    			List<TestScaleQualitativeMessage> tsqMessList = mapper.selectAll(tsqMess);
    			if(tsqMessList!=null && tsqMessList.size()>0){
    				for(int i=0;i<tsqMessList.size();i++){
    					TestScaleQualitativeMessage tsqMessage = tsqMessList.get(i);
    					if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_OTHER == tsqMessage.getQualitativeType()){
    						throw new RuntimeException(ErrorCode.ERROR_HAVE_OTHER_QUALITATIVE.getMessage());
    					}
    				}
    			}
        	}
    	}
	}
	
	//如果是修改为前提定性或其他定性，需要判断数据库中是否已经有前提定性或者其他定性，
    //因为前提定性或其他定性一个量表只能创建一个。
	private void isRepeatQualitativeWhenUpdate(TestScaleQualitativeMessage testScaleQualitativeMessage,Integer testScaleId) {
		Integer qualitativeType = testScaleQualitativeMessage.getQualitativeType();
		Integer qualitativeId = testScaleQualitativeMessage.getId();
    	if(qualitativeType !=null && qualitativeId!=null && qualitativeId>0){
    		if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_PREMISE == qualitativeType){
    			
    			TestScaleQualitativeMessage tsqMess = new TestScaleQualitativeMessage();
    			tsqMess.setRelateTestScaleId(testScaleId);
    			List<TestScaleQualitativeMessage> tsqMessList = mapper.selectAll(tsqMess);
    			if(tsqMessList!=null && tsqMessList.size()>0){
    				for(int i=0;i<tsqMessList.size();i++){
    					TestScaleQualitativeMessage tsqMessage = tsqMessList.get(i);
    					if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_PREMISE == tsqMessage.getQualitativeType() 
    							&& qualitativeId != tsqMessage.getId()){
    						throw new RuntimeException(ErrorCode.ERROR_HAVE_PREMISE_QUALITATIVE.getMessage());
    					}
    				}
    			}
    		}else if(qualitativeType == TestScaleQualitativeMessage.QUALITATIVE_TYPE_OTHER){
    			
    			TestScaleQualitativeMessage tsqMess = new TestScaleQualitativeMessage();
    			tsqMess.setRelateTestScaleId(testScaleId);
    			List<TestScaleQualitativeMessage> tsqMessList = mapper.selectAll(tsqMess);
    			if(tsqMessList!=null && tsqMessList.size()>0){
    				for(int i=0;i<tsqMessList.size();i++){
    					TestScaleQualitativeMessage tsqMessage = tsqMessList.get(i);
    					if(TestScaleQualitativeMessage.QUALITATIVE_TYPE_OTHER == tsqMessage.getQualitativeType()
    							&& qualitativeId != tsqMessage.getId()){
    						throw new RuntimeException(ErrorCode.ERROR_HAVE_OTHER_QUALITATIVE.getMessage());
    					}
    				}
    			}
        	}
    	}
	}
	
    
    @Transactional
    public Boolean updateMess(TestScaleQualitativeMessage testScaleQualitativeMessage,Integer testScaleId) {
    	try{
	    	Boolean isSuccess = false; 
	
	    	Integer qualitativeId = testScaleQualitativeMessage.getId();
	    	
	    	isRepeatQualitativeWhenUpdate(testScaleQualitativeMessage,testScaleId);
	    	
	        Date currentTime = new Date();
	        testScaleQualitativeMessage.setRelateTestScaleId(testScaleId);
	        testScaleQualitativeMessage.setUpdateTime(currentTime);
	        int updateNum = mapper.updateMess(testScaleQualitativeMessage);
	    	if(updateNum == 0){
	    		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	    	}
	        
	    	//获取修改的定性成立条件
	    	List<ConditionsOfQualitativeMessage> updateCoqList = testScaleQualitativeMessage.getCoqList();
	       
	    	
	        //获取原始的定性成立条件
	    	ConditionsOfQualitativeMessage coqMess = new ConditionsOfQualitativeMessage();
	    	coqMess.setTestScaleQualitativeId(qualitativeId);
	    	List<ConditionsOfQualitativeMessage> oriCoqList = coqMapper.selectAll(coqMess);
	    	
	    
	    	//判断原先数据库中是否有定性成立条件，如果有，就先添加新增的定性成立条件，再删除原先的定性成立条件。
	    	if(oriCoqList!=null && oriCoqList.size()>0){
	    		
	    		
	    		//获取原先关联的定性成立条件id的list
	  			List<Integer> oriCoqIdList = new ArrayList<Integer>();
	  			for(int i=0;i<oriCoqList.size();i++){
	  				oriCoqIdList.add(oriCoqList.get(i).getId());
	  			}
	    		
	  			List<Integer> repetitiveIdList  = new ArrayList<Integer>();
	  			
	  		    //原有的定性成立条件保持不变,添加新增的定性成立条件
	      	    if(updateCoqList!=null && updateCoqList.size()>0){
		        	for(int j=0;j<updateCoqList.size();j++){
		        		
		        		  ConditionsOfQualitativeMessage updateCoqMessage = updateCoqList.get(j);
		        		  
		        		  Integer updateCoqId = updateCoqMessage.getId();
		        		  
		        		  if(!oriCoqIdList.contains(updateCoqId)){
				        		
		        			    updateCoqMessage.setTestScaleQualitativeId(qualitativeId);
		        			    updateCoqMessage.setIsDelete(0);
		        			    updateCoqMessage.setIsEnable(0);
		        			    updateCoqMessage.setCreateTime(currentTime);
		        			    updateCoqMessage.setUpdateTime(currentTime);
				        		coqMapper.insertMess(updateCoqMessage);
				        		int insertId = updateCoqMessage.getId();
				        		if(insertId == 0){
				        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
				        		}
				        	}else{ //修改原有的定性成立条件
				        		repetitiveIdList.add(updateCoqId);
				        		
				        		updateCoqMessage.setUpdateTime(currentTime);
				        		int updateNumber = coqMapper.updateMess(updateCoqMessage);
				        		if(updateNumber == 0){
				        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
				        		}
				        	}
		        		  
		        		  
		        	}
	      	    }
	      	    
	      	    //删除去掉的定性成立条件
			    List<Integer> delIdList  = new ArrayList<Integer>();
			    for(int k=0;k<oriCoqList.size();k++){
			    	  ConditionsOfQualitativeMessage oriMes = oriCoqList.get(k);
			    	  int oriCoqId = oriMes.getId();
			    	  if(!repetitiveIdList.contains(oriCoqId)){
			    		  delIdList.add(oriCoqId);
			    	  }
			    }
			      
			    int delIdListSize = delIdList.size();
			    if(delIdList!=null && delIdListSize>0){
			    	  ConditionsOfQualitativeMessage delTsoMes = new ConditionsOfQualitativeMessage();
			    	  delTsoMes.setSearchList(delIdList);
			    	  delTsoMes.setIsDelete(1);
			    	  delTsoMes.setUpdateTime(currentTime);
					  int delNum = coqMapper.batchDeleteMess(delTsoMes);
				      if(delNum < delIdListSize){
		        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		        	  }
			    }
		        
	        }else{ //判断原先数据库中是否有定性成立条件，如果没有，就直接添加新的定性成立条件就行。
				
	    	    //原有的定性成立条件保持不变,添加新增的定性成立条件
	    	    if(updateCoqList!=null && updateCoqList.size()>0){
			        for(int j=0;j<updateCoqList.size();j++){
			        	ConditionsOfQualitativeMessage updateCoqMessage = updateCoqList.get(j);
			        	
			        	updateCoqMessage.setTestScaleQualitativeId(qualitativeId);
			        	updateCoqMessage.setIsDelete(0);
			        	updateCoqMessage.setIsEnable(0);
			        	updateCoqMessage.setCreateTime(currentTime);
			        	updateCoqMessage.setUpdateTime(currentTime);
		        		coqMapper.insertMess(updateCoqMessage);
		        		int addId = updateCoqMessage.getId();
		        		if(addId == 0){
		        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		        		}
			        }
	    	    }
			}
	
	        isSuccess = true;
	        return isSuccess;
        
    	}catch(Exception e){
    		log.error(e.getMessage(), e);
    		throw new RuntimeException(e.getMessage(),e);
    	}
    }
    
     @Transactional
	 public ResultEntity deleteMess(TestScaleQualitativeMessage testScaleQualitativeMessage) {
	       
    	 try{
	    	ResultEntity result = new ResultEntity(); 
	    	
	    	Integer tsqId = testScaleQualitativeMessage.getId();
	    	Integer testScaleId = testScaleQualitativeMessage.getRelateTestScaleId();
	    	
	    	//判断量表是否在线上，如果在线上，无法删除定性
	    	if(tsiService.isOnline(testScaleId)){
	    		result.setCode(ErrorCode.ERROR_TESTSCALE_ONLINE.getCode());
				result.setMsg(ErrorCode.ERROR_TESTSCALE_ONLINE.getMessage());
				return result;
	    	}
	    	
	    	Date currentTime = new Date();
	    	
	    	//删除定性
	    	TestScaleQualitativeMessage tsqMessage = new TestScaleQualitativeMessage();
	    	tsqMessage.setId(tsqId);
	    	tsqMessage.setIsDelete(TestScaleQualitativeMessage.IS_DELETE);
	    	tsqMessage.setUpdateTime(currentTime);
	    	int deleteQualitativeId = mapper.updateMess(tsqMessage);
	       	if(deleteQualitativeId==0){
	    		throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	    	}
	    	
	        //删除定性成立条件
	    	ConditionsOfQualitativeMessage coqMessage = new ConditionsOfQualitativeMessage();
	    	coqMessage.setTestScaleQualitativeId(tsqId);
	    	coqMessage.setIsDelete(ConditionsOfQualitativeMessage.IS_DELETE);
	    	coqMessage.setUpdateTime(currentTime);
	    	coqMapper.batchDeleteMessByQualitativeId(coqMessage);
					
	    	result.put("isSuccess", true);
			result.setCode(ErrorCode.SUCCESS.getCode());
			result.setMsg(ErrorCode.SUCCESS.getMessage());
		    return result;
		    
	        
     	}catch(Exception e){
     		log.error(e.getMessage(), e);
     		throw new RuntimeException(e.getMessage(),e);
     	}
	    	
	 }
     
     public List<TestScaleQualitativeMessage> getMessageByIdList(TestScaleQualitativeMessage tsqMessage){
    	 return  mapper.selectAll(tsqMessage);
    }
}

