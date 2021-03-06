package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;

public interface IBnkTxnService extends IBaseService<BnkTxnModel, Long>{
	public List<Map<String, Object>> saveBnkTxn(BnkTxnModel bnktxn) ;

    public Boolean upLoad(String uploadFileName);

    public void updateUploadLog(String uploadFileName);
    
    public void deleteFailedWechatUploadLog(String uploadFileName);
}
