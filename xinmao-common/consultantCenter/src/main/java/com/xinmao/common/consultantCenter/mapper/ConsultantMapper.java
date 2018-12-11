package com.xinmao.common.consultantCenter.mapper;

import com.xinmao.common.consultantCenter.domain.Consultant;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 咨询师相关的Map
 * @time 2018.11.18
 * @author 李志坚
 */
public interface ConsultantMapper {


    Consultant getConsultantById(Long id);

    List<Consultant> getAllMessageByCondition(Consultant consultant);

    int selectCount(Consultant consultant);

    int insertOrUpdateConsultant(Consultant consultant);

//    int updateByExampleSelective(@Param("record") Consultant record, @Param("example") ConsultantExample example);
//
//    int updateByExample(@Param("record") Consultant record, @Param("example") ConsultantExample example);
}