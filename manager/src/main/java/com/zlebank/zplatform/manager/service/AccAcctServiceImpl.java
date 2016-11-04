package com.zlebank.zplatform.manager.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.FrozenAccBean;
import com.zlebank.zplatform.manager.dao.iface.AccAcctDAO;
import com.zlebank.zplatform.manager.dao.object.AccAcctModel;
import com.zlebank.zplatform.manager.service.iface.AccAcctService;
import com.zlebank.zplatform.trade.common.page.PageVo;
import com.zlebank.zplatform.trade.service.base.BaseServiceImpl;

@Service("accAcctService")
public class AccAcctServiceImpl extends BaseServiceImpl<AccAcctModel, String> implements AccAcctService {
    public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy/MM/dd HH:mm:ss";
    @Autowired
    private AccAcctDAO accAcctDAO;
    @Override
    public Session getSession() {
        return accAcctDAO.getSession();
    }
    
    public List<?> getAccAcctInfo(String acctcode) {
        String queryString="select ACCT_CODE_NAME,CRDR,BUSINESS_ACTOR_ID from t_acc_acct  where ACCT_CODE=?";
        return (List<?>) super.queryBySQL(queryString, new Object[]{acctcode});
    }


	@Override
	public PageVo<FrozenAccBean> queryByPage(FrozenAccBean query, int pageNo, int pageSize) {
		PageVo<FrozenAccBean> pageVo = new PageVo<FrozenAccBean>();
		if(query!=null){
			List<Object> obj= new ArrayList<Object>();
			List<Object> param =new ArrayList<Object>();
			StringBuffer querySql= new StringBuffer(" where 1=1");
			if(StringUtil.isNotEmpty(query.getStatus())){
				querySql.append(" and t1.status=?");
				obj.add(query.getStatus());
				param.add(query.getStatus());
			}
            if(StringUtil.isNotEmpty(query.getMemberId())){
                querySql.append(" and t2.business_actor_id=?");
                obj.add(query.getMemberId().trim());
                param.add(query.getMemberId().trim());
            }
	        if(StringUtil.isNotEmpty(query.getAcctCode())){
                querySql.append(" and t.acct_code=?");
                obj.add(query.getAcctCode().trim());
                param.add(query.getAcctCode().trim());
	        }
	        if(StringUtil.isNotEmpty(query.getUsage())){
                querySql.append(" and t2.usage=?");
                obj.add(query.getUsage());
                param.add(query.getUsage());
	        }
	        if(StringUtil.isNotEmpty(query.getFrozenSTimeFrom())){
	            String dateString1 = query.getFrozenSTimeFrom().replace("-", "/");
                querySql.append(" and to_char(t1.frozen_s_time,'yyyy/mm/dd HH:mm:ss')>=?");
                obj.add(dateString1);
                param.add(dateString1);
	        }
	        if(StringUtil.isNotEmpty(query.getFrozenStimeTo())){
	            String dateString2 = query.getFrozenStimeTo().replace("-", "/");
                querySql.append(" and to_char(t1.frozen_s_time,'yyyy/mm/dd HH:mm:ss')<=?");
                obj.add(dateString2);
                param.add(dateString2);
            }
	        
	        if(StringUtil.isNotEmpty(query.getTxnseqno())){
                querySql.append(" and t1.txnseqno=?");
                obj.add(query.getTxnseqno().trim());
                param.add(query.getTxnseqno().trim());
            }
	        if(StringUtil.isNotEmpty(query.getAcctCodeName())){
                querySql.append(" and t.acct_code_name=?");
                obj.add(query.getAcctCodeName().trim());
                param.add(query.getAcctCodeName().trim());
            }
            if(StringUtil.isNotEmpty(query.getFrozenTimeFrom())){
                String dateString1 = query.getFrozenTimeFrom().replace("-", "/");
                querySql.append(" and to_char(t1.UNFROZEN_TIME,'yyyy/mm/dd HH:mm:ss')>=?");
                obj.add(dateString1);
                param.add(dateString1);
            }
            if(StringUtil.isNotEmpty(query.getFrozentimeTo())){   
                String dateString2 = query.getFrozentimeTo().replace("-", "/");
                querySql.append(" and to_char(t1.UNFROZEN_TIME,'yyyy/mm/dd HH:mm:ss')<=?");
                obj.add(dateString2);
                param.add(dateString2);
            }
	        
			StringBuffer sql=new StringBuffer(); 
			sql.append(" from T_ACC_FROZEN_TASK t1 ");
			sql.append(" left join t_acc_acct t  on t1.acc_id=t.id ");
			sql.append(" left join t_acc_busiacct t2 on t1.acc_id=t2.acct_id ");
			//查询列表
			StringBuffer listSql= new StringBuffer();
			listSql.append(" select t1.id id, t2.business_actor_id memberId, t.acct_code acctCode, t.acct_code_name acctCodeName ,"
					+ "t2.usage usage,t1.txnseqno txnseqno,t1.frozen_balance/100 frozenBalance,t1.status status, t1.frozen_s_time frozenStime, t1.UNFROZEN_TIME frozenTime ");
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
			
			List<FrozenAccBean> beanList = new ArrayList<FrozenAccBean>();
			for(Map item :resultList){
				FrozenAccBean bean = new  FrozenAccBean();
				bean.setId(null==item.get("ID")?"":item.get("ID").toString());
				bean.setMemberId(null==item.get("MEMBERID")?"":item.get("MEMBERID").toString());
				bean.setAcctCode(null==item.get("ACCTCODE")?"":item.get("ACCTCODE").toString());
				bean.setAcctCodeName(null==item.get("ACCTCODENAME")?"":item.get("ACCTCODENAME").toString());
				bean.setUsage(null==item.get("USAGE")?"":item.get("USAGE").toString());
				bean.setTxnseqno(null==item.get("TXNSEQNO")?"":item.get("TXNSEQNO").toString());
				bean.setFrozenBalance(null==item.get("FROZENBALANCE")?"":item.get("FROZENBALANCE").toString());
				bean.setStatus(null==item.get("STATUS")?"":item.get("STATUS").toString());
				bean.setFrozenStime(null==item.get("FROZENSTIME")?"":item.get("FROZENSTIME").toString());
				bean.setFrozenTime(null==item.get("FROZENTIME")?"":item.get("FROZENTIME").toString());
				beanList.add(bean);
			}
			pageVo.setList(beanList);
			pageVo.setTotal(total==null?0: Integer.valueOf(total.get(0).get("TOTAL").toString()));
		}
		return pageVo;
	 }
	
    @Override
    public List<?> getAccBusiacctInfo(String acctcode) {
        String queryString = "select tt.USAGE from T_ACC_ACCT t join T_ACC_BUSIACCT tt  on t.ID=tt.ACCT_ID where t.ACCT_CODE=?";
        return (List<?>) super.queryBySQL(queryString, new Object[]{acctcode});
    }

}
