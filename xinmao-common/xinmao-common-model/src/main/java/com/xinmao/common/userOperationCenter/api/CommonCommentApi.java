package com.xinmao.common.userOperationCenter.api;


import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmao.common.userOperationCenter.domain.CommonComment;

@RequestMapping("commonComment")
public interface CommonCommentApi {
	
    @RequestMapping(value = "getTestScaleCommentNum",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Integer getTestScaleCommentNum(@RequestBody CommonComment commonComment);
    
    @RequestMapping(value = "getTopicIdListByMid",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public List<Long> getTopicIdListByMid(@RequestBody CommonComment commonComment);
    
    @RequestMapping(value = "getCommentNum",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Integer getCommentNum(@RequestBody CommonComment commonComment);
    
    @RequestMapping(value = "getSingleMessage",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public CommonComment getSingleMessage(@RequestBody CommonComment commonComment);
	    
}
