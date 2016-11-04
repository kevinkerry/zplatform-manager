package com.zlebank.zplatform.manager.action.cmbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.exception.ResolveReconFileContentException;
/**
 * 民生实时代扣
 * Class Description
 *
 * @author jingxr
 * @version
 * @date 2016年10月19日 下午2:56:08
 * @since
 */
public class CMBCRealTimePayContent extends AbstractFileContentHandler{
    private List<BnkTxnModel> bnkTxnList; 
    @Override
    public List<BnkTxnModel> readFile(File[] upload,String instiid,String[] uploadFileName)
            throws NumberFormatException, IOException,ResolveReconFileContentException {
        
        
        bnkTxnList=new ArrayList<BnkTxnModel>();
        String newline = "";// 读取一行
        BufferedReader brfile = new BufferedReader(new FileReader(upload[0]));
        try {
        while ((newline = brfile.readLine()) != null&& !newline.equals("########")) {
                BnkTxnModel bnk = new BnkTxnModel();
                Object[] obzl = newline.replace(" ", "").split("\\|");
                //交易服务码|合作方流水号|银行处理流水号|收款人账户号|收款人账户名|
                //交易金额(分)|应答码类型|应答码|应答描述|银行对账日期
                bnk.setPayordno(obzl[1].toString());//合作方流水号
                bnk.setSystrcno(obzl[2].toString());//银行处理流水号
                bnk.setPan(obzl[3].toString());//卡号
                bnk.setInstiid("93000004");
                bnk.setAmount(Long.parseLong(obzl[5].toString()));//交易金额
                bnk.setRetcode(obzl[7].toString());//应答信息
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
