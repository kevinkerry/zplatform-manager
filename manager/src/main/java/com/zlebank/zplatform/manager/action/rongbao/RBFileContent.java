package com.zlebank.zplatform.manager.action.rongbao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.exception.ResolveReconFileContentException;

public class RBFileContent extends AbstractFileContentHandler {

    private List<BnkTxnModel> bnkTxnList;
    private Log log = LogFactory.getLog(RBFileContent.class);
    
    public List<BnkTxnModel> readFile(File[] upload,
            String instiid,
            String[] uploadFileName) throws NumberFormatException, IOException,
            ResolveReconFileContentException {
        if (upload[0] != null) {
            InputStream is = new FileInputStream(upload[0]);
            Workbook wbk;
            try {
                wbk = Workbook.getWorkbook(is);

                for (int k = 0; k < wbk.getNumberOfSheets(); k++) {
                    Sheet rs = wbk.getSheet(k);
                    bnkTxnList = this.getExcelToElement(rs, instiid);
                }
            } catch (BiffException e) {
                log.error("读取文件失败,文件名:"+upload[0].getName()+".请将文件另存为Excel 2003格式后再尝试");
                ResolveReconFileContentException rrfce = new ResolveReconFileContentException();
                rrfce.setParams("读取文件失败.请将文件另存为Excel 2003格式后再尝试");
                rrfce.initCause(e);
                throw rrfce;
            }
        }
        return bnkTxnList;
    }

    public List<BnkTxnModel> getExcelToElement(Sheet rs, String instiid) {
        bnkTxnList = new ArrayList<BnkTxnModel>();
        String acqsettledate = "";
        int row = rs.getRows();
        Cell c1 = null, c2 = null, c3 = null, c4 = null, c5 = null ;

        if (row == 0) {
            return null;
        }
        for (int k = 1; k < row; k++) {
            if (k == 2) {
                c2 = rs.getCell(1, k);// 商户订单号
                acqsettledate = c2.getContents().trim();
            }
            if (k >= 7) {
                // Cell[] cells = rs.getRow(k);
                c1 = rs.getCell(1, k);// 流水号
                c2 = rs.getCell(2, k);// 商户订单号
                c3 = rs.getCell(3, k);// 支付时间
                c4 = rs.getCell(4, k);// 交易类型
                c5 = rs.getCell(5, k);// 交易金额
                // UserModel user = getCurrentUser();
                BnkTxnModel bnk = new BnkTxnModel();
                bnk.setAcqsettledate(c1.getContents().trim());
                bnk.setPaytrcno(c1.getContents().trim());
                bnk.setPayordno(c2.getContents().trim());
                bnk.setInstiid(instiid);
                String txndatetime = c3.getContents().trim().toString();
                bnk.setTxndatetime(txndatetime.replace(".", "")
                        .replace(":", "").replace(" ", ""));
                bnk.setBusicode(c4.getContents().trim());
                bnk.setAcqsettledate(acqsettledate.replace("-", ""));
                if (c5.getContents() != null
                        && !c5.getContents().trim().equals("")) {
                    bnk.setAmount(new BigDecimal(c5.getContents()).multiply(
                            new BigDecimal(100)).longValue());
                }
                bnk.setRetcode("00");
                bnk.setSystrcno(c1.getContents().trim());
                bnkTxnList.add(bnk);
            }
        }
        return bnkTxnList;
    }
}