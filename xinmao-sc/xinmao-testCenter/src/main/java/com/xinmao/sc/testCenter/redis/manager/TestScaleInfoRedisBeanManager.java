//package com.xinmao.sc.testCenter.redis.manager;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.Cursor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ScanOptions;
//import org.springframework.stereotype.Component;
//
//import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
//
//@Component
//public class TestScaleInfoRedisBeanManager {
//	
//	private static final Logger log = LogManager.getLogger();
//	
//	@Autowired
//	private RedisTemplate<String,Map<String,TestScaleInfoMessage>> redisTemplate;
//	
//	public void insertAllTestScaleInfoToRedis(List<TestScaleInfoMessage> testScaleInfoList){
//		
//		if(testScaleInfoList!=null && testScaleInfoList.size()>0){
//			
//			Map<String,TestScaleInfoMessage> testScaleInfoMap = new HashMap<String,TestScaleInfoMessage>();
//			
//	    	for(int i=0;i<testScaleInfoList.size();i++){
//	    		TestScaleInfoMessage testScaleInfoMessage = testScaleInfoList.get(i);
//	    		testScaleInfoMap.put(testScaleInfoMessage.getId()+"", testScaleInfoMessage);
//	    	}
//	    	
//	    	// 保存对象
//	    	redisTemplate.opsForHash().putAll("testScaleInfo", testScaleInfoMap);
//	    	
////	    	Cursor<Map.Entry<Object, Object>> curosr = redisTemplate.opsForHash().scan("testScaleInfo", ScanOptions.NONE);
////	        while(curosr.hasNext()){
////	            Map.Entry<Object, Object> entry = curosr.next();
////	            TestScaleInfoMessage  tsiMessage = (TestScaleInfoMessage)entry.getValue();
////	            log.error(entry.getKey()+":"+tsiMessage.getName());
////	        }
//    		
//		}
//
//		
//	}
//
//}
