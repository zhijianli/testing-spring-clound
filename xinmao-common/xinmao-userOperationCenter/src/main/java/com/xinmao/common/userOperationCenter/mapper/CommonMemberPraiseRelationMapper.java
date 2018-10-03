package com.xinmao.common.userOperationCenter.mapper;


import java.util.List;

import com.xinmao.common.userOperationCenter.domain.CommonMemberPraiseRelation;

public interface CommonMemberPraiseRelationMapper {

    int insert(CommonMemberPraiseRelation record);
    
    List<CommonMemberPraiseRelation> getPraiseByRelationId(CommonMemberPraiseRelation record);
}