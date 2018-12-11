package com.xinmao.common.consultantCenter.service;

import com.xinmao.common.consultantCenter.domain.Consultant;
import com.xinmao.common.consultantCenter.mapper.ConsultantMapper;
import com.xinmao.common.userCenter.domain.Member;
import com.xinmao.common.userCenter.domain.wechat.MemberWechat;
import com.xinmao.common.userCenter.domain.wechat.UserWeiXin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 咨询师相关的Service
 * @time 2018.11.18
 * @author 李志坚
 */
@Service
public class ConsultantService {
	
	@Autowired
	ConsultantMapper consultantMapper;

//	public static int WECHAT_OPEN = 0; //开放平台


    public Consultant getConsultantById(Long id) {
        return consultantMapper.getConsultantById(id);
    }

    public List<Consultant> getAllMessageByCondition(Consultant consultant) {
        return consultantMapper.getAllMessageByCondition(consultant);
    }

    public int selectCount(Consultant consultant) {
        return consultantMapper.selectCount(consultant);
    }

    public int insertOrUpdateConsultant(Consultant consultant) {
        return consultantMapper.insertOrUpdateConsultant(consultant);
    }


}
