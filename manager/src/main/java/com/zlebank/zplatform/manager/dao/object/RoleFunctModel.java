package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TRoleFunct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ROLE_FUNCT")
public class RoleFunctModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6389885565020228656L;
	private Long roleFunctId;
	private Long roleId;
	private Long functId;

	// Constructors

	/** default constructor */
	public RoleFunctModel() {
	}

	/** full constructor */
	public RoleFunctModel(Long roleId, Long functId) {
		this.roleId = roleId;
		this.functId = functId;
	}

	// Property accessors
	
	@Id
	@Column(name = "ROLE_FUNCT_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getRoleFunctId() {
		return this.roleFunctId;
	}

	public void setRoleFunctId(Long roleFunctId) {
		this.roleFunctId = roleFunctId;
	}

	@Column(name = "ROLE_ID", nullable = false, precision = 10, scale = 0)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "FUNCT_ID", nullable = false, precision = 10, scale = 0)
	public Long getFunctId() {
		return this.functId;
	}

	public void setFunctId(Long functId) {
		this.functId = functId;
	}

}