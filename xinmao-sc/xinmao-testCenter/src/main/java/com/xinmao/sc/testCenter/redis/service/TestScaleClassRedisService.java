//package com.xinmao.sc.testCenter.redis.service;
//
//import java.util.List;
//import java.util.Set;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
//import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
//import com.xinmao.sc.testCenter.mapper.TestScaleClassMapper;
//import com.xinmao.sc.testCenter.redis.manager.TestScaleClassRedisBeanManager;
//import com.xinmao.sc.testCenter.redis.manager.TestScaleClassRedisIndexesManager;
//
//@Service
//public class TestScaleClassRedisService {
//	
//	private static final Logger log = LogManager.getLogger();
//    
//    @Autowired
//    private TestScaleClassMapper mapper;
//    
//    @Autowired
//    private TestScaleClassRedisBeanManager tscrbManager;
//    
//    @Autowired
//    private TestScaleClassRedisIndexesManager tscriManager;
//    
//	//插入量表分类信息与索引到Redis中
//    public void insertAllTestScaleClassToRedis(){
//    	
//    	List<TestScaleClassMessage> testScaleClassList =  mapper.selectAll();
//    	
//    	//插入量表分类信息
//    	tscrbManager.insertAllTestScaleClassToRedis(testScaleClassList);
//    	
//    	//插入推荐量表分类索引
//    	tscriManager.insertHomeRecommClassIndexes(testScaleClassList);
//         
//    }
//    
//    //从Redis中获取所有量表分类信息
//    public List<TestScaleClassMessage> getAllMessage(){
//    	return tscrbManager.getAllMessage();
//    }
//    
//    //从Redis中获取首页推荐的分类信息
//    public List<TestScaleClassMessage> getHomeRecommMessage(){
//    	Set<Object> indexesSet = tscriManager.getHomeRecommIndexes();
//    	return tscrbManager.getHomeRecommBeans(indexesSet);
//    }
//
//}
