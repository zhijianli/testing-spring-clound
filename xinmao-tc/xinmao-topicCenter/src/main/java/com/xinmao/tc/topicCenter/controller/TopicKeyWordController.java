package com.xinmao.tc.topicCenter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.xinmao.tc.entity.ErrorCode;
import com.xinmao.tc.entity.ResultEntity;
import com.xinmao.tc.topicCenter.domain.Topic;
import com.xinmao.tc.topicCenter.domain.TopicKeyWord;
import com.xinmao.tc.topicCenter.service.TopicKeyWordService;
import com.xinmao.tc.topicCenter.service.TopicService;


/**
 * 话题关键词Controller
 * @param 李志坚
 * @param 2018.6.6
 */
@RestController
@RequestMapping("topicKeyWord")
public class TopicKeyWordController {
	
//	Logger log = Logger.getLogger(this.getClass());
	
	private static final Logger log = LogManager.getLogger();

    @Autowired
    private TopicKeyWordService service;
    
	/**
	 * 获取话题列表
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TopicKeyWord topicKeyWord){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<TopicKeyWord> list = new ArrayList<TopicKeyWord>();
    	Integer count = 0;
    	try{
    		Integer pageIndex = topicKeyWord.getPageIndex();
    		Integer pageSize = topicKeyWord.getPageSize();
  		    if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
  		    PageHelper.startPage(pageIndex, pageSize);
	        list = service.getAllMessage(topicKeyWord);
	        count = service.getAllCount(topicKeyWord);  ;
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("topicKeyWordList", list);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    

	/**
	 * 新增关键词
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value="/addMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(TopicKeyWord topicKeyWord){
    	
    	ResultEntity result = new ResultEntity(); 
    	try{
    		
    		String keyWord = topicKeyWord.getKeyWord();
    		if(keyWord==null || StringUtils.isEmpty(keyWord)){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
    		
    		Date currentTime = new Date();
    		topicKeyWord.setCreateTime(currentTime);
    		topicKeyWord.setUpdateTime(currentTime);
    		topicKeyWord.setIsDelete((byte)0);
    		topicKeyWord.setIsEnable((byte)0);
    		
	        service.addMessage(topicKeyWord);
	        Long insertId = topicKeyWord.getId();
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
	 * 编辑关键词
	 * @param 李志坚
	 * @param 2018.6.6
	 */
    @RequestMapping(value="/updateMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(TopicKeyWord topicKeyWord){
    	
    	ResultEntity result = new ResultEntity(); 
    	try{
    		Long id = topicKeyWord.getId();
    		String keyWord = topicKeyWord.getKeyWord();
    		if(id ==null || id<=0 || keyWord==null || StringUtils.isEmpty(keyWord)){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
    		Date currentTime = new Date();
    		topicKeyWord.setUpdateTime(currentTime);
    		int updateNum = service.updateMessage(topicKeyWord);
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
    
}

