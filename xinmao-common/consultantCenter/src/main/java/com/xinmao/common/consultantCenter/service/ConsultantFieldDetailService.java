package com.xinmao.common.consultantCenter.service;

import com.xinmao.common.consultantCenter.domain.Consultant;
import com.xinmao.common.consultantCenter.mapper.ConsultingFieldDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 咨询师领域详情相关的Service
 * @time 2018.11.25
 * @author 李志坚
 */
@Service
public class ConsultantFieldDetailService {
	
	@Autowired
    ConsultingFieldDetailMapper consultingFieldDetailMapper;

//	public static int WECHAT_OPEN = 0; //开放平台

//    public List<Consultant> getAllMessageByCondition(Consultant consultant) {
//        return consultantMapper.getAllMessageByCondition(consultant);
//    }
}
