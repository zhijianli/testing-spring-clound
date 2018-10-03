package com.xinmao.sc.testCenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.xinmao.common.userOperationCenter.api.CommonCollectionApi;


@FeignClient(value = "userOperationCenter-provider")
public interface CommonCollectionService extends CommonCollectionApi{

}
