package com.xinmao.common.userOperationCenter.domain;

import java.util.Date;

public class CommonMemberPraiseRelation {
	
	//点赞位置在评论
    public static Integer LIKE_POSITION_COMMENT =101;
	
    private Long praiseNumId;

    private Long mid;

    private Long relationId;
    
    private Integer likePosition;

    private Date createTime;

    private Date modifyTime;

    private Integer isDelete;

    public Long getPraiseNumId() {
        return praiseNumId;
    }

    public void setPraiseNumId(Long praiseNumId) {
        this.praiseNumId = praiseNumId;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public Integer getLikePosition() {
		return likePosition;
	}

	public void setLikePosition(Integer likePosition) {
		this.likePosition = likePosition;
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
}