package com.xinmao.common.consultantCenter.domain;

import java.util.Date;

/**
 * 咨询师与咨询领域详情关系DO
 * @time 2018.11.18
 * @author 李志坚
 */
public class ConsultingFieldDetailRelation {
    private Long id;

    private Long consultantId;

    private Long consultingFieldDetailId;

    private Integer isDelete;

    private Date gmtCreate;

    private Date gmtUpdate;

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Long consultantId) {
        this.consultantId = consultantId;
    }

    public Long getConsultingFieldDetailId() {
        return consultingFieldDetailId;
    }

    public void setConsultingFieldDetailId(Long consultingFieldDetailId) {
        this.consultingFieldDetailId = consultingFieldDetailId;
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