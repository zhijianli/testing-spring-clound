package com.xinmao.sc.testCenter.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinmao.sc.testCenter.domain.TestScaleDimensionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionTitleRelationMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleTitleMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;
import com.xinmao.sc.testCenter.mapper.TestScaleDimensionMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleDimensionTitleRelationMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleInfoMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleOptionMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleTitleMapper;

@Service
public class TestScaleTitleService {
	
//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
	
    @Autowired
    private TestScaleTitleMapper mapper;
    
    @Autowired
    private TestScaleDimensionMapper tsdMapper;
    
    @Autowired
    private TestScaleDimensionTitleRelationMapper tsdtrMapper;
    
    @Autowired
    private TestScaleOptionMapper tsoMapper;
    
    @Autowired
    private TestScaleInfoMapper tsiMapper;
    
    @Autowired
    private TestScaleInfoService tsiService;
    
//    public TestScaleTitleMessage getMessByTestScaleIdAndTitleNumber(Integer testScaleId,Integer titleNumber){
//    	
//    	TestScaleTitleMessage testScaleTitleMessage = new TestScaleTitleMessage();
//    	
//    	TestScaleTitleMessage tstMessage = new TestScaleTitleMessage(); 
//    	tstMessage.setRelateTestScaleId(testScaleId);
//    	List<Integer> titleIdList = mapper.selectAllId(tstMessage);
//    	Integer titleId = 0;
//    	for(int i=0;i<titleIdList.size();i++){
//    		if(titleNumber == (i+1)){
//    			titleId = titleIdList.get(i);
//    		}
//    	}
//    	if(titleId!=0){
//	    	testScaleTitleMessage = mapper.getMessById(titleId);
//	    	
//	    	if(testScaleTitleMessage != null){
//		    	//获取选项信息
//		    	TestScaleOptionMessage tsoMessage = new TestScaleOptionMessage();
//		    	tsoMessage.setRelateTitleId(titleId);
//		    	List<TestScaleOptionMessage> tsoMesList = tsoMapper.selectAll(tsoMessage);
//		    	if(tsoMesList!=null&&tsoMesList.size()>0){
//		    		testScaleTitleMessage.setTsoList(tsoMesList);
//		    	}
//	    	}
//    	}
//    	
//    	return testScaleTitleMessage;
//    }
    
    
    
    //获取选项信息
	public Map<Integer,TestScaleTitleMessage> getTitleAndOptionInfo(TestScaleOrderMessage tsoMessage) {

		Integer testScaleId = tsoMessage.getRelateTestScaleId();
		List<TestScaleOrderTitleMessage> tsotList = tsoMessage.getTsotList();
		
		//该量表的每道题目选了哪个选项，设置成map
    	Map<Integer,Integer> toIdMap = new HashMap<Integer,Integer>();
    	if(tsotList!=null && tsotList.size()>0){
    		for(int i=0;i<tsotList.size();i++){
    			TestScaleOrderTitleMessage tsoMess = tsotList.get(i);
    			toIdMap.put(tsoMess.getOptionId(),tsoMess.getTestScaleTitleId());
    		}
    	}
    	
		//取出此量表下的所有题目，设置成map
    	Map<Integer,TestScaleTitleMessage> titleMap = new HashMap<Integer,TestScaleTitleMessage>();
    	List<Integer> tstIdList = new ArrayList<Integer>();
    	TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
    	tstMessage.setRelateTestScaleId(testScaleId);
    	List<TestScaleTitleMessage> tstMessageList = mapper.selectAll(tstMessage);
    	if(tstMessageList!=null && tstMessageList.size()>0){
    		for(int i=0;i<tstMessageList.size();i++){
    			TestScaleTitleMessage tsTitleMess = tstMessageList.get(i);
    			Integer tsTitleId = tsTitleMess.getId();
    			tstIdList.add(tsTitleId);
    			titleMap.put(tsTitleId, tsTitleMess);
    		}
    	}
    	
    	//把选择的选项信息放到题目下面
    	TestScaleOptionMessage tsoMess = new TestScaleOptionMessage();
    	tsoMess.setSearchList(tstIdList);
    	List<TestScaleOptionMessage> tsoList = tsoMapper.selectAllByTitleIdList(tsoMess);
    	if(tsoList!=null && tsoList.size()>0){
    		for(int i=0;i<tsoList.size();i++){
    			TestScaleOptionMessage tsOptionMess = tsoList.get(i);
    			Integer tsOptionId = tsOptionMess.getId();
    			if(toIdMap.containsKey(tsOptionId)){
    				Integer titleId = toIdMap.get(tsOptionId);
    				TestScaleTitleMessage tsTitleMess = titleMap.get(titleId);
    				tsTitleMess.setTsoMessage(tsOptionMess);
    			}
    		}
    	}
    	
    	return titleMap;
	}
    
    public List<TestScaleTitleMessage> getAllTitleAndOption(TestScaleTitleMessage testScaleTitleMessage){
    	List<TestScaleTitleMessage> tstList =  mapper.selectAll(testScaleTitleMessage);
    	if(tstList!=null && tstList.size()>0){
    		for(int i=0;i<tstList.size();i++){
        		TestScaleTitleMessage tstMessage = tstList.get(i);
	    		int titleId = tstMessage.getId();
	    		
    		   	//获取选项信息
    	    	getOption(tstMessage, titleId);
    		}
    	}
    	return tstList;
    }

    //获取选项信息
	private void getOption(TestScaleTitleMessage tstMessage, int titleId) {
		TestScaleOptionMessage tsoMessage = new TestScaleOptionMessage();
		tsoMessage.setRelateTitleId(titleId);
		List<TestScaleOptionMessage> tsoMesList = tsoMapper.selectAll(tsoMessage);
		if(tsoMesList!=null&&tsoMesList.size()>0){
			tstMessage.setTsoList(tsoMesList);
		}
	}
    
	public TestScaleTitleMessage getMessById(int titleId){
    	 return mapper.getMessById(titleId);
    }
	
	
    public TestScaleTitleMessage getMessageById(int titleId){
    	TestScaleTitleMessage testScaleTitleMessage = mapper.getMessById(titleId);
    	
    	if(testScaleTitleMessage != null){
    	
	    	//获取与此标题关联的维度信息
	    	TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
			tsdtrMessage.setTestScaleTitleId(titleId);
			List<TestScaleDimensionTitleRelationMessage> tsdtrList = tsdtrMapper.selectAll(tsdtrMessage);
			
			List<Integer> chooseIdList= new ArrayList<Integer>();
			if(tsdtrList!=null&&tsdtrList.size()>0){
	    		for(int i=0;i<tsdtrList.size();i++){
	    			chooseIdList.add(tsdtrList.get(i).getTestScaleDimensionId());
	    		}
			}
	    	
	    	//获取此标题关联量表下面的所有维度信息
	    	TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
	    	tsdMessage.setRelateTestScaleId(testScaleTitleMessage.getRelateTestScaleId());
	    	List<TestScaleDimensionMessage>  allTsdList = tsdMapper.selectAll(tsdMessage);
	    	if(allTsdList!=null && allTsdList.size()>0){
	        	for(int j=0;j<allTsdList.size();j++){
	        		TestScaleDimensionMessage allTsdMessage = allTsdList.get(j);
	        		if(chooseIdList.contains(allTsdMessage.getId())){
	        			allTsdMessage.setIsBelongTo(true);
	        		}else{
	        			allTsdMessage.setIsBelongTo(false);
	        		}
	        	}
	        	testScaleTitleMessage.setTsdList(allTsdList);
	    	}
	
	    	//获取选项信息
	    	getOption(testScaleTitleMessage, titleId);
    	}
    	
    	return testScaleTitleMessage;
    }
    
   public int selectCount(TestScaleTitleMessage testScaleTitleMessage){
        return mapper.selectCount(testScaleTitleMessage);
   }
    
   public List<TestScaleTitleMessage> getAllTitleAndOptionFromEAP(TestScaleTitleMessage testScaleTitleMessage){
    	
    	//获取题目信息
    	List<TestScaleTitleMessage> list =  mapper.selectAll(testScaleTitleMessage);
    	
    	if(list!=null&&list.size()>0){
    		
        	Integer titleOrderNumberBasic = getTitleOrderNumberBasic(testScaleTitleMessage);
        	
    		for(int i=0;i<list.size();i++){
	    		TestScaleTitleMessage tstMessage = list.get(i);
	    		int titleId = tstMessage.getId();
	    		
	        	//获取选项信息
	        	getOption(tstMessage, titleId);
	        	
	        	//设置题目编号
	        	tstMessage.setTitleOrderNumber(titleOrderNumberBasic+i+1);
	        	
    		}
    	}
    	return list;
   }

private Integer getTitleOrderNumberBasic(TestScaleTitleMessage testScaleTitleMessage) {
	Integer pageIndex = testScaleTitleMessage.getPageIndex();
	Integer pageSize = testScaleTitleMessage.getPageSize();
	Integer titleOrderNumberBasic = (pageIndex-1)*pageSize;
	return titleOrderNumberBasic;
}
   
   public List<TestScaleTitleMessage> selectAllBySearchIdList(TestScaleTitleMessage testScaleTitleMessage){
   	
   	//获取题目信息
   	List<TestScaleTitleMessage> list =  mapper.selectAllBySearchIdList(testScaleTitleMessage);
   	
   	if(list!=null&&list.size()>0){
   		
//   		Integer titleOrderNumberBasic = getTitleOrderNumberBasic(testScaleTitleMessage);
   		
   		for(int i=0;i<list.size();i++){
	    		TestScaleTitleMessage tstMessage = list.get(i);
	    		int titleId = tstMessage.getId();
	    		
	        	//获取选项信息
	        	getOption(tstMessage, titleId);
	        	
//	        	//设置题目编号
//	        	tstMessage.setTitleOrderNumber(titleOrderNumberBasic+i+1);
	        	
   		}
   	}
   	return list;
  }
    
    
    public List<TestScaleTitleMessage> searchMessage(TestScaleTitleMessage testScaleTitleMessage){
    	
    	//获取题目信息
    	List<TestScaleTitleMessage> list =  mapper.searchMessage(testScaleTitleMessage);
    	
    	if(list!=null&&list.size()>0){
    		
    		Integer titleOrderNumberBasic = getTitleOrderNumberBasic(testScaleTitleMessage);
    		
    		for(int i=0;i<list.size();i++){
	    		TestScaleTitleMessage tstMessage = list.get(i);
	    		int titleId = tstMessage.getId();
	    		
	        	//获取维度信息
	    		TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
	    		tsdtrMessage.setTestScaleTitleId(titleId);
	    		List<TestScaleDimensionTitleRelationMessage> tsdtrList = tsdtrMapper.selectAll(tsdtrMessage);
	    		
	        	if(tsdtrList!=null&&tsdtrList.size()>0){
	        		List<Integer> searchIdList= new ArrayList<Integer>();
	        		for(int j=0;j<tsdtrList.size();j++){
	        			searchIdList.add(tsdtrList.get(j).getTestScaleDimensionId());
	        		}
	        		
	        		TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
	        		tsdMessage.setSearchList(searchIdList);
	        		List<TestScaleDimensionMessage>  tsdMesList = tsdMapper.batchSelectMessage(tsdMessage);
	        		if(tsdMesList!=null&&tsdMesList.size()>0){
	        			tstMessage.setTsdList(tsdMesList);
	        		}
	        	}
	        	//获取选项信息
	        	getOption(tstMessage, titleId);
	        	
	        	//设置题目编号
	        	tstMessage.setTitleOrderNumber(titleOrderNumberBasic+i+1);
	        	
    		}
    	}
    	return list;
   }
    
    @Transactional
    public Boolean addMessage(TestScaleTitleMessage testScaleTitleMessage) {
    	try{
	    	Boolean isSuccess = false;
	    	
	    	Integer testScaleId = testScaleTitleMessage.getRelateTestScaleId();
	    	
	    	//判断相关量表是否存在
	    	TestScaleInfoMessage tsiMessage = tsiMapper.getMessById(testScaleId);
	    	if(tsiMessage == null){
	    		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	    	}
	
	    	//插入题目数据
	        Date currentTime = new Date();
	        testScaleTitleMessage.setIsDelete(0);
	        testScaleTitleMessage.setIsEnable(0);
	        testScaleTitleMessage.setCreateTime(currentTime);
	        testScaleTitleMessage.setUpdateTime(currentTime);
	        mapper.insertMess(testScaleTitleMessage);
	        int insertTitleId = testScaleTitleMessage.getId();
	        if(insertTitleId == 0){
	        	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	        }
	        
	        //插入题目维度关联数据
	        List<TestScaleDimensionMessage> tsdList = testScaleTitleMessage.getTsdList();
	        for(int i=0;i<tsdList.size();i++){
	        	int dimensionId = tsdList.get(i).getId();
	        	TestScaleDimensionMessage tsdMessage = tsdMapper.getMessById(dimensionId);
	        	if(tsdMessage==null){
	        		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	        	}
	        	TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
	        	tsdtrMessage.setTestScaleDimensionId(dimensionId);
	        	tsdtrMessage.setTestScaleTitleId(insertTitleId);
	        	tsdtrMessage.setIsDelete(0);
	        	tsdtrMessage.setIsEnable(0);
	        	tsdtrMessage.setCreateTime(currentTime);
	        	tsdtrMessage.setUpdateTime(currentTime);
	        	tsdtrMapper.insertMess(tsdtrMessage);
	        	int insertRelateId = tsdtrMessage.getId();
	            if(insertRelateId == 0){
	            	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	            }
	        }
	        
	        
	        //插入选项数据
	        List<TestScaleOptionMessage> tsoList = testScaleTitleMessage.getTsoList();
	        for(int j=0;j<tsoList.size();j++){
	        	TestScaleOptionMessage tsoMessage = tsoList.get(j);
	        	Float optionScore = tsoMessage.getOptionScore();
	        	if(optionScore==null){
	        		throw new RuntimeException(ErrorCode.ERROR_OPTION_SCORE_IS_NULL.getMessage());
	        	}
	        	tsoMessage.setRelateTitleId(insertTitleId);
	        	tsoMessage.setIsDelete(0);
	        	tsoMessage.setIsEnable(0);
	        	tsoMessage.setCreateTime(currentTime);
	        	tsoMessage.setUpdateTime(currentTime);
	        	tsoMapper.insertMess(tsoMessage);
	        	int insertTsoId = tsoMessage.getId();
	            if(insertTsoId == 0){
	            	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
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
    public Boolean updateMess(TestScaleTitleMessage testScaleTitleMessage) {
	    try{
	    	Boolean isSuccess = false; 
	    	
	    	//修改题目
	        Date currentTime = new Date();
	        testScaleTitleMessage.setUpdateTime(currentTime);
	    	int updateNum = mapper.updateMess(testScaleTitleMessage);
	       	if(updateNum==0){
	       		throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	    	}
	    	
	    	Integer tstId = testScaleTitleMessage.getId();
	        
	    	
	        //修改题目与维度的关联
	       	updateDimensionTitleRelation(testScaleTitleMessage,
					currentTime, tstId);
	       	
	    	
	    	//修改选项
			updateOption(testScaleTitleMessage, currentTime, tstId);
					
					
			isSuccess = true;
		    return isSuccess;
		    
	   	}catch(Exception e){
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(),e);
		}
    }

	private void updateOption(TestScaleTitleMessage testScaleTitleMessage, Date currentTime, Integer tstId) {
		TestScaleOptionMessage tsoMessage = new TestScaleOptionMessage();
		tsoMessage.setRelateTitleId(tstId);
		List<TestScaleOptionMessage> oriOptionList = tsoMapper.selectAll(tsoMessage);
		List<TestScaleOptionMessage> updateOptionList = testScaleTitleMessage.getTsoList();
				
		//判断原先数据库中是否有选项，如果有，就先添加新增的关联，再删除原先的关联。
		if(oriOptionList!=null&&oriOptionList.size()>0){
				List<Integer> oriIdList  = new ArrayList<Integer>();
	    		for(int i=0;i<oriOptionList.size();i++){
	    			oriIdList.add(oriOptionList.get(i).getId());
	    		}
	    		
	    		List<Integer> updateIdList  = new ArrayList<Integer>();
	    	    
	    	       
	    	    //修改原有的选项,添加新增的选项
	    	    if(updateOptionList!=null && updateOptionList.size()>0){
			        for(int j=0;j<updateOptionList.size();j++){
			        	TestScaleOptionMessage updateOptionMessage = updateOptionList.get(j);
			        	Integer uoId = updateOptionMessage.getId();
			         	Float optionScore = updateOptionMessage.getOptionScore();
			        	if(optionScore==null){
			        		throw new RuntimeException(ErrorCode.ERROR_OPTION_SCORE_IS_NULL.getMessage());
			        	}
			        	
			        	if(!oriIdList.contains(uoId)){
			        		
			        		updateOptionMessage.setRelateTitleId(tstId);
			        		updateOptionMessage.setIsDelete(0);
			        		updateOptionMessage.setIsEnable(0);
			        		updateOptionMessage.setCreateTime(currentTime);
			        		updateOptionMessage.setUpdateTime(currentTime);
			        		int insertId = tsoMapper.insertMess(updateOptionMessage);
			        		if(insertId == 0){
			        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
			        		}
			        	}else{ //修改原有的选项
			        		updateIdList.add(uoId);
			        		
			        		updateOptionMessage.setUpdateTime(currentTime);
			        		int updateNumber = tsoMapper.updateMess(updateOptionMessage);
			        		if(updateNumber == 0){
			        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
			        		}
			        	}
			  
			        }
	    	    }
		        
		      //删除去掉的选项
		      List<Integer> delIdList  = new ArrayList<Integer>();
		      for(int k=0;k<oriOptionList.size();k++){
		    	  TestScaleOptionMessage oriMes = oriOptionList.get(k);
		    	  int oriOptionId = oriMes.getId();
		    	  if(!updateIdList.contains(oriOptionId)){
		    		  delIdList.add(oriOptionId);
		    	  }
		      }
		      
		      int delIdListSize = delIdList.size();
		      if(delIdList!=null && delIdListSize>0){
		    	  TestScaleOptionMessage delTsoMes = new TestScaleOptionMessage();
		    	  delTsoMes.setSearchList(delIdList);
		    	  delTsoMes.setIsDelete(1);
		    	  delTsoMes.setUpdateTime(currentTime);
				  int delNum = tsoMapper.batchDeleteMess(delTsoMes);
			      if(delNum < delIdListSize){
	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	        	  }
		      }
	        
		}else{ //判断原先数据库中是否有选项，如果没有，就直接添加新关联就行。
		
    	    //原有的选项保持不变,添加新增的选项
    	    if(updateOptionList!=null && updateOptionList.size()>0){
		        for(int j=0;j<updateOptionList.size();j++){
		        	TestScaleOptionMessage updateOptionMessage = updateOptionList.get(j);
		        	
		         	Float optionScore = updateOptionMessage.getOptionScore();
		        	if(optionScore==null){
		        		throw new RuntimeException(ErrorCode.ERROR_OPTION_SCORE_IS_NULL.getMessage());
		        	}
		        	
		        	updateOptionMessage.setRelateTitleId(tstId);
		        	updateOptionMessage.setIsDelete(0);
	        		updateOptionMessage.setIsEnable(0);
	        		updateOptionMessage.setCreateTime(currentTime);
	        		updateOptionMessage.setUpdateTime(currentTime);
	        		int addId = tsoMapper.insertMess(updateOptionMessage);
	        		if(addId == 0){
	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	        		}
		        }
    	    }
		}
	}

	private void updateDimensionTitleRelation(
			TestScaleTitleMessage testScaleTitleMessage, Date currentTime, Integer tstId) {
		
		//获取原有的关联信息
		TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
       	tsdtrMessage.setTestScaleTitleId(tstId);
		List<TestScaleDimensionTitleRelationMessage> oriDimensionList = tsdtrMapper.selectAll(tsdtrMessage);
		List<TestScaleDimensionMessage> updateDimensionList = testScaleTitleMessage.getTsdList();
		
		//判断原先数据库中是否有关联，如果有，就先添加新增的关联，再删除原先的关联。
		if(oriDimensionList!=null&&oriDimensionList.size()>0){
				List<Integer> oriIdList  = new ArrayList<Integer>();
	    		for(int i=0;i<oriDimensionList.size();i++){
	    			oriIdList.add(oriDimensionList.get(i).getTestScaleDimensionId());
	    		}
	    		
	    		List<Integer> updateIdList  = new ArrayList<Integer>();
	    	    
	    	       
	    	    //原有的关联保持不变,添加新增的关联
	    	    if(updateDimensionList!=null && updateDimensionList.size()>0){
			        for(int j=0;j<updateDimensionList.size();j++){
			        	int udId = updateDimensionList.get(j).getId();
			        	
			        	if(!oriIdList.contains(udId)){
			        		
			        		//判断关联的维度是否存在，如果不存在那就回滚
			        		TestScaleDimensionMessage tdMessage = tsdMapper.getMessById(udId);
			        		if(tdMessage == null){
			        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
			        		}
			        		
			        		TestScaleDimensionTitleRelationMessage tdtrMes = new TestScaleDimensionTitleRelationMessage();
			        		tdtrMes.setTestScaleDimensionId(udId);
			        		tdtrMes.setTestScaleTitleId(tstId);
			        		tdtrMes.setIsDelete(0);
			        		tdtrMes.setIsEnable(0);
			        		tdtrMes.setCreateTime(currentTime);
			        		tdtrMes.setUpdateTime(currentTime);
			        		int insertId = tsdtrMapper.insertMess(tdtrMes);
			        		if(insertId == 0){
			        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
			        		}
			        	}else{
			        		updateIdList.add(udId);
			        	}
			        }
	    	    }
		        
		      //删除去掉的关联
		      List<Integer> delIdList  = new ArrayList<Integer>();
		      for(int k=0;k<oriDimensionList.size();k++){
		    	  TestScaleDimensionTitleRelationMessage oriMes = oriDimensionList.get(k);
		    	  int oriDimensionId = oriMes.getTestScaleDimensionId();
		    	  if(!updateIdList.contains(oriDimensionId)){
		    		  delIdList.add(oriMes.getId());
		    	  }
		      }
		      
		      int delIdListSize = delIdList.size();
		      if(delIdList!=null && delIdListSize>0){
		    	  TestScaleDimensionTitleRelationMessage delTsdtrMes = new TestScaleDimensionTitleRelationMessage();
		    	  delTsdtrMes.setSearchList(delIdList);
		    	  delTsdtrMes.setIsDelete(1);
		    	  delTsdtrMes.setUpdateTime(currentTime);
				  int delNum = tsdtrMapper.batchDeleteMess(delTsdtrMes);
			      if(delNum < delIdListSize){
	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	        	  }
		      }
	        
		}else{ //判断原先数据库中是否有关联，如果没有，就直接添加新关联就行。
		
    	    //原有的关联保持不变,添加新增的关联
    	    if(updateDimensionList!=null && updateDimensionList.size()>0){
		        for(int j=0;j<updateDimensionList.size();j++){
		        	int udId = updateDimensionList.get(j).getId();
		        	
	        		//判断关联的分类是否存在，如果不存在那就回滚
		        	TestScaleDimensionMessage tdMessage = tsdMapper.getMessById(udId);
	        		if(tdMessage == null){
	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	        		}
		        	
	        		TestScaleDimensionTitleRelationMessage tdtrMes = new TestScaleDimensionTitleRelationMessage();
	        		tdtrMes.setTestScaleDimensionId(udId);
	        		tdtrMes.setTestScaleTitleId(tstId);
	        		tdtrMes.setIsDelete(0);
	        		tdtrMes.setIsEnable(0);
	        		tdtrMes.setCreateTime(currentTime);
	        		tdtrMes.setUpdateTime(currentTime);
	        		int addId = tsdtrMapper.insertMess(tdtrMes);
	        		if(addId == 0){
	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	        		}
		        }
    	    }
		}
	}
	
	
	 @Transactional
	 public ResultEntity deleteMess(TestScaleTitleMessage testScaleTitleMessage) {
	       try{
		    	ResultEntity result = new ResultEntity(); 
		    	
		    	Integer tstId = testScaleTitleMessage.getId();
		    	Integer testScaleId = testScaleTitleMessage.getRelateTestScaleId();
		    	
		    	//判断量表是否在线上，如果在线上，无法删除题目
		    	if(tsiService.isOnline(testScaleId)){
		    		result.setCode(ErrorCode.ERROR_TESTSCALE_ONLINE.getCode());
					result.setMsg(ErrorCode.ERROR_TESTSCALE_ONLINE.getMessage());
					return result;
		    	}
		    	
		    	Date currentTime = new Date();
		    	
		        //删除题目
		    	TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
		    	tstMessage.setId(tstId);
		    	tstMessage.setIsDelete(TestScaleTitleMessage.IS_DELETE);
		    	tstMessage.setUpdateTime(currentTime);
		    	int deleteTstNum = mapper.updateMess(tstMessage);
		    	if(deleteTstNum==0){
		    		throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		    	}
		    	
		    	//删除选项
		    	TestScaleOptionMessage tsoMessage  = new TestScaleOptionMessage();
		    	tsoMessage.setRelateTitleId(tstId);
		    	tsoMessage.setIsDelete(TestScaleOptionMessage.IS_DELETE);
		    	tsoMessage.setUpdateTime(currentTime);
		    	tsoMapper.batchDeleteMessByTitleId(tsoMessage);
		    	
		    	//删除题目维度关联
		    	TestScaleDimensionTitleRelationMessage  tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
		    	tsdtrMessage.setTestScaleTitleId(tstId);
		    	tsdtrMessage.setIsDelete(TestScaleDimensionTitleRelationMessage.IS_DELETE);
		    	tsdtrMessage.setUpdateTime(currentTime);
		    	tsdtrMapper.batchDeleteMessByTitleId(tsdtrMessage);
						
		    	result.put("isSuccess", true);
				result.setCode(ErrorCode.SUCCESS.getCode());
				result.setMsg(ErrorCode.SUCCESS.getMessage());
			    return result;
		    
		   	}catch(Exception e){
				log.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage(),e);
			}
	    }
	
}

