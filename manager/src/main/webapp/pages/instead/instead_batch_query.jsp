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
						<td align="right" width="10%">代付批次号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="insteadPayBatchQuery.batchNo" id="batchno" maxlength="32" />
						</td>
							<td align="right" width="10%">申请日期:</td>
						<td align="left" style="padding-left: 5px" width="30%">
							<input name="insteadPayBatchQuery.beginDate" id="beginDate" maxlength="32" />-
							<input name="insteadPayBatchQuery.endDate" id="endDate" maxlength="32" />
						</td>
				
						<td align="right" rowspan="6"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
				<input type="hidden" name="insteadPayBatchQuery.status" id="queryStatus" maxlength="32" />
				<input type="hidden"  id="currentId" maxlength="32" />
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="insteadBatchPanel"></table>
		</div>
		<div style="margin-top: 5px">
			<table id="tranBatchPanel"></table>
		</div>
	</div>
</body>
<script>
	$(function() {
		$('#beginDate').datebox();
		$('#endDate').datebox();
		//$("#withdraworcheckbox").unbind();
		$('#insteadBatchPanel').datagrid({
			title : '代付批次列表',
			iconCls : 'icon-save',
			height : 300,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url :'pages/instead/queryInsteadBatchInsteadAction.action', 
			remoteSort : false,
			idField : 'ORGAN_ID',
			collapsible:true,
			columns : [ [
					{field : 'id',hidden:true},
					
					{field : 'insteadPayBatchSeqNo',title : '代付批次号',width : 150,align : 'center'},
					{field : 'type',title : '代付类型',width : 70,align : 'center',
						formatter : function(value, rec) {
										if (value == '01') {
											return '文件导入';
										} else if (value == '00') {
										return '接口';
									} 
						} 
					},
					{field : 'totalQty',title : '总笔数',width : 70,align : 'center'},
					{field : 'totalAmt',title : '总金额（元）',width : 70,align : 'center',
						formatter : function(value, rec) {
							return fen2Yuan(value);
						}},
					{field : 'approveCount',title : '审核通过<br/>笔数',width : 70,align : 'center'},
					{field : 'approveAmt',title : '审核通过<br/>金额（元）',width : 60,align : 'center',
						formatter : function(value, rec) {
							return fen2Yuan(value);
						}},
					{field : 'refuseCount',title : '审核拒绝<br/>笔数',width : 60,align : 'center'},
					{field : 'refuseAmt',title : '审核拒绝<br/>金额（元）',width : 60,align : 'center',
						formatter : function(value, rec) {
							return fen2Yuan(value);
						}},
					{field : 'unapproveCount',title : '未审核<br/>笔数',width : 70,align : 'center'},
					{field : 'unapproveAmt',title : '未审核<br/>金额（元）',width : 70,align : 'center',
						formatter : function(value, rec) {
							return fen2Yuan(value);
						}},
					{field : 'status',title : '状态',width : 120,align : 'center',
						formatter : function(value, rec) {
										if (value == '01') {
											return '未审核';
										} else if (value == '02') {
											return '部分审核完毕';
										} else if (value == '03') {
											return '全部审核完毕';
										} else if (value == '00') {
											return '全部处理完毕';
										} else {
											return '';
										}
									} 
					}
					] ],
			singleSelect : true,
			selectOnCheck : true,
			checkOnSelect : false,
			pagination : true,
			rownumbers : true,
			onClickRow : function(index,row){
				queryTranBatch(row.id, row.insteadPayBatchSeqNo, '00,01,02,03');
			}});
	});
	
	var statusAll = '00,01,02,03';
	var statusFinished = '00,02,03';
	var statusInit = '01';
	
	function queryTranBatch(id, insteadPayBatchSeqNo, status){
		var columns;
		var url = 'pages/instead/queryTranBatchByInsteadPayBatchInsteadAction.action';
		var toolBar = [{
			id : 'all',
			text : '全部',
			iconCls: status == statusAll?'icon-ok':'icon-blank',
			handler : function() {
				queryTranBatch(id, insteadPayBatchSeqNo, statusAll);
			}
		}, {
			id : 'unapproved',
			text : '未审核',
			iconCls: status == statusInit?'icon-ok':'icon-blank',
			handler : function() {
				queryTranBatch(id, insteadPayBatchSeqNo, statusInit);
			}
		},{
			id : 'approved',
			text : '已审核',
			iconCls: status ==  statusFinished?'icon-ok':'icon-blank',
			handler : function() {
				queryTranBatch(id, insteadPayBatchSeqNo, statusFinished);
			}
		}];
		var param = {
				"insteadPayBatchQuery.id" : id,
				"insteadPayBatchQuery.status" : status
		};
		columns=[[ 
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
							return fen2Yuan(value);
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
							return fen2Yuan(value);
						}
			   		},
			   		{
			   			field: 'unapproveCount',
			   			title: '拒绝笔数',
			   			width: 90,
			   			align: 'center'
			   		},
			   		{
			   			field: 'unapproveAmt',
			   			title: '拒绝金额（元）',
			   			width: 90,
			   			align: 'center',
						formatter : function(value, rec) {
							return fen2Yuan(value);
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
							return fen2Yuan(value);
						}
			   		},
			   		{
			   			field: 'busitype',
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
			   					return '处理完成';
			   				} else if (value == '03') {
			   					return '全部审核通过';
			   				} else {
			   					return '';
			   				}
			   			}
			   	}]];
		$('#tranBatchPanel').datagrid({
			title : '划拨批次列表<font color="red">[代付批次号：'+insteadPayBatchSeqNo+']</font>',
			iconCls: 'icon-save',
			height : 300,
			collapsible:true,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url : url,
			remoteSort : false,
			idField : 'id',
			columns: columns,
			pagination: false,
			rownumbers: true,
			toolbar: toolBar,
			queryParams : param
		});
	}
	
	function search() {
		var data = {
			"insteadPayBatchQuery.batchNo": $('#batchno').val(),
			"insteadPayBatchQuery.beginDate": $("#beginDate").datebox("getValue"),
			"insteadPayBatchQuery.endDate": $("#endDate").datebox("getValue")
		}
		$('#insteadBatchPanel').datagrid('load', data);
	}
	
	function fen2Yuan( num ) {
	       if ( typeof num !== "number" || isNaN( num ) ) return null;
	       return ( num / 100 ).toFixed( 2 );
	  }
</script>
</html>