package com.xinmao.common.consultantCenter.domain;

import com.xinmao.common.entity.Page;

import java.util.Date;
import java.util.List;

/**
 * 咨询领域DO
 * @time 2018.11.18
 * @author 李志坚
 */
public class ConsultingField  extends Page{
    private Long id;

    private String name;

    private String consultingFieldDetailStr;

    private Integer isDelete;

    private Date gmtCreate;

    private Date gmtUpdate;

    private List<ConsultingFieldDetail> consultingFieldDetailList;

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public List<ConsultingFieldDetail> getConsultingFieldDetailList() {
        return consultingFieldDetailList;
    }

    public void setConsultingFieldDetailList(List<ConsultingFieldDetail> consultingFieldDetailList) {
        this.consultingFieldDetailList = consultingFieldDetailList;
    }

    public String getConsultingFieldDetailStr() {
        return consultingFieldDetailStr;
    }

    public void setConsultingFieldDetailStr(String consultingFieldDetailStr) {
        this.consultingFieldDetailStr = consultingFieldDetailStr;
    }

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