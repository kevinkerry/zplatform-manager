package com.zlebank.zplatform.manager.action.rongbao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;

public class RBFileContent extends AbstractFileContentHandler {

    private List<BnkTxnModel> bnkTxnList;

    public List<BnkTxnModel> readFile(File[] upload,
            String instiid,
            String[] uploadFileName) throws NumberFormatException, IOException {
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        try {
            // String realpath =
            // ServletActionContext.getServletContext().getRealPath("/merchant");
            if (upload[0] != null) {
                // String fileNameString = generateFileName(headImageFileName);
                InputStream is = new FileInputStream(upload[0]);
                Workbook wbk = Workbook.getWorkbook(is);
                for (int k = 0; k < wbk.getNumberOfSheets(); k++) {
                    Sheet rs = wbk.getSheet(k);
                    bnkTxnList = this.getExcelToElement(rs, instiid);
                }
                if (returnList.size() == 0) {
                    Map<String, Object> valueMap = new HashMap<String, Object>();
                    valueMap.put("RET", "succ");
                    valueMap.put("INFO", "操作成功");
                    returnList.add(valueMap);
                } else {
                    // json_encode(returnList);
                }

            }
        } catch (IOException e) {
            Map<String, Object> valueMap = new HashMap<String, Object>();
            valueMap.put("RET", "error");
            valueMap.put("INFO", "上传文件发生错误，请重新上传！");
            returnList.add(valueMap);
            e.printStackTrace();
        } catch (BiffException e) {
            Map<String, Object> valueMap = new HashMap<String, Object>();
            valueMap.put("RET", "error");
            valueMap.put("INFO", "导入失败！");
            returnList.add(valueMap);
            e.printStackTrace();
        }
        return bnkTxnList;
        // return errorString;

    }

    public List<BnkTxnModel> getExcelToElement(Sheet rs, String instiid) {
        bnkTxnList = new ArrayList<BnkTxnModel>();
        String acqsettledate = "";
        int row = rs.getRows();
        Cell c0 = null, c1 = null, c2 = null, c3 = null, c4 = null, c5 = null, c6 = null;

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
                c0 = rs.getCell(0, k);// 序列
                c1 = rs.getCell(1, k);// 流水号
                c2 = rs.getCell(2, k);// 商户订单号
                c3 = rs.getCell(3, k);// 支付时间
                c4 = rs.getCell(4, k);// 交易类型
                c5 = rs.getCell(5, k);// 交易金额
                c6 = rs.getCell(6, k);// 手续费
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