package com.xinmao.common.util.wechat;

//import net.sf.json.JSONObject;
//import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.commons.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.FileItemFactory;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * User: 李志坚
 * Date: 2019/10/5
 * 微信安全检查工具类
 */
public class SecurityCheckUtil
{

	public static String GET_ACCESS_TOKEN_OAUTH = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public static String CHECK_TEXT_URL = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=ACCESSTOKEN";

	/**
	 *  纯文本拦截敏感词
	 * @param textConetnt
	 * @return
	 */
	public static Boolean checkText(String textConetnt,String accessToken) {

		try {
			String url = CHECK_TEXT_URL.replace("ACCESSTOKEN", accessToken);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = null;

//			String accessToken = MiniProgram.getAccessToken(1);
			HttpPost request = new HttpPost(url);
			request.addHeader("Content-Type", "application/json;charset=UTF-8");

			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("content", textConetnt);
			request.setEntity(new StringEntity(JSONObject.toJSONString(paramMap).toString(), ContentType.create("application/json", "utf-8")));

			response = httpclient.execute(request);
			HttpEntity httpEntity = response.getEntity();
			String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
			JSONObject jso = JSONObject.parseObject(result);


			Object errcode = jso.get("errcode");
			//int errCode = (int) errcode;
			int errCode = Integer.parseInt(errcode+"");
			if (errCode == 0) {
				return true;
			} else if (errCode == 87014) {
				System.out.println("内容违规-----------" + textConetnt);
				return false;
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----------------调用腾讯内容过滤系统出错------------------");
			return true;
		}
	}


	/**
	 *  恶意图片过滤
	 * @param multipartFile
	 * @return
	 */
	public static Boolean checkPic(MultipartFile multipartFile, String accessToken) {
		try {

			CloseableHttpClient httpclient = HttpClients.createDefault();

			CloseableHttpResponse response = null;

			HttpPost request = new HttpPost("https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + accessToken);
			request.addHeader("Content-Type", "application/octet-stream");

			InputStream inputStream = multipartFile.getInputStream();

			byte[] byt = new byte[inputStream.available()];
			inputStream.read(byt);
			request.setEntity(new ByteArrayEntity(byt, ContentType.create("image/jpg")));

			response = httpclient.execute(request);
			HttpEntity httpEntity = response.getEntity();
			String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
//			JSONObject jso = JSONObject.parseObject(result);
			JSONObject jso = JSONObject.parseObject(result);
			System.out.println(jso + "-------------验证效果");

			Object errcode = jso.get("errcode");
//			int errCode = (int) errcode;
			int errCode = Integer.parseInt(errcode+"");
			if (errCode == 0) {
				return true;
			} else if (errCode == 87014) {
				System.out.println("图片内容违规-----------");
				return false;
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----------------调用腾讯内容过滤系统出错------------------");
			return true;
		}
	}


	public static String getAccessToken() {

		String accessTokent = null;

		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			CloseableHttpResponse response = null;

			String url = GET_ACCESS_TOKEN_OAUTH.replace("APPID", "wx64e17f58ebba5d24").replace("APPSECRET", "5ebf51ee4a0d1f0c8fa565f71b194324");

			HttpPost request = new HttpPost(url);
			request.addHeader("Content-Type", "application/json;charset=UTF-8");

			response = httpclient.execute(request);
			HttpEntity httpEntity = response.getEntity();
			String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
			JSONObject jso = JSONObject.parseObject(result);

//			Object errcode = jso.get("errcode");
//			int errCode = Integer.parseInt(errcode+"");
//			if (errCode == 0) {
				accessTokent = jso.getString("access_token");
//			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----------------调用腾讯内容过滤系统出错------------------");
		}


		return accessTokent;

	}


	public static MultipartFile getMulFileByPath(String picPath) {

//		FileItem fileItem = createFileItem(picPath);
//		MultipartFile mfile = new CommonsMultipartFile(fileItem);

		MultipartFile mfile = null;
		URL url = null;
		try {
			url = new URL(picPath);
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			mfile = new MockMultipartFile("temp.jpg","temp.jpg","", inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mfile;

	}



	private static FileItem createFileItem(String filePath) {

		FileItemFactory factory = new DiskFileItemFactory(16, null);
		String textFieldName = "textField";
		int num = filePath.lastIndexOf(".");
		String extFile = filePath.substring(num);
		FileItem item = factory.createItem(textFieldName, "text/plain", true,
				"MyFileName" + extFile);
		File newfile = new File(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[4096];

		try {
			FileInputStream fis = new FileInputStream(newfile);
			OutputStream os = item.getOutputStream();

			while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			fis.close();
		}

		catch (IOException e)

		{
			e.printStackTrace();
		}

		return item;

	}

}
