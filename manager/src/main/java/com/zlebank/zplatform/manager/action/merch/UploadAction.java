package com.zlebank.zplatform.manager.action.merch;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.dao.object.UploadLogModel;
import com.zlebank.zplatform.manager.dao.object.scan.ChannelFileMode;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.IChannelFileService;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

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

    @Autowired
    private IChannelFileService iChannelFileService;

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
        } else {
            // excel
            channelFileMode = iChannelFileService
                    .getLikeInstiid(uploadFileName[0].split("-")[0]);
        }
        if (channelFileMode != null) {
            sonInstiid = channelFileMode.getChnlCode();
            Class c = Class.forName(channelFileMode.getClassPath());
            contentHandler = (AbstractFileContentHandler) c.newInstance();

            list = contentHandler.readFile(upload, sonInstiid, uploadFileName);
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

}
