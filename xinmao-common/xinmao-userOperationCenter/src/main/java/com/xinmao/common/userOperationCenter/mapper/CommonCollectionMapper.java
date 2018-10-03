package com.xinmao.common.userOperationCenter.mapper;


import java.util.List;

import com.xinmao.common.userOperationCenter.domain.CommonCollection;

public interface CommonCollectionMapper {


    int insert(CommonCollection record);
    
    Integer cancelCollection(CommonCollection commonCollection);
    
    List<CommonCollection> getCollectionListByUserId(CommonCollection commonCollection);
    
}