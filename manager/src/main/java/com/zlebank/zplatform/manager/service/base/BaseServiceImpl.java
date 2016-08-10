package com.zlebank.zplatform.manager.service.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.service.iface.IBaseService;

public abstract class BaseServiceImpl<E extends Serializable, E_PK extends Serializable> implements IBaseService<E, E_PK>{

	public abstract IBaseDAO<E, E_PK> getDao();
	
	public void delete(E entity) {
		getDao().delete(entity);
	}

	public List<E> findAll() {
		return getDao().findAll();
	}

	public List<E> findByProperty(String propertyName, Object value) {
		return getDao().findByProperty(propertyName, value);
	}

	/*public PaginationSupport<?> findPaginationSupport(String queryString,
			Object[] paramaters, int page, int pageSize) {
		// TODO Auto-generated method stub
		return getDao().findPaginationSupport(queryString, paramaters, page, pageSize);
	}

	public PaginationSupport<?> findPaginationSupportBySQL(String queryString,
			Object[] paramaters, int page, int pageSize) {
		// TODO Auto-generated method stub
		return getDao().findPaginationSupport(queryString, paramaters, page, pageSize);
	}*/

	public E get(E_PK id) {
		return getDao().get(id);
	}

	public List<E> getNamedQuery(String queryName, Object[] paramaters) {
		return getDao().getNamedQuery(queryName, paramaters);
	}

	public E getUniqueByHQL(String queryString, Object[] paramaters) {
		return getDao().getUniqueByHQL(queryString, paramaters);
	}

	public E getUniqueBySQL(String queryString, Object[] paramaters) {
		return getDao().getUniqueBySQL(queryString, paramaters);
	}

	public List<?> queryByHQL(String queryString,Object[] paramaters ) {
		return getDao().queryByHQL(queryString,paramaters );
	}

	public List<?> queryByHQL(String queryString, String paramaterName,
			Collection<?> paramater) {
		return getDao().queryByHQL(queryString, paramaterName, paramater);
	}

	public List<?> queryBySQL(String queryString,Object[] paramaters) {
		return getDao().queryBySQL(queryString,paramaters );
	}

	public List<?> queryBySQL(String queryString, String paramaterName,
			Collection<?> paramater) {
		return getDao().queryBySQL(queryString, paramaterName, paramater);
	}

	public void save(E entity) {
		getDao().save(entity);
	}

	public void save(List<E> entity) {
		getDao().save(entity);
	}

	public void saveOrUpdate(E entity) {
		getDao().saveOrUpdate(entity);
	}

	public void update(E entity) {
		getDao().update(entity);
	}

	public void update(List<E> entity) {
		getDao().update(entity);
	}

	public void updateByHQL(String queryString,Object[] paramaters) {
		getDao().updateByHQL(queryString,paramaters);
	}

	public void updateByHQL(String queryString, String paramaterName,
			Collection<?> paramater) {
		getDao().updateByHQL(queryString, paramaterName, paramater);
	}

	public List<E> findByProperty(Map<String, Object> variable) {
		return getDao().findByProperty(variable);
	}

	public List<?> executeBySQL(String queryString, Object[] paramaters) {
		return getDao().executeBySQL(queryString, paramaters);
	}
	public List<Map<String, Object>> executeOracleProcedure(String queryString,String[] columns, Object[] paramaters,String cursor){
		return getDao().executeOracleProcedure(queryString,columns, paramaters, cursor);
	}  
	
}
