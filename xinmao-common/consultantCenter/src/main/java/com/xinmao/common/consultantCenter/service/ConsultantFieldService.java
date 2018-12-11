package com.xinmao.common.consultantCenter.service;

import com.xinmao.common.consultantCenter.mapper.ConsultingFieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.xinmao.common.consultantCenter.domain.ConsultingField;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 咨询师领域相关的Service
 * @time 2018.11.25
 * @author 李志坚
 */
@Service
public class ConsultantFieldService {
	
	@Autowired
    ConsultingFieldMapper consultantFieldMapper;

//	public static int WECHAT_OPEN = 0; //开放平台

    public List<ConsultingField> getAllMessageByCondition(ConsultingField consultingField) {
        return consultantFieldMapper.getAllMessageByCondition(consultingField);
    }
}
