package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.IndustryGroupModel;


public interface IndustryAccountGroupService extends IBaseService<IndustryGroupModel, String> {

    List<Map<String, Object>> getMerchNameAndInstiName(String merchId);

    List<Map<String, Object>> getmemberInfo(String memberid);

    List<Map<String, Object>> getAllGroupCodeAndName();


}
