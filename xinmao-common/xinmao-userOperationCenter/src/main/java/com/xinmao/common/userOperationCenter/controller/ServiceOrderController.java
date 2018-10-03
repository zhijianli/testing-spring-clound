package com.xinmao.common.userOperationCenter.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

//import com.alibaba.fastjson.JSON;
//import com.depression.model.*;
//import com.depression.service.*;
//import com.depression.utils.IMUtil;

import org.apache.log4j.Logger;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CreationHelper;
//import org.apache.poi.ss.usermodel.DataFormat;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.depression.base.ucpaas.DateUtil;
//import com.depression.entity.ErrorCode;
//import com.depression.entity.OrderState;
//import com.depression.model.web.dto.WebServiceOrderDTO;
//import com.depression.service.TagService;
//import com.depression.utils.BigDecimalUtil;
//import com.depression.utils.PropertyUtils;
//import com.depression.utils.SmsUtil;
import com.xinmao.common.entity.ResultEntity;
import com.xinmao.common.userOperationCenter.domain.ServiceCallRecord;
import com.xinmao.common.userOperationCenter.domain.ServiceOrder;
import com.xinmao.common.userOperationCenter.service.ServiceCallRecordService;
import com.xinmao.common.userOperationCenter.service.ServiceOrderService;
import com.xinmao.common.entity.ErrorCode;

/**
 * 下载咨询师录音
 * 
 * @author admin
 * 
 */
@Controller
@RequestMapping("/ServiceOrder")
public class ServiceOrderController
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	ServiceCallRecordService serviceCallRecordService;
	@Autowired
	ServiceOrderService mServiceOrderService;
	
	
	/**
	 * 获取订单录音名称--测试用
	 * @param soid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/getRecordNameByTest.json")
	@ResponseBody
	public Object  getRecordNameByTest(Long serviceProviderId){
		ResultEntity result = new ResultEntity();
		result.setCode(ErrorCode.SUCCESS.getCode());
		
//		System.out.println("-----getRecordNameByTest Start-----");
		log.error("-----getRecordNameByTest Start-----");
		
		ServiceOrder soRecord = new ServiceOrder();
		soRecord.setServiceProviderId(serviceProviderId);
		List<ServiceOrder> soList = mServiceOrderService.selectSelective(soRecord);
		
		for(int i =0;i<soList.size();i++){
			
			
//			if(i<=580){
//				continue;
//			}
			
			ServiceOrder so = soList.get(i);
			Long soid = so.getSoid();
			List<String> recordName = new ArrayList<String>();
			ServiceCallRecord record =  new ServiceCallRecord();
			record.setServiceOrderId(soid);
			List<ServiceCallRecord> serviceCallRecords = serviceCallRecordService.selectSelective(record);
			if(serviceCallRecords.size()>0){
				for(ServiceCallRecord call : serviceCallRecords){
					if(call.getRecordUrl()!=null&&call.getRecordUrl().contains("@")){
//						recordName.add(call.getRecordUrl().split("@")[1]);
						String recordUrlKey = call.getRecordUrl().split("@")[1];
						System.out.println("-----getRecordNameByTest start-----recordUrlKey = "+recordUrlKey+",i = "+i);
						this.fileDowload(recordUrlKey);
						System.out.println("-----getRecordNameByTest Success-----recordUrlKey = "+recordUrlKey+",i = "+i);
					}
				}
			}
			if(recordName.size()==0){
				log.error("该订单没有录音文件,soid = "+ soid);
			}
			
		
//			System.out.println("-----getRecordNameByTest Success-----i = "+i);
			
		}
		log.error("-----getRecordNameByTest Success-----");
		return result;
		
	}
	
	
	public void fileDowload(String recordUrlKey) {

		// 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        try {
        	System.out.println("-----Start-----");
        	URL url = new URL("https://file.120xinmao.com/"+recordUrlKey+".wav");
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("D:/我的资料/心猫公司资料/music/"+recordUrlKey+".wav");
//            FileOutputStream fs = new FileOutputStream("/home/microService/music/"+recordUrlKey+".wav");
 
            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
//                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
            System.out.println("-----Success-----");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
}
