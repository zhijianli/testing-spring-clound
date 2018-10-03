package com.xinmao.common.userOperationCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.userOperationCenter.domain.CommonCollection;
import com.xinmao.common.userOperationCenter.mapper.CommonCollectionMapper;

@Service
public class CommonCollectionService {
    
    
    @Autowired
    private CommonCollectionMapper mapper;
    
    public Boolean addMessage(CommonCollection commonCollection) {
    	
    	Boolean isSuccess = false;
    	
        mapper.insert(commonCollection);
        Long id = commonCollection.getColId();

		if(id==null || id==0){
			throw new RuntimeException(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
		}
        
        isSuccess = true;
        return isSuccess;
    }
    
    public Integer cancelCollection(CommonCollection commonCollection) {
    	
    	
        return mapper.cancelCollection(commonCollection);

    }
    
    public List<CommonCollection> getCollectionListByUserId(CommonCollection commonCollection) {
    	
        return mapper.getCollectionListByUserId(commonCollection);

    }
    
}

