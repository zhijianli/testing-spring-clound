package com.xinmao.common.util.wechat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.xinmao.common.userCenter.domain.wechat.UserWeiXin;

public class OAuthService
{
	public static Logger log = Logger.getLogger(OAuthService.class);

	public static String OAUTH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public static String GET_USER_INFO_OAUTH = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static String GET_ACCESS_TOKEN_OAUTH = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static String XCX_OAUTH = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code";  //测试小程序获取openId
	public static String XCXAPPID = "wx78d77c4e2de3e5e5";  //小程序相关APPID
	public static String XCXSECRETTEST = "58f375fbdd260a123957acb5eed45035";//小程序相关SECRETTEST
	public static String TOPIC_APPID = "wxf01eecfd4f99bfe6";  //话题中心小程序相关APPID
	public static String TOPIC_SECRETTEST = "0bb67d3a65e38242d61a96b4cb474f99";//话题中心小程序相关SECRETTEST

	public static String getOauthUrl(String redirectUrl, String charset, String scope)
	{
		String url = "";
		try
		{
			url = OAUTH.replace("APPID", ConstantWeChat.APPID).replace("REDIRECT_URI", URLEncoder.encode(redirectUrl, charset)).replace("SCOPE", scope);
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return url;
	}

	public static AccessTokenOAuth getOAuthAccessToken(String code)
	{
		String url = GET_ACCESS_TOKEN_OAUTH.replace("APPID", ConstantWeChat.APPID).replace("SECRET", ConstantWeChat.APPSECRET).replace("CODE", code);

		JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", null);

		AccessTokenOAuth accessTokenOAuth = null;

		if (jsonObject != null)
		{
			if ((StringUtil.isNotEmpty(jsonObject.get("errcode"))) && (jsonObject.get("errcode") != "0"))
			{
				log.error("获取access_token失败 errcode:" + jsonObject.getInt("errcode") + "，errmsg:" + jsonObject.getString("errmsg"));
			} else
			{
				accessTokenOAuth = new AccessTokenOAuth();
				accessTokenOAuth.setAccessToken(jsonObject.getString("access_token"));
				accessTokenOAuth.setExpiresIn(jsonObject.getInt("expires_in"));
				accessTokenOAuth.setRefreshToken(jsonObject.getString("refresh_token"));
				accessTokenOAuth.setOpenid(jsonObject.getString("openid"));
				accessTokenOAuth.setScope(jsonObject.getString("scope"));
			}
		}
		return accessTokenOAuth;
	}

	public static String getOAuthAccessOpenId(String code) {
		String url = GET_ACCESS_TOKEN_OAUTH.replace("APPID", ConstantWeChat.APPID).replace("SECRET", ConstantWeChat.APPSECRET).replace("CODE", code);
		JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", null);
		String openId = null;
		if (jsonObject != null) {
			if ((StringUtil.isNotEmpty(jsonObject.get("errcode"))) && (jsonObject.get("errcode") != "0")) {
				log.error("获取openId失败 errcode:" + jsonObject.getInt("errcode") + "，errmsg:" + jsonObject.getString("errmsg"));
			} else {
				openId = jsonObject.getString("openid");
			}
		}
		return openId;
	}

	/**
	 * @Description: 根据小程序code获取openId
	 * @Author: zhangw
	 * @Date: 2018/2/3 16:55
	 */
	public static String getXcxOpenId(String code){
//		String url = XCX_OAUTH.replace("APPID", XCXAPPID).replace("SECRET", XCXSECRETTEST).replace("CODE", code);
		String url = XCX_OAUTH.replace("APPID", WechatConstant.APP_ID).replace("SECRET", WechatConstant.APP_SECRET).replace("CODE", code);
		JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", null);
		String openId = null;
		if (jsonObject != null){
			if ((StringUtil.isNotEmpty(jsonObject.get("errcode"))) && (jsonObject.get("errcode") != "0")) {
				log.error("获取openId失败 errcode:" + jsonObject.getInt("errcode") + "，errmsg:" + jsonObject.getString("errmsg"));
				return null;
			}else {
				 openId = jsonObject.getString("openid");
			}
		}
		return openId;
	}
	
	
	/**
	 * @Description: 根据话题中心小程序code获取openId
	 * @Author: 李志坚
	 * @Date: 2018/6/13
	 */
	public static String getTopicOpenId(String code){
		String url = XCX_OAUTH.replace("APPID", TOPIC_APPID).replace("SECRET", TOPIC_SECRETTEST).replace("CODE", code);
		JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", null);
		String openId = null;
		if (jsonObject != null){
			if ((StringUtil.isNotEmpty(jsonObject.get("errcode"))) && (jsonObject.get("errcode") != "0")) {
				log.error("获取openId失败 errcode:" + jsonObject.getInt("errcode") + "，errmsg:" + jsonObject.getString("errmsg"));
				return null;
			}else {
				 openId = jsonObject.getString("openid");
			}
		}
		return openId;
	}
	

	public static UserWeiXin getUserInfoOauth(String token, String openid)
	{
		UserWeiXin user = null;
		if (token != null)
		{
			String url = GET_USER_INFO_OAUTH.replace("ACCESS_TOKEN", token).replace("OPENID", openid);

			JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", null);

			if (jsonObject != null)
			{
				if ((StringUtil.isNotEmpty(jsonObject.get("errcode"))) && (jsonObject.get("errcode") != "0"))
				{
					log.error("获取用户信息失败 errcode:" + jsonObject.getInt("errcode") + "，errmsg:" + jsonObject.getString("errmsg"));
				} else
				{
					user = new UserWeiXin();
					user.setOpenid(jsonObject.getString("openid"));
					user.setNickname(jsonObject.getString("nickname"));
					user.setSex(Integer.valueOf(jsonObject.getInt("sex")));
					user.setCity(jsonObject.getString("city"));
					user.setCountry(jsonObject.getString("country"));
					user.setProvince(jsonObject.getString("province"));
					user.setLanguage(jsonObject.getString("language"));
					user.setPrivilege(jsonObject.getString("privilege"));
					user.setHeadimgurl(jsonObject.getString("headimgurl"));
				}
			}
		}
		return user;
	}
}
