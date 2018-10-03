package com.xinmao.common.bannerCenter.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.common.bannerCenter.mapper.BannerMapper;
import com.xinmao.common.bannerCenter.domain.BannerMessage;

@Service
public class BannerService {
    
    @Autowired
    private BannerMapper mapper;
    
    public List<BannerMessage> getBannerByShowLocation(BannerMessage bannerMessage){
    	return mapper.getBannerByShowLocation(bannerMessage);
    }
    
    public List<BannerMessage> selectMessByCondition(BannerMessage bannerMessage){
    	return mapper.selectMessByCondition(bannerMessage);
    }
    
    public BannerMessage getMessageById(int id){
    	return mapper.getMessById(id);
    }
    public int addMessage(BannerMessage bannerMessage) {
        return mapper.insertMess(bannerMessage);
    }
    
    public int updateMess(BannerMessage bannerMessage) {
        return mapper.updateMess(bannerMessage);
    }
}

