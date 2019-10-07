package com.xinmao.common.util.wechat;

/**
 * User: 李志坚
 * Date: 2019/10/7
 * 微信相关常量类
 */
public class WechatConstant {

	public static String APP_ID = "wx64e17f58ebba5d24";

	public static String APP_SECRET = "5ebf51ee4a0d1f0c8fa565f71b194324";

	//获取accessToken的链接
	public static String GET_ACCESS_TOKEN_OAUTH = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	//文字安全检查的链接
	public static String CHECK_TEXT_URL = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=ACCESSTOKEN";

	//图片安全检查的链接
	public static String CHECK_PIC_URL = "https://api.weixin.qq.com/wxa/img_sec_check?access_token=ACCESSTOKEN";

}
