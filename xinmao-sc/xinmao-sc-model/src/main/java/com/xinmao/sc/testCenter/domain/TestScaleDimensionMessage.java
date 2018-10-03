package com.xinmao.sc.testCenter.domain;

import java.util.Date;
import java.util.List;

//测试量表维度 对象
public class TestScaleDimensionMessage {
	
	//已删除状态
    public static Integer IS_DELETE =1;
    
	//显示此维度
    public static Integer IS_SHOW_DIMENSION =0;
    
	//不显示此维度
    public static Integer IS_NOT_SHOW_DIMENSION =1;
	
    private Integer id;

    private String name;
    
    private String description;
    
    private Integer relateTestScaleId;
    
    private Integer isShowDimension;
   
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private List<Integer> searchList;
    
    private Boolean isBelongTo;
    
    private String titleNumberStr;
    
    private Integer displayMode;
    
    private Float dimensionFullScore;
    
    private TestScaleNormMessage tsnMessage;
    
    private List<TestScalePopulationRangeMessage> tsprList;
    
    private List<Integer> tstIdList;
	
	public static Float getTscore(Float oriScore,Float mean,Float sd) {
	    Float tScore = 0f;
	    try {
	    	tScore = 50 + ((oriScore-mean)/sd)*10;
	    	if(tScore>90f){
	    		tScore=90f;
	    	}else if(tScore<10f){
	    		tScore =10f;
	    	}
	        return tScore;
	    } catch (Exception e){
	       return 0f;
	    }
    }
	
	public static Float getZscore(Float oriScore,Float mean,Float sd) {
	    Float zScore = 0f;
	    try {
	    	zScore = (oriScore-mean)/sd;
	    	if(zScore>4f){
	    		zScore=4f;
	    	}else if(zScore<-4f){
	    		zScore =-4f;
	    	}
	        return zScore;
	    } catch (Exception e){
	       return 0f;
	    }
    }
	
	public static Float getRoughscore(Float oriScore,Float dimensionFullScore) {
	    Float roughScore = 0f;
	    try {
	    	roughScore = oriScore/dimensionFullScore*10;

	        return roughScore;
	    } catch (Exception e){
	       return 0f;
	    }
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRelateTestScaleId() {
		return relateTestScaleId;
	}

	public void setRelateTestScaleId(Integer relateTestScaleId) {
		this.relateTestScaleId = relateTestScaleId;
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

	public Boolean getIsBelongTo() {
		return isBelongTo;
	}

	public void setIsBelongTo(Boolean isBelongTo) {
		this.isBelongTo = isBelongTo;
	}

	public String getTitleNumberStr() {
		return titleNumberStr;
	}

	public void setTitleNumberStr(String titleNumberStr) {
		this.titleNumberStr = titleNumberStr;
	}

	public List<TestScalePopulationRangeMessage> getTsprList() {
		return tsprList;
	}

	public void setTsprList(List<TestScalePopulationRangeMessage> tsprList) {
		this.tsprList = tsprList;
	}

	public Integer getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
	}

	public TestScaleNormMessage getTsnMessage() {
		return tsnMessage;
	}

	public void setTsnMessage(TestScaleNormMessage tsnMessage) {
		this.tsnMessage = tsnMessage;
	}

	public List<Integer> getTstIdList() {
		return tstIdList;
	}

	public void setTstIdList(List<Integer> tstIdList) {
		this.tstIdList = tstIdList;
	}

	public Float getDimensionFullScore() {
		return dimensionFullScore;
	}

	public void setDimensionFullScore(Float dimensionFullScore) {
		this.dimensionFullScore = dimensionFullScore;
	}

	public Integer getIsShowDimension() {
		return isShowDimension;
	}

	public void setIsShowDimension(Integer isShowDimension) {
		this.isShowDimension = isShowDimension;
	}
}
