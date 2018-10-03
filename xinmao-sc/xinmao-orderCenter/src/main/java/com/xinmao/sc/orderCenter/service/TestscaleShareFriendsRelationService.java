package com.xinmao.sc.orderCenter.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.sc.orderCenter.domain.TestscaleShareFriendsRelation;
import com.xinmao.sc.orderCenter.mapper.TestscaleShareFriendsRelationMapper;


@Service
public class TestscaleShareFriendsRelationService {
    
    @Autowired
    private TestscaleShareFriendsRelationMapper mapper;
    
    public List<Long> getTesterListBySharingPersonIdList(TestscaleShareFriendsRelation record){
    	return mapper.getTesterListBySharingPersonIdList(record);
    }
    
    public List<Long> getSharingPersonIdListByTesterIdList(TestscaleShareFriendsRelation record){
    	return mapper.getSharingPersonIdListByTesterIdList(record);
    }
    
    public TestscaleShareFriendsRelation insertRelation(TestscaleShareFriendsRelation record) {
    	TestscaleShareFriendsRelation relation = new TestscaleShareFriendsRelation();
    	relation.setSharingPersonId(record.getSharingPersonId());
    	relation.setTesterId(record.getTesterId());
    	relation.setTestScaleId(record.getTestScaleId());
    	TestscaleShareFriendsRelation singleRel = mapper.getSingleMessage(relation);
    	if(singleRel==null){
    	   mapper.insertRelation(record);
    	   return record;
    	}else{
    	   return singleRel;
    	}
    }
    
}

