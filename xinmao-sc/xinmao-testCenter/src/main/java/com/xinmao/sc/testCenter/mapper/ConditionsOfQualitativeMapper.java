package com.xinmao.sc.testCenter.mapper;

import java.util.List;

import com.xinmao.sc.testCenter.domain.ConditionsOfQualitativeMessage;

public interface ConditionsOfQualitativeMapper {

    List<ConditionsOfQualitativeMessage> selectAll(ConditionsOfQualitativeMessage coqMessage);

    ConditionsOfQualitativeMessage getMessById(int id);
    
    int insertMess(ConditionsOfQualitativeMessage conditionsOfQualitativeMessage);
    
    int updateMess(ConditionsOfQualitativeMessage conditionsOfQualitativeMessage);
    
    int batchDeleteMess(ConditionsOfQualitativeMessage conditionsOfQualitativeMessage);
    
    int batchDeleteMessByQualitativeId(ConditionsOfQualitativeMessage conditionsOfQualitativeMessage);
    
    int batchDeleteMessByQualitativeIdList(ConditionsOfQualitativeMessage conditionsOfQualitativeMessage);
}
