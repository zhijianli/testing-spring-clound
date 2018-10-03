package com.xinmao.tc.topicCenter.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.tc.topicCenter.domain.Topic;
import com.xinmao.tc.topicCenter.domain.TopicKeyWord;
import com.xinmao.tc.topicCenter.mapper.TopicKeyWordMapper;
import com.xinmao.tc.topicCenter.mapper.TopicKeyWordTopicRelationMapper;
import com.xinmao.tc.topicCenter.mapper.TopicMapper;


@Service
public class TopicKeyWordService {
	
   private static final Logger log = LogManager.getLogger();
    
   @Autowired
   private TopicKeyWordMapper mapper;
   
   @Autowired
   private TopicMapper topicMapper;
    
   @Autowired
   private TopicKeyWordTopicRelationMapper topicKeyWordTopicRelationMapper;
   
   public List<TopicKeyWord> getAllMessage(TopicKeyWord topicKeyWord){
	    List<TopicKeyWord> topicKeyWordList = new ArrayList<TopicKeyWord>();
	    topicKeyWordList = mapper.getAllMessage(topicKeyWord);
	    if(topicKeyWordList!=null && topicKeyWordList.size()>0){
	    	for(int i=0;i<topicKeyWordList.size();i++){
	    		TopicKeyWord keyWord = topicKeyWordList.get(i);
	    		Long keyWordId = keyWord.getId();
	    		List<Long> topicIdList = topicKeyWordTopicRelationMapper.selectTopicIdListByKeyWordId(keyWordId);
	    	    Integer degreeOfHeat = 0;
	    	    Integer relatedTopicNum = 0;
	    	    
	    		if(topicIdList!=null && topicIdList.size()>0){
	    	    	Topic topic = new Topic();
	    	    	topic.setSearchIdList(topicIdList);
	    	    	List<Topic> topicList = topicMapper.selectList(topic);
	    	    	if(topicList!=null && topicList.size()>0){
	    	    		for(int j=0;j<topicList.size();j++){
	    	    			Topic top = topicList.get(j);
	    	    			Integer readingVolume = top.getReadingVolume();
	    	    			if(readingVolume!=null && readingVolume>0){
	    	    				degreeOfHeat += readingVolume;
	    	    			}
	    	    		}
	    	    		relatedTopicNum = topicList.size();
	    	    	}
	    	    }
	    		keyWord.setDegreeOfHeat(degreeOfHeat);
	    		keyWord.setRelatedTopicNum(relatedTopicNum);
	    	}
	    }
	    return topicKeyWordList;
   }

   public Integer getAllCount(TopicKeyWord topicKeyWord){
        return mapper.getAllCount(topicKeyWord);
   }
   
   public void addMessage(TopicKeyWord topicKeyWord){
    	mapper.insertMessage(topicKeyWord);
   }

   public int updateMessage(TopicKeyWord topicKeyWord){
        return mapper.updateMessage(topicKeyWord);
   }
}

