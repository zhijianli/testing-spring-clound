package com.xinmao.common.consultantCenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableTransactionManagement
@MapperScan(basePackages = { "com.xinmao.common.consultantCenter.mapper" })
@EnableEurekaClient
@SpringBootApplication
public class ConsultantCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultantCenterApplication.class, args);
	}
}
