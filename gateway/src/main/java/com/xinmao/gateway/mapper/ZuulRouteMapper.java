package com.xinmao.gateway.mapper;

import java.util.List;

import com.xinmao.gateway.domain.ZuulRouteMessage;


public interface ZuulRouteMapper {

    List<ZuulRouteMessage> selectAll();
    
}
