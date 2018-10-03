package com.xinmao.sc.orderCenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan("com.moluan.eureka.provider.mapper.AppMessageMapper")
@EnableFeignClients
@EnableTransactionManagement
@MapperScan(basePackages = { "com.xinmao.sc.orderCenter.mapper" })
@EnableEurekaClient
@SpringBootApplication
public class OrderCenterApplication {

	public static void main(String[] args) {
		System.setProperty("WORKDIR", "/home/server/");
		SpringApplication.run(OrderCenterApplication.class, args);
	}
}
