package com.xinmao.sc.testCenter.api;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;

@RequestMapping("testScaleInfo")
public interface TestScaleInfoApi {

	    @RequestMapping(value="/getTestScaleNum",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public ResultEntity getTestScaleNum();
	    
	    @RequestMapping(value="/getTestScaleMap",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public ResultEntity getTestScaleMap(@RequestBody TestScaleInfoMessage tsInfoMess);
	    
	    @RequestMapping(value="/getTestScaleAllMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public TestScaleInfoMessage getTestScaleAllMessageById(@RequestBody TestScaleOrderMessage tsoMessage);
	    
	    @RequestMapping(value="/addNumberOfTest",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public ResultEntity addNumberOfTest(@RequestBody TestScaleInfoMessage tsInfoMess);
	    
	    @RequestMapping(value="/getTestScaleAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public TestScaleInfoMessage getTestScaleAllMessage(@RequestBody TestScaleOrderMessage tsoMessage);
	    
}
