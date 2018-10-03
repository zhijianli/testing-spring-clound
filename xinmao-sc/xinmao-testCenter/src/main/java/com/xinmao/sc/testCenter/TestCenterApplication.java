package com.xinmao.sc.testCenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan("com.moluan.eureka.provider.mapper.AppMessageMapper")
@EnableFeignClients
@EnableTransactionManagement
@MapperScan(basePackages = { "com.xinmao.sc.testCenter.mapper" })
@EnableEurekaClient
@SpringBootApplication
public class TestCenterApplication {


	public static void main(String[] args) {
//		String logdir = System.getProperty("logdir");
//		System.setProperty("logdir",logdir);
		SpringApplication.run(TestCenterApplication.class, args);
	}
}
