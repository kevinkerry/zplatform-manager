package com.zlebank.zplatform.manager.action.cmbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
/**
 * *民生实名认证
 * @author eason
 *
 */
public class CMBCRealNameFeilContent extends AbstractFileContentHandler{
    
    private List<BnkTxnModel> bnkTxnList; 
    

    public List<BnkTxnModel> readFile(File[] upload,String instiid, String[] uploadFileName) throws NumberFormatException, IOException{
        
        bnkTxnList=new ArrayList<BnkTxnModel>();
        String newline = "";// 读取一行
        Object[] fileNameob;// 文件名称按照_拆分
        BufferedReader brfile = new BufferedReader(new FileReader(upload[0]));
        try {
        while ((newline = brfile.readLine()) != null
                && !newline.equals("########")) {
                BnkTxnModel bnk = new BnkTxnModel();
                fileNameob = uploadFileName[0].split("_");
                Object[] obzl = newline.replace(" ", "").split("\\|");
                bnk.setPayordno(obzl[1].toString());
               // bnk.setMerchno(fileNameob[1].toString());
                bnk.setBusicode("80000001");
                bnk.setPan(obzl[2].toString());
                bnk.setRetcode(obzl[4].toString());
                if(Long.parseLong(obzl[5].toString())<0){
                    bnk.setDfee(Long.parseLong(obzl[5].toString()) );
                }else{
                    bnk.setCfee(Long.parseLong(obzl[5].toString()));
                }
                bnk.setInstiid(instiid);
                bnk.setStatus("01");
                bnk.setAcqsettledate(fileNameob[3].toString().substring(0,8));
                //bnk.setSystrcno("11");
                //bnk.setAcqsettledate("11");
                bnkTxnList.add(bnk);
        } 
        }finally{
            if (brfile != null) {
                brfile.close();
            }
        }
        return bnkTxnList;
        
    }
}