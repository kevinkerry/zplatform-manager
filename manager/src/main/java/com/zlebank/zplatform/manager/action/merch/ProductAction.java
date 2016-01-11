package com.zlebank.zplatform.manager.action.merch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.CashModel;
import com.zlebank.zplatform.manager.dao.object.ProductModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;

public class ProductAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private ServiceContainer serviceContainer;
	private ProductModel productModel;
	private CashModel cashModel;
	private String pid;
	private List checkboxList;
	private List<ProductModel> groupList;
	private Map<String, Object> cashMap;
	
/*    
    private GroupCaseModel groupcase;
	private String pid;
	private String pCode;
*/
	//产品信息管理页面
    public String show(){
			return SUCCESS;
	}

	//产品组合分页查询
	public String  queryProduct(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if(productModel==null){
			productModel=new ProductModel();
		}
		variables.put("prdtver", productModel.getPrdtver());
		variables.put("prdtname", productModel.getPrdtname());
		Map<String, Object> groupList=serviceContainer.getProductService().findProductByPage(variables, getPage(), getRows());
		json_encode(groupList);
		return null;
	}
	//产品新增
	public String AddProduct(){
		productModel.setInuser(getCurrentUser().getUserId());
		String markString=serviceContainer.getProductService().AddOneProduct(productModel, checkboxList);
		json_encode(markString);
		return null;
	}
	//业务类型
	 public String queryBusinessType() throws Exception {
        List<?> paralist2=(List<?>) serviceContainer.getProductService().queryBusinessType();
	    json_encode(paralist2);
        return null; 
	}
    //查询一条产品信息
	public String queryOneProduct(){
	    	if(pid!=null&&!pid.equals("")){
	    		List<?> product=serviceContainer.getProductService().findByProperty("prdtver", pid);
	    		if(product.size()>0){
	    			productModel=(ProductModel) product.get(0);
	    		}
                json_encode(productModel);
	    	}
	    	
	    	return null;
	 }
	 //查询业务类型，标记拥有的业务
	 public String queryProductCase(){
	    	if(pid!=null&&!pid.equals("")){
	    		List<?> caselist=serviceContainer.getProductService().queryProdCase(pid);
                try {
					json_encode(caselist);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
	    	return null;
	 }
	//产品修改
	public String UpdateProduct(){
		productModel.setInuser(getCurrentUser().getUserId());
		String markString=serviceContainer.getProductService().UpdateProduct(productModel, checkboxList);
		json_encode(markString);
		return null;
	}
	//-----------------------------------------------收银台----------------------------------------------------------------
    public String showCash(){
		return "cash_manager";
    }
	//收银台分页查询
	public String  queryCash(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if(cashModel==null){
			cashModel=new CashModel();
		}
		variables.put("cashver", cashModel.getCashver());
		variables.put("cashname", cashModel.getCashname());
		Map<String, Object> groupList=serviceContainer.getProductService().findCashByPage(variables, getPage(), getRows());
		json_encode(groupList);
		return null;
	}
	
	//收银台新增
	public String AddCash(){
		cashModel.setInuser(getCurrentUser().getUserId());
		String markString=serviceContainer.getProductService().AddOneCash(cashModel, checkboxList);
		json_encode(markString);
		return null;
	}
	//支付渠道
	public String queryChnlType() throws Exception {
	    List<?> paralist2=(List<?>) serviceContainer.getProductService().queryChnlType();
		json_encode(paralist2);
	    return null; 
   }
	 //查询业务类型，标记拥有的业务
    public String queryChnlMark(){
	    if(pid!=null&&!pid.equals("")){
	    	List<?> caselist=serviceContainer.getProductService().queryCaseMark(pid);
             try {
				json_encode(caselist);
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}	    	
	    return null;
	 }
    //查询一条收银台，显示到修改页面
   	public String queryOneCash(){
   	    	if(pid!=null&&!pid.equals("")){
   	    		cashMap=serviceContainer.getProductService().queryOneCase(pid);
                json_encode(cashMap);
   	    	}
   	    	return null;
   	 }
    //产品修改
  	public String UpdateCash(){
  		cashModel.setInuser(getCurrentUser().getUserId());
  		String markString=serviceContainer.getProductService().UpdateCash(cashModel, checkboxList);
  		json_encode(markString);
  		return null;
  	}
	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}
	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public List getCheckboxList() {
		return checkboxList;
	}
	public void setCheckboxList(List checkboxList) {
		this.checkboxList = checkboxList;
	}
	public List<ProductModel> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<ProductModel> groupList) {
		this.groupList = groupList;
	}
	public ProductModel getProductModel() {
		return productModel;
	}
	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	public CashModel getCashModel() {
		return cashModel;
	}

	public void setCashModel(CashModel cashModel) {
		this.cashModel = cashModel;
	}

	public Map<String, Object> getCashMap() {
		return cashMap;
	}

	public void setCashMap(Map<String, Object> cashMap) {
		this.cashMap = cashMap;
	}
	



}
