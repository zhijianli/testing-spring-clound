package com.xinmao.common.consultantCenter.service;

import com.xinmao.common.consultantCenter.domain.Consultant;
import com.xinmao.common.consultantCenter.mapper.ConsultingFieldDetailRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 咨询师领域与领域详情关联关系的Service
 * @time 2018.11.25
 * @author 李志坚
 */
@Service
public class ConsultingFieldDetailRelationService {
	
	@Autowired
    ConsultingFieldDetailRelationMapper consultingFieldDetailRelationMapper;

//	public static int WECHAT_OPEN = 0; //开放平台

//    public List<Consultant> getAllMessageByCondition(Consultant consultant) {
//        return consultantMapper.getAllMessageByCondition(consultant);
//    }
}
