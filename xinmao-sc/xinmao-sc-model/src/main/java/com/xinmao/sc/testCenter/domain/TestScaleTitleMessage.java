package com.xinmao.sc.testCenter.domain;

import java.util.Date;
import java.util.List;

import com.xinmao.sc.entity.Page;

//测试量表题目 对象
public class TestScaleTitleMessage extends Page{
	
	//已删除状态
    public static Integer IS_DELETE =1;
	
    private Integer id;

    private Integer relateTestScaleId;
    
    private String problemWord;
    
    private String problemPicSrc;
    
    private Integer optionType;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer titleOrderNumber;
    
    private TestScaleOptionMessage tsoMessage;
    
    private List<TestScaleDimensionMessage> tsdList;
    
    private List<TestScaleOptionMessage> tsoList;
    
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

	public String getProblemWord() {
		return problemWord;
	}

	public void setProblemWord(String problemWord) {
		this.problemWord = problemWord;
	}

	public String getProblemPicSrc() {
		return problemPicSrc;
	}

	public void setProblemPicSrc(String problemPicSrc) {
		this.problemPicSrc = problemPicSrc;
	}

	public Integer getOptionType() {
		return optionType;
	}

	public void setOptionType(Integer optionType) {
		this.optionType = optionType;
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

	public Integer getTitleOrderNumber() {
		return titleOrderNumber;
	}

	public void setTitleOrderNumber(Integer titleOrderNumber) {
		this.titleOrderNumber = titleOrderNumber;
	}

	public List<TestScaleDimensionMessage> getTsdList() {
		return tsdList;
	}

	public void setTsdList(List<TestScaleDimensionMessage> tsdList) {
		this.tsdList = tsdList;
	}

	public List<TestScaleOptionMessage> getTsoList() {
		return tsoList;
	}

	public void setTsoList(List<TestScaleOptionMessage> tsoList) {
		this.tsoList = tsoList;
	}

	public TestScaleOptionMessage getTsoMessage() {
		return tsoMessage;
	}

	public void setTsoMessage(TestScaleOptionMessage tsoMessage) {
		this.tsoMessage = tsoMessage;
	}

	public List<Integer> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<Integer> searchList) {
		this.searchList = searchList;
	}
}
