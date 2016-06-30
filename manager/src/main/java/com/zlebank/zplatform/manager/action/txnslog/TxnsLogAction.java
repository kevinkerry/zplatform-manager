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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





















import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.PasswordException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.service.BusinessServiec;
import com.zlebank.zplatform.commons.bean.PagedResult;
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
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
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
        return "import";
    }
    
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
        variables.put("accsecmerno ", txnsLogModel.getAccsecmerno());
//        variables.put("stime", txnsLogModel.getPan());
//        variables.put("etime", txnsLogModel.getPan());
        variables.put("payrettsnseqno", txnsLogModel.getPayrettsnseqno());
        variables.put("retcode", txnsLogModel.getRetcode());
        variables.put("accmemberid ", txnsLogModel.getAccmemberid());
        variables.put("userId", getCurrentUser().getUserId());
       

        Map<String, Object> groupList = txnsLogsService.findPersonTxnsLogs(variables,getPage(),getRows());
        json_encode(groupList);
        return null;
    }
    
    
    public void exportAll() throws WriteException{
        Map<String, Object> variables = new HashMap<String, Object>();
        if (txnsLogModel == null) {
            txnsLogModel = new TxnsLogModel();
        }
        
        variables.put("txnseqno", txnsLogModel.getTxnseqno());
        variables.put("busicode", txnsLogModel.getBusicode());
        variables.put("pan", txnsLogModel.getPan());
        variables.put("accordno", txnsLogModel.getAccordno());
        variables.put("accsecmerno ", txnsLogModel.getAccsecmerno());
//        variables.put("stime", txnsLogModel.getPan());
//        variables.put("etime", txnsLogModel.getPan());
        variables.put("payrettsnseqno", txnsLogModel.getPayrettsnseqno());
        variables.put("retcode", txnsLogModel.getRetcode());
        variables.put("accmemberid ", txnsLogModel.getAccmemberid());
        variables.put("userId", getCurrentUser().getUserId());
       

        Map<String, Object> groupList = txnsLogsService.findPersonTxnsLogsOfAllPage(variables);
        exportExcel(groupList);
    }

    private void exportExcel(Map<String,Object> map) throws  WriteException {
 
        // 打开文件
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File("D:/交易流水.xls"));

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
            List<Map<String, Object>> rowslist = (List<Map<String, Object>>) map.get("rows");
            int totalList =  (Integer)map.get("total");
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

    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }

    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }
    
}
