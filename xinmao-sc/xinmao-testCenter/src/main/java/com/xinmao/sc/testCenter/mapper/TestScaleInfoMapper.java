package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;

public interface TestScaleInfoMapper {

	int addNumberOfTest(TestScaleInfoMessage testScaleInfoMessage);
	
	int getTestScaleNum();
	
	int getTestScaleNumByCondition(TestScaleInfoMessage testScaleInfoMessage);
	
	List<TestScaleInfoMessage> getHotMessage(TestScaleInfoMessage testScaleInfoMessage);
	
	List<TestScaleInfoMessage> getAllMessageByIdList(TestScaleInfoMessage testScaleInfoMessage);
	
//	List<TestScaleInfoMessage> getAllMessageByCollectIdList(TestScaleInfoMessage testScaleInfoMessage);
	
	List<TestScaleInfoMessage> getAllMessageFromClassPage(TestScaleInfoMessage testScaleInfoMessage);
	
	List<TestScaleInfoMessage> getNewestMessage(TestScaleInfoMessage testScaleInfoMessage);
	
    Page<TestScaleInfoMessage> selectAll(TestScaleInfoMessage testScaleInfoMessage);

    TestScaleInfoMessage getMessById(int id);
    
    TestScaleInfoMessage getMessByIdFromFront(TestScaleInfoMessage testScaleInfoMessage);
    
    int insertMess(TestScaleInfoMessage testScaleInfoMessage);
    
    int updateMess(TestScaleInfoMessage testScaleInfoMessage);
    
}
