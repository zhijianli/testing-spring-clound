package com.xinmao.sc.orderCenter.mapper;

import java.util.List;

import com.xinmao.sc.orderCenter.domain.TestScaleOrderDimensionNormMessage;

public interface TestScaleOrderDimensionNormMapper {

    List<TestScaleOrderDimensionNormMessage> selectAll(TestScaleOrderDimensionNormMessage tsodnMessage);

    List<TestScaleOrderDimensionNormMessage> getMessById(int id);
    
    int insertMess(TestScaleOrderDimensionNormMessage tsodnMessage);
    
    int updateMess(TestScaleOrderDimensionNormMessage tsodnMessage);
    
    
}
