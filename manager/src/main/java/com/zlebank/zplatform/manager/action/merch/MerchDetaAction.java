package com.zlebank.zplatform.manager.action.merch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.CityModel;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;
import com.zlebank.zplatform.manager.dao.object.ProvinceModel;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.IMerchDetaService;
import com.zlebank.zplatform.manager.util.CommonUtil;
import com.zlebank.zplatform.member.service.MemberService;

public class MerchDetaAction extends BaseAction {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(MerchDetaAction.class);
    private static final long serialVersionUID = 1L;
    private ServiceContainer serviceContainer;
    // private UserModel user;
    private long pid;
    private String vid;
    private String flag;// 标记流程 1商户信息管理列表 2初审查询列表 3复审查询列表 5商户初审审核页面 10商户查询页面
    private String bankName;
    private MerchDetaModel merchDate;
    private Map<String, Object> merchMap;
    private File headImage;
    private String headImageFileName; // 文件名称
    private String headImageContentType;
    private String memberId;
    private String imageURL;
    private String merchId;
    private String certTypeCode;
    @Autowired
    MemberService memberService;
    private String isAgree;
    private String oldBankName;
    private String fouceDownload;

    // 商户信息管理页面
    public String show() {
        flag = "1";
        return SUCCESS;
    }

    // 商户新增页面
    public String showMerchAdd() {
        return "merch_add";
    }

    // 商户初审分页查询页面
    public String showMerchAuditQuery() {
        flag = "2";
        return "success";
    }

    // 商户复审分页查询页面
    public String showMerchReAuditQuery() {
        flag = "3";
        return "success";
    }

    // 商户初审审核页面
    public String toMerchAudit() {
        flag = "5";
        return "merch_detail";
    }

    // 商户查询页面（查询所有状态）
    public String showMerchQueryAll() {
        flag = "10";
        return "merch_query_all";
    }

    // 商户修改页面
    public String ToMerchChange() {
        /*Long userId = getCurrentUser().getUserId();
        merchMap = serviceContainer.getMerchDetaService().queryOneMerchDeta(
                merchDate.getMerchid(), userId);*/
        merchDate = serviceContainer.getMerchDetaService().get(Long.parseLong(merchId));
        oldBankName = serviceContainer.getMerchDetaService().queryBankName(merchDate.getBanknode(), merchDate.getBankcode());
        return "merch_change";
    }

    // 查看某一条商户信息,查看商户详细信息
    public String ToMerchDetail() {
        Long userId = getCurrentUser().getUserId();
        merchMap = serviceContainer.getMerchDetaService().queryOneMerchDeta(
                Long.parseLong(merchId), userId);
        return "merch_detail";
    }

    /**
     * 保存商户信息
     * 
     * @return
     */
    public String saveMerchDate() {
        String codeANDnode = merchDate.getBanknode();
        if (!"".equals(codeANDnode) && null != codeANDnode) {
            Object[] paramaters = codeANDnode.split(",");
            merchDate.setBankcode(paramaters[0].toString());
            merchDate.setBanknode(paramaters[1].toString());
        }
        if (merchDate.getIsDelegation() == null) {
            merchDate.setIsDelegation(0);
        }

        UserModel currentUser = getCurrentUser();
        merchDate.setInuser(String.valueOf(currentUser.getUserId()));
        List<?> resultlist = serviceContainer.getMerchDetaService()
                .saveMerchDeta(merchDate);

        json_encode(resultlist.get(0));

        return null;
    }

    public String toUpload() {
        merchDate = serviceContainer.getMerchDetaService().get(Long.parseLong(merchId));
        if (merchDate == null) {
        }

        return "toUpload";
    }

    /**
     * 修改商户信息
     * 
     * @return
     */
    public String saveChangeMerchDate() {
        if (merchDate.getIsDelegation() == null) {
            merchDate.setIsDelegation(0);
        } 
        merchDate.setMerchid(Long.parseLong(merchId));
        List<?> resultlist = serviceContainer.getMerchDetaService()
                .saveChangeMerchDeta(merchDate);
        merchDate.setInuser(String.valueOf(getCurrentUser().getUserId()));
        json_encode(resultlist.get(0));
        return null;
    }

    /**
     * 商户信息管理（查询，修改，查看详情）页面
     * 
     * @return
     */
    public String queryMerch() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (merchDate == null) {
            merchDate = new MerchDetaModel();
        }
        variables.put("merberId", merchDate.getMemberid());
        variables.put("merchName", merchDate.getMerchname());
        variables.put("address", merchDate.getAddress());
        variables.put("status", merchDate.getStatus());
        variables.put("flag", flag);
        Map<String, Object> merchList = serviceContainer.getMerchDetaService()
                .findMerchByPage(variables, getPage(), getRows());
        json_encode(merchList);
        // FTPClient ff= new FTPClient();
        return null;
    }

    // 商户秘钥下载
    public String loadMerchMk() {
        if (memberId != null && !memberId.equals("")) {
        }
        merchMap = serviceContainer.getMerchDetaService().loadMerchMk(memberId);
        if (merchMap == null) {
            json_encode("没有密钥");
            return null;
        }
        return "merch_mk_export";
    }

    /**
     * 商户审核（通过，否决，驳回） --0 通过 1 拒绝 9 终止
     * 
     * @return
     * @throws Exception
     */
    public String audit() throws Exception { 
        
        merchDate.setMerchid(Long.parseLong(merchId));
        //初审意见和复审意见，在页面中都是通过merchDate.stexaopt传过来的
        String stexopt = URLDecoder.decode(merchDate.getStexaopt(), "utf-8");
        if(flag.equals("2")){//初审，需要记录初审人和初审意见
            
            merchDate.setStexaopt(stexopt);
            merchDate.setStexauser(String.valueOf(getCurrentUser().getUserId()));
        }else if(flag.equals("3")){//复审，需要记录复审人和复审意见
            merchDate.setCvlexaopt(stexopt);
            merchDate.setCvlexauser(String.valueOf(getCurrentUser().getUserId()));
        } 
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultlist = (List<Map<String, Object>>) serviceContainer
                .getMerchDetaService().merchAudit(merchDate, flag,isAgree);
        json_encode(resultlist);
        return null;
    }

     
    /**
     * get cert img url
     * 
     * @return
     */
    public String downloadImgUrl() {
        String webRootPath = ServletActionContext.getServletContext().getRealPath("/");
        String realpath = webRootPath+"/"+CommonUtil.DOWNLOAD_ROOTPATH;
        boolean fouce = (fouceDownload!=null&&fouceDownload.equals("fouce"));
        String filePath = serviceContainer.getMerchDetaService()
                .downloadFromFtp(Long.parseLong(merchId), realpath,
                        CertType.format(certTypeCode),fouce);
        Map<String, String> result = new HashMap<String, String>();
       if(filePath==null) {
           result.put("status", "fail"); 
       }else if(filePath.equals("")){
           result.put("status", "notExist");
       } else{
           result.put("status", "OK");
           result.put("url", filePath);
           new MerchantThread(webRootPath + "/" + filePath).start();
       }
        json_encode(result);
        return null;
    }

    // 加载商户新增页面所有下拉框
    // 省
    public String queryProvince() {
        try {
            List<ProvinceModel> provinceList = serviceContainer
                    .getProvinceService().findAll();
            json_encode(provinceList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 市
    public String queryCity() {
        try {
            List<CityModel> cityList = serviceContainer.getCityService()
                    .findNotMuniByPid(pid);
            json_encode(cityList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 县
    public String queryCounty() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryCounty(pid);
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 所属行业
    public String queryTrate() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryTrade();
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 商户类型
    public String queryMerchType() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryMerchType();
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 商户清算类型
    public String queryMerchClearType() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .querysetltype();
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 所属商户
    public String showMerchParent() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryMerchParent();
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 关键字查询开户行
    public String queryBankNode() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryBankNode(bankName, getPage(), getRows());
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 收银台
    public String queryCash() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryCashAll();
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 交易渠道
    public String queryChnlnameAll() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryChnlnameAll();
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 路由版本
    public String queryRouteAll() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryRouteAll();
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 产品下的风控版本
    public String queryRiskType() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryRiskType(vid);
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 产品下的分润版本
    public String querySplit() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .querySplit(vid);
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 产品下的扣率版本
    public String queryFee() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .queryFee(vid);
            json_encode(countyList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 清算周期
    public String querySetlcycleAll() {
        try {
            List<?> countyList = serviceContainer.getMerchDetaService()
                    .querySetlcycleAll();
            json_encode(countyList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // -----------------------------------------------------------------------------------------------------------
    // 上传文件FTP
    public String upload() {
        Map<String, String> result = new HashMap<String, String>();
        if (certTypeCode == null || certTypeCode.equals("")) {
            result.put("status", "FAIL");
            return null;
        }
        CertType certType = CertType.format(certTypeCode);

        IMerchDetaService merchDetaService = serviceContainer
                .getMerchDetaService();
        boolean isSucc = merchDetaService.upload(Long.parseLong(merchId),
                headImageFileName, headImage, certType);
        if (isSucc) {
            result.put("status", "OK");
        } else {
            result.put("status", "FAIL");
        }
        json_encode(result);
        return null;
    }

    public String commitMerch() {
        Map<String, String> result = new HashMap<String, String>();
        IMerchDetaService merchDetaService = serviceContainer
                .getMerchDetaService();
        boolean isSucc = merchDetaService.commitMerch(Long.parseLong(merchId));
        if (isSucc) {
            result.put("status", "OK");
        } else {
            result.put("status", "FAIL");
        }
        json_encode(result);
        return null;
    }

    @SuppressWarnings("resource")
    public long getFileSizes(File f) throws Exception {// 取得文件大小
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
            s = fis.available();
        } else {
            f.createNewFile();
        }
        return s;
    }

    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }

    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public MerchDetaModel getMerchDate() {
        return merchDate;
    }

    public void setMerchDate(MerchDetaModel merchDate) {
        this.merchDate = merchDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 重命名上传文件
     * 
     * @param fileName
     * @return
     */
    public String generateFileName(String fileName) {
        String formatDate = new SimpleDateFormat("yyMMddHHmmss")
                .format(new Date());
        int random = new Random().nextInt(10000);
        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return formatDate + random + extension;
    }

    public File getHeadImage() {
        return headImage;
    }

    public void setHeadImage(File headImage) {
        this.headImage = headImage;
    }

    public String getHeadImageFileName() {
        return headImageFileName;
    }

    public void setHeadImageFileName(String headImageFileName) {
        this.headImageFileName = headImageFileName;
    }

    public String getHeadImageContentType() {
        return headImageContentType;
    }

    public void setHeadImageContentType(String headImageContentType) {
        this.headImageContentType = headImageContentType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Map<String, Object> getMerchMap() {
        return merchMap;
    }

    public void setMerchMap(Map<String, Object> merchMap) {
        this.merchMap = merchMap;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public class MerchantThread extends Thread {
        private String sPath;

        public void run() {
            try {
                deleteFile(sPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public MerchantThread(String sPath) {
            this.sPath = sPath;
        }

        /**
         * 删除单个文件
         * 
         * @param sPath
         *            被删除文件的文件名
         * @return 单个文件删除成功返回true，否则返回false
         */
        @SuppressWarnings("static-access")
        public void deleteFile(String sPath) {
            try {//保留一小时
                Thread.currentThread().sleep(1000*60*60);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            File file = new File(sPath);
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        }

        public String getsPath() {
            return sPath;
        }

        public void setsPath(String sPath) {
            this.sPath = sPath;
        }
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getCertTypeCode() {
        return certTypeCode;
    }

    public void setCertTypeCode(String certTypeCode) {
        this.certTypeCode = certTypeCode;
    }

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }

    public String getOldBankName() {
        return oldBankName;
    }

    public void setOldBankName(String oldBankName) {
        this.oldBankName = oldBankName;
    }

    public String getFouceDownload() {
        return fouceDownload;
    }

    public void setFouceDownload(String fouceDownload) {
        this.fouceDownload = fouceDownload;
    }
}