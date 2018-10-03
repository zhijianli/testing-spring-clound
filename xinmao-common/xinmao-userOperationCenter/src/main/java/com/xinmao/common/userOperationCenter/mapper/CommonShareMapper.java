package com.xinmao.common.userOperationCenter.mapper;

import com.xinmao.common.userOperationCenter.domain.CommonShare;

public interface CommonShareMapper {

    int deleteByPrimaryKey(Long shareId);

    int insert(CommonShare record);

    int insertSelective(CommonShare record);

    CommonShare selectByPrimaryKey(Long shareId);

    int updateByPrimaryKeySelective(CommonShare record);

    int updateByPrimaryKey(CommonShare record);
}