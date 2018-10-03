package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScaleTitleMessage;

public interface TestScaleTitleMapper {
	
	int selectCount(TestScaleTitleMessage testScaleTitleMessage);
	
	List<TestScaleTitleMessage> selectAllBySearchIdList(TestScaleTitleMessage testScaleTitleMessage);    

    List<TestScaleTitleMessage> selectAll(TestScaleTitleMessage testScaleTitleMessage);
    
    List<Integer> selectAllId(TestScaleTitleMessage testScaleTitleMessage);
    
    List<TestScaleTitleMessage> searchMessage(TestScaleTitleMessage testScaleTitleMessage);

    TestScaleTitleMessage getMessById(int id);
    
    int insertMess(TestScaleTitleMessage testScaleTitleMessage);
    
    int updateMess(TestScaleTitleMessage testScaleTitleMessage);
    
    int batchDeleteMessByTestScaleId(TestScaleTitleMessage testScaleTitleMessage);
    
}
