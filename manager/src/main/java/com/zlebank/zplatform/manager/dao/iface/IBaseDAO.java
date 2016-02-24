package com.zlebank.zplatform.manager.dao.iface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;

public interface IBaseDAO<E extends Serializable, E_PK extends Serializable> {
	
	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(final E entity);

	/**
	 * 通过主键获得实体并加锁
	 * 
	 * @param id
	 * @param mode
	 * @return
	 */
	E getWithLock(E_PK id, LockMode mode);
	/**
	 * 保存多个实体
	 * @param entity
	 */
	public void save(final List<E> entity);
	/**
	 * 更新实体
	 * @param entity
	 */
	public void update(final E entity);
	
	/**
	 * 更新多个实体
	 * @param entity
	 */
	public void update(final List<E> entity);

	/**
	 * 保存或更新实体
	 * @param entity
	 */
	public void saveOrUpdate(final E entity);

	/**
	 * 删除实体
	 * @param entity
	 */
	public void delete(final E entity);
	
	/**
	 * 通过主键获取实体
	 * @param id
	 * @return
	 */
	public E get(final E_PK id);
	
	public List<E> getNamedQuery(final String queryName, final Object[] paramaters);
	
	/**
	 * 执行HQL语句取得唯一实体
	 * @param queryString
	 * @param paramaters
	 * @return
	 */
	public E getUniqueByHQL(final String queryString,final Object[] paramaters);
	
	/**
	 * 执行SQL语句取得唯一实体
	 * @param queryString
	 * @param paramaters
	 * @return
	 */
	public E getUniqueBySQL(final String queryString,final Object[] paramaters);

	/**
	 * 取得全部实体
	 * @return
	 */
	public List<E> findAll();
	
	/**
	 * 通过一个属性名称取得实体集合
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<E> findByProperty(final String propertyName, final Object value);
	
	/**
	 * 通过多个属性名称取得实体集合
	 * @param variable
	 * @return
	 */
	public List<E> findByProperty(final Map<String, Object> variable);
	
	/**
	 * 通过SQL语句取得实体集合
	 * @param queryString SQL语句
	 * @param paramaters 参数数组
	 * @return
	 */
	public List<?> queryBySQL(final String queryString,final Object[] paramaters);

	/**
	 * 通过SQL语句取得实体集合（用于SQL语句中含有in的查询）
	 * @param queryString
	 * @param paramaterName
	 * @param paramater
	 * @return
	 */
	public List<?> queryBySQL(final String queryString,final String paramaterName,final Collection<?> paramater);

	/**
	 * 通过HQL语句取得实体集合
	 * @param queryString
	 * @param paramaters
	 * @return
	 */
	public List<?> queryByHQL(final String queryString,final Object[] paramaters);
	/**
	 * 无结果集存储过程调用
	 * @param queryString
	 * @param paramaters
	 * @return
	 */
	public void updateBySQL(String queryString, Object[] paramaters);
	/**
	 * 使用SQL语句取得实体集（其中包含需要提交事务的语句（如存储过程））
	 * @param queryString
	 * @param paramaters
	 * @return
	 */
	public List<?> executeBySQL(final String queryString,final Object[] paramaters);
	
	/**
	 * 通过HQL语句取得实体集合（用于HQL语句中含有in的查询）
	 * @param queryString
	 * @param paramaterName
	 * @param paramater
	 * @return
	 */
	public List<?> queryByHQL(final String queryString,final String paramaterName,final Collection<?> paramater);
	
	/**
	 * 通过HQL语句进行更新操作
	 * @param queryString
	 * @param paramaters
	 */
	public void updateByHQL(final String queryString,final Object[] paramaters);
	
	/**
	 * 通过HQL语句进行更新操作（用于HQL语句中含有in）
	 * @param queryString
	 * @param paramaterName
	 * @param paramater
	 */
	public void updateByHQL(final String queryString,final String paramaterName,final Collection<?> paramater);
	
	/**
	 * 执行Oracle的存储过程
	 * @param queryString
	 * @param paramaters
	 * @return
	 */
	public List<Map<String, Object>> executeOracleProcedure(String queryString,String[] columns, Object[] paramaters,String cursor);
	
	/**
	 * 执行 多条Oracle的存储过程
	 * @param queryString
	 * @param paramaters
	 * @return
	 * @since 2012-11-23 
	 * add by yangying 
	 * need test to confirm if there has bugs
	 */
	public List<Map<String, Object>> executeMuliOracleProcedure(String queryString[],String[][] columns, Object[][] paramaters,String cursor[]);
	/*public PaginationSupport<?> findPaginationSupport(final String queryString,
			final Object[] paramaters, final int page, final int pageSize);
	
	public PaginationSupport<?> findPaginationSupportBySQL(final String queryString,
			final Object[] paramaters, final int page, final int pageSize);*/
	
	public List<Map<String, Object>> executeOracleProcedureBatch(String queryString,String[][] columns, Object[][] paramaters);
	/**
	 * 执行Oracle的存储过程,返回字符串
	 * @param queryString
	 * @param paramaters
	 * @param outparameter 输出参数名称
	 * @return
	 */
	public String executeVarcharOracleProcedure(String queryString,String[] columns, Object[] paramaters,String outparameter);
	/**
	 * 执行Oracle的存储过程（分页）,返回MAP
	 * @param queryString
	 * @param paramaters
	 * @param outparameter 输出参数名称 游标
	 * @param totalName 输出参数名称 总行数
	 * @return
	 */
	public Map<String, Object> executePageOracleProcedure(String queryString,String[] columns, Object[] paramaters,String cursorName,String totalName);
}
