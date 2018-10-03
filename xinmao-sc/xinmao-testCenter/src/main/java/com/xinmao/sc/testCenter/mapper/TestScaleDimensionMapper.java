package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScaleDimensionMessage;

public interface TestScaleDimensionMapper {

    List<TestScaleDimensionMessage> selectAll(TestScaleDimensionMessage tsdMessage);
    
    List<Integer> selectAllId(TestScaleDimensionMessage tsdMessage);
    
    List<TestScaleDimensionMessage> batchSelectMessage(TestScaleDimensionMessage tsdMessage);

    TestScaleDimensionMessage getMessById(int id);
    
    int insertMess(TestScaleDimensionMessage testScaleDimensionMessage);
    
    int updateMess(TestScaleDimensionMessage testScaleDimensionMessage);
    
    int batchDeleteMessByTestScaleId(TestScaleDimensionMessage testScaleDimensionMessage);
    
}
