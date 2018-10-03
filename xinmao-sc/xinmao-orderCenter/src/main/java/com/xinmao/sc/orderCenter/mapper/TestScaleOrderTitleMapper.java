package com.xinmao.sc.orderCenter.mapper;

import java.util.List;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;

public interface TestScaleOrderTitleMapper {

    List<TestScaleOrderTitleMessage> selectAll(TestScaleOrderTitleMessage tsotMessage);

    List<TestScaleOrderTitleMessage> getMessById(int id);
    
    int insertMess(TestScaleOrderTitleMessage testScaleOrderTitleMessage);
    
    int updateMess(TestScaleOrderTitleMessage testScaleOrderTitleMessage);
    
    int selectCount(TestScaleOrderTitleMessage testScaleOrderTitleMessage);
}
