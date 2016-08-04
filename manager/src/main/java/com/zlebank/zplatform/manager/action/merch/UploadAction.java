package com.zlebank.zplatform.manager.action.merch;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.exception.IllegalEntryRequestException;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.bean.CmbcResfileBean;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.dao.object.UploadLogModel;
import com.zlebank.zplatform.manager.dao.object.scan.ChannelFileMode;
import com.zlebank.zplatform.manager.exception.ResolveReconFileContentException;
import com.zlebank.zplatform.manager.service.WeChatReconFileService;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.CheckFileService;
import com.zlebank.zplatform.manager.service.iface.IChannelFileService;
import com.zlebank.zplatform.manager.service.iface.ICmbcResfileService;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.trade.service.ITxnsLogService;

public class UploadAction extends BaseAction {
    private static final Log log = LogFactory.getLog(UploadAction.class);
    
    /**
	 * 
	 */
    private static final long serialVersionUID = -3963094287299215112L;
    private File[] upload;
    private String[] uploadFileName;
    private String instiid;
    private String startDate;
    private String endDate;
    private String filestartid;
    private String memberId;
    private String memberName;
    private String memberPhone;
    private String acctCode;
    private String accNo;
    private ServiceContainer serviceContainer;
    private String falg;
    private String billDate;
    
    @Autowired
    private IChannelFileService iChannelFileService;
    @Autowired
    private WeChatReconFileService chatReconFileService;
    @Autowired
    private ITxnsLogService txnsLogService;
    @Autowired
    private CheckFileService cfs;
    @Autowired
    private ICmbcResfileService cmbcservice;
    
    private CmbcResfileBean crb;
    public CmbcResfileBean getCrb() {
        return crb;
    }
    public void setCrb(CmbcResfileBean crb) {
        this.crb = crb;
    }
    public String getFalg() {
        return falg;
    }
    public void setFalg(String falg) {
        this.falg = falg;
    }
    public String show() {
        return "upload_file";
    }
    public String showACC() {
        return "member_acc";
    }
    public String showStartCheck() {
        return "StartCheck_file";
    }
    public String showMemberQuery() {
        return "member_manager";
    }
   
    // 会员分页查询
    public String queryMerchByPage() {

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("memberId", memberId);
        variables.put("memberName", memberName);
        variables.put("memberPhone", memberPhone);
        if ("person".equals(falg)) {
            // 个人会员查询
            variables.put("membertype", BusinessActorType.INDIVIDUAL.getCode());
        } else if ("merch".equals(falg)) {
            variables.put("membertype", BusinessActorType.ENTERPRISE.getCode());
        }
        Map<String, Object> memberList = serviceContainer.getUploadlogService()
                .findMemberByPage(variables, getPage(), getRows());
        json_encode(memberList);
        return null;
    }
    // 会员账户查询
    public String queryACCByPage() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("memberId", memberId);
        variables.put("accNo", accNo);
        Map<String, Object> memberList = serviceContainer.getUploadlogService()
                .findMemberACCByPage(variables, getPage(), getRows());
        json_encode(memberList);
        return null;
    }
    // ---------------------------------------------------------------对账-----------------------------------------------------------------------------
    // 开始对账
    public String StartCheckFile() {
        List<?> list = serviceContainer.getUploadlogService().StartCheckFile(
                filestartid);
        try {
            json_encode(list);
        } catch (IOException e) {         
            e.printStackTrace();
        }
        return null;
    }
    // 保存任务
    public String saveProcess() {
        List<?> list = serviceContainer.getUploadlogService().saveProcess(
                instiid);
        json_encode(list.get(0));

        return null;
    }
    /**
     * 任务分页查询（新增任务，开始对账）
     * 
     * @return
     */
    public String queryProcess() {

        Map<String, Object> variables = new HashMap<String, Object>();
        if (startDate != null && !startDate.equals("")) {
            variables.put("startDate", startDate.replace("-", ""));
        }
        if (endDate != null && !endDate.equals("")) {
            variables.put("endDate", endDate.replace("-", ""));
        }
        Map<String, Object> processList = serviceContainer
                .getUploadlogService().findProcessByPage(variables, getPage(),
                        getRows());
        json_encode(processList);
        return null;
    }

    public void queryChannel() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<ChannelFileMode> list = iChannelFileService.getAllStatusChannel();
        result.put("list", list);
        json_encode(result);

    }
    // 上传对账文件
    public String upload() throws IOException, InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        Map<String, Object> result = new HashMap<String, Object>();
        try{
        List<Map<String, Object>> resultMark = null;// 保存对账数据后，返回的标记
        // 判断文件的类型（证联or中信）
        if (uploadFileName[0] != null) {
            // 判断机构与对账文件是否一致，（98000001：证联）（ 97000001：中信）（96000001：融宝）（93000003
            // 民生本行代扣）
            // 文件名类似，渠道代码前6位相同
            Boolean flag = iChannelFileService.booChanCodeAndFileName(
                    uploadFileName[0], instiid);
            if (flag) {
            } else {
                result.put("info", "上传文件类型与机构不符！");
                json_encode(result);
                return null;
            }
        } else {
            result.put("info", "请上传对账文件！");
            json_encode(result);
            return null;
        }
        AbstractFileContentHandler contentHandler = null;
        // 判断是否重复上传文件
        Boolean boo = serviceContainer.getBnktxnService().upLoad(
                uploadFileName[0]);
        if (boo) {
            result.put("info", "此对账文件已经上传过！");
            json_encode(result);
            return null;
        } else {
            UploadLogModel ulm = new UploadLogModel();
            ulm.setLogid(1l);
            ulm.setFilename(uploadFileName[0]);
            ulm.setUploaderid(getCurrentUser().getUserId());
            ulm.setUploadername(getCurrentUser().getUserName());
            serviceContainer.getUploadlogService().save(ulm); // 保存任务
        }
        // 解析对账文件内容
        List<BnkTxnModel> list = null;
        String sonInstiid = "";
        ChannelFileMode channelFileMode = null;
        if (uploadFileName[0].split("_").length > 2) {
            channelFileMode = iChannelFileService
                    .getLikeInstiid(uploadFileName[0].split("_")[2]);
            if(channelFileMode==null){
            	channelFileMode = iChannelFileService
                        .getLikeInstiid(uploadFileName[0].split("_")[0]);
            }
        } else {
            // excel
//            channelFileMode = iChannelFileService.getLikeInstiid(uploadFileName[0].split("-")[0]);
            channelFileMode = iChannelFileService.getLikeInstiid(uploadFileName[0].substring(0, 5));
        }
        if (channelFileMode != null) {
            sonInstiid = channelFileMode.getChnlCode();
            @SuppressWarnings("unchecked")
            Class<AbstractFileContentHandler> fileHandlerClass = (Class<AbstractFileContentHandler>)Class.forName(channelFileMode.getClassPath());
            contentHandler =  fileHandlerClass.newInstance();

            try {
                list = contentHandler.readFile(upload, sonInstiid, uploadFileName);
                if(list==null){
                    ResolveReconFileContentException rrfce = new ResolveReconFileContentException();
                    rrfce.setParams("解析对账文件后,返回结果为空");
                    throw rrfce;
                }
            } catch (ResolveReconFileContentException e) {
                result.put("info", e.getMessage());
                json_encode(result);
            } catch (Exception e) {
                result.put("info", "上传失败");
                json_encode(result);
            }
            for (BnkTxnModel bnk : list) {
                resultMark = serviceContainer.getBnktxnService()
                        .saveBnkTxn(bnk);
            }
            // 等对账数据保存成功后，更新UPload表的上传数据状态
            serviceContainer.getBnktxnService().updateUploadLog(
                    uploadFileName[0]);
            result.put("info", "对账文件上传成功！");
            json_encode(result);
        }
    }catch (Exception e) {
        result.put("info", "上传失败！");
        json_encode(result);
        log.error("上传失败:"+e.getMessage());
   }
        return null;
    }

    // 中信对账文件：读取一行对账文件数据
    public BnkTxnModel MackBnkTxn_zhongxin(String newline) {
        BnkTxnModel bnk = new BnkTxnModel();
        Object[] obzl = newline.replace(" ", "").split("\\|");
        bnk.setPayordno(obzl[17].toString());
        // bnk.setSystrcno(obzl[1].toString());
        bnk.setPan(obzl[2].toString());
        bnk.setAcqsettledate(obzl[0].toString());
        bnk.setMerchno(obzl[10].toString());
        bnk.setAmount(Long.valueOf(obzl[6].toString()));
        bnk.setRetcode("00");
        bnk.setInstiid(instiid);
        return bnk;
    }

    public String generateFileName(String fileName) {
        String formatDate = new SimpleDateFormat("yyMMddHHmmss")
                .format(new Date(1));
        int random = new Random().nextInt(10000);
        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return formatDate + random + extension;
    }
    
    public void dowanWeChatBill(){
    	Map<String, Object> result = new HashMap<String, Object>();
    	billDate = billDate.replaceAll("-", "");
    	// 判断是否重复上传文件
        Boolean boo = serviceContainer.getBnktxnService().upLoad(billDate);
        if (boo) {
            result.put("info", "此日期对账文件已经保存！");
            json_encode(result);
            return;
        } else {
            UploadLogModel ulm = new UploadLogModel();
            ulm.setLogid(1l);
            ulm.setFilename(billDate);
            ulm.setUploaderid(getCurrentUser().getUserId());
            ulm.setUploadername(getCurrentUser().getUserName());
            serviceContainer.getUploadlogService().save(ulm); // 保存任务
        }
    	
    	
    	try {
			List<BnkTxnModel> saveWeChatBill = chatReconFileService.saveWeChatBill(billDate);
			if (saveWeChatBill==null) {
				result.put("info", "未获取到微信对账文件！");
				serviceContainer.getBnktxnService().deleteFailedWechatUploadLog(billDate);
			}else{
				for (BnkTxnModel bnktxn : saveWeChatBill) {
	                serviceContainer.getBnktxnService().saveBnkTxn(bnktxn);
	            }
				// 等对账数据保存成功后，更新UPload表的上传数据状态
	            serviceContainer.getBnktxnService().updateUploadLog(billDate);
				result.put("info", "微信对账文件处理成功！");
			}			
		} catch (ParseException e) {			
			e.printStackTrace();
			result.put("info", "微信对账文件处理失败！");
		}
    	json_encode(result);
    }
    
    
    
    /////////////////////////////////////////////////结算///////////////////////////////////////////////////////////
    
    public String showSetted(){
    	return "setted";
    }
    
    public String startSetted(){
    	try {
			txnsLogService.excuteSetted();
			json_encode("结算完成");
		} catch (AccBussinessException e) {			
			e.printStackTrace();
			json_encode("结算失败");
		} catch (AbstractBusiAcctException e) {
			e.printStackTrace();
			json_encode("结算失败");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			json_encode("结算失败");
		} catch (IllegalEntryRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }
    //-----------------------------------------------导出对账表、差错表--------------------------------------
    /**
     * 查询对账成功的记录
     * @return
     */
    public String querySuccess(){
        Map<String, Object> variables = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        variables.put("proid", request.getParameter("proid")); 
        variables.put("user",getCurrentUser().getUserId());
        Map<String, Object> successList = serviceContainer
                .getUploadlogService().querySuccess(variables, getPage(),
                        getRows());
        json_encode(successList);
        return null;
    }
    

    /**
     * 查询对账差错的记录
     * @return
     */
    public String queryFail(){
        Map<String, Object> variables = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        variables.put("proid", request.getParameter("proid")); 
        variables.put("user",getCurrentUser().getUserId());
        Map<String, Object> failList = serviceContainer
                .getUploadlogService().queryFail(variables, getPage(),
                        getRows());
        json_encode(failList);
        return null;   
    }
    /**
     * 导出对账成功的记录为excel表
     * @throws UnsupportedEncodingException 
     * @throws WriteException 
     * @throws RowsExceededException 
     */
    public void exportCheckSuccess() throws UnsupportedEncodingException, RowsExceededException, WriteException{
        Map<String, Object> variables = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        variables.put("proid", request.getParameter("proid")); 
        variables.put("user",getCurrentUser().getUserId());
        Map<String, Object> successList = serviceContainer
                .getUploadlogService().exportCheckSuccess(variables, getPage(),
                        getRows());
        exportSuccessExcel(successList);
        
    }
    
    /**
     * 导出对账差错表为excel表
     * @throws WriteException 
     * @throws UnsupportedEncodingException 
     * @throws RowsExceededException 
     */
    public void exportCheckFail() throws RowsExceededException, UnsupportedEncodingException, WriteException{
        Map<String, Object> variables = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        variables.put("proid", request.getParameter("proid")); 
        variables.put("user",getCurrentUser().getUserId());
        Map<String, Object> failList = serviceContainer
                .getUploadlogService().exportCheckFail(variables, getPage(),
                        getRows());
        exportFailExcel(failList);
    }
    /**
     * excel创建差错表的表头并且写入数据
     * @param failList
     * @throws WriteException 
     * @throws RowsExceededException 
     * @throws UnsupportedEncodingException 
     */
    private void exportFailExcel(Map<String, Object> failList) throws RowsExceededException, WriteException, UnsupportedEncodingException {
        HttpServletResponse response = ServletActionContext.getResponse();   
        response.setContentType("application/vnd.ms-excel;charset=utf-8");      
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("差错表.xls", "UTF-8")); 
        WritableWorkbook workbook = null;
        try {
            
            workbook = Workbook.createWorkbook(response.getOutputStream());    
            // 生成名为"对账单"的工作表，参数0表示这是第一页
            WritableSheet sheet = workbook.createSheet("差错表",0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为交易流水，依次做此操作
            Label label1 = new Label(0,0,"交易流水号");
            Label label2 = new Label(1,0,"支付订单号");
            Label label3 = new Label(2,0,"应答流水号");
            Label label4 = new Label(3,0,"交易时间");
            Label label5 = new Label(4,0,"交易类型");
            Label label6 = new Label(5,0,"交易金额(元)");
            Label label7 = new Label(6,0,"手续费金额(元)");
            Label label8 = new Label(7,0,"通道手续费");
            Label label9 = new Label(8,0,"交易渠道");
            Label label10 = new Label(9,0,"差错原因");
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
            List<Map<String, Object>> rowList = (List<Map<String, Object>>) failList.get("rows");
            for(int i= 0;i<rowList.size();i++){
                Label labelone = new Label(0,i+1,(String) rowList.get(i).get("TXNSEQNO"));
                Label labeltwo = new Label(1,i+1,(String) rowList.get(i).get("PAYORDNO"));
                Label labelthree = new Label(2,i+1,(String) rowList.get(i).get("SYSTRCNO"));
                Label labelfour = new Label(3,i+1,(String) rowList.get(i).get("TXNDATETIME"));
                Label labelfive = new Label(4,i+1,(String) rowList.get(i).get("BUSINAME"));
                Label labelsix = new Label(5,i+1,(String) rowList.get(i).get("AMOUNT"));
                Label labelseven = new Label(6,i+1,(String) rowList.get(i).get("TXNFEE"));
                Label labeleight = new Label(7,i+1,(String) rowList.get(i).get("CFEE"));
                Label labelnine = new Label(8,i+1,(String) rowList.get(i).get("PAYINST"));
                Label labelten = new Label(9,i+1,(String) rowList.get(i).get("MISTAKEDESC"));
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
            //写入数据并关闭文件 
            workbook.write(); 
            workbook.close(); 
        } catch (IOException e) {            
            e.printStackTrace();
        }
        
    }
    /**
     * excel创建对账表的表头并且写入数据，
     * @param successList
     * @throws UnsupportedEncodingException
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void exportSuccessExcel(Map<String, Object> successList) throws UnsupportedEncodingException, RowsExceededException, WriteException {
        HttpServletResponse response = ServletActionContext.getResponse();   
        response.setContentType("application/vnd.ms-excel;charset=utf-8");      
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("对账单.xls", "UTF-8")); 
        WritableWorkbook workbook = null;
        try {
            
            workbook = Workbook.createWorkbook(response.getOutputStream());    
            // 生成名为"对账单"的工作表，参数0表示这是第一页
            WritableSheet sheet = workbook.createSheet("对账单",0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为交易流水，依次做此操作
            Label label1 = new Label(0,0,"交易流水号");
            Label label2 = new Label(1,0,"支付订单号");
            Label label3 = new Label(2,0,"应答流水号");
            Label label4 = new Label(3,0,"交易时间");
            Label label5 = new Label(4,0,"交易类型");
            Label label6 = new Label(5,0,"交易金额(元)");
            Label label7 = new Label(6,0,"手续费金额(元)");
            Label label8 = new Label(7,0,"通道手续费");
            Label label9 = new Label(8,0,"交易渠道");
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
            sheet.addCell(label7);
            sheet.addCell(label8);
            sheet.addCell(label9);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rowList = (List<Map<String, Object>>) successList.get("rows");
            for(int i= 0;i<rowList.size();i++){
                Label labelone = new Label(0,i+1, (String) rowList.get(i).get("TXNSEQNO"));
                Label labeltwo = new Label(1,i+1, (String) rowList.get(i).get("PAYORDNO"));
                Label labelthree = new Label(2,i+1, (String) rowList.get(i).get("PAYRETTSNSEQNO"));
                Label labelfour = new Label(3,i+1, (String) rowList.get(i).get("PAYORDFINTIME"));
                Label labelfive = new Label(4,i+1, (String) rowList.get(i).get("BUSINAME"));
                Label labelsix = new Label(5,i+1, (String) rowList.get(i).get("AMOUNT"));
                Label labelseven = new Label(6,i+1, (String) rowList.get(i).get("TXNFEE"));
                Label labeleight = new Label(7,i+1, (String) rowList.get(i).get("CFEE"));
                Label labelnine = new Label(8,i+1, (String) rowList.get(i).get("PAYINST"));
                sheet.addCell(labelone);
                sheet.addCell(labeltwo);
                sheet.addCell(labelthree);
                sheet.addCell(labelfour);
                sheet.addCell(labelfive);
                sheet.addCell(labelsix);
                sheet.addCell(labelseven);
                sheet.addCell(labeleight);
                sheet.addCell(labelnine);          
            }
            //写入数据并关闭文件 
            workbook.write(); 
            workbook.close();
        } catch (IOException e) {            
            e.printStackTrace();
        }
        
    }
    /**
     * 导出对账失败的记录为excel表
     * @return
     */
    
    public File[] getUpload() {
        return upload;
    }
    
    /**
     * 民生回盘文件解析
     * @param upload
     */
    public String peopleFileParsing(){
        return "parsing";
    }
    
    /**
     * 根据交易时间查询民生代付的回盘文件
     * @param upload
     */
    public void queryMinsheng(){
        int page = this.getPage();
        int pagesize = this.getRows();
        Map<String,Object> map = new HashMap<String, Object>();
        PagedResult<CmbcResfileBean> cfb = cmbcservice.queryPaged(page, pagesize,crb);
        try {
            List<CmbcResfileBean> li = cfb.getPagedResult();
            Long count = cfb.getTotal();
            map.put("total", count);
            map.put("rows", li);
            json_encode(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getInstiid() {
        return instiid;
    }

    public void setInstiid(String instiid) {
        this.instiid = instiid;
    }

    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }
    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getFilestartid() {
        return filestartid;
    }
    public void setFilestartid(String filestartid) {
        this.filestartid = filestartid;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getAccNo() {
        return accNo;
    }
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    public String getMerch() {
        return "merch";
    }
	/**
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
    public String getMemberPhone() {
        return memberPhone;
    }
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

	
}
