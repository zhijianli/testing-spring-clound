package com.xinmao.tc.topicCenter.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.xinmao.common.userOperationCenter.domain.CommonComment;
import com.xinmao.tc.entity.ErrorCode;
import com.xinmao.tc.entity.ResultEntity;
import com.xinmao.tc.topicCenter.domain.Topic;
import com.xinmao.tc.topicCenter.domain.TopicKeyWord;
import com.xinmao.tc.topicCenter.domain.TopicKeyWordTopicRelation;
import com.xinmao.tc.topicCenter.mapper.TopicKeyWordMapper;
import com.xinmao.tc.topicCenter.mapper.TopicKeyWordTopicRelationMapper;
import com.xinmao.tc.topicCenter.mapper.TopicMapper;



@Service
public class TopicService {
	
	private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TopicMapper mapper;  
    
    @Autowired
    private TopicKeyWordMapper topicKeyWordMapper;
    
    @Autowired
    private TopicKeyWordTopicRelationMapper topicKeyWordTopicRelationMapper;
    
    @Autowired
    private CommonCommentService commonCommentService;
    
    public List<Topic> getHomePageTopicList(Topic topic){
    	
    	topic.setStatus(Topic.is_DISPLAY_STATUS);
    	List<Topic> topicList = mapper.selectList(topic);
    	
    	//排序，取相关分页的数据
    	Integer pageIndex = topic.getPageIndex();
		Integer pageSize = topic.getPageSize();
    	topicList = this.sortAndPagingTopic(topicList,pageSize,pageIndex);
    	
    	return topicList;
    }
    
    public List<Topic> getSortedTopicList(){
    	
    	Topic top = new Topic();
    	top.setStatus(Topic.is_DISPLAY_STATUS);
    	List<Topic> topicList = mapper.selectList(top);
    	
    	if(topicList!=null && topicList.size()>0){
    	 	
    		//排序
        	this.sortTopic(topicList);
    	}
    	
    	return topicList;
    }
    
    public List<Topic> getListByIdList(Topic topic){
    	return mapper.selectList(topic);
    }
    
    public Integer getHomePageTopicCount(Topic topic){
    	topic.setStatus(Topic.is_DISPLAY_STATUS);
    	return mapper.getAllCount(topic);
    }
    
    
    
   public List<Topic> getAllMessage(Integer pageSize,Integer pageIndex,String keyWord){
	    Topic topic = new Topic();
	    this.configTopic(keyWord, topic);
    	List<Topic> topicList = mapper.selectList(topic);
    	
    	//排序，取相关分页的数据
    	topicList = this.sortAndPagingTopic(topicList,pageSize,pageIndex);
    	
    	
    	if(topicList!=null && topicList.size()>0){
    		for(int i=0;i<topicList.size();i++){
    			Topic top = topicList.get(i);
    			Long topicId = top.getId();
    			
    			//获取关键词列表
    			List<Long> keyWordIdList = topicKeyWordTopicRelationMapper.selectKeyWordIdListByTopicId(topicId);
    			if(keyWordIdList!=null && keyWordIdList.size()>0){
    				TopicKeyWord tkWord = new TopicKeyWord();
        			tkWord.setSearchIdList(keyWordIdList);
        			List<String> keyWordList = topicKeyWordMapper.getKeyWordByIdList(tkWord);
        			if(keyWordList!=null && keyWordList.size()>0){
        				top.setKeyWordList(keyWordList);
        			}
    			}
    			
    			//获取回答数
    			CommonComment commonComment = new CommonComment();
    			commonComment.setArticleId(topicId);
    			commonComment.setSource(CommonComment.SOURCE_TOPIC);
    			Integer commentNum = commonCommentService.getCommentNum(commonComment);
    			top.setCommentNum(commentNum);
    		}
    	}
    	return topicList;
    }

   public Integer getAllCount(Integer pageSize,Integer pageIndex,String keyWord){
	    Topic topic = new Topic();
	    this.configTopic(keyWord, topic);
   	    return mapper.getAllCount(topic);
   }
   
private void configTopic(String keyWord, Topic topic) {
	TopicKeyWord topicKeyWord = new TopicKeyWord();
	if(keyWord!=null && StringUtils.isNotEmpty(keyWord)){
		topicKeyWord.setKeyWord(keyWord);
		 List<Long> tkwIdList = topicKeyWordMapper.selectIdList(topicKeyWord);
		 if(tkwIdList!=null && tkwIdList.size()>0){
			 TopicKeyWordTopicRelation tkwtRelation = new TopicKeyWordTopicRelation();
			 tkwtRelation.setSearchIdList(tkwIdList);
			 List<Long> kwTopicIdList = topicKeyWordTopicRelationMapper.selectTopicIdList(tkwtRelation);
			 topic.setSearchIdList(kwTopicIdList);
		 }
	}
}

    //排序，取相关分页的数据
	private List<Topic> sortAndPagingTopic(List<Topic> topicList,Integer pageSize,Integer pageIndex) {
		List<Topic> pagingList = new ArrayList<Topic>();
		
		if(topicList!=null && topicList.size()>0){
			
			//排序
			this.sortTopic(topicList);
			
			//获取分页数据
			Integer startIndex = (pageIndex-1)*pageSize;
			Integer endIndex = pageIndex*pageSize;
			
			for(int i=startIndex;i<endIndex;i++){
				if(i>=topicList.size()){
					break;
				}
				pagingList.add(topicList.get(i));
			}
		}
		return pagingList;
   }
	//排序
	private void sortTopic(List<Topic> topicList) {
		for(int i=0;i<topicList.size();i++){
			Topic topic =topicList.get(i);
			Byte isTop = topic.getIsTop();
			if(Topic.IS_TOP.equals(isTop)){
				topic.setSortTime(topic.getSetTopTime());
			}else{
				topic.setSortTime(topic.getUpdateTime());
			}
		}
		
		Collections.sort(topicList, new Comparator<Topic>(){
		    public int compare(Topic t1, Topic t2) {
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

   
   public Topic getSingleMessage(Long topicId){
	   Topic topic = mapper.getSingleMessage(topicId);
	   List<Long> keyWordIdList = topicKeyWordTopicRelationMapper.selectKeyWordIdListByTopicId(topicId);
	   topic.setKeyWordIdList(keyWordIdList);
   	   return topic;
   }
    
    @Transactional
    public void addMessage(Topic topic){
    	
    	//新增话题
        mapper.addMessage(topic);
        Long topicId = topic.getId();
        if(topicId==null || topicId<=0){
        	log.error("新增话题出错，id = "+topicId);
        	throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
        }
        
    	//新增关键词关联
        Date currentTime = new Date();
        List<Long> addKeyWordIdList = this.getKeyWordIdList(topic);
        if(addKeyWordIdList!=null && addKeyWordIdList.size()>0){
        	for(int i =0;i<addKeyWordIdList.size();i++){
        		Long addKeyWordId = addKeyWordIdList.get(i);
        		this.insertRelation(currentTime, topicId, addKeyWordId);
        	}
        }
    }

    public int updateMessage(Topic topic){
        return mapper.updateMessage(topic);
    }
    
    public int addReadingVolume(Topic topic){
        return mapper.addReadingVolume(topic);
    }
    
    public int setTop(Topic topic){
        return mapper.setTop(topic);
    }
    
    @Transactional
    public Boolean editTopic(Topic topic){
    	Long topicId = topic.getId();
    	
      	//修改话题
    	Date currentTime = new Date();
		topic.setUpdateTime(currentTime);
        int updateNum = mapper.updateMessage(topic);
        if(updateNum<=0){
        	log.error("修改话题出错，id = "+topicId);
        	throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
        }
    	
    	//编辑话题关键字
    	this.updateKeyWord(topic);
  
        return true;
    }
    
    private Boolean updateKeyWord(Topic topic){
    	Date currentTime = new Date();
    	Long topicId = topic.getId();
    	
    	//获取原有的关联信息
		List<Long> oriKeyWordIdList = topicKeyWordTopicRelationMapper.selectKeyWordIdListByTopicId(topicId);
		List<Long> addKeyWordIdList = this.getKeyWordIdList(topic);
		
		//判断原先数据库中是否有关联，如果有，就先添加新增的关联，再删除原先的关联。
		if(oriKeyWordIdList!=null&&oriKeyWordIdList.size()>0){
	    		List<Long> updateIdList  = new ArrayList<Long>();
	    	       
	    	    //原有的关联保持不变,添加新增的关联
	    	    if(addKeyWordIdList!=null && addKeyWordIdList.size()>0){
			        for(int j=0;j<addKeyWordIdList.size();j++){
			        	Long addKeyWordId = addKeyWordIdList.get(j);
			        	
			        	if(!oriKeyWordIdList.contains(addKeyWordId)){
			        		this.insertRelation(currentTime, topicId, addKeyWordId);
			        	}else{
			        		updateIdList.add(addKeyWordId);
			        	}
			        }
	    	    }
		        
		      //删除去掉的关联
		      List<Long> delIdList  = new ArrayList<Long>();
		      for(int k=0;k<oriKeyWordIdList.size();k++){
		    	  Long coincidentKeyWordId = oriKeyWordIdList.get(k);
		    	  if(!updateIdList.contains(coincidentKeyWordId)){
		    		  delIdList.add(coincidentKeyWordId);
		    	  }
		      }
		      
		      int delIdListSize = delIdList.size();
		      if(delIdList!=null && delIdListSize>0){
		    	  TopicKeyWordTopicRelation delRelation = new TopicKeyWordTopicRelation();
		    	  delRelation.setTopicId(topicId);
		    	  delRelation.setSearchIdList(delIdList);
		    	  delRelation.setIsDelete((byte)1);
		    	  delRelation.setUpdateTime(currentTime);
				  int delNum = topicKeyWordTopicRelationMapper.batchDeleteMess(delRelation);
			      if(delNum < delIdListSize){
			    	     log.error("删除关键词关联话题失败，delIdList = "+delIdList+"，topicId = "+topicId);
	        			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
	        	  }
		      }
	        
		}else{ //判断原先数据库中是否有关联，如果没有，就直接添加新关联就行。
			
    	    //原有的关联保持不变,添加新增的关联
    	    if(addKeyWordIdList!=null && addKeyWordIdList.size()>0){
		        for(int j=0;j<addKeyWordIdList.size();j++){
		        	Long addKeyWordId = addKeyWordIdList.get(j);
	        		this.insertRelation(currentTime, topicId, addKeyWordId);
		        }
    	    }
		}
		
		return true;
    }

	private void insertRelation(Date currentTime, Long topicId, Long addKeyWordId) {
		
		//判断关联的关键词是否存在，如果不存在那就回滚
		TopicKeyWord topicKeyWord = topicKeyWordMapper.getMessById(addKeyWordId);
		if(topicKeyWord == null){
			log.error("该关键词不存在，keyWordId = "+addKeyWordId);
			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		}
		
		TopicKeyWordTopicRelation tkwTopicRelation = new TopicKeyWordTopicRelation();
		tkwTopicRelation.setTopicId(topicId);
		tkwTopicRelation.setKeyWordId(addKeyWordId);
		tkwTopicRelation.setIsDelete((byte)0);
		tkwTopicRelation.setIsEnable((byte)0);
		tkwTopicRelation.setCreateTime(currentTime);
		tkwTopicRelation.setUpdateTime(currentTime);
		topicKeyWordTopicRelationMapper.insertMess(tkwTopicRelation);
		Long insertId = tkwTopicRelation.getId();
		if(insertId == null || insertId <= 0){
			log.error("新增关键词关联话题失败，keyWordId = "+addKeyWordId+"，topicId = "+topicId);
			throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
		}
	}
    
    
//    @Transactional
//	 public ResultEntity deleteMess(TestScaleClassMessage testScaleClassMessage) {
//	       
//    	try{
//	    	ResultEntity result = new ResultEntity(); 
//	    	
//	    	Integer tscId = testScaleClassMessage.getId();
//	    	
//	    	//判断量表分类是否被首页推荐，如果是，无法删除分类
//	    	if(isHomeRecomm(tscId)){
//	    		result.setCode(ErrorCode.ERROR_CLASS_HOME_RECOMM.getCode());
//				result.setMsg(ErrorCode.ERROR_CLASS_HOME_RECOMM.getMessage());
//				return result;
//	    	}
//	    	
//	    	Date currentTime = new Date();
//	    	
//	    	//删除分类
//	    	TestScaleClassMessage tscMessage = new TestScaleClassMessage();
//	    	tscMessage.setId(tscId);
//	    	tscMessage.setIsDelete(TestScaleClassMessage.IS_DELETE);
//	    	tscMessage.setUpdateTime(currentTime);
//	    	int updateClassNum = mapper.updateMess(tscMessage);
//	       	if(updateClassNum==0){
//		       throw new RuntimeException(ErrorCode.ERROR_DATABASE_QUERY.getMessage());
//		    }
//	    	
//	        //删除分类量表关联
//	       	TestScaleClassRelationMessage  tscrMessage = new TestScaleClassRelationMessage();
//	       	tscrMessage.setTestScaleClassId(tscId);
//	       	tscrMessage.setIsDelete(TestScaleClassRelationMessage.IS_DELETE);
//	       	tscrMessage.setUpdateTime(currentTime);
//	       	tscrMapper.batchDeleteMessByClassId(tscrMessage);
//					
//	    	result.put("isSuccess", true);
//			result.setCode(ErrorCode.SUCCESS.getCode());
//			result.setMsg(ErrorCode.SUCCESS.getMessage());
//		    return result;
//		    
//    	}catch(Exception e){
//    		log.error(e.getMessage(), e);
//    		throw new RuntimeException(e.getMessage(),e);
//    	}
//	    	
//	}
	
	private List<Long> getKeyWordIdList(Topic topic) {
		List<Long> addKeyWordIdList = new ArrayList<Long>();
        String keyWordStr = topic.getKeyWordStr();
        String[] keyWordArry = keyWordStr.split(Topic.KEY_WORD_SPLIT_CHAR);
        if(keyWordArry!=null && keyWordArry.length>0){
        	for(int i =0;i<keyWordArry.length;i++){
        		addKeyWordIdList.add(Long.valueOf(keyWordArry[i]));
        	}
        }
		return addKeyWordIdList;
	}
    
    
}

