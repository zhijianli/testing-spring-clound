package com.xinmao.tc.topicCenter.mapper;

import java.util.List;

import com.xinmao.tc.topicCenter.domain.Topic;

public interface TopicMapper {

    List<Topic> selectList(Topic record);
    
    Integer getAllCount(Topic record);
    
    void addMessage(Topic record);
    
    int updateMessage(Topic record);
    
    int addReadingVolume(Topic record);
    
    int setTop(Topic record);
    
    Topic getSingleMessage(Long topicId);
}