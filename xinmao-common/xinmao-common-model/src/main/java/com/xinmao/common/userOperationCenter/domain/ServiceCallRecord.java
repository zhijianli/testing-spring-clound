package com.xinmao.common.userOperationCenter.domain;

import java.util.Date;

import com.xinmao.common.entity.Page;

public class ServiceCallRecord extends Page {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.cr_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Long crId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.service_order_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Long serviceOrderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.order_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private String orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.callSid
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private String callsid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.caller
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private String caller;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.called
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private String called;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.caller_duration
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Long callerDuration;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.called_duration
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Long calledDuration;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.begin_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Date beginTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.end_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Date endTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.byetype
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Long byetype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.record_url
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private String recordUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.create_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.modify_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Date modifyTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.is_enable
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Byte isEnable;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.is_delete
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Byte isDelete;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column service_call_record.is_calling
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	private Byte isCalling;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.cr_id
	 * @return  the value of service_call_record.cr_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Long getCrId() {
		return crId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.cr_id
	 * @param crId  the value for service_call_record.cr_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setCrId(Long crId) {
		this.crId = crId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.service_order_id
	 * @return  the value of service_call_record.service_order_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Long getServiceOrderId() {
		return serviceOrderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.service_order_id
	 * @param serviceOrderId  the value for service_call_record.service_order_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setServiceOrderId(Long serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.order_id
	 * @return  the value of service_call_record.order_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.order_id
	 * @param orderId  the value for service_call_record.order_id
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.callSid
	 * @return  the value of service_call_record.callSid
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public String getCallsid() {
		return callsid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.callSid
	 * @param callsid  the value for service_call_record.callSid
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setCallsid(String callsid) {
		this.callsid = callsid == null ? null : callsid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.caller
	 * @return  the value of service_call_record.caller
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public String getCaller() {
		return caller;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.caller
	 * @param caller  the value for service_call_record.caller
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setCaller(String caller) {
		this.caller = caller == null ? null : caller.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.called
	 * @return  the value of service_call_record.called
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public String getCalled() {
		return called;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.called
	 * @param called  the value for service_call_record.called
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setCalled(String called) {
		this.called = called == null ? null : called.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.caller_duration
	 * @return  the value of service_call_record.caller_duration
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Long getCallerDuration() {
		return callerDuration;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.caller_duration
	 * @param callerDuration  the value for service_call_record.caller_duration
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setCallerDuration(Long callerDuration) {
		this.callerDuration = callerDuration;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.called_duration
	 * @return  the value of service_call_record.called_duration
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Long getCalledDuration() {
		return calledDuration;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.called_duration
	 * @param calledDuration  the value for service_call_record.called_duration
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setCalledDuration(Long calledDuration) {
		this.calledDuration = calledDuration;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.begin_time
	 * @return  the value of service_call_record.begin_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.begin_time
	 * @param beginTime  the value for service_call_record.begin_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.end_time
	 * @return  the value of service_call_record.end_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.end_time
	 * @param endTime  the value for service_call_record.end_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.byetype
	 * @return  the value of service_call_record.byetype
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Long getByetype() {
		return byetype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.byetype
	 * @param byetype  the value for service_call_record.byetype
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setByetype(Long byetype) {
		this.byetype = byetype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.record_url
	 * @return  the value of service_call_record.record_url
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public String getRecordUrl() {
		return recordUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.record_url
	 * @param recordUrl  the value for service_call_record.record_url
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setRecordUrl(String recordUrl) {
		this.recordUrl = recordUrl == null ? null : recordUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.create_time
	 * @return  the value of service_call_record.create_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.create_time
	 * @param createTime  the value for service_call_record.create_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.modify_time
	 * @return  the value of service_call_record.modify_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.modify_time
	 * @param modifyTime  the value for service_call_record.modify_time
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.is_enable
	 * @return  the value of service_call_record.is_enable
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Byte getIsEnable() {
		return isEnable;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.is_enable
	 * @param isEnable  the value for service_call_record.is_enable
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.is_delete
	 * @return  the value of service_call_record.is_delete
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Byte getIsDelete() {
		return isDelete;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.is_delete
	 * @param isDelete  the value for service_call_record.is_delete
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column service_call_record.is_calling
	 * @return  the value of service_call_record.is_calling
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public Byte getIsCalling() {
		return isCalling;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column service_call_record.is_calling
	 * @param isCalling  the value for service_call_record.is_calling
	 * @mbggenerated  Thu Jun 08 17:22:13 CST 2017
	 */
	public void setIsCalling(Byte isCalling) {
		this.isCalling = isCalling;
	}
}
