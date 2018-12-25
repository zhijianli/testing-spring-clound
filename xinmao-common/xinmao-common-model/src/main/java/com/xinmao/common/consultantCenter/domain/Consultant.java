package com.xinmao.common.consultantCenter.domain;

import com.xinmao.common.entity.Page;

import java.util.Date;
import java.util.List;

/**
 * 咨询师DO
 * @time 2018.11.18
 * @author 李志坚
 */
public class Consultant extends Page{

    public static final String OPERATION_INSERT = "添加";

    public static final String OPERATION_UPDATE = "编辑";

    private Long id;

    private String name;

    private String telephone;

    private Integer price;

    private String headPortraitUrl;

    private Integer consultationMethod;

    private String consultationMethodStr;

    private String experienceOfTraining;

    private String experienceOfTrainingAbb;

    private String province;

    private String city;

    private List<Long> checkFieldDetailIdList;

    private String operation;

    private Integer isTop;

    private Integer isDelete;

    private Date gmtCreate;

    private Date gmtUpdate;

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public String getExperienceOfTrainingAbb() {
        return experienceOfTrainingAbb;
    }

    public void setExperienceOfTrainingAbb(String experienceOfTrainingAbb) {
        this.experienceOfTrainingAbb = experienceOfTrainingAbb;
    }

    public String getConsultationMethodStr() {
        return consultationMethodStr;
    }

    public void setConsultationMethodStr(String consultationMethodStr) {
        this.consultationMethodStr = consultationMethodStr;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<Long> getCheckFieldDetailIdList() {
        return checkFieldDetailIdList;
    }

    public void setCheckFieldDetailIdList(List<Long> checkFieldDetailIdList) {
        this.checkFieldDetailIdList = checkFieldDetailIdList;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl == null ? null : headPortraitUrl.trim();
    }

    public Integer getConsultationMethod() {
        return consultationMethod;
    }

    public void setConsultationMethod(Integer consultationMethod) {
        this.consultationMethod = consultationMethod;
    }

    public String getExperienceOfTraining() {
        return experienceOfTraining;
    }

    public void setExperienceOfTraining(String experienceOfTraining) {
        this.experienceOfTraining = experienceOfTraining == null ? null : experienceOfTraining.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
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