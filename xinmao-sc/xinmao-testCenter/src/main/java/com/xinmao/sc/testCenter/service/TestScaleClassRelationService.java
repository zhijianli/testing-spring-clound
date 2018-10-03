package com.xinmao.sc.testCenter.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.sc.testCenter.domain.TestScaleClassRelationMessage;
import com.xinmao.sc.testCenter.mapper.TestScaleClassRelationMapper;

@Service
public class TestScaleClassRelationService {
    
    @Autowired
    private TestScaleClassRelationMapper mapper;
    
    public List<Integer> getTestIdListByClassIdList(TestScaleClassRelationMessage testScaleClassRelationMessage){
    	return mapper.getTestIdListByClassIdList(testScaleClassRelationMessage);
    }
    
    public List<Integer> getAllClassIdList(TestScaleClassRelationMessage testScaleClassRelationMessage){
    	return mapper.getAllClassIdList(testScaleClassRelationMessage);
    }
    
    
    public List<TestScaleClassRelationMessage> getMessageById(int id){
         List<TestScaleClassRelationMessage> list = mapper.getMessById(id);
         return list;
    }
    
    public List<TestScaleClassRelationMessage> getAllMessage(TestScaleClassRelationMessage testScaleClassRelationMessage){
        List<TestScaleClassRelationMessage> list = mapper.selectAll(testScaleClassRelationMessage);
        return list;
   }
    
    public int addMessage(TestScaleClassRelationMessage testScaleClassRelationMessage) {
        return mapper.insertMess(testScaleClassRelationMessage);
    }
    
    public int updateMess(TestScaleClassRelationMessage testScaleClassRelationMessage) {
        return mapper.updateMess(testScaleClassRelationMessage);
    }
}

