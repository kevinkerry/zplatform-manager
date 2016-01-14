package com.zlebank.zplatform.manager.action.coopinsti;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.commons.dao.pojo.ProductModel;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.member.bean.CoopInsti;
import com.zlebank.zplatform.member.exception.AbstractCoopInstiException;
import com.zlebank.zplatform.member.service.CoopInstiProductService;
import com.zlebank.zplatform.member.service.CoopInstiService;

public class CoopInstiAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2378542712581556004L;

	@Autowired
	private CoopInstiService coopInstiServiceImpl;
	@Autowired
	private CoopInstiProductService coopInstiProductServiceImpl;

	private String coopInstiId;

	public String queryAll() {
		List<CoopInsti> resultList = coopInstiServiceImpl.getAllCoopInsti();
		try {
			json_encode(resultList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String queryCoopInstiProduct() {
		List<ProductModel> resultList;
		try {
			resultList = coopInstiProductServiceImpl.getProducts(Long
					.parseLong(coopInstiId));
			json_encode(resultList);
		} catch (AbstractCoopInstiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getCoopInstiId() {
		return coopInstiId;
	}

	public void setCoopInstiId(String coopInstiId) {
		this.coopInstiId = coopInstiId;
	}
}
