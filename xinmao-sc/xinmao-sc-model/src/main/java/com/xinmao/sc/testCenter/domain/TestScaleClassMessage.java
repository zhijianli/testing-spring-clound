package com.xinmao.sc.testCenter.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//测试量表分类 对象
public class TestScaleClassMessage implements Serializable {
	
	private static final long serialVersionUID = -1L;
	
	//已删除状态
    public static Integer IS_DELETE =1;
    
    //首页推荐状态
    public static Integer IS_HOME_RECOMM =1;
	
    private Integer id;

    private String name;
    
    private String relatePicSrc;
    
    private Integer homeRecomm;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer testScaleNum;
    
    private Integer numberOfTest;
    
    private Boolean isBelongTo;
    
    private List<Integer> searchList;
    
    private List<TestScaleInfoMessage> tsiList;

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

	public String getRelatePicSrc() {
		return relatePicSrc;
	}

	public void setRelatePicSrc(String relatePicSrc) {
		this.relatePicSrc = relatePicSrc;
	}

	public Integer getHomeRecomm() {
		return homeRecomm;
	}

	public void setHomeRecomm(Integer homeRecomm) {
		this.homeRecomm = homeRecomm;
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

	public Integer getTestScaleNum() {
		return testScaleNum;
	}

	public void setTestScaleNum(Integer testScaleNum) {
		this.testScaleNum = testScaleNum;
	}

	public Integer getNumberOfTest() {
		return numberOfTest;
	}

	public void setNumberOfTest(Integer numberOfTest) {
		this.numberOfTest = numberOfTest;
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

	public List<TestScaleInfoMessage> getTsiList() {
		return tsiList;
	}

	public void setTsiList(List<TestScaleInfoMessage> tsiList) {
		this.tsiList = tsiList;
	}
	
}
