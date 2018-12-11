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

    List<ConsultingField> getAllMessageByCondition(ConsultingField consultingField);

}