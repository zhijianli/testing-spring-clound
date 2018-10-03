package com.xinmao.common.bannerCenter.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.common.bannerCenter.service.BannerService;
import com.xinmao.common.bannerCenter.domain.BannerMessage;
import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;


@RestController
@RequestMapping("banner")
public class BannerController {
	
	Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private BannerService service;
    
    
    @RequestMapping(value="/getMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageById(HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	BannerMessage bannerMessage = new BannerMessage();
    	
    	try{
	    	
	        Integer bannerId = Integer.parseInt(request.getParameter("bannerId"));
		    if(bannerId==null || bannerId ==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        
	        bannerMessage = service.getMessageById(bannerId);
	    	
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("bannerMessage", bannerMessage);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    
    @RequestMapping(value="/getBannerByShowLocation",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getBannerByShowLocation(HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	List<BannerMessage> list = new ArrayList<BannerMessage>();
    	int count = 0;
    	try{
    		Integer showLocation = Integer.parseInt(request.getParameter("showLocation"));
    		
    		BannerMessage bannerMessage = new BannerMessage();
    		bannerMessage.setShowLocation(showLocation);
	        list = service.getBannerByShowLocation(bannerMessage);
	        if(list!=null&&list.size()>0){
	        	count = list.size();
	        }

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		result.put("bannerList", list);
		return result;
        
    }
    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(){
    	ResultEntity result = new ResultEntity(); 
    	List<BannerMessage> list = new ArrayList<BannerMessage>();
    	int count = 0;
    	try{
    		
    		//传一个空的bannerMessage，表示查询不带任何条件
    		BannerMessage bannerMessage = new BannerMessage();
	        list = service.selectMessByCondition(bannerMessage);
	        if(list!=null&&list.size()>0){
	        	count = list.size();
	        }

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("count", count);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		result.put("bannerList", list);
		return result;
        
    }
    
    
    @RequestMapping(value = "add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(BannerMessage bannerMessage){
         
    	ResultEntity result = new ResultEntity(); 
    	Integer bannerId = 0;
    	try{
    		
    		//判断是否有重复排序号的banner存在了
    		if(!isRepeatBanner(bannerMessage)){
		       	result.setCode(ErrorCode.ERROR_HAVE_SAME_SORTING.getCode());
				result.setMsg(ErrorCode.ERROR_HAVE_SAME_SORTING.getMessage());
				return result;
    		}
    		
		    Date currentTime = new Date();
		    bannerMessage.setIsDelete(0);
		    bannerMessage.setIsEnable(0);
		    bannerMessage.setCreateTime(currentTime);
		    bannerMessage.setModifyTime(currentTime);
	     	
	        service.addMessage(bannerMessage);
	        
	        bannerId = bannerMessage.getBannerId();
	        if(bannerId==null || bannerId==0){
	        	result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
				result.setMsg(ErrorCode.ERROR_SYSTEM_ERROR.getMessage());
				return result;
	        }
	        
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("bannerId",bannerId);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(BannerMessage bannerMessage){

    	ResultEntity result = new ResultEntity(); 
    	int updateNum = 0;
    	
    	try{
    		
	        Integer bannerId = bannerMessage.getBannerId();
		    if(bannerId==null || bannerId ==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
    		
    		//判断是否有重复排序号的banner存在了
    		if(!isRepeatBanner(bannerMessage)){
		       	result.setCode(ErrorCode.ERROR_HAVE_SAME_SORTING.getCode());
				result.setMsg(ErrorCode.ERROR_HAVE_SAME_SORTING.getMessage());
				return result;
    		}
    		
		    Date currentTime = new Date();
		    bannerMessage.setModifyTime(currentTime);
	    	
	        updateNum = service.updateMess(bannerMessage);
	    	
	    	if(updateNum==0){
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
    	result.put("updateNum", updateNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }


	private Boolean isRepeatBanner(BannerMessage bannerMessage) {
		BannerMessage bannerMess = new BannerMessage();
		bannerMess.setShowLocation(BannerMessage.APP_TEST_HOME_PAGE);
		List<BannerMessage> bannerList = service.selectMessByCondition(bannerMessage);
		if(bannerList!=null && bannerList.size()>0){
			for(int i=0;i<bannerList.size();i++){
				BannerMessage bm = bannerList.get(i);
				Integer sorting = bm.getSorting();
				Integer addSorting = bannerMessage.getSorting();
				if(addSorting!=null && addSorting==sorting){
					return false;
				}
			}
		}
		return true;
	}
	
	
	@RequestMapping(value = "/delete",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object deleteMessage(HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	int deleteNum = 0;
    	try{
	        
	        Integer bannerId =  Integer.parseInt(request.getParameter("bannerId"));
	        if(bannerId==null || bannerId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
	        
	        Date currentTime = new Date();
	        BannerMessage bannerMessage = new BannerMessage();
	        bannerMessage.setBannerId(bannerId);
	        bannerMessage.setIsDelete(BannerMessage.IS_DELETE);
	        bannerMessage.setModifyTime(currentTime);
	        deleteNum = service.updateMess(bannerMessage);
	    	
	    	if(deleteNum==0){
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
    	result.put("deleteNum", deleteNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
    
}

