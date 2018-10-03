package com.xinmao.sc.testCenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.xinmao.sc.orderCenter.api.TestScaleOrderTitleApi;

@FeignClient(value = "orderCenter-provider")
public interface TestScaleOrderTitleService extends TestScaleOrderTitleApi{

}
