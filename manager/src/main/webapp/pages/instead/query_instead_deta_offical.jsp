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
			style="height: 120px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="10%">代付流水号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.insteadPayDataSeqNo" id="insteadPayDataSeqNo" maxlength="32" />
						</td>
						<td align="right" width="10%">商户订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.orderId" id="orderId" maxlength="32" />
						</td>
						</tr>
						<tr>
						<td align="right" width="10%">商户代付批次号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.batchNo" id="batchno" maxlength="32" />
						</td>
						<td align="right" rowspan="6"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="insteadPayDetailPanel"></table>
		</div> 
	</div>
</body>

<script>
	$(function() {
		$('#beginDate').datebox();
		$('#endDate').datebox();
		$('#insteadPayDetailPanel').datagrid({
			title: '划拨流水列表',
			iconCls: 'icon-save',
			height: 600,
			singleSelect: true,
			nowrap: false,
			striped: true,
			url: 'pages/instead/queryInsteadDetailInsteadAction.action',
			remoteSort: false,
			idField: 'ORGAN_ID',
			collapsible: true,
			columns: [[ 
			{
				field: 'id',
				hidden: true
			},
			{
				field: 'insteadPayDataSeqNo',
				title: '代付流水号',
				width: 200,
				align: 'center'
			},
			{
				field: 'orderId',
				title: '商户订单号',
				width: 140,
				align: 'center'
			},
			{
				field: 'accNo',
				title: '账户号',
				width: 140,
				align: 'center'
			},
			{
				field: 'accName',
				title: '账户名',
				width: 140,
				align: 'center'
			},
			{
				field: 'amt',
				title: '转账金额',
				width: 120,
				align: 'center',
				formatter : function(value, rec) {
					return value;
				}
			},
			{
				field: 'status',
				title: '状态',
				width: 120,
				align: 'center',
				formatter : function(value, rec) {
					switch(value){
						case '01':return '等待审核';
						case '09':return '审核拒绝';
						case '00':return '成功';
						case '39':return '划拨失败';
						case '29':return '审核拒绝';
						case '19':return '审核拒绝';
						default:return '划拨中';
					}
				}
			}]],
			singleSelect: false,
			selectOnCheck: true,
			checkOnSelect: false,
			pagination: true,
			rownumbers: true
		});
	
	});
	function search() {
		var data = {
			"instead.insteadPayDataSeqNo": $('#insteadPayDataSeqNo').val(),
			"instead.orderId": $("#orderId").val(),
			"instead.batchNo": $("#batchNo").val(),
		}
		$('#insteadPayDetailPanel').datagrid('load', data);
	}
</script>
</html>
