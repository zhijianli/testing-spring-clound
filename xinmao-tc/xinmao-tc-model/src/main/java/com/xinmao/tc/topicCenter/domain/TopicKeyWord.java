package com.xinmao.tc.topicCenter.domain;

import java.util.Date;
import java.util.List;

import com.xinmao.tc.entity.Page;
/**
 * 话题关键词对象
 * @param 李志坚
 * @param 2018.6.4
 */
public class TopicKeyWord extends Page{
    private Long id;

    private String keyWord;

    private Byte isEnable;

    private Byte isDelete;
    
    private Integer degreeOfHeat;
    
    private Integer relatedTopicNum;

    private Date createTime;

    private Date updateTime;
    
    private List<Long> searchIdList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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

	public Integer getDegreeOfHeat() {
		return degreeOfHeat;
	}

	public void setDegreeOfHeat(Integer degreeOfHeat) {
		this.degreeOfHeat = degreeOfHeat;
	}

	public Integer getRelatedTopicNum() {
		return relatedTopicNum;
	}

	public void setRelatedTopicNum(Integer relatedTopicNum) {
		this.relatedTopicNum = relatedTopicNum;
	}
}