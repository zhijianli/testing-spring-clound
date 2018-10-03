package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScaleDimensionTitleRelationMessage;

public interface TestScaleDimensionTitleRelationMapper {

    List<TestScaleDimensionTitleRelationMessage> selectAll(TestScaleDimensionTitleRelationMessage tsdtrMessage);

    List<TestScaleDimensionTitleRelationMessage> getMessById(int id);
    
    int insertMess(TestScaleDimensionTitleRelationMessage testScaleDimensionTitleRelationMessage);
    
    int updateMess(TestScaleDimensionTitleRelationMessage testScaleDimensionTitleRelationMessage);
    
    int batchDeleteMess(TestScaleDimensionTitleRelationMessage testScaleDimensionTitleRelationMessage);
    
    int batchDeleteMessByTitleId(TestScaleDimensionTitleRelationMessage testScaleDimensionTitleRelationMessage);
 
    int batchDeleteMessByDimensionId(TestScaleDimensionTitleRelationMessage testScaleDimensionTitleRelationMessage);

    int batchDeleteMessByDimensionIdList(TestScaleDimensionTitleRelationMessage testScaleDimensionTitleRelationMessage);
}
