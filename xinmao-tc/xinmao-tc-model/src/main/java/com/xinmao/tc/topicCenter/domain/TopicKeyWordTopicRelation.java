package com.xinmao.tc.topicCenter.domain;

import java.util.Date;
import java.util.List;
/**
 * 话题关键词与话题关联对象
 * @param 李志坚
 * @param 2018.6.4
 */
public class TopicKeyWordTopicRelation {
    private Long id;

    private Long keyWordId;

    private Long topicId;

    private Byte isEnable;

    private Byte isDelete;

    private Date createTime;

    private Date updateTime;
    
    private List<Long> searchIdList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKeyWordId() {
		return keyWordId;
	}

	public void setKeyWordId(Long keyWordId) {
		this.keyWordId = keyWordId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

    public Byte getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public List<Long> getSearchIdList() {
		return searchIdList;
	}

	public void setSearchIdList(List<Long> searchIdList) {
		this.searchIdList = searchIdList;
	}
}