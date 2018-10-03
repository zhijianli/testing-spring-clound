package com.xinmao.tc.topicCenter.mapper;

import java.util.List;

import com.xinmao.tc.topicCenter.domain.TopicKeyWordTopicRelation;

public interface TopicKeyWordTopicRelationMapper {

    List<TopicKeyWordTopicRelation> getAllMessage(TopicKeyWordTopicRelation topicKeyWordTopicRelation);
    
    List<Long> selectKeyWordIdListByTopicId(Long topicId);
    
    List<Long> selectTopicIdListByKeyWordId(Long keyWordId);
    
    void insertMess(TopicKeyWordTopicRelation topicKeyWordTopicRelation);
    
    int batchDeleteMess(TopicKeyWordTopicRelation topicKeyWordTopicRelation);
    
    List<Long> selectTopicIdList(TopicKeyWordTopicRelation topicKeyWordTopicRelation);

}