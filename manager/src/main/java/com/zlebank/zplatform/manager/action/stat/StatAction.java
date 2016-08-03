package com.zlebank.zplatform.manager.action.stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.action.merch.DownloadAction;
import com.zlebank.zplatform.manager.bean.RiskAnalyseLogQueryBean;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;
import com.zlebank.zplatform.manager.bean.stat.EntryCountRequest;
import com.zlebank.zplatform.manager.bean.stat.TradeStatRequest;
import com.zlebank.zplatform.manager.dao.object.TradeStatModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.IRiskAnalyseLogService;
import com.zlebank.zplatform.manager.service.iface.IStatService;

public class StatAction extends BaseAction {

    public final static String DEFAULT_TIME_STAMP_FROMAT1 = "yyyy-MM-dd HH:mm";
    
    HttpServletRequest request =ServletActionContext.getRequest();
    private static final Log log = LogFactory.getLog(DownloadAction.class);
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6766439996672692042L;

    private TradeStatRequest tradeStatRequest;
    
    private TxnsLogBean tlb;
    
    private EntryCountRequest entryCountRequest;
    @Autowired
    private IStatService statService;

    public String showTradeStat() {
        return "tradeStat";
    }
    
    public String showentryCount() {
        return "entryCount";
    }

    public String tradeStat() {
        if (tradeStatRequest == null) {
            tradeStatRequest = new TradeStatRequest();
        }
        List<TradeStatModel> result = statService
                .queryTradeStat(tradeStatRequest);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("total", result.size());
        resultMap.put("rows", result);
        json_encode(resultMap);

        return null;
    }
    
    public String entryCount() {
        if (entryCountRequest == null) {
            entryCountRequest = new EntryCountRequest();
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = statService.entryCount(entryCountRequest,getPage(),getRows());
        json_encode(resultMap);

        return null;
    }
//*******************************************************风控规则报表*******************************************************************
//    private RiskAnalyseLogModel riskAnalyseLogModel;
    
    private RiskAnalyseLogQueryBean riskAnalyseLogQueryBean;
    
    private ServiceContainer serviceContainer;
       
    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }

    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }

    public String showRiskControlRules(){
        return "riskRulesControl";
    }

    public RiskAnalyseLogQueryBean getRiskAnalyseLogQueryBean() {
        return riskAnalyseLogQueryBean;
    }

    public void setRiskAnalyseLogQueryBean(RiskAnalyseLogQueryBean riskAnalyseLogQueryBean) {
        this.riskAnalyseLogQueryBean = riskAnalyseLogQueryBean;
    }

    /**
     * 根据查询条件(规则类型、规则编号、商户号\会员号\银行卡号、日期)查询
     * @return
     */
    public Map<String, Object> queryRiskRulesControl(){
        if(riskAnalyseLogQueryBean==null){
            riskAnalyseLogQueryBean = new RiskAnalyseLogQueryBean();
        }
        String roletype = request.getParameter("roletype");
        
        Map<String, Object> resultMap = serviceContainer.getRiskAnalyseLogService().
                queryRiskRulesControl(this.getPage(),this.getRows(),riskAnalyseLogQueryBean,roletype);
        json_encode(resultMap);
        return null;        
    }
    
    /**根据规则类型查询规则编号以及对应的规则描述
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String showRulecodeList(){
        String roletype = request.getParameter("roletype");
        IRiskAnalyseLogService riskAnalyseLogService = serviceContainer.getRiskAnalyseLogService();
        if(riskAnalyseLogService!=null){
            try {
                List<Map<String, Object>> resultlist = (List<Map<String, Object>>) riskAnalyseLogService.showRulecodeList(roletype);
                json_encode(resultlist);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;      
    }
    /**
     * 导出商户规则交易报表
     * @return
     * @throws WriteException 
     * @throws RowsExceededException 
     * @throws IOException 
     */
    public void exportMerchExcel() throws RowsExceededException, WriteException, IOException{
        if(riskAnalyseLogQueryBean==null){
            riskAnalyseLogQueryBean = new RiskAnalyseLogQueryBean();
        }
        String roletype = request.getParameter("roletype");
        riskAnalyseLogQueryBean.setUser(null);
        Map<String, Object> resultMap = serviceContainer.getRiskAnalyseLogService().
                queryAllRiskRulesControl(this.getPage(),this.getRows(),riskAnalyseLogQueryBean,roletype);
        createMerchExcel(resultMap);
    }
    /**
     * 导出个人钱包账户安全规则报表
     * @throws WriteException 
     * @throws UnsupportedEncodingException 
     * @throws RowsExceededException 
     */
    public void exportPersonExcel() throws RowsExceededException, UnsupportedEncodingException, WriteException{
        if(riskAnalyseLogQueryBean==null){
            riskAnalyseLogQueryBean = new RiskAnalyseLogQueryBean();
        }
        String roletype = request.getParameter("roletype");
        riskAnalyseLogQueryBean.setUser(null);
        Map<String, Object> resultMap = serviceContainer.getRiskAnalyseLogService().
                queryAllRiskRulesControl(this.getPage(),this.getRows(),riskAnalyseLogQueryBean,roletype);
        if(resultMap!=null){
            createPersonExcel(resultMap);
        }else {
            log.info("查询结果为空，导出失败");
        }
       
    }
    
    /**
     *导出账户规则报表
     * @throws UnsupportedEncodingException 
     * @throws WriteException 
     * @throws RowsExceededException 
     */
    public void exportAccountExcel() throws RowsExceededException, WriteException, UnsupportedEncodingException{
//      String flag = request.getParameter("flag");
        if(riskAnalyseLogQueryBean==null){
            riskAnalyseLogQueryBean = new RiskAnalyseLogQueryBean();
        }
        String roletype = request.getParameter("roletype");
        riskAnalyseLogQueryBean.setUser(null);
        Map<String, Object> resultMap = serviceContainer.getRiskAnalyseLogService().
                queryAllRiskRulesControl(this.getPage(),this.getRows(),riskAnalyseLogQueryBean,roletype);
        createAccountExcel(resultMap);
    }

    /**
     * 按照表头创建账户的excel
     * @param resultMap
     * @throws RowsExceededException
     * @throws WriteException
     * @throws UnsupportedEncodingException
     */
    private void createAccountExcel(Map<String, Object> resultMap) throws RowsExceededException, WriteException, UnsupportedEncodingException {
        log.info("开始创建excel:");
        HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setContentType("application/vnd.ms-excel;charset=utf-8"); 
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("账户安全规则.xls", "UTF-8")); 
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(response.getOutputStream());
            // 生成名为"交易规则"的工作表，参数0表示这是第一页
            WritableSheet sheet = workbook.createSheet("交易规则",0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为规则编号，依次做此操作
            Label label1 = new Label(0,0,"触犯规则编号");
            Label label2 = new Label(1,0,"支付卡号");
            Label label3 = new Label(2,0,"触犯规则类");
            Label label4 = new Label(3,0,"日期");
            Label label5 = new Label(4,0,"数值");
            Label label6 = new Label(5,0,"写入时间");
            Label label7 = new Label(6,0,"备注");
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
            sheet.addCell(label7);
      
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rowList = (List<Map<String, Object>>) resultMap.get("rows");
            if(rowList!=null && rowList.size()!= 0){
                for(int i= 0;i<rowList.size();i++){
                    //触犯规则编号 支付卡号      触犯规则类      日期       数值                   写入时间  备注
                    //ROLECODE MEMBERID ROLENAME DATES  PARAMETER  INTIME NOTES
                    Label labelone = new Label(0,i+1,(String) rowList.get(i).get("ROLECODE"));
                    Label labeltwo = new Label(1,i+1,(String) rowList.get(i).get("MEMBERID"));
                    Label labelthree = new Label(2,i+1,(String) rowList.get(i).get("ROLENAME"));
                    //日期
                    Label labelfour = new Label(3,i+1,(String) rowList.get(i).get("DATES"));
                    //数值
                    BigDecimal bigDecimal=  (BigDecimal) rowList.get(i).get("PARAMETER");               
                    Label labelfive = new Label(4, i+1, String.valueOf( bigDecimal.intValue()));
                    //写入时间
                    Date date = (Date) rowList.get(i).get("INTIME");
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    Label labelsix = new Label(5,i+1,(String) dateStr);
                    //备注
                    Label labelseven = new Label(6,i+1,(String) rowList.get(i).get("NOTES"));                 
                    sheet.addCell(labelone);
                    sheet.addCell(labeltwo);
                    sheet.addCell(labelthree);
                    sheet.addCell(labelfour);
                    sheet.addCell(labelfive);
                    sheet.addCell(labelsix);
                    sheet.addCell(labelseven);                   
                }
                
            } 
          //写入数据并关闭文件 
            workbook.write(); 
            workbook.close();
        } catch (IOException e) {
            log.info("创建excel失败:"+e.getMessage());
            e.printStackTrace();
        }   
        
    }

    /**
     * 按照表头创建个人的excel
     * @param resultMap
     * @throws UnsupportedEncodingException
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void createPersonExcel(Map<String, Object> resultMap) throws UnsupportedEncodingException, RowsExceededException, WriteException {
        log.info("开始创建excel:");
        HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setContentType("application/vnd.ms-excel;charset=utf-8");      
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("个人钱包账户规则.xls", "UTF-8")); 
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(response.getOutputStream());
            // 生成名为"交易规则"的工作表，参数0表示这是第一页
            WritableSheet sheet = workbook.createSheet("交易规则",0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为规则编号，依次做此操作
            Label label1 = new Label(0,0,"会员编号");
            Label label2 = new Label(1,0,"触犯规则编号");
            Label label3 = new Label(2,0,"姓名");
            Label label4 = new Label(3,0,"身份证号");
            Label label5 = new Label(4,0,"注册手机号");
            Label label6 = new Label(5,0,"触犯规则类");
            Label label7 = new Label(6,0,"日期");
            Label label8 = new Label(7,0,"数值");
            Label label9 = new Label(8,0,"写入时间");
            Label label10 = new Label(9,0,"备注");
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
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rowList = (List<Map<String, Object>>) resultMap.get("rows");
            if(rowList!=null && rowList.size()!= 0){
                for(int i= 0;i<rowList.size();i++){
                  //会员编号                触犯规则编号  姓名                      身份证号 注册手机号  触犯规则类      日期      数值                   写入时间  备注
                  // MEMBERID    ROLECODE  member_name  idnum phone   ROLENAME  DATES PARAMETER  INTIME NOTES
                    Label labelone = new Label(0,i+1,(String) rowList.get(i).get("MEMBERID"));
                    Label labeltwo = new Label(1,i+1,(String) rowList.get(i).get("ROLECODE"));
                    Label labelthree = new Label(2,i+1,(String) rowList.get(i).get("member_name"));
                    Label labelfour = new Label(3,i+1,(String) rowList.get(i).get("idnum"));
                    Label labelfive = new Label(4,i+1,(String) rowList.get(i).get("phone"));
                    Label labelsix = new Label(5,i+1,(String) rowList.get(i).get("ROLENAME"));
                    //日期
                    Label labelseven = new Label(6,i+1,(String) rowList.get(i).get("DATES"));
                    //数值
                    BigDecimal bigDecimal=  (BigDecimal) rowList.get(i).get("PARAMETER"); 
                    Label labeleight = new Label(7, i+1, String.valueOf( bigDecimal.intValue()));
                    //写入时间
                    Date date = (Date) rowList.get(i).get("INTIME");
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);               
                    Label labelnine= new Label(8,i+1,(String) dateStr);
                    //备注
                    Label labelten = new Label(9,i+1,(String) rowList.get(i).get("NOTES"));               
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
                }
            }
            //写入数据并关闭文件 
            workbook.write(); 
            workbook.close();
        } catch (IOException e) {
            log.info("创建excel失败:"+e.getMessage());
            e.printStackTrace();
        }   
        
    }

   /***
    * 按照表头创建商户的excel
    * @param resultMap
    * @throws RowsExceededException
    * @throws WriteException
    * @throws IOException
    */
    private void createMerchExcel(Map<String, Object> resultMap) throws RowsExceededException, WriteException, IOException { 
        log.info("开始创建excel:");
        HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setContentType("application/vnd.ms-excel;charset=utf-8"); 
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("商户交易规则.xls", "UTF-8")); 
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(response.getOutputStream());
            // 生成名为"交易规则"的工作表，参数0表示这是第一页
            WritableSheet sheet = workbook.createSheet("交易规则",0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为规则编号，依次做此操作
            Label label1 = new Label(0,0,"触犯规则编号");
            Label label2 = new Label(1,0,"商户ID");
            Label label3 = new Label(2,0,"交易类型");
            Label label4 = new Label(3,0,"触犯规则类");
            Label label5 = new Label(4,0,"日期");
            Label label6 = new Label(5,0,"数值");
            Label label7 = new Label(6,0,"写入时间");
            Label label8 = new Label(7,0,"备注");
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
            sheet.addCell(label7);
            sheet.addCell(label8);
            
            
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rowList = (List<Map<String, Object>>) resultMap.get("rows");
            if(rowList!=null && rowList.size()!= 0){
                for(int i= 0;i<rowList.size();i++){
                    //触犯规则编号   商户ID     交易类型 触犯规则类   日期       数值                  写入时间  备注
                    //ROLECODE  MEMBERID  消费       ROLENAME DATES PARAMETER  INTIME NOTES
                    Label labelone = new Label(0,i+1,(String) rowList.get(i).get("ROLECODE"));
                    Label labeltwo = new Label(1,i+1,(String) rowList.get(i).get("MEMBERID"));
                    Label labelthree = new Label(2,i+1,"消费");
                    Label labelfour = new Label(3,i+1,(String) rowList.get(i).get("ROLENAME"));
                   
                    Label labelfive = new Label(4,i+1,(String) rowList.get(i).get("DATES"));
                    BigDecimal bigDecimal=  (BigDecimal) rowList.get(i).get("PARAMETER");               
                    Label labelsix = new Label(5, i+1, String.valueOf( bigDecimal.intValue()));
                    
                    Date date = (Date) rowList.get(i).get("INTIME");
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    Label labelseven = new Label(6,i+1,(String) dateStr);
                    Label labeleight = new Label(7,i+1,(String) rowList.get(i).get("NOTES"));
                    
                    sheet.addCell(labelone);
                    sheet.addCell(labeltwo);
                    sheet.addCell(labelthree);
                    sheet.addCell(labelfour);
                    sheet.addCell(labelfive);
                    sheet.addCell(labelsix);
                    sheet.addCell(labelseven);
                    sheet.addCell(labeleight); 
                }
                
            } 
          //写入数据并关闭文件 
            workbook.write(); 
            workbook.close();
        } catch (IOException e) {
            log.info("创建excel失败:"+e.getMessage());
            e.printStackTrace();
        }   
    }

    public TradeStatRequest getTradeStatRequest() {
        return tradeStatRequest;
    }
    public void setTradeStatRequest(TradeStatRequest tradeStatRequest) {
        this.tradeStatRequest = tradeStatRequest;
    }

    public EntryCountRequest getEntryCountRequest() {
        return entryCountRequest;
    }

    public void setEntryCountRequest(EntryCountRequest entryCountRequest) {
        this.entryCountRequest = entryCountRequest;
    }
}

