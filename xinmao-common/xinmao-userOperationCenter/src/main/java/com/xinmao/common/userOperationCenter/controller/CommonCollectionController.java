package com.xinmao.common.userOperationCenter.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;
import com.xinmao.common.userOperationCenter.api.CommonCollectionApi;
import com.xinmao.common.userOperationCenter.domain.CommonCollection;
import com.xinmao.common.userOperationCenter.service.CommonCollectionService;


@RestController
//@RequestMapping("commonCollection")
public class CommonCollectionController implements CommonCollectionApi {
	
	Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CommonCollectionService service;
    
    public Boolean isCollected(@RequestBody CommonCollection commonCollection){
    	Boolean isCollected = false;
    	try{
    		
    		Long mid = commonCollection.getMid();
    		Long articleId = commonCollection.getArticleId();
    		Integer source  = commonCollection.getSource();
		    if(mid==null || mid==0|| articleId==null || articleId==0 || source==null){
		    	return false;
		    }
	     	
		    List<CommonCollection> commonCollectionList  = service.getCollectionListByUserId(commonCollection);
		    if(commonCollectionList!=null &&commonCollectionList.size()>0){
		    	isCollected = true;
		    }
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return isCollected;
    	}
		return isCollected;
    }
    

    public List<CommonCollection> getCollectionListByUserId(@RequestBody CommonCollection commonCollection){
         
    	List<CommonCollection> collectionList = new ArrayList<CommonCollection>();
    	try{
    		
    		Long mid = commonCollection.getMid();
    		
    		Integer pageIndex = commonCollection.getPageIndex();
    		Integer pageSize = commonCollection.getPageSize();
		    if(mid==null || mid==0|| pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
		    	return null;
		    }
    		
		    commonCollection.setSource(CommonCollection.SOURCE_TEST_SCALE);
	     	
		    PageHelper.startPage(pageIndex, pageSize);
		    collectionList = service.getCollectionListByUserId(commonCollection);
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
		return collectionList;
    }
    
    
    @RequestMapping(value = "collectionTestScale",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object collectionTestScale(CommonCollection commonCollection){
         
    	ResultEntity result = new ResultEntity(); 
    	Boolean isSuccess = false;
    	try{
    		
    		Long articleId = commonCollection.getArticleId();
    		Long mid = commonCollection.getMid();
    		Integer isCollected = commonCollection.getIsCollected();
    		
    		if(articleId==null || articleId==0||
    		   mid==null || mid==0 || 
    		   isCollected == null){
		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
    		}
    		
    		if(isCollected == commonCollection.IS_COLLECTED){
    			
    	    	    Long id = 0l;
    			    Date currentTime = new Date();
    			    commonCollection.setIsDelete(0);
    			    commonCollection.setCollectionTime(currentTime);
    			    commonCollection.setCreateTime(currentTime);
    			    commonCollection.setModifyTime(currentTime);
    			    commonCollection.setSource(CommonCollection.SOURCE_TEST_SCALE);
    		     	
    		        service.addMessage(commonCollection);
    		        
    		        id = commonCollection.getColId();
    		        if(id==null || id==0){
    		        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
    					result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
    					return result;
    		        }
    		}else if(isCollected == commonCollection.IS_NOT_COLLECTED){
    			
    	    	    Integer updateNum = 0;
    			    Date currentTime = new Date();
    			    commonCollection.setIsDelete(1);
    			    commonCollection.setModifyTime(currentTime);
    			    commonCollection.setSource(CommonCollection.SOURCE_TEST_SCALE);
    		     	
    		        updateNum = service.cancelCollection(commonCollection);
    		        
    		        if(updateNum==null || updateNum==0){
    		        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
    					result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
    					return result;
    		        }
    		}else{
    	     	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
    			result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
    			return result;
    		}
    		
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	isSuccess = true;
    	result.put("isSuccess",isSuccess);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
//    @RequestMapping(value = "cancelCollectionTestScale",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
//    public Object cancelCollectionTestScale(CommonCollection commonCollection){
//         
//    	ResultEntity result = new ResultEntity(); 
//    	Integer updateNum = 0;
//    	try{
//    		
//    		Long articleId = commonCollection.getArticleId();
//    		Long mid = commonCollection.getMid();
//    		
//    		if(articleId==null || articleId==0||
//    		   mid==null || mid==0){
//		       	result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//				return result;
//    		}
//    		
//		    Date currentTime = new Date();
//		    commonCollection.setIsDelete(1);
//		    commonCollection.setModifyTime(currentTime);
//		    commonCollection.setSource(CommonCollection.SOURCE_TEST_SCALE);
//	     	
//	        updateNum = service.cancelCollection(commonCollection);
//	        
//	        if(updateNum==null || updateNum==0){
//	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
//				return result;
//	        }
//	        
//    	}catch(Exception e){
//    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
//			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
//			result.setMsg(e.getMessage());
//			return result;
//    	}
//    	result.put("updateNum",updateNum);
//		result.setCode(ErrorCode.SUCCESS.getCode());
//		result.setMsg(ErrorCode.SUCCESS.getMessage());
//		return result;
//    }
    
    
}

