package com.zlebank.zplatform.manager.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.bean.FinProductBean;
import com.zlebank.zplatform.manager.dao.iface.IFinProductDAO;
import com.zlebank.zplatform.manager.dao.object.scan.FinProductMode;
import com.zlebank.zplatform.trade.common.page.PageVo;

public class FinProductDAOImpl  extends HibernateBaseDAOImpl<FinProductMode>   implements
		IFinProductDAO {

	@Override
	public PageVo findByPage(FinProductBean bean, int pageNo, int pageSize) {
		PageVo<FinProductBean> pageVo = new PageVo<FinProductBean>();
		StringBuffer hq = new StringBuffer();
		if(bean!=null){
			hq.append(" and t.fundManager=t1.memberId and t.financier=t2.memberId ");
			if(StringUtils.isNotEmpty(bean.getProductCode())){
				hq.append(" and t.productCode like:productCode");
			}
			if(StringUtils.isNotEmpty(bean.getProductName())){
				hq.append(" and t.productName like:productName");
			}
			if(StringUtils.isNotEmpty(bean.getFundManagerName())){
				hq.append(" and t1.memberName like:funManager");
			}
			if(StringUtils.isNotEmpty(bean.getFinancierName())){
				hq.append(" and t2.memberName like:financier");
			}
			//查询列表
			StringBuffer listHql= new StringBuffer(" select t.pid, t.productCode, t.productName,t.fundManager,t.financier,t.inTime, t.notes,t1.memberName as funName, t2.memberName as finName from  FinProductMode t,PojoMember t1 , PojoMember t2   where 1=1  ").append(hq);
			//查询总条数
			StringBuffer totalHql = new StringBuffer(" select count(t.pid) from FinProductMode t,PojoMember t1 , PojoMember t2  where 1=1 ").append(hq);
	        
			Query query = this.getSession().createQuery(listHql.toString());
	        
			Query tquery = getSession().createQuery(totalHql.toString());
			if(StringUtils.isNotEmpty(bean.getProductCode())){
				query.setParameter("productCode",  "%"+bean.getProductCode()+"%");
				tquery.setParameter("productCode",  "%"+bean.getProductCode()+"%");
			}
			if(StringUtils.isNotEmpty(bean.getProductName())){
				query.setParameter("productName", "%"+bean.getProductName()+"%");
				tquery.setParameter("productName", "%"+bean.getProductName()+"%");
			}
			if(StringUtils.isNotEmpty(bean.getFundManagerName())){
				query.setParameter("funManager", "%"+bean.getFundManagerName()+"%");
				tquery.setParameter("funManager", "%"+bean.getFundManagerName()+"%");
			}
			if(StringUtils.isNotEmpty(bean.getFinancierName())){
				query.setParameter("financier", "%"+bean.getFinancierName()+"%");
				tquery.setParameter("financier", "%"+bean.getFinancierName()+"%");
			}
			query.setFirstResult(((pageNo==0?1:pageNo)-1)*pageSize);
	    	query.setMaxResults(pageSize);
			List<Object[]> list= query.list();
			List<FinProductBean> voList= new ArrayList<FinProductBean>();
			for(Object[] obj:list){
				FinProductBean item = new FinProductBean();
				item.setPid(null==obj[0]?"":obj[0].toString());
				item.setProductCode(obj[1]==null ?"":obj[1].toString());
				item.setProductName(obj[2]==null?"":obj[2].toString());
				item.setFundManager(obj[3]==null?"":obj[3].toString());
				item.setFinancier(obj[4]==null?"":obj[4].toString());
				item.setInTime(obj[5]==null?"":obj[5].toString());
				item.setNotes(obj[6]==null?"":obj[6].toString());
				item.setFundManagerName(obj[7]==null?"":obj[7].toString());
				item.setFinancierName(obj[8]==null?"":obj[8].toString());
				voList.add(item);
			}
			pageVo.setList(voList);
			int total =((Number) tquery.iterate().next()).intValue();
			pageVo.setTotal(total);
		}
		
		return pageVo;
	}

	
	
}
