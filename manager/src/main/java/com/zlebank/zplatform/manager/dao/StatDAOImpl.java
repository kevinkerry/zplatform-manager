package com.zlebank.zplatform.manager.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.manager.dao.iface.IStatDAO;
import com.zlebank.zplatform.manager.dao.object.TradeStatModel;

@Repository
public class StatDAOImpl implements IStatDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<TradeStatModel> queryTradeStat(String beginTime,
            String endTime,
            String payInstCode,
            String firLevMemId,
            String secLevMemId) {
        Query query = getCurrentSession().getNamedQuery("tradeStat_SP");
        query.setParameter("v_date1", beginTime);
        query.setParameter("v_date2", endTime);
        query.setParameter("v_payinst", payInstCode);
        query.setParameter("v_accfirmerno", firLevMemId);
        query.setParameter("v_accsecmerno", secLevMemId);
        return query.list();
    }

    @Transactional
    public Map<String,Object> queryEntryCount(String beginTime,
            String endTime,
            String acctCode,
            int page,
            int pageSize) {
        String[] columns = new String[]{"v_date1", "v_date2", "i_no",
                "i_perno","v_acct_code"};
        Object[] paramaters = new Object[]{
                beginTime==null? null:beginTime,
                endTime==null?null:endTime,
                        page,pageSize,acctCode==null?null:acctCode};
        Map<String,Object> result = executePageOracleProcedure("{call stat_t_acc_entry.entry_count(?,?,?,?,?,?,?)}",columns
                ,paramaters,"cursor0","v_total");
        
        return result;
    }
    
    public Map<String, Object> executePageOracleProcedure(final String queryString,
            final String[] columns,
            final Object[] paramaters,
            final String cursorName,
            final String totalName) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Session session = getCurrentSession();
        returnMap = session
                .doReturningWork(new ReturningWork<Map<String, Object>>() {
                    @SuppressWarnings({"unchecked", "rawtypes"})
                    @Override
                    public Map<String, Object> execute(Connection connection)
                            throws SQLException {
                        ResultSet rs = null;
                        List resultList = null;
                        int rets = 0;
                        Map<String, Object> map = new HashMap<String, Object>();
                        CallableStatement proc = null;
                        proc = connection.prepareCall(queryString);

                        for (int i = 1; i <= columns.length; i++) {
                            proc.setObject(columns[i - 1], paramaters[i - 1]);
                        }
                        proc.registerOutParameter(totalName,
                                oracle.jdbc.OracleTypes.NUMBER);
                        proc.registerOutParameter(cursorName,
                                oracle.jdbc.OracleTypes.CURSOR);
                        // 执行过程
                        proc.execute();

                        // 获得打开的游标（结果集）
                        rets = proc.getInt(totalName);
                        rs = (ResultSet) proc.getObject(cursorName);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnCount = rsmd.getColumnCount();

                        resultList = new ArrayList();
                        while (rs.next()) {
                            Map<String, Object> value = new HashMap<String, Object>();
                            for (int i = 1; i <= columnCount; i++) {
                                value.put(rsmd.getColumnName(i),
                                        rs.getObject(i));
                            }
                            resultList.add(value);
                        }
                        map.put("total", rets);
                        map.put("rows", resultList);
                        return map;
                    }
                });
        return returnMap;
    }
}
