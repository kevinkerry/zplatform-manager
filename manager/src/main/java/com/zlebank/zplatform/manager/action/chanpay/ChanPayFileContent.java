package com.zlebank.zplatform.manager.action.chanpay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
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

    public List<BnkTxnModel> readFile(File[] upload,
            String instiid,
            String[] uploadFileName) throws NumberFormatException, IOException {
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        try {
            
            if (upload[0] != null) {
                // String fileNameString = generateFileName(headImageFileName);
                InputStream is = new FileInputStream(upload[0]);
                System.out.println(is);
                Workbook wbk = Workbook.getWorkbook(is);
                System.out.println(wbk);
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
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return bnkTxnList;
        // return errorString;

    }

    public List<BnkTxnModel> getExcelToElement(Sheet rs, String instiid) throws ParseException {
        bnkTxnList = new ArrayList<BnkTxnModel>();
        String acqsettledate = "";
        int row = rs.getRows();
        Cell c0 = null, c1 = null, c2 = null, c3 = null, c4 = null, c5 = null, c6 = null;

        if (row == 0) {
            return null;
        }
        for (int k = 1; k < row; k++) {
            
            if (k >= 4) {
                c0 = rs.getCell(0, k);// 商户编号
                c1 = rs.getCell(1, k);// 交易发起时间
                c2 = rs.getCell(2, k);// 商户订单ID
                c3 = rs.getCell(3, k);// 钱包交易订单号
                c4 = rs.getCell(4, k);// 支付订单号
                c5 = rs.getCell(5, k);// 支付金额
                c6 = rs.getCell(6, k);// 手续费
                // UserModel user = getCurrentUser();
                BnkTxnModel bnk = new BnkTxnModel();
                bnk.setTxndatetime(DateUtil.formatDateTime("yyyyMMddHHmmss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(c1.getContents().trim())));
                bnk.setSystrcno(c3.getContents().trim());
                bnk.setPayordno(c2.getContents().trim());
                bnk.setInstiid(instiid);
                
                //借记手续费=交易手续费
                bnk.setDfee(Money.yuanValueOf(new BigDecimal(c6.getContents().trim())).getAmount().longValue());
                //
                //bnk.setCfee(0L);
                bnk.setAcqsettledate(acqsettledate.replace("-", ""));
                if (c5.getContents() != null && !c5.getContents().trim().equals("")) {
                    bnk.setAmount(Money.yuanValueOf(new BigDecimal(c5.getContents().trim())).getAmount().longValue());
                }
                bnk.setRetcode("00");
                bnkTxnList.add(bnk);
            }
        }
        return bnkTxnList;
    }
}