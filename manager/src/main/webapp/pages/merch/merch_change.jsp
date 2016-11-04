<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
</head>
<style type="text/css">
table {
	
}

table tr td {
	height: 25px;
	padding-left: 10px;
	border-width: 1px 1px 1px 1px;
	border-style: groove;
}

table tr td input {
	height: 15px
}

table tr td.head-title {
	background-color: #F0F8FF;
	font-weight: bold;
}

table tr td.head-guide {
	background-color: #F0FFFF;
	font-weight: bold;
}

table tr td font.current-step {
	color: #EE4000;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>/js/extendsValidator_1.0_20151215.js"></script>
</head>
<body>
	<div style="margin: 5px;" id="continer">
		<div id="title" name="title" class="easyui-panel" title="商户修改 "
			style="background: #fafafa;" iconCls="icon-save" collapsible="false">
			<div style="padding-left: 5px; padding-right: 5px">
				<form id="merchDetaForm"
					action="pages/merchant/saveChangeMerchDetaMerchantAction.action"
					method="post">
					<input type="hidden" id="merchApplyId" name="merchApplyId"
						value="${merchApplyId}" /> <input type="hidden"
						id="coopInstiId_old" value="${merchDeta.member.coopInstiId}" /> <input
						type="hidden" id="enterpriseInsti"
						value="${merchDeta.member.enterpriseInsti}" /> <input
						type="hidden" id="province_old"
						value="${merchDeta.member.province}" /> <input type="hidden"
						id="city_old" value="${merchDeta.member.city}" /> <input
						type="hidden" id="county_old" value="${merchDeta.member.street}" />
					<input type="hidden" id="setltype_old"
						value="${merchDeta.setlType}" /> <input type="hidden"
						id="setlcycle_old" value="${merchDeta.setlCycle}" /> <input
						type="hidden" id="banknode_old" value="${merchDetabankNode}" /> <input
						type="hidden" id="prdtver_old" value="${merchDeta.prdtVer}" /> <input
						type="hidden" id="feever_old" value="${merchDeta.feeVer}" /> <input
						type="hidden" id="spiltver_old" value="${merchDeta.spiltVer}" />
					<input type="hidden" id="riskver_old" value="${merchDeta.riskVer}" />
					<input type="hidden" id="routver_old" value="${merchDeta.routVer}" />
					<input type="hidden" id="agreemtStart_old"
						value="${merchDeta.agreemtStart}" /> <input type="hidden"
						id="agreemtEnd_old" value="${merchDeta.agreemtEnd}" /> <input
						type="hidden" id="mcclist_old" value="${merchDeta.member.mccList}" />
					<input type="hidden" id="isDelegation_old"
						value="${merchDeta.member.isDelegation}" /> <input type="hidden"
						id="bankname_old" value="${oldBankName}" />
					<table width="100%">
						<tr>
							<td colspan="4" class="head-guide"><font
								class="current-step">第一步:企业信息录入</font>---->第二步:上传证件照片</td>
						</tr>
						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center" width="20%">企业名称</td>
							<td width="30%"><input id="merName"
								name="merchDeta.member.enterpriseName"
								value="${merchDeta.member.enterpriseName}" required="true"
								maxlength="30" style="width: 250px" class="easyui-validatebox"
								validType="MerchLength[60]" /> <font color="red">*</font></td>
							<td align="center">会员编号</td>
							<td>${merchDeta.memberId}</td>
						</tr>
						<tr>
							<td align="center">企业所在地</td>
							<td colspan="3"><select id="province_ins"
								class="easyui-validatebox" required="true"
								name="merchDeta.member.province"
								onchange="showCity('province_ins')" /></select> <select id="city_ins"
								class="easyui-validatebox" required="true"
								name="merchDeta.member.city" onchange="showCounty('city_ins')" /></select>
								<select id="county_ins" class="easyui-validatebox"
								required="true" name="merchDeta.member.street" /></select> <font
								color="red">*</font></td>
						</tr>
						<tr>
							<td align="center">联系手机号</td>
							<td><input class="easyui-validatebox" maxlength="20"
								validType="cellphonenum" required="true"
								name="merchDeta.member.phone" value="${merchDeta.member.phone}" />
								<font color="red">*</font></td>
							<td align="center">邮箱</td>
							<td><input name="merchDeta.member.email" maxlength="32"
								validType="email" type="text" required="true"
								class="easyui-validatebox" value="${merchDeta.member.email}" />
								<font color="red">*</font></td>
						</tr>
						<tr>
							<td align="center">地址</td>
							<td><input class="easyui-validatebox" maxlength="256"
								name="merchDeta.member.address" style="width: 300px"
								value="${merchDeta.member.address}" /></td>
							<td align="center">邮编编码</td>
							<td><input class="easyui-validatebox"
								validType="postcode[6]" maxlength="6"
								name="merchDeta.member.postCode"
								value="${merchDeta.member.postCode}" /></td>
						</tr>
						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<!--  
					<tr> 
						<td align="center">企业类型</td>
						<td> 企业</td><td></td><td></td>
					</tr>-->
						<tr>
							<td align="center">营业执照号</td>
							<td><input name="merchDeta.member.licenceNo" maxlength="15"
								type="text" validType="licencenoMerLength[15]"
								onkeyup="value=value.replace(/[^0-9a-zA-Z]/g,'')"
								required="true" class="easyui-validatebox"
								value="${merchDeta.member.licenceNo}" /><font color="red">*</font>
							</td>
							<td align="center">组织机构代码号</td>
							<td><input name="merchDeta.member.orgCode" maxlength="10"
								type="text" validType="orgLength[8]"
								onkeyup="value=value.replace(/[^\d\-]/g,'')" required="true"
								class="easyui-validatebox" value="${merchDeta.member.orgCode}" />
								<font color="red">*</font></td>
						</tr>
						<tr>
							<td align="center">税务登记号</td>
							<td><input name="merchDeta.member.taxno" maxlength="20"
								type="text" required="true" validType="merLength[15]"
								onkeyup="value=value.replace(/[^0-9a-zA-Z]/g,'')"
								class="easyui-validatebox" value="${merchDeta.member.taxno}" />
								<font color="red">*</font></td>
							<td align="center">所属行业</td>
							<td><select id="mcclist_ins" class="easyui-validatebox"
								name="merchDeta.member.mccList"
								value="${merchDeta.member.mccList}" required="true" /></select><font
								color="red">*</font></td>
						</tr>
						<tr>
							<td align="center">企业网站地址</td>
							<td><input name="merchDeta.member.website" maxlength="256"
								type="text" validType="url" required="true"
								class="easyui-validatebox" value="${merchDeta.member.website}" />
								<font color="red">*</font></td>
							<td></td>
							<td></td>
							<!--  
						<td align="center">ICP备案号</td>
						<td>
						 	<input name="merchDate.icp" maxlength="256" type="text"  required="true" class="easyui-validatebox" /> <font color="red">*</font>
						</td>-->
						</tr>
						<tr>
							<td align="center" id="psamORpass">法人姓名</td>
							<td><input name="merchDeta.member.corporation"
								maxlength="16" type="text" required="true"
								class="easyui-validatebox"
								value="${merchDeta.member.corporation}" /> <font color="red">*</font>
							</td>
							<td align="center">法人身份证号</td>
							<td><input class="easyui-validatebox" required="true"
								validType="cardNo[18]" maxlength="18"
								name="merchDeta.member.corpNo"
								value="${merchDeta.member.corpNo}" /> <font color="red">*</font>
							</td>
						</tr>

						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center">商户清算类型</td>
							<td><select id="setltype_ins" class="easyui-validatebox"
								required="true" name="merchDeta.setlType" /></select> <font color="red">*</font>
							</td>
							<td align="center">商户清算周期</td>
							<td><select id="setlcycle_ins" class="easyui-validatebox"
								required="true" name="merchDeta.setlCycle" /></select> <font color="red">*</font>
							</td>
						</tr>
						<tr>

							<td align="center">开户行</td>
							<td colspan="3"><input id="oldBankName_input"
								readonly="true" required="true"> <a id="a_bank_info"
								href="javascript:modifyBank()" style="color: blue">修改</a> <span
								id="bank_info"> <select id="banknode_ins"
									class="easyui-validatebox" required="true"
									name="merchDeta.bankNode" style="width: 150px" /></select> <font
									color="red">*</font> <input id="banknode_key" maxlength="16"
									type="text" onclick="checkBankKey()" onchange="queryBankNode()" /></span>
							</td>
						</tr>
						<tr>
							<td align="center">开户账号</td>
							<td><input name="merchDeta.accNum" maxlength="32"
								required="true" type="text" validType="settleAccount"
								class="easyui-validatebox" value="${merchDeta.accNum}" /> <font
								color="red">*</font></td>
							<td align="center">开户名</td>
							<td><input class="easyui-validatebox" maxlength="30"
								validType="accName" required="true" name="merchDeta.accName"
								value="${merchDeta.accName}" /> <font color="red">*</font></td>
						</tr>

						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center">合作机构</td>
							<td><select id="coop_insti_ins" class="easyui-validatebox"
								required="true" name="merchDeta.member.coopInstiId"
								style="width: 150px" onchange="refreshProduct()" /></select> <font
								color="red">*</font></td>
							<td align="center" colspan="2"></td>

						</tr>
						<tr>
							<td align="center">产品</td>
							<td><select id="prdtver_ins" class="easyui-validatebox"
								required="true" name="merchDeta.prdtVer" style="width: 150px"
								onchange="showThreeVersion()" /></select> <font color="red">*</font></td>
							<td align="center">风控版本</td>
							<td><select name="merchDeta.riskVer" maxlength="8"
								required="true" id="riskver" /></select> <font color="red">*</font></td>
						</tr>
						<tr>
							<td align="center">扣率版本</td>
							<td><select id="feever_ins" class="easyui-validatebox"
								required="true" name="merchDeta.feeVer" /></select> <font color="red">*</font>
							</td>
							<td align="center">分润版本</td>
							<td><select name="merchDeta.spiltVer" maxlength="8"
								id="spiltver" /></select></td>
						</tr>
						<tr>
							<td align="center">路由版本</td>
							<td><select id="routver_ins" class="easyui-validatebox"
								name="merchDeta.routVer" style="width: 150px" required="true" /></select>
								<font color="red">*</font></td>
							<td align="center"></td>
							<td></td>
						</tr>

						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center"><input type="checkbox" id="isDelegation"
								name="merchDeta.member.isDelegation"
								onchange="checkIsDelegation()" />是否授权人办理</td>
							<td colspan="3"></td>
						</tr>
						<tr id="delegation">
							<td align="center">委托人姓名</td>
							<td><input class="easyui-validatebox" maxlength="16"
								id="signatory" name="merchDeta.member.signatory"
								value="${merchDeta.member.signatory}" /> <font color="red">*</font>
							</td>
							<td align="center">委托人身份证号</td>
							<td><input class="easyui-validatebox" validType="cardNo[18]"
								id="signCertNo" maxlength="18"
								name="merchDeta.member.signCertNo"
								value="${merchDeta.member.signCertNo}" /> <font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center">客户经理</td>
							<td><input class="easyui-validatebox" maxlength="16"
								name="merchDeta.member.custMgr"
								value="${merchDeta.member.custMgr}" /></td>
							<td align="center">客户经理部门</td>
							<td><input name="merchDeta.member.custMgrDept"
								maxlength="16" type="text"
								value="${merchDeta.member.custMgrDept}" /></td>
						</tr>
						<tr>
							<td align="center">合约开始日期</td>
							<td><input name="merchDeta.agreemtStart" maxlength="12"
								type="text" id="startDate" value="${merchDeta.agreemtStart}" />
							</td>
							<td align="center">合约终止日期</td>
							<td><input class="easyui-validatebox" maxlength="32"
								name="merchDeta.agreemtEnd" id="endDate"
								value="${merchDeta.agreemtEnd}" /></td>
						</tr>
						<tr>
							<td align="center">保证金</td>
							<td><input class="easyui-validatebox" maxlength="8"
								validType="amount" name="deposit" value="${deposit}" /><font
								color="red">元</font></td>
							<td align="center">服务费</td>
							<td><input name="charge" maxlength="8" validType="amount"
								class="easyui-validatebox" type="text" value="${charge}" /><font
								color="red">元</font></td>
						</tr>

						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center">联系人姓名</td>
							<td><input name="merchDeta.member.contact" maxlength="16"
								type="text" class="easyui-validatebox"
								value="${merchDeta.member.contact}" />
							<td align="center">联系人地址</td>
							<td><input name="merchDeta.member.contAddress"
								maxlength="128" style="width: 250px" type="text"
								class="easyui-validatebox"
								value="${merchDeta.member.contAddress}" /></td>
						</tr>
						<tr>
							<td align="center">联系人电话</td>
							<td><input class="easyui-validatebox" maxlength="11"
								validType="chinesetest" name="merchDeta.member.contPhone"
								value="${merchDeta.member.contPhone}" /></td>
							<td align="center">联系人职位</td>
							<td><input name="merchDeta.member.contTitle" maxlength="16"
								type="text" value="${merchDeta.member.contTitle}" /></td>
						</tr>
						<tr>
							<td align="center">联系人邮箱</td>
							<td><input class="easyui-validatebox" maxlength="16"
								validType="email" name="merchDeta.member.contEmail"
								value="${merchDeta.member.contEmail}" /></td>


							<td align="center">备注</td>
							<td><input class="easyui-validatebox" maxlength="50"
								name="merchDeta.notes" value="${merchDeta.notes}" /></td>

						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" id="button_id"
					href="javascript:savemerchDeta()" onclick="">下一步</a>
				<!--  <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:resize()" onclick="init()">取消</a>-->
				<a href="javascript:backToMerchIndex()" class="easyui-linkbutton"
					iconCls="icon-back">返回</a>
			</div>
		</div>
	</div>
</body>

<script>
	  $(function() {
			init();
			
		});
		
	  	function init(){
	  		
	  		var oldbankname = $("#bankname_old").val();
	  		$("#oldBankName_input").val(oldbankname);
	  		showProvince();
			showCity('province_ins');
			showCounty('city_ins');
			 
			showMccList();
			showCoopInsti() ;
			//showProduct();
			queryDistType($('#prdtver_old').val());
			queryFee($('#prdtver_old').val());
			queryRiskType($('#prdtver_old').val())
			//showCash();
			showChnlname();
		  
			showSetlcycleAll();
			showSetClearType();
			$('#startDate,#endDate').datebox({required: false});
			$("#startDate,#endDate").datebox({ editable:false});
			//$('#startDate').datebox('setValue',$('#agreemtStart_old').val());
			//$('#endDate').datebox('setValue',$('#agreemtEnd_old').val());
			initDelegation();
			$('#bank_info').hide();
	  	}
	  
		function loadOrgan() {
			var html = '<option value="">--请选择所属机构--</option>';
			$.ajax({
				type: "GET",
				url: "pages/pos/queryOrganfAction.action",
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.organId + '">' + value.organName + '</option>';
					});
					$("#dept_ins").html(html);
				}
			});
		}
		
		function savemerchDeta() {
			var jp = $("#banknode_ins").val(); 
			if ($('#merchDetaForm').form("validate")) {
				$("#button_id").linkbutton('disable');
				$("#coop_insti_ins").removeAttr('disabled');
				$('#merchDetaForm').form('submit', {
					onSubmit: function() {
						return $('#merchDetaForm').form('validate');
					},
					success: function(json) {
						json = eval('(' + json + ')');
						if (json.RET == "succ") {
							$("#button_id").linkbutton('enable');
							$.messager.confirm('提示', '保存成功,等待上传证件照片',function(data){
								if(data){
									window.location.href= "<%=basePath%>" +'/pages/merchant/toUploadMerchantAction.action?merchApplyId='+json.INFO;
								}								
							});
						} else {
							$.messager.alert('提示', json.INFO);
							$("#button_id").linkbutton('enable');
						}
					}
				});
			}
		}
		
		function showProvince() {
			$.ajax({
				async:false,
				type: "POST",
				url: "pages/merchant/queryProvinceMerchantAction.action",
				dataType: "json",
				success: function(json) {
					var province = $('#province_old').val();
					var html = "<option value=''>--请选择所属省--</option>";
					$.each(json,
					function(key, value) {
						if(value.pId==province){
							html += '<option value="' + value.pId + '" selected="selected">' + value.pName + '</option>';
						}else{
							html += '<option value="' + value.pId + '">' + value.pName + '</option>';
						}
					}) ;
					$("#province_ins").html(html);
				}
			});
		}
		
		function showCity(type) {
			var pid;
			if (type == 'province_ins') {
				pid = $("#province_ins").val();
			} else {
				pid = $("#bnkProvince_ins").val();
			}
		
			$.ajax({
				async:false,
				type: "POST",
				url: "pages/merchant/queryCityMerchantAction.action",
				data: "pid=" + pid,
				dataType: "json",
				success: function(json) {
					var city = $('#city_old').val();
					var html = "<option value=''>--请选择所属市--</option>";
					$.each(json,
					function(key, value) {
						if(value.cId==city){
							html += '<option value="' + value.cId + '" selected="selected">' + value.cName + '</option>';
						}else{
							html += '<option value="' + value.cId + '">' + value.cName + '</option>';
						}
					});
					if (type == 'province_ins') {
						$("#city_ins").html(html);
					} else {
						$("#bnkCity_ins").html(html);
					}
				}
			});
		}
		function showCounty(type) {
			var pid;
			if (type == 'city_ins') {
				pid = $("#city_ins").val();
			} else {
				pid = $("#bnkCity_ins").val();
			}
			$.ajax({
				async:false,
				type: "POST",
				url: "pages/merchant/queryCountyMerchantAction.action",
				data: "pid=" + pid,
				dataType: "json",
				success: function(json) {
					var county = $('#county_old').val();
					var html = "<option value=''>--请选择所属县--</option>";
					$.each(json,
					function(key, value) {
						if(value.T_ID==county){
							html += '<option value="' + value.T_ID + '" selected="selected">' + value.T_NAME + '</option>';
						}else{
							html += '<option value="' + value.T_ID + '">' + value.T_NAME + '</option>';
						}
					});
					if (type == 'city_ins') {
						$("#county_ins").html(html);
					} else {
						$("#bnkStreet_ins").html(html);
					}
		
				}
			});
		}
		
		function checkBankKey(){
			var pid = $("#banknode_key").val();
			 if(pid=='输入关键字检索开户行'){
				$("#banknode_key").val('');
			}
			$("#banknode_key").css({color:"#515151"});
		}
		
		function queryBankNode() {
			$("#oldBankName_input").attr("hidden","true")
			var pid = $("#banknode_key").val();
			if(pid==null||pid==''){
				$("#banknode_key").val('输入关键字检索开户行');
				$("#banknode_key").css({color:"#BEBEBE"});
				return;
			} 
			$.ajax({
				type: "POST",
				url: "pages/merchant/queryBankNodeMerchantAction.action",
				data: "bankName=" + pid,
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择开户行--</option>";
					$.each(json,
					function(key, value) {
						var codenode = value.BANK_CODE + "," + value.BANK_NODE;
						html += '<option value="' + codenode + '">' + value.BANK_NAME + '</option>';
					});
					$("#banknode_ins").html(html);
				}
			});
		}
		
		function showMerchType(){
			$.ajax({
				type: "POST",
				url: "pages/merchant/queryMerchTypeMerchantAction.action",
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择商户类型--</option>";
					$.each(json,
					function(key, value) {
						//alert(value.roleName);
						html += '<option value="' + value.PARA_CODE + '">' + value.PARA_NAME + '</option>';
					}) ;
					$("#merchtype_ins").html(html);
		
				}
			});
		}
		/*
		function showqueryTrade(){
			$.ajax({
				type: "POST",
				url: "pages/merchant/queryTrateMerchantAction.action",
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择所属行业--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.PARA_CODE + '">' + value.PARA_NAME + '</option>';
					});
					$("#trade_ins").html(html);
		
				}
			});
		}*/
		 
		
		function showMccList(){
			$.ajax({
				type: "POST",
				url: "pages/merchant/queryMccListMccAction.action",
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					var mcclist = $('#mcclist_old').val();
					var html = "<option value=''>--商户所属行业--</option>";
					$.each(json,
					function(key, value) {
						if(value.mccList==mcclist){
							html += '<option value="' + value.mccList + '" selected="selected">' + value.mccCont + '</option>';
						}else{
							html += '<option value="' + value.mccList + '">' + value.mccCont + '</option>';
						}
						
					}) ;
					$("#mcclist_ins").html(html);
		
				}
			});
		}
		
		function showUpload(type) {
			$("#btn_upload").linkbutton('enable');
			var uploadTitle;
			if (type == 'busi') {
				uploadTitle = '上传税务登记证';
			} else if (type == 'licenceno') {
				uploadTitle = '上传营业执照';
			} else if (type == 'orgcode') {
				uploadTitle = '上传组织代码证';
			} else if (type == 'corpfile') {
				uploadTitle = '上传身份证照';
			} else if (type == 'union') {
				uploadTitle = '上传入网协议';
			} else if (type == 'shop') {
				uploadTitle = '上传门店照片';
			} else if (type == 'other') {
				uploadTitle = '上传全部附件';
			}
			$("#scanType").val(type);
			$("#uptitle").html(uploadTitle.substring(2));
			$('#w').window({
				title: uploadTitle,
				width: 400,
				modal: true,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				shadow: false,
				closed: false,
				height: 120
			});
			cleanFile('imagehead');
			$('#imagehead').validatebox();
		}
		function closeUpload() {
			$('#w').window('close');
		}
		function uploadProcess() {
			var type = $("#scanType").val();
			if ($('#imagehead').validatebox("isValid")) {
				$("#btn_upload").linkbutton('disable');
				$('#theForm').ajaxSubmit({
		
					success: function(json) {
						json = eval('(' + json + ')');
		
						if (json.status == "OK") {
							$.messager.alert('提示', "上传成功");
							if (type == 'busi') {
								$("#merchant_busiScan1").val(json.fileName);
								//$("#taxfile_ins").val(json.fileName);
							} else if (type == 'licenceno') {
								$("#merchant_busiScan2").val(json.fileName);
								//$("#licencefile_ins").val(json.fileName);
							} else if (type == 'orgcode') {
								$("#merchant_busiScan3").val(json.fileName);
								//$("#orgcodefile_ins").val(json.fileName);
							} else {
								$("#merchant_busiScan4").val(json.fileName);
								//$("#corpfile_ins").val(json.fileName);
							}
							closeUpload();
						} else {
							var error = "上传失败";
							if (json.status == "file_over") {
								error = "上传失败，上传文件大小超过10M！";
							}
							$.messager.alert('提示', error);
							$("#btn_upload").linkbutton('enable');
						}
					}
				});
			}
		}
		function cleanFile(id) {
			/*alert("清除"+id);
					var _file = document.getElementById(id);    
					if(_file.files){        
						_file.value = "";    
					}else{        
						if (typeof _file != "object"){ 
							return null; 
						}        
						var _span = document.createElement("span");        
						_span.id = "__tt__";        
						_file.parentNode.insertBefore(_span,_file);        
						var tf = document.createElement("form");        
						tf.appendChild(_file);        
						document.getElementsByTagName("body")[0].appendChild(tf);        
						tf.reset();        
						_span.parentNode.insertBefore(_file,_span);        
						_span.parentNode.removeChild(_span);        
						_span = null;        
						tf.parentNode.removeChild(tf);    
					}*/
			var file = $("#" + id);
			file.after(file.clone().val(""));
			file.remove();
		}
		
		function showProduct(coopInstiId) {
			$.ajax({
				async:false,
				type: "POST",
				url: "pages/coopinsti/queryCoopInstiProductCoopInstiAction.action?coopInstiId="+coopInstiId,
				dataType: "json",
				success: function(json) {
					var prdtver = $('#prdtver_old').val();
					var html = "<option value=''>--请选择产品--</option>";
					$.each(json,
					function(key, value) {
						if(value.prdtver==prdtver){
							html += '<option value="' + value.prdtver + '" selected="selected">' + value.prdtname + '</option>';
						}else{
							html += '<option value="' + value.prdtver + '">' + value.prdtname + '</option>';
						}
					}) ;
					$("#prdtver_ins").html(html);
				}
			});
		}
		
		function showCash() {
			$.ajax({
				type: "POST",
				url: "pages/merchant/queryCashMerchantAction.action",
				dataType: "json",
				success: function(json) {
					var cashver =  $('#cashver_old').val();
					var html = "<option value=''>--请选择收银台--</option>";
					$.each(json,
					function(key, value) {
						if(value.CASHVER==cashver){
							html += '<option value="' + value.CASHVER + '" selected="selected">' + value.CASHNAME + '</option>';
						}else{
							html += '<option value="' + value.CASHVER + '">' + value.CASHNAME + '</option>';
						}
					});
					$("#cashver_ins").html(html);
				}
			});
		}
		
		function showChnlname() {
			$.ajax({
				type: "POST",
				url: "pages/merchant/queryRouteAllMerchantAction.action",
				dataType: "json",
				success: function(json) {
					var routever =  $('#routver_old').val();
					var html = "<option value=''>--请选择路由版本--</option>";
					$.each(json,
					function(key, value) {
						if(value.CHNLCODE==routever){
							html += '<option value="' + value.CHNLCODE + '" selected="selected">' + value.CHNLNAME + '</option>';
						}else{
							html += '<option value="' + value.CHNLCODE + '">' + value.CHNLNAME + '</option>';
						}
					});
					$("#routver_ins").html(html);
				}
			});
		}
		function showSetlcycleAll() {
			$.ajax({
				type: "POST",
				url: "pages/merchant/querySetlcycleAllMerchantAction.action",
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					var setlcycle = $('#setltype_old').val();
					var html = "<option value=''>--请选择清算周期--</option>";
					$.each(json,
					function(key, value) {
						if(value.PARA_CODE==setlcycle){
							html += '<option value="' + value.PARA_CODE + '" selected="selected">' + value.PARA_NAME + '</option>';
						}else{
							html += '<option value="' + value.PARA_CODE + '">' + value.PARA_NAME + '</option>';
						}
					});
					$("#setlcycle_ins").html(html);
		
				}
			});
		}
		
		function showCoopInsti() {
			$.ajax({
				type: "POST",
				url: "pages/coopinsti/queryAllCoopInstiAction.action",
				dataType: "json",
				success: function(json) {
					var merCoopInstiId = $('#coopInstiId_old').val();
					var html = "<option value=''>--请选择合作机构--</option>";
					$.each(json,
					function(key, value) {
						if(value.id==merCoopInstiId){
							html += '<option value="' + value.id + '" selected="selected">' + value.instiName + '</option>';
						}else{
							html += '<option value="' + value.id + '">' + value.instiName + '</option>';
						}
						
					}) ;
					$("#coop_insti_ins").html(html);
					refreshProduct(merCoopInstiId);
					$('#coop_insti_ins').attr('disabled','disabled');
				}
			});
		}
		
		function refreshProduct(){
			var coopInstiId = $("#coop_insti_ins").val();
			if (coopInstiId != "" && coopInstiId != null) {
				showProduct(coopInstiId);
			}
			showThreeVersion();
		}
		
		function showThreeVersion() {
			var pid = $("#prdtver_ins").val();
			if (pid != "" && !pid != null) {
				queryDistType(pid);
				queryFee(pid);
				queryRiskType(pid)
			}
		}
		function queryRiskType(pid) {
			$.ajax({
				type: "POST",
				url: "pages/merchant/queryRiskTypeMerchantAction.action?vid=" + pid,
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					var riskver= $('#riskver_old').val();
					var html = "<option value=''>--请选择风控版本--</option>";
					$.each(json,
					function(key, value) {
						if(value.RISKVER==riskver){
							html += '<option value="' + value.RISKVER + '" selected="selected">' + value.RISKNAME + '</option>';
						}else{
							html += '<option value="' + value.RISKVER + '">' + value.RISKNAME + '</option>';
						}
					});
					$("#riskver").html(html);
		
				}
			});
		}
		
		function queryDistType(pid) {
			$.ajax({
				async:false,
				type: "POST",
				url: "pages/merchant/querySplitMerchantAction.action?vid=" + pid,
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					var spiltver = $('#spiltver_old').val();
					var html = "<option value=''>--请选择分润版本--</option>";
					$.each(json,
					function(key, value) {
						if(value.SPLITVER==spiltver){
							html += '<option value="' + value.SPLITVER + '" selected="selected">' + value.SPLITNAME + '</option>';
						}else{
							html += '<option value="' + value.SPLITVER + '">' + value.SPLITNAME + '</option>';
						}
					});
					$("#spiltver").html(html);
				}
			});
		}
		
		function queryFee(pid) {
			$.ajax({
				async:false,
				type: "POST",
				url: "pages/merchant/queryFeeMerchantAction.action?vid=" + pid,
				dataType: "json",
				success: function(json) {
					var feever = $('#feever_old').val();
					var html = "<option value=''>--请选择扣率版本--</option>";
					$.each(json,
					function(key, value) {
						if(value.FEEVER==feever){
							html += '<option value="' + value.FEEVER + '" selected="selected">' + value.FEENAME + '</option>';
						}else{
							html += '<option value="' + value.FEEVER + '">' + value.FEENAME + '</option>';
						}
					});
					$("#feever_ins").html(html);
				}
			});
		}
		function showSetClearType() {
			$.ajax({
				type: "POST",
				url: "pages/merchant/queryMerchClearTypeMerchantAction.action",
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					var setltype = $('#setltype_old').val();
					var html = "<option value=''>--请选择清算类型--</option>";
					$.each(json,
					function(key, value) {
						if(value.PARA_CODE==setltype){
							html += '<option value="' + value.PARA_CODE + '" selected="selected">' + value.PARA_NAME + '</option>';
						}else{
							html += '<option value="' + value.PARA_CODE + '">' + value.PARA_NAME + '</option>';
						}
					});
					$("#setltype_ins").html(html);
		
				}
			});
		}
		
		function checkIsDelegation(){
			var isDelegation = $('#isDelegation').prop("checked");
			if(isDelegation){
				$('#isDelegation').val(1);
				$('#delegation').show();
				$('#corpno').validatebox({required: true});
				$('#signatory').validatebox({required: true});
			}else{
				$('#isDelegation').val(0);
				$('#delegation').hide();
				$('#corpno').validatebox({required: false});
				$('#signatory').validatebox({required: false});
			}
		}
		
		function modifyBank(){
			$('#bank_info').show();
			$('#a_bank_info').hide();
			$('#bank_node_old').val('');
			queryBankNode();
		}
		
		function initDelegation(){
			if($('#isDelegation_old').val()=='1'){
				$('#isDelegation').attr('checked','checked');
			}
			checkIsDelegation();
		}
		
		function backToMerchIndex(){
			window.location.href= "<%=basePath%>" +'pages/merchant/showMerchantAction.action';
		}
	</script>
</html>

