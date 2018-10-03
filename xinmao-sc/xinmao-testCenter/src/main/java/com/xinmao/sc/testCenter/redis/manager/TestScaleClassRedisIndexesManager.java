//package com.xinmao.sc.testCenter.redis.manager;
//
//import java.util.ArrayList;
//import java.util.HashSet;
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
//public class TestScaleClassRedisIndexesManager {
//	
//	private static final Logger log = LogManager.getLogger();
//	
//	@Autowired
//	private RedisTemplate<String,Object> redisTemplate;
//	
//	public void insertHomeRecommClassIndexes(List<TestScaleClassMessage> testScaleClassList){
//		
//		if(testScaleClassList!=null && testScaleClassList.size()>0){
//			
//			List<String> homeRecommIdList = new ArrayList<String>();
//			
//			for(int i=0;i<testScaleClassList.size();i++){
//				TestScaleClassMessage testScaleClassMessage = testScaleClassList.get(i);
//				Integer homeRecomm = testScaleClassMessage.getHomeRecomm();
//				if(homeRecomm!=null && 
//				   testScaleClassMessage.getHomeRecomm() == TestScaleClassMessage.IS_HOME_RECOMM){
//					homeRecommIdList.add(testScaleClassMessage.getId()+"");
//				}
//			}
//			if(homeRecommIdList!=null && homeRecommIdList.size()>0){
//				String[] homeRecommIdArry = new String[homeRecommIdList.size()];
//				for(int i = 0;i<homeRecommIdList.size();i++){
//					homeRecommIdArry[i] = homeRecommIdList.get(i);
//				}
//				
//				redisTemplate.opsForSet().add("homeRecommTestScaleClassId",homeRecommIdArry);
//				
////				Cursor curosr = redisTemplate.opsForSet().scan("homeRecommTestScaleClassId",ScanOptions.NONE);
////		        while(curosr.hasNext()){
////		            log.error(curosr.next());
////		        }
//			}
//
//		}
//	}
//	
//	public Set<Object> getHomeRecommIndexes(){
//		
//		 return redisTemplate.opsForSet().members("homeRecommTestScaleClassId");
//		
//	}
//
//}
