package com.xinmao.common.userOperationCenter.service;


import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.userOperationCenter.domain.CommonComment;
import com.xinmao.common.userOperationCenter.domain.CommonMemberPraiseRelation;
import com.xinmao.common.userOperationCenter.mapper.CommonCommentMapper;
import com.xinmao.common.userOperationCenter.mapper.CommonMemberPraiseRelationMapper;

@Service
public class CommonCommentService {
	
	@Autowired
	private CommonCommentMapper mapper;
	 
	@Autowired
	private CommonMemberPraiseRelationMapper commonMemberPraiseRelationMapper;
	    
    public Boolean addMessage(CommonComment commonComment) {
    	
    	Boolean isSuccess = false;
    	
        mapper.insert(commonComment);
        Long commentId = commonComment.getCommentId();

		if(commentId==null || commentId==0){
			throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
		}
        
        isSuccess = true;
        return isSuccess;
    }
    
    public int updateMessage(CommonComment commonComment){
        return mapper.updateMessage(commonComment);
    }
    
	 
    public Integer getTestScaleCommentNum(CommonComment commonComment){
		  return mapper.getCommentNum(commonComment);
	}
    
    public Integer getCommentNum(CommonComment commonComment){
		  return mapper.getCommentNum(commonComment);
	}
    
	    
   public List<CommonComment> getAllMessage(CommonComment commonComment){
    	
	   List<CommonComment> parentCommentList = mapper.getAllMessage(commonComment);
//		   if(parentCommentList!=null && parentCommentList.size()>0){
//			   for(int i=0;i<parentCommentList.size();i++){
//				   CommonComment parentComment = parentCommentList.get(i);
//				   Long parentCommentId = parentComment.getCommentId();
//				   CommonComment sonComment = new CommonComment();
//				   sonComment.setParentCommentId(parentCommentId);
//				   sonComment.setArticleId(commonComment.getArticleId());
//				   List<CommonComment> sonCommentList = mapper.getAllMessage(sonComment);
//				   if(sonCommentList!=null && sonCommentList.size()>0){
//					   parentComment.setSonCommentList(sonCommentList);
//				   }
//			   }
//		   }
        
	   return parentCommentList;
   }
   
   public CommonComment getSingleMessage(CommonComment commonComment){
	   return mapper.getSingleMessage(commonComment);
   }
   
   
   
   public List<CommonComment> getCommentList(CommonComment commonComment){
   	
	   //获取评论列表
	   List<CommonComment> commentList = mapper.getAllMessage(commonComment);
	   if(commentList!=null && commentList.size()>0){
		   for(int i =0;i<commentList.size();i++){
			   CommonComment comment = commentList.get(i);
			   Long commentId = comment.getCommentId();
			   Long mid = comment.getMid();
			   
			   //计算该评论点赞数，并判断该用户是否点过赞
			   CommonMemberPraiseRelation record = new CommonMemberPraiseRelation();
			   record.setRelationId(commentId);
			   record.setLikePosition(CommonMemberPraiseRelation.LIKE_POSITION_COMMENT);
			   int praiseNum = 0;
			   Date latestPraiseTime = null;
			   Date latestUpdateTime = null;
			   List<CommonMemberPraiseRelation> cmpRelationList = commonMemberPraiseRelationMapper.getPraiseByRelationId(record);
			   if(cmpRelationList!=null && cmpRelationList.size()>0){
				   praiseNum = cmpRelationList.size();
				   for(int j=0;j<cmpRelationList.size();j++){
					   CommonMemberPraiseRelation cmpRelation = cmpRelationList.get(j);
					   Long praiseMid = cmpRelation.getMid();
					   if(praiseMid.equals(mid)){
						   comment.setIsPraise(true);
						   break;
					   }
				   }
				   
				   //如果有点赞，取最近一次点赞的创建时间
				   CommonMemberPraiseRelation latestRelation = cmpRelationList.get(0);
				   latestPraiseTime = latestRelation.getCreateTime();
				   
			   }
			   
			   //获取修改时间和点赞时间中最近的那个时间
			   latestUpdateTime = comment.getUpdateTime();
			   if(latestPraiseTime!=null && latestPraiseTime.getTime()>latestUpdateTime.getTime()){
				   comment.setSortTime(latestPraiseTime);
			   }else{
				   comment.setSortTime(latestUpdateTime);
			   }
			   
			   //获取最终点赞数=实际点赞数+优化点赞数
			   Integer optimizedPraiseNum = comment.getOptimizedPraiseNum();
			   comment.setPraiseNum(praiseNum);
			   comment.setFinalPraiseNum(praiseNum+optimizedPraiseNum);
		   }
		   
		   Collections.sort(commentList, new Comparator<CommonComment>(){
	            public int compare(CommonComment t1, CommonComment t2) {
	                //按照Topic的排序时间进行降序排列
	                if(t1.getSortTime().getTime() < t2.getSortTime().getTime()){
	                    return 1;
	                }
	                if(t1.getSortTime() == t2.getSortTime()){
	                    return 0;
	                }
	                return -1;
	            }
	        });
	   }
	   return commentList;
   }

 
   
   public List<CommonComment> getMessageBySearchList(CommonComment commonComment){
	   return mapper.getMessageBySearchList(commonComment);
   }
   
   public List<Long> getTopicIdListByMid(CommonComment commonComment){
		  return mapper.getTopicIdListByMid(commonComment);
   }
   
   public List<CommonComment> getCommonCommentListByMidAndArticleId(CommonComment commonComment){
		  return mapper.getCommonCommentListByMidAndArticleId(commonComment);
}
}

