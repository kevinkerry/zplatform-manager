package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.RiskAnalyseLogModel;

public interface IRiskAnalyseLogDAO extends IBaseDAO<RiskAnalyseLogModel, Long>{

    List<?> showRulecodeList(String roletype);

}
