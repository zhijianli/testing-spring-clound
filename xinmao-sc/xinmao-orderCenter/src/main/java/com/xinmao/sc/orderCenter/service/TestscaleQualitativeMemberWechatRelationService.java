package com.xinmao.sc.orderCenter.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.sc.orderCenter.domain.TestscaleQualitativeMemberWechatRelation;
import com.xinmao.sc.orderCenter.mapper.TestscaleQualitativeMemberWechatRelationMapper;

@Service
public class TestscaleQualitativeMemberWechatRelationService {
	
	private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private TestscaleQualitativeMemberWechatRelationMapper tqmwrMapper;
    
    
    public List<TestscaleQualitativeMemberWechatRelation> getListByQidAndMidList(TestscaleQualitativeMemberWechatRelation record){
    	return tqmwrMapper.getListByQidAndMidList(record);
    }
    
    public TestscaleQualitativeMemberWechatRelation getListByQidAndMid(TestscaleQualitativeMemberWechatRelation record){
    	return tqmwrMapper.getListByQidAndMid(record);
    }
}

