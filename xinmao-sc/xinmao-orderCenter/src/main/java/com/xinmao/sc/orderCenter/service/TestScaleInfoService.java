package com.xinmao.sc.orderCenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.xinmao.sc.testCenter.api.TestScaleInfoApi;

@FeignClient(value = "testCenter-provider")
public interface TestScaleInfoService extends TestScaleInfoApi{

}
