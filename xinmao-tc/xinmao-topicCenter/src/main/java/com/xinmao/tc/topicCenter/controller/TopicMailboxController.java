package com.xinmao.tc.topicCenter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.xinmao.tc.entity.ErrorCode;
import com.xinmao.tc.entity.ResultEntity;
import com.xinmao.tc.topicCenter.api.TopicMailboxApi;
import com.xinmao.tc.topicCenter.domain.TopicMailbox;
import com.xinmao.tc.topicCenter.service.TopicMailboxService;


/**
 * 话题关键词Controller
 * @param 李志坚
 * @param 2018.6.6
 */
@RestController
//@RequestMapping("topicMailbox")
public class TopicMailboxController implements TopicMailboxApi{
	
//	Logger log = Logger.getLogger(this.getClass());
	
	private static final Logger log = LogManager.getLogger();

    @Autowired
    private TopicMailboxService service;
    
    
    
    /**
	 * 前端获取信箱列表
	 * @param 李志坚
	 * @param 2018.6.6
	 */
    @RequestMapping(value="/getAllMessageFromReception",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessageFromReception(TopicMailbox topicMailbox){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TopicMailbox> list = new ArrayList<TopicMailbox>();
    	Integer count = 0;
    	try{
    		Integer pageIndex = topicMailbox.getPageIndex();
    		Integer pageSize = topicMailbox.getPageSize();
  		    if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
  		    PageHelper.startPage(pageIndex, pageSize);
	        list = service.getAllMessageFromReception(topicMailbox);
	        count = service.getAllCount(topicMailbox);
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("topicMailboxList", list);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
	/**
	 * 后台获取信箱列表
	 * @param 李志坚
	 * @param 2018.6.6
	 */
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TopicMailbox topicMailbox){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TopicMailbox> list = new ArrayList<TopicMailbox>();
    	Integer count = 0;
    	try{
    		Integer pageIndex = topicMailbox.getPageIndex();
    		Integer pageSize = topicMailbox.getPageSize();
  		    if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
  		    PageHelper.startPage(pageIndex, pageSize);
	        list = service.getAllMessage(topicMailbox);
	        count = service.getAllCount(topicMailbox);
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("topicMailboxList", list);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    /**
	 * 获取单个信箱信息
	 * @param 李志坚
	 * @param 2018.6.6
	 */
    @RequestMapping(value="/getSingleMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getSingleMessage(TopicMailbox topicMailbox){
    	
    	ResultEntity result = new ResultEntity(); 
    	TopicMailbox tMailbox = new TopicMailbox();
    	try{
    		Long id = topicMailbox.getId();
  		    if(id==null || id<=0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
  		    tMailbox = service.getSingleMessage(topicMailbox);
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("topicMailbox", tMailbox);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    

  /**
	 * 标记/取消标记
	 * @param 李志坚
	 * @param 2018.6.6
	 */
  @RequestMapping(value="/updateMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
  public Object updateMessage(TopicMailbox topicMailbox){
  	
  	ResultEntity result = new ResultEntity(); 
  	try{
  		Long id = topicMailbox.getId();
  		Byte isMark = topicMailbox.getIsMark();
  		if(id ==null || id<=0 || isMark==null ||
  		  (!TopicMailbox.is_MARK.equals(isMark) && !TopicMailbox.is_NOT_MARK.equals(isMark))){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		}
  		Date currentTime = new Date();
  		topicMailbox.setUpdateTime(currentTime);
  		int updateNum = service.updateMessage(topicMailbox);
        if(updateNum<=0){
        	log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
			return result;
        }
	        
  	}catch(Exception e){
		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
		result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
		result.setMsg(e.getMessage());
		return result;
  	}
	result.setCode(ErrorCode.SUCCESS.getCode());
	result.setMsg(ErrorCode.SUCCESS.getMessage());
	return result;
  }
    

	/**
	 * 写信
	 * @param 李志坚
	 * @param 2018.6.7
	 */
    @RequestMapping(value="/addMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(TopicMailbox topicMailbox){
    	
    	ResultEntity result = new ResultEntity(); 
    	try{
    		Long mid = topicMailbox.getMid();
    		String content = topicMailbox.getContent();
    		
    		if(mid==null || mid<=0 || content==null || StringUtils.isEmpty(content)){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
    		
    		Date currentTime = new Date();
    		topicMailbox.setCreateTime(currentTime);
    		topicMailbox.setUpdateTime(currentTime);
    		topicMailbox.setIsDelete((byte)0);
    		topicMailbox.setIsEnable((byte)0);
    		topicMailbox.assembleScreenshot();
	        service.addMessage(topicMailbox);
	        Long insertId = topicMailbox.getId();
	        if(insertId==null || insertId<=0){
	        	log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage()+",insertId = "+insertId);
				result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
				return result;
	        }
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    /**
	 * 修改回复状态
	 * @param 李志坚
	 * @param 2018.6.12
	 */
  public Integer updateStatus(@RequestBody TopicMailbox topicMailbox){
    Integer updateNum = 0;
  	try{
  		Long id = topicMailbox.getId();
  		Byte status = topicMailbox.getStatus();
  		if(id ==null || id<=0 || status==null ||
  		  (!TopicMailbox.is_Reply.equals(status) && !TopicMailbox.is_NOT_Reply.equals(status))){
  			log.error(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
			return null;
		}
  		Date currentTime = new Date();
  		topicMailbox.setUpdateTime(currentTime);
  		updateNum = service.updateMessage(topicMailbox);
        if(updateNum<=0){
        	log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
			return null;
        }
	        
  	}catch(Exception e){
		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
		return null;
  	}
	return updateNum;
  }
    
    
}

