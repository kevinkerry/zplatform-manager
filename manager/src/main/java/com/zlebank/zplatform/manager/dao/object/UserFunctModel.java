package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TUserFunct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_USER_FUNCT")
public class UserFunctModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472274870777488732L;
	private Long userFunctId;
	private Long userId;
	private Long functId;

	// Constructors

	/** default constructor */
	public UserFunctModel() {
	}

	/** full constructor */
	public UserFunctModel(Long userId, Long functId) {
		this.userId = userId;
		this.functId = functId;
	}

	// Property accessors
	@Id
	@Column(name = "USER_FUNCT_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getUserFunctId() {
		return this.userFunctId;
	}

	public void setUserFunctId(Long userFunctId) {
		this.userFunctId = userFunctId;
	}

	@Column(name = "USER_ID", precision = 10, scale = 0)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "FUNCT_ID", precision = 10, scale = 0)
	public Long getFunctId() {
		return this.functId;
	}

	public void setFunctId(Long functId) {
		this.functId = functId;
	}

}