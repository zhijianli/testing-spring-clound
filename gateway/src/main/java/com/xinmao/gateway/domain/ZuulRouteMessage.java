package com.xinmao.gateway.domain;

import java.util.Date;

/**
 * 路由类
 * @author 李志坚
 * @date 2018/2/2.
 */
public class ZuulRouteMessage {
	
	
    private Integer primaryId;
	   /**
     * The ID of the route (the same as its map key by default).
     */
    private String id;

    /**
     * The path (pattern) for the route, e.g. /foo/**.
     */
    private String path;

    /**
     * The service ID (if any) to map to this route. You can specify a physical URL or
     * a service, but not both.
     */
    private String serviceId;

    /**
     * A full physical URL to map to the route. An alternative is to use a service ID
     * and service discovery to find the physical address.
     */
    private String url;

//    /**
//     * Flag to determine whether the prefix for this route (the path, minus pattern
//     * patcher) should be stripped before forwarding.
//     */
    private Boolean stripPrefix = true;
//
//    /**
//     * Flag to indicate that this route should be retryable (if supported). Generally
//     * retry requires a service ID and ribbon.
//     */
    private Boolean retryable;
   
    
//    private boolean customSensitiveHeaders = true;
    
//    private Boolean enabled;
    
    private Integer isEnable;
    
    private Integer isDelete;
    
    private Date createTime;
    
    private Date updateTime;

	public Integer getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Integer primaryId) {
		this.primaryId = primaryId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public Boolean getStripPrefix() {
		return stripPrefix;
	}

	public void setStripPrefix(Boolean stripPrefix) {
		this.stripPrefix = stripPrefix;
	}

	public Boolean getRetryable() {
		return retryable;
	}

	public void setRetryable(Boolean retryable) {
		this.retryable = retryable;
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

//	public boolean isCustomSensitiveHeaders() {
//		return customSensitiveHeaders;
//	}
//
//	public void setCustomSensitiveHeaders(boolean customSensitiveHeaders) {
//		this.customSensitiveHeaders = customSensitiveHeaders;
//	}
    
//    public Boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
    
    
    
}
