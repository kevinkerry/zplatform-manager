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

						<td align="right" width="10%">批次号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="transQuery.batchno" id="batchno" maxlength="32" /></td>

						<td align="right" width="10%">状态:</td>
						<td colspan="1"><select name="transQuery.busicode"
							class="easyui-validatebox validatebox-text" id="status">
								<option value="">全部</option>
								<option value="01">初始</option>
								<option value="02">正在划拨</option>
								<option value="03">划拨失败</option>
								<option value="00">划拨完成</option>


						</select></td>

						<td align="right" width="10%">划拨类型:</td>
						<td colspan="1"><select name="transQuery.acctType"
							class="easyui-validatebox validatebox-text" id="transfertype">
								<option value="">全部</option>
								<option value="01">行内</option>
								<option value="02">跨行</option>
						</select></td>
						<td align="right" width="10%">账务处理状态:</td>
						<td colspan="1"><select name="transQuery.status"
							class="easyui-validatebox validatebox-text" id="accstatus">
								<option value="">全部</option>
								<option value="00">处理完成</option>
								<option value="01">等待处理</option>
								<option value="02">处理中</option>
								<option value="03">处理失败</option>
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



</body>

<script>
	var width = $("#continer").width();
	$(function() {
		$("#withdraworcheckbox").unbind();
		$('#test').datagrid({
			title : '资金划拨',
			iconCls : 'icon-save',
			height : 400,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url : 'pages/transfer/querytransferTransferAction.action',
			remoteSort : false,
			idField : 'ORGAN_ID',
			columns : [ [

			{
				field : 'batchno',
				title : '批次号',
				width : 120,
				align : 'center'
			}, {
				field : 'sumitem',
				title : '总笔数',
				width : 120,
				align : 'center'
			},

			{
				field : 'sumMoney',
				title : '总金额',
				width : 120,
				align : 'center'

			}, {
				field : 'succitem',
				title : '成功笔数',
				width : 120,
				align : 'center'
			}, {
				field : 'succMoney',
				title : '成功金额',
				width : 120,
				align : 'center'
			}, {
				field : 'failitem',
				title : '失败笔数',
				width : 120,
				align : 'center'
			}, {
				field : 'failMoney',
				title : '失败金额',
				width : 120,
				align : 'center',
			},

			{
				field : 'status',
				title : '状态',
				width : 120,
				align : 'center',
				formatter : function(value, rec) {
					if (value == '01') {

						return '初始';
					} else if (value == "02") {
						return '正在划拨';
					} else if (value == "03") {
						return '划拨失败';
					} else if (value == "00") {
						return '划拨完成';
					} else {
						return '';

					}

				}

			},

			{
				field : 'createtime',
				title : '创建时间',
				width : 120,
				align : 'center'
			}, {
				field : 'transfertime',
				title : '划拨时间',
				width : 120,
				align : 'center'
			}, {
				field : 'retrytimes',
				title : '重试次数',
				width : 120,
				align : 'center'
			}, {
				field : 'accstatus',
				title : '账务处理状态',
				width : 120,
				align : 'center',

				formatter : function(value, rec) {
					if (value == '00') {
						return '处理完成';
					} else if (value == "01") {
						return '等待处理';
					} else if (value == "02") {
						return '处理中';
					} else if (value == "03") {
						return '处理失败';
					} else {
						return '';

					}

				}
			} /* ,
												{
												00-，-，-，-
													field : 'batchno-',
													title : '操作',
													width : 120,
													align : 'center',
													formatter : function(value, rec) {
														if (rec.batchno !=null) {
															return '<a>划拨</a>';
														} else {
															return '';
														}
													} 
												} */] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false,
			pagination : true,
			rownumbers : true
		/* 	,
						toolbar : [ {
									id : 'btnadd',
									text : '批量审核',
									iconCls : 'icon-add',
									handler : function() {
										var check= $('#test' ).datagrid( 'getChecked');
										if(check.length!=0){
										var myArray="";
		                                   for (var i=0;i<check.length;i++){
		                                	   myArray+=check[i].withdraworderno+"|"
		                                  } 
										$("#firstTrial")[0].reset();
										$("#btn_submit").linkbutton('enable');
										$("#withdraworderno").val(myArray);
										showAdd();
									}else{
										$.messager.alert('提示',"请选择数据"); 
										
									}
									}
								} ] */
		});
	});

	function search() {

		var data = {
			"tbq.batchno" : $('#batchno').val(),
			"tbq.status" : $('#status').val(),
			"tbq.transfertype" : $('#transfertype').val(),
			"tbq.accstatus" : $('#accstatus').val()
		}
		$('#test').datagrid('load', data);
	}
	/* 
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
	
	 /* function a(falg){
	 if(falg==true){
	
	 $("#firstTrial").attr("action",
	 "pages/withdraw/queryTrialWithdraTriaAction.action");
	 }else{
	 $("#firstTrial").attr("action",
	 "pages/withdraw/queryTrialWithdraTriaAction.action");
	 }
	
	 } 
	
	
	 function firstTrial(falg){
	 if(falg==true){
	 $("#firstTrial").attr("action",
	 "pages/withdraw/firstTrialTriaAction.action");
	 $("#falg").val("true");
	 }else{
	 $("#firstTrial").attr("action",
	 "pages/withdraw/firstTrialTriaAction.action");
	 $("#falg").val("false");
	
	 }
	 $('#firstTrial').form('submit', {  
	 onSubmit: function(){  
	 if($('#firstTrial').form('validate')){
	 $('#btn_submit').linkbutton('disable');		
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
	 */
</script>
</html>
