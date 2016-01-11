package com.zlebank.zplatform.manager.dao;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IProductDAO;
import com.zlebank.zplatform.manager.dao.object.ProductModel;

public class ProductDAOImpl extends HibernateDAOImpl<ProductModel, Long> implements IProductDAO{

}
