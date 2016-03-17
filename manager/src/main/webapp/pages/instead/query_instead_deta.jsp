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
						<td align="right" width="10%">一级商户号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.merId" id="merId" maxlength="32" /></td>

						<td align="right" width="10%">商户订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.orderId" id="orderId" maxlength="32" /></td>



<!-- 						<td align="right" width="10%">批次号:</td> -->
<!-- 						<td align="left" style="padding-left: 5px" width="15%"><input -->
<!-- 							name="instead.batchNo" id="batchFileNo" maxlength="32" /></td> -->


						<td align="right" width="10%">代付状态:</td>
						<td colspan="1"><select name="instead.status"
							class="easyui-validatebox validatebox-text" id="status">
								<option value="">请选择</option>
								<option value="01">待初审</option>
								<option value="09">初审未过</option>
								<option value="00">充值成功</option>
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
				<form id="firstTrial"   method="post" action="pages/withdraw/queryTrialWithdraTriaAction.action" enctype="multipart/form-data">
				导入文件:<input  id="file" type="file" name="file">
			</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:filel()" id="btn_submit" onclick="">上传</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"  id="icon-cancel" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
</body>

<script>
	var width = $("#continer").width();
	$(function() {
		$("#withdraworcheckbox").unbind();
		$('#test').datagrid({
			title : '代付信息',
			iconCls : 'icon-save',
			height : 400,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url : 'pages/instead/queryInsteadInsteadAction.action',
			remoteSort : false,
			idField : 'ORGAN_ID',
			columns : [ [
			           	{
							field : 'merId',
							title : '商户编号',
							width : 120,
							align : 'center'
						},

			{
				field : 'orderId',
				title : '商户订单号',
				width : 120,
				align : 'center'
			}, {
				field : 'amt',
				title : '单笔金额',
				width : 120,
				align : 'center'
			}, {
				field : 'accType',
				title : '账号类型',
				width : 120,
				align : 'center',
				formatter : function(value, rec) {
					if (value == '02') {
						return '商户';
					} else if (value == '01') {
						return '个人';
					} else {
						return '';
					}
				}
			}, {
				field : 'accNo',
				title : '账号',
				width : 120,
				align : 'center'
			},

			{
				field : 'accName',
				title : '户名',
				width : 120,
				align : 'center'
			}, {
				field : 'bankCode',
				title : '开户行代码',
				width : 180,
				align : 'center'
			}, {
				field : 'status',
				title : '状态',
				width : 180,
				align : 'center',
				formatter : function(value, rec) {

					if (value == '01') {
						return '待初审';
					} else if (value == '09') {
						return '初审未过';
					} else if (value == '11') {
						return '待复审';
					} else if (value == '19') {
						return '复审未过';
					} else if (value == '21') {
						return '等待批处理';
					} else if (value == '29') {
						return '批处理失败';
					} else if (value == '00') {
						return '提现成功';
					} else if (value == '39') {
						return '自行终止';
					} else {
						return '';
					}
				}
			}
// 			, {
// 				field : 'insteadPayDataSeqNo',
// 				title : '批次号',
// 				width : 180,
// 				align : 'center'
// 			} 
			] ],
			/* singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false, */
			pagination : true,
			rownumbers : true,
			toolbar : [ {
				id : 'btnadd',
				text : '导入划拨数据',
				iconCls : 'icon-add',
				handler : function() {
					$("#btn_submit").linkbutton('enable');
					$("#icon-cancel").linkbutton('enable');
					
					$("#firstTrial")[0].reset();
					showAdd();
				}
				}
			 ] 
		});
	});

	function search() {
		var data = {
			"instead.merId" : $('#merId').val(),
			"instead.orderId" : $('#orderId').val(),
			"instead.batchFileNo":$('#batchFileNo').val(),
			"instead.status" : $('#status').val()
		}
		$('#test').datagrid('load', data);
	}
	function closeAdd() {

		$('#w').window('close');
	}

	function showAdd() {
		
		$('#w').window({
			title : '导入文件',
			top : 100,
			width : 400,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height : 300
		});
	} 
	 function 	filel(){
		 $("#firstTrial").attr("action",
			"pages/instead/fileInsteadAction.action");
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
			 
			        
			    }  
			});  
		 
	 }

</script>
</html>
