package com.xinmao.sc.orderCenter.mapper;

import java.util.List;

import com.xinmao.sc.orderCenter.domain.TestscaleQualitativeMemberWechatRelation;

public interface TestscaleQualitativeMemberWechatRelationMapper {

    void insert(TestscaleQualitativeMemberWechatRelation record);
    
    TestscaleQualitativeMemberWechatRelation selectByTestScaleIdAndMid(TestscaleQualitativeMemberWechatRelation record);

    List<TestscaleQualitativeMemberWechatRelation> getListByQidAndMidList(TestscaleQualitativeMemberWechatRelation record);
    
    TestscaleQualitativeMemberWechatRelation getListByQidAndMid(TestscaleQualitativeMemberWechatRelation record);

    Integer updateMessage(TestscaleQualitativeMemberWechatRelation record);
}