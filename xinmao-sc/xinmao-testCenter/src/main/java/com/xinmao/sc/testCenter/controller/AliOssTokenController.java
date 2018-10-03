
package com.xinmao.sc.testCenter.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@WebServlet(asyncSupported = true)

@RestController
@RequestMapping("aliOssToken")
public class AliOssTokenController extends HttpServlet{
 
	
	private static final Logger log = LogManager.getLogger();
	
    private static final long serialVersionUID = 5522372203700422672L;
    
    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    
    public static final String STS_API_VERSION = "2015-04-01";
	
    private static final  String ACCESS_KEY_ID = "LTAIe50PsT1CPEZd";
	
	private static final  String ACCESS_KEY_SECRET = "qI8ePOlOUCDvBpthRcOT4E7tSB0zrG";
	
	private static final  String ROLE_ARN = "acs:ram::1594130670229353:role/testscalerole";
	
    private static final long DURATION_SECONDS = 1000L;
    
    @RequestMapping(value="/getOssToken",method=RequestMethod.POST)
    public Object getOssToken(HttpServletRequest request, HttpServletResponse response){
    	ResultEntity result = new ResultEntity();
    	try{
    		doGet(request, response,result);
//    		response.addHeader("Access-Control-Allow-Origin", "*");
//    	    response.setHeader("Access-Control-Allow-Headers", "Authentication");
    		return result;
    	}catch(Exception e){
			log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return result;
    	}
    }
    
    
    
    
    protected AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
            String roleSessionName, String policy, ProtocolType protocolType, long durationSeconds) throws ClientException 
    {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);

            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(durationSeconds);

            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);

            return response;
        } catch (ClientException e) {
            throw e;
        }
    }

    public static String ReadJson(String path){
        //从给定位置获取文件
        File file = new File(path);
        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭文件流
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response,ResultEntity result)
            throws ServletException, IOException {


//        String data = ReadJson("./config.json");
//        System.out.println("用户输入url:" + data);
//        if (data.equals(""))
//        {
//            response(request, response, "./config.json is empty or not found");
//            return;
//        }
//        System.out.println(data);
//        JSONObject jsonObj  = JSONObject.fromObject(data);


        // 只有 RAM用户（子账号）才能调用 AssumeRole 接口
        // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
        // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
//        String accessKeyId = jsonObj.getString("AccessKeyID");
//        String accessKeySecret = jsonObj.getString("AccessKeySecret");

        // RoleArn 需要在 RAM 控制台上获取
//        String roleArn = jsonObj.getString("RoleArn");
//        long durationSeconds = jsonObj.getLong("TokenExpireTime");
//        String policy = ReadJson(jsonObj.getString("PolicyFile"));
        
    	//看一下阿里云或者网上那边是否有config.json这个文件，有的话直接看一下里面的数据就行了
        String accessKeyId = this.ACCESS_KEY_ID;
        String accessKeySecret = this.ACCESS_KEY_SECRET;
        String roleArn = this.ROLE_ARN;
        long durationSeconds = this.DURATION_SECONDS;
        String policy = null;
        
        // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
        // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
        // 具体规则请参考API文档中的格式要求
        String roleSessionName = "alice-001";

        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;

        try {
            final AssumeRoleResponse stsResponse = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName,
                    policy, protocolType, durationSeconds);

//            Map<String, String> respMap = new LinkedHashMap<String, String>();
            result.put("status", "200");
            result.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
            result.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
            result.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
            result.put("Expiration", stsResponse.getCredentials().getExpiration());

//            JSONObject ja1 = JSONObject.fromObject(result);
//            response(request, response, ja1.toString());

        } catch (ClientException e) {

//            Map<String, String> respMap = new LinkedHashMap<String, String>();
        	result.put("status", e.getErrCode());
        	result.put("AccessKeyId", "");
        	result.put("AccessKeySecret", "");
        	result.put("SecurityToken", "");
        	result.put("Expiration", "");         
//            JSONObject ja1 = JSONObject.fromObject(result);
//            response(request, response, ja1.toString());
        }

    }


//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
//        doGet(request, response);
//    }

    private void response(HttpServletRequest request, HttpServletResponse response, String results) throws IOException {
        String callbackFunName = request.getParameter("callback");
        if (callbackFunName==null || callbackFunName.equalsIgnoreCase(""))
            response.getWriter().println(results);
        else
            response.getWriter().println(callbackFunName + "( "+results+" )");
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
    }
}