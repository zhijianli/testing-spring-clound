package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;



public interface TestScaleClassMapper {
	
    List<TestScaleClassMessage> getHomeRecommMessage(TestScaleClassMessage testScaleClassMessage);

	List<TestScaleClassMessage> getAllMessageByIdList(TestScaleClassMessage testScaleClassMessage);
	
    List<TestScaleClassMessage> selectAll();

    TestScaleClassMessage getMessById(int id);
    
    TestScaleClassMessage getMessByIdFromFront(int id);
    
    int insertMess(TestScaleClassMessage testScaleClassMessage);
    
    int updateMess(TestScaleClassMessage testScaleClassMessage);
    
}
