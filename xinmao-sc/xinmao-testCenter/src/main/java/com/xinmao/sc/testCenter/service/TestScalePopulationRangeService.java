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

import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleNormMessage;
import com.xinmao.sc.testCenter.domain.TestScalePopulationRangeMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.mapper.TestScaleInfoMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleNormMapper;
import com.xinmao.sc.testCenter.mapper.TestScalePopulationRangeMapper;



@Service
public class TestScalePopulationRangeService {
	
//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TestScalePopulationRangeMapper mapper;
    
    @Autowired
    private TestScaleNormMapper tsnMapper;
    
    @Autowired
    private TestScaleInfoMapper tsiMapper;
    
    
    
//    public List<TestScalePopulationRangeMessage> getMessageById(int id){
//         List<TestScalePopulationRangeMessage> list = mapper.getMessById(id);
//         return list;
//    }
    
    public List<TestScalePopulationRangeMessage> getAllMessage(TestScalePopulationRangeMessage testScalePopulationRangeMessage){
        List<TestScalePopulationRangeMessage> list = mapper.selectAll(testScalePopulationRangeMessage);
        return list;
   }
    
    @Transactional
    public Boolean addMessage(TestScaleInfoMessage testScaleInfoMessage) {
    	try{
	    	Boolean isSuccess = false;
	    	
	    	Integer testScaleId = testScaleInfoMessage.getId();
	    	
	    	//判断相关量表是否存在
	    	TestScaleInfoMessage tsiMessage = tsiMapper.getMessById(testScaleId);
	    	if(tsiMessage == null){
	    		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	    	}
	    	
	    	List<TestScalePopulationRangeMessage> tsprList = testScaleInfoMessage.getTsprList();
	    	if(tsprList!=null&&tsprList.size()>0){
	    		
	    		judgeTestScalePopulationRange(tsprList);
	    		
	    		Date currentTime = new Date();
	    		
	    		for(int i=0;i<tsprList.size();i++){
	    			TestScalePopulationRangeMessage tsprMessage = tsprList.get(i);
	    			tsprMessage.setRelateTestScaleId(testScaleId);
	    			tsprMessage.setIsDelete(0);
	    			tsprMessage.setIsEnable(0);
	    			tsprMessage.setCreateTime(currentTime);
	    			tsprMessage.setUpdateTime(currentTime);
	    	        mapper.insertMess(tsprMessage);
	    	        int inserId = tsprMessage.getId();
	    	        if(inserId == 0){
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
    
    @Transactional
    public ResultEntity updateMess(TestScaleInfoMessage testScaleInfoMessage) {
    	
    	try{
		     ResultEntity result = new ResultEntity(); 
		     
		     int testScaleId = testScaleInfoMessage.getId();
		     Date currentTime = new Date();
		     
		     
		     //获取修改的人群范围
	    	 List<TestScalePopulationRangeMessage> updateTsprList = testScaleInfoMessage.getTsprList();
	    	 
	    	 if(updateTsprList!=null&&updateTsprList.size()>0){
	    		 
	//    		 //判断人群范围下是否有常模，如果有，就不允许修改
	//    		 for(int i=0;i<updateTsprList.size();i++){
	//    			 TestScalePopulationRangeMessage tsprMessage = updateTsprList.get(i);
	//    			 Integer tsprId = tsprMessage.getId();
	//    			 if(tsprId!=null && tsprId>0){
	//    		 		 TestScaleNormMessage tsnMessage = new TestScaleNormMessage();
	//    	    		 tsnMessage.setPopulationRangeId(tsprId);
	//    	    		 List<TestScaleNormMessage>  tsnList = tsnMapper.selectAll(tsnMessage);
	//    	    		 if(tsnList!=null && tsnList.size()>0){
	//    	 	    		result.setCode(ErrorCode.ERROR_POPULATION_RANGE_HAVE_NORM.getCode());
	//    	 				result.setMsg(ErrorCode.ERROR_POPULATION_RANGE_HAVE_NORM.getMessage());
	//    	 				return result;
	//    	    		 }
	//    				 
	//    			 }
	//    			 
	//    		 }
	    	     
	             judgeTestScalePopulationRange(updateTsprList);
	    	 }
		     
		     //获取原始的人群范围
		     TestScalePopulationRangeMessage tsprMes = new TestScalePopulationRangeMessage();
		     tsprMes.setRelateTestScaleId(testScaleId);
		     List<TestScalePopulationRangeMessage> oriTsprList = mapper.selectAll(tsprMes);
		     
		     
		     //判断原先数据库中是否有人群范围，如果有，就先添加新增的人群范围，再删除原先的人群范围。
		     if(oriTsprList!=null && oriTsprList.size()>0){
		     		
		     		//获取原先关联的人群范围id的list
		   			List<Integer> oriTsprIdList = new ArrayList<Integer>();
		   			for(int i=0;i<oriTsprList.size();i++){
		   				oriTsprIdList.add(oriTsprList.get(i).getId());
		   			}
		     		
		   			List<Integer> repetitiveIdList  = new ArrayList<Integer>();
		   			
		   		    //原有的人群范围保持不变,添加新增的人群范围
		       	    if(updateTsprList!=null && updateTsprList.size()>0){
		 	        	for(int j=0;j<updateTsprList.size();j++){
		 	        		
		 	        		TestScalePopulationRangeMessage updateTsprMessage = updateTsprList.get(j);
		 	        		  
		 	        		  Integer updateTsprId = updateTsprMessage.getId();
		 	        		  
		 	        		  if(!oriTsprIdList.contains(updateTsprId)){
		 			        		
		 	        			    updateTsprMessage.setRelateTestScaleId(testScaleId);
		 	        			    updateTsprMessage.setIsDelete(0);
		 	        			    updateTsprMessage.setIsEnable(0);
		 	        			    updateTsprMessage.setCreateTime(currentTime);
		 	        			    updateTsprMessage.setUpdateTime(currentTime);
		 	        			    mapper.insertMess(updateTsprMessage);
		 			        		int insertId = updateTsprMessage.getId();
		 			        		if(insertId == 0){
		 			        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		 			        		}
		 			        	}else{ //修改原有的人群范围
		 			        		repetitiveIdList.add(updateTsprId);
		 			        		
		 			        		updateTsprMessage.setUpdateTime(currentTime);
		 			        		int updateNumber = mapper.updateMess(updateTsprMessage);
		 			        		if(updateNumber == 0){
		 			        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		 			        		}
		 			        	}
		 	        	}
		       	    }
		       	    
		       	    //删除去掉的人群范围
		 		    List<Integer> delIdList  = new ArrayList<Integer>();
		 		    for(int k=0;k<oriTsprList.size();k++){
		 		    	  TestScalePopulationRangeMessage oriMes = oriTsprList.get(k);
		 		    	  int oriTsprId = oriMes.getId();
		 		    	  if(!repetitiveIdList.contains(oriTsprId)){
		 		    		  delIdList.add(oriTsprId);
		 		    	  }
		 		    }
		 		      
		 		    int delIdListSize = delIdList.size();
		 		    if(delIdList!=null && delIdListSize>0){
		 		    	  TestScalePopulationRangeMessage delTsprMes = new TestScalePopulationRangeMessage();
		 		    	  delTsprMes.setSearchList(delIdList);
		 		    	  delTsprMes.setIsDelete(1);
		 		    	  delTsprMes.setUpdateTime(currentTime);
		 				  int delNum = mapper.batchDeleteMess(delTsprMes);
		 			      if(delNum < delIdListSize){
		 	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		 	        	  }
		 			      
		 			     //同时删除该人群范围下的常模
		 			     TestScaleNormMessage  tsnMessage = new TestScaleNormMessage();
		 			     tsnMessage.setSearchList(delIdList);
		 			     tsnMessage.setIsDelete(1);
		 			     tsnMessage.setUpdateTime(currentTime);
		 			     tsnMapper.batchDeleteMessByPopulationRangeId(tsnMessage);
		 			    
		 		    }
		
	         }else{ //判断原先数据库中是否有人群范围，如果没有，就直接添加新的人群范围就行。
	 			
	     	    //原有的人群范围保持不变,添加新增的人群范围
	     	    if(updateTsprList!=null && updateTsprList.size()>0){
	 		        for(int j=0;j<updateTsprList.size();j++){
	 		        	TestScalePopulationRangeMessage updateTsprMessage = updateTsprList.get(j);
	 		        	
	 		        	updateTsprMessage.setRelateTestScaleId(testScaleId);
	 		        	updateTsprMessage.setIsDelete(0);
	 		        	updateTsprMessage.setIsEnable(0);
	 		        	updateTsprMessage.setCreateTime(currentTime);
	 		        	updateTsprMessage.setUpdateTime(currentTime);
	 		        	mapper.insertMess(updateTsprMessage);
	 	        		int addId = updateTsprMessage.getId();
	 	        		if(addId == 0){
	 	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	 	        		}
	 	        		
	 		        }
	     	    }
	 		}
	    	 
	    	
	    	result.put("isSuccess", true);
			result.setCode(ErrorCode.SUCCESS.getCode());
			result.setMsg(ErrorCode.SUCCESS.getMessage());
		    return result;
	    
     	}catch(Exception e){
    		log.error(e.getMessage(), e);
    		throw new RuntimeException(e.getMessage(),e);
    	}
        
    }

	private void judgeTestScalePopulationRange(List<TestScalePopulationRangeMessage> tsprList) {
		//判断性别与年龄不能为空,且必须符合逻辑
	     for(int i=0;i<tsprList.size();i++){
    			
    			TestScalePopulationRangeMessage tsprMessage = tsprList.get(i);
    			String sex = tsprMessage.getSex();
    			Integer lowerAgeLimit = tsprMessage.getLowerAgeLimit();
    			Integer upperAgeLimit = tsprMessage.getUpperAgeLimit();
    			if(sex==null||sex.isEmpty()||
    					lowerAgeLimit==null||lowerAgeLimit<0||
    					upperAgeLimit==null||upperAgeLimit<0||
    					lowerAgeLimit>=upperAgeLimit){
    				throw new RuntimeException(ErrorCode.ERROR_POPULATION_RANGE_REQUIRED.getMessage());
    			}
    	  }
    		
	      //修改人群范围的时候，必须要判断不能重合
    	  for(int i=0;i<tsprList.size();i++){
    		  
    		  TestScalePopulationRangeMessage tsprMessage = tsprList.get(i);
  			  String sex = tsprMessage.getSex();
  			  Integer lowerAgeLimit = tsprMessage.getLowerAgeLimit();
  			  Integer upperAgeLimit = tsprMessage.getUpperAgeLimit();
    		  
    			for(int j=0;j<tsprList.size();j++){
    				if(i!=j){
    					TestScalePopulationRangeMessage tspRangeMessage = tsprList.get(j);
    	    			String compareSex = tspRangeMessage.getSex();
    	    			Integer compareLowerAgeLimit = tspRangeMessage.getLowerAgeLimit();
    	    			Integer compareUpperAgeLimit = tspRangeMessage.getUpperAgeLimit();
    	    			if(sex.equals(compareSex) && 
                           lowerAgeLimit < compareUpperAgeLimit && 
                           upperAgeLimit > compareLowerAgeLimit){
    	    				 throw new RuntimeException(ErrorCode.ERROR_POPULATION_RANGE_COINCIDENCE.getMessage());
    	    			}
    				}
    			}
    	  }
	}
}
