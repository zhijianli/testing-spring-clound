package com.xinmao.sc.testCenter.domain;

import java.util.Date;
import java.util.List;

import com.xinmao.sc.orderCenter.domain.TestscaleQualitativeMemberWechatRelation;

//测试量表定性 对象
public class TestScaleQualitativeMessage {
	
	//已删除状态
    public static Integer IS_DELETE =1;
    
	//普通定性类型
    public static Integer QUALITATIVE_TYPE_ORDINARY =0;
    
	//前提定性类型
    public static Integer QUALITATIVE_TYPE_PREMISE =1;
    
	//其他定性类型
    public static Integer QUALITATIVE_TYPE_OTHER =2;
    
    
    private Integer id;

    private String name;
    
    private Integer relateTestScaleId;
    
    private String qualitativeImage;
    
    private String briefIntroduction;
    
    private String concept;
    
    private String advantage;
    
    private String inferiority;
    
    private String proposal;
    
    private Integer qualitativeType;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private List<Integer> searchIdList;
    
    private List<String> coqStrList;
    
    private List<ConditionsOfQualitativeMessage> coqList;
    
    private List<TestScaleDimensionMessage> allTsdList;
    
    private List<TestscaleQualitativeMemberWechatRelation> tqmwRelList;

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

	public Integer getRelateTestScaleId() {
		return relateTestScaleId;
	}

	public void setRelateTestScaleId(Integer relateTestScaleId) {
		this.relateTestScaleId = relateTestScaleId;
	}

	public String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getInferiority() {
		return inferiority;
	}

	public void setInferiority(String inferiority) {
		this.inferiority = inferiority;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public Integer getQualitativeType() {
		return qualitativeType;
	}

	public void setQualitativeType(Integer qualitativeType) {
		this.qualitativeType = qualitativeType;
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

	public List<String> getCoqStrList() {
		return coqStrList;
	}

	public void setCoqStrList(List<String> coqStrList) {
		this.coqStrList = coqStrList;
	}

	public List<ConditionsOfQualitativeMessage> getCoqList() {
		return coqList;
	}

	public void setCoqList(List<ConditionsOfQualitativeMessage> coqList) {
		this.coqList = coqList;
	}

	public List<TestScaleDimensionMessage> getAllTsdList() {
		return allTsdList;
	}

	public void setAllTsdList(List<TestScaleDimensionMessage> allTsdList) {
		this.allTsdList = allTsdList;
	}

	public String getQualitativeImage() {
		return qualitativeImage;
	}

	public void setQualitativeImage(String qualitativeImage) {
		this.qualitativeImage = qualitativeImage;
	}

	public List<Integer> getSearchIdList() {
		return searchIdList;
	}

	public void setSearchIdList(List<Integer> searchIdList) {
		this.searchIdList = searchIdList;
	}

	public List<TestscaleQualitativeMemberWechatRelation> getTqmwRelList() {
		return tqmwRelList;
	}

	public void setTqmwRelList(List<TestscaleQualitativeMemberWechatRelation> tqmwRelList) {
		this.tqmwRelList = tqmwRelList;
	}
}
