package com.zlebank.zplatform.manager.dao.object;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zlebank.zplatform.member.pojo.PojoCoopInsti;

/**
 * MerchDetaModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MERCH_DETA")
public class MerchDetaModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3063340553919898770L;
	private Long merchid;
	private String merchname;
	private String memberid;
	private String alias;
	private String engname;
	private String merchinsti;
	private String province;
	private String city;
	private String street;
	private String address;
	private String taxno;
	private String licenceno;
	private String orgcode;
	private String website;
	private String merchtype;
	private String trade;
	private String corporation;
	private String corpno;
	private String contact;
	private String contphone;
	private String conttitle;
	private String contemail;
	private String custfrom;
	private String custmgr;
	private String custmgrdept;
	private String signatory;
	private String signphone;
	private String setlcycle;
	private String bankcode;
	private String banknode;
	private String accnum;
	private String accname;
	private String charge;
	private String deposit;
	private Timestamp agreemtStart;
	private Timestamp agreemtEnd;
	private String bnkProvince;
	private String bnkCity;
	private String bnkStreet;
	private String postcode;
	private String email;
	private String corpfile;
	private String taxfile;
	private String licencefile;
	private String orgcodefile;
	private String status;
	private Timestamp firsttime;
	private String inuser;
	private Timestamp intime;
	private String stexauser;
	private Timestamp stexatime;
	private String stexaopt;
	private String cvlexauser;
	private Timestamp cvlexatime;
	private String cvlexaopt;
	private String secretKey;
	private String zonecode;
	private String prdtver;
	private String feever;
	private String spiltver;
	private String riskver;
	private String routver;
	private String notes;
	private String remarks;
   // private String instAddr;
    private String cashver;
    private String parent;
    private String contaddress;
    private String setltype;
    private String mcc;
    private String mcclist;
    private Integer isDelegation;
    private String signCertNo;
    private String cellPhoneNo;
    private String corpfileOpp;
    private String signfile;
    private String signfileOpp;
    private PojoCoopInsti coopInsti;
	// Constructors

	/** default constructor */
	public MerchDetaModel() {
	}

	/** minimal constructor */
	public MerchDetaModel(Long merchid, String memberid, String merchname,
			String alias, String province, String city, String street, String taxno,
			String licenceno, String orgcode, String corporation,
			String corpno, String contact, String bankcode, String banknode,
			String accnum, String accname, String status, Timestamp intime) {
		this.merchid = merchid;
		this.memberid = memberid;
		this.merchname = merchname;
		this.alias = alias;
		this.province = province;
		this.city = city;
		this.street = street;
		this.taxno = taxno;
		this.licenceno = licenceno;
		this.orgcode = orgcode;
		this.corporation = corporation;
		this.corpno = corpno;
		this.contact = contact;
		this.bankcode = bankcode;
		this.banknode = banknode;
		this.accnum = accnum;
		this.accname = accname;
		this.status = status;
		this.intime = intime;
	}

	/** full constructor */
	public MerchDetaModel(Long merchid, String memberid, String merchname,
			String alias, String engname, String merchinsti, String province,
			String city, String street, String address, String taxno,
			String licenceno, String orgcode, String website, String merchtype,
			String trade, String corporation, String corpno, String contact,
			String contphone, String conttitle, String contemail,
			String custfrom, String custmgr, String custmgrdept,
			String signatory, String signphone, String setlcycle,
			String bankcode, String banknode, String accnum, String accname,
			String charge, String deposit, Timestamp agreemtStart, Timestamp agreemtEnd,
			String bnkProvince, String bnkCity, String bnkStreet, String postcode,
			String email, String corpfile, String taxfile, String licencefile,
			String orgcodefile, String status, Timestamp firsttime, String inuser,
			Timestamp intime, String stexauser, Timestamp stexatime, String stexaopt,
			String cvlexauser, Timestamp cvlexatime, String cvlexaopt,
			String secretKey, String zonecode, String prdtver, String feever,
			String spiltver, String riskver, String routver, String notes,
			String remarks) {
		this.merchid = merchid;
		this.memberid = memberid;
		this.merchname = merchname;
		this.alias = alias;
		this.engname = engname;
		this.merchinsti = merchinsti;
		this.province = province;
		this.city = city;
		this.street = street;
		this.address = address;
		this.taxno = taxno;
		this.licenceno = licenceno;
		this.orgcode = orgcode;
		this.website = website;
		this.merchtype = merchtype;
		this.trade = trade;
		this.corporation = corporation;
		this.corpno = corpno;
		this.contact = contact;
		this.contphone = contphone;
		this.conttitle = conttitle;
		this.contemail = contemail;
		this.custfrom = custfrom;
		this.custmgr = custmgr;
		this.custmgrdept = custmgrdept;
		this.signatory = signatory;
		this.signphone = signphone;
		this.setlcycle = setlcycle;
		this.bankcode = bankcode;
		this.banknode = banknode;
		this.accnum = accnum;
		this.accname = accname;
		this.charge = charge;
		this.deposit = deposit;
		this.agreemtStart = agreemtStart;
		this.agreemtEnd = agreemtEnd;
		this.bnkProvince = bnkProvince;
		this.bnkCity = bnkCity;
		this.bnkStreet = bnkStreet;
		this.postcode = postcode;
		this.email = email;
		this.corpfile = corpfile;
		this.taxfile = taxfile;
		this.licencefile = licencefile;
		this.orgcodefile = orgcodefile;
		this.status = status;
		this.firsttime = firsttime;
		this.inuser = inuser;
		this.intime = intime;
		this.stexauser = stexauser;
		this.stexatime = stexatime;
		this.stexaopt = stexaopt;
		this.cvlexauser = cvlexauser;
		this.cvlexatime = cvlexatime;
		this.cvlexaopt = cvlexaopt;
		this.secretKey = secretKey;
		this.zonecode = zonecode;
		this.prdtver = prdtver;
		this.feever = feever;
		this.spiltver = spiltver;
		this.riskver = riskver;
		this.routver = routver;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "MERCHID", unique = true, nullable = false, precision = 11, scale = 0)
	public Long getMerchid() {
		return this.merchid;
	}

	public void setMerchid(Long merchid) {
		this.merchid = merchid;
	}

	@Column(name = "MEMBERID", nullable = false, length = 15)
	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	@Column(name = "MERCHNAME", nullable = false, length = 50)
	public String getMerchname() {
		return this.merchname;
	}

	public void setMerchname(String merchname) {
		this.merchname = merchname;
	}


	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getEngname() {
		return this.engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	@Column(name = "MERCHINSTI", precision = 10, scale = 0)
	public String getMerchinsti() {
		return this.merchinsti;
	}

	public void setMerchinsti(String merchinsti) {
		this.merchinsti = merchinsti;
	}

	@Column(name = "PROVINCE", nullable = false, precision = 10, scale = 0)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", nullable = false, precision = 10, scale = 0)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STREET", nullable = false, precision = 10, scale = 0)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "ADDRESS", length = 256)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TAXNO", nullable = false, length = 32)
	public String getTaxno() {
		return this.taxno;
	}

	public void setTaxno(String taxno) {
		this.taxno = taxno;
	}

	@Column(name = "LICENCENO", nullable = false, length = 32)
	public String getLicenceno() {
		return this.licenceno;
	}

	public void setLicenceno(String licenceno) {
		this.licenceno = licenceno;
	}

	@Column(name = "ORGCODE", nullable = false, length = 10)
	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	@Column(name = "WEBSITE", length = 128)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}


	public String getMerchtype() {
		return this.merchtype;
	}

	public void setMerchtype(String merchtype) {
		this.merchtype = merchtype;
	}


	public String getTrade() {
		return this.trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	@Column(name = "CORPORATION", nullable = false, length = 32)
	public String getCorporation() {
		return this.corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	@Column(name = "CORPNO", nullable = false, length = 18)
	public String getCorpno() {
		return this.corpno;
	}

	public void setCorpno(String corpno) {
		this.corpno = corpno;
	}

	@Column(name = "CONTACT", nullable = false, length = 32)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "CONTPHONE", length = 32)
	public String getContphone() {
		return this.contphone;
	}

	public void setContphone(String contphone) {
		this.contphone = contphone;
	}

	@Column(name = "CONTTITLE", length = 32)
	public String getConttitle() {
		return this.conttitle;
	}

	public void setConttitle(String conttitle) {
		this.conttitle = conttitle;
	}

	@Column(name = "CONTEMAIL")
	public String getContemail() {
		return this.contemail;
	}

	public void setContemail(String contemail) {
		this.contemail = contemail;
	}

	@Column(name = "CUSTFROM", length = 64)
	public String getCustfrom() {
		return this.custfrom;
	}

	public void setCustfrom(String custfrom) {
		this.custfrom = custfrom;
	}

	@Column(name = "CUSTMGR", length = 32)
	public String getCustmgr() {
		return this.custmgr;
	}

	public void setCustmgr(String custmgr) {
		this.custmgr = custmgr;
	}

	@Column(name = "CUSTMGRDEPT", length = 32)
	public String getCustmgrdept() {
		return this.custmgrdept;
	}

	public void setCustmgrdept(String custmgrdept) {
		this.custmgrdept = custmgrdept;
	}

	@Column(name = "SIGNATORY", length = 32)
	public String getSignatory() {
		return this.signatory;
	}

	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}


	public String getSignphone() {
		return this.signphone;
	}

	public void setSignphone(String signphone) {
		this.signphone = signphone;
	}

	@Column(name = "SETLCYCLE", precision = 10, scale = 0)
	public String getSetlcycle() {
		return this.setlcycle;
	}

	public void setSetlcycle(String setlcycle) {
		this.setlcycle = setlcycle;
	}

	@Column(name = "BANKCODE", nullable = false, length = 12)
	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	@Column(name = "BANKNODE", nullable = false, length = 12)
	public String getBanknode() {
		return this.banknode;
	}

	public void setBanknode(String banknode) {
		this.banknode = banknode;
	}

	@Column(name = "ACCNUM", nullable = false, length = 32)
	public String getAccnum() {
		return this.accnum;
	}

	public void setAccnum(String accnum) {
		this.accnum = accnum;
	}

	@Column(name = "ACCNAME", nullable = false, length = 32)
	public String getAccname() {
		return this.accname;
	}

	public void setAccname(String accname) {
		this.accname = accname;
	}

	@Column(name = "CHARGE", precision = 10)
	public String getCharge() {
		return this.charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	@Column(name = "DEPOSIT", precision = 10)
	public String getDeposit() {
		return this.deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	 
	@Column(name = "AGREEMT_START", length = 7)
	public Timestamp getAgreemtStart() {
		return this.agreemtStart;
	}

	public void setAgreemtStart(Timestamp agreemtStart) {
		this.agreemtStart = agreemtStart;
	}

	 
	@Column(name = "AGREEMT_END", length = 7)
	public Timestamp getAgreemtEnd() {
		return this.agreemtEnd;
	}

	public void setAgreemtEnd(Timestamp agreemtEnd) {
		this.agreemtEnd = agreemtEnd;
	}

	@Column(name = "BNK_PROVINCE", precision = 10, scale = 0)
	public String getBnkProvince() {
		return this.bnkProvince;
	}

	public void setBnkProvince(String bnkProvince) {
		this.bnkProvince = bnkProvince;
	}

	@Column(name = "BNK_CITY", precision = 10, scale = 0)
	public String getBnkCity() {
		return this.bnkCity;
	}

	public void setBnkCity(String bnkCity) {
		this.bnkCity = bnkCity;
	}

	@Column(name = "BNK_STREET", precision = 10, scale = 0)
	public String getBnkStreet() {
		return this.bnkStreet;
	}

	public void setBnkStreet(String bnkStreet) {
		this.bnkStreet = bnkStreet;
	}

	@Column(name = "POSTCODE", length = 6)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CORPFILE", length = 128)
	public String getCorpfile() {
		return this.corpfile;
	}

	public void setCorpfile(String corpfile) {
		this.corpfile = corpfile;
	}

	@Column(name = "TAXFILE", length = 128)
	public String getTaxfile() {
		return this.taxfile;
	}

	public void setTaxfile(String taxfile) {
		this.taxfile = taxfile;
	}

	@Column(name = "LICENCEFILE", length = 128)
	public String getLicencefile() {
		return this.licencefile;
	}

	public void setLicencefile(String licencefile) {
		this.licencefile = licencefile;
	}

	@Column(name = "ORGCODEFILE", length = 128)
	public String getOrgcodefile() {
		return this.orgcodefile;
	}

	public void setOrgcodefile(String orgcodefile) {
		this.orgcodefile = orgcodefile;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
 
	@Column(name = "FIRSTTIME", length = 7)
	public Timestamp getFirsttime() {
		return this.firsttime;
	}

	public void setFirsttime(Timestamp firsttime) {
		this.firsttime = firsttime;
	}

	@Column(name = "INUSER", precision = 10, scale = 0)
	public String getInuser() {
		return this.inuser;
	}

	public void setInuser(String inuser) {
		this.inuser = inuser;
	}
 
	@Column(name = "INTIME", nullable = false, length = 7)
	public Timestamp getIntime() {
		return this.intime;
	}

	public void setIntime(Timestamp intime) {
		this.intime = intime;
	}

	@Column(name = "STEXAUSER", precision = 10, scale = 0)
	public String getStexauser() {
		return this.stexauser;
	}

	public void setStexauser(String stexauser) {
		this.stexauser = stexauser;
	}
 
	@Column(name = "STEXATIME", length = 7)
	public Timestamp getStexatime() {
		return this.stexatime;
	}

	public void setStexatime(Timestamp stexatime) {
		this.stexatime = stexatime;
	}

	@Column(name = "STEXAOPT", length = 256)
	public String getStexaopt() {
		return this.stexaopt;
	}

	public void setStexaopt(String stexaopt) {
		this.stexaopt = stexaopt;
	}

	@Column(name = "CVLEXAUSER", precision = 10, scale = 0)
	public String getCvlexauser() {
		return this.cvlexauser;
	}

	public void setCvlexauser(String cvlexauser) {
		this.cvlexauser = cvlexauser;
	}

	 
	@Column(name = "CVLEXATIME", length = 7)
	public Timestamp getCvlexatime() {
		return this.cvlexatime;
	}

	public void setCvlexatime(Timestamp cvlexatime) {
		this.cvlexatime = cvlexatime;
	}

	@Column(name = "CVLEXAOPT", length = 256)
	public String getCvlexaopt() {
		return this.cvlexaopt;
	}

	public void setCvlexaopt(String cvlexaopt) {
		this.cvlexaopt = cvlexaopt;
	}

	@Column(name = "SECRET_KEY", length = 32)
	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Column(name = "ZONECODE", length = 8)
	public String getZonecode() {
		return this.zonecode;
	}

	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}

	@Column(name = "PRDTVER", length = 8)
	public String getPrdtver() {
		return this.prdtver;
	}

	public void setPrdtver(String prdtver) {
		this.prdtver = prdtver;
	}

	@Column(name = "FEEVER", length = 8)
	public String getFeever() {
		return this.feever;
	}

	public void setFeever(String feever) {
		this.feever = feever;
	}

	@Column(name = "SPILTVER", length = 8)
	public String getSpiltver() {
		return this.spiltver;
	}

	public void setSpiltver(String spiltver) {
		this.spiltver = spiltver;
	}

	@Column(name = "RISKVER", length = 8)
	public String getRiskver() {
		return this.riskver;
	}

	public void setRiskver(String riskver) {
		this.riskver = riskver;
	}

	@Column(name = "ROUTVER", length = 8)
	public String getRoutver() {
		return this.routver;
	}

	public void setRoutver(String routver) {
		this.routver = routver;
	}

	@Column(name = "NOTES", length = 256)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "REMARKS", length = 256)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/*public String getInstAddr() {
		return instAddr;
	}
	
	public void setInstAddr(String instAddr) {
		this.instAddr = instAddr;
	}*/
	
	public String getCashver() {
		return cashver;
	}

	public void setCashver(String cashver) {
		this.cashver = cashver;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getContaddress() {
		return contaddress;
	}

	public void setContaddress(String contaddress) {
		this.contaddress = contaddress;
	}

	public String getSetltype() {
		return setltype;
	}

	public void setSetltype(String setltype) {
		this.setltype = setltype;
	}
	
	@Column(name = "MCC", length = 4)
	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	
	@Column(name = "MCCLIST", length = 4)
	public String getMcclist() {
		return mcclist;
	}

	public void setMcclist(String mcclist) {
		this.mcclist = mcclist;
	}

	@Column(name = "ISDELEGATION", length = 1)
	public Integer getIsDelegation() {
		return isDelegation;
	}
	
	public void setIsDelegation(Integer isDelegation) {
		this.isDelegation = isDelegation;
	}
	
	@Column(name = "SIGNCERTNO", length = 32)
	public String getSignCertNo() {
		return signCertNo;
	}

	public void setSignCertNo(String signCertNo) {
		this.signCertNo = signCertNo;
	}
	
	@Column(name = "CELLPHONENO", length = 32)
	public String getCellPhoneNo() {
		return cellPhoneNo;
	}

	public void setCellPhoneNo(String cellPhoneNo) {
		this.cellPhoneNo = cellPhoneNo;
	}
	@Column(name = "CORPFILEOOP", length = 128)
    public String getCorpfileOpp() {
        return corpfileOpp;
    }

    public void setCorpfileOpp(String corpfileOpp) {
        this.corpfileOpp = corpfileOpp;
    }
    @Column(name = "SIGNCERTFILE", length = 128)
    public String getSignfile() {
        return signfile;
    }

    public void setSignfile(String signfile) {
        this.signfile = signfile;
    }
    @Column(name = "SIGNCERTFILEOPP", length = 128)
    public String getSignfileOpp() {
        return signfileOpp;
    }

    public void setSignfileOpp(String signfileOpp) {
        this.signfileOpp = signfileOpp;
    }
    
    @ManyToOne
    @JoinColumn(name="COOP_INSTI_ID")
	public PojoCoopInsti getCoopInsti() {
		return coopInsti;
	}

	public void setCoopInsti(PojoCoopInsti coopInsti) {
		this.coopInsti = coopInsti;
	}
}