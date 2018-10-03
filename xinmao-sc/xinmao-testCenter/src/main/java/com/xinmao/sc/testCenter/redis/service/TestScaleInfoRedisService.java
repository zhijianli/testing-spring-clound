//package com.xinmao.sc.testCenter.redis.service;
//
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
//import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
//import com.xinmao.sc.testCenter.mapper.TestScaleClassMapper;
//import com.xinmao.sc.testCenter.mapper.TestScaleInfoMapper;
//import com.xinmao.sc.testCenter.redis.manager.TestScaleInfoRedisBeanManager;
//import com.xinmao.sc.testCenter.redis.manager.TestScaleInfoRedisIndexesManager;
//@Service
//public class TestScaleInfoRedisService {
//	
//	private static final Logger log = LogManager.getLogger();
//	
//	
//    @Autowired
//    private TestScaleInfoRedisBeanManager tsirbManager;
//    
//    @Autowired
//    private TestScaleInfoRedisIndexesManager tsiriManager;
//	
//    @Autowired
//    private TestScaleInfoMapper mapper;
//	
//    public void insertAllTestScaleInfoToRedis(){
//    	TestScaleInfoMessage testScaleInfo = new TestScaleInfoMessage();
//    	
//    	List<TestScaleInfoMessage> testScaleInfoList =  mapper.selectAll(testScaleInfo);
//    	tsirbManager.insertAllTestScaleInfoToRedis(testScaleInfoList);
//         
//    }
//
//}
