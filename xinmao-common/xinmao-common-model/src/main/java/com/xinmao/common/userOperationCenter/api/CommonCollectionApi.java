package com.xinmao.common.userOperationCenter.api;


import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmao.common.userOperationCenter.domain.CommonCollection;

@RequestMapping("commonCollection")
public interface CommonCollectionApi {
	
	    @RequestMapping(value="/getCollectionListByUserId",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public List<CommonCollection> getCollectionListByUserId(@RequestBody CommonCollection commonCollection);
	    
	    @RequestMapping(value="/isCollected",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public Boolean isCollected(@RequestBody CommonCollection commonCollection);
	    
}
