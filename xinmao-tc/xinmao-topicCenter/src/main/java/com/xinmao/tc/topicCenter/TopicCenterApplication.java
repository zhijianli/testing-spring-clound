package com.xinmao.tc.topicCenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan("com.moluan.eureka.provider.mapper.AppMessageMapper")
@EnableFeignClients
@EnableTransactionManagement
@MapperScan(basePackages = { "com.xinmao.tc.topicCenter.mapper" })
@EnableEurekaClient
@SpringBootApplication
public class TopicCenterApplication {


	public static void main(String[] args) {
		SpringApplication.run(TopicCenterApplication.class, args);
	}
}
