package com.xinmao.tc.topicCenter.api;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmao.tc.topicCenter.domain.TopicMailbox;


@RequestMapping("topicMailbox")
public interface TopicMailboxApi {
	
	 @RequestMapping(value="/updateStatus",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	 public Integer updateStatus(@RequestBody TopicMailbox topicMailbox);
}
