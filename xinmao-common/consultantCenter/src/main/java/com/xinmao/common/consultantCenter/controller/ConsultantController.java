package com.xinmao.common.consultantCenter.controller;//package com.xinmao.common.consultantCenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xinmao.common.consultantCenter.domain.Consultant;
import com.xinmao.common.consultantCenter.mapper.ConsultantMapper;
import com.xinmao.common.consultantCenter.service.ConsultantService;
import com.xinmao.common.consultantCenter.service.MemberWechatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.common.entity.ErrorCode;
import com.xinmao.common.entity.ResultEntity;
import com.xinmao.common.userCenter.domain.Member;
import com.xinmao.common.userCenter.domain.wechat.MemberWechat;
import com.xinmao.common.userCenter.domain.wechat.UserWeiXin;
import com.xinmao.common.util.wechat.OAuthService;

import java.util.ArrayList;
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
    public Object getAllMessageByCondition(Consultant consultant) {
        ResultEntity result = new ResultEntity();
        List<Consultant> consultantList = new ArrayList<>();
//        if (code == null || code.equals("")) {
//            result.setCode(-1);
//            result.setMsg("code不能为空");
//            return result;
//        }

        try {
            consultantList = consultantService.getAllMessageByCondition(consultant);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultEntity.ERROR);
            result.setError("系统错误");
            return result;
        }
        result.put("consultantList", consultantList);
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMessage());
        return result;
    }
}

