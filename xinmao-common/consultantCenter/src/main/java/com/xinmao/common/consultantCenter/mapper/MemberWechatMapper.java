package com.xinmao.common.consultantCenter.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.xinmao.common.userCenter.domain.wechat.MemberWechat;

public interface MemberWechatMapper {
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_wechat
     *
     * @mbggenerated Sat Dec 31 15:56:32 CST 2016
     */
    List<MemberWechat> selectSelective(MemberWechat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_wechat
     *
     * @mbggenerated Sat Dec 31 15:56:32 CST 2016
     */
    int insertSelective(MemberWechat record);
	
	
}