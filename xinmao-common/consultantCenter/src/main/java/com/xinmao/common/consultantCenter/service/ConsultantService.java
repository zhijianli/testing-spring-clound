package com.xinmao.common.consultantCenter.service;

import com.xinmao.common.consultantCenter.domain.Consultant;
import com.xinmao.common.consultantCenter.domain.ConsultationMethodEnum;
import com.xinmao.common.consultantCenter.mapper.ConsultantMapper;
import com.xinmao.common.consultantCenter.mapper.ConsultingFieldDetailRelationMapper;
import com.xinmao.common.consultantCenter.domain.ConsultingFieldDetailRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 咨询师相关的Service
 * @time 2018.11.18
 * @author 李志坚
 */
@Service
public class ConsultantService {
	
	@Autowired
	ConsultantMapper consultantMapper;

    @Autowired
    ConsultingFieldDetailRelationMapper consultingFieldDetailRelationMapper;

    public Consultant getConsultantById(Long id) {
        return consultantMapper.getConsultantById(id);
    }

    public List<Consultant> getAllMessageByCondition(Consultant consultant) {

        List<Consultant> consultantList =  consultantMapper.getAllMessageByCondition(consultant);

        if(consultantList!=null && consultantList.size()>0){
            for(int i =0;i<consultantList.size();i++){

                Consultant consult = consultantList.get(i);
                Long consultantId = consult.getId();
                //组装咨询领域
//                ConsultingFieldDetailRelation cdfRelation = new ConsultingFieldDetailRelation();
//                cdfRelation.setConsultantId(consultantId);
//                List<Long> detailIdList = consultingFieldDetailRelationMapper.getConsultingFieldDetailIdList(cdfRelation);
//                if(detailIdList!=null && detailIdList.size()>0){
//
//                }

                //配置咨询方式
                Integer consultationMethod = consult.getConsultationMethod();
                consult.setConsultationMethodStr(ConsultationMethodEnum.getValueByKey(consultationMethod));

                //配置培训简介缩写
                String experienceOfTraining = consult.getExperienceOfTraining();
                if(experienceOfTraining!=null&&experienceOfTraining.length()>10){
                    consult.setExperienceOfTrainingAbb(experienceOfTraining.substring(0,10));
                }else{
                    consult.setExperienceOfTrainingAbb(experienceOfTraining);
                }
            }
        }
        return consultantList;
    }

    public int selectCount(Consultant consultant) {
        return consultantMapper.selectCount(consultant);
    }

    public void deleteConsultant(Consultant consultant) {

         //删除咨询师
         Date currentTime = new Date();
         consultant.setIsDelete(-1);
         consultant.setGmtUpdate(currentTime);
         consultantMapper.deleteConsultant(consultant);

         //删除咨询师与咨询领域的关联
         ConsultingFieldDetailRelation cfdr = new ConsultingFieldDetailRelation();
         cfdr.setConsultantId(consultant.getId());
         cfdr.setIsDelete(-1);
         cfdr.setGmtUpdate(currentTime);
         consultingFieldDetailRelationMapper.deleteRelationByConsultantId(cfdr);
    }

    public void insertOrUpdateConsultant(Consultant consultant) {

        String operation =  consultant.getOperation();

        //新增或修改咨询师
        if(Consultant.OPERATION_INSERT.equals(operation)){
            consultantMapper.insertConsultant(consultant);
        }else if(Consultant.OPERATION_UPDATE.equals(operation)){
            consultantMapper.updateConsultant(consultant);
        }

        Long consultantId = consultant.getId();
        if(consultantId!=null && consultantId>0){

            Date currentTime = new Date();

            //先删除原先的关联
            ConsultingFieldDetailRelation cfdr = new ConsultingFieldDetailRelation();
            cfdr.setConsultantId(consultantId);
            cfdr.setIsDelete(-1);
            cfdr.setGmtUpdate(currentTime);
            consultingFieldDetailRelationMapper.deleteRelationByConsultantId(cfdr);

            //维护咨询师与领域详情的关联
            List<Long> checkFieldDetailIdList = consultant.getCheckFieldDetailIdList();
            if(checkFieldDetailIdList!=null && checkFieldDetailIdList.size()>0){

                //再新增或修改原先的关联
                for(int i=0;i<checkFieldDetailIdList.size();i++){
                    ConsultingFieldDetailRelation consultingFieldDetailRelation = new ConsultingFieldDetailRelation();
                    consultingFieldDetailRelation.setConsultantId(consultantId);
                    consultingFieldDetailRelation.setConsultingFieldDetailId(checkFieldDetailIdList.get(i));
                    consultingFieldDetailRelation.setIsDelete(0);
                    consultingFieldDetailRelation.setGmtCreate(currentTime);
                    consultingFieldDetailRelation.setGmtUpdate(currentTime);
                    consultingFieldDetailRelationMapper.insertOrUpdateRelation(consultingFieldDetailRelation);
                }
            }
        }
    }
}
