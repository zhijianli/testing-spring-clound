package com.xinmao.common.userOperationCenter.domain;

import java.util.Date;
import java.util.List;

import com.xinmao.common.entity.Page;

public class CommonComment extends Page {
	
	//来源在心理测试
    public static Integer SOURCE_TEST_SCALE =101;
    
	//来源在话题
    public static Integer SOURCE_TOPIC =201;
    
	//来源在解忧杂货铺信箱
    public static Integer SOURCE_TOPIC_MAILBOX =301;
    
	//默认的父评论id
    public static Long DEFAULT_PARENT_COMMENT_ID = 0l;
    
	//显示状态
    public static Byte IS_DISPLAY_STATUS = 0;
    
	//不显示状态
    public static Byte IS_NOT_DISPLAY_STATUS = -1;
	
    private Long commentId;

    private Long articleId;

    private Long mid;
    
    private String mName;
    
    private String mHeadPortrait;

    private String commentContent;
    
    private String draft;

    private Long parentCommentId;
    
    private String parentCommentName;

    private Integer source;
    
    private Date commentTime;
    
    private Date updateTime;
    
    private Date sortTime;

    private Integer isDelete;
    
    private Integer praiseNum;
    
    private Integer optimizedPraiseNum;
    
    private Integer finalPraiseNum;
    
    private Byte status;
    
    private Boolean isPraise;
    
    private List<Long> searchList;
    
    private List<CommonComment> sonCommentList;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getDraft() {
		return draft;
	}

	public void setDraft(String draft) {
		this.draft = draft;
	}

	public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public List<CommonComment> getSonCommentList() {
		return sonCommentList;
	}

	public void setSonCommentList(List<CommonComment> sonCommentList) {
		this.sonCommentList = sonCommentList;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmHeadPortrait() {
		return mHeadPortrait;
	}

	public void setmHeadPortrait(String mHeadPortrait) {
		this.mHeadPortrait = mHeadPortrait;
	}

	public String getParentCommentName() {
		return parentCommentName;
	}

	public void setParentCommentName(String parentCommentName) {
		this.parentCommentName = parentCommentName;
	}

	public List<Long> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<Long> searchList) {
		this.searchList = searchList;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
	
	public Integer getOptimizedPraiseNum() {
		return optimizedPraiseNum;
	}

	public void setOptimizedPraiseNum(Integer optimizedPraiseNum) {
		this.optimizedPraiseNum = optimizedPraiseNum;
	}

	public Integer getFinalPraiseNum() {
		return finalPraiseNum;
	}

	public void setFinalPraiseNum(Integer finalPraiseNum) {
		this.finalPraiseNum = finalPraiseNum;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(Boolean isPraise) {
		this.isPraise = isPraise;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getSortTime() {
		return sortTime;
	}

	public void setSortTime(Date sortTime) {
		this.sortTime = sortTime;
	}
}