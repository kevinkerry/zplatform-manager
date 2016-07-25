package com.zlebank.zplatform.manager.dao;

import java.util.List;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IDeptDAO;
import com.zlebank.zplatform.manager.dao.object.DeptModel;

public class DeptDAOImpl extends HibernateDAOImpl<DeptModel, Long> implements IDeptDAO{

    @Override
    public List<?> updateDeptNotes(String deptCode, String notes) {
      String sql="update T_DEPT t set  t.NOTES =?  where t.DEPT_CODE=?";
      this.updateBySQL(sql, new Object[]{notes,deptCode});
      logger.info(sql);
      return null;  
    }

}
