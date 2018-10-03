package com.xinmao.sc.testCenter.domain;

import java.util.Date;
import java.util.List;

//测试量表常模 对象
public class TestScaleNormMessage {
	
	//已删除状态
    public static Integer IS_DELETE =1;
	
    private Integer id;

    private Integer relateTestScaleId;
    
    private Integer dimensionId;
    
    private Integer populationRangeId;
    
    private Float lowScore;
    
    private Float highScore;
    
    private Float averageValue;
    
    private Float standardDeviation;
    
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

	public Integer getRelateTestScaleId() {
		return relateTestScaleId;
	}

	public void setRelateTestScaleId(Integer relateTestScaleId) {
		this.relateTestScaleId = relateTestScaleId;
	}

	public Integer getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
	}

	public Integer getPopulationRangeId() {
		return populationRangeId;
	}

	public void setPopulationRangeId(Integer populationRangeId) {
		this.populationRangeId = populationRangeId;
	}

	public Float getLowScore() {
		return lowScore;
	}

	public void setLowScore(Float lowScore) {
		this.lowScore = lowScore;
	}

	public Float getHighScore() {
		return highScore;
	}

	public void setHighScore(Float highScore) {
		this.highScore = highScore;
	}

	public Float getAverageValue() {
		return averageValue;
	}

	public void setAverageValue(Float averageValue) {
		this.averageValue = averageValue;
	}

	public Float getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(Float standardDeviation) {
		this.standardDeviation = standardDeviation;
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
