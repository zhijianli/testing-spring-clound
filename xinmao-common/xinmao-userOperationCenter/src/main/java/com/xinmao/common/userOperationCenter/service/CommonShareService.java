package com.xinmao.common.userOperationCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.userOperationCenter.domain.CommonShare;
import com.xinmao.common.userOperationCenter.mapper.CommonShareMapper;


@Service
public class CommonShareService {
    
    
    @Autowired
    private CommonShareMapper mapper;
    
    public Boolean addMessage(CommonShare commonShare) {
    	
    	Boolean isSuccess = false;
    	
        mapper.insert(commonShare);
        Long shareId = commonShare.getShareId();

		if(shareId==null || shareId==0){
			throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
		}
        
        isSuccess = true;
        return isSuccess;
    }
}

