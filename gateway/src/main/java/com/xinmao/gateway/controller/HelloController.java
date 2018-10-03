package com.xinmao.gateway.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.gateway.service.RefreshRouteService;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试gateway是否启动成功的Controller
 * @author 李志坚
 * @date 2018/2/2.
 */
@RestController
@RequestMapping("hello")
public class HelloController{
	
	private static final Logger log = LogManager.getLogger();
	
    @RequestMapping(value="/world",method=RequestMethod.GET)
    public Object world(HttpServletRequest request){
    	
       Integer port = request.getServerPort();
    	
       log.error("hello world!!!,port = "+port);
       return "hello world!";
    }
    
}

