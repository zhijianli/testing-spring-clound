package com.xinmao.common.userOperationCenter.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.depression.dao.AppOrderBespeakMapper;
//import com.depression.dao.CapitalDiscountBestowalMapper;
//import com.depression.dao.CapitalIncomeExpensesMapper;
//import com.depression.dao.CapitalPersonalAssetsMapper;
//import com.depression.dao.CapitalPlatformCashMapper;
//import com.depression.dao.EvaluationLabelMapper;
//import com.depression.dao.LicenseTypeMapper;
//import com.depression.dao.PsychoCardMapper;
//import com.depression.dao.ServiceGoodsBespeakMapper;
//import com.depression.dao.ServiceGoodsListenMapper;
//import com.depression.dao.ServiceGoodsMapper;
//import com.depression.dao.ServiceOrderMapper;
//import com.depression.dao.ServiceOrderNotesMapper;
//import com.depression.dao.ServiceOrderRefuseMapper;
//import com.depression.entity.AccountDetailsDirectionType;
//import com.depression.entity.AccountDetailsType;
//import com.depression.entity.Constant;
//import com.depression.entity.CouponStatus;
//import com.depression.entity.CouponType;
//import com.depression.entity.ErrorCode;
//import com.depression.entity.OrderState;
//import com.depression.model.AppOrderBespeak;
//import com.depression.model.BespeakParams;
//import com.depression.model.CapitalCommissionRate;
//import com.depression.model.CapitalCouponEntity;
//import com.depression.model.CapitalDiscountBestowal;
//import com.depression.model.CapitalIncomeExpenses;
//import com.depression.model.CapitalPersonalAssets;
//import com.depression.model.CapitalPlatformCash;
//import com.depression.model.ElectAssistantRecord;
//import com.depression.model.EvaluationLabel;
//import com.depression.model.LicenseType;
//import com.depression.model.Member;
//import com.depression.model.NetsalesOrder;
//import com.depression.model.Page;
//import com.depression.model.PennantRecord;
//import com.depression.model.PsychoCard;
//import com.depression.model.ServiceGoods;
//import com.depression.model.ServiceGoodsBespeak;
//import com.depression.model.ServiceGoodsListen;
//import com.depression.model.ServiceOrder;
//import com.depression.model.ServiceOrderNotes;
//import com.depression.model.ServiceOrderRefuse;
//import com.depression.utils.AliyunIMUtil;
//import com.depression.utils.BigDecimalUtil;
//import com.depression.utils.SmsUtil;
import com.github.pagehelper.PageHelper;
import com.xinmao.common.userOperationCenter.domain.ServiceOrder;
import com.xinmao.common.userOperationCenter.mapper.ServiceOrderMapper;

@Service
public class ServiceOrderService
{

    Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private ServiceOrderMapper serviceOrderMapper;

    /**
     * 条件查询
     *
     * @param record
     * @return
     */
    public List<ServiceOrder> selectSelective(ServiceOrder record)
    {
        return serviceOrderMapper.selectSelective(record);
    }

}
