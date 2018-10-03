package com.xinmao.common.userOperationCenter.domain;

import java.util.Date;

import com.xinmao.common.entity.Page;

public class CommonCollection extends Page{
	
	//来源在心理测试
    public static Integer SOURCE_TEST_SCALE =101;
    
	//未被收藏
    public static Integer IS_NOT_COLLECTED =0;
    
	//已被收藏
    public static Integer IS_COLLECTED =1;
	
    private Long colId;

    private Long mid;

    private Long articleId;

    private Date collectionTime;

    private Integer source;

    private Date createTime;

    private Date modifyTime;
    
    private Integer isCollected;

    private Integer isDelete;

    public Long getColId() {
        return colId;
    }

    public void setColId(Long colId) {
        this.colId = colId;
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

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public Integer getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(Integer isCollected) {
		this.isCollected = isCollected;
	}
    
}