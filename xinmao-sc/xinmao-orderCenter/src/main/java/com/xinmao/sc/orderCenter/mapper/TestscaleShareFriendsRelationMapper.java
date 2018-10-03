package com.xinmao.sc.orderCenter.mapper;

import java.util.List;

import com.xinmao.sc.orderCenter.domain.TestscaleShareFriendsRelation;

public interface TestscaleShareFriendsRelationMapper {

    int insertRelation(TestscaleShareFriendsRelation record);

    List<Long> getTesterListBySharingPersonIdList(TestscaleShareFriendsRelation record);
    
    List<Long> getSharingPersonIdListByTesterIdList(TestscaleShareFriendsRelation record);
    
    TestscaleShareFriendsRelation getSingleMessage(TestscaleShareFriendsRelation record);

}