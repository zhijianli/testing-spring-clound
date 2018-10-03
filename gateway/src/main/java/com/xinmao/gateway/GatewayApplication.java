package com.xinmao.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;


/**
 * 项目启动类
 * 启动的时候加载了两个连接器，负责把端口号"depressionPort"和"springCloundPort",跳转到端口号"serverPort"
 * @author 李志坚
 * @date 2018/2/2.
 */
@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan(basePackages = { "com.xinmao.gateway.mapper" })
@EnableZuulProxy
public class GatewayApplication {
	
//	 @Value("${server.port}")  
//	 private Integer serverPort;   
	
//	 @Value("${port.depression}")  
//	 private Integer depressionPort;    

//	 @Value("${port.springClound}")  
//	 private Integer springCloundPort;  
	 
//	 @Bean
//	 public EmbeddedServletContainerFactory servletContainer() {
//	        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//	            @Override
//	            protected void postProcessContext(Context context) {
//	                //Due to CONFIDENTIAL and /*, this will cause Tomcat to redirect every request to HTTPS. 
//	                //You can configure multiple patterns and multiple constraints if you need more control over what is and is not redirected.
//
//	                SecurityConstraint constraint = new SecurityConstraint();
//	                constraint.setUserConstraint("CONFIDENTIAL");
//	                SecurityCollection collection = new SecurityCollection();
//	                collection.addPattern("/*");
//	                constraint.addCollection(collection);
//	                context.addConstraint(constraint);
//	            }
//	        };
////	        tomcat.addAdditionalTomcatConnectors(httpDepressionConnector());
//	        tomcat.addAdditionalTomcatConnectors(httpSpringCloundConnector());
//	        return tomcat;
//
//	  }
//	  @Bean
//	  public Connector httpDepressionConnector() {
//	        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//
//	        //Set the scheme that will be assigned to requests received through this connector
//	        //@param scheme The new scheme
//	        connector.setScheme("http");
//
//	        //Set the port number on which we listen for requests.
//	        // @param port The new port number
//	        connector.setPort(depressionPort);       
//
//	        //Set the secure connection flag that will be assigned to requests received through this connector.
//	        //@param secure The new secure connection flag
//	        //if connector.setSecure(true),the http use the http and https use the https;else if connector.setSecure(false),the http redirect to https;
//	        //如果connector.setSecure(true)，http用http，https用https；
//	        //否则如果connector.setSecure(false)，则http跳转到https，
//	        connector.setSecure(true);
//
//	        //redirectPort The redirect port number (non-SSL to SSL)
//	        connector.setRedirectPort(serverPort);
//	        return connector;
//	    }
	  
//	  @Bean
//	  public Connector httpSpringCloundConnector() {
//	        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//
//	        //Set the scheme that will be assigned to requests received through this connector
//	        //@param scheme The new scheme
//	        connector.setScheme("http");
//
//	        //Set the port number on which we listen for requests.
//	        // @param port The new port number
//	        connector.setPort(springCloundPort);       
//
//	        //Set the secure connection flag that will be assigned to requests received through this connector.
//	        //@param secure The new secure connection flag
//	        //if connector.setSecure(true),the http use the http and https use the https;else if connector.setSecure(false),the http redirect to https;
//	        connector.setSecure(true);
//
//	        //redirectPort The redirect port number (non-SSL to SSL)
//	        connector.setRedirectPort(serverPort);
//	        return connector;
//	    }
	  
	    public static void main(String[] args) throws Exception {           
	        SpringApplication.run(GatewayApplication.class, args);
	    }
	    
//	    @Override
//	    public void run(String... arg0) throws Exception {
//	        // TODO Auto-generated method stub
//	    }
}
