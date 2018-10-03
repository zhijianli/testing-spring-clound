package com.xinmao.sc.orderCenter.domain;

import java.util.Date;
import java.util.List;

import com.xinmao.sc.entity.Page;
import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;

//测试量表订单题目 对象
public class TestScaleOrderTitleMessage extends Page{
	
    private Integer id;

    private Integer testScaleOrderId;
    
    private Integer testScaleTitleId;
     
    private String problemWord;
    
    private Integer optionId;
   
    private String optionWord;
    
    private Float optionScore;
    
    private String problemPicSrc;
    
    private String optionPicSrc;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer titleOrderNumber;
    
    private List<TestScaleOptionMessage> optionList;

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

	public Integer getTestScaleTitleId() {
		return testScaleTitleId;
	}

	public void setTestScaleTitleId(Integer testScaleTitleId) {
		this.testScaleTitleId = testScaleTitleId;
	}

	public String getProblemWord() {
		return problemWord;
	}

	public void setProblemWord(String problemWord) {
		this.problemWord = problemWord;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionWord() {
		return optionWord;
	}

	public void setOptionWord(String optionWord) {
		this.optionWord = optionWord;
	}

	public String getProblemPicSrc() {
		return problemPicSrc;
	}

	public void setProblemPicSrc(String problemPicSrc) {
		this.problemPicSrc = problemPicSrc;
	}

	public String getOptionPicSrc() {
		return optionPicSrc;
	}

	public void setOptionPicSrc(String optionPicSrc) {
		this.optionPicSrc = optionPicSrc;
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

	public Float getOptionScore() {
		return optionScore;
	}

	public void setOptionScore(Float optionScore) {
		this.optionScore = optionScore;
	}

	public List<TestScaleOptionMessage> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<TestScaleOptionMessage> optionList) {
		this.optionList = optionList;
	}

	public Integer getTitleOrderNumber() {
		return titleOrderNumber;
	}

	public void setTitleOrderNumber(Integer titleOrderNumber) {
		this.titleOrderNumber = titleOrderNumber;
	}

}
