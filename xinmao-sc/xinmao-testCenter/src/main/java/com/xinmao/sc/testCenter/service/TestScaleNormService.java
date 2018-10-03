package com.xinmao.sc.testCenter.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.sc.testCenter.domain.TestScaleNormMessage;
import com.xinmao.sc.testCenter.mapper.TestScaleNormMapper;



@Service
public class TestScaleNormService {
    
    @Autowired
    private TestScaleNormMapper mapper;
    
    public List<TestScaleNormMessage> getAllMessage(TestScaleNormMessage tsnMessage){
        return mapper.selectAll(tsnMessage);
    }
    
}

