package com.xinmao.common.userOperationCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmao.common.userOperationCenter.domain.ServiceCallRecord;
import com.xinmao.common.userOperationCenter.mapper.ServiceCallRecordMapper;

//import com.depression.dao.ServiceCallRecordMapper;
//import com.depression.model.ServiceCallRecord;

/**
 * 订单通话记录
 * 
 * @author 范新辉
 * 
 */
@Service
public class ServiceCallRecordService
{
	@Autowired
	private ServiceCallRecordMapper serviceCallRecordMapper;


	public List<ServiceCallRecord> selectSelective(ServiceCallRecord record)
	{
		return serviceCallRecordMapper.selectSelective(record);
	}

	
}
