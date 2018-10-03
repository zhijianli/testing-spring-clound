package com.xinmao.sc.orderCenter.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.sc.orderCenter.domain.TestScaleOrderDimensionNormMessage;
import com.xinmao.sc.orderCenter.mapper.TestScaleOrderDimensionNormMapper;

@Service
public class TestScaleOrderDimensionNormService {
    
    @Autowired
    private TestScaleOrderDimensionNormMapper mapper;
    
    public List<TestScaleOrderDimensionNormMessage> getMessageById(int id){
         List<TestScaleOrderDimensionNormMessage> list = mapper.getMessById(id);
         return list;
    }
    
    public List<TestScaleOrderDimensionNormMessage> getAllMessage(TestScaleOrderDimensionNormMessage tsodnMessage){
        List<TestScaleOrderDimensionNormMessage> list = mapper.selectAll(tsodnMessage);
        return list;
   }
}

