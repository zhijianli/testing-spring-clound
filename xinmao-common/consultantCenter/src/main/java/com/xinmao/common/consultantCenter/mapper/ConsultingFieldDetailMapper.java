package com.xinmao.common.consultantCenter.mapper;

import com.xinmao.common.consultantCenter.domain.ConsultingFieldDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 咨询师领域详情相关的Map
 * @time 2018.11.25
 * @author 李志坚
 */
public interface ConsultingFieldDetailMapper {

    void deleteConsultantFieldDetail(ConsultingFieldDetail consultingFieldDetail);

    int insertOrUpdateConsultantFieldDetail(ConsultingFieldDetail consultingFieldDetail);

    List<ConsultingFieldDetail> getCfdListByCondition(ConsultingFieldDetail consultingFieldDetail);

}