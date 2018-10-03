package com.xinmao.common.userOperationCenter.mapper;


import java.util.List;

import com.xinmao.common.userOperationCenter.domain.CommonComment;

public interface CommonCommentMapper {


    int insert(CommonComment commonComment);
    
    List<CommonComment>  getAllMessage(CommonComment commonComment);
    
    CommonComment getSingleMessage(CommonComment commonComment);
    
    int  updateMessage(CommonComment commonComment);
    
//    Integer getTestScaleCommentNum(CommonComment commonComment);
    
    Integer getCommentNum(CommonComment commonComment);
    
    List<CommonComment>  getMessageBySearchList(CommonComment commonComment);
    
    List<CommonComment>  getCommonCommentListByMidAndArticleId(CommonComment commonComment);  
    
    List<Long>  getTopicIdListByMid(CommonComment commonComment);
}