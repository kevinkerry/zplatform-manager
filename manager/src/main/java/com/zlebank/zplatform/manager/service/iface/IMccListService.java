package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.MccListModel;

public interface IMccListService extends IBaseService<MccListModel, String>{
	public List<MccListModel> findAll();
}
