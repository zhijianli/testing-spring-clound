package com.xinmao.tc.topicCenter.mapper;

import java.util.List;
import com.xinmao.tc.topicCenter.domain.TopicKeyWord;

public interface TopicKeyWordMapper {

    List<TopicKeyWord> getAllMessage(TopicKeyWord topicKeyWord);
    
    Integer getAllCount(TopicKeyWord topicKeyWord);
    
    TopicKeyWord getMessById(Long id);

    List<Long> selectIdList(TopicKeyWord topicKeyWord);
    
    List<String> getKeyWordByIdList(TopicKeyWord topicKeyWord);
    
    void insertMessage(TopicKeyWord topicKeyWord);
    
    int updateMessage(TopicKeyWord topicKeyWord);

}