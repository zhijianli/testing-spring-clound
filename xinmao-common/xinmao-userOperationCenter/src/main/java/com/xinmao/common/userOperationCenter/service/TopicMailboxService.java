package com.xinmao.common.userOperationCenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.xinmao.tc.topicCenter.api.TopicMailboxApi;

@FeignClient(value = "topicCenter-provider")
public interface TopicMailboxService  extends TopicMailboxApi{
	
}
