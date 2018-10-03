package com.xinmao.sc.testCenter.api;


import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.domain.TestScaleQualitativeMessage;

@RequestMapping("testScaleQualitative")
public interface TestScaleQualitativeApi {
	    
	    @RequestMapping(value="/getMessageByIdFromFront",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public ResultEntity getMessageByIdFromFront(@RequestBody TestScaleQualitativeMessage tsqMessage);
	    
	    @RequestMapping(value="/getMessageByIdList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public List<TestScaleQualitativeMessage> getMessageByIdList(@RequestBody TestScaleQualitativeMessage tsqMessage);
	    
	    @RequestMapping(value="/getListByTestScaleId",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public List<TestScaleQualitativeMessage> getListByTestScaleId(@RequestBody TestScaleQualitativeMessage tsqMessage);
}
