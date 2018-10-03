package com.xinmao.common.userOperationCenter.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.userOperationCenter.domain.CommonComment;
import com.xinmao.common.userOperationCenter.domain.CommonMemberPraiseRelation;
import com.xinmao.common.userOperationCenter.mapper.CommonCommentMapper;
import com.xinmao.common.userOperationCenter.mapper.CommonMemberPraiseRelationMapper;

@Service
public class CommonMemberPraiseRelationService {
	
	    @Autowired
	    private CommonMemberPraiseRelationMapper mapper;
	    
	    public Boolean addMessage(CommonMemberPraiseRelation cmpRelation) {
	    	
	    	Boolean isSuccess = false;
	    	
	        mapper.insert(cmpRelation);
	        Long praiseNumId = cmpRelation.getPraiseNumId();

			if(praiseNumId==null || praiseNumId==0){
				throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
			}
	        
	        isSuccess = true;
	        return isSuccess;
	    }
    
}

