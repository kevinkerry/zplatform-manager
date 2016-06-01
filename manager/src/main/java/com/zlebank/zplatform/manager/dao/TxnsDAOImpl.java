/* 
 * TxnSLogDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年11月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;
import com.zlebank.zplatform.manager.dao.iface.ITxnsDAO;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月16日 下午5:24:54
 * @since
 */
@Repository("txnsdao")
public class TxnsDAOImpl
        extends
            AbstractPagedQueryDAOImpl<PojoTxnsLog, TxnsLogBean>
        implements
            ITxnsDAO {
    public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm";
    public final static String DEFAULT_DATE_STAMP_FROMAT = "yyyy-MM-dd";

    /**
     *
     * @param e
     * @return
     * @throws ParseException
     */
    @Override
    protected Criteria buildCriteria(TxnsLogBean e) {
        Criteria crite = this.getSession().createCriteria(PojoTxnsLog.class);
        try {
            if (e != null) {
                if (StringUtil.isNotEmpty(e.getAccfirmerno())) {
                    crite.add(Restrictions.eq("accfirmerno", e.getAccfirmerno()));
                }
                if (StringUtil.isNotEmpty(e.getAccordcommitimes())) {
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyyMMddHHmmss");
                    String datetimes;

                    datetimes = format
                            .format(DateUtil.convertToDate(
                                    e.getAccordcommitimes(),
                                    DEFAULT_TIME_STAMP_FROMAT));

                    crite.add(Restrictions.ge("accordcommitime", datetimes));

                }
                if (StringUtil.isNotEmpty(e.getAccordcommitimen())) {
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyyMMddHHmmss");
                    String datetimen = format
                            .format(DateUtil.convertToDate(
                                    e.getAccordcommitimen(),
                                    DEFAULT_TIME_STAMP_FROMAT));

                    crite.add(Restrictions.le("accordcommitime", datetimen));

                }
                if (StringUtil.isNotEmpty(e.getAccordno())) {
                    crite.add(Restrictions.eq("accordno", e.getAccordno()));
                }
                if (StringUtil.isNotEmpty(e.getAccsecmerno())) {
                    crite.add(Restrictions.eq("accsecmerno", e.getAccsecmerno()));
                }
                if (StringUtil.isNotEmpty(e.getAccsettledate())) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                    String date = format.format(DateUtil.convertToDate(
                            e.getAccsettledate(), DEFAULT_DATE_STAMP_FROMAT));

                    crite.add(Restrictions.eq("accsettledate", date));

                }
                if (StringUtil.isNotEmpty(e.getBusicode())) {
                    crite.add(Restrictions.eq("busicode", e.getBusicode()));
                }
                if (StringUtil.isNotEmpty(e.getPayType())) {
                    crite.add(Restrictions.eq("paytype", e.getPayType()));
                }
                if (StringUtil.isNotEmpty(e.getPan())) {
                    crite.add(Restrictions.eq("pan", e.getPan()));
                }
                if (StringUtil.isNotEmpty(e.getPayrettsnseqno())) {
                    crite.add(Restrictions.eq("payrettsnseqno",
                            e.getPayrettsnseqno()));
                }
                if (StringUtil.isNotEmpty(e.getRetcode())) {
                    if ("00".equals(e.getRetcode())) {
                        crite.add(Restrictions.sqlRestriction(
                                "? like substr(trim(retcode),-2)", "00",
                                StringType.INSTANCE));
                    } else {
                        crite.add(Restrictions.or(Property.forName("retcode").isNull()
                                ,Restrictions.not(
                                        Restrictions.sqlRestriction(
                                        "?  like substr(trim(retcode),-2)",
                                        "00", StringType.INSTANCE))
                                    )
                                );
                    }

                }
                if (StringUtil.isNotEmpty(e.getTxnseqno())) {
                    crite.add(Restrictions.eq("txnseqno", e.getTxnseqno()));
                }
                if (e.getUserId() != null) {
                    crite.add(Restrictions.eq("userId", e.getUserId()));
                }
            }
        } catch (ParseException e1) {

            e1.printStackTrace();
        }
        crite.addOrder(Order.desc("txnseqno"));
        return crite;

    }

    /**
     *
     * @param txnseqno
     * @return
     */
    @Override
    public PojoTxnsLog getTxnsModelByNo(String txnseqno) {
        Criteria crite = this.getSession().createCriteria(PojoTxnsLog.class);
        crite.add(Restrictions.eq("txnseqno", txnseqno));
          return   (PojoTxnsLog)crite.uniqueResult();
    }
    
    
    
}
