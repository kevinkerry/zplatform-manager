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
						<td align="right" width="10%">调账订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="rq.revisionno" id="revisionno" maxlength="32" /></td>

						<td align="right" width="10%">充值订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="rq.txnslogid" id="txnslogid" maxlength="32" /></td>


						<td align="right" width="10%">业务代码:</td>
						<td colspan="1"><select name="rq.busicode"
							class="easyui-validatebox validatebox-text" id="busicode">
								<option value="">请选择</option>
								<option value="10000001">消费</option>
								<option value="10000002">消费-账户</option>
								<option value="20000001">充值</option>
								<option value="30000001">提现</option>
								<option value="40000001">退款</option>
								<option value="60000001">保障金</option>
								<option value="70000001">代付</option>
								<option value="80000001">实名认证</option>
								<option value="90000001">手工充值</option>
								
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
							<td align="center" width="35%">订单号:</td>
							<td align="center" style="padding-left: 5px" width="65%"><input
								name="txnsLogNo" id="txnsLogNo" type="text"
								class="easyui-validatebox" maxlength="32" required="true" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:firstTrial()" id="btn_submit" onclick="">提交</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" id="icon-cancel" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>

</body>

<script>
	var width = $("#continer").width();
	$(function() {
		$('#test').datagrid({
			title : '手工调账信息',
			iconCls : 'icon-save',
			height : 400,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url : 'pages/revision/queryRevisionRevisionAction.action',
			remoteSort : false,
			idField : 'ORGAN_ID',
			columns : [ [

			{
				field : 'revisionno',
				title : '调账订单号',
				width : 120,
				align : 'center'
			}, {
				field : 'txnslogid',
				title : '关联订单',
				width : 120,
				align : 'center'
			}, {
				field : 'busitype',
				title : '业务类型',
				width : 120,
				align : 'center',
				formatter : function(value, rec) {
					if (value == '1000') {
						return '消费';
					} else if (value == '2000') {
						return '充值';
					}else if (value == '3000') {
						return '提现';
					}
					else if (value == '4000') {
						return '退款';
					}
					else if (value == '6000') {
						return '保障金';
					}
					else if (value == '7000') {
						return '代付';
					}
					else if (value == '8000') {
						return '实名认证';
					}
					else if (value == '9000') {
						return '手工充值';
					}
					
					
					else {
						return '';
					}
				}
			}, {
				field : 'inuser',
				title : '写入人',
				width : 120,
				align : 'center'
			}, {
				field : 'intime',
				title : '写入时间',
				width : 180,
				align : 'center'
			} ] ],
	
			pagination : true,
			rownumbers : true,
			toolbar : [ {
				id : 'btnadd',
				text : '手工调账',
				iconCls : 'icon-add',
				handler : function() {
					$("#firstTrial")[0].reset();
					$("#btn_submit").linkbutton('enable');
					$("#icon-cancel").linkbutton('enable');

					showAdd();
				}
			} ]
		});
	});

	function search() {

		var data = {
			"rq.revisionno" : $('#revisionno').val(),
			"rq.txnslogid" : $('#txnslogid').val(),
			"rq.busicode" : $('#busicode').val()
		}
		$('#test').datagrid('load', data);
	}


	function closeAdd() {

		$('#w').window('close');
	}

	


	function firstTrial() {
		$("#firstTrial").attr("action",
				"pages/revision/saveRevisionRevisionAction.action");
		$('#firstTrial').form(
				'submit',
				{
					onSubmit : function() {
						if ($('#firstTrial').form('validate')) {
							$('#btn_submit').linkbutton('disable');
							$("#icon-cancel").linkbutton('disable');
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
							$("#icon-cancel").linkbutton('enable');
						}

					}
				});
	}

	function showAdd() {

		$('#w').window({
			title : '手工调账',
			top : 100,
			width :300,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height :200
		});

	}
</script>
</html>
