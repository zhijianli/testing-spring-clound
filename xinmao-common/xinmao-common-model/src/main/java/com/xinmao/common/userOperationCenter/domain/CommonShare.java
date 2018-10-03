package com.xinmao.common.userOperationCenter.domain;

import java.util.Date;

public class CommonShare {
	
	//来源在心理测试
    public static Integer SOURCE_TEST_SCALE =101;
    
    private Long shareId;

    private Long mid;

    private Long articleId;

    private Byte plateformType;

    private Integer source;

    private Date shareTime;

    private Integer isDelete;

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Byte getPlateformType() {
        return plateformType;
    }

    public void setPlateformType(Byte plateformType) {
        this.plateformType = plateformType;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}