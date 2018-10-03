package com.xinmao.sc.testCenter.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xinmao.sc.entity.Page;

//测试量表基础信息 对象
public class TestScaleInfoMessage extends Page  implements Serializable {
	
	private static final long serialVersionUID = -1L;
	
    
	//新结果页状态
	public static  Integer IS_NEW_RESULT_PAGE=1;
	
	//已删除状态
    public static Integer IS_DELETE =1;
	
	//上线状态
    public static Integer IS_ONLINE =1;
    
	//未上线状态
    public static Integer IS_NOT_ONLINE =0;
    
	//置顶状态
    public static Integer IS_TOP =1;
    
	//未置顶状态
    public static Integer IS_NOT_TOP =0;
    
	//在首页显示的热门测试，先从数据库取前15个，在此数量下随机获取10个热门测试显示在首页
    public static Integer HOME_PAGE_LIMIT =15;
    
	//在分类页面显示的热门测试数量
    public static Integer CLASS_PAGE_LIMIT =100;
    
	//在测试结果页面显示的同分类下的热门测试，取3个
    public static Integer TEST_RESULT_LIMIT =3;
    
	//在首页显示的热门测试量表数量
    public static Integer HOME_PAGE_HOT_TEST_NUM =10;
    
    //前端展现方式：原始分
    public static Integer DISPLAY_MODE_O_POINT = 0;
    
    //前端展现方式：T分
    public static Integer DISPLAY_MODE_T_POINT = 1;
    
    //前端展现方式：Z分
    public static Integer DISPLAY_MODE_Z_POINT = 2;
    
    //显示维度剖面图
    public static Integer IS_SHOW_DIMENSION_PROFILE = 0;
    
    //不显示维度剖面图
    public static Integer IS_NOT_SHOW_DIMENSION_PROFILE = 1;
    
    //量表标题字数限制
    public static Integer TESTSCALE_NAME_LIMIT = 16;

	private Integer id;

    private String name;
    
    private String abstractStr;
    
    private String description;
    
    private String relatePicSrc;
    
    private Integer isTop;
    
    private Integer isShowDimensionProfile;
    
    private String founder;
    
    private Integer recommendedTestInterval;
    
    private Integer displayMode;
    
    //数据库是什么类型的？需要规定长度吗？
    private Float price;
    
    private Integer numberOfTest;
    
    private Integer numberOfTitle;
    
    private Integer numberOfComment;
    
    private Integer isNewResultPage;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer limit;
    
    private Integer latestOrderId;
    
    private List<Integer> searchList;  
    
    private List<TestScaleClassMessage> tscList; 
    
    private List<TestScalePopulationRangeMessage> tsprList;
    
    private List<TestScaleDimensionMessage> tsdList;
    
    private List<TestScaleQualitativeMessage> tsqList;
    
    private Map<Integer,TestScaleTitleMessage> titleMap;
    
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

	public String getAbstractStr() {
		return abstractStr;
	}

	public void setAbstractStr(String abstractStr) {
		this.abstractStr = abstractStr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRelatePicSrc() {
		return relatePicSrc;
	}

	public void setRelatePicSrc(String relatePicSrc) {
		this.relatePicSrc = relatePicSrc;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public Integer getRecommendedTestInterval() {
		return recommendedTestInterval;
	}

	public void setRecommendedTestInterval(Integer recommendedTestInterval) {
		this.recommendedTestInterval = recommendedTestInterval;
	}

	public Integer getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getNumberOfTest() {
		return numberOfTest;
	}

	public void setNumberOfTest(Integer numberOfTest) {
		this.numberOfTest = numberOfTest;
	}

	public Integer getNumberOfTitle() {
		return numberOfTitle;
	}

	public void setNumberOfTitle(Integer numberOfTitle) {
		this.numberOfTitle = numberOfTitle;
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
	
    public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<Integer> getSearchList() {  
        return searchList;  
    }  
  
    public void setSearchList(List<Integer> searchList) {  
        this.searchList = searchList;  
    }

	public List<TestScaleClassMessage> getTscList() {
		return tscList;
	}

	public void setTscList(List<TestScaleClassMessage> tscList) {
		this.tscList = tscList;
	}

	public List<TestScalePopulationRangeMessage> getTsprList() {
		return tsprList;
	}

	public void setTsprList(List<TestScalePopulationRangeMessage> tsprList) {
		this.tsprList = tsprList;
	}

	public List<TestScaleDimensionMessage> getTsdList() {
		return tsdList;
	}

	public void setTsdList(List<TestScaleDimensionMessage> tsdList) {
		this.tsdList = tsdList;
	}

	public List<TestScaleQualitativeMessage> getTsqList() {
		return tsqList;
	}

	public void setTsqList(List<TestScaleQualitativeMessage> tsqList) {
		this.tsqList = tsqList;
	}

	public Map<Integer, TestScaleTitleMessage> getTitleMap() {
		return titleMap;
	}

	public void setTitleMap(Map<Integer, TestScaleTitleMessage> titleMap) {
		this.titleMap = titleMap;
	}

	public Integer getNumberOfComment() {
		return numberOfComment;
	}

	public void setNumberOfComment(Integer numberOfComment) {
		this.numberOfComment = numberOfComment;
	}

	public Integer getIsShowDimensionProfile() {
		return isShowDimensionProfile;
	}

	public void setIsShowDimensionProfile(Integer isShowDimensionProfile) {
		this.isShowDimensionProfile = isShowDimensionProfile;
	}
	
	public Integer getLatestOrderId() {
		return latestOrderId;
	}

	public void setLatestOrderId(Integer latestOrderId) {
		this.latestOrderId = latestOrderId;
	}

	public Integer getIsNewResultPage() {
		return isNewResultPage;
	}

	public void setIsNewResultPage(Integer isNewResultPage) {
		this.isNewResultPage = isNewResultPage;
	}
}
