package com.xinmao.sc.orderCenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.xinmao.sc.testCenter.api.TestScaleDimensionApi;

@FeignClient(value = "testCenter-provider")
public interface TestScaleDimensionService extends TestScaleDimensionApi{

}
