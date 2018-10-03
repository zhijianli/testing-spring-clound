package com.xinmao.sc.orderCenter.domain;

import java.util.Date;
import java.util.List;

/**
 * 测试量表-分享用户关系信息
 * @param 李志坚
 * @param 2018.6.1
 */
public class TestscaleShareFriendsRelation {
	
	//用户传播层级--4
	public static  Integer USER_PROPAGATION_LEVEL_FOUR = 4;
	
	//用户传播层级--2
	public static  Integer USER_PROPAGATION_LEVEL_TWO = 2;
	
    private Long id;
    
    private Long testScaleId;

    private Long sharingPersonId;

    private Long testerId;

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
    
    public Long getTestScaleId() {
		return testScaleId;
	}

	public void setTestScaleId(Long testScaleId) {
		this.testScaleId = testScaleId;
	}

	public Long getSharingPersonId() {
		return sharingPersonId;
	}

	public void setSharingPersonId(Long sharingPersonId) {
		this.sharingPersonId = sharingPersonId;
	}

	public Long getTesterId() {
		return testerId;
	}

	public void setTesterId(Long testerId) {
		this.testerId = testerId;
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