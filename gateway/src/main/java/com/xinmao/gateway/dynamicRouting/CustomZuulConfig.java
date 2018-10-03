package com.xinmao.gateway.dynamicRouting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;


/**
 * 动态路由容器，系统启动时加载动态路由器
 * @author 李志坚
 * @date 2018/2/2.
 */
@Configuration
public class CustomZuulConfig {
	
	private static final Logger logger = LogManager.getLogger();

    @Autowired
    ZuulProperties zuulProperties;
    
    @Autowired
    ServerProperties server;
    
    @Autowired
    DiscoveryClient discovery;
    
    @Bean
    public CustomRouteLocator routeLocator() {
    	logger.error(this.discovery);
        CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServletPrefix(),this.discovery,this.zuulProperties);
        return routeLocator;
    }

}
