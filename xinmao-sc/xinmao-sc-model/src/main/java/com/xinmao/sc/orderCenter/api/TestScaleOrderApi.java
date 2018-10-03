package com.xinmao.sc.orderCenter.api;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;

@RequestMapping("testScaleOrder")
public interface TestScaleOrderApi {
	    @RequestMapping(value="/getTestScaleOrderById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public TestScaleOrderMessage getTestScaleOrderById(@RequestBody TestScaleOrderMessage tsoMessage);
	    
	    @RequestMapping(value="/getLatestOrder",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public TestScaleOrderMessage getLatestOrder(@RequestBody TestScaleOrderMessage tsoMessage);
	    
}
