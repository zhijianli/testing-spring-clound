package com.xinmao.common.bannerCenter.domain;

import java.util.Date;

//Banner 对象
public class BannerMessage{
	
	//已删除状态
    public static Integer IS_DELETE =1;
	
	//展示位置：app心理测试首页
    public static Integer APP_TEST_HOME_PAGE =11;
	
	private Integer bannerId;
	
	private Integer showLocation;
	
	private String bannerTitle;
	
	private String picPath;
	
	private Integer sorting;
	
	private Integer linkType;
	
	private String outsideLink;
	
	private Integer insideLinkType;

	private Integer insideLinkId;
	
	private Integer releaseFrom;
	
	private Integer isEnable;
	    
	private Integer isDelete;
	    
	private Date createTime;
	    
	private Date modifyTime;

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public Integer getShowLocation() {
		return showLocation;
	}

	public void setShowLocation(Integer showLocation) {
		this.showLocation = showLocation;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public String getOutsideLink() {
		return outsideLink;
	}

	public void setOutsideLink(String outsideLink) {
		this.outsideLink = outsideLink;
	}

	public Integer getInsideLinkType() {
		return insideLinkType;
	}

	public void setInsideLinkType(Integer insideLinkType) {
		this.insideLinkType = insideLinkType;
	}

	public Integer getInsideLinkId() {
		return insideLinkId;
	}

	public void setInsideLinkId(Integer insideLinkId) {
		this.insideLinkId = insideLinkId;
	}

	public Integer getReleaseFrom() {
		return releaseFrom;
	}

	public void setReleaseFrom(Integer releaseFrom) {
		this.releaseFrom = releaseFrom;
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
