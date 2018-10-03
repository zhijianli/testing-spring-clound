package com.xinmao.common.userOperationCenter.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.depression.dao.MemberMapper;
//import com.depression.dao.MemberWechatMapper;
import com.xinmao.common.userCenter.domain.Member;
import com.xinmao.common.userCenter.domain.wechat.MemberWechat;
//import com.depression.model.Member;
//import com.depression.model.MemberWechat;
import com.xinmao.common.userCenter.domain.wechat.UserWeiXin;
import com.xinmao.common.userOperationCenter.mapper.MemberMapper;
import com.xinmao.common.userOperationCenter.mapper.MemberWechatMapper;

@Service
public class MemberWechatService
{
	@Autowired
	MemberWechatMapper memberWechatMapper;
	@Autowired
	MemberMapper memberMapper;
	
	public static int WECHAT_OPEN = 0; //开放平台
	public static int WECHAT_PUBLIC = 1; //公众平台
	
//	/**
//	 * 根据会员id查询微信用户
//	 * @param mid
//	 * @return 不存在则返回null
//	 */
//	public MemberWechat obtainWechatByMid(Long mid)
//	{
//		MemberWechat mw = new MemberWechat();
//		mw.setMid(mid);
//		List<MemberWechat> mws = memberWechatMapper.selectSelective(mw);
//		if(mws.size() > 0)
//		{
//			return mws.get(0);
//		}else
//		{
//			return null;
//		}
//	}
//	
//	/**
//	 * 根据微信开放平台openid查询微信用户
//	 * @param openOpenid
//	 * @return 不存在则返回null
//	 */
//	public MemberWechat obtainWechatByOpenOpenid(String openOpenid)
//	{
//		MemberWechat mw = new MemberWechat();
//		mw.setOpenOpenid(openOpenid);
//		List<MemberWechat> mws = memberWechatMapper.selectSelective(mw);
//		if(mws.size() > 0)
//		{
//			return mws.get(0);
//		}else
//		{
//			return null;
//		}		
//	}
	
	/**
	 * 根据公众平台openid获取微信用户信息
	 * @param publicOpenid 公众平台openid
	 * @return
	 */
	public MemberWechat obtainWechatByPublicOpenid(String publicOpenid)
	{
		MemberWechat mw = new MemberWechat();
		mw.setPublicOpenid(publicOpenid);
		List<MemberWechat> mws = memberWechatMapper.selectSelective(mw);
		if(mws.size() > 0)
		{
			return mws.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 公用方法  用来根据openId查询用户信息 如果没有此用户则创建新用户
	 * @param userWeiXin
	 * @param openId
	 * @return
	 */
	public MemberWechat getWechatUser(UserWeiXin userWeiXin) {
		Long mid = null;
		MemberWechat memberWechat = obtainWechatByPublicOpenid(userWeiXin.getOpenid());
		if (memberWechat == null) {
			MemberWechat mw = new MemberWechat();
			if(userWeiXin.getNickname() == null){
				mw.setNickname(createNickname());
			}else {
				mw.setNickname(userWeiXin.getNickname());
			}
			mw.setHeadimgurl(userWeiXin.getHeadimgurl());
			if(userWeiXin.getSex() != null ) {
				mw.setSex(userWeiXin.getSex().byteValue());
			}else {
				mw.setSex((byte)0);
			}
			mw.setCountry(userWeiXin.getCountry());
			mw.setProvince(userWeiXin.getProvince());
			mw.setCity(userWeiXin.getCity());
			mw.setPublicOpenid(userWeiXin.getOpenid());
			Member curMem = transCreateMemberByWechat(mw);
			mid = curMem.getMid();
			mw.setMid(mid);
			return mw;
		} else {
//			mid = memberWechat.getMid();
			return memberWechat;
		}
//		return mid;
	}

	public String createNickname() {
		String nickName = null;
			for (int i=0;i< 1;i++){
				String[] firsname = {"饱满的", "沉着的", "吃惊的", "冲动的", "纯洁的", "聪明的", "从容的", "大方的", "多变的",
						"发呆的", "风趣的", "感动的", "高兴的", "古怪的", "果断的", "害羞的", "好学的", "滑稽的", "欢快的", "欢乐的", "欢喜的", "活泼的", "机灵的",
						"机智的", "积极的", "激动的", "坚强的", "健谈的", "开朗的", "慷慨的", "可爱的", "快活的", "快乐的", "宽容的", "老实的", "乐观的", "冷静的", "礼貌的", "理智的",
						"励志的", "伶俐的", "灵活的", "满意的", "美丽的", "腼腆的", "木讷的", "耐心的", "内向的", "飘逸的", "平静的", "亲切的", "勤劳的", "清纯的", "热情的", "热心的",
						"仁慈的", "认真的", "任性的", "洒脱的", "善变的", "善良的", "善意的", "上进的", "深情的", "生动的", "舒畅的", "舒适的", "爽快的", "随便的", "随和的", "随性的",
						"体贴的", "调皮的", "痛快的", "外向的", "微笑的", "温和的", "温柔的", "温顺的", "稳健的", "稳重的", "务实的", "细心的", "贤惠的", "欣喜的", "兴奋的", "幸运的",
						"幸福的", "勇敢的", "幽默的", "犹豫的", "幼稚的", "愉快的", "愉悦的", "正直的", "直率的", "质朴的", "自由的"};
				String[] namelist = { "巴旦杏","白菜","包心菜","荸荠","槟榔","菠菜","菠萝","菜豆","菜瓜","菜花","蚕豆","草菇","草莓","橙","醋粟","大葱","大蒜","地瓜","冬瓜",
						"豆芽","鳄梨","番木瓜","番石榴","蕃茄","蕃薯","凤梨","覆盆子","甘蔗","柑桔","柑子","橄榄","哈密瓜","海棠果","黑加仑","黑莓","红橙",
						"红醋栗","红椒","红毛丹","胡椒","胡萝卜","胡桃","花菜","花生","花椰菜","黄瓜","黄椒","火龙果","鸡蛋果","姜","芥菜","金桔","金针菇",
						"韭菜","橘子","卷心菜","可可果","空心菜","苦瓜","梨","李子","荔枝","莲藕","莲雾","菱角","榴莲","龙眼","芦荟","芦笋","萝卜","绿豆芽",
						"马铃薯","芒果","毛丹","毛豆","弥猴桃","蘑菇","魔芋","木耳","木瓜","南瓜","柠檬","欧查果","枇杷","苹果","葡萄","葡萄柚","奇异果",
						"茄子","芹菜","青椒","青柠檬","秋葵","人参果","桑椹","沙糖桔","山梨","山药","山芋","山楂","山竹","蛇果","圣女果","石榴","柿子","柿子椒",
						"水蜜桃","丝瓜","四季豆","松子","粟子","桃","甜菜","土豆","豌豆","莴苣","无花果","芜菁","西瓜","西红柿","西葫芦","西柚","鲜枣",
						"香菜","香菇","香瓜","香蕉","香梨","杏","雪里红","杨桃","洋葱","椰枣","椰子","银杏","樱桃","油桃","柚子","玉米","芋头","越蔓橘",
						"枣","榨菜","榛子","紫菜" };
				int a = (int) Math.abs(firsname.length * Math.random());
				int b = (int) Math.abs(namelist.length * Math.random());
				nickName = firsname[a] + namelist[b];
			}
			return nickName;
		}

//	/**
//	 * 绑定微信开放平台openid
//	 * @param mid 会员id	
//	 * @param openOpenid 开放平台openid
//	 * @return 0 绑定成功
//	 * 		   1 用户id不存在
//	 *         2 将绑定的openOpenid已经存在，请先解除绑定
//	 */
//	public Integer transBindWechatOpen(Long mid, String openOpenid)
//	{
//		Member member = memberMapper.selectByPrimaryKey(mid);
//		if(member == null)
//		{//用户id不存在
//			return 1;
//		}
//		
//		MemberWechat mw0 = obtainWechatByOpenOpenid(openOpenid);
//		if(mw0 != null)
//		{//将绑定的openOpenid已经存在，请先解除绑定 。或者进一步合并会员信息
//			return 2;
//		}
//		
//		MemberWechat mw1 = obtainWechatByMid(mid);
//		if(mw1 == null)
//		{//微信用户记录不存在，创建新纪录。
//			mw1 = new MemberWechat();
//			mw1.setMid(mid);
//			mw1.setOpenOpenid(openOpenid);
//			//TODO : 从微信服务器获取用户基本信息
//			mw1.setCreateTime(new Date());
//			memberWechatMapper.insertSelective(mw1);
//		}else
//		{
//			mw1.setOpenOpenid(openOpenid);
//			mw1.setModifyTime(new Date());
//			memberWechatMapper.updateByPrimaryKeySelective(mw1);
//		}
//		
//		//在会员记录中保存openid，兼容遗留代码中的问题，部分代码从会员记录获取openid
//		//重构完成后可以去除
//		member.setOpenid(openOpenid);
//		member.setModifyTime(new Date());
//		memberMapper.updateByPrimaryKeySelective(member);
//		
//		return 0;
//	}
	
	/**
	 * 微信用户创建会员记录
	 * @param memberWechat
	 * @return
	 */
	public Member transCreateMemberByWechat(MemberWechat memberWechat)
	{
		//创建会员记录
		Member member = new Member();
		member.setNickname(memberWechat.getNickname());
		member.setSex(memberWechat.getSex());
		member.setOpenid(memberWechat.getPublicOpenid()); //通过微信添加用户时有openId
		//头像需要先保存微信头像
		if(memberWechat.getHeadimgurl() != null) {
			member.setAvatar(memberWechat.getHeadimgurl());
		}
		//member.setAvatarThumbnail(avatarThumbnail);
		//TODO : 注册IM账号
		member.setRegTime(new Date());
		memberMapper.insertSelective(member);
		
		//创建微信会员记录
		memberWechat.setMid(member.getMid());
		//TODO : 从微信服务器获取unionid
		//memberWechat.setUnionid(unionid);
		memberWechat.setCreateTime(new Date());
		memberWechatMapper.insertSelective(memberWechat);
		
		return member;
	}
}
