package com.zlebank.zplatform.manager.dao.base;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.util.CommonUtil;
import com.zlebank.zplatform.manager.util.PaginationSupport;

@Transactional(propagation = Propagation.REQUIRED)
public class HibernateDAOImpl<E extends Serializable, E_PK extends Serializable> extends HibernateDaoSupport implements
        IBaseDAO<E, E_PK> {

    private static final Log log = LogFactory.getLog(HibernateDAOImpl.class);

    private Class<E> getPersistentClass() {
        Class<E> clazz = CommonUtil.getGenericClass(getClass(), 1);
        if (null == clazz)
            throw new RuntimeException(
                    "concreate class must provide entity type or the type is incorrect!");
        return clazz;
    }
    
    protected Session getCurrentSession(){
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    private String getCountHQL(String queryString) {
        int formIndex = queryString.indexOf("from");
        int whereIndex = queryString.indexOf("where");
        StringBuffer queryStringBuffer = new StringBuffer();
        queryStringBuffer.append("select count(*) as count ");
        if (formIndex < 0) {
            log.error("queryString is error");
            throw new RuntimeException();
        } else if (whereIndex > 0) {
            queryStringBuffer.append(queryString.substring(formIndex,
                    whereIndex));
        } else {
            queryStringBuffer.append(queryString.substring(formIndex));
        }
        if (whereIndex > 0) {
            queryStringBuffer.append(" ");
            queryStringBuffer.append(queryString.substring(whereIndex));
        }

        log.info("count hql is " + queryStringBuffer.toString());
        return queryStringBuffer.toString();
    }

    public void save(E entity) {
        if (log.isDebugEnabled()) {
            log.debug("saving " + getPersistentClass().getName() + " instance");
        }
        try {
            getHibernateTemplate().save(entity);
            log.info("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void save(List<E> entity) {
        if (log.isDebugEnabled()) {
            log.debug("saving " + getPersistentClass().getName() + " instance");
        }
        for (E e : entity) {
            try {
                getHibernateTemplate().save(e);
                log.info("save successful");
            } catch (RuntimeException re) {
                log.error("save failed", re);
                throw re;
            }
        }
    }

    public void update(E entity) {
        if (log.isDebugEnabled()) {
            log.debug("update " + getPersistentClass().getName() + " instance");
        }
        try {
            getHibernateTemplate().update(entity);
            log.info("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }

    }

    public void update(List<E> entity) {
        if (log.isDebugEnabled()) {
            log.debug("update " + getPersistentClass().getName() + " instance");
        }
        
        for (E e : entity) {
            try {
                getHibernateTemplate().update(e);
                log.info("update successful");
            } catch (RuntimeException re) {
                log.error("update failed", re);
                throw re;
            }

        }

    }

    public void saveOrUpdate(E entity) {
        if (log.isDebugEnabled()) {
            log.debug("attaching dirty " + getPersistentClass().getName()
                    + " instance");
        }
        try {
            getHibernateTemplate().saveOrUpdate(entity);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(E entity) {
        if (log.isDebugEnabled()) {
            log.debug("saving " + entity.getClass().getName() + " instance");
        }
        try {
            getHibernateTemplate().delete(entity);getHibernateTemplate().clear();
            log.info("save successful");
        } catch (RuntimeException e) {
            log.error("save failed", e);
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        if (log.isDebugEnabled()) {
            log.debug("finding all " + getPersistentClass().getName()
                    + " instances");
        }
        
        try {
            String queryString = "from " + getPersistentClass().getName();
            return (List<E>)getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    @SuppressWarnings("unchecked")
    public List<E> findByProperty(String propertyName, Object value) {
        if (log.isDebugEnabled()) {
            log.debug("finding " + getPersistentClass().getName()
                    + " instance with property: " + propertyName + ", value: "
                    + value);
        }
        try {
            String queryString = "from " + getPersistentClass().getName()
                    + " as model where model." + propertyName + "= ?";
            return (List<E>)getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }

    }
    @SuppressWarnings("unchecked")
    public List<E> findByProperty(Map<String, Object> variable) {

        try {
            String queryString = "from " + getPersistentClass().getName()
                    + " as model where ";
            int size = variable.size();
            Object[] value = new Object[variable.size()];
            int i = 0;
            for (String key : variable.keySet()) {
                queryString += "model." + key + "= ? and ";
                value[i] = variable.get(key);
                i++;
            }
            queryString += "1=1";
            return (List<E>)getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public E get(E_PK id) {
        if (log.isDebugEnabled()) {
            log.debug("getting " + getPersistentClass().getName()
                    + " instance with id: " + id);
        }
        try {
            E entity = (E) getHibernateTemplate().get(getPersistentClass(),
                    (Serializable) id);
            return entity;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }

    }

    public E getUniqueByHQL(String queryString, Object[] paramaters) {
        log.info("queryString is " + queryString);
        Query query = null;
        Session session = getCurrentSession();
        E result = null;
        try {
            query = session.createQuery(
                    queryString);
            if (paramaters != null) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            result = (E) query.uniqueResult();
            
        } catch (RuntimeException e) {
            log.error("find error", e);
            throw e;
        }
        
        return result;
    }

    public E getUniqueBySQL(String queryString, Object[] paramaters) {
        E result = null;
        try {
            Session session = getCurrentSession();
            SQLQuery query = session.createSQLQuery(
                    queryString);
            if (paramaters != null) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            result = (E) query.addEntity(getPersistentClass()).uniqueResult();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        return result;
    }

    public List<E> getNamedQuery(String queryName, Object[] paramaters) {
        Query query;
        List<E> result = null;
        try {
            query = getCurrentSession().getNamedQuery(queryName);
            if (null != paramaters) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            result = query.list();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public List<?> queryBySQL(String queryString,Object[] paramaters) {
        List<?> resultList = null;
        try {
            Session session = getCurrentSession();
            SQLQuery query = (SQLQuery) session
                    .createSQLQuery(queryString).setResultTransformer(
                            Transformers.ALIAS_TO_ENTITY_MAP);
            if (null != paramaters) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            resultList = query.list();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        return resultList;
    }

    public List<?> queryBySQL(String queryString, String paramaterName,
            Collection<?> paramater) {
        log.info("queryString is " + queryString);
        SQLQuery query = null;
        List<?> resultList = null;
        try {
            Session session = getCurrentSession();
            query = (SQLQuery) session.createSQLQuery(queryString).
            setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            query.setParameterList(paramaterName, paramater);
            resultList = query.list();
             
        } catch (HibernateException e) {
            log.error("sql query error", e);
            throw e;
        }
        return resultList;
    }

    public List<?> executeBySQL(String queryString, Object[] paramaters) {
        List<?> result = null;
        try {
            Session session = getCurrentSession();
            SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            if (null != paramaters) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            
            result = query.list();
            
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return result;
    }
    
    public void updateBySQL(String queryString, Object[] paramaters) {
        
        try {
            Session session = getCurrentSession();
            SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
                    ;
            if (null != paramaters) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            query.executeUpdate();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public List<Map<String, Object>> executeOracleProcedure(String queryString,String[] columns, Object[] paramaters,String cursor){
        
        List<Map<String, Object>> resultList = null;
        ResultSet rs = null;
        Connection connection = null;
        ConnectionProvider cp = ((SessionFactoryImplementor)this.getSessionFactory()).getConnectionProvider();
        java.sql.CallableStatement proc = null;
        try {
            
            log.info(""+queryString);
            connection = cp.getConnection();
            proc = connection.prepareCall(queryString); 
            
            for(int i=1;i<=columns.length;i++){
                log.info(columns[i-1]+":"+paramaters[i-1]);
                proc.setObject(columns[i-1], paramaters[i-1]);
            }
            proc.registerOutParameter(cursor, oracle.jdbc.OracleTypes.CURSOR);
            //执行过程
            proc.execute();
            
            
            //获得打开的游标（结果集）
            rs = (ResultSet)proc.getObject(cursor);
            
            ResultSetMetaData rsmd = rs.getMetaData();   
            int columnCount = rsmd.getColumnCount();   
   
            resultList = new ArrayList<Map<String,Object>>();
            while (rs.next()){ 
                Map<String,Object> value = new HashMap<String, Object>();
                for (int i=1; i<=columnCount; i++){  
                    value.put(rsmd.getColumnName(i), rs.getObject(i));
                }   
                resultList.add(value);
            }
            connection.commit();
            //proc.close();
            if(rs!=null)
            rs.close();
            if(connection!=null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(proc!=null){
                    proc.close();
                }
                if(rs!=null)
                rs.close(); 
                if(connection!=null)
                connection.close();
                
                proc=null;
                rs = null;
                connection = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return resultList;

    }
    
    public List<Map<String, Object>> executeMuliOracleProcedure(String queryString[],String[][] columns, Object[][] paramaters,String cursor[]){
        List<Map<String, Object>> resultList = null;
        if(queryString.length!=columns.length||columns.length!=paramaters.length||paramaters.length!=cursor.length){
            return null;
        }
        Connection connection =null;
        ResultSet rs = null;
        java.sql.CallableStatement proc = null;
        try {
            
            
            ConnectionProvider cp = ((SessionFactoryImplementor)this.getSessionFactory()).getConnectionProvider();
            connection = cp.getConnection();
            for(int count=0;count<queryString.length;count++){      
            
            proc = connection.prepareCall(queryString[count]); 
            
            for(int i=1;i<=columns[count].length;i++){
                proc.setObject(columns[count][i-1], paramaters[count][i-1]);
            }
            proc.registerOutParameter(cursor[count], oracle.jdbc.OracleTypes.CURSOR);
            //执行过程
            proc.execute();
            
            //获得打开的游标（结果集）
             rs = (ResultSet)proc.getObject(cursor[count]);
            ResultSetMetaData rsmd = rs.getMetaData();   
            int columnCount = rsmd.getColumnCount();   
   
            resultList = new ArrayList<Map<String,Object>>();
            while (rs.next()){ 
                Map<String,Object> value = new HashMap<String, Object>();
                for (int i=1; i<=columnCount; i++){  
                    value.put(rsmd.getColumnName(i), rs.getObject(i));
                }   
                resultList.add(value);
            }
            rs.close();
            proc.close();
            }
            if(connection!=null)
            connection.commit();
            if(rs!=null)
            rs.close();  
            if(connection!=null)
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(proc!=null){
                    proc.close();
                }
                if(rs!=null)
                rs.close(); 
                if(connection!=null)
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return resultList;
    }
    
    public List<Map<String, Object>> executeOracleProcedureBatch(String queryString,String[][] columns, Object[][] paramaters){
        List<Map<String, Object>> resultList = null;
        if(columns.length!=paramaters.length){
            return null;
        }
        Connection connection =null;
        ResultSet rs = null;
        java.sql.CallableStatement proc = null;
        try {
            ConnectionProvider cp = ((SessionFactoryImplementor)this.getSessionFactory()).getConnectionProvider();
            connection = cp.getConnection();
            proc = connection.prepareCall(queryString); 
            for(int count=0;count<columns.length;count++){  
            
            for(int i=1;i<=columns[count].length;i++){
                proc.setObject(columns[count][i-1], paramaters[count][i-1]);
            }
            proc.addBatch();
            
            //proc.registerOutParameter(cursor[count], oracle.jdbc.OracleTypes.CURSOR);
            //执行过程
            //proc.execute();
            //获得打开的游标（结果集）
            // rs = (ResultSet)proc.getObject(cursor[count]);
            // rs.close();
            }
            proc.executeBatch();
            proc.close();
            if(connection!=null)
            connection.commit();
            if(rs!=null)
            rs.close();  
            if(connection!=null)
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  finally{
            try {
                if(proc!=null){
                    proc.close();
                }
                if(rs!=null)
                rs.close(); 
                if(connection!=null)
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } 
        
        return resultList;
    }
    
    
    public List queryByHQL(String queryString, String paramaterName,
            Collection<?> paramaters) {
        log.info("queryString is " + queryString);
        Query query = null;
        List<?> result = null;
        try {
            query = getCurrentSession().createQuery(
                    queryString);
            query.setParameterList(paramaterName, paramaters);
            result = query.list();
        } catch (HibernateException e) {
            log.error("query error ", e);
            throw e;
        }
        return result;
    }

    public List<?> queryByHQL(String queryString,Object[] paramaters) {
        List<?> result = null;
        try {
            Query query = getCurrentSession().createQuery(
                    queryString);
            if (paramaters != null) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            result = query.list();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return result;
    }

    public void updateByHQL(String queryString,Object[] paramaters) {
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery(queryString);
            if (paramaters != null) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            query.executeUpdate();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }

    public void updateByHQL(String queryString, String paramaterName,
            Collection<?> paramater) {
        
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery(queryString);
            query.setParameterList(paramaterName, paramater);
            query.executeUpdate();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }

    public PaginationSupport findPaginationSupport(final String queryString,
            final Object[] paramaters, final int page, final int pageSize) {
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery(
                    getCountHQL(queryString));
            if (paramaters != null) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            ArrayList list = (ArrayList) query.list();
            int totalCount = Integer.valueOf(list.get(0).toString());
            query = null;
            query = session.createQuery(
                    queryString);
            if (paramaters != null) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            List<?> resultList = query.list();
             
            return new PaginationSupport(resultList, totalCount, page, pageSize);
        } catch (HibernateException e) {
            log.error("pagination error " + e);
            throw e;
        } catch (NumberFormatException e) {
            log.error("get totalCount error " + e);
            throw e;
        }

    }

    public PaginationSupport<?> findPaginationSupportBySQL(String queryString,
            Object[] paramaters, int page, int pageSize) {
        try {
            String sql_count = "select count(*) as total from (" + queryString
                    + ") a ";
            log.info("sql count is " + sql_count);
            Session session = getCurrentSession();
            SQLQuery query = session.createSQLQuery(sql_count);
            if (paramaters != null) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            query.addScalar("total", StandardBasicTypes.INTEGER);
            int row_total = (Integer) query.uniqueResult();
            query = null;

            query = (SQLQuery) session.createSQLQuery(queryString)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            if (page == 0) {
                page = 1;
            }
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            if (paramaters != null) {
                for (int i = 0; i < paramaters.length; i++) {
                    query.setParameter(i, paramaters[i]);
                }
            }
            List<?> resultList = query.list();
             
            return new PaginationSupport<E>(resultList, row_total, page,
                    pageSize);
        } catch (DataAccessResourceFailureException e) {
            log.error("DataAccessResourceFailure ", e);
            throw e;
        } catch (HibernateException e) {
            log.error("pagination error ", e);
            throw e;
        } catch (IllegalStateException e) {
            log.error("IllegalStateException", e);
            throw e;
        }
    }

    public E getWithLock(E_PK id, LockMode mode) {
        return (E) getHibernateTemplate().get(getPersistentClass(), id, mode);
    }
    
    public String executeVarcharOracleProcedure(String queryString,String[] columns, Object[] paramaters,String outparameter){
        String rets = "";
        Connection connection = null;
        ConnectionProvider cp = ((SessionFactoryImplementor)this.getSessionFactory()).getConnectionProvider();
        try {
            java.sql.CallableStatement proc = null;
            
            connection = cp.getConnection();
            proc = connection.prepareCall(queryString); 
            
            for(int i=1;i<=columns.length;i++){
                proc.setObject(columns[i-1], paramaters[i-1]);
            }
            
            proc.registerOutParameter(outparameter, Types.VARCHAR);
            //执行过程
            proc.execute();
            rets = proc.getString(outparameter);
            proc.close();
            if(connection!=null)
            connection.commit();
            if(connection!=null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(connection!=null)
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rets;

    }
    
    public Map<String, Object> executePageOracleProcedure(String queryString,String[] columns, Object[] paramaters,String cursorName,String totalName){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List resultList = null;
        ResultSet rs = null;
        int rets = 0;
        Connection connection = null;
        ConnectionProvider cp = ((SessionFactoryImplementor)this.getSessionFactory()).getConnectionProvider();
        try {
            java.sql.CallableStatement proc = null;
            
            connection = cp.getConnection();
            proc = connection.prepareCall(queryString); 
            
            for(int i=1;i<=columns.length;i++){
                proc.setObject(columns[i-1], paramaters[i-1]);
            }
            
            proc.registerOutParameter(totalName, oracle.jdbc.OracleTypes.NUMBER);
            proc.registerOutParameter(cursorName, oracle.jdbc.OracleTypes.CURSOR);
            //执行过程
            proc.execute();
            
            //获得打开的游标（结果集）
            rets = proc.getInt(totalName);
            rs = (ResultSet)proc.getObject(cursorName);
            ResultSetMetaData rsmd = rs.getMetaData();   
            int columnCount = rsmd.getColumnCount();   
   
            resultList = new ArrayList();
            while (rs.next()){ 
                Map<String,Object> value = new HashMap<String, Object>();
                for (int i=1; i<=columnCount; i++){  
                    value.put(rsmd.getColumnName(i), rs.getObject(i));
                }   
                resultList.add(value);
            }
            
            
            returnMap.put("total", rets);
            returnMap.put("rows", resultList);
            
            if(connection!=null)
            connection.commit();
            if(rs!=null)
            rs.close();  
            proc.close();
            if(connection!=null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(connection!=null)
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return returnMap;

    }

}
