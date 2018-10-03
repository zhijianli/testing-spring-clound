package com.xinmao.tc.topicCenter.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.common.userOperationCenter.domain.CommonComment;
import com.xinmao.tc.topicCenter.domain.TopicMailbox;
import com.xinmao.tc.topicCenter.mapper.TopicMailboxMapper;


@Service
public class TopicMailboxService {
	
   private static final Logger log = LogManager.getLogger();
    
   @Autowired
   private TopicMailboxMapper mapper;
   
   @Autowired
   private CommonCommentService commonCommentService;
   
   public List<TopicMailbox> getAllMessage(TopicMailbox topicMailbox){
	   List<TopicMailbox>  topicMailboxList = mapper.getAllMessage(topicMailbox);
	   if(topicMailboxList!=null && topicMailboxList.size()>0){
		   for(int i=0;i<topicMailboxList.size();i++){
			   TopicMailbox tMailbox = topicMailboxList.get(i);
			   tMailbox.splitScreenshotStr();
		   }
	   }
	   return topicMailboxList;
   }
   
   public List<TopicMailbox> getAllMessageFromReception(TopicMailbox topicMailbox){
	   List<TopicMailbox>  topicMailboxList = mapper.getAllMessage(topicMailbox);
	   if(topicMailboxList!=null && topicMailboxList.size()>0){
		   for(int i=0;i<topicMailboxList.size();i++){
			   TopicMailbox tMailbox = topicMailboxList.get(i);
			   this.setCommentContent(tMailbox);
		   }
	   }
	   return topicMailboxList;
   }

private void setCommentContent(TopicMailbox tMailbox) {
	   Long articleId = tMailbox.getId();
	   Long mid = tMailbox.getMid();
	   CommonComment commonComment = new CommonComment();
	   commonComment.setArticleId(articleId);
	   commonComment.setMid(mid);
	   commonComment.setSource(CommonComment.SOURCE_TOPIC_MAILBOX);
	   
	   //获取评论内容
	   CommonComment comment = commonCommentService.getSingleMessage(commonComment);
	   tMailbox.setCommentContent(comment.getCommentContent());
}
   
   public Integer getAllCount(TopicMailbox topicMailbox){
	   return  mapper.getAllCount(topicMailbox);
   }
   
   
   public TopicMailbox getSingleMessage(TopicMailbox topicMailbox){
	    TopicMailbox tMailbox = mapper.getSingleMessage(topicMailbox);
	    tMailbox.splitScreenshotStr();
	    this.setCommentContent(tMailbox);
	    return tMailbox;
   }

   public void addMessage(TopicMailbox topicMailbox){
    	mapper.addMessage(topicMailbox);
   }

   public int updateMessage(TopicMailbox topicMailbox){
        return mapper.updateMessage(topicMailbox);
   }
}

