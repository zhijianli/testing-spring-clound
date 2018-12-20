package com.xinmao.common.consultantCenter.mapper;

import com.xinmao.common.consultantCenter.domain.Consultant;
import com.xinmao.common.consultantCenter.domain.ConsultingField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 咨询师领域相关的Map
 * @time 2018.11.25
 * @author 李志坚
 */
public interface ConsultingFieldMapper {

    void deleteConsultingField(ConsultingField consultingField);

    ConsultingField getConsultingFieldById(Long id);

    List<ConsultingField> getAllMessageByCondition(ConsultingField consultingField);

    int selectCount(ConsultingField consultingField);

    int insertOrUpdateConsultantField(ConsultingField consultingField);

    Long getConsultantFieldId(ConsultingField consultingField);

}