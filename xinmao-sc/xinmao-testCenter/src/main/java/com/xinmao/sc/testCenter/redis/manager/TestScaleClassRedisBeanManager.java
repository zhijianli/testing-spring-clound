//package com.xinmao.sc.testCenter.redis.manager;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.Cursor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ScanOptions;
//import org.springframework.stereotype.Component;
//
//import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
//
//@Component
//public class TestScaleClassRedisBeanManager {
//	
//	private static final Logger log = LogManager.getLogger();
//	
//	@Autowired
//	private RedisTemplate<String,Map<String,TestScaleClassMessage>> redisTemplate;
//	
//	public void insertAllTestScaleClassToRedis(List<TestScaleClassMessage> testScaleClassList){
//		
//		if(testScaleClassList!=null && testScaleClassList.size()>0){
//			Map<String,TestScaleClassMessage> testScaleClassMap = new HashMap<String,TestScaleClassMessage>();
//	    	for(int i=0;i<testScaleClassList.size();i++){
//	    		TestScaleClassMessage  testScaleClassMessage = testScaleClassList.get(i);
//	    		testScaleClassMap.put(testScaleClassMessage.getId()+"", testScaleClassMessage);
//	    	}
//	    	// 保存对象
//	    	redisTemplate.opsForHash().putAll("testScaleClass", testScaleClassMap);
//	    	
////	    	Cursor<Map.Entry<Object, Object>> curosr = redisTemplate.opsForHash().scan("testScaleClass", ScanOptions.NONE);
////	        while(curosr.hasNext()){
////	            Map.Entry<Object, Object> entry = curosr.next();
////	            TestScaleClassMessage  tscMessage = (TestScaleClassMessage)entry.getValue();
////	            log.error(entry.getKey()+":"+tscMessage.getName());
////	        }
//
//		}
//		
//	}
//	
//	public List<TestScaleClassMessage> getAllMessage(){
//		
//		List<TestScaleClassMessage> testScaleClassList = new ArrayList<TestScaleClassMessage>();
//		
//    	Cursor<Map.Entry<Object, Object>> curosr = redisTemplate.opsForHash().scan("testScaleClass", ScanOptions.NONE);
//        while(curosr.hasNext()){
//            Map.Entry<Object, Object> entry = curosr.next();
//            testScaleClassList.add((TestScaleClassMessage)entry.getValue());
//        }
//        
//        return testScaleClassList;
//		
//	}
//	
//	public List<TestScaleClassMessage> getHomeRecommBeans(Set<Object> indexesSet){
//
//		List<TestScaleClassMessage> tscList = new ArrayList<TestScaleClassMessage>(); 
//		List<Object> objectList = redisTemplate.opsForHash().multiGet("testScaleClass",indexesSet);
//		if(objectList!=null && objectList.size()>0){
//			for(int i=0;i<objectList.size();i++){
//				tscList.add((TestScaleClassMessage) objectList.get(i));
//			}
//		}
//		return tscList;
//		
//	}
//
//}
