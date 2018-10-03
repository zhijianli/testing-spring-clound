package com.xinmao.sc.orderCenter.domain;

import java.util.Date;
import java.util.List;


/**
 * 测试量表-定性-微信用户关联信息
 * @param 李志坚
 * @param 2018.6.1
 */
public class TestscaleQualitativeMemberWechatRelation {
	
	//定性数量上限
    public static Integer QUALITATIVE_NUM = 10;
    
	//用户数量上限
    public static Integer MEMBER_NUM = 4;
    
	//需要展示
    public static Integer IS_SHOW = 1;
	
    private Long id;

    private Long relateTestScaleId;

    private Long qualitativeId;

    private Long mid;

    private String mwHeadimgurl;

    private Byte isEnable;

    private Byte isDelete;

    private Date createTime;

    private Date updateTime;
    
    private Boolean isSelf;
    
    private List<Long> searchIdList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getRelateTestScaleId() {
		return relateTestScaleId;
	}

	public void setRelateTestScaleId(Long relateTestScaleId) {
		this.relateTestScaleId = relateTestScaleId;
	}

	public Long getQualitativeId() {
		return qualitativeId;
	}

	public void setQualitativeId(Long qualitativeId) {
		this.qualitativeId = qualitativeId;
	}

	public String getMwHeadimgurl() {
		return mwHeadimgurl;
	}

	public void setMwHeadimgurl(String mwHeadimgurl) {
		this.mwHeadimgurl = mwHeadimgurl;
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

	public Boolean getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(Boolean isSelf) {
		this.isSelf = isSelf;
	}
	
	
}