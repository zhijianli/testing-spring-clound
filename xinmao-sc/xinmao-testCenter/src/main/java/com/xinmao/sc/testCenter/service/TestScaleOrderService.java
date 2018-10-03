package com.xinmao.sc.testCenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.xinmao.sc.orderCenter.api.TestScaleOrderApi;

@FeignClient(value = "orderCenter-provider")
public interface TestScaleOrderService extends TestScaleOrderApi{

}
