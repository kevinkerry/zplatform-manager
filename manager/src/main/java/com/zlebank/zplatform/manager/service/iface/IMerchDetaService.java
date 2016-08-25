package com.zlebank.zplatform.manager.service.iface;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.bean.MerchDeta;
import com.zlebank.zplatform.manager.dao.object.PojoMerchDetaApply;
/**
 * <p>
 * Merchant service.
 * </p>
 * <p>
 *  Modify to adapter merchant domain model changed since 1.3.0.
 * </p>
 * @author yangying
 * @version 1.3.0 
 * @date 2016年3月9日 下午5:11:08
 * @since 1.0.0
 * 
 */
public interface IMerchDetaService
        extends
            IBaseService<PojoMerchDetaApply, Long> {

    /**
     * save a merchant info to db.It create tow record,one is in
     * t_merch_deta,the other in t_enterprise. note, it don't add record to
     * t_member.
     * 
     * @param merch
     * @return
     */
    public List<Map<String, String>> saveMerchDeta(MerchDeta merch);
    /**
     * 
     * @param variables
     * @param page
     * @param rows
     * @return
     */
    public Map<String, Object> findMerchByPage(Map<String, Object> variables,
            int page,
            int rows);

    /**
     * deprecated since 1.3.0
     * 
     * @param variables
     * @return
     */
    @Deprecated
    public long findMerchByPageCount(Map<String, Object> variables);
    /**
     * 
     * @param merchId
     * @return
     */
    public MerchDeta getBean(long merchDetaApplyId);
    /**
     * 
     * @param pid
     * @return
     */
    public List<?> queryCounty(long pid);
    /**
     * 
     * @return
     */
    public List<?> queryTrade();
    /**
     * 
     * @return
     */
    public List<?> queryMerchType();
    /**
     * 
     * @return
     */
    public List<?> queryCashAll();
    /**
     * 
     * @return
     */
    public List<?> queryChnlnameAll();
    /**
     * 
     * @return
     */
    public List<?> queryRouteAll();
    /**
     * 
     * @param pid
     * @return
     */
    public List<?> queryRiskType(String pid);
    /**
     * 
     * @param pid
     * @return
     */
    public List<?> querySplit(String pid);
    /**
     * 
     * @param pid
     * @return
     */
    public List<?> queryFee(String pid);
    /**
     * 
     * @param bankName
     * @param page
     * @param rows
     * @return
     */
    public List<?> queryBankNode(String bankName, int page, int rows);
    /**
     * query merchant apply detail by merchApplyId
     * @param merchApplyId
     * @param userId
     * @return
     */
    public Map<String, Object> queryApplyMerchDeta(Long merchApplyId,
            Long userId);
    /**
     * query merchant detail by merchId
     * @param merchApplyId
     * @param userId
     * @param isQueryApply
     * @return
     */
    public Map<String, Object> queryMerchDeta(Long merchId,Long userId);
    /**
     * 
     * @return
     */
    public List<?> queryMerchParent();
    /**
     * <P>
     * Audit a merchant apply.If isAgree is true,means audit pass, then add this
     * merchant apply to official.Else,abandon this merchant apply.
     * </P>
     * <P>If the merchant audit pass,it will copy record from "t_merch_deta_apply" to "t_merch_deta",
     *  copy record from "t_member_apply" to "t_member",and copy record from "t_enterprise_deta_apply" to "t_enterprise_deta".
     *  Then  it will generate merchant MK and create a account  for this merchant.
     * </P>
     * @param merchDeta
     * @param flag
     * @param isAgree
     * @return
     * @throws Exception
     */
    public List<?> merchAudit(long merchApplyId,MerchDeta merchDeta, String flag, String isAgree)
            throws Exception;
    /**
     * 
     * @param merchApplyId
     * @param merch
     * @return
     */
    public List<?> saveChangeMerchDeta(long merchApplyId, MerchDeta merch);
    /*
     * public List<?> saveMerchMk(Map<String, Object> variables); 
     */
    /**
     * download merchant MK
     * @param memberId
     * @return
     */
    public Map<String, Object> loadMerchMk(String memberId);
    /**
     * 
     * @return
     */
    public List<?> querySetlcycleAll();
    /*
     * public List<?> querySplit(String pid); public List<?>
     * queryRiskType(String pid);
     */
    /**
     * 
     * @return
     */
    public List<?> querysetltype();

    /**
     * <p>
     * upload local(usually,it a tomcat server) merchant cert file to ftp
     * server.
     * </p>
     * <p>
     * ftp server dir hierarchy is:[ftp user root path+"/merchant/${merchid}
     * </p>
     * 
     * @param merchId
     *            the unique merchant id
     * @param fileName
     * @param file
     * @param certType
     *            see {@link CertType}
     * @return
     */
    public boolean upload(long merchApplyId,
            String fileName,
            File file,
            CertType certType);
    /**
     * commit info to apply a merchant
     * 
     * @param merchId
     * @return
     */
    public boolean commitMerch(long merchpplyId);
    /**
     * <p>
     * download merchant cert file to local(usually,it a tomcat server) from a
     * ftp server. There is a cache in local dir.It will check if file is exist
     * in local dir,if not, download from ftp server.And return the a url of
     * this file
     * </p>
     * <p>
     * Local dir hierarchy is :[classpath root path+"/download"] and ftp server
     * dir hierarchy is:[ftp user root path+"/merchant/${merchid}"].
     * </p>
     * <p>
     * file name is dependened on CertType
     * </p>
     * 
     * @param merchId
     *            the unique merchant id
     * @param targDir
     *            the local directory the file will download to
     * @param certType
     *            see {@link CertType}
     * @return "" if file not upload so the column value in db is null.</br>null
     *         if file is not exist in local cache dir, neither ftp server.<br>
     *         filePath can be visited
     */
    public String downloadFromFtp(long merchId,
            String targDir,
            CertType certType,
            boolean fouce);
    /**
     * Query bank name by bankNode and bankCode.Should remove from here ,is
     * don't obey SRP
     * 
     * @param bankNode
     * @param bankCode
     * @return
     */
    public String queryBankName(String bankNode, String bankCode);
    public Map<String, Object> findMerchModifyByPage(Map<String, Object> variables,
            int page,
            int rows);
    
    /**
     * commit info to modify a merchant
     * 
     * @param merchId
     * @return
     */
    public boolean commitMerchModify(long merchpplyId);
    public List<?> saveMerchModifyDeta(long parseLong, MerchDeta merchDeta);
    public Map<String, Object> queryModifyMerchDeta(long merchApply, Long userId);
}



