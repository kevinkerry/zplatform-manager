<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../../top.jsp"></jsp:include>
<body>
	<style type="text/css">
table tr td {
	height: 25px
}

table tr td input {
	height: 15px
}

table tr td select {
	height: 20px
}
</style>
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 140px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="10%">会员号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="chargeQuery.memberId" id="memberId" maxlength="32" /></td>

						<td align="right" width="10%">充值类型:</td>
						<td colspan="1"><select name="chargeQuery.chargeType"
							class="easyui-validatebox validatebox-text" id="chargeType">
								<option value="">请选择</option>
								<option value="0">个人</option>
								<option value="1">商户</option>

						</select></td>

						<td align="right" width="10%">充值订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="chargeQuery.chargeno" id="chargeno" maxlength="32" /></td>

					</tr>

					<tr>
						<td align="right" rowspan="6"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>

					</tr>

				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>

	</div>

	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
		<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"  border="solid">
					<tr>
						<td whdth="40px" align="center">充值订单号</td>
						<td whdth="40px" align="center" id="tchargeno"></td>
						<td whdth="40px" align="center">会员号</td>
						<td whdth="40px" align="center" id="tmemberid"></td>
					</tr>
					<tr>
						<td whdth="40px" align="center">会员姓名</td>
						<td whdth="40px" align="center" id="memberName"></td>
						<td whdth="40px" align="center">写入时间</td>
						<td whdth="40px" align="center" id="tintime"></td>
					</tr>
					<tr>
						<td whdth="40px" align="center">充值类型</td>
						<td whdth="40px" align="center" id="tchargetype"></td>
						<td whdth="40px" align="center">金额</td>
						<td whdth="40px" align="center" id="tamount"></td>
					</tr>
				</table>
			<br>
				<form id="firstTrial" method="post">
					<input id="withdraworderno" type="hidden"
						name="trial.orderNo"> <input id="falg"
						type="hidden" name="trial.falg">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="center">初审意见:</td>
							<td><textarea rows="5" cols="80" name="trial.stexaopt">
				</textarea></td>
						</tr>
					</table>
				</form>

			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:firstTrial(true)" id="btn_submit" onclick="">通过</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" id="btn_submit"
					onclick="firstTrial(false)">拒绝</a>
			</div>
		</div>
	</div>

</body>

<script>
	var width = $("#continer").width();
	$(function() {
		$("#withdraworcheckbox").unbind();
		$('#test')
				.datagrid(
						{
							title : '充值信息表',
							iconCls : 'icon-save',
							height : 400,
							singleSelect : true,
							nowrap : false,
							striped : true,
							url : 'pages/charge/queryChargeChargeAction.action?falg=first',
							remoteSort : false,
							idField : 'ORGAN_ID',
							columns : [ [

									{
										field : 'chargeno',
										title : '充值订单号',
										width : 120,
										align : 'center'
									},
									{
										field : 'memberid',
										title : '会员号',
										width : 120,
										align : 'center'
									},
									{
										field : 'memberName',
										title : '会员姓名',
										width : 120,
										align : 'center'
									},
									{
										field : 'chargetype',
										title : '充值类型',
										width : 120,
										align : 'center',
										formatter : function(value, rec) {
											if (value == '02') {
												return '商户';
											} else if (value == '01') {
												return '个人';
											} else {
												return '';
											}
										}
									},
									{
										field : 'amount',
										title : '充值金额',
										width : 120,
										align : 'center'
									},

									{
										field : 'status',
										title : '状态',
										width : 120,
										align : 'center',
										formatter : function(value, rec) {

											if (value == '01') {
												return '待初审';
											} else if (value == '09') {
												return '初审未过';
											} else if (value == '11') {
												return '待复审';
											} else if (value == '19') {
												return '复审未过';
											} else if (value == '21') {
												return '等待批处理';
											} else if (value == '29') {
												return '批处理失败';
											} else if (value == '00') {
												return '充值成功';
											} else if (value == '39') {
												return '自行终止';
											} else {
												return '';
											}
										}
									},
									{
										field : 'intime',
										title : '写入时间',
										width : 120,
										align : 'center'
									},

									{
										field : 'txnseqno-',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, rec) {
											if (rec.chargeno != null) {
												return '<a href="javascript:getCharge(\''
														+ rec.chargeno
														+ '\')" style="color:blue;margin-left:10px">审核</a>';
											} else {
												return '';
											}
										}
									} ] ],
						});
	});

	function search() {

		var data = {
			"chargeQuery.memberId" : $('#memberId').val(),
			"chargeQuery.chargeno":$('#chargeno').val(),
			"chargeQuery.chargeType" : $('#chargeType').val(),
		}
		$('#test').datagrid('load', data);
	}

	function closeAdd() {

		$('#w').window('close');
	}

	/* function a(falg){
		if(falg==true){
			
			$("#firstTrial").attr("action",
					"pages/withdraw/queryTrialWithdraTriaAction.action");
			}else{
				$("#firstTrial").attr("action",
				"pages/withdraw/queryTrialWithdraTriaAction.action");
			}
		
	} */

	function firstTrial(falg) {
		$("#firstTrial").attr("action",
				"pages/charge/firstChargeChargeAction.action");
		if (falg == true) {
			$("#falg").val(true);
		} else {
			$("#falg").val(false);
		}

		$('#firstTrial').form('submit', {
			onSubmit : function() {
				if($('#tamount').val().length>10){
					$.messager.alert('提示', '超过最大金额');
				}
				if ($('#firstTrial').form('validate')) {
					$('#btn_submit').linkbutton('disable');
					return true;
				}
				return false;
			},
			success : function(data) {
				var json = eval('(' + data + ')');
				if (json.falg == true) {
					$.messager.alert('提示', json.messg);
					search();
					closeAdd();
				} else {
					$.messager.alert('提示', json.messg);
					$("#btn_submit").linkbutton('enable');
				}
			}
		});
	}

	function showAdds() {

		$('#w').window({
			title : '单笔审核',
			top : 100,
			width : 800,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height : 500
		});
		
		$("#firstTrial")[0].reset();
		$("#btn_submit").linkbutton('enable');
	}

	function getCharge(chargeno) {

		var isok = false;
		$.ajax({
			type : "POST",
			url : "pages/charge/queryChargeChargeAction.action?falg=first",
			data : {
				"chargeQuery.chargeno" : chargeno
			},
			dataType : "json",
			async : false,
			success : function(data) {

				var json = data.rows[0];
				if (json == '' || json == undefined || json == null) {

					$.messager.alert('提示', "数据不正确，请刷新后重试");
				} else {
					$("#tchargeno").html(json.chargeno);
					$("#tmemberid").html(json.memberid);
					$("#memberName").html(json.memberName);
					$("#tintime").html(json.intime);
					if (json.chargetype == '01') {
						$("#tchargetype").html('个人')
					} else if (json.chargetype == '02') {
						$("#tchargetype").html('商户')
					}
					$("#tamount").html(json.amount + "(元)");
					$("#withdraworderno").val(chargeno);

					isok = true;

				}
			}
		})
		if (isok == true) {
			showAdds();
		} else {
			$.messager.alert('提示', "数据不存在或者内部错误");
		}

	}
</script>
</html>
