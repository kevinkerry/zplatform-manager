package com.zlebank.zplatform.manager.dao.object;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * TFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_FUNCTION")
public class FunctionModel implements java.io.Serializable {

	// Fields

	private Long functId;
	private String functName;
	private Long functOrder;
	private String parentId;
	private String url;
	private String icon;
	private Byte status;
	private Short levelId;
	private String leafnode;
	private String sysFlag;
	private Byte functType;
	private String isadmin;
	private String moveSort;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public FunctionModel() {
	}

	/** full constructor */
	public FunctionModel(String functName, Long functOrder, String parentId,
			String url, String icon, Byte status, Short levelId,
			String leafnode, String sysFlag, Byte functType, String isadmin,
			String moveSort, String notes, String remarks) {
		this.functName = functName;
		this.functOrder = functOrder;
		this.parentId = parentId;
		this.url = url;
		this.icon = icon;
		this.status = status;
		this.levelId = levelId;
		this.leafnode = leafnode;
		this.sysFlag = sysFlag;
		this.functType = functType;
		this.isadmin = isadmin;
		this.moveSort = moveSort;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "FUNCT_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getFunctId() {
		return this.functId;
	}

	public void setFunctId(Long functId) {
		this.functId = functId;
	}

	@Column(name = "FUNCT_NAME", length = 32)
	public String getFunctName() {
		return this.functName;
	}

	public void setFunctName(String functName) {
		this.functName = functName;
	}

	@Column(name = "FUNCT_ORDER", precision = 10, scale = 0)
	public Long getFunctOrder() {
		return this.functOrder;
	}

	public void setFunctOrder(Long functOrder) {
		this.functOrder = functOrder;
	}

	@Column(name = "PARENT_ID", length = 10)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "URL", length = 128)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ICON", length = 64)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "STATUS", precision = 2, scale = 0)
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "LEVEL_ID", precision = 4, scale = 0)
	public Short getLevelId() {
		return this.levelId;
	}

	public void setLevelId(Short levelId) {
		this.levelId = levelId;
	}

	@Column(name = "LEAFNODE", length = 1)
	public String getLeafnode() {
		return this.leafnode;
	}

	public void setLeafnode(String leafnode) {
		this.leafnode = leafnode;
	}

	@Column(name = "SYS_FLAG", length = 32)
	public String getSysFlag() {
		return this.sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	@Column(name = "FUNCT_TYPE", precision = 2, scale = 0)
	public Byte getFunctType() {
		return this.functType;
	}

	public void setFunctType(Byte functType) {
		this.functType = functType;
	}

	@Column(name = "ISADMIN", length = 1)
	public String getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	@Column(name = "MOVE_SORT", length = 1)
	public String getMoveSort() {
		return this.moveSort;
	}

	public void setMoveSort(String moveSort) {
		this.moveSort = moveSort;
	}

	@Column(name = "NOTES", length = 128)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "REMARKS", length = 128)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	private String id;
	private String text;
	private String state;
	private String checked;
	private String checkbox;
	private List<FunctionModel> children;
	
	
	
	@Transient
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Transient
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	@Transient
	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}
	@Transient
	public List<FunctionModel> getChildren() {
		return children;
	}

	public void setChildren(List<FunctionModel> children) {
		this.children = children;
	}
	@Transient
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Transient
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}