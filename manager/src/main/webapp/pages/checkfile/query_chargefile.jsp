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
						<td align="right" width="10%">交易序列号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
						 id="txnseqno" maxlength="32" /></td>

						<td align="right" width="10%">充值订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							 id="payordno" maxlength="32" /></td>

						<td align="right" width="10%">商户编号:</td>
							<td align="left" style="padding-left: 5px" width="15%"><input
							 id="merchno" maxlength="32" /></td>

						<td align="right" width="10%">交易流水号:</td>
							<td align="left" style="padding-left: 5px" width="15%"><input
							 id="paytrcno" maxlength="32" /></td>
						
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
			title : '差错信息表',
			iconCls : 'icon-save',
			height : 400,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url : 'pages/checkfile/getCheckFileByQueryChargeFileAction.action',
			remoteSort : false,
			idField : 'ORGAN_ID',
			columns : [ [

			{
				field : 'txnseqno',
				title : '交易序列号',
				width : 120,
				align : 'center'
			}, {
				field : 'payordno',
				title : '支付订单号',
				width : 120,
				align : 'center'
			}, {
				field : 'memberName',
				title : '会员姓名',
				width : 120,
				align : 'center'
			}, {
				field : 'txndatetime',
				title : '交易时间',
				width : 120,
				align : 'center'}, 
				{
				field : 'busName',
				title : '业务名称',
				width : 120,
				align : 'center'
			},

			{
				field : 'amount',
				title : '金额',
				width : 120,
				align : 'center'
			}, 
			{
				field : 'merchno',
				title : '商户编号',
				width : 180,
				align : 'center'
			},
			{
				field : 'merchName',
				title : '商户名',
				width : 180,
				align : 'center'
				
			}	,	
			{
				field : 'mistatus',
				title : '差错状态',
				width : 180,
				align : 'center',
				formatter : function(value, rec) {
					if (value == '00') {
						return '待受理';
					} else if (value == '09') {
						return '拒绝受理';
					} else {
						return '';
					}
				}
				
				
				
			}
			
			
			
			
			
			
			] ],
			/* singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false, */
			pagination : true,
			rownumbers : true,
		/* 	toolbar : [ {
				id : 'btnadd',
				text : '手工充值',
				iconCls : 'icon-add',
				handler : function() {
					$("#firstTrial")[0].reset();
					$("#btn_submit").linkbutton('enable');
					$("#moneys").html("");

					showAdd();
				}
			} ] */
		});
	});

	function search() {
		var data = {
			"cfq.txnseqno" : $('#txnseqno').val(),
			"cfq.payordno" : $('#payordno').val(),
			"cfq.merchno" : $('#merchno').val(),
			"cfq.paytrcno" : $('#paytrcno').val()
		}
		$('#test').datagrid('load', data);
	}



</script>
</html>
