package com.xinmao.sc.testCenter.domain;

import java.util.Date;
import java.util.List;

//定性成立条件 对象
public class ConditionsOfQualitativeMessage {
	
	//已删除状态
    public static Integer IS_DELETE =1;
	
	//左侧类型，1表示维度
    public static Integer LEFT_TYPE_DIMENSION =1;
	
	//左侧类型，2表示分数
	public static Integer LEFT_TYPE_SCORE =2;
	
	//右侧类型，1表示维度
	public static Integer RIGHT_TYPE_DIMENSION =1;
	
	//右侧类型，2表示分数
	public static Integer RIGHT_TYPE_SCORE =2;
	
	//左右侧的关系，1表示大于
	public static Integer LEFT_RIGHT_RELATION_GREATER = 1;
	
	//左右侧的关系，2表示大于等于
	public static Integer LEFT_RIGHT_RELATION_GREATER_EQUAL = 2;
	
	//左右侧的关系，3表示等于
	public static Integer LEFT_RIGHT_RELATION_EQUAL = 3;
	
	//左右侧的关系，4表示小于等于
	public static Integer LEFT_RIGHT_RELATION_LESS_EQUAL = 4;
	
	//左右侧的关系，5表示小于
	public static Integer LEFT_RIGHT_RELATION_LESS = 5;
	
	
    private Integer id;

    private Integer testScaleQualitativeId;
    
//    private Float leftValue;
    
//    private String leftValueStr;

    private Integer leftDimensionId;
    
//    private Float rightValue;
//    
//    private String  rightValueStr;
//    
//    private Integer rightType;
    
    private Integer rightDimensionId;
    
    private Float rightScore;
    
    private Integer leftRightRelation;
    
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

	public Integer getTestScaleQualitativeId() {
		return testScaleQualitativeId;
	}

	public void setTestScaleQualitativeId(Integer testScaleQualitativeId) {
		this.testScaleQualitativeId = testScaleQualitativeId;
	}
	
	public Integer getLeftDimensionId() {
		return leftDimensionId;
	}

	public void setLeftDimensionId(Integer leftDimensionId) {
		this.leftDimensionId = leftDimensionId;
	}

	public Integer getRightDimensionId() {
		return rightDimensionId;
	}

	public void setRightDimensionId(Integer rightDimensionId) {
		this.rightDimensionId = rightDimensionId;
	}

	public Float getRightScore() {
		return rightScore;
	}

	public void setRightScore(Float rightScore) {
		this.rightScore = rightScore;
	}

	public Integer getLeftRightRelation() {
		return leftRightRelation;
	}

	public void setLeftRightRelation(Integer leftRightRelation) {
		this.leftRightRelation = leftRightRelation;
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
