package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_mcc_desc")
public class MccDescModel {
	
	private String mcc;
	private String mccName;
	
	@Id
	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	
	@Column(name="mccname",length=4)
	public String getMccName() {
		return mccName;
	}

	public void setMccName(String mccName) {
		this.mccName = mccName;
	}
}
