package com.zlebank.zplatform.manager.service;



import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.certhandler.BusiLicePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CertPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CorpFileFacePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CorpFileOppPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.OrgCertPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.SignFileFacePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.SignFileOppPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.TaxRegCertPicHandler;
import com.zlebank.zplatform.manager.service.iface.IFTPEnterpriseService;
import com.zlebank.zplatform.manager.util.CommonUtil;
import com.zlebank.zplatform.manager.util.net.FTPClientFactory;
import com.zlebank.zplatform.member.service.MemberService;

public class FTPEnterpriseServiceImpl extends
BaseServiceImpl<PojoEnterpriseDetaApply, Long> implements IFTPEnterpriseService {
    private final static Log log = LogFactory.getLog(MerchDetaServiceImpl.class);
    private DAOContainer daoContainer;
    private FTPClientFactory ftpClientFactory;
    private final String merchCertRootPath = "/merchant";
    private MemberService memberServiceImpl;

    
    
    public FTPClientFactory getFtpClientFactory() {
        return ftpClientFactory;
    }


    public void setFtpClientFactory(FTPClientFactory ftpClientFactory) {
        this.ftpClientFactory = ftpClientFactory;
    }


    public MemberService getMemberServiceImpl() {
        return memberServiceImpl;
    }


    public void setMemberServiceImpl(MemberService memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }


    public String getMerchCertRootPath() {
        return merchCertRootPath;
    }


    public DAOContainer getDaoContainer() {
        return daoContainer;
    }


    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }


    @Override
    public IBaseDAO<PojoEnterpriseDetaApply, Long> getDao() {
        daoContainer.getFtpEnterpriseDAO();
        return null;
    }


    @Override
    public String downloadEnterpriseFromFtp(long enterpriseApplyId,
            String targDir,
            CertType certType,
            boolean fouce) {
        
//        PojoEnterpriseDetaApply pojoEnterpriseDetaApply = get(enterpriseApplyId);
//        CertPicHandler certPicHandler = getCertHandler(certType);
//        String fileName = certPicHandler
//                .getFileName((PojoEnterpriseDetaApply) pojoEnterpriseDetaApply
//                        .getMemberApplyId());
//        if (fileName == null) {// not upload yet return "";
//            return "";
//        }
//        targDir = targDir + "/" + pojoEnterpriseDetaApply.getMemberId();
//        if (fouce || !checkLocalExist(targDir, fileName)) {// not exist in local
//            try {
//                ftpClientFactory.getFtpClient().download(
//                        getMerchCertPath(pojoEnterpriseDetaApply.getMemberId()),
//                        fileName, targDir, fileName);
//                if (!checkLocalExist(targDir, fileName)) {
//                    return null;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                log.warn("download from ftp get a exception.caused by:"
//                        + e.getMessage());
//                return null;
//            }
//        }
//        return CommonUtil.DOWNLOAD_ROOTPATH + "/"
//                + pojoEnterpriseDetaApply.getMemberId() + "/" + fileName;
        return targDir;
    }
    private String getMerchCertPath(String memberId) {
        return merchCertRootPath + "/" + String.valueOf(memberId);
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
}
