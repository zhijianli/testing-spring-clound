package com.xinmao.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.common.userCenter.domain.SystemUserInfo;
import com.xinmao.gateway.mapper.SystemUserInfoMapper;

@Service
public class SystemUserInfoService {
	
    @Autowired
    private SystemUserInfoMapper mapper;
    
    public SystemUserInfo getSystemUserInfo(SystemUserInfo systemUserInfo){
    	return mapper.getSystemUserInfo(systemUserInfo);
    }

}
