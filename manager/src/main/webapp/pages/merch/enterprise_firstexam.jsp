<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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

<body>
	<div style="margin: 5px;" id="continer">
		<div id="title" name="title" class="easyui-panel" title="商户信息"
			style="background: #fafafa;" iconCls="icon-save"
			data-options="fit:true,border:false" collapsible="false">
			<div style="padding-left: 5px; padding-right: 5px">
				<form id="merchDetaForm"
					action="pages/merchant/saveMerchDetaMerchantAction.action"
					method="post">
					<input type="hidden" id="isDelegation"
						value="${enterpriseDeta.IS_DELEGATION}" /> <input type="hidden"
						id="enterpriseApplyId" value="${enterpriseDeta.SELF_ID}" /> <input
						type="hidden" id="flag_ins" value="${flag}" />
					<table width="100%">
						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center" width="20%">企业名称<font color="red">*</font></td>
							<td width="30%">${enterpriseDeta.ENTERPRISE_NAME}</td>
							<td align="center">会员编号</td>
							<td>${enterpriseDeta.MEMBER_ID}</td>
						</tr>
						<tr>
							<td align="center">企业所在地<font color="red">*</font></td>
							<td>${enterpriseDeta.PROVINCENAME }
								${enterpriseDeta.CITYNAME} ${enterpriseDeta.STREETNAME}</td>
							<td align="center">企业行政地区代码<font color="red">*</font></td>
							<td>${enterpriseDeta.ZONE_CODE}</td>
						</tr>
						<tr>
							<td align="center">企业联系手机号<font color="red">*</font></td>
							<td>${enterpriseDeta.PHONE}</td>
							<td align="center">企业联系邮箱<font color="red">*</font></td>
							<td>${enterpriseDeta.EMAIL}</td>
						</tr>
						<tr>
							<td align="center">企业地址</td>
							<td>${enterpriseDeta.ADDRESS}</td>
							<td align="center">企业邮编</td>
							<td>${enterpriseDeta.POST_CODE}</td>
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
							<td align="center">营业执照号<font color="red">*</font></td>
							<td>${enterpriseDeta.LICENCE_NO}</td>
							<td align="center">合作机构号<font color="red">*</font></td>
							<td>${enterpriseDeta.ORG_CODE}</td>
						</tr>
						<tr>
							<td align="center">税务登记号<font color="red">*</font></td>
							<td>${enterpriseDeta.TAX_NO}</td>
							<td align="center">所属行业<font color="red">*</font></td>
							<td>${enterpriseDeta.MCC}</td>
						</tr>
						<tr>
							<td align="center">企业网址<font color="red">*</font></td>
							<td>${enterpriseDeta.WEBSITE}</td>
							<td align="center">所属行业子类别<font color="red">*</font></td>
							<td>${enterpriseDeta.MCCLIST}</td>
						</tr>
						<tr>
							<td align="center" id="psamORpass">法人姓名<font color="red">*</font></td>
							<td>${enterpriseDeta.CORPORATION}</td>
							<td align="center">法人证件号码<font color="red">*</font></td>
							<td>${enterpriseDeta.CORP_NO}</td>
						</tr>

						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>

						<tr>
							<td align="center">是否授权人办理<font color="red">*</font></td>
							<td>${enterpriseDeta.IS_DELEGATION}</td>
						</tr>
						<tr id="delegation">
							<td align="center">委托人姓名<font color="red">*</font></td>
							<td>${enterpriseDeta.SIGNATORY}</td>
							<td align="center">委托人身份证号<font color="red">*</font></td>
							<td>${enterpriseDeta.SIGN_CERT_NO}
						</tr>
						<tr>
							<td align="center">客户经理</td>
							<td>${enterpriseDeta.CUST_MGR}</td>
							<td align="center">客户经理部门</td>
							<td>${enterpriseDeta.CUST_MGR_DEPT}</td>
						</tr>
						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center">联系人姓名</td>
							<td>${enterpriseDeta.CONTACT}</td>
							<td align="center">联系人地址</td>
							<td>${enterpriseDeta.CONT_ADDRESS}</td>
						</tr>
						<tr>
							<td align="center">联系人电话</td>
							<td>${enterpriseDeta.CONT_PHONE}</td>
							<td align="center">联系人职位</td>
							<td>${enterpriseDeta.CONT_TITLE}</td>
						</tr>
						<tr>
							<td align="center">联系人邮箱</td>
							<td>${enterpriseDeta.CONT_EMAIL}</td>
							<td align="center">联系人邮编</td>
							<td>${enterpriseDeta.CONT_POST}</td>
						</tr>
						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center">营业执照</td>
							<td><span id="busilice_cert_img"></span></td>
							<td align="center">组织机构代码证</td>
							<td><span id="orgCert_cert_img"></span></td>
						</tr>
						<tr>
							<td align="center">法人身份证正面照</td>
							<td><span id="corpfileFace_cert_img"></span></td>
							<td align="center" id="psamORpass">法人身份证背面照</td>
							<td><span id="corpfileOpp_cert_img"></span></td>
						</tr>
						<tr id="delegation_pic">
							<td align="center">委托人身份证正面照</td>
							<td><span id="signfileFace_cert_img"></span></td>
							<td align="center" id="psamORpass">委托人身份证背面照</td>
							<td><span id="signfileOpp_cert_img"></span></td>
						</tr>
						<tr>
							<td align="center">税务登记证文件目录</td>
							<td><span id="taxRegCert_cert_img"></span></td>
							<td></td>
							<td></td>
						</tr>

						<s:if test="%{flag==2}">
							<tr>
								<td align="center">初核意见</td>
								<td colspan="3" align="center"><textarea rows="5"
										cols="100" style="margin: 5px" maxlength="60" id="STOPINION"></textarea>
								</td>
							</tr>
						</s:if>
						<s:if test="%{flag==3}">
							<tr>
								<td align="center">初审人</td>
								<td>${enterpriseDeta.STEXANAME}</td>
								<td align="center">初审意见</td>
								<td>${enterpriseDeta.STEXA_OPT}</td>
							</tr>
							<tr>
								<td align="center">复核意见</td>
								<td colspan="3" align="center"><textarea rows="5"
										cols="100" style="margin: 5px" maxlength="60" id="STOPINION"></textarea>
								</td>
							</tr>
						</s:if>
						<s:if test="%{flag==10}">
							<tr>
								<td align="center">初核意见</td>
								<td colspan="3" align="center"><textarea rows="5"
										cols="100" style="margin: 5px"
										value="${enterpriseDeta.STEXA_OPT}" maxlength="60"
										id="STOPINION"></textarea></td>
							</tr>
							<tr>
								<td align="center">复核意见</td>
								<td colspan="3" align="center"><textarea rows="5"
										cols="100" style="margin: 5px"
										value="${enterpriseDeta.CVLEXA_OPT}" maxlength="60"
										id="STOPINION"></textarea></td>
							</tr>
						</s:if>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div region="south" border="false"
		style="text-align: center; padding: 5px 0;">
		<s:if test="%{flag==2||flag==3}">
			<a href="javascript:enterpriseAudit('0');" id="button_ins1"
				class="easyui-linkbutton" iconCls="icon-ok">通过</a>
			<a href="javascript:enterpriseAudit('9');" id="button_ins2"
				class="easyui-linkbutton" iconCls="icon-cancel">否决</a>
			<a href="javascript:enterpriseAudit('1');" id="button_ins3"
				class="easyui-linkbutton" iconCls="icon-no">驳回</a>
		</s:if>
		<a href="javascript:history.back(-1);" class="easyui-linkbutton"
			iconCls="icon-back">返回</a>
	</div>
	<script>
	  $(function() {
			checkIsDelegation();
			//initCertUrl(); 
		});
		
		function checkIsDelegation(){
			var isDelegation = $('#isDelegation').val();
			if(isDelegation=='1'){
				$('#delegation').show();
				$('#delegation_pic').show();
			}else{
				$('#delegation').hide();
				$('#delegation_pic').hide();
			}
		}
		
		function initCertUrl(){
			$("span[id*='_cert_img']").each(function(){
				var _this = $(this);
				var id = _this.attr('id');
				var certType = id.substring(0,id.indexOf('_cert_img'));
				$.ajax({
					type: "POST",
					url: "pages/merchant/downloadEnterpriseImgUrlMerchantAction.action", 
					data: "enterpriseApplyId=" + $('#enterpriseApplyId').val()+"&certTypeCode="+certType,
					dataType: "json",
					success: function(json) {
						 if(json.status=='OK'){
							 _this.html('<a href="<%=basePath%>'+json.url+'" target="view_window" style="font-size: 12px;color:blue">点击查看</a>');
						 }else if(json.status=='notExist'){
							 $(this).html('暂无可查看文件');
						 } else{
							 _this.html('查询失败');
						 }
					}
				}); 
			});
		}
		
		function enterpriseAudit(result) {
			$("#button_ins1").linkbutton('disable');
			$("#button_ins2").linkbutton('disable');
			$("#button_ins3").linkbutton('disable');
			var enterpriseApplyId = $("#enterpriseApplyId").val();
			var stexaOpt = $("#STOPINION").val();
			var flag = $("#flag_ins").val();
			$.ajax({
				type: "POST",
				url: "pages/merchant/examEnterpriseMerchantAction.action?isAgree=" + result + "&enterpriseApplyId=" + enterpriseApplyId + "&flag=" + flag,
				data: "enterprise.stexaOpt=" + encodeURI(stexaOpt),
				dataType: "json",
				success: function(json) {
					$.each(json,
					function(key, value) {
						alert(value.INFO);
						if (value.INFO == "操作成功!") {
							history.back( - 1);
						}
					})
		
				}
			});
		}
	</script>
</body>

</html>
