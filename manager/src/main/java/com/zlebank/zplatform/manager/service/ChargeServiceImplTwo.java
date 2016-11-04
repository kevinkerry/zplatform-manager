package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.bean.FrozenAccBean;
import com.zlebank.zplatform.manager.dao.iface.ChargeDAO;
import com.zlebank.zplatform.manager.dao.object.ChargeModel;
import com.zlebank.zplatform.manager.service.iface.ChargeService;
import com.zlebank.zplatform.trade.common.page.PageVo;
import com.zlebank.zplatform.trade.service.base.BaseServiceImpl;

@Service
public class ChargeServiceImplTwo extends BaseServiceImpl<ChargeModel, String> implements ChargeService{
    @Autowired
    private ChargeDAO chargeDao;
    @Override
    public Session getSession() {
        return chargeDao.getSession();
    }


    @Override
    public PageVo<ChargeQuery> queryByPage(ChargeQuery chargeQuery,
            int pageNo,
            int pageSize) {
        PageVo<ChargeQuery> pageVo = new PageVo<ChargeQuery>();
        if(chargeQuery!=null){
            List<Object> obj= new ArrayList<Object>();
            List<Object> param =new ArrayList<Object>();
            //查询条件
            StringBuffer querySql= new StringBuffer();
            if(StringUtil.isNotEmpty(chargeQuery.getStatus())){//充值状态
                querySql.append(" and t.status=?");
                obj.add(chargeQuery.getStatus());
                param.add(chargeQuery.getStatus());
            }
            if(StringUtil.isNotEmpty(chargeQuery.getMemberId())){//会员号
                querySql.append(" and t2.member_id=?");
                obj.add(chargeQuery.getMemberId().trim());
                param.add(chargeQuery.getMemberId().trim());
            }
            if(StringUtil.isNotEmpty(chargeQuery.getChargeno())){//充值订单号
                querySql.append(" and t.chargeno=?");
                obj.add(chargeQuery.getChargeno().trim());
                param.add(chargeQuery.getChargeno().trim());
            }
            if(StringUtil.isNotEmpty(chargeQuery.getChargeType())){//充值类型
                querySql.append(" and t.chargetype=?");
                obj.add(chargeQuery.getChargeType());
                param.add(chargeQuery.getChargeType());
            }
            if(StringUtil.isNotEmpty(chargeQuery.getUsage())){//用途
                querySql.append(" and t1.usage=?");
                obj.add(chargeQuery.getUsage());
                param.add(chargeQuery.getUsage());
            }

            
            StringBuffer sql=new StringBuffer(); 
//            sql.append(" from t_txns_charge t ");            
//            sql.append(" left join t_member t2 on t.MEMBERID=t2.MEM_ID ");
//            sql.append(" left join t_acc_busiacct t1  on t2.member_id = t1.BUSINESS_ACTOR_ID ");
            sql.append( "from t_txns_charge t , t_member t2,t_acc_busiacct t1 where t.MEMBERID=t2.MEM_ID and t2.member_id = t1.BUSINESS_ACTOR_ID and t.usage = t1.usage ");
            //查询列表
            StringBuffer listSql= new StringBuffer();
            listSql.append(" select t.chargeno, t2.member_id memberid, t2.member_name membername, t1.usage, t.chargetype,t.chargecode,t.amount/100 amount,t.status,t.intime ");
            listSql.append(sql).append(querySql);
            //分页查询
            StringBuffer pageSql= new StringBuffer();
            pageSql.append("select * from ( SELECT A.*, ROWNUM RN FROM ( ").append(listSql).append(" ) A ) WHERE RN BETWEEN ? AND ? ");
            obj.add((pageNo - 1) * pageSize+1);
            obj.add(pageNo*pageSize);
            //查询总条数
            StringBuffer totalSql = new StringBuffer(" select count(*) total ").append(sql).append(querySql);
            //结果集
            @SuppressWarnings({"unchecked", "rawtypes"})
            List<Map> resultList= (List<Map>) this.queryBySQL(pageSql.toString(), obj.toArray());
            @SuppressWarnings({"unchecked", "rawtypes"})
            List<Map> total = (List<Map>) this.queryBySQL(totalSql.toString(), param.toArray());
            
            List<ChargeQuery> beanList = new ArrayList<ChargeQuery>();
            for(Map item :resultList){
                ChargeQuery bean = new  ChargeQuery();
                bean.setChargeno(null==item.get("CHARGENO")?"":item.get("CHARGENO").toString());
                bean.setMemberId(null==item.get("MEMBERID")?"":item.get("MEMBERID").toString());                
                bean.setMemberName(null==item.get("MEMBERNAME")?"":item.get("MEMBERNAME").toString());
                bean.setUsage(null==item.get("USAGE")?"":item.get("USAGE").toString());
                bean.setChargeType(null==item.get("CHARGETYPE")?"":item.get("CHARGETYPE").toString());
                bean.setChargeCode(null==item.get("CHARGECODE")?"":item.get("CHARGECODE").toString());
                bean.setAmount(null==item.get("AMOUNT")?"":item.get("AMOUNT").toString());
                bean.setStatus(null==item.get("STATUS")?"":item.get("STATUS").toString());
                bean.setIntime(null==item.get("INTIME")?"":item.get("INTIME").toString());
                beanList.add(bean);
            }
            pageVo.setList(beanList);
            pageVo.setTotal(total==null?0: Integer.valueOf(total.get(0).get("TOTAL").toString()));
        }
        return pageVo;
    }


}
