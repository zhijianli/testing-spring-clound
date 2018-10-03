package com.xinmao.sc.orderCenter.mapper;

import java.util.List;

import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;

public interface TestScaleOrderMapper {

	List<TestScaleOrderMessage> getAllMessageByLimit(TestScaleOrderMessage testScaleOrderMessage);
	
	TestScaleOrderMessage getLatestOrder(TestScaleOrderMessage testScaleOrderMessage);
	
    List<TestScaleOrderMessage> selectAll(TestScaleOrderMessage testScaleOrderMessage);

    TestScaleOrderMessage getMessById(int id);
    
    int insertMess(TestScaleOrderMessage testScaleOrderMessage);
    
    int updateMess(TestScaleOrderMessage testScaleOrderMessage);
    
    int getTestScaleOrderNum();
    
    int getTestScaleOrderNumByCondition(TestScaleOrderMessage testScaleOrderMessage);
    
}
