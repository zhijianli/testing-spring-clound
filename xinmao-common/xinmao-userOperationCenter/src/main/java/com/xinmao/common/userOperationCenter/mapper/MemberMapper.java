package com.xinmao.common.userOperationCenter.mapper;

import com.xinmao.common.userCenter.domain.Member;

public interface MemberMapper {
	
	int insertSelective(Member record);
	
	Member selectByPrimaryKey(Long mid);
	
}
