//package com.xinmao.gateway.token;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//
///**
// * 容器，系统启动时负责加载token验证的拦截器
// * @author 李志坚
// * @date 2018/2/2
// */
//@Configuration
//public class TokenConfig extends WebMvcConfigurerAdapter {
//
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor);
//    }
//
//}
