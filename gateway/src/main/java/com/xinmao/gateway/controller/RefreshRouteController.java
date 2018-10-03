package com.xinmao.gateway.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.gateway.service.RefreshRouteService;

/**
 * 动态路由相关的，负责刷新数据库路由信息到内存中
 * @author 李志坚
 * @date 2018/2/2.
 */
@RestController
@RequestMapping("dynamicRouting")
public class RefreshRouteController {
	
	private static final Logger log = LogManager.getLogger();
	
    @Autowired
    private RefreshRouteService service;
    
    @RequestMapping(value="/refreshRoute",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public Object refreshRoute(HttpServletRequest request){
   
    	String resultInfo = null;
    	
    	try{
		    service.refreshRoute();
		    resultInfo = "refresh is success";
    	}catch(Exception e){
			log.error("system is error", e);
			resultInfo="system is error";
			return resultInfo;
    	}
    	
		return resultInfo;
        
    }

}
