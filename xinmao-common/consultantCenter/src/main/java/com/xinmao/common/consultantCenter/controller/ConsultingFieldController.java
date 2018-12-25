package com.xinmao.common.consultantCenter.controller;//package com.xinmao.common.consultantCenter.controller;

import com.github.pagehelper.PageHelper;
import com.xinmao.common.consultantCenter.domain.Consultant;
import com.xinmao.common.consultantCenter.domain.ConsultingField;
import com.xinmao.common.consultantCenter.service.ConsultantFieldService;
import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
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
     * @Description: 获取咨询领域和咨询领域详情
     * @Author: 李志坚
     * @Date: 2018/12/24
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getConsultantFieldAndDetail")
    @ResponseBody
    public Object getConsultantFieldAndDetail(HttpServletResponse response,Long consultantId) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();
        List<ConsultingField> consultingFieldList = new ArrayList<ConsultingField>();

        try {
            consultingFieldList = consultantFieldService.getConsultantFieldAndDetail(consultantId);

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


    /**
     * @Description: 根据id获取咨询领域
     * @Author: 李志坚
     * @Date: 2018/12/12
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getConsultantFieldById")
    @ResponseBody
    public Object getConsultantFieldById(ConsultingField consultantField,HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();
        ConsultingField resultConsultantField = new ConsultingField();

        try {
            Long id = consultantField.getId();
            if(id==null || id==0){
                result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
                result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
                return result;
            }

            resultConsultantField = consultantFieldService.getConsultingFieldById(id);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.put("consultantField", resultConsultantField);
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * @Description: 根据条件获取咨询领域列表
     * @Author: 李志坚
     * @Date: 2018/11/18
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getAllMessageByCondition")
    @ResponseBody
    public Object getAllMessageByCondition(ConsultingField consultantField,HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();
        List<ConsultingField> consultingFieldList = new ArrayList<ConsultingField>();
        int consultingFieldCount = 0;

        try {
            Integer pageIndex = consultantField.getPageIndex();
            Integer pageSize = consultantField.getPageSize();
            if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
                result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
                result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
                return result;
            }

            PageHelper.startPage(pageIndex, pageSize);

            consultingFieldList = consultantFieldService.getAllMessageByCondition(consultantField);

            //获取咨询领域数量
            ConsultingField cf = new ConsultingField();
            cf.setName(consultantField.getName());
            consultingFieldCount = consultantFieldService.selectCount(cf);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.put("consultingFieldList", consultingFieldList);
        result.put("consultingFieldCount", consultingFieldCount);
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }


    /**
     * @Description: 添加/编辑咨询领域
     * @Author: 李志坚
     * @Date: 2018/12/11
     */
    @RequestMapping(method = RequestMethod.POST, value = "/insertOrUpdateConsultantField")
    @ResponseBody
    public Object insertOrUpdateConsultantField(ConsultingField consultantField, HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();
        try {
            Date currentTime = new Date();
            consultantField.setIsDelete(0);
            consultantField.setGmtCreate(currentTime);
            consultantField.setGmtUpdate(currentTime);
            long consultantFieldId = consultantFieldService.insertOrUpdateConsultantField(consultantField);

            if(consultantFieldId<=0){
                result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
                result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * @Description: 删除咨询领域
     * @Author: 李志坚
     * @Date: 2018/12/12
     */
    @RequestMapping(method = RequestMethod.POST, value = "/deleteConsultingField")
    @ResponseBody
    public Object deleteConsultingField(ConsultingField consultantField,HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();

        try {

            Long id = consultantField.getId();
            if(id==null || id==0){
                result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
                result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
                return result;
            }

            consultantFieldService.deleteConsultingField(consultantField);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }

}

