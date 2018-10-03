package com.xinmao.sc.testCenter.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

import com.xinmao.sc.testCenter.domain.ConditionsOfQualitativeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleDimensionTitleRelationMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleNormMessage;
import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;
import com.xinmao.sc.testCenter.domain.TestScalePopulationRangeMessage;
import com.xinmao.sc.testCenter.domain.TestScaleTitleMessage;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;
import com.xinmao.sc.testCenter.mapper.ConditionsOfQualitativeMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleDimensionMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleDimensionTitleRelationMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleInfoMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleNormMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleOptionMapper;
import com.xinmao.sc.testCenter.mapper.TestScalePopulationRangeMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleTitleMapper;

@Service
public class TestScaleDimensionService {
	
//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TestScaleDimensionMapper mapper;
    
    @Autowired
    private TestScaleDimensionTitleRelationMapper tsdtrMapper;
    
    @Autowired
    private TestScaleTitleMapper tstMapper;
    
    @Autowired
    private TestScaleOptionMapper tsoMapper;
    
    @Autowired
    private TestScalePopulationRangeMapper tsprMapper;
    
    @Autowired
    private TestScaleNormMapper tsnMapper;
    
    @Autowired
    private TestScaleInfoMapper tsiMapper;
    
    @Autowired
    private ConditionsOfQualitativeMapper coqMapper;
    
    @Autowired
    private TestScaleInfoService tsiService;
    
    public int updateMessage(TestScaleDimensionMessage testScaleDimensionMessage){
    	
   	   return mapper.updateMess(testScaleDimensionMessage);
           
    }
    
public List<TestScaleDimensionMessage> getMessageByTestScaleId(TestScaleOrderMessage tsoMessage){
        
        Integer testScaleId = tsoMessage.getRelateTestScaleId();
        String sex = tsoMessage.getSex();
        Date birthday = tsoMessage.getBirthday();
        int age = getAgeByBirth(birthday); 
        
        
        TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
        tsdMessage.setRelateTestScaleId(testScaleId);
        
        List<TestScaleDimensionMessage> list = mapper.selectAll(tsdMessage);
        if(list!=null && list.size()>0){
        	
        	
        	//设置各维度下的常模，题目，选项
        	for(int i=0;i<list.size();i++){
                TestScaleDimensionMessage tsDimensionMessage = list.get(i);
                Integer dimensionId = tsDimensionMessage.getId();
              
                //设置相关常模
          		TestScalePopulationRangeMessage  tsprMessage = new TestScalePopulationRangeMessage();
          		tsprMessage.setRelateTestScaleId(testScaleId);
          		List<TestScalePopulationRangeMessage> tsprList = tsprMapper.selectAll(tsprMessage);
          		if(tsprList!=null && tsprList.size()>0){
          			for(int h=0;h<tsprList.size();h++){
          				
          				TestScalePopulationRangeMessage tsprMess = tsprList.get(h);
          				if(sex.equals(tsprMess.getSex())&& 
          				   age>tsprMess.getLowerAgeLimit()&&
          				   age<=tsprMess.getUpperAgeLimit()){
          					
          					log.error("该用户在此人群范围内：性别为："+sex+",年龄为："+age+
        							  ";人群范围的id为："+tsprMess.getId()+",人群范围的性别为："+tsprMess.getSex()+
        							  ",人群范围的年龄范围为："+tsprMess.getLowerAgeLimit()+"-"+tsprMess.getUpperAgeLimit()+
        							  ",该量表id为："+testScaleId);
          					
          					TestScaleNormMessage tsnMessage = new TestScaleNormMessage();
              				tsnMessage.setDimensionId(dimensionId);
              				tsnMessage.setRelateTestScaleId(testScaleId);
              				tsnMessage.setPopulationRangeId(tsprMess.getId());
              				List<TestScaleNormMessage> tsnList = tsnMapper.selectAll(tsnMessage);
              				if(tsnList!=null && tsnList.size()>0){
              					tsDimensionMessage.setTsnMessage(tsnList.get(0));
              				}
          				}else{
          					log.error("该用户并不在此人群范围内：性别为："+sex+",年龄为："+age+
          							  ";人群范围的id为："+tsprMess.getId()+",人群范围的性别为："+tsprMess.getSex()+
          							  ",人群范围的年龄范围为："+tsprMess.getLowerAgeLimit()+"-"+tsprMess.getUpperAgeLimit()+
          							  ",该量表id为："+testScaleId);
          				}
          			}
          		}
          		
          		
          		//获取维度下的相关题目id
        		TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
        		tsdtrMessage.setTestScaleDimensionId(dimensionId);
        		List<TestScaleDimensionTitleRelationMessage>  tsdtrList = tsdtrMapper.selectAll(tsdtrMessage);
        		
        		if(tsdtrList!=null && tsdtrList.size()>0){
        			
        			//获取原先关联的题目id的list
        			List<Integer> tstIdList = new ArrayList<Integer>();
        			for(int k=0;k<tsdtrList.size();k++){
        				Integer titleId = tsdtrList.get(k).getTestScaleTitleId();
        				tstIdList.add(titleId);
        			}
        			tsDimensionMessage.setTstIdList(tstIdList);
        			
        			
        			//获取这个维度下的维度满分
        			Float dimensionFullScore = 0f;
        			Map<Integer,Float> titleMaxScore = new HashMap<Integer,Float>();
        			TestScaleOptionMessage tsoMessge = new TestScaleOptionMessage();
        			tsoMessge.setSearchList(tstIdList);
        			List<TestScaleOptionMessage> tsoList = tsoMapper.selectAllByTitleIdList(tsoMessge);
        			if(tsoList!=null && tsoList.size()>0){
        				for(int j=0;j<tsoList.size();j++){
        					TestScaleOptionMessage tsoMess = tsoList.get(j);
        					Integer relateTitleId = tsoMess.getRelateTitleId();
        					Float optionScore = tsoMess.getOptionScore();
        					if(titleMaxScore.containsKey(relateTitleId)){
        						Float oriOptionScore = titleMaxScore.get(relateTitleId);
        						if(optionScore > oriOptionScore){
        							titleMaxScore.put(relateTitleId,optionScore);
        						}
        					}else{
        						titleMaxScore.put(relateTitleId, optionScore);
        					}
            			}
        			    for(Integer titleId : titleMaxScore.keySet()){
        			    	dimensionFullScore += titleMaxScore.get(titleId);
        			    }
        				
        			    tsDimensionMessage.setDimensionFullScore(dimensionFullScore);
        			    
        			}
        		}
            }
        }
      
        return list;
   }
    

	private static int getAgeByBirth(Date birthday) {
		
        Calendar cal = Calendar.getInstance();  
        
        if (cal.before(birthday)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH)+1;  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthday);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH)+1;  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    }
	
    
    public TestScaleDimensionMessage getMessById(int id){
    	return mapper.getMessById(id);
    }
    
    public TestScaleDimensionMessage getMessageById(int id){

    	TestScaleDimensionMessage tsdMessage = mapper.getMessById(id);
    	if(tsdMessage!=null){
    		Integer testScaleId =tsdMessage.getRelateTestScaleId();
    		
        	//判断相关量表是否存在
        	TestScaleInfoMessage tsiMessage = tsiMapper.getMessById(testScaleId);
        	if(tsiMessage == null){
        		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
        	}
        	Integer displayMode = tsiMessage.getDisplayMode();
    		
    		setDimensionInfo(testScaleId, tsdMessage,displayMode);
    	}
        return tsdMessage;
    }
    
 public List<TestScaleDimensionMessage> getAllDimensionByTestId(TestScaleDimensionMessage tsdMessage){
    	
    	Integer testScaleId =tsdMessage.getRelateTestScaleId();
    	
    	//判断相关量表是否存在
    	TestScaleInfoMessage tsiMessage = tsiMapper.getMessById(testScaleId);
    	if(tsiMessage == null){
    		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
    	}
    	
        List<TestScaleDimensionMessage> list = mapper.selectAll(tsdMessage);

        return list;
   }
 
 public List<TestScaleDimensionMessage> selectAll(Integer testScaleId){
        TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
        tsdMessage.setRelateTestScaleId(testScaleId);
	    return mapper.selectAll(tsdMessage);
}
    
    
    public List<TestScaleDimensionMessage> getAllMessage(TestScaleDimensionMessage tsdMessage){
    	
    	Integer testScaleId =tsdMessage.getRelateTestScaleId();
    	
    	//判断相关量表是否存在
    	TestScaleInfoMessage tsiMessage = tsiMapper.getMessById(testScaleId);
    	if(tsiMessage == null){
    		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
    	}
    	Integer displayMode = tsiMessage.getDisplayMode();
    	
        List<TestScaleDimensionMessage> list = mapper.selectAll(tsdMessage);
        if(list!=null && list.size()>0){
            for(int i=0;i<list.size();i++){
            	TestScaleDimensionMessage tsDimensionMessage = list.get(i);
            	setDimensionInfo(testScaleId, tsDimensionMessage,displayMode);
            
            }
        }

        return list;
   }

	private void setDimensionInfo(Integer testScaleId, TestScaleDimensionMessage tsDimensionMessage,Integer displayMode) {
		
		int dimensionId = tsDimensionMessage.getId();
		
		
		//设置前端展现方式
		tsDimensionMessage.setDisplayMode(displayMode);
		
		//获取人群范围与相关常模
		TestScalePopulationRangeMessage  tsprMessage = new TestScalePopulationRangeMessage();
		tsprMessage.setRelateTestScaleId(testScaleId);
		List<TestScalePopulationRangeMessage> tsprList = tsprMapper.selectAll(tsprMessage);
		if(tsprList!=null && tsprList.size()>0){
			for(int h=0;h<tsprList.size();h++){
				TestScalePopulationRangeMessage tsprMess = tsprList.get(h);
				TestScaleNormMessage tsnMessage = new TestScaleNormMessage();
				tsnMessage.setDimensionId(dimensionId);
				tsnMessage.setRelateTestScaleId(testScaleId);
				tsnMessage.setPopulationRangeId(tsprMess.getId());
				List<TestScaleNormMessage> tsnList = tsnMapper.selectAll(tsnMessage);
				if(tsnList!=null && tsnList.size()>0){
					tsprMess.setNormMessage(tsnList.get(0));
				}
			}
			
			tsDimensionMessage.setTsprList(tsprList);
		}
		
		//获取题目编号串
		String titleNumberStr = "";
		
		TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
		tsdtrMessage.setTestScaleDimensionId(dimensionId);
		List<TestScaleDimensionTitleRelationMessage> tsdtrList = tsdtrMapper.selectAll(tsdtrMessage);
		
		if(tsdtrList!=null&&tsdtrList.size()>0){
			List<Integer> dimensionTitleIdList = new ArrayList<Integer>();
			for(int j=0;j<tsdtrList.size();j++){
				dimensionTitleIdList.add(tsdtrList.get(j).getTestScaleTitleId());
			}
			
		  	TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
			tstMessage.setRelateTestScaleId(testScaleId);;
			List<TestScaleTitleMessage> tstList = tstMapper.selectAll(tstMessage);
			if(tstList!=null&&tstList.size()>0){
			    for(int k=0;k<tstList.size();k++){
			    	if(dimensionTitleIdList.contains(tstList.get(k).getId())){
			    		titleNumberStr=titleNumberStr+String.valueOf(k+1)+",";
			    	}
			    }
			}
			
		}
		if(titleNumberStr.length()>0){
			titleNumberStr = titleNumberStr.substring(0,titleNumberStr.length()-1);
		}
		
		tsDimensionMessage.setTitleNumberStr(titleNumberStr);
	}
    
	@Transactional
    public Boolean addMessage(TestScaleDimensionMessage testScaleDimensionMessage) {
    	try{
			Boolean isSuccess = false;
			
	    	Integer testScaleId = testScaleDimensionMessage.getRelateTestScaleId();
	    	
	    	//判断相关量表是否存在
	    	TestScaleInfoMessage tsiMessage = tsiMapper.getMessById(testScaleId);
	    	if(tsiMessage == null){
	    		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	    	}
	
	    	//插入维度数据
		    Date currentTime = new Date();
	        testScaleDimensionMessage.setIsDelete(0);
	        testScaleDimensionMessage.setIsEnable(0);
	        testScaleDimensionMessage.setIsShowDimension(TestScaleDimensionMessage.IS_SHOW_DIMENSION);
	        testScaleDimensionMessage.setCreateTime(currentTime);
	        testScaleDimensionMessage.setUpdateTime(currentTime);
	    	
	        mapper.insertMess(testScaleDimensionMessage);
	        int insertDimensionId = testScaleDimensionMessage.getId();
	        if(insertDimensionId == 0){
	        	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	        }
	        
	        //插入常模相关数据，每个量表，每个维度，每个人群范围唯一对应一个常模
	        List<TestScalePopulationRangeMessage> tsprList = testScaleDimensionMessage.getTsprList();
	        if(tsprList!=null && tsprList.size()>0){
		        for(int i=0;i<tsprList.size();i++){
		        	TestScalePopulationRangeMessage tsprMessage = tsprList.get(i);
		        	Integer tsprId = tsprMessage.getId();
		        	
		        	//根据传过来的人群范围id判断人群范围是否存在，不存在就报异常
		        	TestScalePopulationRangeMessage tsprMessResult = tsprMapper.getMessById(tsprId);
		        	if(tsprMessResult==null){
		        		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
		        	}	
		        	
		        	TestScaleNormMessage tsnMessage = tsprMessage.getNormMessage();
		        	if(tsnMessage!=null){
		        		
		        		//常模低分和高分必须填写，否则报异常
		        		Float lowScore = tsnMessage.getLowScore();
		        		Float highScore = tsnMessage.getHighScore();
		        		if(lowScore == null||highScore==null){
		        			throw new RuntimeException(ErrorCode.ERROR_NORM_LOW_AND_HIGH_SCORE_REQUIRED.getMessage());
		        		}
		        		
			        	tsnMessage.setRelateTestScaleId(testScaleId);
			        	tsnMessage.setPopulationRangeId(tsprId);
			        	tsnMessage.setDimensionId(insertDimensionId);
			        	tsnMessage.setIsDelete(0);
			        	tsnMessage.setIsEnable(0);
			        	tsnMessage.setCreateTime(currentTime);
			        	tsnMessage.setUpdateTime(currentTime);
			        	tsnMapper.insertMess(tsnMessage);
			        	int insertNormId = tsnMessage.getId();
			            if(insertNormId == 0){
			            	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
			            }
		        	}
		        }
	        }
	        
	        //插入维度与题目的关联
	        String titleNumberStr = testScaleDimensionMessage.getTitleNumberStr();
	        
	        List<String> titleNumberStrList = new ArrayList<String>();
	        if(titleNumberStr!=null&&!titleNumberStr.isEmpty()){
	        	titleNumberStrList = Arrays.asList(titleNumberStr.split(",")); 
	        }
	
		  	TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
			tstMessage.setRelateTestScaleId(testScaleId);;
			List<TestScaleTitleMessage> tstList = tstMapper.selectAll(tstMessage);
			if(tstList!=null && tstList.size()>0){
				for(int j=0;j<tstList.size();j++){
					int seqNum = j+1;
					if(titleNumberStrList.contains(seqNum+"")){
						TestScaleTitleMessage tsTitleMessage = tstList.get(j);
						
						TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
						tsdtrMessage.setTestScaleTitleId(tsTitleMessage.getId());
						tsdtrMessage.setTestScaleDimensionId(insertDimensionId);
						tsdtrMessage.setIsDelete(0);
						tsdtrMessage.setIsEnable(0);
						tsdtrMessage.setCreateTime(currentTime);
						tsdtrMessage.setUpdateTime(currentTime);
						tsdtrMapper.insertMess(tsdtrMessage);
						int insertTsdtrId = tsdtrMessage.getId();
			            if(insertTsdtrId == 0){
			            	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
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
    public Boolean updateMess(TestScaleDimensionMessage testScaleDimensionMessage,Integer testScaleId) {
		try{
			Boolean isSuccess = false; 
			Integer dimensionId = testScaleDimensionMessage.getId();
			
			
			//修改维度数据
	        Date currentTime = new Date();
	        testScaleDimensionMessage.setUpdateTime(currentTime);
			
	        int updateNum = mapper.updateMess(testScaleDimensionMessage);
	       	if(updateNum==0){
	       		throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	    	}
	       	
	       	//修改常模数据
	        List<TestScalePopulationRangeMessage> tsprList = testScaleDimensionMessage.getTsprList();
	        for(int i=0;i<tsprList.size();i++){
	        	TestScalePopulationRangeMessage tsprMessage = tsprList.get(i);
	        	Integer tsprId = tsprMessage.getId();
	        	
	        	//根据传过来的人群范围id判断人群范围是否存在，不存在就报异常
	        	TestScalePopulationRangeMessage tsprMessResult = tsprMapper.getMessById(tsprId);
	        	if(tsprMessResult==null){
	        		throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	        	}
	        	
	        	TestScaleNormMessage tsnMessage = tsprMessage.getNormMessage();
	        	if(tsnMessage!=null){
	        		
	        		//常模低分和高分必须填写，否则报异常
	        		Float lowScore = tsnMessage.getLowScore();
	        		Float highScore = tsnMessage.getHighScore();
	        		if(lowScore == null||highScore==null){
	        			throw new RuntimeException(ErrorCode.ERROR_NORM_LOW_AND_HIGH_SCORE_REQUIRED.getMessage());
	        		}
	        		
	            	tsnMessage.setUpdateTime(currentTime);
	            	
	            	Integer tsnId = tsnMessage.getId();
	            	if(tsnId!=null && tsnId>0){
	
	                 	int updateNormNum = tsnMapper.updateMess(tsnMessage);
	                    if(updateNormNum == 0){
	                    	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	                    }
	            	}else{
	            	 	tsnMessage.setRelateTestScaleId(testScaleId);
			        	tsnMessage.setPopulationRangeId(tsprId);
			        	tsnMessage.setDimensionId(dimensionId);
			        	tsnMessage.setIsDelete(0);
			        	tsnMessage.setIsEnable(0);
			        	tsnMessage.setCreateTime(currentTime);
			        	tsnMessage.setUpdateTime(currentTime);
			        	tsnMapper.insertMess(tsnMessage);
			        	int insertNormId = tsnMessage.getId();
			            if(insertNormId == 0){
			            	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
			            }
	            	}
	            	
	   
	        	}
	        }
	       	
	       	//修改维度与题目的关联数据
	        updateDimensionTitleRelation(testScaleDimensionMessage, dimensionId, testScaleId, currentTime);
	        
	        
			isSuccess = true;
		    return isSuccess;
		    
    	}catch(Exception e){
    		log.error(e.getMessage(), e);
    		throw new RuntimeException(e.getMessage(),e);
    	}
    }

	private void updateDimensionTitleRelation(TestScaleDimensionMessage testScaleDimensionMessage, Integer dimensionId,
			Integer testScaleId, Date currentTime) {
		
		//获取修改的题目id的list
        List<Integer> updateTitleIdList = new ArrayList<Integer>();
        String titleNumberStr = testScaleDimensionMessage.getTitleNumberStr();
        
        List<String> titleNumberStrList = new ArrayList<String>();
        if(titleNumberStr!=null&&!titleNumberStr.isEmpty()){
        	titleNumberStrList = Arrays.asList(titleNumberStr.split(",")); 
        }
        
	  	TestScaleTitleMessage tstMessage = new TestScaleTitleMessage();
		tstMessage.setRelateTestScaleId(testScaleId);
		List<TestScaleTitleMessage> tstList = tstMapper.selectAll(tstMessage);
		if(tstList!=null && tstList.size()>0){
			for(int j=0;j<tstList.size();j++){
				int seqNum = j+1;
				if(titleNumberStrList.contains(seqNum+"")){
					updateTitleIdList.add(tstList.get(j).getId());
				}	
			}
		}
		
		
		TestScaleDimensionTitleRelationMessage tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
		tsdtrMessage.setTestScaleDimensionId(dimensionId);
		List<TestScaleDimensionTitleRelationMessage>  oriTsdtrList = tsdtrMapper.selectAll(tsdtrMessage);
		
		//判断原先数据库中是否有关联，如果有，就先添加新增的关联，再删除原先的关联。
		if(oriTsdtrList!=null && oriTsdtrList.size()>0){
			
			//获取原先关联的题目id的list
			List<Integer> oriTitleIdList = new ArrayList<Integer>();
			for(int k=0;k<oriTsdtrList.size();k++){
				oriTitleIdList.add(oriTsdtrList.get(k).getTestScaleTitleId());
			}
			
			List<Integer> repetitiveIdList  = new ArrayList<Integer>();
	    	    
   	       
    	    //原有的关联保持不变,添加新增的关联
    	    if(updateTitleIdList!=null && updateTitleIdList.size()>0){
		        for(int h=0;h<updateTitleIdList.size();h++){
		        	int updateTitleId = updateTitleIdList.get(h);
		        	
		        	if(!oriTitleIdList.contains(updateTitleId)){
		        		
		        		//判断关联的题目是否存在，如果不存在那就回滚
		        		TestScaleTitleMessage tsTitleMessage = tstMapper.getMessById(updateTitleId);
		        		if(tsTitleMessage == null){
		        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		        		}
		        		
		        		TestScaleDimensionTitleRelationMessage tdtrMes = new TestScaleDimensionTitleRelationMessage();
		        		tdtrMes.setTestScaleDimensionId(dimensionId);
		        		tdtrMes.setTestScaleTitleId(updateTitleId);
		        		tdtrMes.setIsDelete(0);
		        		tdtrMes.setIsEnable(0);
		        		tdtrMes.setCreateTime(currentTime);
		        		tdtrMes.setUpdateTime(currentTime);
		        		tsdtrMapper.insertMess(tdtrMes);
		        		int insertId =tdtrMes.getId();
		        		if(insertId == 0){
		        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		        		}
		        	}else{
		        		repetitiveIdList.add(updateTitleId);
		        	}
		        }
    	    }
			
			
    	    
		      //删除去掉的关联
		      List<Integer> delIdList  = new ArrayList<Integer>();
		      for(int l=0;l<oriTsdtrList.size();l++){
		    	  TestScaleDimensionTitleRelationMessage oriMes = oriTsdtrList.get(l);
		    	  int oriTitleId = oriMes.getTestScaleTitleId();
		    	  if(!repetitiveIdList.contains(oriTitleId)){
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
          	    if(updateTitleIdList!=null && updateTitleIdList.size()>0){
      		        for(int j=0;j<updateTitleIdList.size();j++){
      		        	int udId = updateTitleIdList.get(j);
      		        	
      	        		//判断关联的题目是否存在，如果不存在那就回滚
      		        	TestScaleTitleMessage tdMessage = tstMapper.getMessById(udId);
      	        		if(tdMessage == null){
      	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
      	        		}
      		        	
      	        		TestScaleDimensionTitleRelationMessage tdtrMes = new TestScaleDimensionTitleRelationMessage();
      	        		tdtrMes.setTestScaleDimensionId(dimensionId);
      	        		tdtrMes.setTestScaleTitleId(udId);
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
	 public ResultEntity deleteMess(TestScaleDimensionMessage testScaleDimensionMessage) {
	       try{
		    	ResultEntity result = new ResultEntity(); 
		    	
		    	Integer tsdId = testScaleDimensionMessage.getId();
		    	Integer testScaleId = testScaleDimensionMessage.getRelateTestScaleId();
		    	
		    	//判断量表是否在线上，如果在线上，无法删除维度
		    	if(tsiService.isOnline(testScaleId)){
		    		result.setCode(ErrorCode.ERROR_TESTSCALE_ONLINE.getCode());
					result.setMsg(ErrorCode.ERROR_TESTSCALE_ONLINE.getMessage());
					return result;
		    	}
		    	
		    	Date currentTime = new Date();
		    	
		    	
		    	//判断定性成立条件表是否有关联维度
		    	ConditionsOfQualitativeMessage coqLeftMessage = new ConditionsOfQualitativeMessage();
	//	    	coqMessage.setLeftType(ConditionsOfQualitativeMessage.LEFT_TYPE_DIMENSION);
	//	    	coqMessage.setLeftValue(tsdId.floatValue());
		    	coqLeftMessage.setLeftDimensionId(tsdId);
		    	List<ConditionsOfQualitativeMessage> coqLeftList = coqMapper.selectAll(coqLeftMessage);
		    	if(coqLeftList!=null && coqLeftList.size()>0){
		    		result.setCode(ErrorCode.ERROR_DIMENSION_RELATE_COQ.getCode());
					result.setMsg(ErrorCode.ERROR_DIMENSION_RELATE_COQ.getMessage());
					return result;
		    	}
		    	
		    	ConditionsOfQualitativeMessage coqRightMessage = new ConditionsOfQualitativeMessage();
		    	coqRightMessage.setRightDimensionId(tsdId);
		    	List<ConditionsOfQualitativeMessage> coqRightList = coqMapper.selectAll(coqRightMessage);
		    	if(coqRightList!=null && coqRightList.size()>0){
		    		result.setCode(ErrorCode.ERROR_DIMENSION_RELATE_COQ.getCode());
					result.setMsg(ErrorCode.ERROR_DIMENSION_RELATE_COQ.getMessage());
					return result;
		    	}
		    	
	//	    	ConditionsOfQualitativeMessage coQualitativeMessage = new ConditionsOfQualitativeMessage();
	//	    	coQualitativeMessage.setRightType(ConditionsOfQualitativeMessage.RIGHT_TYPE_DIMENSION);
	//	    	coQualitativeMessage.setRightValue(tsdId.floatValue());
	//	    	List<ConditionsOfQualitativeMessage> coQualitativeList = coqMapper.selectAll(coQualitativeMessage);
	//	    	if(coQualitativeList!=null && coQualitativeList.size()>0){
	//	    		result.setCode(ErrorCode.ERROR_DIMENSION_RELATE_COQ.getCode());
	//				result.setMsg(ErrorCode.ERROR_DIMENSION_RELATE_COQ.getMessage());
	//				return result;
	//	    	}
		    	
		    	
		        //删除维度
		    	TestScaleDimensionMessage tsdMessage = new TestScaleDimensionMessage();
		    	tsdMessage.setId(tsdId);
		    	tsdMessage.setIsDelete(TestScaleDimensionMessage.IS_DELETE);
		    	tsdMessage.setUpdateTime(currentTime);
		    	int deleteTsdNum = mapper.updateMess(tsdMessage);
		    	if(deleteTsdNum==0){
		    		throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		    	}
		    	
		    	//删除常模
		    	TestScaleNormMessage tsnMessage = new TestScaleNormMessage();
		    	tsnMessage.setDimensionId(tsdId);
		    	tsnMessage.setIsDelete(TestScaleNormMessage.IS_DELETE);
		    	tsnMessage.setUpdateTime(currentTime);
		    	tsnMapper.batchDeleteMessById(tsnMessage);
		    	
		    	//删除维度题目关联
		    	TestScaleDimensionTitleRelationMessage  tsdtrMessage = new TestScaleDimensionTitleRelationMessage();
		    	tsdtrMessage.setTestScaleDimensionId(tsdId);
		    	tsdtrMessage.setIsDelete(TestScaleDimensionTitleRelationMessage.IS_DELETE);
		    	tsdtrMessage.setUpdateTime(currentTime);
		    	tsdtrMapper.batchDeleteMessByDimensionId(tsdtrMessage);
						
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

