package com.xinmao.tc.topicCenter.domain;

import java.util.Date;
import java.util.List;

import com.xinmao.tc.entity.Page;

/**
 * 话题对象
 * @param 李志坚
 * @param 2018.6.2
 */
public class Topic extends Page {
	
	//未显示状态
	public static Byte is_NOT_DISPLAY_STATUS = 0;
	
	//显示状态
	public static Byte is_DISPLAY_STATUS = 1;
	
	//未置顶
	public static Byte IS_NOT_TOP = 0;
	
	//置顶
	public static Byte IS_TOP = 1;
	
	//关键词分割符--“||”
	public static String KEY_WORD_SPLIT_CHAR = "\\|\\|";
	
    private Long id;

    private String title;

    private String pictureUrl;

    private String content;

    private Integer readingVolume;
    
    private Byte status;

    private Byte isTop;

    private Date setTopTime;

    private Byte isEnable;

    private Byte isDelete;

    private Date createTime;

    private Date updateTime;
    
    private Date sortTime;
    
    private String keyWordStr; 
    
    private Integer commentNum; 
    
    private List<String> keyWordList;
    
    private List<Long> keyWordIdList;
    
    private List<Long> searchIdList;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReadingVolume() {
		return readingVolume;
	}

	public void setReadingVolume(Integer readingVolume) {
		this.readingVolume = readingVolume;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getIsTop() {
		return isTop;
	}

	public void setIsTop(Byte isTop) {
		this.isTop = isTop;
	}

	public Date getSetTopTime() {
		return setTopTime;
	}

	public void setSetTopTime(Date setTopTime) {
		this.setTopTime = setTopTime;
	}

	public Byte getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
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

	public String getKeyWordStr() {
		return keyWordStr;
	}

	public void setKeyWordStr(String keyWordStr) {
		this.keyWordStr = keyWordStr;
	}

	public List<Long> getSearchIdList() {
		return searchIdList;
	}

	public void setSearchIdList(List<Long> searchIdList) {
		this.searchIdList = searchIdList;
	}

	public List<String> getKeyWordList() {
		return keyWordList;
	}

	public void setKeyWordList(List<String> keyWordList) {
		this.keyWordList = keyWordList;
	}

	public Date getSortTime() {
		return sortTime;
	}

	public void setSortTime(Date sortTime) {
		this.sortTime = sortTime;
	}

	public List<Long> getKeyWordIdList() {
		return keyWordIdList;
	}

	public void setKeyWordIdList(List<Long> keyWordIdList) {
		this.keyWordIdList = keyWordIdList;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
}