package com.zlebank.zplatform.manager.action.chanpay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.exception.ResolveReconFileContentException;
import com.zlebank.zplatform.trade.utils.DateUtil;
/**
 * 
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年5月26日 下午2:35:26
 * @since
 */
public class ChanPayFileContent extends AbstractFileContentHandler {

    private List<BnkTxnModel> bnkTxnList;
    private Log log = LogFactory.getLog(ChanPayFileContent.class);

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
                log.error("读取文件失败,文件名:" + upload[0].getName()
                        + ".请将文件另存为Excel 2003格式后再尝试");
                ResolveReconFileContentException rrfce = new ResolveReconFileContentException();
                rrfce.setParams("读取文件失败.请将文件另存为Excel 2003格式后再尝试");
                rrfce.initCause(e);
                throw rrfce;
            }
        }
        return bnkTxnList;
    }
    private List<BnkTxnModel> getExcelToElement(Sheet rs, String instiid)
            throws ResolveReconFileContentException {
        bnkTxnList = new ArrayList<BnkTxnModel>();
        String acqsettledate = "";
        int row = rs.getRows();
        Cell c1 = null, c2 = null, c3 = null, c5 = null, c6 = null;

        if (row == 0) {
            return null;
        }

        for (int k = 1; k < row; k++) {
            try {
                if (k >= 4) {
                    c1 = rs.getCell(1, k);// 交易发起时间
                    c2 = rs.getCell(2, k);// 商户订单ID
                    c3 = rs.getCell(3, k);// 钱包交易订单号
                    c5 = rs.getCell(5, k);// 支付金额
                    c6 = rs.getCell(6, k);// 手续费
                    // UserModel user = getCurrentUser();
                    BnkTxnModel bnk = new BnkTxnModel();
                    bnk.setTxndatetime(DateUtil.formatDateTime(
                            "yyyyMMddHHmmss", new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss").parse(c1
                                    .getContents().trim())));
                    bnk.setSystrcno(c3.getContents().trim());
                    bnk.setPayordno(c2.getContents().trim());
                    bnk.setInstiid(instiid);

                    // 借记手续费=交易手续费
                    bnk.setDfee(Money
                            .yuanValueOf(
                                    new BigDecimal(c6.getContents().trim()))
                            .getAmount().longValue());
                    //
                    // bnk.setCfee(0L);
                    bnk.setAcqsettledate(acqsettledate.replace("-", ""));
                    if (c5.getContents() != null
                            && !c5.getContents().trim().equals("")) {
                        bnk.setAmount(Money
                                .yuanValueOf(
                                        new BigDecimal(c5.getContents().trim()))
                                .getAmount().longValue());
                    }
                    bnk.setRetcode("00");
                    bnkTxnList.add(bnk);
                }
            } catch (Exception e) {
                log.error("Resolve file content error.Line:" + k);
                ResolveReconFileContentException rrfce = new ResolveReconFileContentException();
                rrfce.setParams("解析文件发生错误,发生在第"+k+"行");
                rrfce.initCause(e);
                throw rrfce;
            }
        }
        return bnkTxnList;
    }
}