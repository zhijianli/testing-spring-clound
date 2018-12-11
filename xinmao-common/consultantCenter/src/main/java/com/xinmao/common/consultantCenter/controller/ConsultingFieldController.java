package com.xinmao.common.consultantCenter.controller;//package com.xinmao.common.consultantCenter.controller;

import com.xinmao.common.consultantCenter.domain.ConsultingField;
import com.xinmao.common.consultantCenter.service.ConsultantFieldService;
import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 咨询师领域相关的Controller
 * @time 2018.11.25
 * @author 李志坚
 */
@RestController
@RequestMapping("consultingField")
public class ConsultingFieldController {

	Logger log = Logger.getLogger(this.getClass());


	 @Autowired
     private ConsultantFieldService consultantFieldService;

    /**
     * @Description: 根据条件获取咨询师列表
     * @Author: 李志坚
     * @Date: 2018/11/18
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getAllMessageByCondition")
    @ResponseBody
    public Object getAllMessageByCondition(ConsultingField consultantField,HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();
        List<ConsultingField> consultingFieldList = new ArrayList<ConsultingField>();

        try {
            consultingFieldList = consultantFieldService.getAllMessageByCondition(consultantField);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.put("consultingFieldList", consultingFieldList);
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }
}

