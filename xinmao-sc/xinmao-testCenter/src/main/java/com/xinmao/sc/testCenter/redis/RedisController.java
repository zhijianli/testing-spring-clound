//package com.xinmao.sc.testCenter.redis;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.apache.log4j.Logger;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.xinmao.sc.entity.ErrorCode;
//import com.xinmao.sc.entity.ResultEntity;
//import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
//import com.xinmao.sc.testCenter.redis.service.TestScaleClassRedisService;
//import com.xinmao.sc.testCenter.redis.service.TestScaleInfoRedisService;
//
//
//
//@RestController
//@RequestMapping("redis")
//public class RedisController{
//	
//    @Autowired
//    private TestScaleClassRedisService testScaleClassRedisService;
//    
//    @Autowired
//    private TestScaleInfoRedisService testScaleInfoRedisService;
//	
//    
//	private static final Logger log = LogManager.getLogger();
//    
//	
//	
//   @RequestMapping(value="/getAllMessageFromFront",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
//   public Object getAllMessageFromFront(){
//    	
//    	ResultEntity result = new ResultEntity(); 
//    	List<TestScaleClassMessage> list = new ArrayList<TestScaleClassMessage>();
//    	int count = 0;
//    	try{
//             
//	        list = testScaleClassRedisService.getHomeRecommMessage();
//	        if(list!=null&&list.size()>0){
//	        	count = list.size();
//	        }
//        
//    	}catch(Exception e){
//			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
//			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//			result.setMsg(e.getMessage());
//			return result;
//    	}
//    	result.put("testScaleClassList", list);
//    	result.put("count", count);
//		result.setCode(ErrorCode.SUCCESS.getCode());
//		result.setMsg(ErrorCode.SUCCESS.getMessage());
//		return result;
//
//        
//    }
//	
//	
//	
//    @RequestMapping(value="/reFreshRedisInfo",method=RequestMethod.GET)
//    public String  reFreshRedisInfo(){
//    	
//    	String resultInfo = null;
//    	
//    	try{
//        	testScaleClassRedisService.insertAllTestScaleClassToRedis();
//        	testScaleInfoRedisService.insertAllTestScaleInfoToRedis();
//		    resultInfo = "refresh is success";
//    	}catch(Exception e){
//			log.error("system is error", e);
//			resultInfo="system is error";
//			return resultInfo;
//    	}
//    	
//		return resultInfo;
//    	
//    }
//    
//    @RequestMapping(value="/getMessage",method=RequestMethod.GET)
//    public String  getMessage(){
//    	
//    	String resultInfo = null;
//    	
//    	try{
//        	List<TestScaleClassMessage> allList = testScaleClassRedisService.getAllMessage();
//        	List<TestScaleClassMessage> homeRecommList = testScaleClassRedisService.getHomeRecommMessage();
//        	log.error(allList.get(0).getName());
//        	log.error(homeRecommList.get(0).getName());
//    	}catch(Exception e){
//			log.error("system is error", e);
//			resultInfo="system is error";
//			return resultInfo;
//    	}
//    	
//		return resultInfo;
//    	
//    }
//	
//    
//}
//
