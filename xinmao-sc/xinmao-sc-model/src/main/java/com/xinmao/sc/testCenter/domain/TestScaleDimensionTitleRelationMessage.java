package com.xinmao.sc.testCenter.domain;

import java.util.Date;
import java.util.List;

//测试量表维度与题目关系 对象
public class TestScaleDimensionTitleRelationMessage {
	
	//已删除状态
    public static Integer IS_DELETE =1;
	
    private Integer id;

    private Integer testScaleDimensionId;
    
    private Integer testScaleTitleId;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private List<Integer> searchList;  

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTestScaleDimensionId() {
		return testScaleDimensionId;
	}

	public void setTestScaleDimensionId(Integer testScaleDimensionId) {
		this.testScaleDimensionId = testScaleDimensionId;
	}

	public Integer getTestScaleTitleId() {
		return testScaleTitleId;
	}

	public void setTestScaleTitleId(Integer testScaleTitleId) {
		this.testScaleTitleId = testScaleTitleId;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
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

	public List<Integer> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<Integer> searchList) {
		this.searchList = searchList;
	}
	
}
