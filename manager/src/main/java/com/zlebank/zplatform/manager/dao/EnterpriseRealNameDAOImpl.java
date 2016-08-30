package com.zlebank.zplatform.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IEnterpriseRealNameDAO;
import com.zlebank.zplatform.manager.dao.object.scan.EnterpriseRealnameMode;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseRealnameApply;
import com.zlebank.zplatform.trade.common.page.PageVo;
@Repository("enterRealNameDao")
public class EnterpriseRealNameDAOImpl  extends HibernateBaseDAOImpl<EnterpriseRealnameMode>   implements
		IEnterpriseRealNameDAO {

	@Override
	public PageVo findByPage(Map<String, Object> map, int pageNo, int pageSize) {
		PageVo<EnterpriseRealnameMode> pageVo = new PageVo<EnterpriseRealnameMode>();
		StringBuffer hq = new StringBuffer();
		if(map!=null){
			//状态 00-在用
			String status = map.get("status")==null ?"":map.get("status").toString();
			//会员ID
			String memberId = map.get("memberId")==null ?"" : map.get("memberId").toString();
			//会员名称
			String enterpriseName = map.get("enterpriseName") ==null?"": map.get("enterpriseName").toString();
			if(StringUtils.isNotEmpty(status)){
				hq.append(" and status =:status");
			}
			if(StringUtils.isNotEmpty(memberId)){
				hq.append(" and memberId like:memberId ");
			}
			if(StringUtils.isNotEmpty(enterpriseName)){
				hq.append(" and enterpriseName like:enterpriseName ");
			}
			//查询列表
			StringBuffer listHql= new StringBuffer(" from  EnterpriseRealnameMode where 1=1 ").append(hq);
			//查询总条数
			StringBuffer totalHql = new StringBuffer(" select count(*) from  EnterpriseRealnameMode where 1=1 ").append(hq);
	        
			Query query = this.getSession().createQuery(listHql.toString());
	        
			Query tquery = getSession().createQuery(totalHql.toString());
			
			if(StringUtils.isNotEmpty(status)){
				query.setParameter("status", status);
				tquery.setParameter("status", status);
			}
			if(StringUtils.isNotEmpty(memberId)){
				query.setParameter("memberId", "%"+memberId+"%");
				tquery.setParameter("memberId", "%"+memberId+"%");
			}
			if(StringUtils.isNotEmpty(enterpriseName)){
				query.setParameter("enterpriseName", "%"+enterpriseName+"%");
				tquery.setParameter("enterpriseName", "%"+enterpriseName+"%");
			}
			query.setFirstResult(((pageNo==0?1:pageNo)-1)*pageSize);
	    	query.setMaxResults(pageSize);
			List<EnterpriseRealnameMode> list= query.list();
			pageVo.setList(list);
			int total =((Number) tquery.iterate().next()).intValue();
			pageVo.setTotal(total);
		}
		return pageVo;
	}

	@Override
	public void updateApplyStatus(EnterpriseRealnameMode realNameBean) {
		getSession().merge(realNameBean);
		
	}

	@Override
	public EnterpriseRealnameMode get(Long id) {
	   Object obj=getSession().get(EnterpriseRealnameMode.class, id);
		return  (obj==null?null:(EnterpriseRealnameMode)obj);
	}
	
}
