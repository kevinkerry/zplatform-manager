<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ include file="../../top.jsp"%>
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
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">

			<form id="theForm" method="post" action="">
				<table width="100%">
					<tr>
						<td align="right" width="10%">批次号:</td>

						<td align="left" style="padding-left: 5px" width="15%"><input
							name="batchNo" id="batchno" maxlength="32" /></td>
						 
						<td align="right" width="10%">申请日期:</td>
						<td align="left" style="padding-left: 5px" width="30%"><input
							name="beginDate" id="beginDate"
							maxlength="32" />- <input
							id="endDate" maxlength="32" /></td>
					</tr>
					
					<tr>
						<td align="right" width="10%">订单编号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="orderNo" id="orderNo" maxlength="32" />
						</td>		
					<td align="right" rowspan="6"><a href="javascript:search1()"
						 -->
						<td align="right" rowspan="6"><a href="javascript:search1()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>

				<input type="hidden" name="insteadPayBatchQuery.status"
					id="queryStatus" maxlength="32" />
				<!-- <input type="hidden"  id="currentBatchId" maxlength="32" />   -->
			</form>
		</div>

		<!-- 商户信息 -->
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
		<!-- 明细-->
		<div style="margin-top: 5px">
			<table id="test2"></table>
		</div>

	</div>
	<!-- ==============================form传递数据 ============================-->
	<input id="data" type="hidden">
	<input id="flag" type="hidden">
	<!-- ================================明细=审核====================== -->
	<div id="ws" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 70px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; text-align: center"></div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:batchAudit(true)" id="Tongguo">通过</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:batchAudit(false)" id="JuJue">拒绝</a>
			</div>
		</div>
	</div>


	<!-- ================================商户还款=审核====================== -->

	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 70px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; text-align: center"></div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:batchAudit(true)" id="btn_submit">通过</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:batchAudit(false)" id="icon-cancel">拒绝</a>
			</div>
		</div>
	</div>
</body>
<!-- =====================================================script方法开始========================================================= -->
<script>
	$(function() {
		$('#beginDate').datebox();
		$('#endDate').datebox();
		//$("#withdraworcheckbox").unbind();
		
	});
		$('#test').datagrid({
			title : '商户还款批次审核',
			iconCls : 'icon-save',
			height : 300,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url :'pages/fund/merchant_getAllmers.action?', 
			remoteSort : false,
			idField : 'ORGAN_ID',
			collapsible:true,
			columns : [ [
					{field : 'ck',checkbox :true},
					{field : 'tid',hidden:true},
					{field : 'batchNo',title : '批次号',width : 150,align : 'center'},
					{field : 'merId',title : '商户号',width : 150,align : 'center'},
					{field : 'prodcutcode',title : '产品代码',width : 120,align : 'center'},
					{field : 'txnTime',title : '订单时间',width : 90,align : 'center'},
					{field : 'totalQty',title : '总笔数',width : 50,align : 'center'},
					{field : 'totalAmt',title : '总金额',width : 50,align : 'center',
						//formatter : function(value, rec) {
						//return value/100.00;
				//	}
					},
					{field : 'approveCount',title : '审核通过<br/>笔数',width : 60,align : 'center'},
					{field : 'approveAmt',title : '审核通过<br/>金额',width : 60,align : 'center',
						//formatter : function(value, rec) {
						//	return value/100.00;
						//}
					},
					{field : 'refuseCount',title : '审核拒绝<br/>笔数',width : 60,align : 'center'},
					{field : 'refuseAmt',title : '审核拒绝<br/>金额',width : 60,align : 'center',
						//formatter : function(value, rec) {
						//	return value/100.00;
						//}
					},
					{field : 'unapproveCount',title : '未审核<br/>笔数',width : 60,align : 'center'},
					{field : 'unapproveAmt',title : '未审核<br/>金额',width : 60,align : 'center',
						//formatter : function(value, rec) {
						//	return value/100.00;
						//}
					},
					{field : 'status',title : '状态',width : 120,align : 'center',
						formatter : function(value, rec,index) {
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
			onUnselect:function(rowIndex, rowData){
				//当用户取消全选时候触发
				quXiaoQuanXuan(1);
				
			},
			onSelect:function(){
				//当用户选中一行的时候触发
				quanXuan(1);
			},
			onClickRow : function(index,row){
				//拿到批次号，到后台请求数据
				var batchno = row.batchNo;
				$("#batchno").val(batchno);
				search2();
				$("#batchno").val(null);
				
			},
			toolbar : [ {
				id : 'btnadd',
				text : '批次审核',
				iconCls : 'icon-ok',
				handler : function() {
					var check= $('#test').datagrid( 'getChecked');
					if(check.length!=0){
						var myArray="";
	                    for (var i=0;i<check.length;i++){
	                  	   myArray+=check[i].tid+"-";
	                    } 
						$("#data").val(myArray);
							showAdd();
					}else{
						$.messager.alert('提示',"请选择数据"); 
						
					}
				}
			},
			/**
			{
				id : 'unapproved',
				text : '未完成',
				iconCls : 'icon-search',
				handler : function() {
					$("#queryStatus").val("01");
					search3();
				}
			
			
			},
			{
				id : 'approved',
				text : '已审核',
				iconCls : 'icon-search',
				handler : function() {
					$("#queryStatus").val("00");
					search3();
				}
			}  */
			]
		});
		
	
		
		$('#test2').datagrid(
				{
					title : '明细审核',
					iconCls : 'icon-save',
					height : 300,
					collapsible:true,
					singleSelect : true,
					nowrap : false,
					striped : true,
					url :'pages/fund/merchant_getMessage.action', 
					remoteSort : false,
					idField : 'ORGAN_ID',
					columns : [ [
							{
								field : 'ck',
								checkbox : true
							},
							{field : 'tid',hidden:true},
							{
								field : 'orderId',
								title : '订单号',
								width : 200,
								align : 'center'
							},
							{field : 'investors',title : '投资人代码',width : 150,align : 'center'},
							{field : 'batchId',hidden:true},
							{
								field : 'porductCode',
								title : '产品代码',
								width : 120,
								align : 'center',
							},
							{
								field : 'txnamt',
								title : '本金',
								width : 120,
								align : 'center'
							},
							{
								field : 'interset',
								title : '利息',
								width : 120,
								align : 'center'
							},
							{
								field : 'status',
								title : '状态',
								width : 120,
								align : 'center',
								formatter : function(value, rec,index) {
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
					onUnselect:function(rowIndex, rowData){
						//当用户取消全选时候触发
						quXiaoQuanXuan(2);
						
					},
					onSelect:function(){
						//当用户选中一行的时候触发
						quanXuan(2);
					},
					toolbar : [ {
						id : 'btnadd',
						text : '批次审核',
						iconCls : 'icon-ok',
						handler : function() {
							var check= $('#test2' ).datagrid( 'getChecked');
							if(check.length!=0){
							var myArray="";
                               for (var i=0;i<check.length;i++){
                            	   myArray+=check[i].tid+"//";
                              } 
                         
							//给form赋值
							$("#data").val(myArray);
							showAdds();
						}else{
							$.messager.alert('提示',"请选择数据"); 
							
						}
						}
					
					
					},
					/**
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
					       $($('#test2').datagrid('getPanel')).panel('collapse',false);
						}
					
					}*/
					]
				});
		
	  
	
	
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
	
	//======================================条件查询===============================================
		function search2(){
		  //按照批次号查询
		   if($("#batchno")!=null){
				var data = {
					"batchno" : $("#batchno").val(),
				   };
			
			$('#test2').datagrid('load', data);
			
		   }
	}
		//点击查询按钮之后查询
	function search1(){
		if($("#batchno").val()!=""){
			var data = {
					"batchno" : $("#batchno").val(),
				   };
			$('#test').datagrid('load', data);
			search2();
		}else if($("#orderNo").val()!=""){
			searchByOrder($("#orderNo").val());
		}else if($('#beginDate').datebox("getValue")!=""||$("#endDate").datebox("getValue")!=""){
				selectByDate();
		}else{
			window.location.reload(true);
		}
	}
		
	//按订单号查询
	function searchByOrder(order){
		$.ajax({
			"type" :"post",
			"url" :  "<%=basePath%>" +'pages/fund/merchant_QueryByOrder.action',
			"data" : {"orderId":order},
			dataType : "json",
			success:function(mess){
				if(mess.success==1){
					alert("按订单查询失败！"); 
				}else if(mess.success == 2){
					alert("查询订单不存在！");
				}else{
					var data = mess.rows;
					$('#test2').datagrid('loadData', { total: mess.total, rows: data });
					$('#test').datagrid('loadData', { total: 0, rows: [] });
					
				}
			}
		});
	}
	
	//按照时间查询
	function selectByDate(){
		$.ajax({
			"type" :"post",
			"url" :"<%=basePath%>" +'pages/fund/merchant_QueryByDate.action',
			"data" :{"beginDate":$('#beginDate').datebox("getValue"),"endDate":$('#endDate').datebox("getValue")},
			dataType : "json",
			success:function(mess){
				if(mess.success){
					$('#test').datagrid('loadData', { total: mess.total, rows: mess.rows});
					$('#test2').datagrid('loadData', { total: 0, rows: [] });
				}else{
					alert("查询数据不存在！");
				}
			}
		});
	}
	//审核通过或者取消
	function batchAudit(ok){
		if(ok){
			//点击确定，提交数据。
			tijiao();
		}else{
		    closeAdd();
		    window.location.reload(true);
		}
	}
	
	function tijiao(){
		$.ajax({
			"type":"post",
			"url" : "<%=basePath%>" +'pages/fund/merchant_getPag.action',
			"data" :{"chackBoxData":$("#data").val(),"biaoJi":$("#flag").val()},
			dataType: "json",
			success:function(data){
				if(data.success==1){
					closeAdd();
	        	window.location.reload(true);
				}else{
					closeAdd();
					alert("审核失败");
				window.location.reload(true);
				}
			}
		});
	}
	//取消全选复选框状态
	function quXiaoQuanXuan(flg){
		if(flg ==1){
	    	$("div.datagrid-header-check input:first").removeAttr("checked");
		}else if(flg ==2){
			$("div.datagrid-header-check input:last").removeAttr("checked");
		}
	}
	//选中全选复选框状态
	function quanXuan(flg){
		if(flg == 1){
		var allrows = $('#test').datagrid('getData').total;
		var check= $('#test' ).datagrid( 'getChecked').length;
		if(allrows == check){
			//将复选框至为选中状态
			$("div.datagrid-header-check input:first").attr("checked",true);
		}
		}else if(flg == 2){
			var allrows = $('#test2').datagrid('getData').total;
			var check= $('#test2' ).datagrid( 'getChecked').length;
			if(allrows == check){
				//将复选框至为选中状态
				$("div.datagrid-header-check input:last").attr("checked",true);
			}
		}
	}
	
</script>
</html>
