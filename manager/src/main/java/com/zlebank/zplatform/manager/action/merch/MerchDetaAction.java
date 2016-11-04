package com.zlebank.zplatform.manager.action.merch;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.Enterprise;
import com.zlebank.zplatform.manager.bean.MerchDeta;
import com.zlebank.zplatform.manager.dao.object.CityModel;
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
    private MerchDeta merchDeta;
    private Enterprise enterprise;
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
    private String merchApplyId;
    private String deposit;
    private String charge;
    private String merchStatus;
    private final static BigDecimal HUNDERED = new BigDecimal(100);

    private String enterpriseApplyId;
    private Map<String,Object> enterpriseDeta;
    private String enterpriseId;
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
    


    /**
     * 保存商户信息
     * 
     * @return
     */
    public String saveMerchDeta() {
        String codeANDnode = merchDeta.getBankNode();
        if (!"".equals(codeANDnode) && null != codeANDnode) {
            Object[] paramaters = codeANDnode.split(",");
            merchDeta.setBankCode(paramaters[0].toString());
            merchDeta.setBankNode(paramaters[1].toString());
        }
        if (enterprise.getIsDelegation() == null) {
            enterprise.setIsDelegation(0L);
        }

        if (charge == null || charge.equals("")) {
            merchDeta.setCharge(Money.ZERO);
        } else {
            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge)
                    .multiply(HUNDERED)));
        }

        if (deposit == null || deposit.equals("")) {
            merchDeta.setDeposit(Money.ZERO);
        } else {
            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
                    .multiply(HUNDERED)));
        }

        UserModel currentUser = getCurrentUser();
        merchDeta.setmInUser(currentUser.getUserId());
        merchDeta.setMember(enterprise);
        List<?> resultlist = serviceContainer.getMerchDetaService()
                .saveMerchDeta(merchDeta);

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
        if (merchDeta != null) {
            variables.put("merberId", merchDeta.getMember().getMemberId());
            variables.put("merchName", merchDeta.getMember().getMemberName());
            variables.put("address",
                    ((Enterprise) merchDeta.getMember()).getAddress());
            variables.put("status", merchStatus);
            variables.put(
                    "coopInstiId",
                    merchDeta.getMember().getInstiCode() != null ? merchDeta
                            .getMember() : null);
        }

        variables.put("flag", flag);
        Map<String, Object> merchList = serviceContainer.getMerchDetaService()
                .findMerchByPage(variables, getPage(), getRows());
        json_encode(merchList);
        return null;
    }
    /**
     * 跳转到上传证件页面
     * 
     * @return
     */
    public String toUpload() {
        merchDeta = serviceContainer.getMerchDetaService().getBean(
                Long.parseLong(merchApplyId));
        if (merchDeta == null) {
            // TODO return merchant not exist error
        }

        return "toUpload";
    }

    /**
     * 
     * @return
     */
    public String upload() {
        Map<String, String> result = new HashMap<String, String>();
        if (certTypeCode == null || certTypeCode.equals("")) {
            result.put("status", "FAIL");
            return null;
        }
        CertType certType = CertType.format(certTypeCode);

        IMerchDetaService merchDetaService = serviceContainer
                .getMerchDetaService();
        boolean isSucc = merchDetaService.upload(Long.parseLong(merchApplyId),
                headImageFileName, headImage, certType);
        if (isSucc) {
            result.put("status", "OK");
        } else {
            result.put("status", "FAIL");
        }
        json_encode(result);
        return null;
    }

    /**
     * get cert img url
     * 
     * @return
     */
    public String downloadImgUrl() {
        String webRootPath = ServletActionContext.getServletContext()
                .getRealPath("/");
        String realpath = webRootPath + "/" + CommonUtil.DOWNLOAD_ROOTPATH;
        boolean fouce = (fouceDownload != null && fouceDownload.equals("fouce"));
        String filePath = serviceContainer.getMerchDetaService().downloadFromFtp
                (Long.parseLong(merchApplyId), realpath,CertType.format(certTypeCode), fouce);
        Map<String, String> result = new HashMap<String, String>();
        if (filePath == null) {
            result.put("status", "fail");
        } else if (filePath.equals("")) {
            result.put("status", "notExist");
        } else {
            result.put("status", "OK");
            result.put("url", filePath);
            new MerchantThread(webRootPath + "/" + filePath).start();
        }
        json_encode(result);
        return null;
    }

    /**
     * 商户修改页面
     * 
     * @return
     */
    public String toMerchChange() {
        merchDeta = serviceContainer.getMerchDetaService().getBean(
                Long.parseLong(merchApplyId));
        oldBankName = serviceContainer.getMerchDetaService().queryBankName(
                merchDeta.getBankNode(), merchDeta.getBankCode());

        charge = merchDeta.getCharge().toString();
        deposit = merchDeta.getDeposit().toString();

        return "merch_change";
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
            try {// 保留一小时
                Thread.currentThread().sleep(1000 * 60 * 60);
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

    /**
     * 修改商户信息
     * 
     * @return
     */
    public String saveChangeMerchDeta() {
        if (merchDeta.getMember().getIsDelegation() == null) {
            merchDeta.getMember().setIsDelegation(0L);
        }

        if (merchDeta.getMember().getIsDelegation() == null) {
            merchDeta.getMember().setIsDelegation(0L);
        }

        if (charge == null || charge.equals("")) {
            merchDeta.setCharge(Money.ZERO);
        } else {
            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge)
                    .multiply(HUNDERED)));
        }

        if (deposit == null || deposit.equals("")) {
            merchDeta.setDeposit(Money.ZERO);
        } else {
            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
                    .multiply(HUNDERED)));
        }

        List<?> resultlist = serviceContainer.getMerchDetaService()
                .saveChangeMerchDeta(Long.parseLong(merchApplyId), merchDeta);
        merchDeta.setmInUser(getCurrentUser().getUserId());
        json_encode(resultlist.get(0));
        return null;
    }

    /**
     * 商户申请提交
     * 
     * @return
     */
    public String commitMerch() {
        Map<String, String> result = new HashMap<String, String>();
        IMerchDetaService merchDetaService = serviceContainer
                .getMerchDetaService();
        boolean isSucc = merchDetaService.commitMerch(Long
                .parseLong(merchApplyId));
        if (isSucc) {
            result.put("status", "OK");
        } else {
            result.put("status", "FAIL");
        }
        json_encode(result);
        return null;
    }

    /**
     * 查看某一条商户信息,查看商户详细信息
     * 
     * @return
     */
    public String toMerchDetail() {
        Long userId = getCurrentUser().getUserId();
        merchMap = serviceContainer.getMerchDetaService().queryApplyMerchDeta(
                Long.parseLong(merchApplyId), userId);
        return "merch_detail";
    }
    /***
     * 复审未通过，则允许变更商户申请信息
     * 
     * @return
     */
    public String toMerchModify(){
        Long userId = getCurrentUser().getUserId();
        merchMap = serviceContainer.getMerchDetaService().queryApplyMerchDeta(Long.parseLong(merchApplyId), userId);
        return "merch_modify";
        
    }

    /**
     * 商户审核（通过，否决，驳回） --0 通过 1 拒绝 9 终止
     * 
     * @return
     * @throws Exception
     */
    public String audit() throws Exception {

        // 初审意见和复审意见，在页面中都是通过merchDate.stexaopt传过来的
        String stexopt = URLDecoder.decode(merchDeta.getStexaOpt(), "utf-8");
        if (flag.equals("2")) {// 初审，需要记录初审人和初审意见
            merchDeta.setStexaOpt(stexopt);
            merchDeta.setStexaUser(getCurrentUser().getUserId());
        } else if (flag.equals("3")) {// 复审，需要记录复审人和复审意见
            merchDeta.setCvlexaOpt(stexopt);
            merchDeta.setCvlexaUser(getCurrentUser().getUserId());
        }else if(flag.equals("5")){//变更初审，需要记录初审人和初审意见
            merchDeta.setStexaOpt(stexopt);
            merchDeta.setStexaUser(getCurrentUser().getUserId());
        }else if(flag.equals("6")){//变更复审，需要记录复审人和复审意见
            merchDeta.setCvlexaOpt(stexopt);
            merchDeta.setCvlexaUser(getCurrentUser().getUserId());
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultlist = (List<Map<String, Object>>) serviceContainer
                .getMerchDetaService().merchAudit(Long.parseLong(merchApplyId),
                        merchDeta, flag, isAgree);
        if(flag.equals("6")){
            resultlist.get(0).put("FLAG", "复审通过");
        }else{
           resultlist.get(0).put("FLAG", ""); 
        }
        json_encode(resultlist);
        return null;
    }

    /**
     * 商户秘钥下载
     * 
     * @return
     */
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
     * 查询正式在用的商户详细信息    
     * @return
     */
    public String toOfficalMerchDetail(){
        Long userId = getCurrentUser().getUserId();
        merchMap = serviceContainer.getMerchDetaService().queryMerchDeta(
                Long.parseLong(merchId), userId);
        return "merch_detail";
    }
    
    
    /*
     * 加载商户新增页面所有下拉框。这部分违反单一职责，考虑将这部分责任分散到对应的action中
     */
    /**
     * 省
     * 
     * @return
     */
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

    /**
     * 市
     * 
     * @return
     */
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

    /**
     * 县
     * 
     * @return
     */
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

    /**
     * 所属行业
     * 
     * @return
     */
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

    /**
     * 商户类型
     * 
     * @return
     */
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

    /**
     * 商户清算类型
     * 
     * @return
     */
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

    /**
     * 所属商户
     * 
     * @return
     */
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

    /**
     * 关键字查询开户行
     * 
     * @return
     */
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

    /**
     * 收银台版本
     * 
     * @return
     */
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

    /**
     * 交易渠道
     * 
     * @return
     */
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

    /**
     * 路由版本
     * 
     * @return
     */
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

    /**
     * 风控版本
     * 
     * @return
     */
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

    /**
     * 分润版本
     * 
     * @return
     */
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

    /**
     * 扣率版本
     * 
     * @return
     */
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

    /**
     * 清算周期
     * 
     * @return
     */
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
//*********************************商户信息变更*******************************************
    /**
     * 商户信息变更菜单
     * @return
     */
    public String showMerchModify(){
        flag="4";
        return "merch_modify_query";
    }
    /**
     * 商户变更初审
     */
    public String merchModifyFirstCheck(){
        flag="5";
        return "merch_modify_query";
    }
    /**
     * 商户变更复审
     */
    public String merchModifySecondCheck(){
        flag="6";
        return "merch_modify_query";
    }
    /**
     * 商户信息变更界面
     * @return
     */
    public String queryMerchModify(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (merchDeta != null) {
            variables.put("merberId", merchDeta.getMember().getMemberId());
            variables.put("merchName", merchDeta.getMember().getMemberName());
        }
        variables.put("flag", flag);
        Map<String, Object> merchList = serviceContainer.getMerchDetaService()
                .findMerchModifyByPage(variables, getPage(), getRows());
        json_encode(merchList);
        return null;
    }
    
    /**
     * 商户信息变更列表的变更功能
     * @return
     */
    public String toMerchModifyEdit(){
        merchDeta = serviceContainer.getMerchDetaService().getBean(
                Long.parseLong(merchApplyId));
        oldBankName = serviceContainer.getMerchDetaService().queryBankName(
                merchDeta.getBankNode(), merchDeta.getBankCode());

        charge = merchDeta.getCharge().toString();
        deposit = merchDeta.getDeposit().toString();

        return "merch_modify_edit";    
    }
    
    /**
     * 点击下一步，保存本页信息
     * @return
     */
    public String toUploadModifyInfo(){
        merchDeta = serviceContainer.getMerchDetaService().getBean(
                Long.parseLong(merchApplyId));
        if (merchDeta == null) {
        }

        return "toUploadModifyInfo";
    }
    /**
     * 
     * 商户变更的提交申请功能
     * @return
     */
    public String commitMerchModify(){
        Map<String, String> result = new HashMap<String, String>();
        IMerchDetaService merchDetaService = serviceContainer
                .getMerchDetaService();
        boolean isSucc = merchDetaService.commitMerchModify(Long
                .parseLong(merchApplyId));
        if (isSucc) {
            result.put("status", "OK");
        } else {
            result.put("status", "FAIL");
        }
        json_encode(result);
        return null;
    }
    
    /**
     * 点击下一步，对商户变更信息做保存更新
     * @return
     */
    public String saveMerchModifyDeta(){
        if (merchDeta.getMember().getIsDelegation() == null) {
            merchDeta.getMember().setIsDelegation(0L);
        }

        if (merchDeta.getMember().getIsDelegation() == null) {
            merchDeta.getMember().setIsDelegation(0L);
        }

        if (charge == null || charge.equals("")) {
            merchDeta.setCharge(Money.ZERO);
        } else {
            merchDeta.setCharge(Money.valueOf(new BigDecimal(charge)
                    .multiply(HUNDERED)));
        }

        if (deposit == null || deposit.equals("")) {
            merchDeta.setDeposit(Money.ZERO);
        } else {
            merchDeta.setDeposit(Money.valueOf(new BigDecimal(deposit)
                    .multiply(HUNDERED)));
        }

        List<?> resultlist = serviceContainer.getMerchDetaService()
                .saveMerchModifyDeta(Long.parseLong(merchApplyId), merchDeta);
        merchDeta.setmInUser(getCurrentUser().getUserId());
        json_encode(resultlist.get(0));
        return null;
        
    }
    /**
     * 变更信息的审核（初审、复审）
     * @return
     */
    
    public String toMerchModifyDetail(){
        Long userId = getCurrentUser().getUserId();
        merchMap = serviceContainer.getMerchDetaService().queryModifyMerchDeta(
                Long.parseLong(merchApplyId), userId);
        return "merch_modify_detail";  
    }
    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }

 //------------------------------------企业审核和查询----------------------------------------------------   
    /**
     * 企业初审菜单
     * @param serviceContainer
     */
    public String enterpriseFirstExam(){  
        flag="2";
        return "enterprise_exam_query";       
    }
    
    /***
     * 企业复审菜单
     * @param serviceContainer
     */
    public String enterpriseSecondExam(){
        flag="3";
        return "enterprise_exam_query";
    }
    
    /**
     * 企业查询菜单
     * @param serviceContainer
     */
    public String enterpriseQuery(){
        flag="10";
        return "enterpriseQueryAll";
    }
    
    /**
     * 初审、复审的查询界面
     * @param serviceContainer
     */
    public String queryEnterprise(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (enterprise != null) {
            variables.put("enterpriseMemberId", enterprise.getEnterpriseMemberId());//会员编号
            variables.put("enterpriseName", enterprise.getEnterpriseName());//企业名称
            variables.put("enterpriseStatus", enterprise.getEnterpriseStatus());//状态
        }
        variables.put("flag", flag);
        Map<String, Object> enterpriseList = serviceContainer.getMerchDetaService()
                .findEnterpriseByPage(variables, getPage(), getRows());
        json_encode(enterpriseList);
        return null;
    }
    /**
     * 审核界面
     * @param serviceContainer
     */
    public String toEnterpriseDetail(){
        Long userId = getCurrentUser().getUserId();
        enterpriseDeta = serviceContainer.getMerchDetaService().queryEnterpriseExamDeta
                (Long.parseLong(enterpriseApplyId),userId);
        return "enterpriseFirstExam"; 
    }
    
    /**
     * 企业查询功能
     * @param serviceContainer
     */
    public String queryEnterpriseAll(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (merchDeta != null) {
            variables.put("merberId", merchDeta.getMember().getMemberId());
            variables.put("merchName", merchDeta.getMember().getMemberName());
            variables.put("address",
                    ((Enterprise) merchDeta.getMember()).getAddress());
            variables.put("status", merchStatus);
            variables.put(
                    "coopInstiId",
                    merchDeta.getMember().getInstiCode() != null ? merchDeta
                            .getMember() : null);
        }

        variables.put("flag", flag);
        Map<String, Object> merchList = serviceContainer.getMerchDetaService()
                .findMerchByPage(variables, getPage(), getRows());
        json_encode(merchList);
        return null;
    }
    /**
     * 企业的审核（初审、复审）
     * @return
     * @throws IOException 
     */
    public String examEnterprise() throws IOException{
        // 初审意见和复审意见，在页面中都是通过merchDate.stexaopt传过来的
        String stexopt = URLDecoder.decode(enterprise.getStexaOpt(), "utf-8");
        if (flag.equals("2")) {// 初审，需要记录初审人和初审意见
            enterprise.setStexaOpt(stexopt);
            enterprise.setStexaUser(getCurrentUser().getUserId());
        } else if (flag.equals("3")) {// 复审，需要记录复审人和复审意见
            enterprise.setCvlexaOpt(stexopt);
            enterprise.setCvlexaUser(getCurrentUser().getUserId());
        }        
        List<Map<String, Object>> resultlist = (List<Map<String, Object>>) serviceContainer
        .getMerchDetaService().enterpriseAudit(Long.parseLong(enterpriseApplyId),
              enterprise, flag, isAgree);
        json_encode(resultlist);
        return null;
    } 
    
    /**
     * 加载证件照片等
     * @param serviceContainer
     */
    
    public String downloadEnterpriseImgUrl(){
        String webRootPath = ServletActionContext.getServletContext().getRealPath("/");
        String realpath = webRootPath + "/" + CommonUtil.DOWNLOAD_ROOTPATH;
        boolean fouce = (fouceDownload != null && fouceDownload.equals("fouce"));
        String filePath = serviceContainer.getFtpEnterpriseService().downloadEnterpriseFromFtp
                (Long.parseLong(enterpriseApplyId), realpath,CertType.format(certTypeCode), fouce);
        Map<String, String> result = new HashMap<String, String>();
        if (filePath == null) {
            result.put("status", "fail");
        } else if (filePath.equals("")) {
            result.put("status", "notExist");
        } else {
            result.put("status", "OK");
            result.put("url", filePath);
            new MerchantThread(webRootPath + "/" + filePath).start();
        }
        json_encode(result);
        return null;
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

    public MerchDeta getMerchDeta() {
        return merchDeta;
    }

    public void setMerchDate(MerchDeta merchDeta) {
        this.merchDeta = merchDeta;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public void setMerchDeta(MerchDeta merchDeta) {
        this.merchDeta = merchDeta;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Map<String, Object> getMerchMap() {
        return merchMap;
    }

    public void setMerchMap(Map<String, Object> merchMap) {
        this.merchMap = merchMap;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getMerchApplyId() {
        return merchApplyId;
    }

    public void setMerchApplyId(String merchApplyId) {
        this.merchApplyId = merchApplyId;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getMerchStatus() {
        return merchStatus;
    }

    public void setMerchStatus(String merchStatus) {
        this.merchStatus = merchStatus;
    }

    public String getEnterpriseApplyId() {
        return enterpriseApplyId;
    }

    public void setEnterpriseApplyId(String enterpriseApplyId) {
        this.enterpriseApplyId = enterpriseApplyId;
    }

    public Map<String, Object> getEnterpriseDeta() {
        return enterpriseDeta;
    }

    public void setEnterpriseDeta(Map<String, Object> enterpriseDeta) {
        this.enterpriseDeta = enterpriseDeta;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    
}
