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
import com.xinmao.common.userOperationCenter.domain.CommonComment;
import com.xinmao.tc.entity.ErrorCode;
import com.xinmao.tc.entity.ResultEntity;
import com.xinmao.tc.topicCenter.domain.Topic;
import com.xinmao.tc.topicCenter.domain.TopicKeyWord;
import com.xinmao.tc.topicCenter.service.CommonCommentService;
import com.xinmao.tc.topicCenter.service.TopicService;


/**
 * 话题Controller
 * @param 李志坚
 * @param 2018.6.5
 */
@RestController
@RequestMapping("topic")
public class TopicController {
	
//	Logger log = Logger.getLogger(this.getClass());
	
	private static final Logger log = LogManager.getLogger();

    @Autowired
    private TopicService service;
    
    @Autowired
    private CommonCommentService commonCommentService;
    
    
    /**
	 * 前端获取话题列表
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value="/getHomePageTopicList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getHomePageTopicList(Topic topic){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<Topic> list = new ArrayList<Topic>();
    	Integer count = 0;
    	try{
    		Integer pageIndex = topic.getPageIndex();
    		Integer pageSize = topic.getPageSize();
    		if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
	        list = service.getHomePageTopicList(topic);
	        count = service.getHomePageTopicCount(topic);
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("homePageTopicList", list);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    /**
   	 * 前端获取排序后指定话题的前后话题集合
   	 * @param 李志坚
   	 * @param 2018.6.5
   	 */
       @RequestMapping(value="/getFrontAndBackTopicList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
       public Object getFrontAndBackTopicList(Topic topic){
       	
       	ResultEntity result = new ResultEntity(); 
       	List<Topic> frontAndBackTopicList = new ArrayList<Topic>();
       	try{
       		Long topicId = topic.getId();
       		if(topicId==null || topicId<=0){
 		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
 				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
 				return result;
     		}
       		List<Topic> topicList = service.getSortedTopicList();
	   	    this.configFrontAndBackTopicList(frontAndBackTopicList, topicId, topicList);
   	        
       	}catch(Exception e){
   			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
   			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
   			result.setMsg(e.getMessage());
   			return result;
       	}
       	result.put("frontAndBackTopicList", frontAndBackTopicList);
   		result.setCode(ErrorCode.SUCCESS.getCode());
   		result.setMsg(ErrorCode.SUCCESS.getMessage());
   		return result;
       }

	private void configFrontAndBackTopicList(List<Topic> frontAndBackTopicList, Long topicId,
			List<Topic> topicList) {
		Integer index = null;
		Integer topicListSize = topicList.size();
		for(int i=0;i<topicListSize;i++){
			Topic tp = topicList.get(i);
			Long tpId = tp.getId();
			if(topicId.equals(tpId)){
				index = i;
				break;
			}
		}
		if(index!=null){
			
			//获取当前话题后面的九个话题
			for(int i=0;i<10;i++){
				if(index+i>=topicListSize){
				    break;
				}
				frontAndBackTopicList.add(topicList.get(index+i));
			}
			
//			if(index>0){
//				frontAndBackTopicList.add(topicList.get(index-1));
//				topicIndex = 1;
//			}else{
//				topicIndex = 0;
//			}
//			frontAndBackTopicList.add(topicList.get(index));
//			if(index<topicListSize-1){
//				frontAndBackTopicList.add(topicList.get(index+1));
//			}
		}
	}
    
    
    /**
   	 * 前端获取专家评论的话题列表
   	 * @param 李志坚
   	 * @param 2018.6.12
   	 */
       @RequestMapping(value="/getPsychoCommentTopicList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
       public Object getPsychoCommentTopicList(Integer pageIndex,Integer pageSize,Long mid){
       	
       	ResultEntity result = new ResultEntity(); 
       	List<Topic> list = new ArrayList<Topic>();
       	Integer count = 0;
       	try{
       		if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0 || mid==null || mid<=0){
 		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
 				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
 				return result;
     	    }
       		CommonComment commonComment = new CommonComment();
       		commonComment.setMid(mid);
       		List<Long> topicIdList = commonCommentService.getTopicIdListByMid(commonComment);
       		if(topicIdList!=null && topicIdList.size()>0){
       			
       			List<Long> pagingIdList = new ArrayList<Long>();
       			
       		    //获取分页数据
    			Integer startIndex = (pageIndex-1)*pageSize;
    			Integer endIndex = pageIndex*pageSize;
    			for(int i=startIndex;i<endIndex;i++){
    				if(i>=topicIdList.size()){
    					break;
    				}
    				pagingIdList.add(topicIdList.get(i));
    			}
    			Topic topic = new Topic();
    			topic.setSearchIdList(pagingIdList);
    			topic.setStatus(Topic.is_DISPLAY_STATUS);
    			list = service.getListByIdList(topic);
    			
    			count = topicIdList.size();
    		}
       	}catch(Exception e){
   			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
   			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
   			result.setMsg(e.getMessage());
   			return result;
       	}
       	result.put("psychoCommentTopicList", list);
       	result.put("count", count);
   		result.setCode(ErrorCode.SUCCESS.getCode());
   		result.setMsg(ErrorCode.SUCCESS.getMessage());
   		return result;
       }
    
	/**
	 * 后台获取话题列表
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(Integer pageSize,Integer pageIndex,String keyWord){
    	
    	ResultEntity result = new ResultEntity(); 
    	List<Topic> list = new ArrayList<Topic>();
    	Integer count = 0;
    	try{
  		    if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
	        list = service.getAllMessage(pageSize, pageIndex, keyWord);
	        count =  service.getAllCount(pageSize, pageIndex, keyWord);
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("topicList", list);
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    /**
	 * 获取单个话题
	 * @param 李志坚
	 * @param 2018.6.6
	 */
    @RequestMapping(value="/getSingleMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getSingleMessage(Long topicId){
    	
    	ResultEntity result = new ResultEntity(); 
    	Topic topic = new Topic();
    	try{
  		    if(topicId==null || topicId<=0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
  		    topic = service.getSingleMessage(topicId);
	        
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("topic", topic);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }

	/**
	 * 新增话题
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value="/addMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(Topic topic){
    	
    	ResultEntity result = new ResultEntity(); 
    	try{
    		
    		String title = topic.getTitle();
    		if(title==null || StringUtils.isEmpty(title)){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
    		
    		
    		Date currentTime = new Date();
    		topic.setCreateTime(currentTime);
    		topic.setUpdateTime(currentTime);
    		topic.setIsDelete((byte)0);
    		topic.setIsEnable((byte)0);
    		topic.setStatus(Topic.is_NOT_DISPLAY_STATUS);
    		topic.setIsTop(Topic.IS_NOT_TOP);
    		
	        service.addMessage(topic);
	        Long insertId = topic.getId();
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
	 * 修改今日话题
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value="/editTopic",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object editTopic(Topic topic){
    	
    	ResultEntity result = new ResultEntity(); 
    	try{
    		Long id = topic.getId();
    		if(id ==null || id<=0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
    		
	        if(!service.editTopic(topic)){
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
	 * 修改显示状态
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value="/updateDisplayStatus",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateDisplayStatus(Topic topic){
    	
    	ResultEntity result = new ResultEntity(); 
    	try{
    		Byte status = topic.getStatus();
    		Long id = topic.getId();
    		if(id ==null || id<=0 ||status==null || 
    		  (!Topic.is_DISPLAY_STATUS.equals(status) && !Topic.is_NOT_DISPLAY_STATUS.equals(status))){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
    		
    		Date currentTime = new Date();
    		topic.setUpdateTime(currentTime);
    		
	        int updateNum = service.updateMessage(topic);
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
	 * 设置置顶
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value="/setTop",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object setTop(Topic topic){
    	
    	ResultEntity result = new ResultEntity(); 
    	try{
    		Byte isTop = topic.getIsTop();
    		Long id = topic.getId();
    		if(id ==null || id<=0 || isTop==null || 
    		  (!Topic.IS_TOP.equals(isTop) && !Topic.IS_NOT_TOP.equals(isTop))){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
    		
    		Date currentTime = new Date();
    		topic.setUpdateTime(currentTime);
    		if(Topic.IS_TOP.equals(isTop)){
    		  topic.setSetTopTime(currentTime);
    		}else{
    		  topic.setSetTopTime(null);
    		}
    		
	        int updateNum = service.setTop(topic);
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
	 * 阅读量+1
	 * @param 李志坚
	 * @param 2018.6.8
	 */
    @RequestMapping(value="/addReadingVolume",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addReadingVolume(Topic topic){
    	
    	ResultEntity result = new ResultEntity(); 
    	try{
    		Long id = topic.getId();
    		Integer readingVolume = topic.getReadingVolume();
    		if(id ==null || id<=0 || readingVolume==null || readingVolume<=0){
  		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
  				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
  				return result;
  		    }
    		
    		Date currentTime = new Date();
    		topic.setUpdateTime(currentTime);
    		
	        int updateNum = service.addReadingVolume(topic);
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

