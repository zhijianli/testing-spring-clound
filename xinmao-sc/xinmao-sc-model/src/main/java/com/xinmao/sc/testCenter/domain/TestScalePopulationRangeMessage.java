package com.xinmao.sc.testCenter.domain;

import java.util.Date;
import java.util.List;

//测试量表人群范围 对象
public class TestScalePopulationRangeMessage {
	
	//已删除状态
    public static Integer IS_DELETE =1;
	
    private Integer id;

    private Integer relateTestScaleId;
    
    private String sex;
    
    private Integer lowerAgeLimit;
    
    private Integer upperAgeLimit;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private TestScaleNormMessage normMessage;
    
    private List<Integer> searchList;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRelateTestScaleId() {
		return relateTestScaleId;
	}

	public void setRelateTestScaleId(Integer relateTestScaleId) {
		this.relateTestScaleId = relateTestScaleId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getLowerAgeLimit() {
		return lowerAgeLimit;
	}

	public void setLowerAgeLimit(Integer lowerAgeLimit) {
		this.lowerAgeLimit = lowerAgeLimit;
	}

	public Integer getUpperAgeLimit() {
		return upperAgeLimit;
	}

	public void setUpperAgeLimit(Integer upperAgeLimit) {
		this.upperAgeLimit = upperAgeLimit;
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

	public TestScaleNormMessage getNormMessage() {
		return normMessage;
	}

	public void setNormMessage(TestScaleNormMessage normMessage) {
		this.normMessage = normMessage;
	}

	public List<Integer> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<Integer> searchList) {
		this.searchList = searchList;
	}

}
