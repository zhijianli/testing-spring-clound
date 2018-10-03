package com.xinmao.sc.orderCenter.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinmao.sc.orderCenter.api.TestScaleOrderTitleApi;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderDimensionNormMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderMessage;
import com.xinmao.sc.orderCenter.domain.TestScaleOrderTitleMessage;
import com.github.pagehelper.PageHelper;
import com.xinmao.sc.entity.ErrorCode;
import com.xinmao.sc.entity.ResultEntity;
import com.xinmao.sc.orderCenter.service.TestScaleOrderDimensionNormService;
import com.xinmao.sc.orderCenter.service.TestScaleOrderService;
import com.xinmao.sc.orderCenter.service.TestScaleOrderTitleService;
import com.xinmao.sc.orderCenter.service.TestScaleTitleService;
import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
import com.xinmao.sc.testCenter.domain.TestScaleOptionMessage;
import com.xinmao.sc.testCenter.domain.TestScaleTitleMessage;


@RestController
//@RequestMapping("testScaleOrderTitle")
public class TestScaleOrderTitleController implements TestScaleOrderTitleApi{
	
//	Logger log = Logger.getLogger(this.getClass());
	private static final Logger log = LogManager.getLogger();

    @Autowired
    private TestScaleOrderTitleService service;
    
    @Autowired
    private TestScaleOrderService tsoService;
    
    @Autowired
    private TestScaleTitleService tstService;
    
    @Autowired
    private TestScaleOrderDimensionNormService tsodnService;


    
    @RequestMapping(value="/getMessageById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getMessageById(HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleOrderTitleMessage> list = new ArrayList<TestScaleOrderTitleMessage>();
    	
    	try{
    		
	        int id =Integer.parseInt(request.getParameter("id"));
	        list = service.getMessageById(id);
    	
       	}catch(Exception e){
       		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("contentList", list);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }

    
    @RequestMapping(value="/exportOrderTitleMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public void exportOrderTitleMessage(TestScaleOrderTitleMessage tsotMessage, HttpServletResponse response){
        
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleOrderTitleMessage> list = new ArrayList<TestScaleOrderTitleMessage>();
    	String userName = "";
    	String testScaleName = "";
    	Integer testScaleOrderId = 0;
    	try{
    		testScaleOrderId =tsotMessage.getTestScaleOrderId();
		    if(testScaleOrderId==null || testScaleOrderId==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return;
		    }
  	        
  	        
  	        //根据订单id获取相关的题目选项快照信息
    		list = service.getAllMessage(tsotMessage);
    		if(list!=null&&list.size()>0){
    			
    			//获取订单信息
    			TestScaleOrderMessage tsOrderMessage = tsoService.getMessageById(testScaleOrderId);
    			if(tsOrderMessage!=null){
    				userName = tsOrderMessage.getUserName();
    				testScaleName = tsOrderMessage.getRelateTestScaleName();
    				
    			}
    			
	    		response.setContentType("application/vnd.ms-excel");  
	    		String codedFileName;
	    		try
	    		{
	    			codedFileName = java.net.URLEncoder.encode("心理测试流水详情", "UTF-8");
	    		} catch (UnsupportedEncodingException e1)
	    		{
	    			codedFileName = "TestScaleOrderTitleList";
	    		} 
	    		
	    		response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xlsx");  
	    				
	    		OutputStream  fOut = null;
	    		try{
	    			Workbook wb = new XSSFWorkbook();
//	    	        CreationHelper createHelper = wb.getCreationHelper();
	    	        Sheet sheet = wb.createSheet("TSOTLIST");
	    	        
	    	        //设置列宽
	    	        sheet.setColumnWidth(0, 20*256);
	    	        sheet.setColumnWidth(1, 20*256);
	    	        sheet.setColumnWidth(2, 20*256);
	    	        sheet.setColumnWidth(3, 20*256);
	    	        sheet.setColumnWidth(4, 20*256);
//	    	        sheet.setColumnWidth(5, 20*256);
//	    	        sheet.setColumnWidth(6, 20*256);
//	    	        sheet.setColumnWidth(7, 20*256);
//	    	        sheet.setColumnWidth(8, 50*256);
//	    	        sheet.setColumnWidth(9, 20*256);
	    	        
	    	        
	    	        //填写列名
	    	        Row row = sheet.createRow((short)0);
	    	        row.createCell(0).setCellValue("用户昵称");
	    	        row.createCell(1).setCellValue("所测量表");
	    	        row.createCell(2).setCellValue("所测题目");
	    	        row.createCell(3).setCellValue("所选选项");
	    	        row.createCell(4).setCellValue("测试时间");
	    	        
	    	        //填写信息
	    	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    	       
	    	        for(int i=0; i< list.size(); i++)
	    	        {
	    	        	TestScaleOrderTitleMessage tMess = list.get(i);
	    	        	row = sheet.createRow(i+1);
		    	        row.setHeightInPoints(50);
		    	        if(userName!=null){
		    	        	row.createCell(0).setCellValue(userName);
		    	        }else{
		    	        	row.createCell(0).setCellValue("");
		    	        }
	    		        row.createCell(1).setCellValue(testScaleName);
	    		        row.createCell(2).setCellValue(tMess.getProblemWord()+"  \r\n"+tMess.getProblemPicSrc());
	    		        row.createCell(3).setCellValue(tMess.getOptionWord()+"  \r\n"+tMess.getOptionPicSrc());
	    		        row.createCell(4).setCellValue(sdf.format(tMess.getCreateTime()));
	    	        }
	    	        	       	        
	    	        fOut = response.getOutputStream();  
	    	        wb.write(fOut);
	    		}catch (Exception e)
	    		{
	    			log.error(ErrorCode.ERROR_EXPORT_TEST_SCALE_ORDER_TITLE.getMessage(), e);
	    			result.setCode(ErrorCode.ERROR_EXPORT_TEST_SCALE_ORDER_TITLE.getCode());
	    			result.setMsg(ErrorCode.ERROR_EXPORT_TEST_SCALE_ORDER_TITLE.getMessage());
	    		}finally
	    		{
	    			if(fOut != null)
	    			{
	    				try
	    				{
	    					fOut.flush();
	    					fOut.close();
	    				} catch (IOException e)
	    				{
	    					// TODO Auto-generated catch block
	    					log.error(ErrorCode.ERROR_EXPORT_TEST_SCALE_ORDER_TITLE.getMessage(), e);
	    				}
	    			}
	    		}	
    			
    		}

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return;
    	}
    	
//    	result.put("count", count);
//		result.setCode(ErrorCode.SUCCESS.getCode());
//		result.setMsg(ErrorCode.SUCCESS.getMessage());
//		result.put("userName", userName);
//		result.put("testScaleOrderId", testScaleOrderId);
//		result.put("testScaleName", testScaleName);
//		result.put("contentList", list);
    }
    
    @RequestMapping(value="/getAllMessage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object getAllMessage(TestScaleOrderTitleMessage tsotMessage){
        
    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleOrderTitleMessage> list = new ArrayList<TestScaleOrderTitleMessage>();
    	int count = 0;
    	int testOrderTitleNum = 0;
    	String userName = "";
    	String testScaleName = "";
    	Integer testScaleOrderId = 0;
    	try{
    		testScaleOrderId =tsotMessage.getTestScaleOrderId();
    		Integer pageIndex = tsotMessage.getPageIndex();
    		Integer pageSize = tsotMessage.getPageSize();
		    if(testScaleOrderId==null || testScaleOrderId==0|| 
		    		pageIndex==null || pageIndex==0 || 
		    		pageSize==null || pageSize==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
  	        
  	        
  	        //根据订单id获取相关的题目选项快照信息
  	        PageHelper.startPage(pageIndex, pageSize);
  	        
    		list = service.getAllMessage(tsotMessage);
    		if(list!=null&&list.size()>0){
    			
    			//获取订单信息
    			TestScaleOrderMessage tsOrderMessage = tsoService.getMessageById(testScaleOrderId);
    			if(tsOrderMessage!=null){
    				userName = tsOrderMessage.getUserName();
    				testScaleName = tsOrderMessage.getRelateTestScaleName();
    				
    			}
    			
    			TestScaleOrderTitleMessage tsotMess = new TestScaleOrderTitleMessage();
    			tsotMess.setTestScaleOrderId(testScaleOrderId);
    			testOrderTitleNum = service.selectCount(tsotMess);
    			
    			count = list.size();
    			
    		}

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("count", count);
    	result.put("testOrderTitleNum", testOrderTitleNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		result.put("userName", userName);
		result.put("testScaleOrderId", testScaleOrderId);
		result.put("testScaleName", testScaleName);
		result.put("contentList", list);
		return result;
    }
    
 
    public TestScaleOrderMessage getChooseTitleAndOption(@RequestBody TestScaleOrderTitleMessage tsotMessage){
        
    	List<TestScaleOrderTitleMessage> chooseList = new ArrayList<TestScaleOrderTitleMessage>();
    	Integer testScaleOrderId = 0;
    	int testOrderTitleNum = 0;
    	TestScaleOrderMessage tsoMessage = new TestScaleOrderMessage();
    	try{
    		testScaleOrderId =tsotMessage.getTestScaleOrderId();
    		Integer pageIndex = tsotMessage.getPageIndex();
    		Integer pageSize = tsotMessage.getPageSize();
		    if(testScaleOrderId==null || testScaleOrderId==0|| 
		    		pageIndex==null || pageIndex==0 || 
		    		pageSize==null || pageSize==0){
		   		log.error("传的参数为空：订单id="+testScaleOrderId+",页码="+pageIndex+",每页数量="+pageSize);
				return null;
		    }
		    
		    //根据订单id查询量表信息，判断此订单是否为空
		    tsoMessage = tsoService.getMessageById(testScaleOrderId);
		    if(tsoMessage==null){
		   		log.error("该订单在数据库不存在，订单id="+testScaleOrderId);
				return null;
		    }
  	        
  	        //根据订单id获取相关的题目选项快照信息
  	        PageHelper.startPage(pageIndex, pageSize);
  	        
  	        //获取题目选项信息List
  	        chooseList = service.getAllMessage(tsotMessage);
    		if(chooseList==null||chooseList.size()==0){
    			log.error("题目选项为空：订单id="+testScaleOrderId+",页码="+pageIndex+",每页数量="+pageSize);
    			return null;
    		}
    		
    		//获取该订单对应的维度常模信息
    		TestScaleOrderDimensionNormMessage tsodnMessage = new TestScaleOrderDimensionNormMessage();
    		tsodnMessage.setTestScaleOrderId(testScaleOrderId);
    		List<TestScaleOrderDimensionNormMessage>  tsodnList = tsodnService.getAllMessage(tsodnMessage);
    		
    		//获取该订单下题目数量
			TestScaleOrderTitleMessage tsotMess = new TestScaleOrderTitleMessage();
			tsotMess.setTestScaleOrderId(testScaleOrderId);
			testOrderTitleNum = service.selectCount(tsotMess);
			
			tsoMessage.setTestScaleTitleNum(testOrderTitleNum);
			tsoMessage.setTsotList(chooseList);
			tsoMessage.setTsodnList(tsodnList);

    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			return null;
    	}
    	
		return tsoMessage;
    }
    
    
    @RequestMapping(value="/addTitle",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addTitle(HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	List<TestScaleOrderTitleMessage> list = new ArrayList<TestScaleOrderTitleMessage>();
    	
    	try{
    		
	        int id =Integer.parseInt(request.getParameter("id"));
	        list = service.getMessageById(id);
    	
       	}catch(Exception e){
       		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	
    	result.put("contentList", list);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
        
    }
    
    @RequestMapping(value = "add",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object addMessage(TestScaleOrderTitleMessage testScaleOrderTitleMessage , HttpServletRequest request){
    	ResultEntity result = new ResultEntity(); 
    	try{
	       
	        String problemWord = testScaleOrderTitleMessage.getProblemWord();
	        String problemPicSrc = testScaleOrderTitleMessage.getProblemPicSrc();
	        String optionWord = testScaleOrderTitleMessage.getOptionWord();
	        String optionPicSrc = testScaleOrderTitleMessage.getOptionPicSrc();
		    if(problemWord==null || problemWord.isEmpty() ||
		       problemPicSrc==null || problemPicSrc.isEmpty() ||
		       optionWord==null || optionWord.isEmpty() ||
		       optionPicSrc==null || optionPicSrc.isEmpty()){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
		    }
		    Date currentTime = new Date();
	        testScaleOrderTitleMessage.setIsDelete(0);
	        testScaleOrderTitleMessage.setIsEnable(0);
	        testScaleOrderTitleMessage.setCreateTime(currentTime);
	        testScaleOrderTitleMessage.setUpdateTime(currentTime);
	     	
	        service.addMessage(testScaleOrderTitleMessage);
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("testScaleOrderTitleMessageId", testScaleOrderTitleMessage.getId());
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }

    
    @RequestMapping(value = "/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object updateMessage(TestScaleOrderTitleMessage testScaleOrderTitleMessage,HttpServletRequest request){

    	ResultEntity result = new ResultEntity(); 
    	int updateNum = 0;
    	
    	try{
	        
	        Integer id = testScaleOrderTitleMessage.getId();
	        if(id==null || id==0){
		   		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	        }
	        
	        Date currentTime = new Date();
	        testScaleOrderTitleMessage.setUpdateTime(currentTime);
	    	
	        updateNum = service.updateMess(testScaleOrderTitleMessage);
	        
	    	if(updateNum==0){
	    		result.setCode(ErrorCode.ERROR_PARAM_INCOMPLETE.getCode());
				result.setMsg(ErrorCode.ERROR_PARAM_INCOMPLETE.getMessage());
				return result;
	    	}
    	}catch(Exception e){
    		log.error(ErrorCode.ERROR_SYSTEM_ERROR.getMessage(), e);
			result.setCode(ErrorCode.ERROR_SYSTEM_ERROR.getCode());
			result.setMsg(e.getMessage());
			return result;
    	}
    	result.put("updateNum", updateNum);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMessage());
		return result;
    }
   
    
}

