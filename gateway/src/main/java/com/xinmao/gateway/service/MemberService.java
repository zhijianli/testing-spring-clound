package com.xinmao.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.common.userCenter.domain.Member;
import com.xinmao.gateway.mapper.MemberMapper;

@Service
public class MemberService {
	
    @Autowired
    private MemberMapper mapper;
    
    public Member getMember(Member member){
    	return mapper.getMember(member);
    }

}
