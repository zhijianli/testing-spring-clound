package com.xinmao.common.consultantCenter.controller;//package com.xinmao.common.consultantCenter.controller;

import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.xinmao.common.consultantCenter.domain.Consultant;
import com.xinmao.common.consultantCenter.service.ConsultantService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 咨询师相关的Controller
 * @time 2018.11.18
 * @author 李志坚
 */
@RestController
@RequestMapping("consultant")
public class ConsultantController{

	Logger log = Logger.getLogger(this.getClass());

//    @Autowired
//    private CommonCollectionService service;

	 @Autowired
     private ConsultantService consultantService;

    /**
     * @Description: 根据条件获取咨询师列表
     * @Author: 李志坚
     * @Date: 2018/11/18
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getAllMessageByCondition")
    @ResponseBody
    public Object getAllMessageByCondition(Consultant consultant,HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();
        List<Consultant> consultantList = new ArrayList<Consultant>();
        int consultantCount = 0;
//        if (code == null || code.equals("")) {
//            result.setCode(-1);
//            result.setMsg("code不能为空");
//            return result;
//        }

        try {
            Integer pageIndex = consultant.getPageIndex();
            Integer pageSize = consultant.getPageSize();
            if(pageIndex==null || pageIndex==0 || pageSize==null || pageSize==0){
                result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
                result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
                return result;
            }

            PageHelper.startPage(pageIndex, pageSize);
            consultantList = consultantService.getAllMessageByCondition(consultant);

            //获取咨询师数量
            Consultant ct = new Consultant();
            ct.setName(consultant.getName());
            ct.setPrice(consultant.getPrice());
            consultantCount = consultantService.selectCount(ct);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.put("consultantList", consultantList);
        result.put("consultantCount", consultantCount);
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * @Description: 根据id获取咨询师列表
     * @Author: 李志坚
     * @Date: 2018/11/18
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getConsultantById")
    @ResponseBody
    public Object getConsultantById(Consultant consultant,HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();
        Consultant resultConsultant = new Consultant();

        try {

            Long id = consultant.getId();
            if(id==null || id==0){
                result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
                result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
                return result;
            }

            resultConsultant = consultantService.getConsultantById(id);


        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.put("consultant", resultConsultant);
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * @Description: 添加/编辑咨询师
     * @Author: 李志坚
     * @Date: 2018/12/11
     */
    @RequestMapping(method = RequestMethod.POST, value = "/insertOrUpdateConsultant")
    @ResponseBody
    public Object insertOrUpdateConsultant(Consultant consultant,HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();

        try {
            Date currentTime = new Date();
            consultant.setIsDelete(0);
            consultant.setIsTop(0);
            consultant.setGmtCreate(currentTime);
            consultant.setGmtUpdate(currentTime);
            consultantService.insertOrUpdateConsultant(consultant);

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
     * @Description: 删除咨询师
     * @Author: 李志坚
     * @Date: 2018/12/12
     */
    @RequestMapping(method = RequestMethod.POST, value = "/deleteConsultant")
    @ResponseBody
    public Object deleteConsultant(Consultant consultant,HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultEntity result = new ResultEntity();

        try {
            Long id = consultant.getId();
            if(id==null || id==0){
                result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
                result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
                return result;
            }

            consultantService.deleteConsultant(consultant);
//
//            if(insertNum<=0){
//                result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
//                result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
//                return result;
//            }

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

