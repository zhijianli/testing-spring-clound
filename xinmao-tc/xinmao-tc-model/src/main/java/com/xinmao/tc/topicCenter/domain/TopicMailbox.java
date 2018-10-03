package com.xinmao.tc.topicCenter.domain;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.xinmao.tc.entity.Page;
/**
 * 话题信箱对象
 * @param 李志坚
 * @param 2018.6.4
 */
public class TopicMailbox extends Page{
	
	
	//截图分隔符
	public static String SCREENHOT_DELIMITER = "||";
		
	//截图分隔符--转义
	public static String SCREENHOT_DELIMITER_TRANSFERENCE = "\\|\\|";
	
	//未标记
    public static Byte is_NOT_MARK = 0;
	
    //已标记
    public static Byte is_MARK = 1;
    
	//未回复
    public static Byte is_NOT_Reply = 0;
	
    //已回复
    public static Byte is_Reply = 1;
    
    private Long id;

    private Long mid;

    private String autoNick;

    private String content;
    
    private String commentContent;

    private String screenshot;
    
    private String screenshotOne;
    
    private String screenshotTwo;
    
    private String screenshotThree;

    private String screenshotFour;

    private Byte isMark;

    private Byte status;

    private Byte isEnable;

    private Byte isDelete;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot == null ? null : screenshot.trim();
    }

	public String getAutoNick() {
		return autoNick;
	}

	public void setAutoNick(String autoNick) {
		this.autoNick = autoNick;
	}

	public Byte getIsMark() {
		return isMark;
	}

	public void setIsMark(Byte isMark) {
		this.isMark = isMark;
	}

	public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

	public String getScreenshotOne() {
		return screenshotOne;
	}

	public void setScreenshotOne(String screenshotOne) {
		this.screenshotOne = screenshotOne;
	}

	public String getScreenshotTwo() {
		return screenshotTwo;
	}

	public void setScreenshotTwo(String screenshotTwo) {
		this.screenshotTwo = screenshotTwo;
	}

	public String getScreenshotThree() {
		return screenshotThree;
	}

	public void setScreenshotThree(String screenshotThree) {
		this.screenshotThree = screenshotThree;
	}

	public String getScreenshotFour() {
		return screenshotFour;
	}

	public void setScreenshotFour(String screenshotFour) {
		this.screenshotFour = screenshotFour;
	}
	
	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	//组装截图
    public void assembleScreenshot(){
		String screenshotOne = this.getScreenshotOne();
		String screenshotTwo = this.getScreenshotTwo();
		String screenshotThree = this.getScreenshotThree();
		String screenshotFour = this.getScreenshotFour();
		String screenshot = "";
		if(screenshotOne!=null && !StringUtils.isEmpty(screenshotOne)){
			screenshot = screenshotOne+this.SCREENHOT_DELIMITER;
		}
		if(screenshotTwo!=null && !StringUtils.isEmpty(screenshotTwo)){
			screenshot = screenshot+screenshotTwo+this.SCREENHOT_DELIMITER;
		}
		if(screenshotThree!=null && !StringUtils.isEmpty(screenshotThree)){
			screenshot = screenshot+screenshotThree+this.SCREENHOT_DELIMITER;
		}
		if(screenshotFour!=null && !StringUtils.isEmpty(screenshotFour)){
			screenshot = screenshot+screenshotFour;
		}
		this.setScreenshot(screenshot);
	}
    
    //分割截图
    public void splitScreenshotStr(){
    	String screenshot = this.getScreenshot();
    	if(screenshot!=null && !StringUtils.isEmpty(screenshot)){
    		String[] screenshotArry = screenshot.split(this.SCREENHOT_DELIMITER_TRANSFERENCE);
        	for(int i=0;i<screenshotArry.length;i++){
        		if(i==0){
        			this.setScreenshotOne(screenshotArry[i]);
        		}else if(i==1){
        			this.setScreenshotTwo(screenshotArry[i]);
        		}else if(i==2){
        			this.setScreenshotThree(screenshotArry[i]);
        		}else if(i==3){
        			this.setScreenshotFour(screenshotArry[i]);
        		}
        	}
    	}
	}
}