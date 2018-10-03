package com.xinmao.sc.orderCenter.service;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderDimensionNormMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;
import com.xinmao.sc.orderCenter.domain.TestscaleQualitativeMemberWechatRelation;
import com.xinmao.sc.orderCenter.mapper.TestScaleOrderDimensionNormMapper;
import com.xinmao.sc.orderCenter.mapper.TestScaleOrderMapper;
import com.xinmao.sc.orderCenter.mapper.TestScaleOrderTitleMapper;
import com.xinmao.sc.orderCenter.mapper.TestscaleQualitativeMemberWechatRelationMapper;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;

@Service
public class TestScaleOrderService {
	
//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TestScaleOrderMapper mapper;
    
    @Autowired
    private TestScaleOrderDimensionNormMapper tsodnMapper;
    
    @Autowired
    private TestScaleOrderTitleMapper tsotMapper;
    
    @Autowired
    private TestscaleQualitativeMemberWechatRelationMapper tqmwrMapper;
    
    @Transactional
    public Boolean completeTest(TestScaleOrderMessage testScaleOrderMessage,TestScaleInfoMessage tsiMessage,String mwHeadimgurl){
	    try{
	    	 Date currentTime = new Date(); 
	    	 
	    	 //创建订单
	    	 testScaleOrderMessage.setRelateTestScaleId(tsiMessage.getId());
	    	 testScaleOrderMessage.setRelateTestScaleName(tsiMessage.getName());
	    	 testScaleOrderMessage.setRelateTestScalePrice(tsiMessage.getPrice());
	    	 testScaleOrderMessage.setRelateDisplayMode(tsiMessage.getDisplayMode());
	    	 testScaleOrderMessage.setIsDelete(0);
	    	 testScaleOrderMessage.setIsEnable(0);
	    	 testScaleOrderMessage.setCreateTime(currentTime);
	    	 testScaleOrderMessage.setUpdateTime(currentTime);
	    	 mapper.insertMess(testScaleOrderMessage);
	    	 int insertTsoId = testScaleOrderMessage.getId();
	         if(insertTsoId == 0){
	         	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	         }
	        
	         //创建维度常模
	    	 List<TestScaleOrderDimensionNormMessage>  tsodnList = testScaleOrderMessage.getTsodnList();
	    	 if(tsodnList!=null && tsodnList.size()>0){
	    		 for(int i=0;i<tsodnList.size();i++){
	    			 TestScaleOrderDimensionNormMessage tsodnMessage = tsodnList.get(i);
	    			 tsodnMessage.setTestScaleOrderId(insertTsoId);
	    			 tsodnMessage.setIsDelete(0);
	    			 tsodnMessage.setIsEnable(0);
	    			 tsodnMessage.setCreateTime(currentTime);
	    			 tsodnMessage.setUpdateTime(currentTime);
	    			 tsodnMapper.insertMess(tsodnMessage);
	    			 int insertTsodnId = tsodnMessage.getId();
	    	         if(insertTsodnId == 0){
	    	          	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	    	         }
	    		 }
	    	 }
	    	 
	        
	         //创建题目选项
	    	 List<TestScaleOrderTitleMessage> tsotList = testScaleOrderMessage.getTsotList();
	    	 if(tsotList!=null && tsotList.size()>0){
	    		 for(int i=0;i<tsotList.size();i++){
	    			 TestScaleOrderTitleMessage tsotMessage = tsotList.get(i);
	    			 tsotMessage.setTestScaleOrderId(insertTsoId);
	    			 tsotMessage.setIsDelete(0);
	    			 tsotMessage.setIsEnable(0);
	    			 tsotMessage.setCreateTime(currentTime);
	    			 tsotMessage.setUpdateTime(currentTime);
	    			 tsotMapper.insertMess(tsotMessage);
	      			 int insertTsotId = tsotMessage.getId();
	    	         if(insertTsotId == 0){
	    	          	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	    	         }
	    		 }
	    	 }
	    	 
	    	 //创建订单-定性-微信用户关联信息
	    	 Integer isNewResultPage = tsiMessage.getIsNewResultPage();
	    	 Integer mid = testScaleOrderMessage.getUserId();
	    	 if(mid!=null && mid>0 && TestScaleInfoMessage.IS_NEW_RESULT_PAGE.equals(isNewResultPage)){
	    		 
//	    		 mwHeadimgurl!=null && StringUtils.isNotEmpty(mwHeadimgurl)
	    	
		    	 Long testScaleId = Long.valueOf(testScaleOrderMessage.getRelateTestScaleId());
		    	 Long qualitativeId = Long.valueOf(testScaleOrderMessage.getRelateQualitativeId());
		    	 TestscaleQualitativeMemberWechatRelation tqmwRel = new TestscaleQualitativeMemberWechatRelation();
		    	 tqmwRel.setRelateTestScaleId(testScaleId);
		    	 tqmwRel.setMid(Long.valueOf(mid));
		    	 TestscaleQualitativeMemberWechatRelation tqmwRelation = tqmwrMapper.selectByTestScaleIdAndMid(tqmwRel);
		    	 
		    	 //判断关联是否已经存在，如果不存在就插入，已经存在就修改
		    	 if(tqmwRelation==null){
		    		 TestscaleQualitativeMemberWechatRelation tqmwr = new TestscaleQualitativeMemberWechatRelation();
		    		 tqmwr.setRelateTestScaleId(testScaleId);
		    		 tqmwr.setQualitativeId(qualitativeId);
		    		 tqmwr.setMid(mid.longValue());
		    		 tqmwr.setMwHeadimgurl(mwHeadimgurl);
		    		 tqmwr.setIsDelete((byte)0);
		    		 tqmwr.setIsEnable((byte)0);
		    		 tqmwr.setCreateTime(currentTime);
		    		 tqmwr.setUpdateTime(currentTime);
		    		 tqmwrMapper.insert(tqmwr);
		    		 Long insertTsoqId = tqmwr.getId();
	    	         if(insertTsoqId == null || insertTsoqId <= 0){
	    	          	throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
	    	         }
		    	 }else{
		    		 TestscaleQualitativeMemberWechatRelation tq = new TestscaleQualitativeMemberWechatRelation();
		    		 tq.setRelateTestScaleId(testScaleId);
		    		 tq.setMid(Long.valueOf(mid));
		    		 tq.setQualitativeId(qualitativeId);
		    		 tq.setMwHeadimgurl(mwHeadimgurl);
		    		 tq.setUpdateTime(currentTime);
		    		 Integer updateNum = tqmwrMapper.updateMessage(tq);
		    		 if(updateNum == null || updateNum <= 0){
		    	         throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
		    	     }
		    	 }
	    	 }
	    	 
	    	return true;
     	}catch(Exception e){
    		log.error(e.getMessage(), e);
    		throw new RuntimeException(e.getMessage(),e);
    	}
    }
    
    public TestScaleOrderMessage getMessageById(int id){
    	return mapper.getMessById(id);
    }
    
    public TestScaleOrderMessage getLatestOrder(TestScaleOrderMessage tsoMessage){
    	
        return mapper.getLatestOrder(tsoMessage);
        
    }
    
    public List<TestScaleOrderMessage> getAllMessageByLimit(TestScaleOrderMessage tsoMessage){
    	
        return mapper.getAllMessageByLimit(tsoMessage);
        
    }
    
    public List<TestScaleOrderMessage> getAllMessage(TestScaleOrderMessage tsoMessage){
    	
        return mapper.selectAll(tsoMessage);
        
    }
    
    public int getTestScaleOrderNum(){
    	return mapper.getTestScaleOrderNum();
    }
    
    
    public int getTestScaleOrderNumByCondition(TestScaleOrderMessage testScaleOrderMessage) {
        return mapper.getTestScaleOrderNumByCondition(testScaleOrderMessage);
    }
    
    public int addMessage(TestScaleOrderMessage testScaleOrderMessage) {
        return mapper.insertMess(testScaleOrderMessage);
    }
    
    public int updateMess(TestScaleOrderMessage testScaleOrderMessage) {
        return mapper.updateMess(testScaleOrderMessage);
    }
}

