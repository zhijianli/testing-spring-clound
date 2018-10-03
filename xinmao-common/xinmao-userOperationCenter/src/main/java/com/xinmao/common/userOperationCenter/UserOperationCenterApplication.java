package com.xinmao.common.userOperationCenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableTransactionManagement
@MapperScan(basePackages = { "com.xinmao.common.userOperationCenter.mapper" })
@EnableEurekaClient
@SpringBootApplication
public class UserOperationCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserOperationCenterApplication.class, args);
	}
}
