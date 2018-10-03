package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScalePopulationRangeMessage;

public interface TestScalePopulationRangeMapper {

    List<TestScalePopulationRangeMessage> selectAll(TestScalePopulationRangeMessage testScalePopulationRangeMessage);

    TestScalePopulationRangeMessage getMessById(int id);
    
    int insertMess(TestScalePopulationRangeMessage testScalePopulationRangeMessage);
    
    int updateMess(TestScalePopulationRangeMessage testScalePopulationRangeMessage);
    
    int batchDeleteMess(TestScalePopulationRangeMessage testScalePopulationRangeMessage);
    
    int batchDeleteMessByTestScaleId(TestScalePopulationRangeMessage testScalePopulationRangeMessage);
}
