package com.xinmao.tc.topicCenter.mapper;

import java.util.List;

import com.xinmao.tc.topicCenter.domain.TopicMailbox;

public interface TopicMailboxMapper {
	
    List<TopicMailbox> getAllMessage(TopicMailbox topicMailbox);
    
    Integer getAllCount(TopicMailbox topicMailbox);
    
    TopicMailbox getSingleMessage(TopicMailbox topicMailbox);
    
    void addMessage(TopicMailbox topicMailbox);
    
    int updateMessage(TopicMailbox topicMailbox);
}