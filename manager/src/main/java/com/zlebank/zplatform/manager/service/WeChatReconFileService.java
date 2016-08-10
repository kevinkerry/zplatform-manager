/* 
 * WeChatReconFileService.java  
 * 
 * version TODO
 *
 * 2016年5月27日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.service.iface.IBnkTxnService;
import com.zlebank.zplatform.wechat.qr.service.WeChatQRService;
import com.zlebank.zplatform.wechat.service.WeChatService;
import com.zlebank.zplatform.wechat.wx.bean.QueryBillBean;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年5月27日 下午3:05:16
 * @since 
 */
@Service
public class WeChatReconFileService {

	@Autowired
	private WeChatService weChatService;
	@Autowired
	private WeChatQRService weChatQRService;
	
	
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public List<BnkTxnModel> saveWeChatBill(String date) throws ParseException{
		//校验订单日期
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.SIMPLE_DATE_FROMAT);
			sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QueryBillBean queryBillBean = new QueryBillBean();
		queryBillBean.setBill_date(date);
		queryBillBean.setBill_type("ALL");
		List<String[]> dowanWeChatBill = weChatService.dowanWeChatBill(queryBillBean);
		
		if(dowanWeChatBill==null){
			return null;
		}
		// 0         1      2      3     4     5         6      7       8	   9	  10     11     12     13       14
		//[交易时间, 公众账号ID, 商户号, 子商户号, 设备号, 微信订单号, 商户订单号, 用户标识, 交易类型, 交易状态, 付款银行, 货币种类, 总金额, 企业红包金额, 微信退款单号,
		//  15       16       17         18     19     20      21     22   23
		//商户退款单号, 退款金额, 企业红包退款金额, 退款类型, 退款状态, 商品名称, 商户数据包, 手续费, 费率]
		List<BnkTxnModel> bnkTxnList = new ArrayList<BnkTxnModel>();
		for(String[] bills : dowanWeChatBill ){
			BnkTxnModel bnk = new BnkTxnModel();
			//SUCCESS支付 REFUND 退款
			String tradeType = bills[9];
			String payorderno="";
			String payretorderno="";
			String amount="";
			String fee = bills[22];
			if("SUCCESS".equals(tradeType)){
				//支付订单号（证联） 6
				 payorderno = bills[6];
				 payretorderno = bills[5];
				 amount =  bills[12];
				 bnk.setCfee(Money.yuanValueOf(new BigDecimal(fee)).getAmount().longValue());
			}else if("REFUND".equals(tradeType)){
				//支付订单号（证联） 6
				 payorderno = bills[15];
				 payretorderno = bills[14];
				 amount =  bills[16];
				 bnk.setDfee(Money.yuanValueOf(new BigDecimal(fee)).getAmount().longValue());
			}
            bnk.setTxndatetime(DateUtil.formatDateTime("yyyyMMddHHmmss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bills[0])));
            bnk.setSystrcno(payretorderno);
            bnk.setPayordno(payorderno);
            bnk.setInstiid("91000001");
            if (amount != null && !amount.equals("")) {
                bnk.setAmount(Money.yuanValueOf(new BigDecimal(amount)).getAmount().longValue());
            }
            bnk.setRetcode("00");
            bnkTxnList.add(bnk);
		}
		
		
		return bnkTxnList;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public List<BnkTxnModel> saveWeChatQRBill(String date) throws ParseException{
		//校验订单日期
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.SIMPLE_DATE_FROMAT);
			sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		com.zlebank.zplatform.wechat.qr.wx.bean.QueryBillBean queryBillBean = new com.zlebank.zplatform.wechat.qr.wx.bean.QueryBillBean();
		queryBillBean.setBill_date(date);
		queryBillBean.setBill_type("ALL");
		List<String[]> dowanWeChatBill = weChatQRService.dowanWeChatBill(queryBillBean);
		
		if(dowanWeChatBill==null){
			return null;
		}
		// 0         1      2      3     4     5         6      7       8	   9	  10     11     12     13       14
		//[交易时间, 公众账号ID, 商户号, 子商户号, 设备号, 微信订单号, 商户订单号, 用户标识, 交易类型, 交易状态, 付款银行, 货币种类, 总金额, 企业红包金额, 微信退款单号,
		//  15       16       17         18     19     20      21     22   23
		//商户退款单号, 退款金额, 企业红包退款金额, 退款类型, 退款状态, 商品名称, 商户数据包, 手续费, 费率]
		List<BnkTxnModel> bnkTxnList = new ArrayList<BnkTxnModel>();
		for(String[] bills : dowanWeChatBill ){
			BnkTxnModel bnk = new BnkTxnModel();
			//SUCCESS支付 REFUND 退款
			String tradeType = bills[9];
			String payorderno="";
			String payretorderno="";
			String amount="";
			String fee = bills[22];
			if("SUCCESS".equals(tradeType)){
				//支付订单号（证联） 6
				 payorderno = bills[6];
				 payretorderno = bills[5];
				 amount =  bills[12];
				 bnk.setCfee(Money.yuanValueOf(new BigDecimal(fee)).getAmount().longValue());
			}else if("REFUND".equals(tradeType)){
				//支付订单号（证联） 6
				 payorderno = bills[15];
				 payretorderno = bills[14];
				 amount =  bills[16];
				 bnk.setDfee(Money.yuanValueOf(new BigDecimal(fee)).getAmount().longValue());
			}
            bnk.setTxndatetime(DateUtil.formatDateTime("yyyyMMddHHmmss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bills[0])));
            bnk.setSystrcno(payretorderno);
            bnk.setPayordno(payorderno);
            bnk.setInstiid("91000002");
            if (amount != null && !amount.equals("")) {
                bnk.setAmount(Money.yuanValueOf(new BigDecimal(amount)).getAmount().longValue());
            }
            bnk.setRetcode("00");
            bnkTxnList.add(bnk);
		}
		
		
		return bnkTxnList;
	}
}
