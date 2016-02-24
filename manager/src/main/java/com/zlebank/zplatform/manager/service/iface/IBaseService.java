package com.zlebank.zplatform.manager.service.iface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IBaseService<E extends Serializable, E_PK extends Serializable> {
	public void save(final E entity);

	public void save(final List<E> entity);
	
	public void update(final E entity);
	
	public void update(final List<E> entity);
	
	public void saveOrUpdate(final E entity);

	public void delete(final E entity);
	
	public E get(final E_PK id);
	
	public List<E> getNamedQuery(String queryName, Object[] paramaters);
	
	public E getUniqueByHQL(String queryString, Object[] paramaters);
	
	public E getUniqueBySQL(String queryString, Object[] paramaters);

	public List<E> findAll();
	
	public List<E> findByProperty(final String propertyName, final Object value);
	
	public List<E> findByProperty(final Map<String, Object> variable);
	
	public List<?> queryBySQL(
			final String queryString,final Object[] paramaters);

	public List<?> queryBySQL(String queryString,String paramaterName,Collection<?> paramater);

	public List<?> queryByHQL(
			final String queryString,final Object[] paramaters);
	
	public List<?> queryByHQL(final String queryString,final String paramaterName,final Collection<?> paramater);
	
	public List<?> executeBySQL(final String queryString,final Object[] paramaters);
	
	public void updateByHQL(final String queryString,final Object[] paramaters);
	
	public void updateByHQL(final String queryString,final String paramaterName,final Collection<?> paramater);
	
	public List<Map<String, Object>> executeOracleProcedure(String queryString,String[] columns, Object[] paramaters,String cursor);
	/*public PaginationSupport<?> findPaginationSupport(final String queryString,
			final Object[] paramaters, final int page, final int pageSize);
	
	public PaginationSupport<?> findPaginationSupportBySQL(final String queryString,
			final Object[] paramaters, final int page, final int pageSize);*/
}
