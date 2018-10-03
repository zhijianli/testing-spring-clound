package com.xinmao.sc.orderCenter.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xinmao.sc.entity.Page;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleQualitativeMessage;

//测试量表订单 对象
public class TestScaleOrderMessage extends Page {
	
	//正常状态
    public static Integer IS_NORMAL =0;
    
	//异常状态
    public static Integer IS_NOT_NORMAL =1;
	
    private Integer id;

    private Integer userId;
    
    private String userName;
    
    private String sex;
    
    private Date birthday;
    
    private Long telephone;
    
    private Integer relateTestScaleId;
    
    private String relateTestScaleName;
    
    private Integer relateQualitativeId;
    
    private String relateQualitativeName;
    
    private Integer isShowDimensionProfile;
    
    private Float relateTestScalePrice;
    
    private Integer relateDisplayMode;
    
    private Integer isNormal;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private String startTime;
    
    private String endTime;
    
    private TestScaleInfoMessage tsiMessage;
    
    private TestScaleQualitativeMessage tsqMessage;
    
    private String tsotListStr;
    
    private Integer testScaleTitleNum;
    
    private Map<Integer,Float> dimensionScoreMap;
    
    private List<TestScaleOrderDimensionNormMessage> tsodnList;
    
    private List<TestScaleOrderTitleMessage> tsotList;

    private List<TestScaleQualitativeMessage> tsqList;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getTelephone() {
		return telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	public Integer getRelateTestScaleId() {
		return relateTestScaleId;
	}

	public void setRelateTestScaleId(Integer relateTestScaleId) {
		this.relateTestScaleId = relateTestScaleId;
	}

	public String getRelateTestScaleName() {
		return relateTestScaleName;
	}

	public void setRelateTestScaleName(String relateTestScaleName) {
		this.relateTestScaleName = relateTestScaleName;
	}

	public Integer getRelateQualitativeId() {
		return relateQualitativeId;
	}

	public void setRelateQualitativeId(Integer relateQualitativeId) {
		this.relateQualitativeId = relateQualitativeId;
	}

	public String getRelateQualitativeName() {
		return relateQualitativeName;
	}

	public void setRelateQualitativeName(String relateQualitativeName) {
		this.relateQualitativeName = relateQualitativeName;
	}

	public Float getRelateTestScalePrice() {
		return relateTestScalePrice;
	}

	public void setRelateTestScalePrice(Float relateTestScalePrice) {
		this.relateTestScalePrice = relateTestScalePrice;
	}

	public Integer getRelateDisplayMode() {
		return relateDisplayMode;
	}

	public void setRelateDisplayMode(Integer relateDisplayMode) {
		this.relateDisplayMode = relateDisplayMode;
	}

	public Integer getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
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

	public List<TestScaleOrderDimensionNormMessage> getTsodnList() {
		return tsodnList;
	}

	public void setTsodnList(List<TestScaleOrderDimensionNormMessage> tsodnList) {
		this.tsodnList = tsodnList;
	}

	public TestScaleInfoMessage getTsiMessage() {
		return tsiMessage;
	}

	public void setTsiMessage(TestScaleInfoMessage tsiMessage) {
		this.tsiMessage = tsiMessage;
	}

	public TestScaleQualitativeMessage getTsqMessage() {
		return tsqMessage;
	}

	public void setTsqMessage(TestScaleQualitativeMessage tsqMessage) {
		this.tsqMessage = tsqMessage;
	}

	public List<TestScaleOrderTitleMessage> getTsotList() {
		return tsotList;
	}

	public void setTsotList(List<TestScaleOrderTitleMessage> tsotList) {
		this.tsotList = tsotList;
	}

	public Map<Integer, Float> getDimensionScoreMap() {
		return dimensionScoreMap;
	}

	public void setDimensionScoreMap(Map<Integer, Float> dimensionScoreMap) {
		this.dimensionScoreMap = dimensionScoreMap;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTsotListStr() {
		return tsotListStr;
	}

	public void setTsotListStr(String tsotListStr) {
		this.tsotListStr = tsotListStr;
	}

	public Integer getTestScaleTitleNum() {
		return testScaleTitleNum;
	}

	public void setTestScaleTitleNum(Integer testScaleTitleNum) {
		this.testScaleTitleNum = testScaleTitleNum;
	}

	public Integer getIsShowDimensionProfile() {
		return isShowDimensionProfile;
	}

	public void setIsShowDimensionProfile(Integer isShowDimensionProfile) {
		this.isShowDimensionProfile = isShowDimensionProfile;
	}

	public List<TestScaleQualitativeMessage> getTsqList() {
		return tsqList;
	}

	public void setTsqList(List<TestScaleQualitativeMessage> tsqList) {
		this.tsqList = tsqList;
	}

}
