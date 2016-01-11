/* 
 * InsteadPayAction.java  
 * 
 * version TODO
 *
 * 2015年12月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.insteadpay;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.commons.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.HibernateValidatorUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.enums.ChargeEnum;
import com.zlebank.zplatform.manager.enums.InsteadEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.IInsteadPayService;
import com.zlebank.zplatform.manager.util.ReadExcle;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailBean;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailQuery;
import com.zlebank.zplatform.trade.exception.BalanceNotEnoughException;
import com.zlebank.zplatform.trade.exception.DuplicateOrderIdException;
import com.zlebank.zplatform.trade.exception.FailToGetAccountInfoException;
import com.zlebank.zplatform.trade.exception.FailToInsertAccEntryException;
import com.zlebank.zplatform.trade.exception.FailToInsertFeeException;
import com.zlebank.zplatform.trade.exception.InvalidCardException;
import com.zlebank.zplatform.trade.exception.MerchWhiteListCheckFailException;
import com.zlebank.zplatform.trade.exception.NotInsteadPayWorkTimeException;
import com.zlebank.zplatform.trade.insteadPay.message.InsteadPayFile;
import com.zlebank.zplatform.trade.insteadPay.message.InsteadPay_Request;
import com.zlebank.zplatform.trade.service.InsteadPayService;

/**
 * 批量导入代付
 *
 * @author yangpeng
 * @version
 * @date 2015年12月21日 下午4:43:04
 * @since
 */
public class InsteadPayAction extends BaseAction {

    private static final String BIZTYPE = "000401";
    private static final String CHANNELTYPE = "00";
    private static final String ACCESSTYPE = "0";
    private static final String TXNTYPE = "21";
    private static final String TXNSUBTYPE = "03";
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private InsteadPayService instea;
    @Autowired
    private IInsteadPayService iInstea;
    private InsteadPayDetailQuery instead;

    private File file;

    private String falg;

    private AuditBean trial;

    private String txnserno;

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public String getTxnserno() {
        return txnserno;
    }

    public void setTxnserno(String txnserno) {
        this.txnserno = txnserno;
    }

    public AuditBean getTrial() {
        return trial;
    }

    public void setTrial(AuditBean trial) {
        this.trial = trial;
    }

    public InsteadPayDetailQuery getInstead() {
        return instead;
    }

    public void setInstead(InsteadPayDetailQuery instead) {
        this.instead = instead;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void file() {
        Long userID = getCurrentUser().getUserId();
        String messg = "";
        try {
            BigDecimal amount = new BigDecimal(0);
            List<InsteadPayFile> ins = new ArrayList<InsteadPayFile>();
            List<InsteadPay_Request> batchList = new ArrayList<InsteadPay_Request>();
            List<String[]> li = ReadExcle.readExcle(0, file);
            // 明细
            ins = InsteadPayDetaList(ins, li);
            List<String[]> batch = ReadExcle.readExcle(1, file);
            // 总和
            batchList = InsteadPayBatchList(batchList, batch);

            for (InsteadPayFile ipf : ins) {
                amount = new BigDecimal(ipf.getAmt()).add(amount);

            }
            if (ins == null || ins.isEmpty() || batchList == null
                    || batchList.isEmpty()) {
                messg="EXCLE数据错误";
            } else {
                if (ins.size() != Integer.valueOf(batchList.get(0)
                        .getTotalQty())
                        || !amount.setScale(2, BigDecimal.ROUND_HALF_DOWN)
                                .equals(new BigDecimal(batchList.get(0)
                                        .getTotalAmt()).setScale(2,
                                        BigDecimal.ROUND_HALF_DOWN))) {
                    
                    messg="总金额或者总笔数不正确";

                } else {
                    if (batchList != null && !batchList.isEmpty()) {
                        InsteadPay_Request insteadRequest = batchList.get(0);
                        insteadRequest.setFileContent(ins);
                        instea.insteadPay(insteadRequest, userID);
                        messg = "操作成功";
                    } else {
                        messg = "读取excle失败";
                    }
                }
            }
        } catch (NotInsteadPayWorkTimeException e) {
            messg = e.getMessage();
        } catch (FailToGetAccountInfoException e) {
            messg = e.getMessage();
        } catch (BalanceNotEnoughException e) {
            messg = e.getMessage();
        } catch (DuplicateOrderIdException e) {
            messg = e.getMessage();
        } catch (BiffException e) {
            messg = "读取excle失败";
        } catch (IOException e) {
            messg = "读取excle失败";
        } catch (InvalidCardException e) {
            messg = e.getMessage();
        } catch (FailToInsertAccEntryException e) {
            messg = e.getMessage();
        } catch (MerchWhiteListCheckFailException e) {
            messg = e.getMessage();
        } catch (ManagerWithdrawException e) {
            // TODO Auto-generated catch block
            messg = e.getMessage();
        } catch (FailToInsertFeeException e) {
            messg = e.getMessage();
        } catch (Exception e) {
            messg = "商户订单号重复";
        }
        json_encode(messg);

    }

    private List<InsteadPayFile> InsteadPayDetaList(List<InsteadPayFile> ins,
            List<String[]> li) throws ManagerWithdrawException {
        for (int i = 0; i < li.size(); i++) {
            String[] str = li.get(i);
            int y = 0;
            InsteadPayFile instea = new InsteadPayFile();
            instea.setMerId(str[y]);
            instea.setOrderId(str[++y]);
            instea.setCurrencyCode(str[++y]);
            instea.setAmt(str[++y]);
            instea.setBizType(str[++y]);
            instea.setAccType(str[++y]);
            instea.setAccNo(str[++y]);
            instea.setAccName(str[++y]);
            instea.setBankCode(str[++y]);
            instea.setIssInsProvince(str[++y]);
            instea.setIssInsCity(str[++y]);
            instea.setIssInsName(str[++y]);
            instea.setCertifTp(str[++y]);
            instea.setCertifId(str[++y]);
            instea.setPhoneNo(str[++y]);
            instea.setBillType(str[++y]);
            instea.setNotes(str[++y]);
            String messg = HibernateValidatorUtil.validateBeans(instea);
            if (StringUtil.isNotEmpty(messg)) {
                throw new ManagerWithdrawException("G100015", 1, i + 1, messg);
            }

            ins.add(instea);
        }

        return ins;
    }

    private List<InsteadPay_Request> InsteadPayBatchList(List<InsteadPay_Request> ins,
            List<String[]> li) throws ManagerWithdrawException {
        for (int i = 0; i < li.size(); i++) {
            String[] str = li.get(i);
            int y = 0;
            InsteadPay_Request instea = new InsteadPay_Request();
            instea.setMerId(str[y]);
            instea.setBatchNo(str[++y]);
            instea.setTotalQty(str[++y]);
            instea.setTxnTime(DateUtil.formatDateTime(
                    DateUtil.DEFAULT_DATE_FROMAT, new Date()));
            instea.setTotalAmt(str[++y]);
            instea.setBizType(BIZTYPE);
            instea.setChannelType(CHANNELTYPE);
            instea.setAccessType(ACCESSTYPE);
            instea.setTxnType(TXNTYPE);
            instea.setTxnSubType(TXNSUBTYPE);
            String messg = HibernateValidatorUtil.validateBeans(instea);
            if (StringUtil.isNotEmpty(messg)) {
                throw new ManagerWithdrawException("G100015", 2, i + 1, messg);
            }
            ins.add(instea);
        }

        return ins;

    }

    public String getInsteadPayDeta() {
        return this.SUCCESS;
    }

    public void queryInstead() {
        if ("first".equals(falg)) {
            if (instead == null) {
                instead = new InsteadPayDetailQuery();
            }
            instead.setStatus(ChargeEnum.FIRSTTRIAL.getCode());

        }
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        PagedResult<InsteadPayDetailBean> pr = instea.queryPaged(page,
                pageSize, instead);
        try {
            List<InsteadPayDetailBean> li = pr.getPagedResult();
            Long total = pr.getTotal();
            map.put("total", total);
            map.put("rows", li);
            json_encode(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public String getFirstInstead() {
        return "first";
    }
    /**
     * 初审
     */
    public void firstInstead() {
        Map<String, Object> map = new HashMap<String, Object>();
        String messg = "";
        boolean isok = false;
        Long userId = getCurrentUser().getUserId();
        trial.setStexauser(userId);

        try {
            iInstea.firstInstead(trial);
            isok = true;
            messg = "操作成功";
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        } catch (ManagerWithdrawException e) {
            messg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            messg = e.getMessage();
        } catch (NumberFormatException e) {
            messg = e.getMessage();
        }
        map.put("messg", messg);
        map.put("falg", isok);
        json_encode(map);

    }

    public void getDetailByTxnseqno() {

        try {
            InsteadPayDetailBean insteadPay = iInstea.getDetailByTxnseqno(
                    txnserno, InsteadEnum.FIRSTTRIAL.getCode());
            json_encode(insteadPay);
        } catch (ManagerWithdrawException e) {
            String messg = e.getMessage();
            json_encode(messg);
        }

    }
    /***
     * 批量审核代付
     */
    public void batchFirst(){
        String messg = "";
        Map<String, Object> map = new HashMap<String, Object>();
        if ("first".equals(falg)) {
            if (instead == null) {
                instead = new InsteadPayDetailQuery();
            }
            instead.setStatus(ChargeEnum.FIRSTTRIAL.getCode());

        }
        try {
            iInstea.batchFirst(trial, instead);
            messg="操作成功";
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        } catch (ManagerWithdrawException e) {
            messg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            messg = e.getMessage();
        } catch (NumberFormatException e) {
            messg = e.getMessage();
        }
        json_encode(messg);

    }   
    
    
    
    
    
    
}
