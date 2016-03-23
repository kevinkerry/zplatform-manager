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
						<td align="right" width="10%">转账批次号:</td>
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
			<table id="test"></table>
		</div>
		<div style="margin-top: 1px">
			<table id="test2"></table>
		</div>
	</div>


	
	
	
	<div id="ws" class="easyui-window" closed="true" title="My Window"iconCls="icon-save" style="width: 800px; height: 70px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; text-align: center">
				<form id="singleTrial"   method="post" action="pages/withdraw/queryTrialWithdraTriaAction.action" >
				<input id="withdraworderno_" type="hidden" name="auditBean.orderNo">
				<input id="falg_" type="hidden" name="auditBean.falg">
				
				</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:singleTrial(true)" id="btn_submit_">通过</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:singleTrial(false)"  id="icon-cancel_">拒绝</a>
			</div>
		</div>
	</div>

	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 70px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; text-align: center">
				<form id="firstTrial"   method="post" action="pages/withdraw/queryTrialWithdraTriaAction.action" >
				<input id="withdraworderno" type="hidden" name="auditBean.batchno">
				<input id="falg" type="hidden" name="auditBean.falg">
				
				</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:batchTrial(true)" id="btn_submit">通过</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:batchTrial(false)"  id="icon-cancel">拒绝</a>
			</div>
		</div>
	</div>
</body>

<script>
	//var width = $("#continer").width();
	$(function() {
		$('#beginDate').datebox();
		$('#endDate').datebox();
		$('#test').datagrid({
			title : '转账批次审核',
			iconCls : 'icon-save',
			height : 300,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url :'pages/transfer/queryBankBatchTransferAction.action', 
			remoteSort : false,
			idField : 'ORGAN_ID',
			collapsible:true,
			columns : [ [
					{field : 'ck',checkbox : true},
					{field : 'bankTranBatchNo',title : '转账批次号',width : 90,align : 'center'},
					{field : 'totalCount',title : '总笔数',width : 90,align : 'center'},
					{field : 'totalAmt',title : '总金额',width : 90,align : 'center',
						formatter:function(value,rec){
							return value/100.00;
					}},
					{field : 'successCount',title : '通过笔数',width : 90,align : 'center'},
					{field : 'successAmt',title : '通过金额',width : 90,align : 'center',
						formatter:function(value,rec){
							return value/100.00;
					}},
					{field : 'failCount',title : '拒绝笔数',width : 90,align : 'center'},
					{field : 'failAmt',title : '拒绝金额',width : 90,align : 'center',
						formatter:function(value,rec){
							return value/100.00;
					}},
					{field : 'applyTime',title : '申请时间',width : 90,align : 'center'},
					{field : 'status',title : '审核状态',width : 100,align : 'center',
						formatter : function(value, rec) {
										if (value == '01') {
											return '未审核';
										} else if (value == '02') {
											return '审核通过';
										} else if (value == '00') {
											return '转账成功';
										} else if (value == '03') {
											return '审核拒绝';
										} else {
											return '';
										}
									} 
					},
					{field : 'tranStatus',title : '转账状态',width : 100,align : 'center',
						formatter : function(value, rec) {
							//01:等待转账;02:部分转账成功;03:全部转账成功;04:全部失败
							// 05:正在转账
										if (value == '01') {
											return '等待转账';
										} else if (value == '02') {
											return '部分转账成功';
										} else if (value == '04') {
											return '全部失败';
										} else if (value == '03') {
											return '全部转账成功';
										} else if (value == '05') {
											return '正在转账';
										} else {
											return '';
										}
									} 
					}
					
					
					] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false,
			pagination : true,
			rownumbers : true,
			onClickRow : function(index,row){
				var data = {
						"queryTransferBean.tid" : row.tid
					   }
					$('#test2').datagrid('load', data);
				$($('#test2').datagrid('getPanel')).panel('expand',true);
			},
			toolbar : [ {
				id : 'btnadd',
				text : '批次审核',
				iconCls : 'icon-ok',
				handler : function() {
					
					
					var check= $('#test' ).datagrid('getChecked');
					if(check.length!=0){
						var myArray="";
	                    for (var i=0;i<check.length;i++){
	                  	   myArray+=check[i].tid+"|"
	                    } 
						$("#firstTrial")[0].reset();
						$("#btn_submit").linkbutton('enable');
						$("#icon-cancel").linkbutton('enable');
						$("#withdraworderno").val(myArray);
						showAdd();
					}else{
						$.messager.alert('提示',"请选择数据"); 
						
					}
				}
			},{
				id : 'btnadd',
				text : '关闭批次',
				iconCls : 'icon-ok',
				handler : function() {
					
					
					$.messager.confirm('提示', '是否关闭所选批次', function(r){
		                if (r){
		                	var check= $('#test' ).datagrid( 'getChecked');
							if(check.length!=0){
								var myArray="";
			                    for (var i=0;i<check.length;i++){
			                  	   myArray+=check[i].tid+"|";
			                    }
								closeTransferBatch(myArray);
							}else{
								$.messager.alert('提示',"请选择数据"); 
								
							}
		                }
		            });
				}
			},{
				id : 'btnadd',
				text : '关闭状态',
				iconCls : 'icon-search',
				handler : function() {
					var data = {
							"queryTransferBean.batchNo" : $('#batchno').val(),
							"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
							"queryTransferBean.endDate":$("#endDate").datebox("getValue"),
							"queryTransferBean.openStatus":"1"
						   }
						$('#test').datagrid('load', data);
				}
			},{
				id : 'btnadd',
				text : '开放状态',
				iconCls : 'icon-search',
				handler : function() {
					var data = {
							"queryTransferBean.batchNo" : $('#batchno').val(),
							"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
							"queryTransferBean.endDate":$("#endDate").datebox("getValue"),
							"queryTransferBean.openStatus":"0"
						   }
						$('#test').datagrid('load', data);
				}
			},{
				id : 'btnadd',
				text : '待审核',
				iconCls : 'icon-search',
				handler : function() {
						var data = {
							"queryTransferBean.batchNo" : $('#batchno').val(),
							"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
							"queryTransferBean.endDate":$("#endDate").datebox("getValue"),
							"queryTransferBean.openStatus":"1",
						    "queryTransferBean.status":"01"
						   }
						$('#test').datagrid('load', data);
				}
			}
			,{
				id : 'btnadd',
				text : '正在转账',
				iconCls : 'icon-search',
				handler : function() {
						var data = {
							"queryTransferBean.batchNo" : $('#batchno').val(),
							"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
							"queryTransferBean.endDate":$("#endDate").datebox("getValue"),
							"queryTransferBean.openStatus":"1",
						    "queryTransferBean.tranStatus":"05"
						   }
						$('#test').datagrid('load', data);
				}
			},{
				id : 'btnadd',
				text : '转账成功',
				iconCls : 'icon-search',
				handler : function() {
						var data = {
							"queryTransferBean.batchNo" : $('#batchno').val(),
							"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
							"queryTransferBean.endDate":$("#endDate").datebox("getValue"),
							"queryTransferBean.openStatus":"1",
						    "queryTransferBean.tranStatus":"03"
						   }
						$('#test').datagrid('load', data);
				}
			},{
				id : 'btnadd',
				text : '转账失败',
				iconCls : 'icon-search',
				handler : function() {
						var data = {
							"queryTransferBean.batchNo" : $('#batchno').val(),
							"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
							"queryTransferBean.endDate":$("#endDate").datebox("getValue"),
							"queryTransferBean.openStatus":"1",
						    "queryTransferBean.tranStatus":"04"
						   }
						$('#test').datagrid('load', data);
				}
			},{
				id : 'btnadd',
				text : '转账部分成功',
				iconCls : 'icon-search',
				handler : function() {
						var data = {
							"queryTransferBean.batchNo" : $('#batchno').val(),
							"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
							"queryTransferBean.endDate":$("#endDate").datebox("getValue"),
							"queryTransferBean.openStatus":"1",
						    "queryTransferBean.tranStatus":"02"
						   }
						$('#test').datagrid('load', data);
				}
			}
			]
						});
		 
		
		$('#test2').datagrid(
				{
					title : '转账明细审核',
					iconCls : 'icon-save',
					height : 300,
					collapsible:true,
					singleSelect : true,
					nowrap : false,
					striped : true,
					url :'pages/transfer/queryBankDataTransferAction.action', 
					remoteSort : false,
					idField : 'ORGAN_ID',
					columns : [ [
							{field : 'bankTranDataSeqNo',title : '转账流水号',width : 190,align : 'center'},
							{field : 'accType',title : '账户类型',width : 90,align : 'center',
								formatter : function(value, rec) {
												if (value == '1') {
													return '对公账户';
												} else if (value == '0') {
													return '对私账户';
												} 
											} 
							},
							{field : 'accNo',title : '账号',width : 120,align : 'center'},
							{field : 'accName',title : '户名',width : 120,align : 'center'},
							{field : 'accBankNo',title : '支付行号',width : 120,align : 'center',},
							{field : 'accBankName',title : '开户行名称',width : 120,align : 'center'},
							
							{field : 'tranAmt',title : '金额(元)',width : 90,align : 'center',
								formatter:function(value,rec){
								return value/100.00;
							}},
							
							{field : 'applyTime',title : '创建时间',width : 120,align : 'center'},
							{field : 'status',title : '状态',width : 120,align : 'center',
								formatter : function(value, rec) {
												if (value == '01') {
													return '未审核';
												} else if (value == '02') {
													return '等待转账';
												} else if (value == '03') {
													return '正在转账';
												} else if (value == '00') {
													return '转账成功';
												} else {
													return '';
												}
											} 
							}
							
							
							] ],
					singleSelect : false,
					selectOnCheck : true,
					checkOnSelect : false,
					pagination : true,
					rownumbers : true
					
				});
		$($('#test2').datagrid('getPanel')).panel('collapse',false);
	/* 	$('#test').datagrid('onClickRow', function(index,row){
			alert(index);
		});
 */	});

	function search() {
		//alert($("#beginDate").datebox("getValue"));
		var data = {
				"queryTransferBean.batchNo" : $('#batchno').val(),
				"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
				"queryTransferBean.endDate":$("#endDate").datebox("getValue")
			   }
			$('#test').datagrid('load', data);
	}
	
	function closeTransferBatch(tid){
		$.ajax({
			type: "POST",
			url: "pages/transfer/closeBankBatchTransferAction.action",
			data:"auditBean.batchno="+tid,
			dataType: "text",
			success: function(json) {
				$.messager.alert('提示',json); 
				var data = {
						"queryTransferBean.batchNo" : $('#batchno').val(),
						"queryTransferBean.beginDate":$("#beginDate").datebox("getValue"),
						"queryTransferBean.endDate":$("#endDate").datebox("getValue"),
						"queryTransferBean.openStatus":"0"
					   }
					$('#test').datagrid('load', data);
			}
		});
	}

	function showAdd() {
	
		$('#w').window({
			title : '批量审核',
			top : 200,
			width : 300,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height : 80,
			left:400
		});
	}

	function showAdds() {
		$('#ws').window({
			title : '明细审核',
			top : 200,
			width : 300,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height : 80,
			left:400
		});
	}
	
	
	function closeAdd() {
		$('#ws').window('close');
		$('#wss').window('close');
		$('#w').window('close');
	}
	
	
	
	function batchTrial(falg){
		if(falg==true){
			$("#firstTrial").attr("action","pages/transfer/batchBankTrialTransferAction.action");
			$("#falg").val("true");
		}else{
			$("#firstTrial").attr("action","pages/transfer/batchBankTrialTransferAction.action");
			$("#falg").val("false");
		}
		$('#firstTrial').form('submit', {  
		    onSubmit: function(){  
			    if($('#firstTrial').form('validate')){
			    	$('#btn_submit').linkbutton('disable');
					$("#icon-cancel").linkbutton('disable');		
			    	return 	true;
				}
		        return false;   
		    },   
		    success:function(data){  
		    	$.messager.alert('提示',data); 
    			search();
	    		closeAdd();
	    		$($('#test2').datagrid('getPanel')).panel('collapse',false);
		    }  
		});   
	}
	
	
</script>
</html>
