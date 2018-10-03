package com.xinmao.sc.orderCenter.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;
import com.xinmao.sc.orderCenter.mapper.TestScaleOrderTitleMapper;

@Service
public class TestScaleOrderTitleService {
    
    @Autowired
    private TestScaleOrderTitleMapper mapper;
    
    public List<TestScaleOrderTitleMessage> getMessageById(int id){
         List<TestScaleOrderTitleMessage> list = mapper.getMessById(id);
         return list;
    }
    
    public List<TestScaleOrderTitleMessage> getAllMessage(TestScaleOrderTitleMessage tsotMessage){
    	return mapper.selectAll(tsotMessage);
   }
    
    public int addMessage(TestScaleOrderTitleMessage testScaleOrderTitleMessage) {
        return mapper.insertMess(testScaleOrderTitleMessage);
    }
    
    public int updateMess(TestScaleOrderTitleMessage testScaleOrderTitleMessage) {
        return mapper.updateMess(testScaleOrderTitleMessage);
    }

    public int selectCount(TestScaleOrderTitleMessage testScaleOrderTitleMessage) {
        return mapper.selectCount(testScaleOrderTitleMessage);
    }

}

