package com.xinmao.sc.testCenter.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("hello")
public class HelloController{
	
//	Logger log = Logger.getLogger(this.getClass());

	private static final Logger log = LogManager.getLogger();
    
    @RequestMapping(value="/world",method=RequestMethod.GET)
    public Object world(){
       log.error("hello world!!!");
       return "hello world!";
    }
    
   
    
}

