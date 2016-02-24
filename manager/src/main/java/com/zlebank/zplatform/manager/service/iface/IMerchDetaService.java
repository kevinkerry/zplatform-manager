package com.zlebank.zplatform.manager.service.iface;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

public interface IMerchDetaService extends IBaseService<MerchDetaModel, Long> {
    public List<?> queryCounty(long pid);
    public List<?> queryTrade();
    public List<?> queryMerchType();
    public List<Map<String, String>> saveMerchDeta(MerchDetaModel merch);
    public Map<String, Object> findMerchByPage(Map<String, Object> variables,
            int page,
            int rows);
    public long findMerchByPageCount(Map<String, Object> variables);
    public List<?> queryBankNode(String bankName, int page, int rows);
    public Map<String, Object> queryOneMerchDeta(Long merchId, Long userId);
    public List<?> queryMerchParent();
    public List<?> merchAudit(MerchDetaModel merchDeta,
            String flag,
            String isAgree) throws Exception;
    public List<?> saveChangeMerchDeta(MerchDetaModel merch);
    public List<?> saveMerchMk(Map<String, Object> variables);
    public Map<String, Object> loadMerchMk(String memberId);
    public List<?> queryCashAll();
    public List<?> queryChnlnameAll();
    public List<?> queryRouteAll();
    public List<?> querySetlcycleAll();
    public List<?> querySplit(String pid);
    public List<?> queryRiskType(String pid);
    public List<?> queryFee(String pid);
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
    public boolean upload(long merchId,
            String fileName,
            File file,
            CertType certType);
    /**
     * commit info to apply a merchant
     * 
     * @param merchId
     * @return
     */
    public boolean commitMerch(long merchId);
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

    public String queryBankName(String bankNode, String bankCode);
}
