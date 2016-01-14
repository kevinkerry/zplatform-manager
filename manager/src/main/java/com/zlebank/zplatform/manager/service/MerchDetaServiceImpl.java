package com.zlebank.zplatform.manager.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.utils.net.ftp.AbstractFTPClient;
import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.MccListModel;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.certhandler.BusiLicePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CertPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CorpFileFacePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.CorpFileOppPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.OrgCertPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.SignFileFacePicHandler;
import com.zlebank.zplatform.manager.service.certhandler.SignFileOppPicHandler;
import com.zlebank.zplatform.manager.service.certhandler.TaxRegCertPicHandler;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.IMerchDetaService;
import com.zlebank.zplatform.manager.util.CommonUtil;
import com.zlebank.zplatform.manager.util.RSAUtils;
import com.zlebank.zplatform.manager.util.net.FTPClientFactory;
import com.zlebank.zplatform.member.service.MemberService;

public class MerchDetaServiceImpl extends BaseServiceImpl<MerchDetaModel, Long>
		implements IMerchDetaService {

	private final static Log log = LogFactory
			.getLog(MerchDetaServiceImpl.class);
	private DAOContainer daoContainer;
	private ServiceContainer serviceContainer;
	private FTPClientFactory ftpClientFactory;
	private final String merchCertRootPath = "/merchant";
	MemberService memberServiceImpl;

	@Override
	public IBaseDAO<MerchDetaModel, Long> getDao() {
		return daoContainer.getMerchDetaDAO();
	}

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	public Map<String, Object> findMerchByPage(Map<String, Object> variables,
			int page, int rows) {
		String[] columns = new String[] { "v_user", "v_memberid",
				"v_merch_name", "v_address", "v_status", "v_coop_insti_id",
				"v_flag", "i_no", "i_perno" };

		Object[] paramaters = new Object[] {
				variables.containsKey("userId") ? variables.get("userId")
						: null,
				variables.containsKey("merberId") ? variables.get("merberId")
						: null,
				variables.containsKey("merchName") ? variables.get("merchName")
						: null,
				variables.containsKey("address") ? variables.get("address")
						: null,
				variables.containsKey("status") ? variables.get("status")
						: null,
				variables.containsKey("coopInstiId") ? variables
						.get("coopInstiId") : null,
				variables.containsKey("flag") ? variables.get("flag") : null,
				page, rows };
		// busiAcctServiceImpl.openBusiAcct(member, busiAcct, userId)
		return getDao()
				.executePageOracleProcedure(
						"{CALL PCK_MERCH.sel_t_merchant_foract(?,?,?,?,?,?,?,?,?,?,?)}",
						columns, paramaters, "cursor0", "v_total");

	}

	public long findMerchByPageCount(Map<String, Object> variables) {
		String[] columns = new String[] { "v_user", "v_memberid",
				"v_merch_name", "v_address", "v_status", "v_flag", };

		Object[] paramaters = new Object[] {
				variables.containsKey("userId") ? variables.get("userId")
						: null,
				variables.containsKey("merberId") ? variables.get("merberId")
						: null,
				variables.containsKey("merchName") ? variables.get("merchName")
						: null,
				variables.containsKey("address") ? variables.get("address")
						: null,
				variables.containsKey("status") ? variables.get("status")
						: null,variables.containsKey("coopInstiId") ? variables
								.get("coopInstiId") : null,
				variables.containsKey("flag") ? variables.get("flag") : null, };

		Object total = getDao()
				.executeOracleProcedure(
						"{CALL PCK_MERCH.sel_t_merchant_foract_num(?,?,?,?,?,?,?)}",
						columns, paramaters, "cursor0").get(0).get("TOTAL");
		return Long.valueOf(total.toString());
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Map<String, String>> saveMerchDeta(MerchDetaModel merch) {
		boolean isRepeat = getDaoContainer().getMerchDetaDAO()
				.isRepeat(merch.getEmail(), merch.getCellPhoneNo(),
						merch.getMerchinsti());

		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = new HashMap<String, String>();
		if (isRepeat) {
			resultMap.put("RET", "fail");
			resultMap.put("INFO", "手机号或邮箱重复");
			result.add(resultMap);
			return result;
		}
		// 所有商户只存在一种加密方式:RSA
		merch.setSecretKey("1");

		MccListModel mccList = daoContainer.getMccListDAO().get(
				merch.getMcclist());
		merch.setMcc(mccList.getMcc());

		String[] columns = new String[] { "v_merchname", "v_merchinsti",
				"v_province", "v_city", "v_street", "v_address", "v_taxno",
				"v_licenceno", "v_orgcode", "v_website", "v_corporation",
				"v_corpno", "v_contact", "v_contphone", "v_conttitle",
				"v_contemail", "v_custfrom", "v_custmgr", "v_custmgrdept",
				"v_signatory", "v_setlcycle", "v_bankcode", "v_banknode",
				"v_accnum", "v_accname", "v_charge", "v_deposit",
				"v_agreemt_start", "v_agreemt_end", "v_postcode", "v_email",
				"v_inuser", "v_secret_key", "v_prdtver", "v_feever",
				"v_spiltver", "v_riskver", "v_routver", "v_cashver",
				"v_parent", "v_notes", "v_remarks", "v_contaddress",
				"v_setltype", "v_mcc", "v_mcclist", "v_isdelegation",
				"v_signcertno", "v_cellphoneno", "v_coop_insti_id" };
		Object[] paramaters = new Object[] {
				"".equals(merch.getMerchname()) ? null : merch.getMerchname(),
				"".equals(merch.getMerchinsti()) ? null : merch.getMerchinsti(),
				"".equals(merch.getProvince()) ? null : merch.getProvince(),
				"".equals(merch.getCity()) ? null : merch.getCity(),
				"".equals(merch.getStreet()) ? null : merch.getStreet(),
				"".equals(merch.getAddress()) ? null : merch.getAddress(),
				"".equals(merch.getTaxno()) ? null : merch.getTaxno(),
				"".equals(merch.getLicenceno()) ? null : merch.getLicenceno(),
				"".equals(merch.getOrgcode()) ? null : merch.getOrgcode(),
				"".equals(merch.getWebsite()) ? null : merch.getWebsite(),
				"".equals(merch.getCorporation()) ? null : merch
						.getCorporation(),
				"".equals(merch.getCorpno()) ? null : merch.getCorpno(),
				"".equals(merch.getContact()) ? null : merch.getContact(),
				"".equals(merch.getContphone()) ? null : merch.getContphone(),
				"".equals(merch.getConttitle()) ? null : merch.getConttitle(),
				"".equals(merch.getContemail()) ? null : merch.getContemail(),
				"".equals(merch.getCustfrom()) ? null : merch.getCustfrom(),
				"".equals(merch.getCustmgr()) ? null : merch.getCustmgr(),
				"".equals(merch.getCustmgrdept()) ? null : merch
						.getCustmgrdept(),
				"".equals(merch.getSignatory()) ? null : merch.getSignatory(),
				"".equals(merch.getSetlcycle()) ? null : merch.getSetlcycle(),
				"".equals(merch.getBankcode()) ? null : merch.getBankcode(),
				"".equals(merch.getBanknode()) ? null : merch.getBanknode(),
				"".equals(merch.getAccnum()) ? null : merch.getAccnum(),
				"".equals(merch.getAccname()) ? null : merch.getAccname(),
				"".equals(merch.getCharge()) ? null : merch.getCharge(),
				"".equals(merch.getDeposit()) ? null : merch.getDeposit(),
				"".equals(merch.getAgreemtStart()) ? null : merch
						.getAgreemtStart(),
				"".equals(merch.getAgreemtEnd()) ? null : merch.getAgreemtEnd(),
				"".equals(merch.getPostcode()) ? null : merch.getPostcode(),
				"".equals(merch.getEmail()) ? null : merch.getEmail(),
				"".equals(merch.getInuser()) ? null : merch.getInuser(),
				"".equals(merch.getSecretKey()) ? null : merch.getSecretKey(),
				"".equals(merch.getPrdtver()) ? null : merch.getPrdtver(),
				"".equals(merch.getFeever()) ? null : merch.getFeever(),
				"".equals(merch.getSpiltver()) ? null : merch.getSpiltver(),
				"".equals(merch.getRiskver()) ? null : merch.getRiskver(),
				"".equals(merch.getRoutver()) ? null : merch.getRoutver(),
				"".equals(merch.getCashver()) ? null : merch.getCashver(),
				"".equals(merch.getParent()) ? null : merch.getParent(),
				"".equals(merch.getNotes()) ? null : merch.getNotes(),
				"".equals(merch.getRemarks()) ? null : merch.getRemarks(),
				"".equals(merch.getContaddress()) ? null : merch
						.getContaddress(),
				"".equals(merch.getSetltype()) ? null : merch.getSetltype(),
				"".equals(merch.getMcc()) ? null : merch.getMcc(),
				"".equals(merch.getMcclist()) ? null : merch.getMcclist(),
				"".equals(merch.getIsDelegation()) ? null : merch
						.getIsDelegation(),
				"".equals(merch.getSignCertNo()) ? null : merch.getSignCertNo(),
				"".equals(merch.getCellPhoneNo()) ? null : merch
						.getCellPhoneNo(), merch.getCoopInsti().getId() };
		List<?> dbResult = getDao()
				.executeOracleProcedure(
						"{CALL PCK_MERCH.pro_i_t_merch_deta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
						columns, paramaters, "cursor0");
		if (dbResult == null || dbResult.get(0) == null) {
			resultMap.clear();
			resultMap.put("RET", "fail");
			resultMap.put("INFO", "操作失败,请联系技术人员");
			result.add(resultMap);
		} else {
			resultMap = (Map<String, String>) dbResult.get(0);
		}
		result.add(resultMap);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<?> saveChangeMerchDeta(MerchDetaModel merch) {
		boolean hasSame = getDaoContainer().getMerchDetaDAO().hasSame(
				merch.getMemberid(), merch.getEmail(), merch.getCellPhoneNo());
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = new HashMap<String, String>();
		if (hasSame) {
			resultMap.put("RET", "fail");
			resultMap.put("INFO", "手机号或邮箱重复");
			result.add(resultMap);
			return result;
		}
		// 所有商户只存在一种加密方式:RSA
		merch.setSecretKey("1");

		MerchDetaModel oldMerchInfo = daoContainer.getMerchDetaDAO().get(
				merch.getMerchid());

		if (merch.getBanknode() == null || merch.getBanknode().equals("")) {
			merch.setBankcode(oldMerchInfo.getBankcode());
			merch.setBanknode(oldMerchInfo.getBanknode());
		} else {
			Object[] paramaters = merch.getBanknode().split(",");
			merch.setBankcode(paramaters[0].toString());
			merch.setBanknode(paramaters[1].toString());
		}

		MccListModel mccList = daoContainer.getMccListDAO().get(
				merch.getMcclist());
		merch.setMcc(mccList.getMcc());
		merch.setInuser(oldMerchInfo.getInuser());
		String[] columns = new String[] { "v_merchid", "v_memberid",
				"v_merchname", "v_merchinsti", "v_province", "v_city",
				"v_street", "v_address", "v_taxno", "v_licenceno", "v_orgcode",
				"v_website", "v_corporation", "v_corpno", "v_contact",
				"v_contphone", "v_conttitle", "v_contemail", "v_custfrom",
				"v_custmgr", "v_custmgrdept", "v_signatory", "v_setlcycle",
				"v_bankcode", "v_banknode", "v_accnum", "v_accname",
				"v_charge", "v_deposit", "v_agreemt_start", "v_agreemt_end",
				"v_postcode", "v_email", "v_inuser", "v_secret_key",
				"v_prdtver", "v_feever", "v_spiltver", "v_riskver",
				"v_routver", "v_cashver", "v_parent", "v_notes", "v_remarks",
				"v_contaddress", "v_setltype", "v_mcc", "v_mcclist",
				"v_isdelegation", "v_signcertno", "v_cellphoneno",
				"v_coop_insti_id" };
		Object[] paramaters = new Object[] {
				"".equals(merch.getMerchid()) ? null : merch.getMerchid(),
				"".equals(merch.getMemberid()) ? null : merch.getMemberid(),
				"".equals(merch.getMerchname()) ? null : merch.getMerchname(),
				// "".equals(merch.getAlias()) ? null : merch.getAlias(),
				// "".equals(merch.getEngname()) ? null : merch.getEngname(),
				"".equals(merch.getMerchinsti()) ? null : merch.getMerchinsti(),

				"".equals(merch.getProvince()) ? null : merch.getProvince(),
				"".equals(merch.getCity()) ? null : merch.getCity(),
				"".equals(merch.getStreet()) ? null : merch.getStreet(),
				"".equals(merch.getAddress()) ? null : merch.getAddress(),
				"".equals(merch.getTaxno()) ? null : merch.getTaxno(),

				"".equals(merch.getLicenceno()) ? null : merch.getLicenceno(),
				"".equals(merch.getOrgcode()) ? null : merch.getOrgcode(),
				"".equals(merch.getWebsite()) ? null : merch.getWebsite(),
				// "".equals(merch.getMerchtype()) ? null :
				// merch.getMerchtype(),
				// "".equals(merch.getTrade()) ? null : merch.getTrade(),

				"".equals(merch.getCorporation()) ? null : merch
						.getCorporation(),
				"".equals(merch.getCorpno()) ? null : merch.getCorpno(),
				"".equals(merch.getContact()) ? null : merch.getContact(),
				"".equals(merch.getContphone()) ? null : merch.getContphone(),
				"".equals(merch.getConttitle()) ? null : merch.getConttitle(),

				"".equals(merch.getContemail()) ? null : merch.getContemail(),
				"".equals(merch.getCustfrom()) ? null : merch.getCustfrom(),
				"".equals(merch.getCustmgr()) ? null : merch.getCustmgr(),
				"".equals(merch.getCustmgrdept()) ? null : merch
						.getCustmgrdept(),
				"".equals(merch.getSignatory()) ? null : merch.getSignatory(),

				"".equals(merch.getSetlcycle()) ? null : merch.getSetlcycle(),
				// "".equals(merch.getSignphone()) ? null :
				// merch.getSignphone(),
				"".equals(merch.getBankcode()) ? null : merch.getBankcode(),
				"".equals(merch.getBanknode()) ? null : merch.getBanknode(),
				"".equals(merch.getAccnum()) ? null : merch.getAccnum(),

				"".equals(merch.getAccname()) ? null : merch.getAccname(),
				"".equals(merch.getCharge()) ? null : merch.getCharge(),
				"".equals(merch.getDeposit()) ? null : merch.getDeposit(),
				"".equals(merch.getAgreemtStart()) ? null : merch
						.getAgreemtStart(),
				"".equals(merch.getAgreemtEnd()) ? null : merch.getAgreemtEnd(),

				// "".equals(merch.getBnkProvince()) ? null :
				// merch.getBnkProvince(),
				// "".equals(merch.getBnkCity()) ? null : merch.getBnkCity(),
				// "".equals(merch.getBnkStreet()) ? null :
				// merch.getBnkStreet(),
				"".equals(merch.getPostcode()) ? null : merch.getPostcode(),
				"".equals(merch.getEmail()) ? null : merch.getEmail(),

				// "".equals(merch.getCorpfile()) ? null : merch.getCorpfile(),
				// "".equals(merch.getTaxfile()) ? null : merch.getTaxfile(),
				// "".equals(merch.getLicencefile()) ? null :
				// merch.getLicencefile(),
				// "".equals(merch.getOrgcodefile()) ? null :
				// merch.getOrgcodefile(),
				"".equals(merch.getInuser()) ? null : merch.getInuser(),

				"".equals(merch.getSecretKey()) ? null : merch.getSecretKey(),
				"".equals(merch.getPrdtver()) ? null : merch.getPrdtver(),
				"".equals(merch.getFeever()) ? null : merch.getFeever(),
				"".equals(merch.getSpiltver()) ? null : merch.getSpiltver(),
				"".equals(merch.getRiskver()) ? null : merch.getRiskver(),
				"".equals(merch.getRoutver()) ? null : merch.getRoutver(),
				"".equals(merch.getCashver()) ? null : merch.getCashver(),
				"".equals(merch.getParent()) ? null : merch.getParent(),
				"".equals(merch.getNotes()) ? null : merch.getNotes(),
				"".equals(merch.getRemarks()) ? null : merch.getRemarks(),

				"".equals(merch.getContaddress()) ? null : merch
						.getContaddress(),
				"".equals(merch.getSetltype()) ? null : merch.getSetltype(),
				"".equals(merch.getMcc()) ? null : merch.getMcc(),
				"".equals(merch.getMcclist()) ? null : merch.getMcclist(),
				"".equals(merch.getIsDelegation()) ? null : merch
						.getIsDelegation(),
				"".equals(merch.getSignCertNo()) ? null : merch.getSignCertNo(),
				"".equals(merch.getCellPhoneNo()) ? null : merch
						.getCellPhoneNo(), merch.getCoopInsti().getId() };
		List<?> dbResult = getDao()
				.executeOracleProcedure(
						"{CALL PCK_MERCH.pro_u_t_merch_deta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
						columns, paramaters, "cursor0");
		if (dbResult == null || dbResult.get(0) == null) {
			resultMap.clear();
			resultMap.put("RET", "fail");
			resultMap.put("INFO", "操作失败,请联系技术人员");
			result.add(resultMap);
		} else {
			resultMap = (Map<String, String>) dbResult.get(0);
		}
		result.add(resultMap);
		return result;
	}

	public List<?> queryCounty(long pid) {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = pid;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_county(?,?)}", columns, paramaters,
				"cursor0");
	}

	public List<?> queryTrade() {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_dic_TRADE(?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> queryMerchType() {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_dic_MERCHTYPE(?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> queryBankNode(String bankName, int page, int rows) {
		String[] columns = new String[] { "v_bank_name", "v_bank_address",
				"i_no", "i_perno" };
		Object[] paramaters = new Object[4];
		paramaters[0] = bankName;
		paramaters[1] = null;
		paramaters[2] = page;
		paramaters[3] = rows;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_t_bank_info(?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

	public Map<String, Object> queryOneMerchDeta(Long merchId, Long userId) {
		String[] columns = new String[] { "v_user", "v_merchid" };
		Object[] paramaters = new Object[2];
		paramaters[0] = userId;
		paramaters[1] = merchId;
		return (Map<String, Object>) getDao().executeOracleProcedure(
				"{CALL  PCK_MERCH.sel_t_merchant_foract_deta (?,?,?)}",
				columns, paramaters, "cursor0").get(0);
	}

	public List<?> queryMerchParent() {
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_parent_merch (?)}", new String[] {},
				new Object[] {}, "cursor0");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<?> merchAudit(MerchDetaModel merchDeta, String flag,
			String isAgree) throws Exception {
		String[] columns = new String[] { "v_user", "v_merchid", "v_opinion",
				"v_isagree", "v_flag" };
		Object[] paramaters = new Object[5];
		if (flag.equals("2")) {
			paramaters[0] = merchDeta.getStexauser();
			paramaters[2] = merchDeta.getStexaopt();
		} else {
			paramaters[0] = merchDeta.getCvlexauser();
			paramaters[2] = merchDeta.getCvlexaopt();
		}

		paramaters[1] = merchDeta.getMerchid();
		paramaters[3] = isAgree;
		paramaters[4] = null;
		List<Map<String, Object>> resultlist = getDao().executeOracleProcedure(
				"{CALL  PCK_MERCH.exam_merch_deta(?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
		String mark = (String) resultlist.get(0).get("INFO");

		if (mark.equals("操作成功!") && isAgree.equals("0") && flag.equals("3")) {// 复审通过，flag=3
			merchDeta = get(merchDeta.getMerchid());
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("merberId", merchDeta.getMemberid());
			// 商户秘钥
			Map<String, Object> merch_keyMap = RSAUtils.genKeyPair();
			Map<String, Object> plath_keyMap = RSAUtils.genKeyPair();
			String merch_publicKey = RSAUtils.getPublicKey(merch_keyMap);
			String merch_privateKey = RSAUtils.getPrivateKey(merch_keyMap);
			String plath_publicKey = RSAUtils.getPublicKey(plath_keyMap);
			String plath_privateKey = RSAUtils.getPrivateKey(plath_keyMap);
			variables.put("memberpubkey", merch_publicKey);
			variables.put("memberprikey", merch_privateKey);
			variables.put("localpubkey", plath_publicKey);
			variables.put("localprikey", plath_privateKey);
			@SuppressWarnings("unused")
			List<?> MKlist = saveMerchMk(variables);
			// 生成商户账户
			memberServiceImpl.openBusiAcct(merchDeta.getMerchname(),
					merchDeta.getMemberid(),
					Long.parseLong(merchDeta.getCvlexauser()));

		}
		return resultlist;

	}

	public List<?> saveMerchMk(Map<String, Object> variables) {

		String[] columns = new String[] { "v_memberid", "v_safetype",
				"v_memberpubkey", "v_memberprikey", "v_localpubkey",
				"v_localprikey", "v_storgetype", "v_keyflag", "v_notes",
				"v_remarks" };
		Object[] paramaters = new Object[] {
				variables.containsKey("merberId") ? variables.get("merberId")
						: null,
				"01",
				variables.containsKey("memberpubkey") ? variables
						.get("memberpubkey") : null,
				variables.containsKey("memberprikey") ? variables
						.get("memberprikey") : null,
				variables.containsKey("localpubkey") ? variables
						.get("localpubkey") : null,
				variables.containsKey("localprikey") ? variables
						.get("localprikey") : null, "01", "1", null, null };
		return getDao()
				.executeOracleProcedure(
						"{CALL  PCK_T_MERCH_MK.pro_i_t_merch_mk(?,?,?,?,?,?,?,?,?,?,?)}",
						columns, paramaters, "cursor0");
	}

	// 商户秘钥下载
	public Map<String, Object> loadMerchMk(String memberId) {
		String[] columns = new String[] { "v_memberid" };
		Object[] paramaters = new Object[1];
		paramaters[0] = memberId;
		return (Map<String, Object>) getDao().executeOracleProcedure(
				"{CALL  PCK_T_MERCH_MK.sel_t_merch_mk_deta (?,?)}", columns,
				paramaters, "cursor0").get(0);
	}

	public List<?> queryCashAll() {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_cash(?,?)}", columns, paramaters,
				"cursor0");
	}

	public List<?> queryChnlnameAll() {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_chnlname(?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> queryRouteAll() {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_route(?,?)}", columns, paramaters,
				"cursor0");
	}

	public List<?> querySetlcycleAll() {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_dic_setl_cycle(?,?)}", columns,
				paramaters, "cursor0");
	}

	// 查询产品下的分润版本
	public List<?> querySplit(String pid) {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = pid;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_split(?,?)}", columns, paramaters,
				"cursor0");
	}

	// 查询产品下的风控版本
	public List<?> queryRiskType(String pid) {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = pid;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_risk(?,?)}", columns, paramaters,
				"cursor0");
	}

	// 查询产品下的扣率版本
	public List<?> queryFee(String pid) {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = pid;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_fee(?,?)}", columns, paramaters,
				"cursor0");
	}

	// 清算类型
	public List<?> querysetltype() {
		String[] columns = new String[] { "v_in" };
		Object[] paramaters = new Object[1];
		paramaters[0] = 1;
		return getDao().executeOracleProcedure(
				"{CALL  PCK_FOR_SELECT.sel_setltype(?,?)}", columns,
				paramaters, "cursor0");
	}

	public boolean upload(long merchId, String fileName, File file,
			CertType certType) {
		if (file == null) {
			log.warn("upload to ftp get a exception.caused by:file is null");
			return false;
		}

		CertPicHandler certHandler = getCertHandler(certType);
		if (certHandler == null) {
			log.warn("upload to ftp get a exception.caused by:certHandler is null");
			return false;
		}

		MerchDetaModel merchDeta = get(merchId);
		if (merchDeta == null) {
			log.warn("upload to ftp get a exception.caused by:merch is not exist");
			return false;
		}

		fileName = certHandler.decorateFileName(fileName);
		AbstractFTPClient ftpClient = ftpClientFactory.getFtpClient();
		try {
			ftpClient.upload(getMerchCertPath(merchDeta.getMemberid()),
					fileName, file);
		} catch (IOException e) {
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			log.warn("upload to ftp get a exception.caused by:"
					+ e.getMessage());
			return false;
		}
		merchDeta = certHandler.decorate(merchDeta, fileName);
		update(merchDeta);
		return true;
	}

	private String getMerchCertPath(String memberId) {
		return merchCertRootPath + "/" + String.valueOf(memberId);
	}

	private CertPicHandler getCertHandler(CertType certType) {
		switch (certType) {
		case TAXREGCERT:
			return new TaxRegCertPicHandler();
		case BUSILICE:
			return new BusiLicePicHandler();
		case ORGCERT:
			return new OrgCertPicHandler();
		case CORPFILE_FACE:
			return new CorpFileFacePicHandler();
		case CORPFILE_OPPOSITE:
			return new CorpFileOppPicHandler();
		case SIGNATORYFILE_FACE:
			return new SignFileFacePicHandler();
		case SIGNATORYFILE_OPPOSITE:
			return new SignFileOppPicHandler();
		default:
			return null;
		}
	}

	// 开通交易商户注册
	public boolean commitMerch(long merchId) {
		String[] columns = new String[] { "v_merchid" };
		Object[] paramaters = new Object[1];
		paramaters[0] = merchId;
		List<?> result = getDao().executeOracleProcedure(
				"{CALL  PCK_MERCH.addi_merch_deta(?,?)}", columns, paramaters,
				"cursor0");
		boolean isSucc = false;
		if (result != null && !(result.get(0) == null)) {
			@SuppressWarnings("unchecked")
			Map<String, String> resultMap = (Map<String, String>) result.get(0);
			if (resultMap.containsKey("RET")
					&& resultMap.get("RET").equals("succ")) {
				isSucc = true;
			}
		}
		return isSucc;
	}

	@Override
	public String downloadFromFtp(long merchId, String targDir,
			CertType certType, boolean fouce) {
		MerchDetaModel merchModel = get(merchId);
		CertPicHandler certPicHandler = getCertHandler(certType);
		String fileName = certPicHandler.getFileName(merchModel);
		if (fileName == null) {// not upload yet
			return "";
		}
		targDir = targDir + "/" + merchModel.getMemberid();
		if (fouce || !checkLocalExist(targDir, fileName)) {// not exist in local
			try {
				ftpClientFactory.getFtpClient().download(
						getMerchCertPath(merchModel.getMemberid()), fileName,
						targDir, fileName);
				if (!checkLocalExist(targDir, fileName)) {
					return null;
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.warn("download from ftp get a exception.caused by:"
						+ e.getMessage());
				return null;
			}
		}
		return CommonUtil.DOWNLOAD_ROOTPATH + "/" + merchModel.getMemberid()
				+ "/" + fileName;
	}

	public String queryBankName(String bankNode, String bankCode) {
		return daoContainer.getMerchDetaDAO().queryBankName(bankNode, bankCode);
	}

	private boolean checkLocalExist(String dirPath, String fileName) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null) {
			return false;
		}
		for (File file : files) {
			if (file.isFile() && file.getName().equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public MemberService getMemberServiceImpl() {
		return memberServiceImpl;
	}

	public void setMemberServiceImpl(MemberService memberServiceImpl) {
		this.memberServiceImpl = memberServiceImpl;
	}

	public FTPClientFactory getFtpClientFactory() {
		return ftpClientFactory;
	}

	public void setFtpClientFactory(FTPClientFactory ftpClientFactory) {
		this.ftpClientFactory = ftpClientFactory;
	}
}
