/* 
 * TxnsLogAction.java  
 * 
 * version 
 *
 * 2015年11月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.txnslog;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.service.BusinessServiec;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;
import com.zlebank.zplatform.manager.service.iface.ITxnsLogsService;
import com.zlebank.zplatform.trade.model.TxnsLogModel;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月17日 上午11:22:39
 * @since
 */
public class TxnsLogAction extends BaseAction {
    public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String SIMPLE_TIME_FROMAT = "HH:mm:ss";
    public final static String SIMPLE_DATE_FROMAT = "yyyy-MM-dd";
    public final static String DEFAULT_TIME_STAMP_FROMAT1 = "yyyy-MM-dd HH:mm";
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private static final Log log = LogFactory.getLog(TxnsLogAction.class);
    @Autowired
    private ITxnsLoService txns;
    @Autowired
    public BusinessServiec business;
    private TxnsLogBean tlb;
    public List<Business> bus;   
    private ServiceContainer serviceContainer;
    
    private TxnsLogModel txnsLogModel;
    @Autowired
    @Qualifier("txnsLogsServiceImpl")
    private ITxnsLogsService txnsLogsService;
    
    public TxnsLogModel getTxnsLogModel() {
        return txnsLogModel;
    }

    public void setTxnsLogModel(TxnsLogModel txnsLogModel) {
        this.txnsLogModel = txnsLogModel;
    }

    public List<Business> getBus() {
        return bus;
    }

    public void setBus(List<Business> bus) {
        this.bus = bus;
    }
    
    public TxnsLogBean getTlb() {
        return tlb;
    }

    public void setTlb(TxnsLogBean tlb) {
        this.tlb = tlb;
    }

    /**
     * 请求页面
     * 
     * @return
     */
    public String getTxnsLog() {
        bus = business.getAllBusiness();
        return SUCCESS;
    }

    /**
     * 查询交易流水
     */
    public void getTxnsLoByTxnsBean(){
        int page=  this.getPage();
        int pageSize= this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        PagedResult<TxnsLog> pr  =  txns.queryPaged(page, pageSize, tlb);
        try {
            List<TxnsLog> li=pr.getPagedResult();
          
            Long total = pr.getTotal();
            
            map.put("total", total);
            map.put("rows", li);
            json_encode(map);
        
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        
    }
    
    public void getTxnsLogByTxnseqno(){
        String messg=null;    
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> li=txns.getTxnsLogById(tlb.getTxnseqno());
           if(li==null||li.isEmpty()){
                messg="交易数据不存在";
                map.put("messg", messg);
           }else {
             map.put("json",li.get(0));
           }
           json_encode( map);
       
    
    }
    /**
     * 查询个人钱包账户即个人登录后的所以交易流水
     */
    public String importTxnsLog(){   
        return "exportAllOfPerson";
    }
    /**
     * 查询所有交易流水
     * @return
     */
    public String exportAllTxnsLog(){
        return "exportAll";
    }
    
    //**********************************************个人交易流水导出：查询个人的所有交易流水，并且可以进行导出*****************************************************************************
    /**
     * 个人会员的所有交易流水分页查询
     */
    public String getPersonTxnsLogs(){
        Map<String, Object> variables = new HashMap<String, Object>();
        if (txnsLogModel == null) {
            txnsLogModel = new TxnsLogModel();
        }       
        variables.put("txnseqno", txnsLogModel.getTxnseqno());
        variables.put("busicode", txnsLogModel.getBusicode());
        variables.put("pan", txnsLogModel.getPan());
        variables.put("accordno", txnsLogModel.getAccordno());
        variables.put("accmemberid", txnsLogModel.getAccmemberid());
        variables.put("accsecmerno", txnsLogModel.getAccsecmerno());
        variables.put("payrettsnseqno", txnsLogModel.getPayrettsnseqno());
        variables.put("retcode", txnsLogModel.getRetcode());        
        variables.put("userId", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsLogsService.findPersonTxnsLogs(variables,getPage(),getRows());
        json_encode(groupList);
        return null;
    }
    
    
    /**
     * 导出个人所有交易流水
     * @throws WriteException
     * @throws UnsupportedEncodingException
     */
    public void exportAll() throws WriteException, UnsupportedEncodingException{
        Map<String, Object> variables = new HashMap<String, Object>();
        if (txnsLogModel == null) {
            txnsLogModel = new TxnsLogModel();
        }
        
        variables.put("txnseqno", txnsLogModel.getTxnseqno());
        variables.put("busicode", txnsLogModel.getBusicode());
        variables.put("pan", txnsLogModel.getPan());
        variables.put("accordno", txnsLogModel.getAccordno());
        variables.put("accsecmerno ", txnsLogModel.getAccsecmerno());
        variables.put("payrettsnseqno", txnsLogModel.getPayrettsnseqno());
        variables.put("retcode", txnsLogModel.getRetcode());
        variables.put("accmemberid ", txnsLogModel.getAccmemberid());
        variables.put("userId", getCurrentUser().getUserId());
       

        Map<String, Object> groupList = txnsLogsService.findPersonTxnsLogsOfAllPage(variables);
        exportExcel(groupList);
    }

    /**
     * 导出个人所有交易流水为excel的具体的创建excel的方法
     * @param map
     * @throws WriteException
     * @throws UnsupportedEncodingException
     */
    private void exportExcel(Map<String,Object> map) throws  WriteException, UnsupportedEncodingException {
 
        
        HttpServletResponse response = ServletActionContext.getResponse();   
        response.setContentType("application/vnd.ms-excel;charset=utf-8");      
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("交易流水.xls", "UTF-8")); 
        // 打开文件
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(response.getOutputStream());

            // 生成名为"交易流水"的工作表，参数0表示这是第一页
            WritableSheet sheet = workbook.createSheet("交易流水",0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为商户名称，依次做此操作
            Label label1 = new Label(0, 0,"商户名称");              
            Label label2 = new Label(1,0,"商户编号");
            Label label3 = new Label(2,0,"会员编号");
            Label label4 = new Label(3,0,"银行卡号");
            Label label5 = new Label(4,0,"商户订单编号");
            Label label6 = new Label(5,0,"交易流水");       
            Label label7 = new Label(6,0,"交易时间");
            Label label8 = new Label(7,0,"交易金额");
            Label label9 = new Label(8,0,"扣率版本号");
            Label label10 = new Label(9,0,"手续费");
            Label label11 = new Label(10,0,"原交易流水号");
            Label label12 = new Label(11,0,"应答流水号");
            Label label13 = new Label(12,0,"交易类型");
            Label label14 = new Label(13,0,"交易渠道");
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
            sheet.addCell(label7);
            sheet.addCell(label8);
            sheet.addCell(label9);
            sheet.addCell(label10);
            sheet.addCell(label11);
            sheet.addCell(label12);
            sheet.addCell(label13);
            sheet.addCell(label14);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rowslist = (List<Map<String, Object>>) map.get("rows");
            for(int i = 1;i <= rowslist.size();i++){                
                    Label labelOne = new Label(0,i,(String) rowslist.get(i-1).get("MEMBER_NAME"));
                    Label labelTwo = new Label(1,i,(String) rowslist.get(i-1).get("ACCSECMERNO"));
                    Label labelThree = new Label(2,i,(String) rowslist.get(i-1).get("ACCMEMBERID"));
                    Label labelFour = new Label(3,i,(String) rowslist.get(i-1).get("PAN"));
                    Label labelFive = new Label(4,i,(String) rowslist.get(i-1).get("ACCORDNO"));
                    Label labelSix = new Label(5,i,(String) rowslist.get(i-1).get("TXNSEQNO"));
                    Label labelSeven = new Label(6,i,(String) rowslist.get(i-1).get("RETDATETIME"));
                    Label labelEight = new Label(7,i, (String) rowslist.get(i-1).get("AMOUNT"));
                    Label labelNine = new Label(8,i,(String) rowslist.get(i-1).get("FEEVER"));
                    Label labelTen = new Label(9,i, (String) rowslist.get(i-1).get("TXNFEE"));
                    Label labelEleven = new Label(10,i,(String) rowslist.get(i-1).get("TXNSEQNO_OG"));
                    Label labelTwelve = new Label(11,i,(String) rowslist.get(i-1).get("PAYRETTSNSEQNO"));
                    Label labelThirteen = new Label(12,i,(String) rowslist.get(i-1).get("BUSINAME"));
                    Label labelFourteen = new Label(13,i,(String) rowslist.get(i-1).get("CHNLNAME"));
                    sheet.addCell(labelOne);
                    sheet.addCell(labelTwo);
                    sheet.addCell(labelThree);
                    sheet.addCell(labelFour);
                    sheet.addCell(labelFive);
                    sheet.addCell(labelSix);
                    sheet.addCell(labelSeven);
                    sheet.addCell(labelEight);
                    sheet.addCell(labelNine);
                    sheet.addCell(labelTen);
                    sheet.addCell(labelEleven);
                    sheet.addCell(labelTwelve);
                    sheet.addCell(labelThirteen);
                    sheet.addCell(labelFourteen);   
            }
          //写入数据并关闭文件 
            workbook.write(); 
            workbook.close();    
        } catch (IOException e) {            
            e.printStackTrace();
        }   
    }
    
    //************************************************交易流水导出：查询所有成功的交易流水并且导出*********************************************************************
    /**
     * 查询(快捷消费、充值、退款)的所有交易流水
     * @return
     * @throws ParseException 
     */
    public String queryAllSuccess() throws ParseException {
        Map<String, Object> variables = new HashMap<String, Object>();
        if (tlb == null) {
            tlb = new TxnsLogBean();
        }
        variables.put("busicode", tlb.getBusicode());
        variables.put("accsecmerno", tlb.getAccsecmerno());
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String commitimes = "" ;
        String commitimen = "" ;
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimes())){
            commitimes = format.format(DateUtil.convertToDate(tlb.getAccordcommitimes(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimen())){
            commitimen = format.format(DateUtil.convertToDate(tlb.getAccordcommitimen(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        variables.put("accordcommitimes", commitimes);
        variables.put("accordcommitimen",commitimen);
        variables.put("user", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsLogsService.queryAllSuccess(variables,getPage(),getRows());
        json_encode(groupList);
        return null;
        
    }
    /**
     * 查询提现的所有交易
     * @throws ParseException 
     */
    public String  queryWithdrawals() throws ParseException{
        Map<String, Object> variables = new HashMap<String, Object>();
        if (tlb == null) {
            tlb = new TxnsLogBean();
        }       
        variables.put("accsecmerno", tlb.getAccsecmerno());
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String commitimes = "" ;
        String commitimen = "" ;
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimes())){
            commitimes = format.format(DateUtil.convertToDate(tlb.getAccordcommitimes(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimen())){
            commitimen = format.format(DateUtil.convertToDate(tlb.getAccordcommitimen(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        variables.put("accordcommitimes", commitimes);
        variables.put("accordcommitimen",commitimen);
        variables.put("user", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsLogsService.queryWithdrawals(variables,getPage(),getRows());
        json_encode(groupList);
        return null;
    }
    
    /**
     * 查询代付的所有交易
     * @throws ParseException 
     */
    public String queryInsteadPay() throws ParseException{
        Map<String, Object> variables = new HashMap<String, Object>();
        if (tlb == null) {
            tlb = new TxnsLogBean();
        }
        variables.put("accsecmerno", tlb.getAccsecmerno());
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String commitimes = "" ;
        String commitimen = "" ;
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimes())){
            commitimes = format.format(DateUtil.convertToDate(tlb.getAccordcommitimes(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimen())){
            commitimen = format.format(DateUtil.convertToDate(tlb.getAccordcommitimen(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        variables.put("accordcommitimes", commitimes);
        variables.put("accordcommitimen",commitimen);
        variables.put("user", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsLogsService.queryInsteadPay(variables,getPage(),getRows());
        json_encode(groupList);
        return null;
    }
    //**************导出开始****************
    
    /**
     * 导出所有成功的交易流水
     * @throws ParseException 
     * @throws IOException 
     */
    public void exportAllSuccess() throws ParseException, IOException{
        Map<String, Object> variables = new HashMap<String, Object>();
        if (tlb == null) {
            tlb = new TxnsLogBean();
        }
        variables.put("busicode", tlb.getBusicode());
        variables.put("accsecmerno", tlb.getAccsecmerno());
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String commitimes = "" ;
        String commitimen = "" ;
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimes())){
            commitimes = format.format(DateUtil.convertToDate(tlb.getAccordcommitimes(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimen())){
            commitimen = format.format(DateUtil.convertToDate(tlb.getAccordcommitimen(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        variables.put("accordcommitimes", commitimes);
        variables.put("accordcommitimen",commitimen);
        variables.put("user", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsLogsService.queryAllCrr(variables);
        createSuccessExcel(groupList);
    }
    private void createSuccessExcel(Map<String, Object> groupList) throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();   
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream outputStream ;
        outputStream = response.getOutputStream();       
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("交易流水.xls", "UTF-8"));        
        WritableWorkbook workbook = null;
        try {
            workbook  = Workbook.createWorkbook(outputStream);
            WritableSheet sheet = workbook.createSheet("交易流水", 0);
            Label label1 = new Label(0, 0, "商户名称");
            Label label2 = new Label(1, 0, "商户编号");
            Label label3 = new Label(2, 0, "银行卡号");
            Label label4 = new Label(3, 0, "商户订单编号");
            Label label5 = new Label(4, 0, "交易流水号");
            Label label6 = new Label(5, 0, "交易时间");
            Label label7 = new Label(6, 0, "交易金额");
            Label label8 = new Label(7, 0, "手续费");
            Label label9 = new Label(8, 0, "原交易流水号");
            Label label10 = new Label(9, 0, "应答流水号");
            Label label11 = new Label(10, 0, "交易类型");
            Label label12 = new Label(11, 0, "交易渠道");
            try {
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);           
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
                sheet.addCell(label9);
                sheet.addCell(label10);
                sheet.addCell(label11);
                sheet.addCell(label12);
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }   
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listOfResult = (List<Map<String, Object>>) groupList.get("rows");
            for (int i= 0 ;i<listOfResult.size();i++){
                //商户名称 商户编号 银行卡号 商户订单编号  交易流水号  交易时间 交易金额 手续费 原交易流水号 应答流水号 交易类型 交易渠道
                Label labelone = new Label(0, i+1, (String) listOfResult.get(i).get("MEMBER_NAME"));
                Label labeltwo = new Label(1, i+1, (String) listOfResult.get(i).get("ACCSECMERNO"));
                Label labelthree = new Label(2, i+1, (String) listOfResult.get(i).get("PAN"));
                Label labelfour = new Label(3, i+1, (String) listOfResult.get(i).get("ACCORDNO"));
                Label labelfive = new Label(4, i+1, (String) listOfResult.get(i).get("TXNSEQNO"));
                Label labelsix = new Label(5, i+1, (String) listOfResult.get(i).get("ACCORDCOMMITIME"));
                Label labelseven = new Label(6, i+1, (String) listOfResult.get(i).get("AMOUNT"));
                Label labeleight = new Label(7, i+1, (String) listOfResult.get(i).get("TXNFEE"));
                Label labelnine = new Label(8, i+1, (String) listOfResult.get(i).get("PAYORDNO"));
                Label labelten = new Label(9, i+1, (String) listOfResult.get(i).get("PAYRETTSNSEQNO"));
                Label labeleleven = new Label(10, i+1, (String) listOfResult.get(i).get("BUSINAME"));
                Label labeltwlve = new Label(11, i+1, (String) listOfResult.get(i).get("CHNLNAME"));
                sheet.addCell(labelone);
                sheet.addCell(labeltwo);
                sheet.addCell(labelthree);
                sheet.addCell(labelfour);
                sheet.addCell(labelfive);
                sheet.addCell(labelsix);
                sheet.addCell(labelseven);
                sheet.addCell(labeleight);
                sheet.addCell(labelnine);
                sheet.addCell(labelten);
                sheet.addCell(labeleleven);
                sheet.addCell(labeltwlve);
            }
            //写入数据并关闭文件 
            workbook.write(); 
            workbook.close(); 
        } catch (IOException e) { 
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {         
            e.printStackTrace();
        }finally{
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

    /**
     * 导出提现的交易为excel
     * @throws IOException 
     * @throws ParseException 
     */
    public void exportAllWithdrawals() throws IOException, ParseException{
        Map<String, Object> variables = new HashMap<String, Object>();
        if (tlb == null) {
            tlb = new TxnsLogBean();
        }       
        variables.put("accsecmerno", tlb.getAccsecmerno());
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String commitimes = "" ;
        String commitimen = "" ;
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimes())){
            commitimes = format.format(DateUtil.convertToDate(tlb.getAccordcommitimes(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimen())){
            commitimen = format.format(DateUtil.convertToDate(tlb.getAccordcommitimen(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        variables.put("accordcommitimes", commitimes);
        variables.put("accordcommitimen",commitimen);
        variables.put("user", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsLogsService.queryAllWithdrawals(variables);
        exportWithdrawalsExcel(groupList);
    }
    
    /**
     * 导出代付的交易为excel
     * @throws IOException
     * @throws ParseException 
     */
    public void exportAllInsteadPay() throws IOException, ParseException{
        Map<String, Object> variables = new HashMap<String, Object>();
        if (tlb == null) {
            tlb = new TxnsLogBean();
        }       
        variables.put("accsecmerno", tlb.getAccsecmerno());
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String commitimes = "" ;
        String commitimen = "" ;
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimes())){
            commitimes = format.format(DateUtil.convertToDate(tlb.getAccordcommitimes(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimen())){
            commitimen = format.format(DateUtil.convertToDate(tlb.getAccordcommitimen(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        variables.put("accordcommitimes", commitimes);
        variables.put("accordcommitimen",commitimen);
        variables.put("user", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsLogsService.queryAllInsteadPay(variables);
        exportInsteadPayExcel(groupList);
        
    }

    /**
     * 导出快捷消费、充值、退款的交易记录为excel
     * @throws IOException 
     * @throws ParseException 
     */
    public void exportAllCrr() throws IOException, ParseException{
        HttpServletRequest request = ServletActionContext.getRequest();
        String flag=  request.getParameter("flag");
        String busicode = request.getParameter("busicode");
        Map<String, Object> variables = new HashMap<String, Object>();
        if (tlb == null) {
            tlb = new TxnsLogBean();
        } 
        variables.put("busicode", busicode);//交易类型
        variables.put("accsecmerno", tlb.getAccsecmerno());//商户号
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String commitimes = "" ;
        String commitimen = "" ;
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimes())){
            commitimes = format.format(DateUtil.convertToDate(tlb.getAccordcommitimes(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        if(StringUtil.isNotEmpty(tlb.getAccordcommitimen())){
            commitimen = format.format(DateUtil.convertToDate(tlb.getAccordcommitimen(), DEFAULT_TIME_STAMP_FROMAT1));
        }
        variables.put("accordcommitimes", commitimes);//开始时间
        variables.put("accordcommitimen",commitimen);//结束时间
        variables.put("user", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsLogsService.queryAllCrr(variables);
        exportCrrExcel(groupList,flag);
    }


    //***************导出结束********************
    
    /**
     * 按规定创建快捷消费、充值、退款的excel
     * @param groupList
     * @throws IOException
     */
    private void exportCrrExcel(Map<String, Object> groupList,String flag) throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();   
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream outputStream ;
        outputStream = response.getOutputStream(); 
        if(flag.equals("1")){
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("快捷消费表.xls", "UTF-8"));
        }else if (flag.equals("2")){
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("充值表.xls", "UTF-8"));
        }else{
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("退款表.xls", "UTF-8"));
        }
        
                
        WritableWorkbook workbook = null;
        try {
            workbook  = Workbook.createWorkbook(outputStream);
            WritableSheet sheet ;
            if(flag.equals("1")){
                sheet  = workbook.createSheet("快捷消费", 0);
            }else if(flag.equals("2")){
                sheet = workbook.createSheet("充值", 0);
            }else {
                sheet = workbook.createSheet("退款", 0);
            }
            
           
            Label label1 = new Label(0, 0, "商户名称");
            Label label2 = new Label(1, 0, "商户编号");
            Label label3 = new Label(2, 0, "银行卡号");
            Label label4 = new Label(3, 0, "商户订单编号");
            Label label5 = new Label(4, 0, "交易流水号");
            Label label6 = new Label(5, 0, "交易时间");
            Label label7 = new Label(6, 0, "交易金额(元)");
            Label label8 = new Label(7, 0, "手续费(元)");
            Label label9 = new Label(8, 0, "交易类型");
            Label label10 = new Label(9, 0, "支付流水号");
            Label label11 = new Label(10, 0, "应答流水号");
            Label label12 = new Label(11, 0, "交易渠道");
            try {
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);           
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
                sheet.addCell(label9);
                sheet.addCell(label10);
                sheet.addCell(label11);
                sheet.addCell(label12);
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }   
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listOfResult = (List<Map<String, Object>>) groupList.get("rows");
            for (int i= 0 ;i<listOfResult.size();i++){
                Label labelone = new Label(0, i+1, (String) listOfResult.get(i).get("MEMBER_NAME"));
                Label labeltwo = new Label(1, i+1, (String) listOfResult.get(i).get("ACCSECMERNO"));
                Label labelthree = new Label(2, i+1, (String) listOfResult.get(i).get("PAN"));
                Label labelfour = new Label(3, i+1, (String) listOfResult.get(i).get("ACCORDNO"));
                Label labelfive = new Label(4, i+1, (String) listOfResult.get(i).get("TXNSEQNO"));
                Label labelsix = new Label(5, i+1, (String) listOfResult.get(i).get("ACCORDCOMMITIME"));
                Label labelseven = new Label(6, i+1, (String) listOfResult.get(i).get("AMOUNT"));
                Label labeleight = new Label(7, i+1, (String) listOfResult.get(i).get("TXNFEE"));
                Label labelnine = new Label(8, i+1, (String) listOfResult.get(i).get("BUSINAME"));
                Label labelten = new Label(9, i+1, (String) listOfResult.get(i).get("PAYORDNO"));
                Label labeleleven = new Label(10, i+1, (String) listOfResult.get(i).get("PAYRETTSNSEQNO"));
                Label labeltwlve = new Label(11, i+1, (String) listOfResult.get(i).get("CHNLNAME"));               
                sheet.addCell(labelone);
                sheet.addCell(labeltwo);
                sheet.addCell(labelthree);
                sheet.addCell(labelfour);
                sheet.addCell(labelfive);
                sheet.addCell(labelsix);
                sheet.addCell(labelseven);
                sheet.addCell(labeleight);
                sheet.addCell(labelnine);
                sheet.addCell(labelten);
                sheet.addCell(labeleleven);
                sheet.addCell(labeltwlve);                
            }
            //写入数据并关闭文件 
            workbook.write(); 
            workbook.close(); 
        } catch (IOException e) { 
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {         
            e.printStackTrace();
        }finally{
            if(outputStream!=null){
                outputStream.close();
            }
        }
        
    }

    /**
     * 按规定创建代付的excel
     * @param groupList
     * @throws IOException 
     */
    private void exportInsteadPayExcel(Map<String, Object> groupList) throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();   
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream outputStream ;
        outputStream = response.getOutputStream();       
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("代付表.xls", "UTF-8"));        
        WritableWorkbook workbook = null;
        try {
            workbook  = Workbook.createWorkbook(outputStream);
            WritableSheet sheet = workbook.createSheet("代付", 0);
            Label label1 = new Label(0, 0, "商户名称");
            Label label2 = new Label(1, 0, "商户订单编号");
            Label label3 = new Label(2, 0, "交易流水号");
            Label label4 = new Label(3, 0, "代付流水号");
            Label label5 = new Label(4, 0, "代付批次号");
            Label label6 = new Label(5, 0, "划拨流水号");
            Label label7 = new Label(6, 0, "划拨批次号");
            Label label8 = new Label(7, 0, "转账流水号");
            Label label9 = new Label(8, 0, "转账批次号");
            Label label10 = new Label(9, 0, "交易时间");
            Label label11 = new Label(10, 0, "交易金额(元)");
            Label label12 = new Label(11, 0, "手续费(元)");
            Label label13 = new Label(12, 0, "交易渠道");
            try {
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);           
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
                sheet.addCell(label9);
                sheet.addCell(label10);
                sheet.addCell(label11);
                sheet.addCell(label12);
                sheet.addCell(label13);
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }   
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listOfResult = (List<Map<String, Object>>) groupList.get("rows");
            for (int i= 0 ;i<listOfResult.size();i++){
                Label labelone = new Label(0, i+1, (String) listOfResult.get(i).get("MEMBER_NAME"));
                Label labeltwo = new Label(1, i+1, (String) listOfResult.get(i).get("ORDER_ID"));
                Label labelthree = new Label(2, i+1, (String) listOfResult.get(i).get("TXNSEQNO"));
                Label labelfour = new Label(3, i+1, (String) listOfResult.get(i).get("INSTEAD_PAY_DATA_SEQ_NO"));
                Label labelfive = new Label(4, i+1, (String) listOfResult.get(i).get("INSTEAD_PAY_BATCH_SEQ_NO"));
                Label labelsix = new Label(5, i+1, (String) listOfResult.get(i).get("TRAN_DATA_SEQ_NO"));
                Label labelseven = new Label(6, i+1, (String) listOfResult.get(i).get("TRAN_BATCH_NO"));
                Label labeleight = new Label(7, i+1, (String) listOfResult.get(i).get("BANK_TRAN_DATA_SEQ_NO"));
                Label labelnine = new Label(8, i+1, (String) listOfResult.get(i).get("BANK_TRAN_BATCH_NO"));
                Label labelten = new Label(9, i+1, (String) listOfResult.get(i).get("PAYORDCOMTIME"));
                Label labeleleven = new Label(10, i+1, (String) listOfResult.get(i).get("AMOUNT"));
                Label labeltwlve = new Label(11, i+1, (String) listOfResult.get(i).get("TXNFEE"));
                Label labelthirteen = new Label(12, i+1, (String) listOfResult.get(i).get("CHNLNAME"));
                sheet.addCell(labelone);
                sheet.addCell(labeltwo);
                sheet.addCell(labelthree);
                sheet.addCell(labelfour);
                sheet.addCell(labelfive);
                sheet.addCell(labelsix);
                sheet.addCell(labelseven);
                sheet.addCell(labeleight);
                sheet.addCell(labelnine);
                sheet.addCell(labelten);
                sheet.addCell(labeleleven);
                sheet.addCell(labeltwlve);
                sheet.addCell(labelthirteen);
            }
            //写入数据并关闭文件 
            workbook.write(); 
            workbook.close(); 
        } catch (IOException e) { 
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {         
            e.printStackTrace();
        }finally{
            if(outputStream!=null){
                outputStream.close();
            }
        }
        
    }
    
    /**
     * 按规定创建提现的excel
     * @param groupList
     * @throws IOException 
     */
    private void exportWithdrawalsExcel(Map<String, Object> groupList) throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();   
        response.setContentType("application/vnd.ms-excel;charset=utf-8");             
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("提现表.xls", "UTF-8"));
        OutputStream outputStream = response.getOutputStream();
        WritableWorkbook workbook = null;
        try {
            workbook  = Workbook.createWorkbook(outputStream);
            WritableSheet sheet = workbook.createSheet("提现", 0);
            Label label1 = new Label(0, 0, "商户名称");
            Label label2 = new Label(1, 0, "商户订单编号");
            Label label3 = new Label(2, 0, "交易流水号");
            Label label4 = new Label(3, 0, "提现订单号");
            Label label5 = new Label(4, 0, "划拨流水号");
            Label label6 = new Label(5, 0, "划拨批次号");
            Label label7 = new Label(6, 0, "转账流水号");
            Label label8 = new Label(7, 0, "转账批次号");
            Label label9 = new Label(8, 0, "交易时间");
            Label label10 = new Label(9, 0, "交易金额(元)");
            Label label11 = new Label(10, 0, "手续费(元)");
            Label label12 = new Label(11, 0, "交易渠道");
            try {
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);           
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
                sheet.addCell(label9);
                sheet.addCell(label10);
                sheet.addCell(label11);
                sheet.addCell(label12);
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }   
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listOfResult = (List<Map<String, Object>>) groupList.get("rows");
            for (int i= 0 ;i<listOfResult.size();i++){
                Label labelone = new Label(0, i+1, (String) listOfResult.get(i).get("MEMBER_NAME"));
                Label labeltwo = new Label(1, i+1, (String) listOfResult.get(i).get("GATEWAYORDERNO"));
                Label labelthree = new Label(2, i+1, (String) listOfResult.get(i).get("TXNSEQNO"));
                Label labelfour = new Label(3, i+1, (String) listOfResult.get(i).get("WITHDRAWORDERNO"));
                Label labelfive = new Label(4, i+1, (String) listOfResult.get(i).get("TRAN_DATA_SEQ_NO"));
                Label labelsix = new Label(5, i+1, (String) listOfResult.get(i).get("TRAN_BATCH_NO"));
                Label labelseven = new Label(6, i+1, (String) listOfResult.get(i).get("BANK_TRAN_DATA_SEQ_NO"));
                Label labeleight = new Label(7, i+1, (String) listOfResult.get(i).get("BANK_TRAN_BATCH_NO"));
                Label labelnine = new Label(8, i+1, (String) listOfResult.get(i).get("PAYORDCOMTIME"));
                Label labelten = new Label(9, i+1, (String) listOfResult.get(i).get("AMOUNT"));
                Label labeleleven = new Label(10, i+1, (String) listOfResult.get(i).get("TXNFEE"));
                Label labeltwlve = new Label(11, i+1, (String) listOfResult.get(i).get("CHNLNAME"));
                sheet.addCell(labelone);
                sheet.addCell(labeltwo);
                sheet.addCell(labelthree);
                sheet.addCell(labelfour);
                sheet.addCell(labelfive);
                sheet.addCell(labelsix);
                sheet.addCell(labelseven);
                sheet.addCell(labeleight);
                sheet.addCell(labelnine);
                sheet.addCell(labelten);
                sheet.addCell(labeleleven);
                sheet.addCell(labeltwlve);
            }
            //写入数据并关闭文件 
            workbook.write(); 
            workbook.close(); 
        } catch (IOException e) { 
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {         
            e.printStackTrace();
        }finally{
            if(outputStream!=null){
                outputStream.close();
            }
        }
    }

    //*******************************************************************************************************************************************
    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }

    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }
    
}
