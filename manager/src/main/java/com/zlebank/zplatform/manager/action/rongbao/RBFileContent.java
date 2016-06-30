package com.zlebank.zplatform.manager.action.rongbao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        int row = rs.getRows();
        Cell c1,c2,c4,c5,c7 = null;
        if (row == 0) {
            return null;
        }
        for (int k = 1; k < row; k++) {           
            if (k >= 2) {
                c1 = rs.getCell(1, k);// 商户订单号
                c2 = rs.getCell(2, k);// 融宝交易号                
                c4 = rs.getCell(4, k);// 支付完成时间（交易时间）
                c5 = rs.getCell(5, k);// 订单金额
                c7 = rs.getCell(7, k);// 手续费
                BnkTxnModel bnk = new BnkTxnModel();              
                bnk.setPayordno(c1.getContents().trim());
                bnk.setSystrcno(c2.getContents().trim());              
                if(c4.getContents().trim()!= null&& !c4.getContents().trim().equals("")){
                    String txndatetime = c4.getContents().trim().toString();
                    bnk.setTxndatetime(txndatetime.replace("-", "")
                            .replace(":", "").replace(" ", ""));                  
                }   
                //受理清算日期               
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
                Date date = null; 
                try { 
                 date = format.parse(c4.getContents().trim().toString()); 
                } catch (ParseException e) { 
                 e.printStackTrace(); 
                }     
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(calendar.DATE,1);
                date=calendar.getTime();
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                String str = sd.format(date).replace("-", "");
                bnk.setAcqsettledate(str);
                
                if(c5.getContents() != null && !c5.getContents().trim().equals("")){
                    bnk.setAmount(new BigDecimal(c5.getContents()).multiply(new BigDecimal(100)).longValue());
                }
                if(c7.getContents() != null && !c7.getContents().trim().equals("")){
                    bnk.setCfee(new BigDecimal(c7.getContents()).multiply(new BigDecimal(100)).longValue());  
                }
                bnk.setRetcode("00");
                bnkTxnList.add(bnk); 
            }
        }
        return bnkTxnList;
    }
}