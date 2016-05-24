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
				<input type="hidden"  id="currentBatchId" maxlength="32" />
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
		<div style="margin-top: 5px">
			<table id="test2"></table>
		</div>
	</div>

	<div id="ws" class="easyui-window" closed="true" title="My Window"iconCls="icon-save" style="width: 800px; height: 70px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; text-align: center">
				<form id="detailAuditForm"   method="post" action="pages/withdraw/queryTrialWithdraTriaAction.action" >
				<input id="auditDataId_" type="hidden" name="auditDataBean.detailId">
				<input id="flag_" type="hidden" name="auditDataBean.pass">
				
				</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:detailAudit(true)" id="btn_submit_">通过</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:detailAudit(false)"  id="icon-cancel_">拒绝</a>
			</div>
		</div>
	</div>

	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 70px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; text-align: center">
				<form id="auditForm"   method="post" action="#" >
				<input id="auditDataId" type="hidden" name="auditDataBean.batchId">
				<input id="flag" type="hidden" name="auditDataBean.pass">
				
				</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:batchAudit(true)" id="btn_submit">通过</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:batchAudit(false)"  id="icon-cancel">拒绝</a>
			</div>
		</div>
	</div>
</body>

<script>
	$(function() {
		$('#beginDate').datebox();
		$('#endDate').datebox();
		//$("#withdraworcheckbox").unbind();
		$('#test').datagrid({
			title : '代付批次审核',
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
					{field : 'ck',checkbox : true},
					{field : 'id',hidden:true},
					{field : 'insteadPayBatchSeqNo',title : '代付批次号',width : 150,align : 'center'},
					{field : 'batchNo',title : '商户批次号',width : 120,align : 'center'},
					{field : 'type',title : '代付类型',width : 70,align : 'center',
						formatter : function(value, rec) {
										if (value == '01') {
											return '文件导入';
										} else if (value == '00') {
										return '接口';
									} 
						} 
					},
					{field : 'totalQty',title : '总笔数',width : 50,align : 'center'},
					{field : 'totalAmt',title : '总金额',width : 50,align : 'center',
						formatter : function(value, rec) {
						return value/100.00;
					}},
					{field : 'approveCount',title : '审核通过<br/>笔数',width : 60,align : 'center'},
					{field : 'approveAmt',title : '审核通过<br/>金额',width : 60,align : 'center',
						formatter : function(value, rec) {
							return value/100.00;
						}},
					{field : 'refuseCount',title : '审核拒绝<br/>笔数',width : 60,align : 'center'},
					{field : 'refuseAmt',title : '审核拒绝<br/>金额',width : 60,align : 'center',
						formatter : function(value, rec) {
							return value/100.00;
						}},
					{field : 'unapproveCount',title : '未审核<br/>笔数',width : 60,align : 'center'},
					{field : 'unapproveAmt',title : '未审核<br/>金额',width : 60,align : 'center',
						formatter : function(value, rec) {
							return value/100.00;
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
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false,
			pagination : true,
			rownumbers : true,
			onClickRow : function(index,row){
				var data = {
						"instead.batchId" : row.id
					   }
				$('#currentBatchId').val(row.id);
					$('#test2').datagrid('load', data);
				$($('#test2').datagrid('getPanel')).panel('expand',true);
			},
			toolbar : [ {
				id : 'btnadd',
				text : '批次审核',
				iconCls : 'icon-ok',
				handler : function() {
					var check= $('#test' ).datagrid( 'getChecked');
					if(check.length!=0){
						var myArray="";
	                    for (var i=0;i<check.length;i++){
	                  	   myArray+=check[i].id+"|"
	                    } 
						$("#auditForm")[0].reset();
						$("#btn_submit").linkbutton('enable');
						$("#icon-cancel").linkbutton('enable');
						$("#auditDataId").val(myArray);
						showAdd();
					}else{
						$.messager.alert('提示',"请选择数据"); 
						
					}
				}
			}, 
			{
				id : 'unapproved',
				text : '未完成',
				iconCls : 'icon-search',
				handler : function() {
					$("#queryStatus").val("01,02");
					search();
				}
			
			
			},
			{
				id : 'approved',
				text : '已审核',
				iconCls : 'icon-search',
				handler : function() {
					$("#queryStatus").val("00,03");
					search();
				}
			
			
			}]
						});
		 
		
		$('#test2')
		.datagrid(
				{
					title : '代付明细审核',
					iconCls : 'icon-save',
					height : 300,
					collapsible:true,
					singleSelect : true,
					nowrap : false,
					striped : true,
					url :'pages/instead/queryInsteadInsteadAction.action', 
					remoteSort : false,
					idField : 'ORGAN_ID',
					columns : [ [
							{
								field : 'ck',
								checkbox : true
							},
							{field : 'id',hidden:true},
							{field : 'orderId',title : '商户订单号',width : 150,align : 'center'},
							{field : 'batchId',hidden:true},
							{
								field : 'insteadPayDataSeqNo',
								title : '代付流水号',
								width : 200,
								align : 'center'
							},
							
							{
								field : 'accType',
								title : '账户类型',
								width : 120,
								align : 'center',
								formatter : function(value, rec) {

									if (value == '02') {
										return '对公账户';
									} else if (value == '01') {
										return '对私账户';
									} 
								}
								
							},
							{
								field : 'amt',
								title : '代付金额(元)',
								width : 120,
								align : 'center'
							},
							{
								field : 'accNo',
								title : '收款账号',
								width : 120,
								align : 'center'
							},
							{
								field : 'accName',
								title : '收款账户名',
								width : 120,
								align : 'center'
							},
							{
								field : 'bankCode',
								title : '收款行行号',
								width : 120,
								align : 'center',
							},
						
							{
								field : 'issInsName',
								title : '收款行名称',
								width : 120,
								align : 'center'
							},
							{
								field : 'status',
								title : '状态',
								width : 120,
								align : 'center',
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
							}
							] ],
					singleSelect : false,
					selectOnCheck : true,
					checkOnSelect : false,
					pagination : true,
					rownumbers : true,
					toolbar : [ {
						id : 'btnadd',
						text : '批次审核',
						iconCls : 'icon-ok',
						handler : function() {
							var check= $('#test2' ).datagrid( 'getChecked');
							if(check.length!=0){
							var myArray="";
                               for (var i=0;i<check.length;i++){
                            	   myArray+=check[i].id+"|";
                              } 
							$("#auditForm")[0].reset();
							$("#btn_submit").linkbutton('enable');
							$("#icon-cancel").linkbutton('enable');
							
							$("#auditDataId_").val(myArray);
							showAdds();
						}else{
							$.messager.alert('提示',"请选择数据"); 
							
						}
						}
					
					
					},
					{
						id : 'unapproved',
						text : '未审核',
						iconCls : 'icon-search',
						handler : function() {
							var data = {
									"instead.batchId" : $('#currentBatchId').val(),
									"instead.status" : "01"
								   };
							$('#test2').datagrid('load', data);
							$($('#test2').datagrid('getPanel')).panel('expand',true);
						}
					
					
					},
					{
						id : 'approved',
						text : '已审核',
						iconCls : 'icon-search',
						handler : function() {
							var data = {
									"instead.batchId" : $('#currentBatchId').val(),
									"instead.status" : "09,11,19,21,29,31,39,00"
								   };
							$('#test2').datagrid('load', data);
							$($('#test2').datagrid('getPanel')).panel('expand',true);
						}
					
					
					}]
				});
		$($('#test2').datagrid('getPanel')).panel('collapse',false);
	});

	function search() {
		var data = {
				"insteadPayBatchQuery.batchNo" : $('#batchno').val(),
				"insteadPayBatchQuery.beginDate":$("#beginDate").datebox("getValue"),
				"insteadPayBatchQuery.endDate":$("#endDate").datebox("getValue"),
				"insteadPayBatchQuery.status":$("#queryStatus").val()
			   }
			$('#test').datagrid('load', data);
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
		$('#w').window('close');
	}

	// 明细审核
	function detailAudit(flag){
		if(flag==true){
			$("#detailAuditForm").attr("action","pages/instead/detailsAuditInsteadAction.action");
			$("#flag_").val("true");
		}else{
			$("#detailAuditForm").attr("action","pages/instead/detailsAuditInsteadAction.action");
			$("#flag_").val("false");
	
		}
		$('#detailAuditForm').form('submit', {  
		    onSubmit: function(){  
			    if($('#detailAuditForm').form('validate')){
			    	$('#btn_submit_').linkbutton('disable');
					$("#icon-cancel_").linkbutton('disable');		
			    	return 	true;
				}
		        return false;   
		    },   
		    success:function(data){  
		    	$.each(JSON.parse(data), function(key, value) {
		    		if (key=="message") 
		    			$.messager.alert('提示',value); 
		    	});
    			search();
	    		closeAdd();
	    		$('#btn_submit_').linkbutton('enable');
				$("#icon-cancel_").linkbutton('enable');		
		    }  
		});   
	}
	// 批量审核
	function batchAudit(flag){
		if(flag==true){
			$("#auditForm").attr("action","pages/instead/batchAuditInsteadAction.action");
			$("#flag").val("true");
		}else{
			$("#auditForm").attr("action","pages/instead/batchAuditInsteadAction.action");
			$("#flag").val("false");
		}
		$('#auditForm').form('submit', {  
		    onSubmit: function(){  
			    if($('#auditForm').form('validate')){
			    	$('#btn_submit').linkbutton('disable');
					$("#icon-cancel").linkbutton('disable');		
			    	return 	true;
				}
		        return false;   
		    },   
		    success:function(data){
		    	$.each(JSON.parse(data), function(key, value) {
		    		if (key=="message") 
		    			$.messager.alert('提示',value); 
		    	});
    			search();
	    		closeAdd();
	    		$('#btn_submit').linkbutton('enable');
				$("#icon-cancel").linkbutton('enable');		
		    }  
		});  
		
	}
	
	
	function secondTrial(flag){
		
		if(flag==true){

				$("#secondTrial").attr("action",
						"pages/transfer/secondAuditByConditionsTransferAction.action?flag=first");
				$("#flags").val("true");
				$("#flag").val("true");
				$("#flagss").val("true");
			}else{

					$("#secondTrial").attr("action",
							"pages/transfer/secondAuditByConditionsTransferAction.action?flag=first");
					$("#flags").val("false");
					$("#flag").val("false");
					$("#flagss").val("false");
		
			}
			$('#secondTrial').form('submit', {  
			    onSubmit: function(){  
				    if($('#secondTrial').form('validate')){
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
			 
			        
			    }  
			});   
		}
</script>
</html>
