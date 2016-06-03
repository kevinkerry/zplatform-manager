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
			style="height: 70px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="10%">划拨批次号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="transQuery.batchno" id="batchno" maxlength="32" />
						</td>
							<td align="right" width="10%">申请日期:</td>
						<td align="left" style="padding-left: 5px" width="30%">
							<input name="queryTransferBean.beginDate" id="beginDate" maxlength="32" />-
							<input name="queryTransferBean.endDate" id="endDate" maxlength="32" />
						</td>
				
						<td align="right" rowspan="6"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-top: 1px">
			<table id="tranBatchPanel"></table>
		</div>
		<div style="margin-top: 1px">
			<table id="bankTranBatchPanel"></table>
		</div>
	</div>
</body>

<script>
$(function() {
	$('#beginDate').datebox();
	$('#endDate').datebox();
	$('#tranBatchPanel').datagrid({
		title: '划拨批次列表',
		iconCls: 'icon-save',
		height: 300,
		singleSelect: true,
		nowrap: false,
		striped: true,
		url: 'pages/transfer/queryBatchTransferAction.action',
		remoteSort: false,
		idField: 'tid',
		collapsible: true,
		columns: [[ 
		{
			field: 'tranBatchNo',
			title: '划拨批次号',
			width: 150,
			align: 'center'
		},
		{
			field: 'totalCount',
			title: '总笔数',
			width: 90,
			align: 'center'
		},
		{
			field: 'totalAmt',
			title: '总金额（元）',
			width: 90,
			align: 'center',
			formatter : function(value, rec) {
				return value/100.00;
			}
		},
		{
			field: 'approveCount',
			title: '通过笔数',
			width: 90,
			align: 'center'
		},
		{
			field: 'approveAmt',
			title: '通过金额（元）',
			width: 90,
			align: 'center',
				formatter : function(value, rec) {
					return value==null?'':value/100.00;
				}
		},
		{
			field: 'refuseCount',
			title: '拒绝笔数',
			width: 90,
			align: 'center'
		},
		{
			field: 'refuseAmt',
			title: '拒绝金额（元）',
			width: 90,
			align: 'center',
			formatter : function(value, rec) {
				return value==null?'':value/100.00;
			}
		},
		{
			field: 'waitApproveCount',
			title: '待审笔数',
			width: 90,
			align: 'center'
		},
		{
			field: 'waitApproveAmt',
			title: '待审金额（元）',
			width: 90,
			align: 'center',
			formatter : function(value, rec) {
				return value==null?'':value/100.00;
			}
		},
		{
			field: 'busiType',
			title: '业务名称',
			width: 90,
			align: 'center',
			formatter: function(value, rec) {
				if (value == '00') {
					return '代付';
				} else if (value == '01') {
					return '提现';
				} else if (value == '02') {
					return '退款';
				} else {
					return '';
				}
			}
		},
		{
			field: 'applyTime',
			title: '申请时间',
			width: 90,
			align: 'center'
		},
		{
			field: 'approveFinishTime',
			title: '审核完成时间',
			width: 90,
			align: 'center'
		},
		{
			field: 'finishTime',
			title: '转账完成时间',
			width: 90,
			align: 'center'
		},
		{
			field: 'status',
			title: '状态',
			width: 100,
			align: 'center',
			formatter: function(value, rec) {
				if (value == '01') {
					return '未审核';
				} else if (value == '02') {
					return '部分审核通过';
				} else if (value == '00') {
					return '转账成功';
				} else if (value == '03') {
					return '全部审核通过';
				} else {
					return '';
				}
			}
		}]],
		singleSelect: true,
		selectOnCheck: true,
		checkOnSelect: false,
		pagination: true,
		rownumbers: true,
		onClickRow: function(index, row) {
			queryBankTranBatch(row.tid, row.tranBatchNo,'all');
		} 
	});
});

function search() {
	var data = {
		"queryTransferBean.batchNo": $('#batchno').val(),
		"queryTransferBean.beginDate": $("#beginDate").datebox("getValue"),
		"queryTransferBean.endDate": $("#endDate").datebox("getValue")
	}
	$('#tranBatchPanel').datagrid('load', data);
}

var statusOpen = '0';
var statusClose = '1';
var statusAll = 'all';

function queryBankTranBatch(tranBatchId, seqNo, openStatus) {
	var columns;
	var url = 'pages/transfer/queryBankTranBatchByTranBatchTransferAction.action';
	var toolbar = [{
		id: 'all',
		text: '全部',
		iconCls: openStatus==statusAll?'icon-search':'icon-blank',
		handler: function() {
			queryBankTranBatch(tranBatchId, seqNo, statusAll);
		}
	},{
		id: 'btnadd',
		text: '未关闭',
		iconCls: openStatus==statusOpen?'icon-search':'icon-blank',
		handler: function() {
			queryBankTranBatch(tranBatchId, seqNo, statusOpen);
		}
	},{
		id: 'btnadd',
		text: '已关闭',
		iconCls: openStatus==statusClose?'icon-search':'icon-blank',
		handler: function() {
			queryBankTranBatch(tranBatchId, seqNo, statusClose);
		}
	}];
	var param = {
		"queryTransferBean.tid":tranBatchId,
		"bankTranBatch.openStatus":openStatus, 
	};
	switch (openStatus) {
	case '1':
		//已关闭
		columns = [[{
			field: 'bankTranBatchNo',
			title: '转账批次号',
			width: 190,
			align: 'center'
		},
		{
			field: 'totalCount',
			title: '总笔数',
			width: 120,
			align: 'center'
		},
		{
			field: 'totalAmt',
			title: '总金额（元）',
			width: 120,
			align: 'center',
			formatter : function(value, rec) {
				return value/100.00;
			}
		},{
			field: 'successCount',
			title: '成功笔数',
			width: 120,
			align: 'center'
		},
		{
			field: 'successAmt',
			title: '成功金额（元）',
			width: 120,
			align: 'center',
			formatter : function(value, rec) {
				return value/100.00;
			}
		},{
			field: 'failCount',
			title: '失败笔数',
			width: 120,
			align: 'center'
		},
		{
			field: 'failAmt',
			title: '失败金额（元）',
			width: 120,
			align: 'center',
			formatter : function(value, rec) {
				return value/100.00;
			}
		},{
			field: 'status',
			title: '审核状态',
			width: 120,
			align: 'center',
			formatter: function(value, rec) {
				if (value == '01') {
					return '未审核';
				} else if (value == '02') {
					return '审核通过'; 
				} else if (value == '03') {
					return '审核通过';
				} else {
					return '未知';
				}
			}
		},{
			field: 'tranStatus',
			title: '转账状态',
			width: 120,
			align: 'center',
			formatter: function(value, rec) {
				if (value == '01') {
					return '等待转账';
				} else if (value == '02') {
					return '部分转账成功';
				} else if (value == '00') {
					return '全部转账成功';
				} else if (value == '03') {
					return '全部失败';
				} else {
					return '未知';
				}
			}
		}/*,
		{
			field: 'status',
			title: '操作',
			width: 100,
			align: 'center',
			formatter: function(value, rec) {
				return '<a>转账明细</a>';
			}
		}*/]];
		break;
	case '0':
		//未关闭
		columns = [[{
			field: 'bankTranBatchNo',
			title: '转账批次号',
			width: 190,
			align: 'center'
		},{
			field: 'totalCount',
			title: '总笔数',
			width: 120,
			align: 'center'
		},
		{
			field: 'totalAmt',
			title: '总金额（元）',
			width: 120,
			align: 'center',
			formatter : function(value, rec) {
				return value/100.00;
			}
		},
		{
			field: 'defaultCloseTime',
			title: '默认关闭时间',
			width: 120,
			align: 'center'
		},{
			field: 'defaultCloseTime',
			title: '最晚关闭时间',
			width: 120,
			align: 'center'
		}]];
		break;
	case 'all':
		//所有
		columns = [[{
			field: 'bankTranBatchNo',
			title: '转账批次号',
			width: 190,
			align: 'center'
		},{
			field: 'totalCount',
			title: '总笔数',
			width: 120,
			align: 'center'
		},
		{
			field: 'totalAmt',
			title: '总金额（元）',
			width: 120,
			align: 'center',
			formatter : function(value, rec) {
				return value/100.00;
			}
		},{
			field: 'successCount',
			title: '成功笔数',
			width: 120,
			align: 'center'
		},
		{
			field: 'successAmt',
			title: '成功金额（元）',
			width: 120,
			align: 'center',
			formatter : function(value, rec) {
				return value/100.00;
			}
		},{
			field: 'failCount',
			title: '失败笔数',
			width: 120,
			align: 'center'
		},
		{
			field: 'failAmt',
			title: '失败金额（元）',
			width: 120,
			align: 'center',
			formatter : function(value, rec) {
				return value/100.00;
			}
		},{
			field: 'status',
			title: '审核状态',
			width: 120,
			align: 'center',
			formatter: function(value, rec) {
				if (value == '01') {
					return '未审核';
				} else if (value == '02') {
					return '审核通过'; 
				} else if (value == '03') {
					return '审核通过';
				} else {
					return '未知';
				}
			}
		},{
			field: 'tranStatus',
			title: '转账状态',
			width: 120,
			align: 'center',
			formatter: function(value, rec) {
				if (value == '01') {
					return '等待转账';
				} else if (value == '02') {
					return '部分转账成功';
				} else if (value == '00') {
					return '全部转账成功';
				} else if (value == '03') {
					return '全部失败';
				} else {
					return '未知';
				}
			}
		},{
			field: 'defaultCloseTime',
			title: '默认关闭时间',
			width: 120,
			align: 'center'
		},{
			field: 'defaultCloseTime',
			title: '最晚关闭时间',
			width: 120,
			align: 'center'
		}]];
		break;
	};
	$('#bankTranBatchPanel').datagrid({
		title: '关联转账批次列表<font color="red">[划拨批次号：'+seqNo+']</font>',
		iconCls: 'icon-blank',
		height: 300,
		collapsible: true,
		singleSelect: true,
		nowrap: false,
		striped: true,
		url: url,
		remoteSort: false,
		idField: 'tid',
		columns: columns,
		pagination: false,
		rownumbers: true,
		toolbar: toolbar,
		queryParams:param
	});
}
</script>
</html>
