package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.DeptModel;

public interface IDeptDAO extends IBaseDAO<DeptModel, Long>{

    List<?> updateDeptNotes(String deptCode, String notes);

}
