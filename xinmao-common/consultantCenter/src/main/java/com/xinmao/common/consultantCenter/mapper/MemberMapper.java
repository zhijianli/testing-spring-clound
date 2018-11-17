package com.xinmao.common.consultantCenter.mapper;

import com.xinmao.common.userCenter.domain.Member;

public interface MemberMapper {
	
	int insertSelective(Member record);
	
	Member selectByPrimaryKey(Long mid);
	
}
