package com.xinmao.sc.testCenter.domain;

import java.util.Date;
import java.util.List;

//测试量表题目选项 对象
public class TestScaleOptionMessage {
	
	//已删除状态
    public static Integer IS_DELETE =1;
	
    private Integer id;

    private Integer relateTitleId;
    
    private String optionWord;
    
    private String optionPic;
    
    private Float optionScore;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean isWarning = false;

    private List<Integer> searchList;  
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRelateTitleId() {
		return relateTitleId;
	}

	public void setRelateTitleId(Integer relateTitleId) {
		this.relateTitleId = relateTitleId;
	}

	public String getOptionWord() {
		return optionWord;
	}

	public void setOptionWord(String optionWord) {
		this.optionWord = optionWord;
	}

	public String getOptionPic() {
		return optionPic;
	}

	public void setOptionPic(String optionPic) {
		this.optionPic = optionPic;
	}

	public Float getOptionScore() {
		return optionScore;
	}

	public void setOptionScore(Float optionScore) {
		this.optionScore = optionScore;
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

	public Boolean getIsWarning() {
		return isWarning;
	}

	public void setIsWarning(Boolean isWarning) {
		this.isWarning = isWarning;
	}
	
}
