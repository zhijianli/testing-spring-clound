package com.xinmao.common.consultantCenter.domain;

import java.util.Date;

/**
 * 咨询领域详情DO
 * @time 2018.11.18
 * @author 李志坚
 */
public class ConsultingFieldDetail {
    private Long id;

    private String name;

    private Long consultingFieldId;

    private Integer isDelete;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getConsultingFieldId() {
        return consultingFieldId;
    }

    public void setConsultingFieldId(Long consultingFieldId) {
        this.consultingFieldId = consultingFieldId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}