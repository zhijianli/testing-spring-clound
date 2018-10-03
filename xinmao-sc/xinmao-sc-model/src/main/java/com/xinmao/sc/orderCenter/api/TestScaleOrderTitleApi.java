package com.xinmao.sc.orderCenter.api;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;

@RequestMapping("testScaleOrderTitle")
public interface TestScaleOrderTitleApi {
	    
	
	@RequestMapping(value="/getChooseTitleAndOption",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public  TestScaleOrderMessage getChooseTitleAndOption(@RequestBody TestScaleOrderTitleMessage tsotMessage);
	    
}
