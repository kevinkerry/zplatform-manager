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

						<td align="right" width="10%">充值订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="chargeQuery.chargeno" id="chargeno" maxlength="32" /></td>

						<td align="right" width="10%">充值类型:</td>
						<td colspan="1"><select name="chargeQuery.chargeType"
							class="easyui-validatebox validatebox-text" id="chargeType">
								<option value="">请选择</option>
								<option value="01">个人</option>
								<option value="02">商户</option>

						</select></td>

						<td align="right" width="10%">充值状态:</td>
						<td colspan="1"><select name="chargeQuery.status"
							class="easyui-validatebox validatebox-text" id="status">
								<option value="">请选择</option>
								<option value="01">待初审</option>
								<option value="09">初审未过</option>
								<option value="00">充值成功</option>
						</select></td>
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
				<form id="firstTrial" method="post">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr align="center">
							<td align="right" width="40%">会员号:</td>
							<td align="left" style="padding-left: 5px" width="45%"><input
								name="cb.memberid" id="memberids" type="text"
								class="easyui-validatebox" maxlength="32" required="true" /></td>
						</tr>
						<tr align="center">
							<td align="right" width="45%">充值金额:</td>
							<td align="left"><input id="amount" placeholder="请输入充值金额"
								name="cb.amount" required="true" type="text"
								class="easyui-validatebox" maxlength="20" onkeyup="money()"
								required="true" />元 &nbsp;&nbsp;&nbsp;&nbsp;人民币:<span
								id="moneys"></span></td>
						</tr>
						<tr align="center">
							<td align="right" width="45%">账户:</td>
							<td align="left"><select name="cb.usage"
								class="easyui-validatebox validatebox-text" id="usage"
								required="true">
									<option value="101">资金账户</option>

							</select></td>
						</tr>

					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:firstTrial()" id="btn_submit" onclick="">提交</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" id="btn_submit" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>

</body>

<script>
	var width = $("#continer").width();
	$(function() {
		$("#withdraworcheckbox").unbind();
		$('#test').datagrid({
			title : '充值信息表',
			iconCls : 'icon-save',
			height : 400,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url : 'pages/charge/queryChargeChargeAction.action',
			remoteSort : false,
			idField : 'ORGAN_ID',
			columns : [ [

			{
				field : 'chargeno',
				title : '充值订单号',
				width : 120,
				align : 'center'
			}, {
				field : 'memberid',
				title : '会员号',
				width : 120,
				align : 'center'
			}, {
				field : 'memberName',
				title : '会员姓名',
				width : 120,
				align : 'center'
			}, {
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
			}, {
				field : 'amount',
				title : '充值金额(元)',
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
			}, {
				field : 'intime',
				title : '写入时间',
				width : 180,
				align : 'center'
			} ] ],
			/* singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false, */
			pagination : true,
			rownumbers : true,
			toolbar : [ {
				id : 'btnadd',
				text : '手工充值',
				iconCls : 'icon-add',
				handler : function() {
					$("#firstTrial")[0].reset();
					$("#btn_submit").linkbutton('enable');
					$("#moneys").html("");

					showAdd();
				}
			} ]
		});
	});

	function search() {

		var data = {
			"chargeQuery.memberId" : $('#memberId').val(),
			"chargeQuery.chargeType" : $('#chargeType').val(),
			"chargeQuery.chargeno" : $('#chargeno').val(),
			"chargeQuery.status" : $('#status').val()
		}
		$('#test').datagrid('load', data);
	}

	function showAdd() {

		$('#w').window({
			title : '批量审核',
			top : 100,
			width : 800,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height : 240
		});
	}
	function closeAdd() {

		$('#w').window('close');
	}

	function money() {
		var money = $("#amount").val();

		var masg;
		var isok = true;
		if (money != null && isMoney(money)) {

			masg = toChineseCash(money);
			$("#moneys").css("color", "black");
		} else {
			masg = "请输入合法的金额";
			$("#moneys").css("color", "red");

			isok = false;
		}
		$("#moneys").html(masg);
		return isok;

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

	function firstTrial() {
		$("#firstTrial").attr("action",
				"pages/charge/saveChargeChargeAction.action");

		$('#firstTrial').form(
				'submit',
				{
					onSubmit : function() {
						if ($('#firstTrial').form('validate')
								&& isMoney($("#amount").val())) {
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

		$('#ws').window({
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

	}
</script>
</html>
