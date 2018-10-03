package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScaleQualitativeMessage;

public interface TestScaleQualitativeMapper {

	List<TestScaleQualitativeMessage> getMessageByTestScaleId(TestScaleQualitativeMessage tsqMessage);
	
    List<TestScaleQualitativeMessage> selectAll(TestScaleQualitativeMessage tsqMessage);
    
    List<Integer> selectAllId(TestScaleQualitativeMessage tsqMessage);

    TestScaleQualitativeMessage getMessById(int id);
    
    int insertMess(TestScaleQualitativeMessage testScaleQualitativeMessage);
    
    int updateMess(TestScaleQualitativeMessage testScaleQualitativeMessage);
    
    int batchDeleteMessByTestScaleId(TestScaleQualitativeMessage testScaleQualitativeMessage);
    
    
}
