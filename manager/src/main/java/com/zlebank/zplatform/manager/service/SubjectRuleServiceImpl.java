/* 
 * SubjectRuleServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年10月28日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cheffo.jeplite.JEP;
import org.cheffo.jeplite.ParseException;
import org.cheffo.jeplite.util.DoubleStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.SubjectAccountRule;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.SubjectRuleService;
import com.zlebank.zplatform.manager.service.iface.ISubjectRuleService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月28日 下午4:25:32
 * @since
 */
@Service(value="subrule")
public class SubjectRuleServiceImpl implements ISubjectRuleService {
    @Autowired
    private SubjectRuleService sub;
    
    /**
    *
    * @return
    * @throws AccBussinessException
    */
    @Transactional
   public boolean balance(SubjectAccountRule asr) throws AccBussinessException {
       int page=1;
       int pageSize=Integer.MAX_VALUE;
      List<Map<String, Object>> li= sub.getRuleBySar(asr, page, pageSize);
      if(li!=null&&!li.isEmpty()){ 
      return     balance(li);
      }
       return false;
   }
    /**
     *
     * @param li
     * @return
     * @throws AccBussinessException
     */

    private  boolean balance(List<Map<String, Object>> li)
            throws AccBussinessException {
            BigDecimal bigdec=new BigDecimal(0.00);
        for (Map<String, Object> map : li) {
            BigDecimal big = getAccEntryAmount(String.valueOf(map.get("ORDFORM")));
            if (!String.valueOf(map.get("STATUS")).equals("99")) {
                String accCode = String.valueOf(map.get("ACCT_CODE"));
                String cr = "";
                if (isF(accCode)) {
                    cr = "D";
                } else if (isT(accCode)) {
                    cr = "D";
                } else {
                    cr = String.valueOf(map.get("CRDR"));

                }
               if(!cr.equals(map.get("TCR"))){
               big= big.multiply(new BigDecimal(-1));
                   
               }  
               bigdec=bigdec.add(big);
                

            }
        }
        if(bigdec.doubleValue()==0.00){
            return true;
        }else{
            return false;
        }
        
    }

    private BigDecimal getAccEntryAmount(String ordform)
            throws AccBussinessException {
        ordform = ordform.toUpperCase();
        JEP jep = new JEP();
        jep.addVariable("A", 100);// 本金
        jep.addVariable("B", 6);// 佣金
        jep.addVariable("C", 2);// 手续费
        jep.addVariable("T", 5);// 金额D
        jep.parseExpression(ordform);
        DoubleStack stack = new DoubleStack();
        try {
            return new BigDecimal(jep.getValue(stack));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AccBussinessException("E000010", new Object[]{ordform});
        }
    }
    private boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public boolean isF(String str) {
        String regex = "^[F|Y|S0-9]+$";
        return match(regex, str);
    }

    public boolean isT(String str) {
        String regex = "^[T0-9]+$";
        return match(regex, str);
    }
    
   
 
}
