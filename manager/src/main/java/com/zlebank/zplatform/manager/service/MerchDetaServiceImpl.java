package com.zlebank.zplatform.manager.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.utils.net.ftp.AbstractFTPClient;
import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.bean.Enterprise;
import com.zlebank.zplatform.manager.bean.MerchDeta;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.MccListModel;
import com.zlebank.zplatform.manager.dao.object.ParaDicModel;
import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;
import com.zlebank.zplatform.manager.dao.object.PojoMerchDetaApply;
import com.zlebank.zplatform.manager.dao.object.scan.MemberQueueMode;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.certhandler.BusiLicePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CertPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CorpFileFacePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CorpFileOppPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.OrgCertPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.SignFileFacePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.SignFileOppPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.TaxRegCertPicHandler;
import com.zlebank.zplatform.manager.service.iface.IMerchDetaService;
import com.zlebank.zplatform.manager.util.CommonUtil;
import com.zlebank.zplatform.manager.util.RSAUtils;
import com.zlebank.zplatform.manager.util.net.FTPClientFactory;
import com.zlebank.zplatform.member.service.MemberService;

public class MerchDetaServiceImpl
        extends
            BaseServiceImpl<PojoMerchDetaApply, Long>
        implements
            IMerchDetaService {

    private final static Log log = LogFactory
            .getLog(MerchDetaServiceImpl.class);
    private DAOContainer daoContainer;
    private FTPClientFactory ftpClientFactory;
    private final String merchCertRootPath = "/merchant";
    private MemberService memberServiceImpl;

    @Override
    public IBaseDAO<PojoMerchDetaApply, Long> getDao() {
        return daoContainer.getMerchDetaDAO();
    }

    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Map<String, String>> saveMerchDeta(MerchDeta merch) {

        Enterprise enterprise = merch.getMember();

        boolean isRepeat = getDaoContainer().getMerchDetaDAO().isRepeat(
                merch.getMember().getEmail(), enterprise.getPhone(),
                enterprise.getInstiCode());

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        Map<String, String> resultMap = new HashMap<String, String>();
        if (isRepeat) {
            resultMap.put("RET", "fail");
            resultMap.put("INFO", "手机号或邮箱重复");
            result.add(resultMap);
            return result;
        }

        MccListModel mccList = daoContainer.getMccListDAO().get(
                enterprise.getMccList());
        enterprise.setMcc(mccList.getMcc());

        String[] columns = new String[]{"v_parent", "v_setlcycle",
                "v_setltype", "v_bankcode", "v_banknode", "v_accnum",
                "v_accname", "v_charge", "v_deposit", "v_agreemt_start",
                "v_agreemt_end", "v_prdtver", "v_feever", "v_spiltver",
                "v_riskver", "v_routver", "v_inuser", "v_notes", "v_remarks",
                "v_merch_name_e", "v_coop_insti_id_e", "v_cellphoneno",
                "v_mcc_e", "v_mcclist_e", "v_merchinsti_e", "v_province_e",
                "v_city_e", "v_street_e", "v_postcode_e", "v_address_e",
                "v_email_e", "v_website_e", "v_cardtype_e", "v_taxno_e",
                "v_licenceno_e", "v_orgcode_e", "v_corporation_e",
                "v_corpno_e", "v_contact_e", "v_contphone_e", "v_conttitle_e",
                "v_contemail_e", "v_contaddress_e", "v_contpost_e",
                "v_custfrom_e", "v_custmgr_e", "v_custmgrdept_e",
                "v_isdelegation_e", "v_signatory_e", "v_signcertno_e",
                "v_notes_e", "v_remarks_e"};
        Object[] paramaters = new Object[]{
                "".equals(merch.getParent()) ? null : "".equals(merch
                        .getParent()),
                "".equals(merch.getSetlCycle()) ? null : merch.getSetlCycle(),
                "".equals(merch.getSetlType()) ? null : merch.getSetlType(),
                "".equals(merch.getBankCode()) ? null : merch.getBankCode(),
                "".equals(merch.getBankNode()) ? null : merch.getBankNode(),
                "".equals(merch.getAccNum()) ? null : merch.getAccNum(),
                "".equals(merch.getAccName()) ? null : merch.getAccName(),
                merch.getCharge().toYuan(),
                merch.getDeposit().toYuan(),
                merch.getAgreemtStart()==null? null : new Timestamp(merch.getAgreemtStart().getTime()),
                merch.getAgreemtEnd()==null ? null : new Timestamp(merch.getAgreemtEnd().getTime()),
                "".equals(merch.getPrdtVer()) ? null : merch.getPrdtVer(),
                "".equals(merch.getFeeVer()) ? null : merch.getFeeVer(),
                "".equals(merch.getSpiltVer()) ? null : merch.getSpiltVer(),
                "".equals(merch.getRiskVer()) ? null : merch.getRiskVer(),
                "".equals(merch.getRoutVer()) ? null : merch.getRoutVer(),
                "".equals(enterprise.getInUser()) ? null : enterprise
                        .getInUser(),
                "".equals(merch.getNotes()) ? null : merch.getNotes(),
                "".equals(merch.getRemarks()) ? null : merch.getRemarks(),
                "".equals(enterprise.getEnterpriseName()) ? null : enterprise
                        .getEnterpriseName(),
                "".equals(enterprise.getCoopInstiId()) ? null : enterprise
                        .getCoopInstiId(),
                "".equals(enterprise.getPhone()) ? null : enterprise.getPhone(),

                "".equals(enterprise.getMcc()) ? null : enterprise.getMcc(),
                "".equals(enterprise.getMccList()) ? null : enterprise
                        .getMccList(),
                "".equals(enterprise.getEnterpriseInsti()) ? null : enterprise
                        .getEnterpriseInsti(),
                "".equals(enterprise.getProvince()) ? null : enterprise
                        .getProvince(),
                "".equals(enterprise.getCity()) ? null : enterprise.getCity(),
                "".equals(enterprise.getStreet()) ? null : enterprise
                        .getStreet(),
                "".equals(enterprise.getPostCode()) ? null : enterprise
                        .getPostCode(),
                "".equals(enterprise.getAddress()) ? null : enterprise
                        .getAddress(),
                "".equals(enterprise.getEmail()) ? null : enterprise.getEmail(),
                "".equals(enterprise.getWebsite()) ? null : enterprise
                        .getWebsite(),
                "".equals(enterprise.getCardType()) ? null : enterprise
                        .getCardType(),
                "".equals(enterprise.getTaxno()) ? null : enterprise.getTaxno(),
                "".equals(enterprise.getLicenceNo()) ? null : enterprise
                        .getLicenceNo(),
                "".equals(enterprise.getOrgCode()) ? null : enterprise
                        .getOrgCode(),
                "".equals(enterprise.getCorporation()) ? null : enterprise
                        .getCorporation(),
                "".equals(enterprise.getCorpNo()) ? null : enterprise
                        .getCorpNo(),
                "".equals(enterprise.getContact()) ? null : enterprise
                        .getContact(),
                "".equals(enterprise.getContPhone()) ? null : enterprise
                        .getContPhone(),
                "".equals(enterprise.getContTitle()) ? null : enterprise
                        .getContTitle(),
                "".equals(enterprise.getContEmail()) ? null : enterprise
                        .getContEmail(),
                "".equals(enterprise.getContAddress()) ? null : enterprise
                        .getContAddress(),
                "".equals(enterprise.getContPost()) ? null : enterprise
                        .getContPost(),
                "".equals(enterprise.getCustFrom()) ? null : enterprise
                        .getCustFrom(),
                "".equals(enterprise.getCustMgr()) ? null : enterprise
                        .getCustMgr(),
                "".equals(enterprise.getCustMgrDept()) ? null : enterprise
                        .getCustMgrDept(),
                "".equals(enterprise.getIsDelegation()) ? null : enterprise
                        .getIsDelegation(),
                "".equals(enterprise.getSignatory()) ? null : enterprise
                        .getSignatory(),
                "".equals(enterprise.getSignCertNo()) ? null : enterprise
                        .getSignCertNo(),
                "".equals(enterprise.getNotes()) ? null : enterprise.getNotes(),
                "".equals(enterprise.getRemarks()) ? null : enterprise
                        .getRemarks()};
        List<?> dbResult = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_MERCH.pro_i_t_merch_deta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0");
        if (dbResult == null || dbResult.get(0) == null) {
            resultMap.clear();
            resultMap.put("RET", "fail");
            resultMap.put("INFO", "操作失败,请联系技术人员");
            result.add(resultMap);
        } else {
           
            resultMap = (Map<String, String>) dbResult.get(0);
            log.info(resultMap.get("RET"));
            log.info(resultMap.get("INFO"));
        }
        result.add(resultMap);
        return result;
    }
    public Map<String, Object> findMerchByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_user", "v_member_id",
                "v_merch_name", "v_address", "v_status", "v_coop_insti_id",
                "v_flag", "i_no", "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("userId")
                        ? variables.get("userId")
                        : null,
                variables.containsKey("merberId")
                        ? variables.get("merberId")
                        : null,
                variables.containsKey("merchName")
                        ? variables.get("merchName")
                        : null,
                variables.containsKey("address")
                        ? variables.get("address")
                        : null,
                variables.containsKey("status")
                        ? variables.get("status")
                        : null,
                variables.containsKey("coopInstiId") ? variables
                        .get("coopInstiId") : null,
                variables.containsKey("flag") ? variables.get("flag") : null,
                page, rows};
        // busiAcctServiceImpl.openBusiAcct(member, busiAcct, userId)
        return getDao().executePageOracleProcedure(
                "{CALL PCK_MERCH.sel_t_merchant(?,?,?,?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");

    }


 




 

 
 

 
 

 

 




 

 



    public MemberService getMemberServiceImpl() {
        return memberServiceImpl;
    }

    public void setMemberServiceImpl(MemberService memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    public FTPClientFactory getFtpClientFactory() {
        return ftpClientFactory;
    }

    public void setFtpClientFactory(FTPClientFactory ftpClientFactory) {
        this.ftpClientFactory = ftpClientFactory;
    }

   

    @Override
    public boolean commitMerchModify(long merchpplyId) {
        String[] columns = new String[]{"v_self_id"};
        Object[] paramaters = new Object[1];
        paramaters[0] = merchpplyId;
        List<?> result = getDao().executeOracleProcedure(
                "{CALL  PCK_MERCH.addi_merch_deta(?,?)}", columns, paramaters,
                "cursor0");
        boolean isSucc = false;
        if (result != null && !(result.get(0) == null)) {

            @SuppressWarnings("unchecked")
            Map<String, String> resultMap = (Map<String, String>) result.get(0);
            if (resultMap.containsKey("RET")
                    && resultMap.get("RET").equals("succ")) {
                isSucc = true;
            }
        }
        return isSucc;
    }

    @Override
    public List<?> saveMerchModifyDeta(long merchApplyId, MerchDeta merchDeta) {
        boolean hasSame = getDaoContainer().getMerchDetaDAO().hasSame(
                merchDeta.getMemberId(), merchDeta.getMember().getEmail(),
                merchDeta.getMember().getPhone(),
                merchDeta.getMember().getCoopInstiId());
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        Map<String, String> resultMap = new HashMap<String, String>();
        if (hasSame) {
            resultMap.put("RET", "fail");
            resultMap.put("INFO", "手机号或邮箱重复");
            result.add(resultMap);
            return result;
        }

        PojoMerchDetaApply oldMerchApplyInfo = daoContainer.getMerchDetaDAO()
                .get(merchApplyId);

        if (merchDeta.getBankNode() == null
                || merchDeta.getBankNode().equals("")) {
            merchDeta.setBankCode(oldMerchApplyInfo.getBankCode());
            merchDeta.setBankNode(oldMerchApplyInfo.getBankNode());
        } else {
            Object[] paramaters = merchDeta.getBankNode().split(",");
            merchDeta.setBankCode(paramaters[0].toString());
            merchDeta.setBankNode(paramaters[1].toString());
        }
        MccListModel mccList = daoContainer.getMccListDAO().get(
                merchDeta.getMember().getMccList());
        merchDeta.getMember().setMcc(mccList.getMcc());
        merchDeta.setmInUser(oldMerchApplyInfo.getmInUser());
        merchDeta.getMember().setMemId(
                oldMerchApplyInfo.getMemberApply().getMemId());
        merchDeta.setMerchId(oldMerchApplyInfo.getMerchId());
        merchDeta.setMemberId(oldMerchApplyInfo.getMemberId());
        String[] columns = new String[]{"v_self_id", "v_merch_id", "v_mem_id",
                "v_member_id", "v_parent", "v_setlcycle", "v_setltype",
                "v_bankcode", "v_banknode", "v_accnum", "v_accname",
                "v_charge", "v_deposit", "v_agreemt_start", "v_agreemt_end",
                "v_prdtver", "v_feever", "v_spiltver", "v_riskver",
                "v_routver", "v_inuser", "v_notes", "v_remarks",
                "v_merch_name_e", "v_coop_insti_id_e", "v_cellphoneno",
                "v_mcc_e", "v_mcclist_e", "v_merchinsti_e", "v_province_e",
                "v_city_e", "v_street_e", "v_postcode_e", "v_address_e",
                "v_email_e", "v_website_e", "v_cardtype_e", "v_taxno_e",
                "v_licenceno_e", "v_orgcode_e", "v_corporation_e",
                "v_corpno_e", "v_contact_e", "v_contphone_e", "v_conttitle_e",
                "v_contemail_e", "v_contaddress_e", "v_contpost_e",
                "v_custfrom_e", "v_custmgr_e", "v_custmgrdept_e",
                "v_isdelegation_e", "v_signatory_e", "v_signcertno_e",
                "v_notes_e", "v_remarks_e"};
        Object[] paramaters = new Object[]{
                merchApplyId,
                merchDeta.getMerchId(),
                merchDeta.getMember().getMemId(),
                merchDeta.getMemberId(),
                "".equals(merchDeta.getParent()) ? null : "".equals(merchDeta
                        .getParent()),
                "".equals(merchDeta.getSetlCycle()) ? null : merchDeta
                        .getSetlCycle(),
                "".equals(merchDeta.getSetlType()) ? null : merchDeta
                        .getSetlType(),
                "".equals(merchDeta.getBankCode()) ? null : merchDeta
                        .getBankCode(),
                "".equals(merchDeta.getBankNode()) ? null : merchDeta
                        .getBankNode(),
                "".equals(merchDeta.getAccNum()) ? null : merchDeta.getAccNum(),
                "".equals(merchDeta.getAccName()) ? null : merchDeta
                        .getAccName(),
                merchDeta.getCharge().toYuan(),
                merchDeta.getDeposit().toYuan(),
                merchDeta.getAgreemtStart()==null? null : new Timestamp(merchDeta.getAgreemtStart().getTime()),
                merchDeta.getAgreemtEnd()==null ? null : new Timestamp(merchDeta.getAgreemtEnd().getTime()),
                "".equals(merchDeta.getPrdtVer()) ? null : merchDeta
                        .getPrdtVer(),
                "".equals(merchDeta.getFeeVer()) ? null : merchDeta.getFeeVer(),
                "".equals(merchDeta.getSpiltVer()) ? null : merchDeta
                        .getSpiltVer(),
                "".equals(merchDeta.getRiskVer()) ? null : merchDeta
                        .getRiskVer(),
                "".equals(merchDeta.getRoutVer()) ? null : merchDeta
                        .getRoutVer(),
                "".equals(merchDeta.getMember().getInUser()) ? null : merchDeta
                        .getMember().getInUser(),
                "".equals(merchDeta.getNotes()) ? null : merchDeta.getNotes(),
                "".equals(merchDeta.getRemarks()) ? null : merchDeta
                        .getRemarks(),
                "".equals(merchDeta.getMember().getEnterpriseName())
                        ? null
                        : merchDeta.getMember().getEnterpriseName(),
                "".equals(merchDeta.getMember().getCoopInstiId())
                        ? null
                        : merchDeta.getMember().getCoopInstiId(),
                "".equals(merchDeta.getMember().getPhone()) ? null : merchDeta
                        .getMember().getPhone(),

                "".equals(merchDeta.getMember().getMcc()) ? null : merchDeta
                        .getMember().getMcc(),
                "".equals(merchDeta.getMember().getMccList())
                        ? null
                        : merchDeta.getMember().getMccList(),
                "".equals(merchDeta.getMember().getEnterpriseInsti())
                        ? null
                        : merchDeta.getMember().getEnterpriseInsti(),
                "".equals(merchDeta.getMember().getProvince())
                        ? null
                        : merchDeta.getMember().getProvince(),
                "".equals(merchDeta.getMember().getCity()) ? null : merchDeta
                        .getMember().getCity(),
                "".equals(merchDeta.getMember().getStreet()) ? null : merchDeta
                        .getMember().getStreet(),
                "".equals(merchDeta.getMember().getPostCode())
                        ? null
                        : merchDeta.getMember().getPostCode(),
                "".equals(merchDeta.getMember().getAddress())
                        ? null
                        : merchDeta.getMember().getAddress(),
                "".equals(merchDeta.getMember().getEmail()) ? null : merchDeta
                        .getMember().getEmail(),
                "".equals(merchDeta.getMember().getWebsite())
                        ? null
                        : merchDeta.getMember().getWebsite(),
                "".equals(merchDeta.getMember().getCardType())
                        ? null
                        : merchDeta.getMember().getCardType(),
                "".equals(merchDeta.getMember().getTaxno()) ? null : merchDeta
                        .getMember().getTaxno(),
                "".equals(merchDeta.getMember().getLicenceNo())
                        ? null
                        : merchDeta.getMember().getLicenceNo(),
                "".equals(merchDeta.getMember().getOrgCode())
                        ? null
                        : merchDeta.getMember().getOrgCode(),
                "".equals(merchDeta.getMember().getCorporation())
                        ? null
                        : merchDeta.getMember().getCorporation(),
                "".equals(merchDeta.getMember().getCorpNo()) ? null : merchDeta
                        .getMember().getCorpNo(),
                "".equals(merchDeta.getMember().getContact())
                        ? null
                        : merchDeta.getMember().getContact(),
                "".equals(merchDeta.getMember().getContPhone())
                        ? null
                        : merchDeta.getMember().getContPhone(),
                "".equals(merchDeta.getMember().getContTitle())
                        ? null
                        : merchDeta.getMember().getContTitle(),
                "".equals(merchDeta.getMember().getContEmail())
                        ? null
                        : merchDeta.getMember().getContEmail(),
                "".equals(merchDeta.getMember().getContAddress())
                        ? null
                        : merchDeta.getMember().getContAddress(),
                "".equals(merchDeta.getMember().getContPost())
                        ? null
                        : merchDeta.getMember().getContPost(),
                "".equals(merchDeta.getMember().getCustFrom())
                        ? null
                        : merchDeta.getMember().getCustFrom(),
                "".equals(merchDeta.getMember().getCustMgr())
                        ? null
                        : merchDeta.getMember().getCustMgr(),
                "".equals(merchDeta.getMember().getCustMgrDept())
                        ? null
                        : merchDeta.getMember().getCustMgrDept(),
                "".equals(merchDeta.getMember().getIsDelegation())
                        ? null
                        : merchDeta.getMember().getIsDelegation(),
                "".equals(merchDeta.getMember().getSignatory())
                        ? null
                        : merchDeta.getMember().getSignatory(),
                "".equals(merchDeta.getMember().getSignCertNo())
                        ? null
                        : merchDeta.getMember().getSignCertNo(),
                "".equals(merchDeta.getMember().getNotes()) ? null : merchDeta
                        .getMember().getNotes(),
                "".equals(merchDeta.getMember().getRemarks())
                        ? null
                        : merchDeta.getMember().getRemarks()};
        List<?> dbResult = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_MERCH.pro_u_t_merch_deta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0");
        if (dbResult == null || dbResult.get(0) == null) {
            resultMap.clear();
            resultMap.put("RET", "fail");
            resultMap.put("INFO", "操作失败,请联系技术人员");
            result.add(resultMap);
        } else {
            resultMap = (Map<String, String>) dbResult.get(0);
        }
        result.add(resultMap);
        return result;
    }

    @Override
    public long findMerchByPageCount(Map<String, Object> variables) {
        String[] columns = new String[]{"v_user", "v_memberid", "v_merch_name",
                "v_address", "v_status", "v_flag",};

        Object[] paramaters = new Object[]{
                variables.containsKey("userId")
                        ? variables.get("userId")
                        : null,
                variables.containsKey("merberId")
                        ? variables.get("merberId")
                        : null,
                variables.containsKey("merchName")
                        ? variables.get("merchName")
                        : null,
                variables.containsKey("address")
                        ? variables.get("address")
                        : null,
                variables.containsKey("status")
                        ? variables.get("status")
                        : null,
                variables.containsKey("coopInstiId") ? variables
                        .get("coopInstiId") : null,
                variables.containsKey("flag") ? variables.get("flag") : null,};

        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_MERCH.sel_t_merchant_foract_num(?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("TOTAL");
        return Long.valueOf(total.toString());
    }

    @Override
    public MerchDeta getBean(long merchDetaApplyId) {
        PojoMerchDetaApply pojoMerchDetaApply = getDao().get(merchDetaApplyId);

        if (pojoMerchDetaApply == null) {
            return null;
        }

        Enterprise tarEnterprise = new Enterprise();
        MerchDeta tarMerchDeta = new MerchDeta();

        BeanUtils.copyProperties(
                (PojoEnterpriseDetaApply) pojoMerchDetaApply.getMemberApply(),
                tarEnterprise);
        BeanUtils.copyProperties(pojoMerchDetaApply, tarMerchDeta, "member");
        tarMerchDeta.setMember(tarEnterprise);
        return tarMerchDeta;
    }

    @SuppressWarnings("unchecked")
    public List<?> saveChangeMerchDeta(long merchApplyId, MerchDeta merchDeta) {
        boolean hasSame = getDaoContainer().getMerchDetaDAO().hasSame(
                merchDeta.getMemberId(), merchDeta.getMember().getEmail(),
                merchDeta.getMember().getPhone(),
                merchDeta.getMember().getCoopInstiId());
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        Map<String, String> resultMap = new HashMap<String, String>();
        if (hasSame) {
            resultMap.put("RET", "fail");
            resultMap.put("INFO", "手机号或邮箱重复");
            result.add(resultMap);
            return result;
        }

        PojoMerchDetaApply oldMerchApplyInfo = daoContainer.getMerchDetaDAO()
                .get(merchApplyId);

        if (merchDeta.getBankNode() == null
                || merchDeta.getBankNode().equals("")) {
            merchDeta.setBankCode(oldMerchApplyInfo.getBankCode());
            merchDeta.setBankNode(oldMerchApplyInfo.getBankNode());
        } else {
            Object[] paramaters = merchDeta.getBankNode().split(",");
            merchDeta.setBankCode(paramaters[0].toString());
            merchDeta.setBankNode(paramaters[1].toString());
        }
        MccListModel mccList = daoContainer.getMccListDAO().get(
                merchDeta.getMember().getMccList());
        merchDeta.getMember().setMcc(mccList.getMcc());
        merchDeta.setmInUser(oldMerchApplyInfo.getmInUser());
        merchDeta.getMember().setMemId(
                oldMerchApplyInfo.getMemberApply().getMemId());
        merchDeta.setMerchId(oldMerchApplyInfo.getMerchId());
        merchDeta.setMemberId(oldMerchApplyInfo.getMemberId());
        String[] columns = new String[]{"v_self_id", "v_merch_id", "v_mem_id",
                "v_member_id", "v_parent", "v_setlcycle", "v_setltype",
                "v_bankcode", "v_banknode", "v_accnum", "v_accname",
                "v_charge", "v_deposit", "v_agreemt_start", "v_agreemt_end",
                "v_prdtver", "v_feever", "v_spiltver", "v_riskver",
                "v_routver", "v_inuser", "v_notes", "v_remarks",
                "v_merch_name_e", "v_coop_insti_id_e", "v_cellphoneno",
                "v_mcc_e", "v_mcclist_e", "v_merchinsti_e", "v_province_e",
                "v_city_e", "v_street_e", "v_postcode_e", "v_address_e",
                "v_email_e", "v_website_e", "v_cardtype_e", "v_taxno_e",
                "v_licenceno_e", "v_orgcode_e", "v_corporation_e",
                "v_corpno_e", "v_contact_e", "v_contphone_e", "v_conttitle_e",
                "v_contemail_e", "v_contaddress_e", "v_contpost_e",
                "v_custfrom_e", "v_custmgr_e", "v_custmgrdept_e",
                "v_isdelegation_e", "v_signatory_e", "v_signcertno_e",
                "v_notes_e", "v_remarks_e"};
        Object[] paramaters = new Object[]{
                merchApplyId,
                merchDeta.getMerchId(),
                merchDeta.getMember().getMemId(),
                merchDeta.getMemberId(),
                "".equals(merchDeta.getParent()) ? null : "".equals(merchDeta
                        .getParent()),
                "".equals(merchDeta.getSetlCycle()) ? null : merchDeta
                        .getSetlCycle(),
                "".equals(merchDeta.getSetlType()) ? null : merchDeta
                        .getSetlType(),
                "".equals(merchDeta.getBankCode()) ? null : merchDeta
                        .getBankCode(),
                "".equals(merchDeta.getBankNode()) ? null : merchDeta
                        .getBankNode(),
                "".equals(merchDeta.getAccNum()) ? null : merchDeta.getAccNum(),
                "".equals(merchDeta.getAccName()) ? null : merchDeta
                        .getAccName(),
                merchDeta.getCharge().toYuan(),
                merchDeta.getDeposit().toYuan(),
                merchDeta.getAgreemtStart()==null? null : new Timestamp(merchDeta.getAgreemtStart().getTime()),
                merchDeta.getAgreemtEnd()==null ? null : new Timestamp(merchDeta.getAgreemtEnd().getTime()),
                "".equals(merchDeta.getPrdtVer()) ? null : merchDeta
                        .getPrdtVer(),
                "".equals(merchDeta.getFeeVer()) ? null : merchDeta.getFeeVer(),
                "".equals(merchDeta.getSpiltVer()) ? null : merchDeta
                        .getSpiltVer(),
                "".equals(merchDeta.getRiskVer()) ? null : merchDeta
                        .getRiskVer(),
                "".equals(merchDeta.getRoutVer()) ? null : merchDeta
                        .getRoutVer(),
                "".equals(merchDeta.getMember().getInUser()) ? null : merchDeta
                        .getMember().getInUser(),
                "".equals(merchDeta.getNotes()) ? null : merchDeta.getNotes(),
                "".equals(merchDeta.getRemarks()) ? null : merchDeta
                        .getRemarks(),
                "".equals(merchDeta.getMember().getEnterpriseName())
                        ? null
                        : merchDeta.getMember().getEnterpriseName(),
                "".equals(merchDeta.getMember().getCoopInstiId())
                        ? null
                        : merchDeta.getMember().getCoopInstiId(),
                "".equals(merchDeta.getMember().getPhone()) ? null : merchDeta
                        .getMember().getPhone(),

                "".equals(merchDeta.getMember().getMcc()) ? null : merchDeta
                        .getMember().getMcc(),
                "".equals(merchDeta.getMember().getMccList())
                        ? null
                        : merchDeta.getMember().getMccList(),
                "".equals(merchDeta.getMember().getEnterpriseInsti())
                        ? null
                        : merchDeta.getMember().getEnterpriseInsti(),
                "".equals(merchDeta.getMember().getProvince())
                        ? null
                        : merchDeta.getMember().getProvince(),
                "".equals(merchDeta.getMember().getCity()) ? null : merchDeta
                        .getMember().getCity(),
                "".equals(merchDeta.getMember().getStreet()) ? null : merchDeta
                        .getMember().getStreet(),
                "".equals(merchDeta.getMember().getPostCode())
                        ? null
                        : merchDeta.getMember().getPostCode(),
                "".equals(merchDeta.getMember().getAddress())
                        ? null
                        : merchDeta.getMember().getAddress(),
                "".equals(merchDeta.getMember().getEmail()) ? null : merchDeta
                        .getMember().getEmail(),
                "".equals(merchDeta.getMember().getWebsite())
                        ? null
                        : merchDeta.getMember().getWebsite(),
                "".equals(merchDeta.getMember().getCardType())
                        ? null
                        : merchDeta.getMember().getCardType(),
                "".equals(merchDeta.getMember().getTaxno()) ? null : merchDeta
                        .getMember().getTaxno(),
                "".equals(merchDeta.getMember().getLicenceNo())
                        ? null
                        : merchDeta.getMember().getLicenceNo(),
                "".equals(merchDeta.getMember().getOrgCode())
                        ? null
                        : merchDeta.getMember().getOrgCode(),
                "".equals(merchDeta.getMember().getCorporation())
                        ? null
                        : merchDeta.getMember().getCorporation(),
                "".equals(merchDeta.getMember().getCorpNo()) ? null : merchDeta
                        .getMember().getCorpNo(),
                "".equals(merchDeta.getMember().getContact())
                        ? null
                        : merchDeta.getMember().getContact(),
                "".equals(merchDeta.getMember().getContPhone())
                        ? null
                        : merchDeta.getMember().getContPhone(),
                "".equals(merchDeta.getMember().getContTitle())
                        ? null
                        : merchDeta.getMember().getContTitle(),
                "".equals(merchDeta.getMember().getContEmail())
                        ? null
                        : merchDeta.getMember().getContEmail(),
                "".equals(merchDeta.getMember().getContAddress())
                        ? null
                        : merchDeta.getMember().getContAddress(),
                "".equals(merchDeta.getMember().getContPost())
                        ? null
                        : merchDeta.getMember().getContPost(),
                "".equals(merchDeta.getMember().getCustFrom())
                        ? null
                        : merchDeta.getMember().getCustFrom(),
                "".equals(merchDeta.getMember().getCustMgr())
                        ? null
                        : merchDeta.getMember().getCustMgr(),
                "".equals(merchDeta.getMember().getCustMgrDept())
                        ? null
                        : merchDeta.getMember().getCustMgrDept(),
                "".equals(merchDeta.getMember().getIsDelegation())
                        ? null
                        : merchDeta.getMember().getIsDelegation(),
                "".equals(merchDeta.getMember().getSignatory())
                        ? null
                        : merchDeta.getMember().getSignatory(),
                "".equals(merchDeta.getMember().getSignCertNo())
                        ? null
                        : merchDeta.getMember().getSignCertNo(),
                "".equals(merchDeta.getMember().getNotes()) ? null : merchDeta
                        .getMember().getNotes(),
                "".equals(merchDeta.getMember().getRemarks())
                        ? null
                        : merchDeta.getMember().getRemarks()};
        List<?> dbResult = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_MERCH.pro_u_t_merch_deta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0");
        if (dbResult == null || dbResult.get(0) == null) {
            resultMap.clear();
            resultMap.put("RET", "fail");
            resultMap.put("INFO", "操作失败,请联系技术人员");
            result.add(resultMap);
        } else {
            resultMap = (Map<String, String>) dbResult.get(0);
        }
        result.add(resultMap);
        return result;
    }

    @Override
    public List<?> queryCounty(long pid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = pid;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_county(?,?)}", columns, paramaters,
                "cursor0");
    }
    @Override
    public List<?> queryTrade() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_dic_TRADE(?,?)}", columns,
                paramaters, "cursor0");
    }
    @Override
    public List<?> queryMerchType() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_dic_MERCHTYPE(?,?)}", columns,
                paramaters, "cursor0");
    }
    @Override
    public List<?> queryBankNode(String bankName, int page, int rows) {
        String[] columns = new String[]{"v_bank_name", "v_bank_address",
                "i_no", "i_perno"};
        Object[] paramaters = new Object[4];
        paramaters[0] = bankName;
        paramaters[1] = null;
        paramaters[2] = page;
        paramaters[3] = rows;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_t_bank_info(?,?,?,?,?)}", columns,
                paramaters, "cursor0");
    }
    @Override
    public Map<String, Object> queryApplyMerchDeta(Long merchApplyId,
            Long userId) {
        PojoMerchDetaApply pojoMerchApply = get(merchApplyId);
        String[] columns = new String[]{"v_user", "v_self_id", "v_merch_id"};
        Object[] paramaters = new Object[3];
        paramaters[0] = userId;
        paramaters[1] = pojoMerchApply.getSelfId();
        paramaters[2] = pojoMerchApply.getMerchId();
        return (Map<String, Object>) getDao().executeOracleProcedure(
                "{CALL  PCK_MERCH.sel_t_merchant_apply_deta(?,?,?,?)}",
                columns, paramaters, "cursor0").get(0);
    }

    @Override
    public Map<String, Object> queryMerchDeta(Long merchId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public List<?> queryMerchParent() {
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_parent_merch (?)}", new String[]{},
                new Object[]{}, "cursor0");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<?> merchAudit(long merchApplyId,
            MerchDeta merchDeta,
            String flag,
            String isAgree) throws Exception {
        
        String[] columns = new String[]{"v_user", "v_merch_id", "v_self_id",
                "v_opinion", "v_isagree", "v_flag"};
        Object[] paramaters = new Object[6];
        if (flag.equals("2")) {
            paramaters[0] = merchDeta.getStexaUser();
            paramaters[3] = merchDeta.getStexaOpt();
        } else if(flag.equals("3")){
            paramaters[0] = merchDeta.getCvlexaUser();
            paramaters[3] = merchDeta.getCvlexaOpt();
        } else if(flag.equals("5")){
            paramaters[0] = merchDeta.getStexaUser();
            paramaters[3] = merchDeta.getStexaOpt();
        } else if(flag.equals("6")){
            paramaters[0] = merchDeta.getCvlexaUser();
            paramaters[3] = merchDeta.getCvlexaOpt();
        }

        paramaters[1] = merchDeta.getMerchId();
        paramaters[2] = merchApplyId;
        paramaters[4] = isAgree;
        paramaters[5] = null;
        List<Map<String, Object>> resultlist = getDao().executeOracleProcedure(
                "{CALL  PCK_MERCH.exam_merch_deta(?,?,?,?,?,?,?)}", columns,
                paramaters, "cursor0");
        String mark = (String) resultlist.get(0).get("INFO");

        if (mark.equals("操作成功!") && isAgree.equals("0") && flag.equals("3")) {
            // 复审通过，flag=3
            PojoMerchDetaApply pojoMerchDetaApply = get(merchApplyId);
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("merberId", pojoMerchDetaApply.getMemberId());
            // 商户秘钥
            Map<String, Object> merch_keyMap = RSAUtils.genKeyPair();
            Map<String, Object> plath_keyMap = RSAUtils.genKeyPair();
            String merch_publicKey = RSAUtils.getPublicKey(merch_keyMap);
            String merch_privateKey = RSAUtils.getPrivateKey(merch_keyMap);
            String plath_publicKey = RSAUtils.getPublicKey(plath_keyMap);
            String plath_privateKey = RSAUtils.getPrivateKey(plath_keyMap);
            variables.put("memberpubkey", merch_publicKey);
            variables.put("memberprikey", merch_privateKey);
            variables.put("localpubkey", plath_publicKey);
            variables.put("localprikey", plath_privateKey);
            @SuppressWarnings("unused")
            List<?> MKlist = saveMerchMk(variables);
            // 生成商户账户
            memberServiceImpl.openBusiAcct(pojoMerchDetaApply.getMemberApply()
                    .getMemberName(), pojoMerchDetaApply.getMemberId(),
                    pojoMerchDetaApply.getCvlexaUser());
            //保存商户激活的信息
            MemberQueueMode memberQueue=new MemberQueueMode();
            memberQueue.setMemberId(pojoMerchDetaApply.getMemberId());
            memberQueue.setEmail(pojoMerchDetaApply.getMemberApply().getEmail());
            memberQueue.setSendTimes(0);
            memberQueue.setFlag("01");
            List<?> list  =daoContainer.getEnterpriseDetaDAO().getIdCardByMemberId(pojoMerchDetaApply.getMemberId());
            if(list.size()>0){
                JSONArray jsonArray = JSONArray.fromObject(list);
                JSONObject job = jsonArray.getJSONObject(0);
                ParaDicModel maxsend= daoContainer.getParadicDAO().get(102L);
                ParaDicModel expirationTime= daoContainer.getParadicDAO().get(101L);
                memberQueue.setEmail(job.get("EMAIL").toString());
                memberQueue.setMaxSendTimes(Integer.parseInt(maxsend.getParaCode()));
                memberQueue.setExpirationTime(expirationTime.getParaCode());
                memberQueue.setIdCard(job.get("CORP_NO").toString());
            }
            getDaoContainer().getMemberQueueDAO().save(memberQueue);
        }
        return resultlist;
    }

    public List<?> saveMerchMk(Map<String, Object> variables) {

        String[] columns = new String[]{"v_memberid", "v_safetype",
                "v_memberpubkey", "v_memberprikey", "v_localpubkey",
                "v_localprikey", "v_storgetype", "v_keyflag", "v_notes",
                "v_remarks"};
        Object[] paramaters = new Object[]{
                variables.containsKey("merberId")
                        ? variables.get("merberId")
                        : null,
                "01",
                variables.containsKey("memberpubkey") ? variables
                        .get("memberpubkey") : null,
                variables.containsKey("memberprikey") ? variables
                        .get("memberprikey") : null,
                variables.containsKey("localpubkey") ? variables
                        .get("localpubkey") : null,
                variables.containsKey("localprikey") ? variables
                        .get("localprikey") : null, "01", "1", null, null};
        return getDao()
                .executeOracleProcedure(
                        "{CALL  PCK_T_MERCH_MK.pro_i_t_merch_mk(?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0");
    }

    @Override
    public Map<String, Object> loadMerchMk(String memberId) {
        String[] columns = new String[]{"v_memberid"};
        Object[] paramaters = new Object[1];
        paramaters[0] = memberId;
        return (Map<String, Object>) getDao().executeOracleProcedure(
                "{CALL  PCK_T_MERCH_MK.sel_t_merch_mk_deta (?,?)}", columns,
                paramaters, "cursor0").get(0);
    }

    @Override
    public List<?> queryCashAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_cash(?,?)}", columns, paramaters,
                "cursor0");
    }
    @Override
    public List<?> queryChnlnameAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_chnlname(?,?)}", columns,
                paramaters, "cursor0");
    }
    @Override
    public List<?> queryRouteAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_route(?,?)}", columns, paramaters,
                "cursor0");
    }

    @Override
    public List<?> querySetlcycleAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_dic_setl_cycle(?,?)}", columns,
                paramaters, "cursor0");
    }

    // 查询产品下的分润版本
    @Override
    public List<?> querySplit(String pid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = pid;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_split(?,?)}", columns, paramaters,
                "cursor0");
    }

    // 查询产品下的风控版本
    @Override
    public List<?> queryRiskType(String pid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = pid;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_risk(?,?)}", columns, paramaters,
                "cursor0");
    }

    // 查询产品下的扣率版本
    @Override
    public List<?> queryFee(String pid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = pid;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_fee(?,?)}", columns, paramaters,
                "cursor0");
    }

    // 清算类型
    @Override
    public List<?> querysetltype() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_setltype(?,?)}", columns,
                paramaters, "cursor0");
    }
    @Override
    public boolean upload(long merchApplyId,
            String fileName,
            File file,
            CertType certType) {
        if (file == null) {
            log.warn("upload to ftp get a exception.caused by:file is null");
            return false;
        }

        CertPicHandler certHandler = getCertHandler(certType);
        if (certHandler == null) {
            log.warn("upload to ftp get a exception.caused by:certHandler is null");
            return false;
        }

        PojoMerchDetaApply pojoMerchDetaApply = get(merchApplyId);
        if (pojoMerchDetaApply == null) {
            log.warn("upload to ftp get a exception.caused by:merch is not exist");
            return false;
        }

        fileName = certHandler.decorateFileName(fileName);
        AbstractFTPClient ftpClient = ftpClientFactory.getFtpClient();
        try {
            ftpClient.upload(
                    getMerchCertPath(pojoMerchDetaApply.getMemberId()),
                    fileName, file);
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            log.warn("upload to ftp get a exception.caused by:"
                    + e.getMessage());
            return false;
        }
        PojoEnterpriseDetaApply enterpriseDetaApply = certHandler.decorate(
                (PojoEnterpriseDetaApply) pojoMerchDetaApply.getMemberApply(),
                fileName);
        daoContainer.getEnterpriseDetaDAO().update(enterpriseDetaApply);
        return true;
    }

    private String getMerchCertPath(String memberId) {
        return merchCertRootPath + "/" + String.valueOf(memberId);
    }

    private CertPicHandler getCertHandler(CertType certType) {
        switch (certType) {
            case TAXREGCERT :
                return new TaxRegCertPicHandler();
            case BUSILICE :
                return new BusiLicePicHandler();
            case ORGCERT :
                return new OrgCertPicHandler();
            case CORPFILE_FACE :
                return new CorpFileFacePicHandler();
            case CORPFILE_OPPOSITE :
                return new CorpFileOppPicHandler();
            case SIGNATORYFILE_FACE :
                return new SignFileFacePicHandler();
            case SIGNATORYFILE_OPPOSITE :
                return new SignFileOppPicHandler();
            default :
                return null;
        }
    }

    // 开通交易商户注册
    public boolean commitMerch(long merchpplyId) {
        String[] columns = new String[]{"v_self_id"};
        Object[] paramaters = new Object[1];
        paramaters[0] = merchpplyId;
        List<?> result = getDao().executeOracleProcedure(
                "{CALL  PCK_MERCH.addi_merch_deta(?,?)}", columns, paramaters,
                "cursor0");
        boolean isSucc = false;
        if (result != null && !(result.get(0) == null)) {

            @SuppressWarnings("unchecked")
            Map<String, String> resultMap = (Map<String, String>) result.get(0);
            if (resultMap.containsKey("RET")
                    && resultMap.get("RET").equals("succ")) {
                isSucc = true;
            }
        }
        return isSucc;
    }

    @Override
    public String downloadFromFtp(long merchApplyId,
            String targDir,
            CertType certType,
            boolean fouce) {
        PojoMerchDetaApply pojoMerchDetaApply = get(merchApplyId);
        CertPicHandler certPicHandler = getCertHandler(certType);
        String fileName = certPicHandler
                .getFileName((PojoEnterpriseDetaApply) pojoMerchDetaApply
                        .getMemberApply());
        if (fileName == null) {// not upload yet return "";
            return "";
        }
        targDir = targDir + "/" + pojoMerchDetaApply.getMemberId();
        if (fouce || !checkLocalExist(targDir, fileName)) {// not exist in local
            try {
                ftpClientFactory.getFtpClient().download(
                        getMerchCertPath(pojoMerchDetaApply.getMemberId()),
                        fileName, targDir, fileName);
                if (!checkLocalExist(targDir, fileName)) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.warn("download from ftp get a exception.caused by:"
                        + e.getMessage());
                return null;
            }
        }
        return CommonUtil.DOWNLOAD_ROOTPATH + "/"
                + pojoMerchDetaApply.getMemberId() + "/" + fileName;
    }

    public String queryBankName(String bankNode, String bankCode) {
        return daoContainer.getMerchDetaDAO().queryBankName(bankNode, bankCode);
    }
    private boolean checkLocalExist(String dirPath, String fileName) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return false;
        }
        for (File file : files) {
            if (file.isFile() && file.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Map<String, Object> findMerchModifyByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_user", "v_member_id",
                "v_merch_name", "v_address", "v_status", "v_coop_insti_id",
                "v_flag", "i_no", "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("userId")? variables.get("userId"): null,
                variables.containsKey("merberId")? variables.get("merberId"): null,
                variables.containsKey("merchName")? variables.get("merchName"): null,
                variables.containsKey("address")? variables.get("address"): null,
                variables.containsKey("status")? variables.get("status"): null,
                variables.containsKey("coopInstiId") ? variables.get("coopInstiId") : null,
                variables.containsKey("flag") ? variables.get("flag") : null,
                page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_MERCH.sel_t_merchant(?,?,?,?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
    }


    @Override
    public Map<String, Object> queryModifyMerchDeta(long merchApplyId, Long userId) {
        PojoMerchDetaApply pojoMerchApply = get(merchApplyId);
        String[] columns = new String[]{"v_user", "v_self_id", "v_merch_id"};
        Object[] paramaters = new Object[3];
        paramaters[0] = userId;
        paramaters[1] = pojoMerchApply.getSelfId();
        paramaters[2] = pojoMerchApply.getMerchId();
        return (Map<String, Object>) getDao().executeOracleProcedure(
                "{CALL  PCK_MERCH.sel_t_merchant_apply_deta(?,?,?,?)}",
                columns, paramaters, "cursor0").get(0);         
       }

    @Override
    public Map<String, Object> findEnterpriseByPage(Map<String, Object> variables,
            int page,
            int rows) {

        String[] columns = new String[]{
                "v_user", "v_member_id",
                "v_merch_name", "v_address",
                "v_status", "v_coop_insti_id",
                "v_flag", "i_no", "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("userId")? variables.get("userId"): null,
                variables.containsKey("enterpriseMemberId")? variables.get("enterpriseMemberId"): null,
                variables.containsKey("enterpriseName")? variables.get("enterpriseName"): null,
                variables.containsKey("address")? variables.get("address"): null,
                variables.containsKey("status")? variables.get("status"): null,
                variables.containsKey("coopInstiId") ? variables.get("coopInstiId") : null,
                variables.containsKey("flag") ? variables.get("flag") : null,
                page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL pck_enterprise.sel_t_enterprise(?,?,?,?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
    }



    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryEnterpriseExamDeta(long enterpriseApplyId,Long userId) {
        String[] columns = new String[]{"v_user", "v_self_id"};
        Object[] paramaters = new Object[2];
        paramaters[0] = userId;
        paramaters[1] = enterpriseApplyId;
        List<Map<String, Object>> resultList =  getDao().executeOracleProcedure(
                "{CALL  pck_enterprise.sel_t_enterprise_apply_deta(?,?,?)}",columns, paramaters, "cursor0");
        return resultList.get(0);
       }

    @Override
    public List<Map<String, Object>> enterpriseAudit(long enterpriseApplyId,
            Enterprise enterprise,String flag,String isAgree) {
        String[] columns = new String[]{"v_user", "v_self_id", "v_opinion",
                "v_isagree"};
        Object[] paramaters = new Object[4];
        if (flag.equals("2")) {
            paramaters[0] = enterprise.getStexaUser();
            paramaters[2] = enterprise.getStexaOpt();
        } else if(flag.equals("3")){
            paramaters[0] = enterprise.getCvlexaUser();
            paramaters[2] = enterprise.getCvlexaOpt();
        } 
        paramaters[1] = enterpriseApplyId;
        paramaters[3] = isAgree;
        return getDao().executeOracleProcedure(
                "{CALL  pck_enterprise.exam_enterprise(?,?,?,?,?)}", columns,
                paramaters, "cursor0");
    }
}

