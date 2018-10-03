package com.xinmao.sc.testCenter.service;


import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
import com.xinmao.sc.testCenter.domain.TestScaleClassRelationMessage;
import com.xinmao.sc.testCenter.mapper.TestScaleClassMapper;
import com.xinmao.sc.testCenter.mapper.TestScaleClassRelationMapper;



@Service
public class TestScaleClassService {
	
//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TestScaleClassMapper mapper;
    
    @Autowired
    private TestScaleClassRelationMapper tscrMapper;
    
//    public List<TestScaleClassMessage> getAllMessageFromFront(TestScaleClassMessage tscMessage){
//        List<TestScaleClassMessage> list = mapper.getAllMessageFromFront(tscMessage);
//        return list;
//    }
//    
    
    public List<TestScaleClassMessage> getAllMessageByIdList(TestScaleClassMessage testScaleClassMessage){
        List<TestScaleClassMessage> list = mapper.getAllMessageByIdList(testScaleClassMessage);
        return list;
   }
    
    public TestScaleClassMessage getMessageById(int id){
    	return mapper.getMessById(id);
    }
    
    public TestScaleClassMessage getMessByIdFromFront(int id){
    	return mapper.getMessByIdFromFront(id);
    }
    
    public List<TestScaleClassMessage> getHomeRecommMessage(TestScaleClassMessage tscMessage){
        return mapper.getHomeRecommMessage(tscMessage);
    }
    
    public List<TestScaleClassMessage> getAllMessage(){
        List<TestScaleClassMessage> list = mapper.selectAll();
        return list;
    }
    
    public int addMessage(TestScaleClassMessage testScaleClassMessage) {
        return mapper.insertMess(testScaleClassMessage);
    }
    
    public int updateMess(TestScaleClassMessage testScaleClassMessage) {
        return mapper.updateMess(testScaleClassMessage);
    }
    
    @Transactional
	 public ResultEntity deleteMess(TestScaleClassMessage testScaleClassMessage) {
	       
    	try{
	    	ResultEntity result = new ResultEntity(); 
	    	
	    	Integer tscId = testScaleClassMessage.getId();
	    	
	    	//判断量表分类是否被首页推荐，如果是，无法删除分类
	    	if(isHomeRecomm(tscId)){
	    		result.setCode(ErrorCode.ERROR_CLASS_HOME_RECOMM.getCode());
				result.setMsg(ErrorCode.ERROR_CLASS_HOME_RECOMM.getMessage());
				return result;
	    	}
	    	
	    	Date currentTime = new Date();
	    	
	    	//删除分类
	    	TestScaleClassMessage tscMessage = new TestScaleClassMessage();
	    	tscMessage.setId(tscId);
	    	tscMessage.setIsDelete(TestScaleClassMessage.IS_DELETE);
	    	tscMessage.setUpdateTime(currentTime);
	    	int updateClassNum = mapper.updateMess(tscMessage);
	       	if(updateClassNum==0){
		       throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		    }
	    	
	        //删除分类量表关联
	       	TestScaleClassRelationMessage  tscrMessage = new TestScaleClassRelationMessage();
	       	tscrMessage.setTestScaleClassId(tscId);
	       	tscrMessage.setIsDelete(TestScaleClassRelationMessage.IS_DELETE);
	       	tscrMessage.setUpdateTime(currentTime);
	       	tscrMapper.batchDeleteMessByClassId(tscrMessage);
					
	    	result.put("isSuccess", true);
			result.setCode(ErrorCode.SUCCESS.getCode());
			result.setMsg(ErrorCode.SUCCESS.getMessage());
		    return result;
		    
    	}catch(Exception e){
    		log.error(e.getMessage(), e);
    		throw new RuntimeException(e.getMessage(),e);
    	}
	    	
	}
    
    public Boolean isHomeRecomm(int id){
    	
        Boolean isHomeRecomm = false;
        TestScaleClassMessage tscMessage = mapper.getMessById(id);
        
        if(tscMessage!=null){
        	  Integer homeRecomm = tscMessage.getHomeRecomm();
        	  if(homeRecomm!=null && homeRecomm==TestScaleClassMessage.IS_HOME_RECOMM){
        		  isHomeRecomm =true;
        	  }
        }
        
        return isHomeRecomm;
   }
}

