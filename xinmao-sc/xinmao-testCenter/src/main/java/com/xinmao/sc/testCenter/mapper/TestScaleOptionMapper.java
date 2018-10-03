package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;

public interface TestScaleOptionMapper {

	
	List<TestScaleOptionMessage> selectAllByTitleIdList(TestScaleOptionMessage testScaleOptionMessage);
	
    List<TestScaleOptionMessage> selectAll(TestScaleOptionMessage testScaleOptionMessage);

    TestScaleOptionMessage getMessById(int id);
    
    int insertMess(TestScaleOptionMessage testScaleOptionMessage);
    
    int updateMess(TestScaleOptionMessage testScaleOptionMessage);
    
    int batchDeleteMess(TestScaleOptionMessage testScaleOptionMessage);
    
    int batchDeleteMessByTitleId(TestScaleOptionMessage testScaleOptionMessage);
    
    int batchDeleteMessByTitleIdList(TestScaleOptionMessage testScaleOptionMessage);
    
}
