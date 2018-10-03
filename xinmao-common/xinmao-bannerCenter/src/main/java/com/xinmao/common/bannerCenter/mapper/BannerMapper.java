package com.xinmao.common.bannerCenter.mapper;

import java.util.List;

import com.xinmao.common.bannerCenter.domain.BannerMessage;

public interface BannerMapper {
	
	
    List<BannerMessage> getBannerByShowLocation(BannerMessage bannerMessage);
	
    List<BannerMessage> selectMessByCondition(BannerMessage bannerMessage);

    BannerMessage getMessById(int id);
    
    int insertMess(BannerMessage bannerMessage);
    
    int updateMess(BannerMessage bannerMessage);
    
    int getTestScaleOrderNum();
    
}
