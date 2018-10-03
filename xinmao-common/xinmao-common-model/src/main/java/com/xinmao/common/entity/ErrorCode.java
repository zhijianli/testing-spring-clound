package com.xinmao.common.entity;

public enum ErrorCode
{
	// 此处添加error code定义
	SUCCESS(0, "Suceess"),

	ERROR_DATABASE_QUERY(1, "数据库查询失败"),
	ERROR_DATABASE_INSERT(2, "database insert failed"),
	ERROR_DATABASE_UPDATE(3, "database update failed"),
	ERROR_USERINFO_NOT_FOUND(4, "user information not found"),
	ERROR_PROPERTY_COPY(100, "property copy failed"),
	ERROR_PARAM_INCOMPLETE(101, "request param incomplete"),
	ERROR_PARAM_INVALID(102, "request param invalid"),
	ERROR_PARAM_JSON(103, "request param json error"),
	ERROR_SYSTEM_ERROR(104, "system error"),
	ERROR_PASSWORD_INCORRECT(200, "password incorrect"),
	ERROR_USERNAME_INEXISTENT(201, "username inexistent"),
	ERROR_USERNAME_EXISTED(202, "username existed"),
	ERROR_LOGIN_FAILED(203, "username or password incorrect"),	
	ERROR_PAGEINDEX_IS_NULL_ERROR(204, "pageIndex is null error"),
	ERROR_PAGESIZE_IS_NULL_ERROR(205, "pageSize is null error"),
	ERROR_STATUS_NOT_ALLOWED_ERROR(206, "status not allowed error"),
	ERROR_MOBILEPHONE_EXISTED(207, "mobile phone existed"),
	ERROR_USER_INEXISTENT(208, "user inexistent"),
	ERROR_ID_INEXISTENT(209, "id inexistent"),
	ERROR_RECORD_INUNIQUE(210, "record inunique"),
	ERROR_SYSTEM_IO(300, "request system io failed"),
	ERROR_REG_VOIP(301, "register voip failed"),
	ERROR_NOT_LOGIN(302, "not login."),
	ERROR_NOT_SHARE(303, "not share."),
	ERROR_WECHAT_LOGIN_FAILED(304, "login failed."),
	ERROR_WECHAT_NOT_LOGIN(305, "wechat not login."),
	ERROR_PSYCHO_GROUP_IN_USE(306, "psycho group in use."),
	ERROR_EMPLOYEE_NOT_BELONGS_ENTERPRISE(307, "employee not belongs to enterprise."),
	
	/*------------------------心猫测试中心--------------------------------*/
	ERROR_TESTSCALE_ONLINE(660, "测试量表已上线，无法删除"),
	ERROR_DIMENSION_RELATE_COQ(661, "此维度有关联定性条件，无法删除"),
	ERROR_CLASS_HOME_RECOMM(662, "此分类已被首页推荐，无法删除"),
	ERROR_POPULATION_RANGE_HAVE_NORM(663, "人群范围已设置维度常模，无法修改"),
	ERROR_TESTSCALE_NOT_ONLINE(664, "测试量表尚未上线"),
	ERROR_SET_UP_DIMENSION_INFO(665, "设置各维度信息时出错，无法创建测试订单"),
	ERROR_GET_TEST_SCALE_INFO(666, "获取量表信息时出错，无法创建测试订单"),
	ERROR_SET_UP_TITLE_OPTION_INFO(667, "设置题目选项信息时出错，无法创建测试订单"),
	ERROR_CHOOSE_QUALITATIVE(668, "选择符合条件的定性时出错，无法创建测试订单"),
	ERROR_COMPLETE_TEST(669, "创建测试量表订单时出错，无法创建测试订单"),
	ERROR_UPDATE_NUMBER_OF_TEST(670, "创建测试订单成功，但增加量表测试次数时出错"),
	ERROR_POPULATION_RANGE_COINCIDENCE(671, "人群范围有重合，请重新设置"),
	ERROR_HAVE_PREMISE_QUALITATIVE(672, "已经存在一个前提定性，无法重复创建"),
	ERROR_HAVE_OTHER_QUALITATIVE(673, "已经存在一个其他定性，无法重复创建"),

	
	/*------------------------心猫Banner中心--------------------------------*/
	ERROR_HAVE_SAME_SORTING(680, "已经有相同排序号的banner存在，请修改排序号后再操作"),

	/*------------------------心猫交互中心--------------------------------*/
	ERROR_ONLY_COMMENT_ONCE(690, "同一个话题同一位专家只能回复一次"),
	ERROR_COMMENT_IS_NOT_EMPTY(691, "评论不能为空"),
	
	;
	
	
	
	
	/*--------------------------------------------------------*/
	private Integer code;
	private String message;

	private ErrorCode(Integer code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
