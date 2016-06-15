package com.zlebank.zplatform.manager.action.merch;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.dao.object.UploadLogModel;
import com.zlebank.zplatform.manager.dao.object.scan.ChannelFileMode;
import com.zlebank.zplatform.manager.exception.ResolveReconFileContentException;
import com.zlebank.zplatform.manager.service.WeChatReconFileService;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.IChannelFileService;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.trade.service.ITxnsLogService;

public class UploadAction extends BaseAction {

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
            // TODO Auto-generated catch block
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
            channelFileMode = iChannelFileService
                    .getLikeInstiid(uploadFileName[0].split("-")[0]);
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
			for (BnkTxnModel bnktxn : saveWeChatBill) {
                serviceContainer.getBnktxnService().saveBnkTxn(bnktxn);
            }
			// 等对账数据保存成功后，更新UPload表的上传数据状态
            serviceContainer.getBnktxnService().updateUploadLog(billDate);
			result.put("info", "微信对账文件处理成功！");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			json_encode("结算失败");
		} catch (AbstractBusiAcctException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json_encode("结算失败");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json_encode("结算失败");
		}
    	
    	return null;
    }
    
    public File[] getUpload() {
        return upload;
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

}
