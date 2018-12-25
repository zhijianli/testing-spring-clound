package com.xinmao.common.consultantCenter.mapper;

import com.xinmao.common.consultantCenter.domain.ConsultingFieldDetailRelation;

import java.util.List;

/**
 * 咨询师领域与领域详情关联关系相关的Map
 * @time 2018.11.25
 * @author 李志坚
 */
public interface ConsultingFieldDetailRelationMapper {

    int insertOrUpdateRelation(ConsultingFieldDetailRelation consultingFieldDetailRelation);

    List<Long> getConsultingFieldDetailIdList(ConsultingFieldDetailRelation consultingFieldDetailRelation);

    void deleteRelationByConsultantId(ConsultingFieldDetailRelation consultingFieldDetailRelation);

}