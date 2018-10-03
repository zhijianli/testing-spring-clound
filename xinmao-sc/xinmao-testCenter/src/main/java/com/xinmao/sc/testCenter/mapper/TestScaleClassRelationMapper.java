package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.TestScaleClassRelationMessage;

public interface TestScaleClassRelationMapper {

	List<Integer> getTestIdListByClassIdList(TestScaleClassRelationMessage testScaleClassRelationMessage);
	
	List<Integer> getAllClassIdList(TestScaleClassRelationMessage testScaleClassRelationMessage);
	
    List<TestScaleClassRelationMessage> selectAll(TestScaleClassRelationMessage testScaleClassRelationMessage);
    
    List<TestScaleClassRelationMessage> getMessById(int id);
    
    int insertMess(TestScaleClassRelationMessage testScaleClassRelationMessage);
    
    int updateMess(TestScaleClassRelationMessage testScaleClassRelationMessage);
    
    int batchDeleteMess(TestScaleClassRelationMessage testScaleClassRelationMessage);
    
    int batchDeleteMessByClassId(TestScaleClassRelationMessage testScaleClassRelationMessage);
    
    int batchDeleteMessByTestScaleId(TestScaleClassRelationMessage testScaleClassRelationMessage);
    
}
