package com.xinmao.common.consultantCenter.service;

import com.xinmao.common.consultantCenter.domain.ConsultingFieldDetailRelation;
import com.xinmao.common.consultantCenter.domain.ConsultingFieldDetail;
import com.xinmao.common.consultantCenter.mapper.ConsultingFieldDetailMapper;
import com.xinmao.common.consultantCenter.mapper.ConsultingFieldDetailRelationMapper;
import com.xinmao.common.consultantCenter.mapper.ConsultingFieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.xinmao.common.consultantCenter.domain.ConsultingField;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 咨询师领域相关的Service
 * @time 2018.11.25
 * @author 李志坚
 */
@Service
public class ConsultantFieldService {
	
	@Autowired
    ConsultingFieldMapper consultantFieldMapper;

    @Autowired
    ConsultingFieldDetailMapper consultingFieldDetailMapper;

    @Autowired
    ConsultingFieldDetailRelationMapper consultingFieldDetailRelationMapper;

    public List<ConsultingField> getConsultantFieldAndDetail(Long consultantId){
        ConsultingField consultingField = new ConsultingField();
        List<ConsultingField>  cfList = consultantFieldMapper.getAllMessageByCondition(consultingField);
        if(cfList!=null && cfList.size()>0){
            for(int i=0;i<cfList.size();i++){

                //获取咨询领域详情
                ConsultingField cf = cfList.get(i);
                ConsultingFieldDetail consultingFieldDetail = new ConsultingFieldDetail();
                consultingFieldDetail.setConsultingFieldId(cf.getId());
                List<ConsultingFieldDetail>  cfdList = consultingFieldDetailMapper.getCfdListByCondition(consultingFieldDetail);

                //判断该领域详情是否已被选中
                if(consultantId!=null && consultantId > 0){
                    ConsultingFieldDetailRelation cfdr = new ConsultingFieldDetailRelation();
                    cfdr.setConsultantId(consultantId);
                    List<Long> consultingFieldDetailIdList = consultingFieldDetailRelationMapper.getConsultingFieldDetailIdList(cfdr);
                    if(cfdList!=null && cfdList.size()>0  && consultingFieldDetailIdList !=null && consultingFieldDetailIdList.size()>0){
                        for(int j=0;j<cfdList.size();j++){
                            ConsultingFieldDetail cfd = cfdList.get(j);
                            if(consultingFieldDetailIdList.contains(cfd.getId())){
                                cfd.setChecked(true);
                            }
                        }
                    }
                }

                cf.setConsultingFieldDetailList(cfdList);
            }
        }
        return cfList;
    }

    public void deleteConsultingField(ConsultingField consultingField) {
        Date currentTime = new Date();
        consultingField.setGmtUpdate(currentTime);
        consultantFieldMapper.deleteConsultingField(consultingField);

        this.deleteConsultingFieldDetail(consultingField.getId(), currentTime);
    }


    public ConsultingField getConsultingFieldById(Long id) {
        ConsultingField consultingField =  consultantFieldMapper.getConsultingFieldById(id);
        this.setConsultingFieldDetailStr(consultingField);

        return consultingField;
    }

    public List<ConsultingField> getAllMessageByCondition(ConsultingField consultingField) {
        List<ConsultingField> consultingFieldList = consultantFieldMapper.getAllMessageByCondition(consultingField);
        if(consultingFieldList!=null && consultingFieldList.size()>0){
            for(int i = 0;i<consultingFieldList.size();i++){
                ConsultingField cf = consultingFieldList.get(i);
                this.setConsultingFieldDetailStr(cf);
            }
        }
        return consultingFieldList;
    }

    public int selectCount(ConsultingField consultingField) {
        return consultantFieldMapper.selectCount(consultingField);
    }

    public long insertOrUpdateConsultantField(ConsultingField consultingField) {

        //新增或者修改领域
        consultantFieldMapper.insertOrUpdateConsultantField(consultingField);
        long consultantFieldId = consultantFieldMapper.getConsultantFieldId(consultingField);

        Date currentTime = new Date();

        //先删除老的领域详情
        this.deleteConsultingFieldDetail(consultantFieldId, currentTime);

        //再新增或者修改新的领域详情
        String consultantFieldStr = consultingField.getConsultingFieldDetailStr();
        String[] consultantFieldArray = consultantFieldStr.split(ConsultingFieldDetail.SEPARATOR);

        for(int i =0;i<consultantFieldArray.length;i++){
            ConsultingFieldDetail consultingFieldDetail = new ConsultingFieldDetail();
            consultingFieldDetail.setConsultingFieldId(consultantFieldId);
            consultingFieldDetail.setName(consultantFieldArray[i]);
            consultingFieldDetail.setIsDelete(0);
            consultingFieldDetail.setGmtCreate(currentTime);
            consultingFieldDetail.setGmtUpdate(currentTime);
            consultingFieldDetailMapper.insertOrUpdateConsultantFieldDetail(consultingFieldDetail);
        }

        return consultantFieldId;
    }

    private void deleteConsultingFieldDetail(long consultantFieldId, Date currentTime) {
        ConsultingFieldDetail deleteDetail = new ConsultingFieldDetail();
        deleteDetail.setConsultingFieldId(consultantFieldId);
        deleteDetail.setGmtUpdate(currentTime);
        consultingFieldDetailMapper.deleteConsultantFieldDetail(deleteDetail);
    }

    private void setConsultingFieldDetailStr(ConsultingField consultingField) {
        if(consultingField!=null){
            ConsultingFieldDetail consultingFieldDetail = new ConsultingFieldDetail();
            consultingFieldDetail.setConsultingFieldId(consultingField.getId());
            List<ConsultingFieldDetail> cfdList = consultingFieldDetailMapper.getCfdListByCondition(consultingFieldDetail);
            if(cfdList!=null && cfdList.size()>0){
                String consultingFieldDetailStr = "";
                for(int i=0;i<cfdList.size();i++){
                    ConsultingFieldDetail cfd = cfdList.get(i);
                    if(i==0){
                        consultingFieldDetailStr = consultingFieldDetailStr + cfd.getName();
                    }else{
                        consultingFieldDetailStr = consultingFieldDetailStr + ConsultingFieldDetail.SEPARATOR + cfd.getName();
                    }
                }
                consultingField.setConsultingFieldDetailStr(consultingFieldDetailStr);
            }
        }
    }
}
