package com.xinmao.sc.testCenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.xinmao.common.userOperationCenter.api.CommonCommentApi;


@FeignClient(value = "userOperationCenter-provider")
public interface CommonCommentService extends CommonCommentApi{

}
