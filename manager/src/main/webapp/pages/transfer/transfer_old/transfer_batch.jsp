<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../../../top.jsp"></jsp:include>
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


						<td align="right" width="10%">划拨类型:</td>
						<td colspan="1"><select name="transQuery.acctType"
							class="easyui-validatebox validatebox-text" id="transfertype">
								<option value="">全部</option>
								<option value="01">行内</option>
								<option value="02">跨行</option>
						</select></td>





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
			url : 'pages/transfer/querytransferTransferAction.action?falg=first',
			remoteSort : false,
			idField : 'ORGAN_ID',
			columns : [ [
			         	{
							field : 'ck',
							checkbox : true
						},
			             	
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

				field : 'batchno-',
				title : '操作',
				width : 120,
				align : 'center',
				formatter : function(value, rec) {
					if (rec.batchno != null) {
						return '<a >划拨</a>';
					} else {
						return '';
					}
				}
			} ] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false,
			pagination : true,
			rownumbers : true,
			toolbar : [ {
				id : 'btnadd',
				text : '批量划拨',
				iconCls : 'icon-add',
				handler : function() {
					
					var check = $('#test').datagrid('getChecked');
					if (check.length != 0) {
						var myArray = "";
						for (var i = 0; i < check.length; i++) {
							myArray += check[i].batchno + "|"
						}
						showAdd(myArray);
					} else {
						$.messager.alert('提示', "请选择数据");

					}
				}
			} ]
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

	 function showAdd( myArray) {
		    $.ajax({
	             type: "POST",
	             url: "pages/transfer/transferTransferAction.action",
	             data: {batchno:myArray},
	             dataType: "text",
	             success: function(data){
	            	 $.messager.alert('提示',data); 
	            	 search();
	             }
	             })
		 
		 
		 
	 } 
	/*  function closeAdd() {

	 $('#w').window('close');
	 } */
	
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
