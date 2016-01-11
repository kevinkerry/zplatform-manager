package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.CashModel;
import com.zlebank.zplatform.manager.dao.object.ProductModel;

public interface IProductService extends IBaseService<ProductModel, Long>{
	public Map<String, Object> findProductByPage(Map<String, Object> variables, int page,int rows) ;
	public List<?> queryBusinessType() ;
	public String AddOneProduct(ProductModel product,List listbusicode);
	public List<?> queryProdCase(String  prdtver) ;
	public String UpdateProduct(ProductModel product,List listbusicode);
	public Map<String, Object> findCashByPage(Map<String, Object> variables, int page,int rows);
	public String AddOneCash(CashModel cash,List listbusicode) ;
	public List<?> queryChnlType();
	public List<?> queryCaseMark(String  cashver) ;
	public Map<String, Object> queryOneCase(String cashver) ;
	public String UpdateCash(CashModel cash,List listbusicode);

}
