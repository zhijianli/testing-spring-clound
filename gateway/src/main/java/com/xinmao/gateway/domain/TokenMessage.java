package com.xinmao.gateway.domain;

import java.util.Date;

/**
 * token相关类
 * @author 李志坚
 * @date 2018/2/2.
 */
public class TokenMessage {
	
    /**
     * token在cookie中的name
     */
    public static final String TOKEN_COOKIE_NAME = "token";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 6;
    
    /**
     * token重新创建的时间区间（毫秒）
     * 半小时
     */
    public static final int TOKEN_TIME_SECTION = 1800000;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
    
    /**
     * token字符串分隔符
     * “.”是转义字符，必须得加"\\";
     */   
    public static final String SPLIT_CHARACTER = "\\.";
    
    /**
     * token编码字符集
     */   
    public static final String CHARACTER_SET = "UTF-8";
	
	private Long userId;
	
	private String userName;
	
	private Date expiresTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(Date expiresTime) {
		this.expiresTime = expiresTime;
	}
	
}
