package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.commons.dao.pojo.ProductModel;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.CashModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IProductService;

public class ProductServiceImpl extends BaseServiceImpl<ProductModel, Long> implements IProductService{

	private DAOContainer daoContainer;

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	@Override
	public IBaseDAO<ProductModel, Long> getDao() {
		return daoContainer.getProductDAO();
	}
	public Map<String, Object> findProductByPage(Map<String, Object> variables, int page,int rows) {

		String[] columns = new String[] { 
				 "v_prdtver"   ,
			     "v_prdtname"  ,
			     "i_no",
			     "i_perno" };
		
		Object[] paramaters = new Object[]{
		variables.containsKey("prdtver") ? variables.get("prdtver") : null,
		variables.containsKey("prdtname") ? variables.get("prdtname") : null,
		page,rows}; 
		return getDao().executePageOracleProcedure("{CALL PCK_T_PRODUCT.sel_t_product(?,?,?,?,?,?)}", columns,
				paramaters, "cursor0","v_total");

	}
	public List<?> queryBusinessType() {
		String[] columns = new String[] { "v_in"};
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_business(?,?)}",columns,
				paramaters, "cursor0");
	}
	 //添加一个产品
	public String AddOneProduct(ProductModel product,@SuppressWarnings("rawtypes") List listbusicode) {	
		    String  busicodeStr="";		  
		    if(product==null){
		    	return "产品不能为空";
		    }
		    if(listbusicode!=null&&listbusicode.size()>0){
		    	 for(int i=0;i<listbusicode.size();i++){
			    	  busicodeStr=busicodeStr+listbusicode.get(i).toString()+",";
			     }
		    }
			Object[] paramaters = new Object[] {product.getPrdtname(),product.getNotes(),product.getRemarks(),busicodeStr,product.getInuser()};
			String[] columns = new String[] {"v_prdtname","v_notes","v_remarks","v_casestr","v_inuser"};
			Object total =getDao().executeOracleProcedure(
					"{CALL PCK_T_PRODUCT.ins_t_product(?,?,?,?,?,?)}",
					columns,paramaters, "cursor0").get(0).get("INFO");
			return (String) total;
	}
	public List<?> queryProdCase(String  prdtver) {
		String[] columns = new String[] { "v_prdtver"};
		Object[] paramaters = new Object[1];
		paramaters[0] = prdtver;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_T_PRODUCT.sel_prod_case(?,?)}", columns,
				paramaters, "cursor0");
	}
	public String UpdateProduct(ProductModel product,@SuppressWarnings("rawtypes") List listbusicode) {	
	    String  busicodeStr="";		  
	    if(product==null){
	    	return "产品不能为空";
	    }
	    if(listbusicode!=null&&listbusicode.size()>0){
	    	 for(int i=0;i<listbusicode.size();i++){
		    	  busicodeStr=busicodeStr+listbusicode.get(i).toString()+",";
		     }
	    }
		Object[] paramaters = new Object[] {product.getPrdtver(), product.getPrdtname(),product.getNotes(),product.getRemarks(),busicodeStr,product.getInuser()};
		String[] columns = new String[] {"v_prdtver","v_prdtname","v_notes","v_remarks","v_casestr","v_upuser"};
		Object total =getDao().executeOracleProcedure(
				"{CALL PCK_T_PRODUCT.upt_t_product(?,?,?,?,?,?,?)}",
				columns,paramaters, "cursor0").get(0).get("INFO");
		return (String) total;
}
//--------------------------------------------------收银台-------------------------------------------------------------
   public Map<String, Object> findCashByPage(Map<String, Object> variables, int page,int rows) {

		String[] columns = new String[] { 
				 "v_cashver"   ,
			     "v_cashname"  ,
			     "i_no",
			     "i_perno" };
		
		Object[] paramaters = new Object[]{
		variables.containsKey("cashver") ? variables.get("cashver") : null,
		variables.containsKey("cashname") ? variables.get("cashname") : null,
		page,rows}; 
		return getDao().executePageOracleProcedure("{CALL PCK_T_CASH.sel_t_cash(?,?,?,?,?,?)}", columns,
				paramaters, "cursor0","v_total");

	}
   public String AddOneCash(CashModel cash,@SuppressWarnings("rawtypes") List listbusicode) {	
	    String  busicodeStr="";		  
	    if(cash==null){
	    	return "收银台不能为空";
	    }
	    if(listbusicode!=null&&listbusicode.size()>0){
	    	 for(int i=0;i<listbusicode.size();i++){
		    	  busicodeStr=busicodeStr+listbusicode.get(i).toString()+",";
		     }
	    }
		Object[] paramaters = new Object[] {cash.getCashname(),cash.getNotes(),cash.getRemarks(),busicodeStr,cash.getInuser()};
		String[] columns = new String[] {"v_cashname","v_notes","v_remarks","v_casestr","v_inuser"};
		Object total =getDao().executeOracleProcedure(
				"{CALL PCK_T_CASH.ins_t_cash(?,?,?,?,?,?)}",
				columns,paramaters, "cursor0").get(0).get("INFO");
		return (String) total;
       }
	public List<?> queryChnlType() {
		String[] columns = new String[] { "v_in"};
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_chnlname(?,?)}",columns,
				paramaters, "cursor0");
	}
	
	public List<?> queryCaseMark(String  cashver) {
		String[] columns = new String[] { "v_cashver"};
		Object[] paramaters = new Object[1];
		paramaters[0] = cashver;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_T_CASH.sel_cash_case(?,?)}", columns,
				paramaters, "cursor0");
	}
	public Map<String, Object> queryOneCase(String cashver) {

		String[] columns = new String[] { 
				 "v_cashver"  };
		
		Object[] paramaters = new Object[]{cashver};
		return getDao().executeOracleProcedure("{CALL PCK_T_CASH.sel_cash_date(?,?)}", columns,
				paramaters, "cursor0").get(0);
	}
	public String UpdateCash(CashModel cash,@SuppressWarnings("rawtypes") List listbusicode) {	
	    String  busicodeStr="";		  
	    if(cash==null){
	    	return "收银台不能为空";
	    }
	    if(listbusicode!=null&&listbusicode.size()>0){
	    	 for(int i=0;i<listbusicode.size();i++){
		    	  busicodeStr=busicodeStr+listbusicode.get(i).toString()+",";
		     }
	    }
		Object[] paramaters = new Object[] {cash.getCashver(), cash.getCashname(),cash.getNotes(),cash.getRemarks(),busicodeStr,cash.getInuser()};
		String[] columns = new String[] {"v_cashver","v_cashname","v_notes","v_remarks","v_casestr","v_upuser"};
		Object total =getDao().executeOracleProcedure(
				"{CALL PCK_T_CASH.upt_t_cash(?,?,?,?,?,?,?)}",
				columns,paramaters, "cursor0").get(0).get("INFO");
		return (String) total;
    }
}
