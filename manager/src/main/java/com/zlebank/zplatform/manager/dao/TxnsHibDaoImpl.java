package com.zlebank.zplatform.manager.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.ITxnsHibDao;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;
@Repository("txnsHibDao")
public class TxnsHibDaoImpl extends HibernateBaseDAOImpl<PojoTxnsLog> implements ITxnsHibDao{
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
            
            
            proc.registerOutParameter(cursorName, oracle.jdbc.OracleTypes.CURSOR);
            proc.registerOutParameter(totalName, oracle.jdbc.OracleTypes.NUMBER);
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
