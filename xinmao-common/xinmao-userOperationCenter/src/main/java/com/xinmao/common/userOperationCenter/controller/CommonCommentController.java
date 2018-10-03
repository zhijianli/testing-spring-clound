package com.xinmao.common.userOperationCenter.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctc.wstx.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;
import com.xinmao.common.userOperationCenter.api.CommonCommentApi;
import com.xinmao.common.userOperationCenter.domain.CommonComment;
import com.xinmao.common.userOperationCenter.domain.CommonMemberPraiseRelation;
import com.xinmao.common.userOperationCenter.service.CommonCommentService;
import com.xinmao.common.userOperationCenter.service.CommonMemberPraiseRelationService;
import com.xinmao.common.userOperationCenter.service.TopicMailboxService;
import com.xinmao.tc.topicCenter.domain.TopicMailbox;

@RestController
//@RequestMapping("commonComment")
public class CommonCommentController implements CommonCommentApi{ 
	
	Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CommonCommentService service;
    
    @Autowired
    private CommonMemberPraiseRelationService cmprService;
    
    @Autowired
    private TopicMailboxService topicMailboxService;
    
    
    public Integer getTestScaleCommentNum(@RequestBody CommonComment commonComment){
         
    	Integer count =null;
    	try{
    		Long articleId = commonComment.getArticleId();
    		
		    if(articleId == null || articleId ==0){
		    	log.error(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage()+",articleId="+articleId);
				return null;
		    }
		    commonComment.setSource(CommonComment.SOURCE_TEST_SCALE);
		    
		    count = service.getTestScaleCommentNum(commonComment);
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
		return count;
    }
    
    
    @RequestMapping(value = "add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(CommonComment commonComment){
         
    	ResultEntity result = new ResultEntity(); 
    	Long commentId = 0l;
    	try{
    		
    		Long articleId = commonComment.getArticleId();
    		Long mid = commonComment.getMid();
    		String mName = commonComment.getmName();
    		String mHeadPortrait = commonComment.getmHeadPortrait();
    		String commentContent = commonComment.getCommentContent();
    		Long parentCommentId = commonComment.getParentCommentId();
    		
    		if(articleId==null || articleId==0||
    		   mid==null || mid==0||
    		   commentContent==null || commentContent.isEmpty()||
    		   mName==null || mName.isEmpty()||
    		   mHeadPortrait==null || mHeadPortrait.isEmpty()||
    		   parentCommentId==null){
		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		
		    Date currentTime = new Date();
		    commonComment.setIsDelete(0);
		    commonComment.setCommentTime(currentTime);
		    commonComment.setUpdateTime(currentTime);
		    commonComment.setSource(CommonComment.SOURCE_TEST_SCALE);
	     	
	        service.addMessage(commonComment);
	        
	        commentId = commonComment.getCommentId();
	        if(commentId==null || commentId==0){
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
    	result.put("commentId",commentId);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "getAllMessage",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(CommonComment commonComment,HttpServletResponse response){
         
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	ResultEntity result = new ResultEntity(); 
    	 List<CommonComment> commonCommentList = new ArrayList<CommonComment>(); 
    	 Integer count =0;
    	try{
    		Long articleId = commonComment.getArticleId();
    		Integer pageIndex = commonComment.getPageIndex();
    		Integer pageSize = commonComment.getPageSize();
		    if(articleId == null || articleId ==0 ||
		       pageIndex==null || pageIndex==0 || 
		       pageSize==null || pageSize==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    commonComment.setSource(CommonComment.SOURCE_TEST_SCALE);
		    
		    PageHelper.startPage(pageIndex, pageSize);
		    commonCommentList = service.getAllMessage(commonComment);
		    
		    //设置父评论的用户名字
		    if(commonCommentList!=null && commonCommentList.size()>0){
		    	List<Long> parentCommentIdList = new ArrayList<Long>();
		    	for(int i=0;i<commonCommentList.size();i++){
		    		CommonComment cct = commonCommentList.get(i);
		    		Long parentCommentId = cct.getParentCommentId();
		    		if(parentCommentId!=null && parentCommentId!=0){
		    			parentCommentIdList.add(parentCommentId);
		    		}
		    	}
		    	
		    	if(parentCommentIdList!=null&&parentCommentIdList.size()>0){
		    		
		    		Map<Long,String> parentIdNameMap = new HashMap<Long,String>();
		    		CommonComment cComment = new CommonComment();
		    		cComment.setSearchList(parentCommentIdList);
		    		List<CommonComment> parentCommentList = service.getMessageBySearchList(cComment);
		    		if(parentCommentList!=null && parentCommentList.size()>0){
		    			for(int j=0;j<parentCommentList.size();j++){
		    				CommonComment parentComment = parentCommentList.get(j);
		    				parentIdNameMap.put(parentComment.getCommentId(),parentComment.getmName());
		    			}
		    			for(int h=0;h<commonCommentList.size();h++){
		    				CommonComment comComment = commonCommentList.get(h);
		    				Long pCommentId = comComment.getParentCommentId();
		    				String parentName = parentIdNameMap.get(pCommentId);
		    				if(parentName!=null && !StringUtils.isBlank(parentName)){
		    					comComment.setParentCommentName(parentName);
		    				}
		    			}
		    		}
		    	}
		    }
		    
		    
		    //获取评论数
		    CommonComment cct = new CommonComment();
		    cct.setArticleId(articleId);
		    cct.setSource(CommonComment.SOURCE_TEST_SCALE);
		    count = service.getTestScaleCommentNum(cct);
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("count",count);
    	result.put("commonCommentList",commonCommentList);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "getTopicSingleMessage",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getTopicSingleMessage(CommonComment commonComment){
         
    	ResultEntity result = new ResultEntity(); 
    	CommonComment cComment = new CommonComment();
    	try{
    		Long mid = commonComment.getMid();
    		Long articleId = commonComment.getArticleId();
		    if(mid == null || mid <= 0 || articleId == null || articleId <= 0 ){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    
		    CommonComment comment = new CommonComment();
		    comment.setArticleId(articleId);
		    comment.setMid(mid);
		    comment.setSource(CommonComment.SOURCE_TOPIC);
    		List<CommonComment> commonCommentList = service.getCommonCommentListByMidAndArticleId(comment);
    		if(commonCommentList!=null && commonCommentList.size()>0){
    			cComment = commonCommentList.get(0);
    		}
//		    cComment = service.getSingleMessage(commonComment);
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("commonComment",cComment);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "getTopicComment",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getTopicComment(CommonComment commonComment){
         
    	ResultEntity result = new ResultEntity();
    	List<CommonComment> commonCommentList = new ArrayList<CommonComment>();
    	Integer count =0;
    	Boolean isAnswered =false;
    	Long updateCommentId =null;
    	try{
    		Long articleId = commonComment.getArticleId();
    		Integer pageIndex = commonComment.getPageIndex();
    		Integer pageSize = commonComment.getPageSize();
    		Long mid = commonComment.getMid();
		    if(articleId == null || articleId ==0 ||pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode()); 
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    commonComment.setSource(CommonComment.SOURCE_TOPIC);
		    
		    PageHelper.startPage(pageIndex, pageSize);
		    commonCommentList = service.getCommentList(commonComment);
		    
		    //获取评论数
		    CommonComment cct = new CommonComment();
		    cct.setArticleId(articleId);
		    cct.setStatus(commonComment.getStatus());
		    cct.setSource(CommonComment.SOURCE_TOPIC);
		    count = service.getCommentNum(cct);
		    
		    //判断是添加回答还是修改回答
		    if(mid!=null && mid>0){
	    	    CommonComment comment = new CommonComment();
	    	    comment.setArticleId(articleId);
	    	    comment.setMid(mid);
	    	    comment.setSource(CommonComment.SOURCE_TOPIC);
	    	    comment.setStatus(CommonComment.IS_DISPLAY_STATUS);
		    	List<CommonComment> commentList = service.getCommonCommentListByMidAndArticleId(comment);
		        if(commentList!=null && commentList.size()>0){
		        	isAnswered = true;
		        	updateCommentId = commentList.get(0).getCommentId();
		        }
		    }
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("count",count);
    	result.put("commonCommentList",commonCommentList);
    	result.put("isAnswered",isAnswered);
    	result.put("updateCommentId",updateCommentId);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    public List<Long> getTopicIdListByMid(@RequestBody CommonComment commonComment){
    	List<Long> topicIdList = new ArrayList<Long>();
    	try{
    		Long mid = commonComment.getMid();
		    if(mid == null || mid<=0){
		    	return null;
		    }
		    commonComment.setSource(CommonComment.SOURCE_TOPIC);
		    topicIdList = service.getTopicIdListByMid(commonComment);
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
    	return topicIdList;
    }
    
    
    @RequestMapping(value = "addTopicDraftComment",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addTopicDraftComment(CommonComment commonComment){
         
    	ResultEntity result = new ResultEntity(); 
    	Long commentId = 0l;
    	try{
    		
    		Long articleId = commonComment.getArticleId();
    		Long mid = commonComment.getMid();
    		String mName = commonComment.getmName();
    		String mHeadPortrait = commonComment.getmHeadPortrait();
    		String draft = commonComment.getDraft();
    		
    		if(articleId==null || articleId==0||
    		   mid==null || mid==0||
    		   draft==null || draft.isEmpty()||
    		   mName==null || mName.isEmpty()||
    		   mHeadPortrait==null || mHeadPortrait.isEmpty()){
		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		
    		//判断是否已经有数据
    		CommonComment cComment = new CommonComment();
    		cComment.setArticleId(articleId);
    		cComment.setMid(mid);
    		cComment.setSource(CommonComment.SOURCE_TOPIC);
    		List<CommonComment> commonCommentList = service.getCommonCommentListByMidAndArticleId(cComment);
    		
    		Date currentTime = new Date();
    		
    		//如果已经有数据，就修改数据
    		if(commonCommentList!=null && commonCommentList.size()>0){
    			commentId = commonCommentList.get(0).getCommentId();
    			CommonComment comment = new CommonComment();
    			comment.setCommentId(commentId);
    			comment.setUpdateTime(currentTime);
    			comment.setDraft(draft);
    			Integer updateNum = service.updateMessage(comment);
    			if(updateNum==null || updateNum==0){
     	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
     				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
     				return result;
     	        }
    		}else{//如果没有数据，就添加数据
    			
    		    commonComment.setIsDelete(0);
    		    commonComment.setCommentTime(currentTime);
    		    commonComment.setUpdateTime(currentTime);
    		    commonComment.setSource(CommonComment.SOURCE_TOPIC);
    		    commonComment.setParentCommentId(CommonComment.DEFAULT_PARENT_COMMENT_ID);
    		    commonComment.setStatus(CommonComment.IS_NOT_DISPLAY_STATUS);
    	        service.addMessage(commonComment);
    	        
    	        commentId = commonComment.getCommentId();
    	        if(commentId==null || commentId==0){
    	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
    				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
    				return result;
    	        }
    		}
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("commentId",commentId);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    @RequestMapping(value = "releaseTopicComment",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object releaseTopicComment(CommonComment commonComment){
         
    	ResultEntity result = new ResultEntity(); 
    	Long commentId = 0l;
    	try{
    		Long articleId = commonComment.getArticleId();
    		Long mid = commonComment.getMid();
    		String commentContent = commonComment.getCommentContent();
    		
    		if(articleId==null || articleId==0||
    		   mid==null || mid==0||
    		   commentContent==null || commentContent.isEmpty()){
		       	result.setCode(ErrorCode.ERROR_COMMENT_IS_NOT_EMPTY.getCode());
				result.setMsg(ErrorCode.ERROR_COMMENT_IS_NOT_EMPTY.getMessage());
				return result;
    		}
    		
    		//判断是否已经回复过
    		CommonComment cComment = new CommonComment();
    		cComment.setArticleId(articleId);
    		cComment.setMid(mid);
    		cComment.setSource(CommonComment.SOURCE_TOPIC);
    		List<CommonComment> commonCommentList = service.getCommonCommentListByMidAndArticleId(cComment);
    		if(commonCommentList==null || commonCommentList.size()<=0){
    			result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		commentId = commonCommentList.get(0).getCommentId();
    		
		    Date currentTime = new Date();
		    CommonComment comment = new CommonComment();
		    comment.setCommentId(commentId);
		    comment.setCommentContent(commentContent);
		    comment.setUpdateTime(currentTime);
		    
		    String oriCommentContent = commonCommentList.get(0).getCommentContent();
		    if(oriCommentContent==null || StringUtils.isEmpty(oriCommentContent)){
		    	comment.setStatus(CommonComment.IS_DISPLAY_STATUS);
		    }
	     	
	        Integer updateNum = service.updateMessage(comment);
	        if(updateNum==null || updateNum<=0){
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
    	result.put("commentId",commentId);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
//    @RequestMapping(value = "updateTopicCommentContent",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
//    public Object updateTopicCommentContent(CommonComment commonComment){
//         
//    	ResultEntity result = new ResultEntity(); 
//    	try{
//    		
//    		Long commentId = commonComment.getCommentId();
//    		String commentContent = commonComment.getCommentContent();
//    		
//    		if(commentId ==null || commentId<=0 || commentContent==null || commentContent.isEmpty()){
//		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//				return result;
//    		}
//    		Date currentTime = new Date();
//    		commonComment.setUpdateTime(currentTime);
//    		int updateNum = service.updateMessage(commonComment);
//	        if(updateNum<=0){
//	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
//				return result;
//	        }
//    	}catch(Exception e){
//    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
//			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//			result.setMsg(e.getMessage());
//			return result;
//    	}
//		result.setCode(ErrorCode.SUCCESS.getCode());
//		result.setMsg(ErrorCode.SUCCESS.getMessage());
//		return result;
//    }
    
    /**
	 * 回复皆有杂货铺信箱
	 * @param 李志坚
	 * @param 2018.6.5
	 */
    @RequestMapping(value = "addTopicMailboxComment",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addTopicMailboxComment(CommonComment commonComment){
         
    	ResultEntity result = new ResultEntity(); 
    	Long commentId = 0l;
    	try{
    		
    		Long articleId = commonComment.getArticleId();
    		Long mid = commonComment.getMid();
    		String commentContent = commonComment.getCommentContent();
    		
    		if(articleId==null || articleId==0|| mid==null || mid==0|| commentContent==null || commentContent.isEmpty()){
		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		
    		//判断是否已经回复过
    		CommonComment cComment = new CommonComment();
    		cComment.setArticleId(articleId);
    		cComment.setMid(mid);
    		cComment.setSource(CommonComment.SOURCE_TOPIC_MAILBOX);
    		List<CommonComment> commonCommentList = service.getCommonCommentListByMidAndArticleId(cComment);
    		
            Date currentTime = new Date();
    		
    		//如果已经有数据，就修改数据
    		if(commonCommentList!=null && commonCommentList.size()>0){
    			commentId = commonCommentList.get(0).getCommentId();
    			CommonComment comment = new CommonComment();
    			comment.setCommentId(commentId);
    			comment.setUpdateTime(currentTime);
    			comment.setCommentContent(commentContent);
    			Integer updateNum = service.updateMessage(comment);
    			if(updateNum==null || updateNum==0){
     	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
     				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
     				return result;
     	        }
    		}else{//如果没有数据，就添加数据
    		    commonComment.setIsDelete(0);
    		    commonComment.setCommentTime(currentTime);
    		    commonComment.setUpdateTime(currentTime);
    		    commonComment.setSource(CommonComment.SOURCE_TOPIC_MAILBOX);
    		    commonComment.setParentCommentId(CommonComment.DEFAULT_PARENT_COMMENT_ID);
    	        service.addMessage(commonComment);
    	        
    	        commentId = commonComment.getCommentId();
    	        if(commentId==null || commentId==0){
    	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
    				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
    				return result;
    	        }
    	          
    	        //信箱状态改成已回复
    	        TopicMailbox topicMailbox =  new TopicMailbox();
    	        topicMailbox.setId(articleId);
    	        topicMailbox.setStatus(TopicMailbox.is_Reply);
    	        Integer updateNum = topicMailboxService.updateStatus(topicMailbox);
    	        if(updateNum==null || updateNum<=0){
    	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
    				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
    				return result;
    	        }
    		}
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("commentId",commentId);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    
    
    /**
	 * 评论点赞优化
	 * @param 李志坚
	 * @param 2018.6.6
	 */
    @RequestMapping(value = "commentPraiseNumOptimization",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object commentPraiseNumOptimization(CommonComment commonComment){
         
    	ResultEntity result = new ResultEntity(); 
    	try{
    		Long commentId = commonComment.getCommentId(); 
    		Integer optimizedPraiseNum = commonComment.getOptimizedPraiseNum();
    		
    		if(commentId==null || commentId==0|| optimizedPraiseNum == null){
		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		Date currentTime = new Date();
    		commonComment.setUpdateTime(currentTime);
	        int updateNum = service.updateMessage(commonComment);
	        
	        if(updateNum<=0){
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
	 * 显示/不显示
	 * @param 李志坚
	 * @param 2018.6.6
	 */
    @RequestMapping(value = "setIsDisPlay",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object setIsDisPlay(CommonComment commonComment){
         
    	ResultEntity result = new ResultEntity(); 
    	try{
    		Long commentId = commonComment.getCommentId(); 
    		Byte status = commonComment.getStatus();
    		
    		if(commentId==null || commentId==0|| status == null ||
    		 (!CommonComment.IS_DISPLAY_STATUS.equals(status)&&
    		  !CommonComment.IS_NOT_DISPLAY_STATUS.equals(status))){
		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		Date currentTime = new Date();
    		commonComment.setUpdateTime(currentTime);
	        int updateNum = service.updateMessage(commonComment);
	        
	        if(updateNum<=0){
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
    
    
    @RequestMapping(value = "like",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object like(CommonMemberPraiseRelation cmpRelation){
         
    	ResultEntity result = new ResultEntity(); 
    	Long praiseNumId = 0l;
    	try{
    		Long relationId = cmpRelation.getRelationId();
    		Long mid = cmpRelation.getMid();
    		
    		if(relationId==null || relationId==0|| mid==null || mid==0){
		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		
		    Date currentTime = new Date();
		    cmpRelation.setLikePosition(CommonMemberPraiseRelation.LIKE_POSITION_COMMENT);
		    cmpRelation.setIsDelete(0);
		    cmpRelation.setCreateTime(currentTime);
		    cmpRelation.setModifyTime(currentTime);
	     	
		    cmprService.addMessage(cmpRelation);
	        
		    praiseNumId = cmpRelation.getPraiseNumId();
	        if(praiseNumId==null || praiseNumId==0){
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
    	result.put("praiseNumId",praiseNumId);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }


	@Override
	public Integer getCommentNum(@RequestBody CommonComment commonComment) {
		Integer count =null;
    	try{
    		Long articleId = commonComment.getArticleId();
    		
		    if(articleId == null || articleId ==0){
		    	log.error(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage()+",articleId="+articleId);
				return null;
		    }
		    count = service.getCommentNum(commonComment);
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
		return count;
	}


	@Override
	public CommonComment getSingleMessage(@RequestBody CommonComment commonComment) {
		CommonComment comment = new CommonComment();
    	try{
    		Long articleId = commonComment.getArticleId();
    		Long mid = commonComment.getMid();
    		Integer source = commonComment.getSource();
    		
		    if(articleId == null || articleId <=0 || mid == null || mid <=0 || source == null || source <=0){
		    	log.error(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage()+",articleId="+articleId+",mid="+mid+",source="+source);
				return null;
		    }
		    List<CommonComment> commonCommentList = service.getCommonCommentListByMidAndArticleId(commonComment);
		    if(commonCommentList!=null && commonCommentList.size()>0){
		    	comment = commonCommentList.get(0);
		    }
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
		return comment;
	}
}

