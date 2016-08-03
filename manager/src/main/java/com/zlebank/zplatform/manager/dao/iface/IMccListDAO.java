package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.MccListModel;

public interface IMccListDAO extends IBaseDAO<MccListModel, String>{
	public List<MccListModel> findAll();
}
