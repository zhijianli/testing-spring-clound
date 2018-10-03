package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScaleNormMessage;

public interface TestScaleNormMapper {

    List<TestScaleNormMessage> selectAll(TestScaleNormMessage tsnMessage);

    List<TestScaleNormMessage> getMessById(int id);
    
    int insertMess(TestScaleNormMessage testScaleNormMessage);
    
    int updateMess(TestScaleNormMessage testScaleNormMessage);
    
    int batchDeleteMessById(TestScaleNormMessage testScaleNormMessage);
    
    int batchDeleteMessByTestScaleId(TestScaleNormMessage testScaleNormMessage);
    
    int batchDeleteMessByPopulationRangeId(TestScaleNormMessage testScaleNormMessage);
    
}
