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

import com.github.pagehelper.Page;
import com.xinmao.sc.testCenter.domain.ConditionsOfQualitativeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
import com.xinmao.sc.testCenter.domain.TestScaleClassRelationMessage;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionTitleRelationMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleNormMessage;
import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;
import com.xinmao.sc.testCenter.domain.TestScalePopulationRangeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleQualitativeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleTitleMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.mapper.ConditionsOfQualitativeMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleClassMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleClassRelationMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleDimensionMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleDimensionTitleRelationMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleInfoMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleNormMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleOptionMapper;
import com.xinmao.sc.testCenter.mapper.TestScalePopulationRangeMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleQualitativeMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleTitleMapper;



@Service
public class TestScaleInfoService {
	
//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TestScaleInfoMapper mapper;
    
    @Autowired
    private TestScaleClassMapper testScaleClassMapper;
    
    @Autowired
    private TestScaleClassRelationMapper testScaleClassRelationMapper;
    
    @Autowired
    private TestScaleDimensionMapper tsdMapper;
    
    @Autowired
    private TestScaleDimensionTitleRelationMapper tsdtrMapper;
    
    @Autowired
    private TestScaleNormMapper tsnMapper;
    
    
    @Autowired
    private TestScaleTitleMapper tstMapper;
    
    @Autowired
    private TestScaleOptionMapper tsoMapper;
    
    @Autowired
    private TestScalePopulationRangeMapper tsprMapper;
    
    @Autowired
    private TestScaleQualitativeMapper tsqMapper;
    
    @Autowired
    private ConditionsOfQualitativeMapper coqMapper;
    
 
    
    
    
  public Boolean addNumberOfTest(TestScaleInfoMessage testScaleInfoMessage) {
    	
        Date currentTime = new Date();
        testScaleInfoMessage.setUpdateTime(currentTime);
    	
    	//添加量表基础信息
        Integer updateNum = mapper.addNumberOfTest(testScaleInfoMessage);

		if(updateNum==null || updateNum==0){
			return false;
		}
        return true;
    }
    
    
    
    public int getTestScaleNum(){
    	
 	   return mapper.getTestScaleNum();
         
    }
    
    public int updateMessage(TestScaleInfoMessage testScaleInfoMessage){
    	
  	   return mapper.updateMess(testScaleInfoMessage);
          
    }
    
    public int getTestScaleNumByCondition(TestScaleInfoMessage testScaleInfoMessage){
    	
   	   return mapper.getTestScaleNumByCondition(testScaleInfoMessage);
           
     }

    @Transactional
	 public ResultEntity deleteMess(TestScaleInfoMessage testScaleInfoMessage) {
	       try{
		    	ResultEntity result = new ResultEntity(); 
		    	
		    	Integer tsiId = testScaleInfoMessage.getId();
		    	
	//	    	//判断量表是否在线上，如果在线上，无法删除定性
	//	    	if(isOnline(tsiId)){
	//	    		result.setCode(ErrorCode.ERROR_TESTSCALE_ONLINE.getCode());
	//				result.setMsg(ErrorCode.ERROR_TESTSCALE_ONLINE.getMessage());
	//				return result;
	//	    	}
		    	
		    	Date currentTime = new Date();
		         
		    	
		    	//删除人群范围
		    	TestScalePopulationRangeMessage tsprMessage = new TestScalePopulationRangeMessage();
		    	tsprMessage.setRelateTestScaleId(tsiId);
		    	tsprMessage.setIsDelete(TestScalePopulationRangeMessage.IS_DELETE);
		    	tsprMessage.setUpdateTime(currentTime);
		    	tsprMapper.batchDeleteMessByTestScaleId(tsprMessage);
		    	
		    	
		    	//获取该量表下所有维度id
		    	TestScaleDimensionMessage tsDimension = new TestScaleDimensionMessage();
		    	tsDimension.setRelateTestScaleId(tsiId);
		    	List<Integer> tsdIdList = tsdMapper.selectAllId(tsDimension);
		    	
		    	if(tsdIdList!=null && tsdIdList.size()>0){
		    		
		    		//删除维度题目关联
			    	TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
			    	tsdtrMessage.setSearchList(tsdIdList);
			    	tsdtrMessage.setIsDelete(TestScaleDimensionTitleRelationMessage.IS_DELETE);
			    	tsdtrMessage.setUpdateTime(currentTime);
			    	tsdtrMapper.batchDeleteMessByDimensionIdList(tsdtrMessage);
			    	
			    	//删除维度
			    	TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
			    	tsdMessage.setRelateTestScaleId(tsiId);
			    	tsdMessage.setIsDelete(TestScaleDimensionMessage.IS_DELETE);
			    	tsdMessage.setUpdateTime(currentTime);
			    	tsdMapper.batchDeleteMessByTestScaleId(tsdMessage);
		    	}
		    	
		     	//删除常模
		    	TestScaleNormMessage tsnMessage = new TestScaleNormMessage();
		    	tsnMessage.setRelateTestScaleId(tsiId);
		    	tsnMessage.setIsDelete(TestScaleNormMessage.IS_DELETE);
		    	tsnMessage.setUpdateTime(currentTime);
		    	tsnMapper.batchDeleteMessByTestScaleId(tsnMessage);
		    	
		    	//获取该量表下所有题目id
		    	TestScaleTitleMessage tsTitleMessage = new TestScaleTitleMessage();
		    	tsTitleMessage.setRelateTestScaleId(tsiId);
		    	List<Integer> titleIdList = tstMapper.selectAllId(tsTitleMessage);
		    	
		    	
		    	if(titleIdList!=null && titleIdList.size()>0){
		    		
		    		//删除选项
			    	TestScaleOptionMessage tsoMessage = new TestScaleOptionMessage();
			    	tsoMessage.setSearchList(titleIdList);
			    	tsoMessage.setIsDelete(TestScaleOptionMessage.IS_DELETE);
			    	tsoMessage.setUpdateTime(currentTime);
			    	tsoMapper.batchDeleteMessByTitleIdList(tsoMessage);
			    	
			    	//删除题目
			    	TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
			    	tstMessage.setRelateTestScaleId(tsiId);
			    	tstMessage.setIsDelete(TestScaleTitleMessage.IS_DELETE);
			    	tstMessage.setUpdateTime(currentTime);
			    	tstMapper.batchDeleteMessByTestScaleId(tstMessage);
		    	}
	
		    	
		    	//查找该量表下的所有定性
		    	TestScaleQualitativeMessage tsQualitativeMessage = new TestScaleQualitativeMessage();
		    	tsQualitativeMessage.setRelateTestScaleId(tsiId);
		    	List<Integer> qualitativeIdList = tsqMapper.selectAllId(tsQualitativeMessage);
		    	
		    	if(qualitativeIdList!=null && qualitativeIdList.size()>0){
		    		
		    		//删除定性成立条件
			    	ConditionsOfQualitativeMessage coqMessage = new ConditionsOfQualitativeMessage();
			    	coqMessage.setSearchList(qualitativeIdList);
			    	coqMessage.setIsDelete(ConditionsOfQualitativeMessage.IS_DELETE);
			    	coqMessage.setUpdateTime(currentTime);
			    	coqMapper.batchDeleteMessByQualitativeIdList(coqMessage);
		    	
			    	//删除定性
			    	TestScaleQualitativeMessage tsqMessage = new TestScaleQualitativeMessage();
			    	tsqMessage.setRelateTestScaleId(tsiId);
			    	tsqMessage.setIsDelete(TestScaleQualitativeMessage.IS_DELETE);
			    	tsqMessage.setUpdateTime(currentTime);
			    	tsqMapper.batchDeleteMessByTestScaleId(tsqMessage);
		    	}
		    	
		    	
		    	//删除量表分类关联
		    	TestScaleClassRelationMessage tscrMessage = new TestScaleClassRelationMessage();
		    	tscrMessage.setTestScaleId(tsiId);
		    	tscrMessage.setIsDelete(TestScaleClassRelationMessage.IS_DELETE);
		    	tscrMessage.setUpdateTime(currentTime);
		    	testScaleClassRelationMapper.batchDeleteMessByTestScaleId(tscrMessage);
		    	
		    	
		    	//删除量表
		    	TestScaleInfoMessage tsiMessage = new TestScaleInfoMessage();
		    	tsiMessage.setId(tsiId);;
		    	tsiMessage.setIsDelete(TestScaleInfoMessage.IS_DELETE);
		    	tsiMessage.setUpdateTime(currentTime);
		    	int updateNum = mapper.updateMess(tsiMessage);
		    	if(updateNum == 0){
		    		throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
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
    
    public Boolean isOnline(int id){
    	
        Boolean isOnline = false;
        TestScaleInfoMessage tsiMessage = mapper.getMessById(id);
        
        if(tsiMessage!=null){
        	  Integer isEnable = tsiMessage.getIsEnable();
        	  if(isEnable!=null && isEnable==TestScaleInfoMessage.IS_ONLINE){
        		  isOnline =true;
        	  }
        }
        
        return isOnline;
   }
    
    public Boolean isOnline(int id,ResultEntity result){
    	
        TestScaleInfoMessage tsiMessage = mapper.getMessById(id);
        
        if(tsiMessage!=null){
        	  Integer isEnable = tsiMessage.getIsEnable();
        	  if(isEnable!=null && isEnable==TestScaleInfoMessage.IS_ONLINE){
        		
  		   		result.setCode(ErrorCode.ERROR_TEST_SCALE_IS_ONLINE.getCode());
  				result.setMsg(ErrorCode.ERROR_TEST_SCALE_IS_ONLINE.getMessage());
  				return true;
        	  }
        }
        
        return false;
    }
    
    
    public List<TestScaleInfoMessage> getNewestMessage(TestScaleInfoMessage tsiMessage){
    	
    	return mapper.getNewestMessage(tsiMessage);
    }
    
    
    
    
   public List<TestScaleInfoMessage> getAllMessageByIdList(TestScaleInfoMessage testScaleInfoMessage){
    	
	   return mapper.getAllMessageByIdList(testScaleInfoMessage);
        
   }
   
//   public List<TestScaleInfoMessage> getAllMessageByCollectIdList(TestScaleInfoMessage testScaleInfoMessage){
//   	
//	   return mapper.getAllMessageByCollectIdList(testScaleInfoMessage);
//       
//   }
   
   public List<TestScaleInfoMessage> getAllMessageFromClassPage(TestScaleInfoMessage testScaleInfoMessage){
	   return mapper.getAllMessageFromClassPage(testScaleInfoMessage);
   }
    
    public TestScaleInfoMessage getMessageById(int id){
         return mapper.getMessById(id);
    }
    
    public TestScaleInfoMessage getMessByIdFromFront(TestScaleInfoMessage tsiMessage){
        return mapper.getMessByIdFromFront(tsiMessage);
    }
    
    public List<TestScaleInfoMessage> getHotMessage(TestScaleInfoMessage testScaleInfoMessage){
    	
    	return mapper.getHotMessage(testScaleInfoMessage);
   }
    
    public List<TestScaleInfoMessage> getAllHomePageMessage(TestScaleInfoMessage testScaleInfoMessage){
    	
    	return mapper.selectAll(testScaleInfoMessage);
   }
    
    public Page<TestScaleInfoMessage> getAllMessage(TestScaleInfoMessage testScaleInfoMessage){
    	
        Page<TestScaleInfoMessage> list = mapper.selectAll(testScaleInfoMessage);
        
        return list;
   }
    
    
    
    
    @Transactional
    public Boolean addMessage(TestScaleInfoMessage testScaleInfoMessage) {
    	try{
	    	Boolean isSuccess = false;
	    	
	    	  //配置量表基础信息
	        Date currentTime = new Date();
	        testScaleInfoMessage.setIsTop(0);
	        testScaleInfoMessage.setNumberOfTest(0);
	        testScaleInfoMessage.setIsDelete(0);
	        testScaleInfoMessage.setIsEnable(0);
	        testScaleInfoMessage.setIsShowDimensionProfile(TestScaleInfoMessage.IS_SHOW_DIMENSION_PROFILE);
	        testScaleInfoMessage.setCreateTime(currentTime);
	        testScaleInfoMessage.setUpdateTime(currentTime);
	    	
	    	//添加量表基础信息
	        mapper.insertMess(testScaleInfoMessage);
	        Integer tsiId = testScaleInfoMessage.getId();
	
			if(tsiId==null || tsiId==0){
				throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
			}
	        
	        //关联量表与分类
	        List<TestScaleClassMessage> tscList = testScaleInfoMessage.getTscList();
	        if(tscList!=null && tscList.size()>0){
		        for(int i=0;i<tscList.size();i++){
		        	TestScaleClassMessage tscMessage = tscList.get(i);
		        	if(tscMessage.getIsBelongTo()){
		        		Integer tscId = tscMessage.getId();
		        		if(tscId==null || tscId==0){
		        			throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
		        		}
		        		
		        		//判断关联的分类是否存在，如果不存在那就回滚
		        		TestScaleClassMessage tsClassMessage = testScaleClassMapper.getMessById(tscId);
		        		
		        		if(tsClassMessage == null){
		        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		        		}
		        		
		        		TestScaleClassRelationMessage tscrMessage = new TestScaleClassRelationMessage();
		        		tscrMessage.setTestScaleClassId(tscId);
			        	tscrMessage.setTestScaleId(tsiId);
			           	tscrMessage.setIsDelete(0);
			        	tscrMessage.setIsEnable(0);
			        	tscrMessage.setCreateTime(currentTime);
			        	tscrMessage.setUpdateTime(currentTime);
			        	testScaleClassRelationMapper.insertMess(tscrMessage);
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
    public Boolean updateMess(TestScaleInfoMessage testScaleInfoMessage) {
    	
    	try{
	    	Boolean isSuccess = false; 
	    	
		    Date currentTime = new Date();
	        testScaleInfoMessage.setUpdateTime(currentTime);
	        
	    	int updateNum = mapper.updateMess(testScaleInfoMessage);
	        
	       	if(updateNum==0){
	       		throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	    	}
	       	
	        
	        //获取原有的关联信息
	       	Integer tsId = testScaleInfoMessage.getId();
	  		TestScaleClassRelationMessage testScaleClassRelationMessage = new TestScaleClassRelationMessage();
			testScaleClassRelationMessage.setTestScaleId(tsId);
			List<TestScaleClassRelationMessage> oriClassRelationList = testScaleClassRelationMapper.selectAll(testScaleClassRelationMessage);
			List<TestScaleClassMessage> addClassList = testScaleInfoMessage.getTscList();
			
			//判断原先数据库中是否有关联，如果有，就先添加新增的关联，再删除原先的关联。
			if(oriClassRelationList!=null&&oriClassRelationList.size()>0){
					List<Integer> oriIdList  = new ArrayList<Integer>();
		    		for(int i=0;i<oriClassRelationList.size();i++){
		    			oriIdList.add(oriClassRelationList.get(i).getTestScaleClassId());
		    		}
		    		
		    		List<Integer> updateIdList  = new ArrayList<Integer>();
		    	    
		    	       
		    	    //原有的关联保持不变,添加新增的关联
		    	    if(addClassList!=null && addClassList.size()>0){
				        for(int j=0;j<addClassList.size();j++){
				        	int tscId = addClassList.get(j).getId();
				        	
				        	if(!oriIdList.contains(tscId)){
				        		
				        		//判断关联的分类是否存在，如果不存在那就回滚
				        		TestScaleClassMessage tsClassMessage = testScaleClassMapper.getMessById(tscId);
				        		if(tsClassMessage == null){
				        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
				        		}
				        		
				        		TestScaleClassRelationMessage tscrMes = new TestScaleClassRelationMessage();
				        		tscrMes.setTestScaleClassId(tscId);
				        		tscrMes.setTestScaleId(tsId);
				        		tscrMes.setIsDelete(0);
				        		tscrMes.setIsEnable(0);
				        		tscrMes.setCreateTime(currentTime);
				        		tscrMes.setUpdateTime(currentTime);
				        		int insertId = testScaleClassRelationMapper.insertMess(tscrMes);
				        		if(insertId == 0){
				        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
				        		}
				        	}else{
				        		updateIdList.add(tscId);
				        	}
				        }
		    	    }
			        
			      //删除去掉的关联
			      List<Integer> delIdList  = new ArrayList<Integer>();
			      for(int k=0;k<oriClassRelationList.size();k++){
			    	  TestScaleClassRelationMessage oriMes = oriClassRelationList.get(k);
			    	  int oriClassId = oriMes.getTestScaleClassId();
			    	  if(!updateIdList.contains(oriClassId)){
			    		  delIdList.add(oriMes.getId());
			    	  }
			      }
			      
			      int delIdListSize = delIdList.size();
			      if(delIdList!=null && delIdListSize>0){
					  TestScaleClassRelationMessage delTscrMes = new TestScaleClassRelationMessage();
					  delTscrMes.setSearchList(delIdList);
					  delTscrMes.setIsDelete(1);
					  delTscrMes.setUpdateTime(currentTime);
					  int delNum = testScaleClassRelationMapper.batchDeleteMess(delTscrMes);
				      if(delNum < delIdListSize){
		        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		        	  }
			      }
		        
			}else{ //判断原先数据库中是否有关联，如果没有，就直接添加新关联就行。
				
	    	    //原有的关联保持不变,添加新增的关联
	    	    if(addClassList!=null && addClassList.size()>0){
			        for(int j=0;j<addClassList.size();j++){
			        	int tscId = addClassList.get(j).getId();
			        	
		        		//判断关联的分类是否存在，如果不存在那就回滚
		        		TestScaleClassMessage tsClassMessage = testScaleClassMapper.getMessById(tscId);
		        		if(tsClassMessage == null){
		        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		        		}
			        	
		        		TestScaleClassRelationMessage tscrMes = new TestScaleClassRelationMessage();
		        		tscrMes.setTestScaleClassId(tscId);
		        		tscrMes.setTestScaleId(tsId);
		        		tscrMes.setIsDelete(0);
		        		tscrMes.setIsEnable(0);
		        		tscrMes.setCreateTime(currentTime);
		        		tscrMes.setUpdateTime(currentTime);
		        		int addId = testScaleClassRelationMapper.insertMess(tscrMes);
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
    
}

