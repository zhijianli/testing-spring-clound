package com.xinmao.sc.orderCenter.domain;

import java.util.Date;


//量表订单  维度常模快照  对象
public class TestScaleOrderDimensionNormMessage {
	
	//正常状态
    public static Integer IS_NORMAL =0;
    
	//异常状态
    public static Integer IS_NOT_NORMAL =1;
	
    private Integer id;
    
    private Integer testScaleOrderId;
    
    private Integer isShowDimension;
    
    private Integer dimensionId;
    
    private String dimensionName;
    
    private String dimensionDescription;
    
    private Float dimensionScore;
    
    private Integer normId;
    
    private Float normLowScore;
    
    private Float normHighScore;
    
    private Integer isNormal;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTestScaleOrderId() {
		return testScaleOrderId;
	}

	public void setTestScaleOrderId(Integer testScaleOrderId) {
		this.testScaleOrderId = testScaleOrderId;
	}

	public Integer getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}
	
	public Float getDimensionScore() {
		return dimensionScore;
	}

	public void setDimensionScore(Float dimensionScore) {
		this.dimensionScore = dimensionScore;
	}

	public Integer getNormId() {
		return normId;
	}

	public void setNormId(Integer normId) {
		this.normId = normId;
	}

	public Float getNormLowScore() {
		return normLowScore;
	}

	public void setNormLowScore(Float normLowScore) {
		this.normLowScore = normLowScore;
	}

	public Float getNormHighScore() {
		return normHighScore;
	}

	public void setNormHighScore(Float normHighScore) {
		this.normHighScore = normHighScore;
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

	public String getDimensionDescription() {
		return dimensionDescription;
	}

	public void setDimensionDescription(String dimensionDescription) {
		this.dimensionDescription = dimensionDescription;
	}

	public Integer getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
	}

	public Integer getIsShowDimension() {
		return isShowDimension;
	}

	public void setIsShowDimension(Integer isShowDimension) {
		this.isShowDimension = isShowDimension;
	}
	
}
